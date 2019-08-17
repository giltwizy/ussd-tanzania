package com.winza.ussdtanzania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.net.Uri;
import android.os.Bundle;

public class Halotel extends AppCompatActivity {
    private final String ussd = Uri.encode("#");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halotel);

    }
}
