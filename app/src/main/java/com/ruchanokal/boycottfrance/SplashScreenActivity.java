package com.ruchanokal.boycottfrance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView splashImage;
    TextView splashText;

    Animation topAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashImage = findViewById(R.id.imageView3);
        splashText = findViewById(R.id.splashText);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.anim_top);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.anim_bottom);

        splashImage.setAnimation(topAnim);
        splashText.setAnimation(bottomAnim);

        //ca-app-pub-5016889744069609~9383583159


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,LanguageActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

        MobileAds.initialize(this);


    }



}