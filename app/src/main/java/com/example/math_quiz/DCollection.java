package com.example.math_quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DCollection implements Serializable {
    private List<AnswerInfo> answerArray = new ArrayList<>();
    public List<AnswerInfo> getAnswerArray() {
        return answerArray;
    }
}
