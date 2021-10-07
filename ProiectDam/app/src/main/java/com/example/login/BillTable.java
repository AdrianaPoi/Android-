package com.example.login;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "BillTable")
public class BillTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="Furnizor")
    private String furnizor;
    @ColumnInfo(name="DataEmitere")
    private String dataEmitere;
    @ColumnInfo(name="DataScadenta")
    private String dataScadenta;
    @ColumnInfo(name="Suma")
    private float suma;
    @ForeignKey(entity = ProviderTable.class,parentColumns = "providerID",childColumns = "fProviderID",onDelete = CASCADE)
    private int fProviderID;

    @Ignore
    public BillTable() {
    }

    public BillTable(int id, String furnizor, String dataEmitere, String dataScadenta, float suma, int fProviderID) {
        this.id = id;
        this.furnizor = furnizor;
        this.dataEmitere = dataEmitere;
        this.dataScadenta = dataScadenta;
        this.suma = suma;
        this.fProviderID = fProviderID;
    }
   @Ignore
    public BillTable(String furnizor, String dataEmitere, String dataScadenta, float suma, int fProviderID) {
        this.furnizor = furnizor;
        this.dataEmitere = dataEmitere;
        this.dataScadenta = dataScadenta;
        this.suma = suma;
        this.fProviderID = fProviderID;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFurnizor() {
        return furnizor;
    }

    public void setFurnizor(String furnizor) {
        this.furnizor = furnizor;
    }

    public String getDataEmitere() {
        return dataEmitere;
    }

    public void setDataEmitere(String dataEmitere) {
        this.dataEmitere = dataEmitere;
    }

    public String getDataScadenta() {
        return dataScadenta;
    }

    public void setDataScadenta(String dataScadenta) {
        this.dataScadenta = dataScadenta;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public int getFProviderID() {
        return fProviderID;
    }

    public void setFProviderID(int fProviderID) {
        this.fProviderID = fProviderID;
    }

    @Override
    public String toString() {
        return id +". "+"Provider: "+ furnizor +
                ", date of issue: " + dataEmitere +
                ", due date: " + dataScadenta  +
                ", payment amount: " + suma +";";
    }
}
