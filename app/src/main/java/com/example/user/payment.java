package com.example.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

//import android.view.View;

public class payment extends AppCompatActivity implements PaymentResultWithDataListener {
    Button confirmpay;
    String paydisplayname,paymobdisplay,payemaildisplay,val;
    String units;
    EditText editpay;
    int rate;
    FirebaseUser user;
    DatabaseReference dbref,dbref2;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        confirmpay=(Button)findViewById(R.id.Pay_btn);
        editpay=(EditText)findViewById(R.id.input_amt);

        confirmpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val=editpay.getText().toString();
                if(val.isEmpty())
                {
                    editpay.setError("Enter the name");
                    editpay.requestFocus();
                    return;
                }
                startPayment();
            }
        });



    }


    private void startPayment() {
        user=FirebaseAuth.getInstance().getCurrentUser();
        dbref=FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        dbref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                register userinfo=snapshot.getValue(register.class);
                if(userinfo!=null)
                {
                    paydisplayname=userinfo.name;
                    paymobdisplay=userinfo.mno;
                    payemaildisplay=userinfo.email;
                    units=userinfo.UnitsRemaining;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
try{
        user=FirebaseAuth.getInstance().getCurrentUser();
        dbref=FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        dbref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                register userinfo=snapshot.getValue(register.class);
                if(userinfo!=null)
                {
                    paydisplayname=userinfo.name;
                    paymobdisplay=userinfo.mno;
                    payemaildisplay=userinfo.email;
                    units=userinfo.UnitsRemaining;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            dbref2=FirebaseDatabase.getInstance().getReference().child("Rate");
            dbref2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    rate=Integer.parseInt(snapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            int unit=Integer.parseInt(units)+Integer.parseInt(val)/rate;
            String intunit=Integer.toString(unit);
            dbref.child(userID).child("Recharge").setValue(val);
            dbref.child(userID).child("UnitsRemaining").setValue(intunit);

            AlertDialog.Builder builder=new AlertDialog.Builder(payment.this);
            builder.setTitle("Payment Successful");
            builder.setMessage("User Contact:"+paymentData.getUserContact()+"\nUser email:"+paymentData.getUserEmail()+"\nAmount:"+val+"\nTransaction Id:"+paymentData.getPaymentId());
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();}
    catch (Exception e)
    {
        Toast.makeText(this,"Payment failed!!! Please try again",Toast.LENGTH_LONG).show();
    }

        }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this,"Error occured:"+s,Toast.LENGTH_LONG).show();
    }


}