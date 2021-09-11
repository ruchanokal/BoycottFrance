package com.ruchanokal.boycottfrance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import static com.ruchanokal.boycottfrance.LanguageActivity.myNumber;
import static com.ruchanokal.boycottfrance.LanguageActivity.sharedPreferences;

public class AyarlarAktivitesiTurk extends PreferenceActivity {

    String dilListe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new MyPreferenceFrag()).commit();

    }


    public static class MyPreferenceFrag extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    @Override
    public void onBackPressed() {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(AyarlarAktivitesiTurk.this);

        dilListe = sp.getString("language","Default Language");


        if (dilListe.matches("turkish")) {
            Intent intent = new Intent(AyarlarAktivitesiTurk.this,MainActivity.class);
            startActivity(intent);
            sharedPreferences.edit().putInt("number",2).apply();
            myNumber= sharedPreferences.getInt("number",5);
            System.out.println("dilliste: "+ dilListe);

        } else if(dilListe.matches("english")){
            Intent intent = new Intent(AyarlarAktivitesiTurk.this,MainActivity2.class);
            startActivity(intent);
            sharedPreferences.edit().putInt("number",3).apply();
            myNumber= sharedPreferences.getInt("number",5);
            System.out.println("dilliste: "+ dilListe);

        }

        super.onBackPressed();
    }
}


