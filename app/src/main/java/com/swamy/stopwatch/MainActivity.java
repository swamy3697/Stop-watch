package com.swamy.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import  android.widget.Button;
import  android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView seconds ,hours,minutes;
    Button startStop,reset;
    boolean running;
    private  int totalSeconds;
    private int secondsOfTime;
    private int hoursOfTime;
    private int minutesOfTime;
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startStop=(Button) findViewById(R.id.startStop);
        reset=(Button) findViewById(R.id.reset);
        seconds=(TextView) findViewById(R.id.seconds);
        minutes=(TextView) findViewById(R.id.minutes);
        hours=(TextView) findViewById(R.id.hours);
        resettime();
        timeRunning();
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(running)
                {
                    startStop.setText("start");

                    running=false;
                    return;
                }
                startStop.setText("stop");
                running =true;

            }
        });
    reset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startStop.setText("start");
            resettime();
        }
    });
    }

    private void timeRunning() {
        handler=new Handler();



        handler.post(new Runnable() {

            @Override
            public void run() {
                secondsOfTime=totalSeconds%60;
                minutesOfTime=(totalSeconds%3600)/60;
                hoursOfTime=totalSeconds/3600;
                String secTime=String.format(Locale.getDefault(),"%02d",secondsOfTime);
                String mintime=String.format(Locale.getDefault(),"%02d",minutesOfTime);
                String hourtime=String.format(Locale.getDefault(),"%d",hoursOfTime);
                seconds.setText(secTime);
                minutes.setText(mintime);
                hours.setText(hourtime);
                if(running)
                {
                    totalSeconds++;
                }

                handler.postDelayed(this,1000);
            }
        });
    }

    private void resettime() {
        totalSeconds=0;
          running=false;
    }

}