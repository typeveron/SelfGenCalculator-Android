package com.example.math_quiz;

import android.widget.Toast;

import java.io.Serializable;
import java.util.Comparator;


public class AnswerInfo implements Serializable {
    String textAnswer;
    float result;
    String isCorrectNotTwo;

    public AnswerInfo(String textAnswer, float result, String isCorrectNotTwo) {
        this.textAnswer = textAnswer;
        this.result = result;
        this.isCorrectNotTwo = isCorrectNotTwo;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {

        this.textAnswer = textAnswer;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public String getIsCorrectNotTwo() {
        return isCorrectNotTwo;
    }

    public void setIsCorrectNotTwo(String isCorrectNotTwo) {
        this.isCorrectNotTwo = isCorrectNotTwo;
    }

    @Override
    public String toString() {
        return textAnswer + " = " + result + " " + isCorrectNotTwo + "\n";
    }

    //right to wrong
    public static final Comparator<AnswerInfo> sortAnswersAscending = new Comparator<AnswerInfo>() {
        @Override
        public int compare(AnswerInfo o1, AnswerInfo o2) {
            return o1.isCorrectNotTwo.compareTo(o2.isCorrectNotTwo);
        }
    };

    //wrong to right
    public static final Comparator<AnswerInfo> sortAnswersDescending = new Comparator<AnswerInfo>() {
        @Override
        public int compare(AnswerInfo o1, AnswerInfo o2) {
            return o2.isCorrectNotTwo.compareTo(o1.isCorrectNotTwo);
        }
    };
}