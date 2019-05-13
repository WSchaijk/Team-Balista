package com.example.api;

public class Answer {

    private int AnswerId;
    private String AnswerText;
    private Question Question;

    public Answer( String answerText ) {
        AnswerText = answerText;
    }
}
