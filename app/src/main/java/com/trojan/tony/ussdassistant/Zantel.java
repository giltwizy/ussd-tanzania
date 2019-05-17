package com.trojan.tony.ussdassistant;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class Zantel extends AppCompatActivity {
    private final String ussd = Uri.encode("#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zantel);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        CardView balance = (CardView) findViewById(R.id.zantelCheckBalanceCardViewId);
        CardView ezypesa = (CardView) findViewById(R.id.ezypesaCardViewId);
        CardView uni = (CardView) findViewById(R.id.zantelBundle1CardViewId);
        CardView menu1 = (CardView) findViewById(R.id.zantelBundle2CardViewId);


        ezypesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse("tel:*150*02" + ussd));
                startActivity(dialerIntent);
            }
        });

        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse("tel:*102" + ussd));
                startActivity(dialerIntent);
            }
        });

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse("tel:*149*09" + ussd));
                startActivity(dialerIntent);
            }
        });

        uni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse("tel:*149*15*4" + ussd));
                startActivity(dialerIntent);
            }
        });


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
