package com.example.c.myapplication;

import android.app.*;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RelativeLayout relative = (RelativeLayout)findViewById(R.id.relative);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mLayoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final ViewGroup container = (ViewGroup) mLayoutInflater.inflate(R.layout.textview,null);
                mPopupWindow = new PopupWindow(container, ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,false);
                mPopupWindow.showAtLocation(relative,Gravity.NO_GRAVITY,500,500);

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mPopupWindow.dismiss();
                        return false;
                    }
                });

                container.setOnTouchListener(new View.OnTouchListener() {
                    int orgX, orgY;
                    int offsetX, offsetY;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                orgX = (int) event.getX();
                                orgY = (int) event.getY();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                offsetX = (int)event.getRawX() - orgX;
                                offsetY = (int)event.getRawY() - orgY;
                                mPopupWindow.update(offsetX, offsetY, -1, -1, true);
                                break;
                        }
                        return true;
                    }});






                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
