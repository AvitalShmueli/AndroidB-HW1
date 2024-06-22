package com.example.common;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class RecordsList {
    public static final String RECORDS_TABLE = "RECORDS";

    private String listName = "";
    private ArrayList<Record> recordsArrayList = new ArrayList<>();
    private int streak = 0;

    public RecordsList() {
    }

    public String getListName() {
        return listName;
    }

    public RecordsList setListName(String listName) {
        this.listName = listName;
        return this;
    }

    public ArrayList<Record> getRecordsArrayList() {
        return recordsArrayList;
    }



    public RecordsList setRecordsArrayList(ArrayList<Record> recordsArrayList) {
        this.recordsArrayList = recordsArrayList;
        return this;
    }

    public RecordsList setRecordArrayList(ArrayList<Record> recordsArrayList) {
        this.recordsArrayList = recordsArrayList;
        return this;
    }

    public RecordsList add(Record record) {
        if(!recordsArrayList.isEmpty() && isStreakSaved())
            streak++;
        else streak = 0;
        this.recordsArrayList.add(record);
        return this;
    }

    @Override
    public String toString() {
        return "RecordsList{" +
                "listName='" + listName + '\'' +
                ", recordsArrayList=" + recordsArrayList + '\'' +
                '}';
    }

    public int getSize(){
        return recordsArrayList.size();
    }

    public Record get(int position){
        return recordsArrayList.get(position);
    }

    public Record getTodayRecord() {
        int todayRecordIndex = getIndexOfToday();
        if(todayRecordIndex != -1)
            return recordsArrayList.get(todayRecordIndex);
        return null;
    }

    public int getIndexOfToday(){
       String today = getTodayDateFormatted();
        if(recordsArrayList != null) {
            for (int i = 0; i < recordsArrayList.size(); i++) {
                if (recordsArrayList.get(i).getDate().equals(today))
                    return i;
            }
            return -1;
        }
        return -1;
    }

    public int getCurrentStreak() {
        return streak;
    }

    private String getTodayDateFormatted(){
        Date todayDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String today = dateFormat.format(todayDate);
        //String today = "23-06-2024";
        Log.d("TEST TIME_RecordsList", today);
        return  today;
    }

    private boolean isStreakSaved(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = dateFormat.format(cal.getTime());
        return recordsArrayList.get(recordsArrayList.size() - 1).getDate().equals(yesterday);
    }


}
