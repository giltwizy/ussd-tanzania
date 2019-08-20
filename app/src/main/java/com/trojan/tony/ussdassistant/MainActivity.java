package com.trojan.tony.ussdassistant;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.trojan.tony.ussdassistant.networkCarriers.Airtel;
import com.trojan.tony.ussdassistant.networkCarriers.Halotel;
import com.trojan.tony.ussdassistant.networkCarriers.TTCL;
import com.trojan.tony.ussdassistant.networkCarriers.Tigo;
import com.trojan.tony.ussdassistant.networkCarriers.Voda;
import com.trojan.tony.ussdassistant.networkCarriers.Zantel;

import static com.trojan.tony.ussdassistant.Constants.airtelMoneyMenu;
import static com.trojan.tony.ussdassistant.Constants.balanceMenu;
import static com.trojan.tony.ussdassistant.Constants.haloPesaMenu;

import static com.trojan.tony.ussdassistant.Constants.rail;
import static com.trojan.tony.ussdassistant.Constants.serikaliMenu;
import static com.trojan.tony.ussdassistant.Constants.tigoPesaMenu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //ads init
    private View view;
    private AdView mAdView;


    //Initialization of the PermissionRequest code
    private final int CALL_PERMISSION_CODE = 1;

    //Firebase analytics
   // private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Admob
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        //   --- Admob ---
        view=getWindow().getDecorView().getRootView();

        Admob.createLoadBanner(getApplicationContext(), view);
        Admob.createLoadInterstitial(getApplicationContext(),null);
        //   --- *** ---


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);





        //Check if calling permission is granted if not permission is requested
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestCallPermission();
        }

        //initialization of USSD Buttons
        CardView recharge = findViewById(R.id.rechargeCardViewId);
        CardView balance = findViewById(R.id.generalCheckBalanceCardView);
        CardView tigoPesa = findViewById(R.id.generalTigoPesaCardView);
        CardView serikali = findViewById(R.id.generalSerikaliCardView);
        CardView airtelMoney = findViewById(R.id.generalAirtelMoneyCardView);
        CardView haloPesa = findViewById(R.id.generalHaloPesaCardView);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rechargeActivity = new Intent(MainActivity.this, Recharge.class);
                startActivity(rechargeActivity);
            }
        });

        balance.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(balanceMenu+rail));
                startActivity(dialerIntent);
            }
        });

        tigoPesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(tigoPesaMenu + rail));
                startActivity(dialerIntent);
            }
        });

        serikali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(serikaliMenu+rail));
                startActivity(dialerIntent);
            }
        });

        airtelMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(airtelMoneyMenu+rail));
                startActivity(dialerIntent);
            }
        });

        haloPesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialerIntent = new Intent(Intent.ACTION_CALL);
                dialerIntent.setData(Uri.parse(haloPesaMenu+rail));
                startActivity(dialerIntent);
            }
        });



        //Initialization of AdMob Ads
     //   MobileAds.initialize(this, getString(R.string.adMobAppId));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    private void openAdmobActivity() {
//        Intent addMob = new Intent(MainActivity.this, AddMob.class);
//        startActivity(addMob);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.vodaNav) {
            openVodaActivity();
        } else if (id == R.id.tigoNav) {
            openTigoActivity();

        } else if (id == R.id.airtelNav) {
            openAirtelActivity();
        } else if (id == R.id.zantelNav) {
            openZantelActivity();
        } else if (id == R.id.halotelNav) {
            openHalotelActivity();
        } else if (id == R.id.ttclNav) {
            openTtclActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openVodaActivity() {
        Intent vodaIntent = new Intent(this, Voda.class);
        startActivity(vodaIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void openTigoActivity() {
        Intent tigoIntent = new Intent(this, Tigo.class);
        startActivity(tigoIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void openAirtelActivity() {
        Intent airtelIntent = new Intent(this, Airtel.class);
        startActivity(airtelIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void openHalotelActivity() {
        Intent halotelIntent = new Intent(this, Halotel.class);
        startActivity(halotelIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void openTtclActivity() {
        Intent ttclIntent = new Intent(this, TTCL.class);
        startActivity(ttclIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void openZantelActivity() {
        Intent zantelIntent = new Intent(this, Zantel.class);
        startActivity(zantelIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    //method requesting calling permission
    private void requestCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CALL_PHONE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed so that this App can work in calling the USSD Codes")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
        }
    }

    //Result of requesting the permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CALL_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
