package com.clara.surveyfragmentoutline;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SurveyFragment extends Fragment {

    private int mYesCount = 0;
    private int mNoCount = 0;

    interface SurveyListener {
        void surveyResults(int yes, int no);
    }

    private SurveyListener listener;

    public SurveyFragment() {
        // Required empty public constructor
    }


    public static SurveyFragment newInstance() {
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

        Button yesButton = view.findViewById(R.id.yes_button);
        Button noButton = view.findViewById(R.id.no_button);
        Button showResults  = view.findViewById(R.id.results_button);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SURVEY", "Yes " + mYesCount);
                mYesCount++;
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SURVEY", "No " + mNoCount);
                mNoCount++;
            }
        });

        showResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.surveyResults(mYesCount, mNoCount);
            }
        });


        return view;
    }

}
