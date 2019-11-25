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
import com.trojan.tony.ussdassistant.Constants;
import com.trojan.tony.ussdassistant.R;

import static com.trojan.tony.ussdassistant.Constants.mPesaMenu;
import static com.trojan.tony.ussdassistant.Constants.rail;
import static com.trojan.tony.ussdassistant.Constants.vodaBundle1Menu;
import static com.trojan.tony.ussdassistant.Constants.vodaBundle2Menu;
import static com.trojan.tony.ussdassistant.Constants.vodaUniMenu;

public class Voda extends AppCompatActivity {

    private View view;
    private AdView mAdView;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voda);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        CardView balanceBundle = findViewById(R.id.vodaBalanceBundleCardView);
        CardView mpesa = findViewById(R.id.mPesaCardView);
        CardView vodaBundle1 = findViewById(R.id.vodaUniMenuCardView);
        CardView vodaBundle2 = findViewById(R.id.vodaBundle2CardView);
        CardView vodaBundle3 = findViewById(R.id.vodaBundle1CardView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                switch (v.getId()) {
                    case R.id.vodaBalanceBundleCardView:
                        Intent vodaBalanceBundleIntent = new Intent(Intent.ACTION_CALL);
                        vodaBalanceBundleIntent.setData(Uri.parse(Constants.balanceBundle + rail));
                        startActivity(vodaBalanceBundleIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("vodaBalanceBundle_button", params);
                        break;
                    case R.id.mPesaCardView:
                        Intent mPesaIntent = new Intent(Intent.ACTION_CALL);
                        mPesaIntent.setData(Uri.parse(mPesaMenu + rail));
                        startActivity(mPesaIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("mPesa_button", params);
                        break;
                    case R.id.vodaBundle1CardView:
                        Intent vodaBundle1Intent = new Intent(Intent.ACTION_CALL);
                        vodaBundle1Intent.setData(Uri.parse(vodaBundle1Menu + rail));
                        startActivity(vodaBundle1Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("vodaBundle1_button", params);
                        break;
                    case R.id.vodaBundle2CardView:
                        Intent vodaBundle2Intent = new Intent(Intent.ACTION_CALL);
                        vodaBundle2Intent.setData(Uri.parse(vodaBundle2Menu + rail));
                        startActivity(vodaBundle2Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("vodaBundle2_button", params);
                        break;
                    case R.id.vodaUniMenuCardView:
                        Intent vodaUniMenuIntent = new Intent(Intent.ACTION_CALL);
                        vodaUniMenuIntent.setData(Uri.parse(vodaUniMenu + rail));
                        startActivity(vodaUniMenuIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("vodaUni_button", params);
                        break;
                    default:
                        break;
                }

            }
        };

        balanceBundle.setOnClickListener(listener);
        mpesa.setOnClickListener(listener);
        vodaBundle1.setOnClickListener(listener);
        vodaBundle2.setOnClickListener(listener);
        vodaBundle3.setOnClickListener(listener);
        
        

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
        if (item.getItemId() == android.R.id.home)
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

