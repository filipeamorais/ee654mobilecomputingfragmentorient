package com.example.fragmentorient;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements ListFgt.OnItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getResources().getBoolean(R.bool.twoPaneMode))  { return; }
        if (savedInstanceState != null) {  // cleanup existing fragments
            getFragmentManager().executePendingTransactions();
            Fragment fragmentById = getFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragmentById != null) {
                getFragmentManager().beginTransaction().remove(fragmentById).commit();
            }
        }
        ListFgt listFragment = new ListFgt();
        FrameLayout viewById = (FrameLayout) findViewById(R.id.fragment_container);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).commit();
    }
    
    @Override
    public void onDateTimeItemSelected(String info) {
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            DetailFgt fragment = (DetailFgt) getFragmentManager().findFragmentById(R.id.detailFragment);
            fragment.setText(info);
        } else {
            DetailFgt newFragment = new DetailFgt();
            Bundle args = new Bundle();
            args.putString(DetailFgt.EXTRA_INFO, info);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
