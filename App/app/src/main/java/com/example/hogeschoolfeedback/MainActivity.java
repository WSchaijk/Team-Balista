package com.example.hogeschoolfeedback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.api.DataGrabber;
import com.example.api.Question;
import com.example.api.QuestionType;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataGrabber Grabber;

    private TextView RoomStatus;
    private ConstraintLayout Layout;
    private int FinalViewId;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.Grabber = new DataGrabber();
        this.RoomStatus = findViewById( R.id.room_status );
        this.Layout = findViewById( R.id.supermain );
        this.FinalViewId = R.id.poll_title;

        List<Question> questions = this.Grabber.getQuestions();
        for ( Question question : questions ) {
            this.addPoll( question );
        }

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                checkRoomStatus();
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed( runnable, 1000);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addPoll(final Question question ) {
        TextView poll = new TextView( this );

        final Activity currentActivity = this;
        String title = question.getAsker() + ": " + question.getQuestionText();
        int id = View.generateViewId();

        poll.setText( title );
        poll.setTextSize( 18 );
        poll.setTextColor( Color.BLACK );
        poll.setId( id );
        poll.setPadding( 51, 30, 0, 0 );

        poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent( currentActivity, QuestionActivity.class );
                intent.putExtra( "QUESTIONID", question.getQuestionId() );
                intent.putExtra( "QUESTIONTEXT", question.getQuestionText() );
                intent.putExtra( "QUESTIONTYPEID", question.getQuestionTypeId() );
                startActivity( intent );
            }
        });

        this.Layout.addView( poll );

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone( this.Layout );
        constraintSet.connect( id, ConstraintSet.TOP, this.FinalViewId, ConstraintSet.BOTTOM );
        constraintSet.applyTo( this.Layout );

        this.FinalViewId = id;
    }

    private void checkRoomStatus() {
        if ( this.Grabber.getClassAvailability() ) {
            this.RoomStatus.setText( "H.1.215 is vrij" );
        } else {
            this.RoomStatus.setText( "H.1.215 is bezet" );
        }
    }
}
