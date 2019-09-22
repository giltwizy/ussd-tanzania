package com.trojan.tony.ussdassistant.networkCarriers;

import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.trojan.tony.ussdassistant.Admob;
import com.trojan.tony.ussdassistant.R;

import static com.trojan.tony.ussdassistant.Constants.salioLaKifurushi;
import static com.trojan.tony.ussdassistant.Constants.mPesaMenu;
import static com.trojan.tony.ussdassistant.Constants.rail;
import static com.trojan.tony.ussdassistant.Constants.vodaBundle1Menu;
import static com.trojan.tony.ussdassistant.Constants.vodaBundle2Menu;
import static com.trojan.tony.ussdassistant.Constants.vodaUniMenu;

public class Voda extends AppCompatActivity {

    private View view;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voda);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        CardView balanceBundle = findViewById(R.id.vodaBalanceBundleCardViewId);
        CardView mpesa = findViewById(R.id.mPesaCardViewId);
        CardView uni = findViewById(R.id.vodaBundle1CardViewId);
        CardView bundle1 = findViewById(R.id.vodaBundle2CardViewId);
        CardView bundle2 = findViewById(R.id.vodaBundle3CardViewId);


        mpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(mPesaMenu + rail));
                startActivity(dialerIntent);
            }
        });

        balanceBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(salioLaKifurushi + rail));
                startActivity(dialerIntent);
            }
        });

        bundle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(vodaBundle1Menu + rail));
                startActivity(dialerIntent);
            }
        });

        bundle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(vodaBundle2Menu + rail));
                startActivity(dialerIntent);
            }
        });

        uni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(vodaUniMenu + rail));
                startActivity(dialerIntent);
            }
        });

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
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}

