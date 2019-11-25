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

import static com.trojan.tony.ussdassistant.Constants.rail;
import static com.trojan.tony.ussdassistant.Constants.tigoBalanceMenu;
import static com.trojan.tony.ussdassistant.Constants.tigoBundle1Menu;
import static com.trojan.tony.ussdassistant.Constants.tigoBundle2Menu;
import static com.trojan.tony.ussdassistant.Constants.tigoBundle3Menu;
import static com.trojan.tony.ussdassistant.Constants.tigoPesaMenu;

public class Tigo extends AppCompatActivity {
    private View view;
    private AdView mAdView;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tigo);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        CardView balance = findViewById(R.id.tigoBundleBalanceCardView);
        CardView tigopesa = findViewById(R.id.tigoPesaCardView);
        CardView bundle1 = findViewById(R.id.tigoBundle1CardView);
        CardView bundle2 = findViewById(R.id.tigoBundle2CardView);
        CardView bundle3 = findViewById(R.id.tigoBundle3CardView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                switch (v.getId()) {
                    case R.id.tigoBundleBalanceCardView:
                        Intent tigoCheckBalanceIntent = new Intent(Intent.ACTION_CALL);
                        tigoCheckBalanceIntent.setData(Uri.parse(tigoBalanceMenu + rail));
                        startActivity(tigoCheckBalanceIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("tigoBundleBalance_button", params);
                        break;
                    case R.id.tigoPesaCardView:
                        Intent tigoPesaIntent = new Intent(Intent.ACTION_CALL);
                        tigoPesaIntent.setData(Uri.parse(tigoPesaMenu + rail));
                        startActivity(tigoPesaIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("tigoPesa_button", params);
                        break;
                    case R.id.tigoBundle1CardView:
                        Intent tigoBundle1Intent = new Intent(Intent.ACTION_CALL);
                        tigoBundle1Intent.setData(Uri.parse(tigoBundle1Menu + rail));
                        startActivity(tigoBundle1Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("tigoBundle1_button", params);
                        break;
                    case R.id.tigoBundle2CardView:
                        Intent tigoBundle2Intent = new Intent(Intent.ACTION_CALL);
                        tigoBundle2Intent.setData(Uri.parse(tigoBundle2Menu + rail));
                        startActivity(tigoBundle2Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("tigoBundle2_button", params);
                        break;
                    case R.id.tigoBundle3CardView:
                        Intent tigoBundle3Intent = new Intent(Intent.ACTION_CALL);
                        tigoBundle3Intent.setData(Uri.parse(tigoBundle3Menu + rail));
                        startActivity(tigoBundle3Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("tigoBundle3_button", params);
                        break;
                    default:
                        break;
                }

            }
        };

        balance.setOnClickListener(listener);
        tigopesa.setOnClickListener(listener);
        bundle1.setOnClickListener(listener);
        bundle2.setOnClickListener(listener);
        bundle3.setOnClickListener(listener);

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
