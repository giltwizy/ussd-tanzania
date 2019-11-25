package com.trojan.tony.ussdassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Recharge extends AppCompatActivity {
    private EditText pin;
    private View view;
    private AdView mAdView;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Button recharge = findViewById(R.id.rechargeBtn);
        pin = findViewById(R.id.pinEt);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RechargeActivity();
            }
        });

    }


    private void RechargeActivity(){
        Bundle params = new Bundle();
        String rail = Uri.encode("#");
        String pin_no = pin.getText().toString();


        if(TextUtils.isEmpty(pin_no)) {
            pin.setError("Tafadhali andika tarakimu za vocha");
            pin.requestFocus();
        }
        else if (pin_no.length() < 12) {
            pin.setError("Samahani! Tarakimu za vocha yako ni pungufu");
            pin.requestFocus();
        }

        else {
            Intent dialerIntent = new Intent(Intent.ACTION_CALL);
            dialerIntent.setData(Uri.parse("tel:*104*" + pin_no + rail));
            startActivity(dialerIntent);

            params.putString("voucher", pin_no);
            mFirebaseAnalytics.logEvent("recharge_button", params);

        }

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
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
