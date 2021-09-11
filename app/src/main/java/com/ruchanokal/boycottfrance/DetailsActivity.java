package com.ruchanokal.boycottfrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.EventListener;


public class DetailsActivity extends AppCompatActivity {

    TextView titleText;
    ImageView logoImage;
    TextView detailText;
    DatabaseReference databaseReference;
    Integer groupIndex;
    Integer childIndex;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    int a;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //DetailsActivityBanner
        //ca-app-pub-5016889744069609/5925739780

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);

        titleText = findViewById(R.id.textView);
        logoImage = findViewById(R.id.logoImage);
        detailText = findViewById(R.id.textView2);


        Intent intent = getIntent();
        String nameInfo = intent.getStringExtra("productName");
        String detailInfo = intent.getStringExtra("productDetail");
        groupIndex = intent.getIntExtra("groupIndex",0);
        childIndex = intent.getIntExtra("childIndex",0);


        getGroupandChildIndex();

        titleText.setText(nameInfo);
        detailText.setText(detailInfo);

        detailText.setMovementMethod(new ScrollingMovementMethod());
        //logoImage.setImageBitmap(selectedImage);

        getDataFromFirebase();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void getGroupandChildIndex() {
        if (groupIndex == 0 && childIndex == 0) {
            a=75;
        } else if(groupIndex==0 && childIndex==1) {
            a=76;
        } else if(groupIndex==1 && childIndex==0) {
            a=2;
        } else if(groupIndex==1 && childIndex==1) {
            a=37;
        } else if(groupIndex==1 && childIndex==2) {
            a=29;
        } else if(groupIndex==2 && childIndex==0) {
            a=52;
        } else if(groupIndex==2 && childIndex==1) {
            a=45;
        } else if(groupIndex==2 && childIndex==2) {
            a=41;
        } else if(groupIndex==3 && childIndex==0) {
            a=42;
        } else if(groupIndex==3 && childIndex==1) {
            a=36;
        } else if(groupIndex==3 && childIndex==2) {
            a=84;
        } else if(groupIndex==3 && childIndex==3) {
            a=24;
        } else if(groupIndex==3 && childIndex==4) {
            a=21;
        } else if(groupIndex==3 && childIndex==5) {
            a=64;
        } else if(groupIndex==3 && childIndex==6) {
            a=51;
        } else if(groupIndex==3 && childIndex==7) {
            a=33;
        } else if(groupIndex==3 && childIndex==8) {
            a=6;
        } else if(groupIndex==3 && childIndex==9) {
            a=12;
        } else if(groupIndex==3 && childIndex==10) {
            a=39;
        } else if(groupIndex==3 && childIndex==11) {
            a=50;
        } else if(groupIndex==3 && childIndex==12) {
            a=44;
        } else if(groupIndex==4 && childIndex==0) {
            a=35;
        } else if(groupIndex==4 && childIndex==1) {
            a=13;
        } else if(groupIndex==5 && childIndex==0) {
            a=23;
        } else if(groupIndex==5 && childIndex==1) {
            a=82;
        } else if(groupIndex==5 && childIndex==2) {
            a=48;
        } else if(groupIndex==5 && childIndex==3) {
            a=68;
        } else if(groupIndex==5 && childIndex==4) {
            a=86;
        } else if(groupIndex==5 && childIndex==5) {
            a=72;
        } else if(groupIndex==6 && childIndex==0) {
            a=61;
        } else if(groupIndex==6 && childIndex==1) {
            a=30;
        } else if(groupIndex==6 && childIndex==2) {
            a=23;
        } else if(groupIndex==7 && childIndex==0) {
            a=81;
        } else if(groupIndex==7 && childIndex==1) {
            a=63;
        } else if(groupIndex==7 && childIndex==2) {
            a=66;
        } else if(groupIndex==7 && childIndex==3) {
            a=19;
        } else if(groupIndex==7 && childIndex==4) {
            a=57;
        } else if(groupIndex==7 && childIndex==5) {
            a=79;
        } else if(groupIndex==7 && childIndex==6) {
            a=67;
        } else if(groupIndex==7 && childIndex==7) {
            a=20;
        } else if(groupIndex==8 && childIndex==0) {
            a=10;
        } else if(groupIndex==8 && childIndex==1) {
            a=53;
        } else if(groupIndex==8 && childIndex==2) {
            a=25;
        } else if(groupIndex==8 && childIndex==3) {
            a=62;
        } else if(groupIndex==9 && childIndex==0) {
            a=52;
        } else if(groupIndex==9 && childIndex==1) {
            a=46;
        } else if(groupIndex==9 && childIndex==2) {
            a=9;
        } else if(groupIndex==9 && childIndex==3) {
            a=16;
        } else if(groupIndex==9 && childIndex==4) {
            a=18;
        } else if(groupIndex==9 && childIndex==5) {
            a=80;
        } else if(groupIndex==10 && childIndex==0) {
            a=15;
        } else if(groupIndex==10 && childIndex==1) {
            a=16;
        } else if(groupIndex==10 && childIndex==2) {
            a=18;
        } else if(groupIndex==10 && childIndex==3) {
            a=26;
        } else if(groupIndex==10 && childIndex==4) {
            a=32;
        } else if(groupIndex==10 && childIndex==5) {
            a=45;
        } else if(groupIndex==11 && childIndex==0) {
            a=18;
        } else if(groupIndex==11 && childIndex==1) {
            a=40;
        } else if(groupIndex==12 && childIndex==0) {
            a=59;
        } else if(groupIndex==12 && childIndex==1) {
            a=43;
        } else if(groupIndex==12 && childIndex==2) {
            a=17;
        } else if(groupIndex==12 && childIndex==3) {
            a=83;
        } else if(groupIndex==13 && childIndex==0) {
            a=1;
        } else if(groupIndex==14 && childIndex==0) {
            a=8;
        } else if(groupIndex==14 && childIndex==1) {
            a=14;
        } else if(groupIndex==15 && childIndex==0) {
            a=47;
        } else if(groupIndex==15 && childIndex==1) {
            a=22;
        } else if(groupIndex==16 && childIndex==0) {
            a=78;
        } else if(groupIndex==16 && childIndex==1) {
            a=28;
        } else if(groupIndex==17 && childIndex==0) {
            a=70;
        } else if(groupIndex==17 && childIndex==1) {
            a=38;
        } else if(groupIndex==17 && childIndex==2) {
            a=65;
        } else if(groupIndex==17 && childIndex==3) {
            a=0;
        } else if(groupIndex==17 && childIndex==4) {
            a=4;
        } else if(groupIndex==17 && childIndex==5) {
            a=5;
        } else if(groupIndex==17 && childIndex==6) {
            a=7;
        } else if(groupIndex==17 && childIndex==7) {
            a=58;
        } else if(groupIndex==18 && childIndex==0) {
            a=69;
        } else if(groupIndex==18 && childIndex==1) {
            a=56;
        } else if(groupIndex==18 && childIndex==2) {
            a=49;
        } else if(groupIndex==18 && childIndex==3) {
            a=27;
        } else if(groupIndex==18 && childIndex==4) {
            a=31;
        } else if(groupIndex==18 && childIndex==5) {
            a=55;
        } else if(groupIndex==18 && childIndex==6) {
            a=77;
        } else if(groupIndex==18 && childIndex==7) {
            a=73;
        } else if(groupIndex==19 && childIndex==0) {
            a=3;
        } else if(groupIndex==20 && childIndex==0) {
            a=71;
        } else if(groupIndex==20 && childIndex==1) {
            a=11;
        } else if(groupIndex==20 && childIndex==2) {
            a=74;
        } else if(groupIndex==21 && childIndex==2) {
            a=88;
        } else if(groupIndex==5 && childIndex==8) {
            a=89;
        } else if(groupIndex==23 && childIndex==0) {
            a=90;
        } else if(groupIndex==3 && childIndex==13) {
            a=91;
        } else if(groupIndex==5 && childIndex==7) {
            a=92;
        } else if(groupIndex==9 && childIndex==6) {
            a=93;
        } else if(groupIndex==24 && childIndex==0) {
            a=94;
        } else if(groupIndex==21 && childIndex==0) {
            a=96;
        } else if(groupIndex==0 && childIndex==2) {
            a=97;
        } else if(groupIndex==21 && childIndex==3) {
            a=98;
        } else if(groupIndex==22 && childIndex==0) {
            a=99;
        } else if(groupIndex==5 && childIndex==6) {
            a=100;
        } else if(groupIndex==0 && childIndex==3) {
            a=101;
        } else if(groupIndex==21 && childIndex==1) {
            a=102;
        }




    }

    private void getDataFromFirebase() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Images").child(String.valueOf(a)).child("downloadurl");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue().toString();
                Uri imageUri = Uri.parse(value);
                System.out.println(imageUri);
                Picasso.get().load(imageUri).into(logoImage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeListener);
    }





}