package com.trojan.tony.ussdassistant;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class Voda extends AppCompatActivity {

    private final String ussd = Uri.encode("#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voda);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        CardView balance = (CardView) findViewById(R.id.vodaCheckBalanceCardViewId);
        CardView mpesa = (CardView) findViewById(R.id.mPesaCardViewId);
        CardView uni = (CardView) findViewById(R.id.vodaBundle1CardViewId);
        CardView menu1 = (CardView) findViewById(R.id.vodaBundle2CardViewId);
        CardView menu2 = (CardView) findViewById(R.id.vodaBundle3CardViewId);


        mpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse("tel:*150*00" + ussd));
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
                dialerIntent.setData(Uri.parse("tel:*149*01" + ussd));
                startActivity(dialerIntent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse("tel:*149*03" + ussd));
                startActivity(dialerIntent);
            }
        });

        uni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse("tel:*149*42" + ussd));
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

