package com.clara.surveyfragmentoutline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity
        implements QuestionFragment.NewQuestionListener, SurveyFragment.SurveyListener, ResultFragment.ResetListener {

    private static final String TAG_QUESTION_FRAG = "QUESTION FRAGMENT";
    private static final String TAG_SURVEY_FRAG = "SURVEY FRAGMENT";
    private static final String TAG_RESULT_FRAG = "RESULT FRAGMENT";

    private static final String TAG = "MAIN ACTIVITY";

    private String mQuestion = "";
    private String mNoAnswer = "";
    private String mYesAnswer = "";
    private int mYesCount = 0;
    private int mNoCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuestionFragment questionFragment = QuestionFragment.newInstance();
        SurveyFragment surveyFragment = SurveyFragment.newInstance();
        ResultFragment resultFragment = ResultFragment.newInstance(mYesCount, mNoCount, mYesAnswer,mNoAnswer);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //add including tags to help find the fragments on screen, if they need ot be updated

        ft.add(R.id.question_view_container, questionFragment, TAG_QUESTION_FRAG);
        ft.add(R.id.survey_view_container, surveyFragment, TAG_SURVEY_FRAG);
        ft.add(R.id.results_view_container, resultFragment, TAG_RESULT_FRAG);

        ft.commit();

    }

    @Override
    public void newQuestionCreated(String newQuestion, String yesAnswer, String noAnswer) {

        //This should be done in a better way

        Log.d(TAG, "Notified that this new item was created: " + newQuestion);
        mQuestion = newQuestion;
        mYesAnswer = yesAnswer;
        mNoAnswer = noAnswer;

        FragmentManager fm = getSupportFragmentManager();
        SurveyFragment surveyFragment = (SurveyFragment) fm.findFragmentByTag(TAG_SURVEY_FRAG);
        surveyFragment.newQuestion(mQuestion, mYesAnswer, mNoAnswer);
        hideKeyboard();
    }

    @Override
    public void surveyResults(int yes, int no, String yesAnswer, String noAnswer) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ResultFragment newResultFragment = ResultFragment.newInstance(yes, no, yesAnswer, noAnswer);
        ft.replace(R.id.results_view_container, newResultFragment);
        ft.addToBackStack("RESULT");
        ft.commit();
        // launch ResultActivity

    }

    @Override
    public void resetSurvey() {

         //Make brand new survey activity, replace result activity.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        QuestionFragment newQuestionFragment = QuestionFragment.newInstance();
        SurveyFragment newSurveyFragment = SurveyFragment.newInstance();
        ResultFragment newResultFragment = ResultFragment.newInstance(0,0,"","");
        ft.replace(R.id.question_view_container, newQuestionFragment);
        ft.replace(R.id.survey_view_container, newSurveyFragment);
        ft.replace(R.id.results_view_container, newResultFragment);
        ft.commit();

    }

    private void hideKeyboard() {
        View mainView = findViewById(android.R.id.content);
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(mainView.getWindowToken(), 0);
    }
}
