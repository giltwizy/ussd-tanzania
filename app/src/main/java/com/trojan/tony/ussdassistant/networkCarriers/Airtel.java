package com.trojan.tony.ussdassistant.networkCarriers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.trojan.tony.ussdassistant.R;

import static com.trojan.tony.ussdassistant.Constants.airtelBundle1Menu;
import static com.trojan.tony.ussdassistant.Constants.airtelBundle2Menu;
import static com.trojan.tony.ussdassistant.Constants.airtelMoneyMenu;
import static com.trojan.tony.ussdassistant.Constants.airtelUniMenu;
import static com.trojan.tony.ussdassistant.Constants.balanceMenu;
import static com.trojan.tony.ussdassistant.Constants.rail;

public class Airtel extends AppCompatActivity {


    private View view;
    private AdView mAdView;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airtel);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        CardView balance = findViewById(R.id.airtelBalanceCardView);
        CardView airtelmoney = findViewById(R.id.airtelMoneyCardView);
        CardView uni = findViewById(R.id.airtelUniCardView);
        CardView airtelBundle1 = findViewById(R.id.airtelBundle1CardView);
        CardView airtelBundle2 = findViewById(R.id.airtelBundle2CardView);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                switch (v.getId()) {
                    case R.id.airtelBalanceCardView:
                        Intent airtelBalanceIntent = new Intent(Intent.ACTION_CALL);
                        airtelBalanceIntent.setData(Uri.parse(balanceMenu + rail));
                        startActivity(airtelBalanceIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("airtelBalance_button", params);
                        break;
                    case R.id.airtelMoneyCardView:
                        Intent airtelMoneyIntent = new Intent(Intent.ACTION_CALL);
                        airtelMoneyIntent.setData(Uri.parse(airtelMoneyMenu + rail));
                        startActivity(airtelMoneyIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("airtelMoney_button", params);
                        break;
                    case R.id.airtelUniCardView:
                        Intent airtelUniIntent = new Intent(Intent.ACTION_CALL);
                        airtelUniIntent.setData(Uri.parse(airtelUniMenu + rail));
                        startActivity(airtelUniIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("airtelUni_button", params);
                        break;
                    case R.id.airtelBundle1CardView:
                        Intent airtelBundle1Intent = new Intent(Intent.ACTION_CALL);
                        airtelBundle1Intent.setData(Uri.parse(airtelBundle1Menu + rail));
                        startActivity(airtelBundle1Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("airtelBundle1_button", params);
                        break;
                    case R.id.airtelBundle2CardView:
                        Intent airtelBundle2Intent = new Intent(Intent.ACTION_CALL);
                        airtelBundle2Intent.setData(Uri.parse(airtelBundle2Menu + rail));
                        startActivity(airtelBundle2Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("airtelBundle2_button", params);
                        break;
                    default:
                        break;
                }

            }
        };


        balance.setOnClickListener(listener);
        airtelBundle1.setOnClickListener(listener);
        airtelBundle2.setOnClickListener(listener);
        airtelmoney.setOnClickListener(listener);
        uni.setOnClickListener(listener);


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


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

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
