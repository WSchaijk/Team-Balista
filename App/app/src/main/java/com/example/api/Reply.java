package com.example.api;

public class Reply {

    private int ReplyId;
    private String ReplyText;
    private Question Question;

    public Reply( int replyId, String replyText, Question question) {
        this.ReplyId = replyId;
        this.ReplyText = replyText;
        this.Question = question;
    }
}
