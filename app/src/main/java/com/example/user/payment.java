package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.util.Objects;

public class payment extends AppCompatActivity implements PaymentResultWithDataListener {

    AlertDialog.Builder builder;
    Button confirmpay;
    String paydisplayname,paymobdisplay,payemaildisplay;
    String units;
    private EditText editpay;
    FirebaseDatabase paydb1=FirebaseDatabase.getInstance();
    DatabaseReference payroot1=paydb1.getReference().child("Users");
    public FirebaseUser payuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        confirmpay=(Button)findViewById(R.id.Pay_btn);
        editpay=(EditText)findViewById(R.id.input_amt);
        builder=new AlertDialog.Builder(this);
        payuser= FirebaseAuth.getInstance().getCurrentUser();
        assert payuser != null;







        confirmpay.setOnClickListener(v -> {
            if(editpay.getText().toString().equals(""))
            {
                Toast.makeText(payment.this, "Please Fill Payment", Toast.LENGTH_SHORT).show();

            }
            startPayment();
        });
//////mmjjj
    }


    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_YUvVuykvtNCKPz");
        Checkout.preload(getApplicationContext());

        /**
         * Set your logo here
         */
        // checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razor pay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Water Meter Payment");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //            options.put("order_id", "order_H8UaIF6EQDC6EO");//from response of step 3.
            options.put("theme.color", "#ffffff");
            options.put("currency", "INR");
            String payment=editpay.getText().toString();
            double total=Double.parseDouble(payment);
            total=total*100;
            options.put("amount", total);//pass amount in currency subunits
            options.put("prefill.email", payemaildisplay);
            options.put("prefill.contact",paymobdisplay);

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", false);
            retryObj.put("max_count", 0);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e("Error", "Error at initialization of Razorpay Checkout" + e.getMessage());
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

        String userid=payuser.getUid();

        payroot1.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                register uprofile=snapshot.getValue(register.class);
                if(uprofile!=null)
                {
                    paydisplayname=uprofile.name;
                    paymobdisplay=uprofile.mno;
                    payemaildisplay=uprofile.email;
                    units=uprofile.UnitsRemaining;
                }
            }

            ////mmmm
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Payment is Successful");
        alertDialog.setMessage("Your payment details are:\nAmount paid: "+editpay.getText().toString()+"\nOrder_ID: "+s+"\nPayment_ID: "+paymentData.getPaymentId()+"\nSignature: "+paymentData.getSignature());
        alertDialog.setButton(Dialog.BUTTON_POSITIVE,"OK", (Message) null);
        alertDialog.show();
        Toast.makeText(this, "payment success " + s + "\n"+paymentData.getUserEmail()+"\n"+paymentData.getUserContact(), Toast.LENGTH_LONG).show();
        //String userid=payuser.getUid();
        payroot1.child(userid).child("Recharge").setValue(editpay.getText().toString());
        DatabaseReference payroot2=paydb1.getReference().child("Rate");

        payroot2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(units);
                //String rt=snapshot.getValue(String.class);
                //int rate=Integer.parseInt(rt);
                //System.out.println(rate);
                //int unit=Integer.parseInt(units)+Integer.parseInt(editpay.getText().toString())/45;
                //System.out.println(unit);
                //payroot1.child(userid).child("UnitsRemaining").setValue("10");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(payment.this,""+error,Toast.LENGTH_LONG).show();
                AlertDialog alertDialog=builder.create();
                alertDialog.setTitle("Payment failed! Try again.");
                alertDialog.show();
            }
        });


        }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this, "Payment failed " + s, Toast.LENGTH_SHORT).show();
        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Payment failed! Try again.");
        alertDialog.show();
    }
    //jjj
    /*@Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "payment success " + s, Toast.LENGTH_SHORT).show();
        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Payment is Successfull");
        alertDialog.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment failed " + s, Toast.LENGTH_SHORT).show();
        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Payment failed! Try again.");
        alertDialog.show();
    }*/
}