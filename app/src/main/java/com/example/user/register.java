package com.example.user;

public class register {
    public String name,mno,email;
    public String Recharge;
    public String UnitsConsumed,UnitsRemaining,BluetoothAddress;

    public register(){

    }
    public register(String name, String mno, String email,String BluetoothAddress,String Recharge,String UnitsConsumed,String UnitsRemaining){
        this.name=name;
        this.mno=mno;
        this.email=email;
        this.BluetoothAddress=BluetoothAddress;
        this.Recharge=Recharge;
        this.UnitsConsumed=UnitsConsumed;
        this.UnitsRemaining=UnitsRemaining;
    }
}
