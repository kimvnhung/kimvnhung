package com.kimvan.hung.parcelable;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements GestureDetector.OnGestureListener ,
GestureDetector.OnDoubleTapListener{

    TextView show;
    ArrayList<String> data = new ArrayList<>();

    GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        show = (TextView) findViewById(R.id.txt_show);

        VerbWordMeaning verbWordMeaning = getIntent().getParcelableExtra("verbMeaning");
        data.add(String.valueOf(verbWordMeaning.get_stt()));
        data.add(verbWordMeaning.get_enAnglais());
        data.add(verbWordMeaning.get_enVietnamien());
        data.add(String.valueOf(verbWordMeaning.getLesTemps().get(0).get_id()));
        data.add(verbWordMeaning.getLesTemps().get(0).get_leTemps());
        data.add(verbWordMeaning.getLesTemps().get(0).get_je());
        data.add(verbWordMeaning.getLesTemps().get(0).get_tu());
        data.add(String.valueOf(verbWordMeaning.getLesTemps().get(1).get_id()));
        data.add(verbWordMeaning.getLesTemps().get(1).get_leTemps());
        data.add(verbWordMeaning.getLesTemps().get(1).get_je());
        data.add(verbWordMeaning.getLesTemps().get(1).get_tu());

        gestureDetectorCompat = new GestureDetectorCompat(this,this);
        gestureDetectorCompat.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        String curr = show.getText().toString();
        for (int i=0;i<data.size();i++){
            if (curr.equals(data.get(i))){
                show.setText(data.get(i+1));
                break;
            }
        }
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
