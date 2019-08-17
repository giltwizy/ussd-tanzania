package com.winza.ussdtanzania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MobileOperators extends AppCompatActivity implements View.OnClickListener {

    private CardView halotel_m ;
    private CardView tigo_m ;
    private CardView airtel_m ;
    private CardView vodacom_m ;
    private CardView ttcl_m ;
    private CardView zantel_m ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_operators);

        halotel_m =(CardView) findViewById(R.id.halotel_m);
        tigo_m =(CardView) findViewById(R.id.tigo_m);
        airtel_m =(CardView) findViewById(R.id.airtel_m);
        vodacom_m =(CardView) findViewById(R.id.vodacom_m);
        ttcl_m =(CardView) findViewById(R.id.ttcl_m);
        zantel_m =(CardView) findViewById(R.id.zantel_m);


        halotel_m.setOnClickListener(this);
        airtel_m.setOnClickListener(this);
        tigo_m.setOnClickListener(this);
        vodacom_m.setOnClickListener(this);
        ttcl_m.setOnClickListener(this);
        zantel_m.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.halotel_m : i =new Intent(this,Halotel.class); startActivity(i); break;
            case R.id.airtel_m : i =new Intent(this,Airtel.class); startActivity(i); break;
            case R.id.tigo_m : i =new Intent(this,Tigo.class); startActivity(i); break;
            case R.id.vodacom_m : i =new Intent(this,Vodacom.class); startActivity(i); break;
            case R.id.ttcl_m : i =new Intent(this,Ttcl.class); startActivity(i); break;
            case R.id.zantel_m : i =new Intent(this,Zantel.class); startActivity(i); break;


            default: break;



        }

    }
}
