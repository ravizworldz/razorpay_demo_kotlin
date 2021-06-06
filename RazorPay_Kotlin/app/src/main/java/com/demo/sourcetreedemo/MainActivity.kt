package com.demo.sourcetreedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Checkout.preload(getApplicationContext())

        payButton.setOnClickListener {
            makePayment()
        }
    }

    private fun makePayment() {
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Learning Worldz")
            options.put("description","Learning Worldz Payment")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("amount","60000")//pass amount in currency subunits

           /* val retryObj =  JSONObject()
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);*/

            val prefill = JSONObject()
            prefill.put("email","abcxyz@gmail.com")
            prefill.put("contact","8527834283")

            options.put("prefill",prefill)
            co.open(this,options)
        }catch (e: Exception){
            Toast.makeText(this,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this,"Payment Sucsess: "+ p0,Toast.LENGTH_LONG).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this,"Payment Failed: "+ p1,Toast.LENGTH_LONG).show()
    }
}