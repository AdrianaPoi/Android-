package com.example.login;

public class Exchange {
    private String curs;
    private double vanzare;
    private double cumparare;

    public Exchange(){
        this.curs="";
        this.vanzare=0;
        this.cumparare=0;
    }


    public Exchange(String curs, double vanzare, double cumparare) {
        this.curs = curs;
        this.vanzare = vanzare;
        this.cumparare = cumparare;
    }

    public String getCurs() {
        return curs;
    }

    public void setCurs(String curs) {
        this.curs = curs;
    }

    public double getVanzare() {
        return vanzare;
    }

    public void setVanzare(double vanzare) {
        this.vanzare = vanzare;
    }

    public double getCumparare() {
        return cumparare;
    }

    public void setCumparare(double cumparare) {
        this.cumparare = cumparare;
    }

    @Override
    public String toString() {
        return curs + ": sell= " + vanzare +
                ", buy=" + cumparare;
    }
}
