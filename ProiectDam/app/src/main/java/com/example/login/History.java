package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class History extends AppCompatActivity {

    private static DataBase database;
    List<BillTable> list;
    ArrayAdapter<BillTable> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        database= Room.databaseBuilder(this,DataBase.class,"proiectDAMDB").allowMainThreadQueries().build();
        list=new ArrayList<>();
        list=database.getDAOBillTable().selectAllBills();
        adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list);
        ListView lv=findViewById(R.id.lvHistory);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);



    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle(R.string.selectOption);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            if(item.getItemId()==R.id.optiuneDelete){
                list.remove(info.position);
                adapter.notifyDataSetChanged();
                database.getDAOBillTable().delete(list.get(info.position));}
             else if(item.getItemId()==R.id.optiuneUpdate){
                 BillTable bill=list.get(info.position);
                 list.remove(info.position);
                 adapter.notifyDataSetChanged();
                 database.getDAOBillTable().update(bill.getSuma()*2,bill.getId());
                 list.add(bill);
                 adapter.notifyDataSetChanged();
             }
                 else return false;
           return true;

    }

    public void showProvidersList(View view){
        Intent it=new Intent(getApplicationContext(),ProvidersList.class);
        startActivity(it);
    }
}