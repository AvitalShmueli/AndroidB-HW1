package com.example.common;

import static com.example.common.Models.RecordsList.RECORDS_TABLE;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.Adapters.TypesAdapter;
import com.example.common.Interfaces.CupTypeCallback;
import com.example.common.Models.CupType;
import com.example.common.Models.Record;
import com.example.common.Models.RecordsList;
import com.example.common.Utilities.SharedPreferencesManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Activity_PanelBase extends AppCompatActivity {
    protected DataManagerBase dataManagerBase;
    protected ShapeableImageView main_IMG_glass;
    private MaterialTextView main_LBL_counter, main_LBL_until_goal, main_LBL_streak;
    private MaterialButton main_BTN_add, main_BTN_save;
    private RecyclerView main_LST_cupTypes;
    protected int amount = 0;
    protected int cups = 0;
    private int goal;
    private ArrayList<CupType> typeArrayList;
    protected String today;
    protected RecordsList records;
    protected Record todayRecord;
    private int streak;
    protected String unit = "ml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferencesManager.init(this);

        Date todayDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        today = dateFormat.format(todayDate);
        Log.d("TEST TIME", today);

        goal = unit.equals("ml")? dataManagerBase.getGoalInCups() : dataManagerBase.getGoalInML_MG();
        loadDataFromSP();

        findViews();
        initViews();

        updateUI();

    }

    private void loadDataFromSP() {
        records = new Gson().fromJson(SharedPreferencesManager.getInstance().getString(RECORDS_TABLE, ""), RecordsList.class);
        if(records == null) {
            records = new RecordsList();
            records.setListName(getString(R.string.app_name)).setUnit(unit).setDailyGoal(goal);
        }
        else{
            todayRecord = records.getTodayRecord();
            if(todayRecord != null){
                amount = todayRecord.getCapacity();
                if(unit.equals("ml"))
                    cups = amount /220;
                else cups = amount;
            }
        }
        Log.d("RECORDS_TABLE from SP", records.toString());
        streak = records.getCurrentStreak();
    }



    private void initViews() {
        typeArrayList =  dataManagerBase.getCupTypes();
        TypesAdapter typesAdapter =  new TypesAdapter(getApplicationContext(),typeArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        main_LST_cupTypes.setLayoutManager(linearLayoutManager);
        main_LST_cupTypes.setAdapter(typesAdapter);

        main_BTN_add.setOnClickListener(v -> {
            main_LST_cupTypes.setVisibility(View.VISIBLE);
            main_BTN_add.setVisibility(View.GONE);
            clearSelection(typesAdapter);
        });

        typesAdapter.setTypesCallback(new CupTypeCallback() {
            @Override
            public void cupTypeSelected(CupType cupType, int position) {
                main_BTN_save.setVisibility(isChoiceSelected() ? View.VISIBLE : View.INVISIBLE);
                main_BTN_save.setOnClickListener(v -> {
                    updateDrinkCounter(cupType);
                });
            }
        });

        if(records.getSize() <= 1)
            main_LBL_streak.setVisibility(View.INVISIBLE);
        else {
            main_LBL_streak.setText(getString(R.string.streak, streak));
            main_LBL_streak.setVisibility(View.VISIBLE);
        }
    }

    void clearSelection(TypesAdapter typesAdapter){
        for(int i = 0; i < typeArrayList.size(); i++){
            typeArrayList.get(i).setSelected(false);
        }
        typesAdapter.notifyDataSetChanged();
    }

    boolean isChoiceSelected(){
        for(int i = 0; i < typeArrayList.size(); i++){
            if(typeArrayList.get(i).isSelected())
                return true;
        }
        return false;
    }

    protected void updateDrinkCounter(CupType cupType){
        cups += cupType.getCapacity()/220;
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


    protected void updateUI(){
        main_LBL_counter.setText(getString(R.string.counter_text,cups));
        if(cups < goal) {
            main_LBL_until_goal.setText(getString(R.string.counter_text_left_until_goal,unit.equals("ml")? goal - cups : goal - amount));
        } else {
            main_LBL_until_goal.setTextColor(getColor(R.color.colorPrimary));
            main_LBL_until_goal.setText(R.string.goal_reached);
        }
        main_LST_cupTypes.setVisibility(View.GONE);
        main_BTN_save.setVisibility(View.GONE);
        main_BTN_add.setVisibility(View.VISIBLE);

        streak = records.getCurrentStreak();
        main_LBL_streak.setText(getString(R.string.streak, streak));
        main_LBL_streak.setVisibility(records.getSize() <= 1 ? View.INVISIBLE : View.VISIBLE);
    }

    private void findViews() {
        main_LBL_counter = findViewById(R.id.main_LBL_counter);
        main_LBL_until_goal = findViewById(R.id.main_LBL_until_goal);
        main_BTN_add = findViewById(R.id.main_BTN_add);
        main_LST_cupTypes = findViewById(R.id.main_LST_cupTypes);
        main_IMG_glass = findViewById(R.id.main_IMG_glass);
        main_BTN_save = findViewById(R.id.main_BTN_save);
        main_LBL_streak = findViewById(R.id.main_LBL_streak);
    }
}