package com.example.login;

public class Bill {
    private String furnizor;
    private String dataEmitere;
    private String dataScadenta;
    private float suma;

    public Bill(String furnizor, String dataEmitere, String dataScadenta, float suma) {
        this.furnizor = furnizor;
        this.dataEmitere = dataEmitere;
        this.dataScadenta = dataScadenta;
        this.suma = suma;
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

    @Override
    public String toString() {
        return "Bill{" +
                "furnizor='" + furnizor + '\'' +
                ", dataEmitere='" + dataEmitere + '\'' +
                ", dataScadenta='" + dataScadenta + '\'' +
                ", suma=" + suma +
                '}';
    }
}
