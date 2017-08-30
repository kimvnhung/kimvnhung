package com.kimvan.hung.parcelable;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener,
        GestureDetector.OnGestureListener{

    GestureDetectorCompat gestureDetectorCompat;

    VerbWordMeaning verbWordMeaning;

    ArrayList<String> data = new ArrayList<>();

    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetectorCompat = new GestureDetectorCompat(this,this);
        gestureDetectorCompat.setOnDoubleTapListener(this);

        input = (EditText)findViewById(R.id.input_edittxt);

        verbWordMeaning = new VerbWordMeaning();
    }

    public void saveClicked(View view){
        verbWordMeaning = new VerbWordMeaning(data.get(1),data.get(2));
        verbWordMeaning.set_stt(Integer.parseInt(data.get(0)));
        ArrayList<VerbWordMeaning.Conjugation> letemps = new ArrayList<>();

        letemps.add(verbWordMeaning.setAnConjugation(Integer.parseInt(data.get(3)),data.get(4),data.get(5),data.get(6)));
        letemps.add(verbWordMeaning.setAnConjugation(Integer.parseInt(data.get(7)),data.get(8),data.get(9),data.get(10)));

        verbWordMeaning.setLesTemps(letemps);

        Intent intent = new Intent(this,Main2Activity.class);

        intent.putExtra("verbMeaning", verbWordMeaning);

        startActivity(intent);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if(verbWordMeaning.getLesTemps().size()<3){
            data.add(input.getText().toString());
        }
        input.setText("");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
