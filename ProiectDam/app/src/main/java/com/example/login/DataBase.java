package com.example.login;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={ProviderTable.class,BillTable.class},version = 1)
public abstract class DataBase extends RoomDatabase{
    public abstract DAOProviderTable getDAOProviderTable();
    public abstract DAOBillTable getDAOBillTable();
}
