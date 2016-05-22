package com.example.admin.demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ToggleView mToggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToggleView = (ToggleView) findViewById(R.id.toggleview);
        mToggleView.setSwitchBackgroundID(R.drawable.switch_background);
        mToggleView.setSlideButtonBackgroundID(R.drawable.slide_button_background);
       mToggleView.setToggleState(true);
        mToggleView.setOnToggleStateChangeListener(new OnToggleStateChangeListener() {
            @Override
            public void onToggleStateChange(boolean state) {
                if (state){
                    Toast.makeText(MainActivity.this,"开关已经开启",0).show();
                }else {
                    Toast.makeText(MainActivity.this,"开关已经关闭",0).show();
                }
            }
        });
    }
}
