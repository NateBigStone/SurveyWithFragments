package com.clara.surveyfragmentoutline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements SurveyFragment.SurveyListener, ResultFragment.ResetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurveyFragment surveyFragment = SurveyFragment.newInstance();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, surveyFragment);
        ft.commit();
    }

    @Override
    public void surveyResults(int yes, int no) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ResultFragment resultFragment = ResultFragment.newInstance(yes, no);
        ft.replace(R.id.container, resultFragment);
        ft.addToBackStack("RESULT");
        ft.commit();
        // launch ResultActivity

    }

    @Override
    public void resetSurvey() {

        // Make brand new survey activity, replace result activity.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SurveyFragment resultFragment = SurveyFragment.newInstance();
        ft.replace(R.id.container, resultFragment);
        ft.commit();

    }
}
