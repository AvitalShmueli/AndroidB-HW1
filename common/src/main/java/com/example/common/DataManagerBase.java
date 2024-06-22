package com.example.common;

import java.util.ArrayList;

public abstract class DataManagerBase {
    public abstract ArrayList<CupType> getCupTypes() ;

    public abstract int getGoalInCups();
    public abstract int getGoalInMilliliter();
}
