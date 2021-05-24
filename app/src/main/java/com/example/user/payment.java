package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class payment extends AppCompatActivity implements PaymentResultListener {

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        builder=new AlertDialog.Builder(this);
        startPayment();

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
            options.put("amount", "60000");//pass amount in currency subunits
            options.put("prefill.email", "anujajanawade.27@gmail.com");
            options.put("prefill.contact", "9850951128");

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
    }
}