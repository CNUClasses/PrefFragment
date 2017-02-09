package com.example.preffragment;

import android.os.Bundle;

public class PrefFragment_Extensive extends PrefFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_extensive);
    }
}
