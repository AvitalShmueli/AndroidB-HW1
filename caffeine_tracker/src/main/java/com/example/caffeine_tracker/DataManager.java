package com.example.caffeine_tracker;

import com.example.common.Models.CupType;
import com.example.common.DataManagerBase;

import java.util.ArrayList;

public class DataManager extends DataManagerBase {
    public ArrayList<CupType> getCupTypes() {
        ArrayList<CupType> cupTypes = new ArrayList<>();

        cupTypes.add(new CupType().setType("cup").setCapacity(95).setUnit("mg").setDrawableImg(R.drawable.coffee));
        cupTypes.add(new CupType().setType("black tea").setCapacity(50).setUnit("mg").setDrawableImg(R.drawable.tea));
        cupTypes.add(new CupType().setType("green tea").setCapacity(25).setUnit("mg").setDrawableImg(R.drawable.tea));
        cupTypes.add(new CupType().setType("energy drink").setCapacity(100).setUnit("mg").setDrawableImg(R.drawable.energy_drink));

        return cupTypes;
    }

    public int getGoalInCups(){return 4;}
    public int getGoalInML_MG(){return 400;}

}
