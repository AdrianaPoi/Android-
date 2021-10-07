package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int requestCode=101;
    EditText edtUsername,edtPassword;
    private DatabaseReference ref;
    String parola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername=(EditText)findViewById(R.id.etUsername);
        edtPassword=(EditText)findViewById(R.id.etPass);
        ref= FirebaseDatabase.getInstance().getReference().child("User");
        Button btn=findViewById(R.id.bLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskClass runner = new AsyncTaskClass();
                String sleepTime ="3";
                runner.execute(sleepTime);

                String username=edtUsername.getText().toString();
                parola=edtPassword.getText().toString();
                ref=ref.child(username);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            User user=dataSnapshot.getValue(User.class);
                            if(parola.equals(user.getPassword())){
                                Toast.makeText(MainActivity.this, R.string.loginSuccessfull,Toast.LENGTH_LONG).show();
                                Intent it=new Intent(getApplicationContext(),BillsList.class);
                                it.putExtra("username",user.getUsername());
                                startActivity(it);
                            }
                            else{
                                Toast.makeText(MainActivity.this, R.string.enterCorrectPassword,Toast.LENGTH_LONG).show();

                            }
                        }else{
                            Toast.makeText(MainActivity.this, R.string.userDoesntExist,Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        TextView tv=findViewById(R.id.tvSignUp);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),CreateAcc.class);
                startActivityForResult(it,requestCode);


            }
        });
        ExchangeParser xmlParser=new ExchangeParser(){
            @Override
            protected void onPostExecute(List<Exchange> cursuri) {
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                List<String> cursuriSchimb=new ArrayList<>();
                for(Exchange curs:cursuri){
                    cursuriSchimb.add(curs.toString());
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,cursuriSchimb);
                spinner.setAdapter(adapter);

            }
        };

        xmlParser.execute("https://www.bancatransilvania.ro/jsn/exchange.php");


    }
    public class AsyncTaskClass extends AsyncTask<String, String, String> {
        private String resp;
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Sleeping...");
            try {
                int time = Integer.parseInt(strings[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + strings[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }
        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    getString(R.string.progressDialog),
                    getString(R.string.wait));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==requestCode)
            if(resultCode==RESULT_OK){
                SharedPreferences sp;
                sp = getSharedPreferences("sharedPref", MODE_PRIVATE);
                String mesaj = sp.getString("firstName", "");
                String mesaj2 = sp.getString("lastName", "");
                String mesaj3 = sp.getString("userName", "");
                if(mesaj.length()>3) {
                    Toast.makeText(this, "User " + mesaj3 + " was created with name " + mesaj + " " + mesaj2,
                            Toast.LENGTH_LONG).show();
                }
                EditText etUser=findViewById(R.id.etUsername);
                etUser.setText(data.getStringExtra("info"));
            }
        if(requestCode==RESULT_CANCELED){
            Toast.makeText(this, R.string.anulare,Toast.LENGTH_SHORT).show();
        }
    }
}