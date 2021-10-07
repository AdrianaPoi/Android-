package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProvidersList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_providers_list);
        DataBase dataBase= Room.databaseBuilder(this,DataBase.class,"proiectDAMDB").allowMainThreadQueries().build();
        List<ProviderTable> list=new ArrayList<>();
        list=dataBase.getDAOProviderTable().selectAllUsers();
        ArrayAdapter<ProviderTable> adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list);
        ListView lv=findViewById(R.id.lvProviders);
        lv.setAdapter(adapter);
    }
}