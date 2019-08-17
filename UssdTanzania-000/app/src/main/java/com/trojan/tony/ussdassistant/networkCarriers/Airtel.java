package com.trojan.tony.ussdassistant.networkCarriers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.trojan.tony.ussdassistant.Admob;
import com.trojan.tony.ussdassistant.R;

import static com.trojan.tony.ussdassistant.Constants.airtelBundle1Menu;
import static com.trojan.tony.ussdassistant.Constants.airtelMoneyMenu;
import static com.trojan.tony.ussdassistant.Constants.airtelUniMenu;
import static com.trojan.tony.ussdassistant.Constants.balanceMenu;
import static com.trojan.tony.ussdassistant.Constants.rail;

public class Airtel extends AppCompatActivity {


    private View view;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airtel);




        CardView balance = findViewById(R.id.checkBalanceCardView);
        CardView airtelmoney = findViewById(R.id.airtelMoneyCardView);
        CardView uni = findViewById(R.id.airtelBundle1CardView);
        CardView airtelBundle1 = findViewById(R.id.airtelBundle2CardView);

        airtelmoney.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(airtelMoneyMenu+rail));
                startActivity(dialerIntent);
            }
        });

        balance.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(balanceMenu+rail));
                startActivity(dialerIntent);
            }
        });

        airtelBundle1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(airtelBundle1Menu+rail));
                startActivity(dialerIntent);
            }
        });

        uni.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(airtelUniMenu+rail));
                startActivity(dialerIntent);
            }
        });

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Admob
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        //   --- Admob ---
        view=getWindow().getDecorView().getRootView();

        Admob.createLoadBanner(getApplicationContext(), view);
        Admob.createLoadInterstitial(getApplicationContext(),null);
        //   --- *** ---


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
