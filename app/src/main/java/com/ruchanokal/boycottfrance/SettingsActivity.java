package com.ruchanokal.boycottfrance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import java.util.Set;


import static com.ruchanokal.boycottfrance.LanguageActivity.myNumber;
import static com.ruchanokal.boycottfrance.LanguageActivity.sharedPreferences;

public class SettingsActivity  extends PreferenceActivity {

    Toolbar toolbar;
    String languageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new MyPreferenceFragment()).commit();
        toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

    }


    public static class MyPreferenceFragment extends PreferenceFragment{

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

        }

    }

    @Override
    public void onBackPressed() {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);

        languageList = sp.getString("language","Default Language");

        if (languageList.matches("turkish")) {
            Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
            startActivity(intent);
            sharedPreferences.edit().putInt("number",2).apply();
            myNumber=sharedPreferences.getInt("number",5);


        } else if(languageList.matches("english")){
            Intent intent = new Intent(SettingsActivity.this,MainActivity2.class);
            startActivity(intent);
            sharedPreferences.edit().putInt("number",3).apply();
            myNumber=sharedPreferences.getInt("number",5);

        }

        super.onBackPressed();
    }
}
