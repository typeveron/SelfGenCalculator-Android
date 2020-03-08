package com.example.math_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final static int REQUEST_CODE1 = 1;

    TextView theTitle;
    EditText editTextAns, editTextG;
    Button but_one, but_two, but_three, but_four, but_five, but_six, but_seven, but_eight, but_nine, but_period, but_zero,
            but_hyp, but_generate, but_validate, but_clear, but_score, but_finish;

    String disString = "";
    DCollection dataCollection;

    int inputOne;
    int operator;
    int inputTwo;
    String isCorrectNotTwo;
    String textAnswer;
    String messageRight = "Your right";
    String messageWrong = "Your wrong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        //initialize textview
        theTitle = findViewById(R.id.textView2);


        //initialize editText
        editTextAns = findViewById(R.id.editTextA);
        editTextG = findViewById(R.id.editText2);

        //initialize buttons
        but_one = findViewById(R.id.button1);
        but_two = findViewById(R.id.button2);
        but_three = findViewById(R.id.button3);
        but_four = findViewById(R.id.button4);
        but_five = findViewById(R.id.button5);
        but_six = findViewById(R.id.button6);
        but_seven = findViewById(R.id.button7);
        but_eight = findViewById(R.id.button8);
        but_nine = findViewById(R.id.button9);
        but_period = findViewById(R.id.buttonPer);
        but_zero = findViewById(R.id.button0);
        but_hyp = findViewById(R.id.buttonHyp);
        but_generate = findViewById(R.id.buttonGen);
        but_validate = findViewById(R.id.buttonVal);
        but_clear = findViewById(R.id.buttonCle);
        but_score = findViewById(R.id.buttonScore);
        but_finish = findViewById(R.id.buttonFin);

        //method for buttons to work
        but_one.setOnClickListener(this);
        but_two.setOnClickListener(this);
        but_three.setOnClickListener(this);
        but_four.setOnClickListener(this);
        but_five.setOnClickListener(this);
        but_six.setOnClickListener(this);
        but_seven.setOnClickListener(this);
        but_eight.setOnClickListener(this);
        but_nine.setOnClickListener(this);
        but_period.setOnClickListener(this);
        but_zero.setOnClickListener(this);
        but_hyp.setOnClickListener(this);
        but_generate.setOnClickListener(this);
        but_validate.setOnClickListener(this);
        but_clear.setOnClickListener(this);
        but_score.setOnClickListener(this);
        but_finish.setOnClickListener(this);
        dataCollection = new DCollection();

        //store user input as a string

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                disString = disString + "1";
                editTextAns.setText(disString);
                break;
            case R.id.button2:
                disString = disString + "2";
                editTextAns.setText(disString);
                break;
            case R.id.button3:
                disString = disString + "3";
                editTextAns.setText(disString);
                break;
            case R.id.button4:
                disString = disString + "4";
                editTextAns.setText(disString);
                break;
            case R.id.button5:
                disString = disString + "5";
                editTextAns.setText(disString);
                break;
            case R.id.button6:
                disString = disString + "6";
                editTextAns.setText(disString);
                break;
            case R.id.button7:
                disString = disString + "7";
                editTextAns.setText(disString);
                break;
            case R.id.button8:
                disString = disString + "8";
                editTextAns.setText(disString);
                break;
            case R.id.button9:
                disString = disString + "9";
                editTextAns.setText(disString);
                break;
            case R.id.buttonPer:
                disString = disString + ".";
                editTextAns.setText(disString);
                break;
            case R.id.button0:
                disString = disString + "0";
                editTextAns.setText(disString);
                break;
            case R.id.buttonHyp:
                disString = disString + "-";
                editTextAns.setText(disString);
                break;
            case R.id.buttonGen:
                genQuestion();
                break;
            case R.id.buttonVal:
                checkAns();
                break;
            case R.id.buttonCle:
                clearAll();
                break;
            case R.id.buttonScore:
                seeScore();
                break;
            case R.id.buttonFin:
                finish();
                break;
        }

    }

    private void genQuestion() {
        inputOne = (int) (Math.random() * 10);
        operator = (int) (Math.random() * 4);
        String operatorSwitch = null;

        switch (operator) {

            case 0:
                operatorSwitch = "+";
                break;
            case 1:
                operatorSwitch = "-";
                break;
            case 2:
                operatorSwitch = "*";
                break;
            case 3:
                operatorSwitch = "/";
                break;

        }
        inputTwo = (int) (Math.random() * 10);
        String textG1 = String.valueOf(inputOne);
        String textG2 = operatorSwitch;
        String textG3 = String.valueOf(inputTwo);
        textAnswer = textG1 + textG2 + textG3;
        editTextG.setText(textAnswer);
    }

    public void checkAns() {


        String sAnswer = editTextAns.getText().toString();
        float guessRandomAnswer = Float.valueOf(sAnswer);
        String inputG = editTextG.getText().toString();


        Float answer = null;

        switch (operator) {

            case 0:
                answer = (float) inputOne + inputTwo;
                break;
            case 1:
                answer = (float) inputOne - inputTwo;
                break;
            case 2:
                answer = (float) inputOne * inputTwo;
                break;
            case 3:
                answer = (float) inputOne / inputTwo;
                break;
            case 4:
                answer = (float) inputOne / 0;
                Toast.makeText(this, "Cannot divide 0, Generate another question", Toast.LENGTH_LONG).show();
                break;
        }

        if (answer == guessRandomAnswer) {
            editTextG.setText(messageRight);
            isCorrectNotTwo = messageRight;

        } else {
            editTextG.setText(messageWrong);
            isCorrectNotTwo = messageWrong;
        }


        float result = Float.valueOf(editTextAns.getText().toString());
        System.out.println(textAnswer + result + isCorrectNotTwo);
        AnswerInfo answerInfo = new AnswerInfo(textAnswer, result, isCorrectNotTwo);
        dataCollection.getAnswerArray().add(answerInfo);

    }

    private void clearAll() {
        editTextAns.setText("");
        disString = "";
    }

    private void seeScore() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleContent", dataCollection);

        Intent intent = new Intent(this, Result.class);

        intent.putExtra("intentExtra", bundle);
        startActivityForResult(intent,1);
    }

    public void finish() {
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE1) {
            String recievedData = (String) data.getStringExtra("return_result");

            if (resultCode == RESULT_OK)
                theTitle.setText(recievedData);
            else
                theTitle.setText("Cancelled from text2");
        }
    }
}