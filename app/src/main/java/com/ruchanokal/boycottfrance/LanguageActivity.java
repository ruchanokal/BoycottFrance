package com.ruchanokal.boycottfrance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LanguageActivity extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    static int myNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);


        sharedPreferences = this.getSharedPreferences("com.ruchanokal.boycottfrance", Context.MODE_PRIVATE);
        myNumber = sharedPreferences.getInt("number",5);

        System.out.println(myNumber);


        if(myNumber ==2){
            Intent intenttoTurkish = new Intent(LanguageActivity.this,MainActivity.class);
            startActivity(intenttoTurkish);
            finish();
        } else if (myNumber == 3) {
            Intent intentToEnglish = new Intent(LanguageActivity.this,MainActivity2.class);
            startActivity(intentToEnglish);
            finish();
        }



        ImageButton turkish = findViewById(R.id.turkish);
        ImageButton english = findViewById(R.id.english);


        turkish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LanguageActivity.this,MainActivity.class);
                startActivity(intent);
                sharedPreferences.edit().putInt("number",2).apply();
                System.out.println(myNumber);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LanguageActivity.this,MainActivity2.class);
                startActivity(intent);
                sharedPreferences.edit().putInt("number",3).apply();
                System.out.println(myNumber);
            }
        });



    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkChangeListener);
        super.onDestroy();
    }




}