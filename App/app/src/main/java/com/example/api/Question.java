package com.example.api;

import java.util.Date;

public class Question {

    private int QuestionId;
    private String QuestionText;
    private String Asker;
    private int QuestionTypeId;

    public Question( int questionId, String questionText, String asker, int questionTypeId ) {
        this.QuestionId = questionId;
        this.QuestionText = questionText;
        this.Asker = asker;
        this.QuestionTypeId = questionTypeId;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public String getAsker() {
        return Asker;
    }

    public int getQuestionTypeId() {
        return QuestionTypeId;
    }

    public int getQuestionId() {
        return QuestionId;
    }
}
