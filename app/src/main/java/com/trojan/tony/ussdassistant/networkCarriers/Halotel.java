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

import static com.trojan.tony.ussdassistant.Constants.balanceMenu;
import static com.trojan.tony.ussdassistant.Constants.haloPesaMenu;
import static com.trojan.tony.ussdassistant.Constants.halotelBundle1Menu;
import static com.trojan.tony.ussdassistant.Constants.halotelUniMenu;
import static com.trojan.tony.ussdassistant.Constants.rail;

public class Halotel extends AppCompatActivity {


    private View view;
    private AdView mAdView;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halotel);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        CardView balance = findViewById(R.id.halotelBalanceCardView);
        CardView halopesa = findViewById(R.id.halopesaCardView);
        CardView bundle1 = findViewById(R.id.halotelBundle1CardView);
        CardView uni = findViewById(R.id.halotelUniCardView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                switch (v.getId()) {
                    case R.id.halotelBalanceCardView:
                        Intent halotelBalanceIntent = new Intent(Intent.ACTION_CALL);
                        halotelBalanceIntent.setData(Uri.parse(balanceMenu + rail));
                        startActivity(halotelBalanceIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("halotelBalance_button", params);
                        break;
                    case R.id.halopesaCardView:
                        Intent halopesaIntent = new Intent(Intent.ACTION_CALL);
                        halopesaIntent.setData(Uri.parse(haloPesaMenu + rail));
                        startActivity(halopesaIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("halopesa_button", params);
                        break;
                    case R.id.halotelBundle1CardView:
                        Intent halotelBundle1Intent = new Intent(Intent.ACTION_CALL);
                        halotelBundle1Intent.setData(Uri.parse(halotelBundle1Menu + rail));
                        startActivity(halotelBundle1Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("halotelBundle1_button", params);
                        break;
                    case R.id.halotelUniCardView:
                        Intent halotelUniIntent = new Intent(Intent.ACTION_CALL);
                        halotelUniIntent.setData(Uri.parse(halotelUniMenu + rail));
                        startActivity(halotelUniIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("halotelUni_button", params);
                        break;
                    default:
                        break;
                }

            }
        };


        balance.setOnClickListener(listener);
        halopesa.setOnClickListener(listener);
        bundle1.setOnClickListener(listener);
        uni.setOnClickListener(listener);


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

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
