package com.example.maximgerasimenko.observer;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

//Widok "Settings"
public class SettingsActivity extends PreferenceActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    public  static class SettingsFragment extends PreferenceFragment{
        @Override
        public void onCreate(final Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
