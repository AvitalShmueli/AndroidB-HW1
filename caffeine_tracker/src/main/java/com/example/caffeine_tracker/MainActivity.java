package com.example.caffeine_tracker;

import static com.example.common.Models.RecordsList.RECORDS_TABLE;

import android.os.Bundle;
import android.util.Log;

import com.example.common.Activity_PanelBase;
import com.example.common.Models.CupType;
import com.example.common.Models.Record;
import com.example.common.Utilities.SharedPreferencesManager;
import com.google.gson.Gson;

public class MainActivity extends Activity_PanelBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataManagerBase = new DataManager();
        unit = "mg";
        super.onCreate(savedInstanceState);
        main_IMG_glass.setImageResource(R.drawable.coffee_beans_24);
    }

    @Override
    protected void updateDrinkCounter(CupType cupType){
        amount += cupType.getCapacity();
        cups = amount;
        Log.d("cupTypeSelected", "cupTypeSelected"+ "("+amount+")");
        if(todayRecord == null){
            todayRecord = new Record().setDate(today).setCapacity(cupType.getCapacity());
            records.add(todayRecord);
        } else {
            records.get(records.getIndexOfToday()).setCapacity(todayRecord.addAmount(cupType.getCapacity()).getCapacity());
        }
        Gson gson = new Gson();
        String recordsListAsJson = gson.toJson(records);
        SharedPreferencesManager.getInstance().putString(RECORDS_TABLE, recordsListAsJson);
        Log.d("RECORDS_TABLE from SP", records.toString());
        updateUI();
    }

}