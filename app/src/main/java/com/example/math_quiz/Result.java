package com.example.math_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static com.example.math_quiz.AnswerInfo.sortAnswersAscending;
import static com.example.math_quiz.AnswerInfo.sortAnswersDescending;

public class Result extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroup, radioGroupSort;
    RadioButton rbut_all, rbut_right, rbut_wrong, rbut_sorta, rbut_sortb;
    Button show_but, back_but;
    EditText regText, scoreText;
    List<AnswerInfo> answerList;
    TextView tResults;
    String str = "";
    float questionsTotal;
    String percentageStr;
    int questionsRight=0;
    String theNameReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
        getMyIntent();
    }


    private void initialize() {
        radioGroupSort = findViewById(R.id.rGroupSort);
        radioGroup = findViewById(R.id.rGroup);
        rbut_all = findViewById(R.id.radioButton);
        rbut_right = findViewById(R.id.radioButton2);
        rbut_wrong = findViewById(R.id.radioButton3);
        rbut_sorta = findViewById(R.id.radioButton4);
        rbut_sortb = findViewById(R.id.radioButton5);
        show_but = findViewById(R.id.sButton);
        show_but.setOnClickListener(this);
        back_but = findViewById(R.id.bButton);
        back_but.setOnClickListener(this);
        regText = findViewById(R.id.editText4);
        tResults = findViewById(R.id.displayRes);
        scoreText = findViewById(R.id.editText6);
    }

    private void getMyIntent() {
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("intentExtra");

        Serializable bundleContent = bundle.getSerializable("bundleContent");

        DCollection dataCollection = (DCollection) bundleContent;

        answerList = dataCollection.getAnswerArray();

        calculatePercentage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sButton:
                showResult();
                break;
            case R.id.bButton:
                goBack();
                break;
        }
    }


    private void showResult() {
        str = "";
        int checkRadioButtonResult = radioGroup.getCheckedRadioButtonId();
        int checkRadioButtonSort = radioGroupSort.getCheckedRadioButtonId();

        switch (checkRadioButtonSort) {
            case R.id.radioButton4:
                showSortA();
                break;
            case R.id.radioButton5:
                showSortB();
                break;
        }


        switch (checkRadioButtonResult) {
            case R.id.radioButton:
                showRAll();
                break;
            case R.id.radioButton2:
                showRRight();
                break;
            case R.id.radioButton3:
                showRWrong();
                break;
        }


    }

    private void showRAll() {
        for (AnswerInfo oneAns : answerList) {
            str = str + oneAns;
        }
        tResults.setText(str);
    }

    private void showRRight() {
        for (AnswerInfo oneAns : answerList) {
            if (oneAns.getIsCorrectNotTwo().equals("Your right"))
            str = str + oneAns;
        }
        tResults.setText(str);
    }

    private void showRWrong() {
        for (AnswerInfo oneAns : answerList) {
            if (oneAns.getIsCorrectNotTwo().equals("Your wrong"))
            str = str + oneAns;
        }
        tResults.setText(str);
    }

    private void showSortA() {
         Collections.sort(answerList,sortAnswersAscending);
    }

    private void showSortB() {
        Collections.sort(answerList, sortAnswersDescending);
    }

    public void calculatePercentage() {
        questionsTotal = answerList.size();
        for (AnswerInfo oneAns : answerList) {
            if (oneAns.getIsCorrectNotTwo().equals("Your right"))
                questionsRight = questionsRight + 1;
        }
        int percentage = (int)(questionsRight/questionsTotal * 100);
        percentageStr = String.valueOf(percentage);
        scoreText.setText(percentageStr+" %" );
    }

    public void goBack() {
        if (regText.length() == 0) {
            Toast.makeText(this, "Enter a name", Toast.LENGTH_LONG).show();
        } else {
            theNameReg = regText.getText().toString();
            String theResult = theNameReg + " Score: " + percentageStr + "%";
            Intent intent = new Intent();
            intent.putExtra("return_result", theResult);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}