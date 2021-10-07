package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void addBill(View view) {
        Intent intent=new Intent();
        EditText edtProvider=findViewById(R.id.etProd);
        String prov=edtProvider.getText().toString();
        intent.putExtra("furnizor",prov);
        EditText edtdateEm=findViewById(R.id.etDataEmitere);
        String dataE=edtdateEm.getText().toString();
        intent.putExtra("dataE",dataE);
        EditText edtdateSc=findViewById(R.id.etDataScadenta);
        String dataS=edtdateSc.getText().toString();
        intent.putExtra("dataS",dataS);
        EditText edtSuma=findViewById(R.id.etSuma);
        float suma=Float.parseFloat(edtSuma.getText().toString());
        intent.putExtra("suma",suma);

        setResult(RESULT_OK,intent);
        finish();
    }
}