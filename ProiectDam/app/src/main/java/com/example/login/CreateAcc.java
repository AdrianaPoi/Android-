package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CreateAcc extends AppCompatActivity {

    private TextView tvData;
    EditText edFirstName,edLastName,edEmail,edPassword,edUsername;
    private User user;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    FirebaseDatabase database;
    DatabaseReference ref;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        tvData = (TextView) findViewById(R.id.tvDate);

        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int an = calendar.get(Calendar.YEAR);
                int luna = calendar.get(Calendar.MONTH);
                int zi = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateAcc.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        an,luna,zi);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int an, int luna, int zi) {
                luna = luna+ 1;


                data = zi + "/" + luna + "/" + an;
                tvData.setText(data);
            }
        };
        edFirstName=(EditText)findViewById(R.id.etFName);
        edLastName=(EditText)findViewById(R.id.etLName);
        edEmail=(EditText)findViewById(R.id.etEmail);
        edPassword=(EditText)findViewById(R.id.etPassword);
        edUsername=(EditText)findViewById(R.id.etUser);
        user=new User();
        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("User");
    }

    public void createAcc(View view) {
        SharedPreferences sp;
        sp = getSharedPreferences("sharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        EditText edt=findViewById(R.id.etFName);
        String fname=edt.getText().toString();
        editor.putString("firstName",fname);
        EditText edLname=findViewById(R.id.etLName);
        String Lname=edt.getText().toString();
        editor.putString("lastName",Lname);
        EditText eduser=findViewById(R.id.etUser);
        String username=edt.getText().toString();
        editor.putString("userName",username);
        editor.commit();
        Intent intent=new Intent();
        EditText edUser=findViewById(R.id.etUser);
        String userName=edUser.getText().toString();
        intent.putExtra("info",userName);
        setResult(RESULT_OK,intent);
        finish();
        user.setFirstName(edFirstName.getText().toString());
        user.setLastName(edLastName.getText().toString());
        user.setEmail(edEmail.getText().toString());
        user.setUsername(edUsername.getText().toString());
        user.setPassword(edPassword.getText().toString());
        user.setBirthDate(data);
        ref.child(user.getUsername()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()&&(user.getFirstName().length()!=0)){
                    Toast.makeText(CreateAcc.this, R.string.userCreated,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(CreateAcc.this, R.string.error,Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}