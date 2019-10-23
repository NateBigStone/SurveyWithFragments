package com.clara.surveyfragmentoutline;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SurveyFragment extends Fragment {

    private int mYesCount = 0;
    private int mNoCount = 0;
    private String mYesAnswer = "";
    private String mNoAnswer = "";

    Button yesButton;
    Button noButton;

    TextView questionView;

    interface SurveyListener {
        void surveyResults(int yes, int no, String yesAnswer, String noAnswer);
    }

    private SurveyListener listener;

    public SurveyFragment() {
        // Required empty public constructor
    }


    public static SurveyFragment newInstance(String yesAnswer, String noAnswer) {
        return new SurveyFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (SurveyListener) context;  // todo verify it's actually a listener

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_survey, container, false);

        questionView = view.findViewById(R.id.question_text);

        yesButton = view.findViewById(R.id.yes_button);
        yesButton.setText(mYesAnswer);
        noButton = view.findViewById(R.id.no_button);
        noButton.setText(mNoAnswer);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SURVEY", "Yes " + mYesCount + mYesAnswer);
                mYesCount++;
                listener.surveyResults(mYesCount, mNoCount, mYesAnswer, mNoAnswer);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SURVEY", "No " + mNoCount + mNoAnswer);
                mNoCount++;
                listener.surveyResults(mYesCount, mNoCount, mYesAnswer, mNoAnswer);
            }
        });


        return view;
    }

    public void newQuestion(String newQuestion, String yesAnswer, String noAnswer) {

        questionView.setText(newQuestion);
        mYesAnswer = yesAnswer;
        yesButton.setText(mYesAnswer);
        mNoAnswer = noAnswer;
        noButton.setText(mNoAnswer);
    }

}
