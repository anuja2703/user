package com.example.user;

public class model {
    String name,email,mno,Recharge,UnitsConsumed,UnitsRemaining;

    public model() {
    }

    public model(String name, String email, String mno) {

        this.name = name;
        this.email = email;
        this.mno = mno;
        //this.Recharge=Recharge;
        //this.UnitsConsumed=UnitsConsumed;
        //this.UnitsRemaining=UnitsRemaining;
    }

    public String getUnitsConsumed() {
        return UnitsConsumed;
    }

    public void setUnitsConsumed(String unitsConsumed) {
        UnitsConsumed = unitsConsumed;
    }

    public String getUnitsRemaining() {
        return UnitsRemaining;
    }

    public void setUnitsRemaining(String unitsRemaining) {
        UnitsRemaining = unitsRemaining;
    }

    public String getRecharge() {
        return Recharge;
    }

    public void setRecharge(String recharge) {
        Recharge = recharge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }
}
