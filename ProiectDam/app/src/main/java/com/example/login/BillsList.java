package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.SparseBooleanArray;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillsList extends AppCompatActivity {

    ArrayList<Bill> bills;
    ListView lv;
    BillAdapter adapter;
    TextView tvUsername;
    Bill facturaEliminata;
    DataBase dataBase;
    int reqCode=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_list);
        dataBase= Room.databaseBuilder(this,DataBase.class,"proiectDAMDB").allowMainThreadQueries().build();

        final String username=getIntent().getStringExtra("username");

        tvUsername=findViewById(R.id.tvUserrrname);
        tvUsername.setText(getString(R.string.logginAs)+username);

        Button btn=findViewById(R.id.bIstoric);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),History.class);
                startActivity(it);
            }
        });
        Button btnAdaugare=findViewById(R.id.btnAddBill);
        btnAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),AddBill.class);
                startActivityForResult(it,reqCode);
            }
        });
        bills=new ArrayList<Bill>();
        bills.add(new Bill("Altex","12/10/2020","30/12/2020",100));
        bills.add(new Bill("Telekom","12/10/2020","30/12/2020",100));
        ProviderTable providerTable=new ProviderTable("Altex");
        dataBase.getDAOProviderTable().inserareProviderInBD(providerTable);
        BillTable billTable=new BillTable("Altex","12/12/2020","30/12/2020",350,providerTable.getProviderID());
        dataBase.getDAOBillTable().inserareFacturaInBD(billTable);
        lv=findViewById(R.id.listView);
        adapter=new BillAdapter(this,bills);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position=i;
                new AlertDialog.Builder(BillsList.this).setIcon(android.R.drawable.ic_delete).setTitle(R.string.areYouSure).setMessage(R.string.moveThisBill)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                facturaEliminata=bills.get(position);
                                ProviderTable providerTable2=new ProviderTable(facturaEliminata.getFurnizor());
                                dataBase.getDAOBillTable().inserareFacturaInBD(new BillTable(facturaEliminata.getFurnizor(),facturaEliminata.getDataEmitere(),facturaEliminata.getDataScadenta(),facturaEliminata.getSuma(),providerTable2.getProviderID()));
                                bills.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton(R.string.no,null).show();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==reqCode) {
           if (resultCode == RESULT_OK) {
               String furnizor=data.getStringExtra("furnizor");
               String dataE=data.getStringExtra("dataE");
               String dataS=data.getStringExtra("dataS");
               float suma=data.getFloatExtra("suma",0);
               Bill bill=new Bill(furnizor,dataE,dataS,suma);
               ProviderTable providerTable3=new ProviderTable(furnizor);
               dataBase.getDAOProviderTable().inserareProviderInBD(new ProviderTable(furnizor));
               bills.add(bill);
               adapter.notifyDataSetChanged();

            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, R.string.anulare, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showList(View view) {



        lv=(ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setScrollContainer(false);
    }


    public void showDetails(View view) {
        Intent it=new Intent(getApplicationContext(),ProviderDetails.class);
        startActivity(it);
    }
}