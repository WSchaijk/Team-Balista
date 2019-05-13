package com.example.api;

import android.arch.core.util.Function;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.*;

public class DataGrabber {

    private final String DataUrl = "http://145.137.57.163:5000/api/";
    private final String SensorUrl = "http://www.prepareme.nl/";

    private String makeServiceCall(String baseUrl, String uri) {
        String response = null;

        try {
            URL url = new URL( baseUrl + uri );
            HttpURLConnection conn = ( HttpURLConnection ) url.openConnection();
            conn.setRequestMethod( "GET" );

            InputStream in = new BufferedInputStream( conn.getInputStream() );
            response = convertStreamToString( in );
        } catch ( Exception e ) {
            return null;
        }

        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public List<QuestionType> getQuestionTypes() {
        String jsonString = makeServiceCall( this.DataUrl, "questiontype" );

        try {
            List<QuestionType> questionTypes = new ArrayList<>();

            JSONArray jsonArray = new JSONArray( jsonString );

            for (int i = 0; i < jsonArray.length(); i++) {
                int questionTypeId = jsonArray.getJSONObject(i).getInt("questionTypeId" );
                String questionTypeName = jsonArray.getJSONObject(i).getString( "questionTypeName" );

                questionTypes.add( new QuestionType( questionTypeId, questionTypeName ) );
            }

            return questionTypes;
        } catch ( Exception e ) {
            return null;
        }
    }

    public List<Question> getQuestions() {
        String jsonString = makeServiceCall( this.DataUrl, "question" );

        try {
            List<Question> questions = new ArrayList<>();

            JSONArray jsonArray = new JSONArray( jsonString );

            for (int i = 0; i < jsonArray.length(); i++) {
                int questionId = jsonArray.getJSONObject(i).getInt( "questionId" );
                String questionText = jsonArray.getJSONObject(i).getString( "questionText" );
                String asker = jsonArray.getJSONObject(i).getString( "asker" );
                int questionTypeId = jsonArray.getJSONObject(i).getInt( "questionTypeId" );

                questions.add( new Question( questionId, questionText, asker, questionTypeId ) );
            }

            List<Question> result = new ArrayList<Question>();
            result.add( questions.get( 0 ) );

            return result;
        } catch ( Exception e ) {
            return null;
        }
    }

    public List<Answer> getAnswers( int QuestionId ) {
        String jsonString = makeServiceCall( this.DataUrl, "answer/" + QuestionId );

        if ( jsonString == null ) {
            return null;
        }

        try {
            List<Answer> answers = new ArrayList<>();

            JSONArray jsonArray = new JSONArray( jsonString );

            for (int i = 0; i < jsonArray.length(); i++) {

                String answerText = jsonArray.getJSONObject(i).getString( "answerText" );
                answers.add( new Answer( answerText ) );
            }

            return answers;
        } catch ( Exception e ) {
            return null;
        }
    }

    public boolean getClassAvailability() {
        String jsonString = makeServiceCall( this.SensorUrl, "Lokaal.php" );

        try {
            JSONObject jsonObject = new JSONObject( jsonString );
            JSONArray jsonArray = jsonObject.getJSONArray( "H.1.215" );

            if (jsonArray.getString(0).equals("0")) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            return false;
        }
    }
}
