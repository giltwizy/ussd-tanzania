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
import com.trojan.tony.ussdassistant.R;

import static com.trojan.tony.ussdassistant.Constants.balanceMenu;
import static com.trojan.tony.ussdassistant.Constants.ezyPesaMenu;
import static com.trojan.tony.ussdassistant.Constants.rail;
import static com.trojan.tony.ussdassistant.Constants.zantelBundle1Menu;
import static com.trojan.tony.ussdassistant.Constants.zantelUniMenu;

public class Zantel extends AppCompatActivity {

    private View view;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zantel);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        CardView balance = findViewById(R.id.zantelBalanceCardView);
        CardView ezypesa = findViewById(R.id.ezypesaCardView);
        CardView bundle1 = findViewById(R.id.zantelBundle1CardView);
        CardView uni = findViewById(R.id.zantelUniCardView);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.zantelBalanceCardView:
                        Intent zantelBalanceIntent = new Intent(Intent.ACTION_CALL);
                        zantelBalanceIntent.setData(Uri.parse(balanceMenu + rail));
                        startActivity(zantelBalanceIntent);
                        break;
                    case R.id.ezypesaCardView:
                        Intent ezypesaIntent = new Intent(Intent.ACTION_CALL);
                        ezypesaIntent.setData(Uri.parse(ezyPesaMenu + rail));
                        startActivity(ezypesaIntent);
                        break;
                    case R.id.zantelBundle1CardView:
                        Intent zantelBundle1Intent = new Intent(Intent.ACTION_CALL);
                        zantelBundle1Intent.setData(Uri.parse(zantelBundle1Menu + rail));
                        startActivity(zantelBundle1Intent);
                        break;
                    case R.id.zantelUniCardView:
                        Intent zantelUniIntent = new Intent(Intent.ACTION_CALL);
                        zantelUniIntent.setData(Uri.parse(zantelUniMenu + rail));
                        startActivity(zantelUniIntent);
                        break;
                    default:
                        break;
                }
//                startActivity(dialerIntent);

            }
        };

        balance.setOnClickListener(listener);
        ezypesa.setOnClickListener(listener);
        bundle1.setOnClickListener(listener);
        uni.setOnClickListener(listener);



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
