package com.example.fragmentorient;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ListFgt extends Fragment {
    private OnItemSelectedListener listener;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fgt, container, false);
        Button dateButton = (Button) view.findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateTime("Date");
            }
        });
        Button timeButton = (Button) view.findViewById(R.id.timeButton);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateTime("Time");
            }
        });
        return view;
    }
    public interface OnItemSelectedListener {
        void onDateTimeItemSelected(String arg);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else { throw new ClassCastException(context.toString()  + "No interface");}
    }
    public void updateDateTime(String dateTime) {
        DateFormat dformat;
        if(dateTime.equals("Date")) dformat = new SimpleDateFormat("MM/dd/yyyy");
        else dformat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String localTime = dformat.format(cal.getTime());
        listener.onDateTimeItemSelected(localTime);
    }
}