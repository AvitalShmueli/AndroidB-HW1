package com.example.androidb_hw1;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private MaterialTextView main_LBL_counter;
    private MaterialTextView main_LBL_until_goal;
    private MaterialButton main_BTN_add;
    private RecyclerView main_LST_cupTypes;
    private int ml = 0;
    private int cups = 0;
    private int goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferencesManager.init(this);

        findViews();
        initViews();

        goal = DataManger.getGoalInCups();
        updateUI();
        main_BTN_add.setOnClickListener(v -> {main_LST_cupTypes.setVisibility(View.VISIBLE);});

    }

    private void initViews() {
        TypesAdapter typesAdapter =  new TypesAdapter(getApplicationContext(), DataManger.getCupTypes());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        main_LST_cupTypes.setLayoutManager(linearLayoutManager);
        main_LST_cupTypes.setAdapter(typesAdapter);
        typesAdapter.setTypesCallback(new CupTypeCallback() {
            @Override
            public void cupTypeSelected(CupType cupType, int position) {
                updateDrinkCounter(cupType);
            }
        });

    }

    private void updateDrinkCounter(CupType cupType){
        cups += cupType.getCapacity()/220;
        ml += cupType.getCapacity();
        Log.d("cupTypeSelected", "cupTypeSelected++ "+cups+" ("+ml+")");
        updateUI();
    }

    private void updateUI(){
        main_LBL_counter.setText(getString(R.string.counter_text,cups));
        if(cups < goal) {
            main_LBL_until_goal.setText(getString(R.string.counter_text_left_until_goal, goal - cups));
        } else {
            main_LBL_until_goal.setTextColor(getColor(R.color.blue500));
            main_LBL_until_goal.setText(R.string.goal_reached);
        }
        main_LST_cupTypes.setVisibility(View.INVISIBLE);
    }

    private void findViews() {
        main_LBL_counter = findViewById(R.id.main_LBL_counter);
        main_LBL_until_goal = findViewById(R.id.main_LBL_until_goal);
        main_BTN_add = findViewById(R.id.main_BTN_add);
        main_LST_cupTypes = findViewById(R.id.main_LST_cupTypes);
    }
}