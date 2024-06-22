package com.example.androidb_hw1;

import android.os.Bundle;

import com.example.common.Activity_PanelBase;
import com.example.common.SharedPreferencesManager;


public class MainActivity extends Activity_PanelBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataManagerBase = new DataManager();
        super.onCreate(savedInstanceState);
        main_IMG_glass.setImageResource(R.drawable.glass_24);
    }

}