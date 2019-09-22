package com.trojan.tony.ussdassistant;

import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Recharge extends AppCompatActivity {
    private EditText pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

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

        }

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
