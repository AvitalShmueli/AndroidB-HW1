package com.example.androidb_hw1;

import com.example.common.CupType;
import com.example.common.DataManagerBase;

import java.util.ArrayList;

public class DataManager extends DataManagerBase {
    public ArrayList<CupType> getCupTypes() {
        ArrayList<CupType> cupTypes = new ArrayList<>();

        cupTypes.add(new CupType().setType("cup").setCapacity(220).setDrawableImg(R.drawable.glass_24));
        cupTypes.add(new CupType().setType("bottle").setCapacity(500).setDrawableImg(R.drawable.water_bottle_24));
        cupTypes.add(new CupType().setType("bottle").setCapacity(1000).setDrawableImg(R.drawable.water_bottle_24));

        return cupTypes;
    }

    public int getGoalInCups(){return 10;}
    public int getGoalInMilliliter(){return 2000;}
}
