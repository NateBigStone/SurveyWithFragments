package com.clara.surveyfragmentoutline;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ResultFragment extends Fragment {

    interface ResetListener {
        void resetSurvey();
    }

    private ResetListener listener;

    private static final String ARG_YES_COUNT = "arg_yes";
    private static final String ARG_NO_COUNT = "arg_no";

    private int yesCount;
    private int noCount;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(int yes, int no) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_YES_COUNT, yes);
        args.putInt(ARG_NO_COUNT, no);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            yesCount = getArguments().getInt(ARG_YES_COUNT);
            noCount = getArguments().getInt(ARG_NO_COUNT);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (ResetListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView yesView = view.findViewById(R.id.yes_count);
        yesView.setText("YES: " + yesCount);

        TextView noView = view.findViewById(R.id.no_count);
        noView.setText("NO: " + noCount);

        Button reset = view.findViewById(R.id.reset_button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.resetSurvey();
            }
        });


        return view;

    }




}
