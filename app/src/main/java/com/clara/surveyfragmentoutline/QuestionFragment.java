package com.clara.surveyfragmentoutline;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class QuestionFragment extends Fragment {


    public interface NewQuestionListener {
        void newQuestionCreated(String newQuestion, String mYesAnswer, String mNoAnswer);
    }


    private NewQuestionListener mNewQuestionListener;

    public QuestionFragment() {
        // Required empty public constructor
    }


    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof NewQuestionListener){    // Context is the hosting Activity.
            mNewQuestionListener = (NewQuestionListener) context;
        } else  {
            throw new RuntimeException(context.toString() + " must implement NewQuestionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        Button addQuestion = view.findViewById(R.id.add_question_button);
        final EditText questionText = view.findViewById(R.id.new_question);
        final EditText yesText = view.findViewById(R.id.yes_answer);
        final EditText noText = view.findViewById(R.id.no_answer);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newQuestion = questionText.getText().toString() +"?";
                String mYesText = yesText.getText().toString();
                String mNoText = noText.getText().toString();

                if (newQuestion.isEmpty() || mYesText.isEmpty() || mNoText.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter some text", Toast.LENGTH_LONG).show();
                    return;
                }

                //Create a new question
                mNewQuestionListener.newQuestionCreated(newQuestion, mYesText, mNoText);

                questionText.getText().clear();
                yesText.getText().clear();
                noText.getText().clear();
            }
        });

        return view;

    }


}
