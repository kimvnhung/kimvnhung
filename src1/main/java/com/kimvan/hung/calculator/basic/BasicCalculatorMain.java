package com.kimvan.hung.calculator.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kimvan.hung.calculator.R;
import com.kimvan.hung.calculator.ViewerScreenFragment;
import com.kimvan.hung.calculator.calculator.InfoFunction;
import com.kimvan.hung.calculator.sience.SienceCalculator;

public class BasicCalculatorMain extends AppCompatActivity implements BasicKeyboardFragment.BasicKeyboardFragmentListenner {

    @Override
    public void getTextFromKeyboard(String s) {
        ViewerScreenFragment viewerScreenFragment = (ViewerScreenFragment) getSupportFragmentManager().findFragmentById(R.id.viewer_screen_main);
        if(s.equals("")){
            viewerScreenFragment.setNull();
        }else {
            viewerScreenFragment.nextElements(s);
        }
    }

    @Override
    public void equalsFunction() {
        ViewerScreenFragment viewerScreenFragment = (ViewerScreenFragment) getSupportFragmentManager().findFragmentById(R.id.viewer_screen_main);
        viewerScreenFragment.equalsFunction();
    }

    @Override
    public void clear() {
        ViewerScreenFragment viewerScreenFragment = (ViewerScreenFragment) getSupportFragmentManager().findFragmentById(R.id.viewer_screen_main);
        viewerScreenFragment.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_calculator_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sience_calculator) {
            Intent intent = new Intent(this, SienceCalculator.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
