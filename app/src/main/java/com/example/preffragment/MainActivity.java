package com.example.preffragment;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    SharedPreferences myPreference;
    OnSharedPreferenceChangeListener listener;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent myIntent = new Intent(this, PrefActivity.class);
                startActivity(myIntent);
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPreference = PreferenceManager.getDefaultSharedPreferences(this);

    }

    public void doPref_Simple(View view) {
        // start the pref activity with the embedded pref fragment
        Intent myIntent = new Intent(this, PrefActivity.class);
        startActivity(myIntent);
    }

    public void doPref_Complex(View view) {
        // start the pref activity with the embedded pref fragment
        Intent myIntent = new Intent(this, PrefActivity_Extensive.class);
        startActivity(myIntent);
    }

    public void doPref_Listener(View v) {
        // listen for a change to the PREF_LIST key,
        // its the key part of the key value pair that holds the list
        // of items in the preference screen
        // make this listener an instance var so it is not GCed due to it being
        // saved as a weak reference
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                Toast.makeText(MainActivity.this, "Key=" + key, Toast.LENGTH_SHORT).show();
                if (key.equals("PREF_LIST")) {
                    String myString = myPreference.getString("PREF_LIST", "Nothing Found");
                    Toast.makeText(MainActivity.this, "From Listener PREF_LIST=" + myString, Toast.LENGTH_SHORT).show();
                }
            }
        };
        // register the listener
        myPreference.registerOnSharedPreferenceChangeListener(listener);
    }
}
