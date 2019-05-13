package com.example.hogeschoolfeedback;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.api.Answer;
import com.example.api.DataGrabber;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private TextView Title;

    private DataGrabber Grabber;
    private Intent Intent;
    private ConstraintLayout Layout;

    private int counter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        this.Intent = getIntent();
        this.Grabber = new DataGrabber();
        this.counter = 0;

        this.Title = findViewById( R.id.question );
        this.Layout = findViewById( R.id.ultramain );
        this.Title.setText( this.Intent.getStringExtra( "QUESTIONTEXT" ) );

        if ( this.Intent.getIntExtra( "QUESTIONTYPEID", 2 ) == 2 ) {
            getYesNoAnswers();
        } else {
            getMultipleAnswers();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.counter = this.counter + 1;
        if ( this.counter > 1 ) {
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void getYesNoAnswers() {
        final Activity currentActivity = this;
        Button yesButton = new Button( this );
        Button noButton = new Button( this );

        int yesId = View.generateViewId();
        int noId = View.generateViewId();

        yesButton.setText( "Eens" );
        yesButton.setId( yesId );
        yesButton.setLayoutParams( new Constraints.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent( currentActivity, ThanksActivity.class );
                startActivity( nextIntent );

            }
        });

        noButton.setText( "Oneens" );
        noButton.setId( noId );
        noButton.setLayoutParams( new Constraints.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent( currentActivity, ThanksActivity.class );
                startActivity( nextIntent );

            }
        });

        this.Layout.addView( yesButton );
        this.Layout.addView( noButton );

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone( this.Layout );
        constraintSet.connect( yesId, ConstraintSet.TOP, R.id.question, ConstraintSet.BOTTOM );
        constraintSet.connect( noId, ConstraintSet.TOP, yesId, ConstraintSet.BOTTOM );
        constraintSet.applyTo( this.Layout );
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void getMultipleAnswers() {
        getYesNoAnswers();
    }
}
