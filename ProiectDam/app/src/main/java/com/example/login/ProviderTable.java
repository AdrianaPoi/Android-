package com.example.login;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProviderTable")
public class ProviderTable {
    @PrimaryKey(autoGenerate = true)
    private int providerID;
    @ColumnInfo(name="Provider")
    private String providerName;

    @Ignore
    public ProviderTable(){

    }

    public ProviderTable(int providerID, String providerName) {
        this.providerID = providerID;
        this.providerName = providerName;
    }

    @Ignore
    public ProviderTable(String providerName) {
        this.providerName = providerName;
    }

    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public String toString() {
        return providerID +". "+ providerName ;

    }
}
