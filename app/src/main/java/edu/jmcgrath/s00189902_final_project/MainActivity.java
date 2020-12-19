package edu.jmcgrath.s00189902_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int Blue = 1;
    private final int Red =2;
    private final int Green=3;
    private final int Purple=4;

    Button btnBlue,btnRed,btnGreen,btnPurple,btn;

    int sequenceCount=4,n=0;

    int[] gamesSequence= new int[120];
    int arrayIndex=0;

    CountDownTimer ct = new CountDownTimer(6000,1500) {
        @Override
        public void onTick(long millisUntilFinished) {
            rndBTN();
        }

        @Override
        public void onFinish() {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBlue = findViewById(R.id.btnBlue);
        btnGreen =findViewById(R.id.btnGreen);
        btnPurple = findViewById(R.id.btnPurple);
        btnRed = findViewById(R.id.btnRed);
    }


    private int getRandom(int maxValue){return ((int)((Math.random()*maxValue)+1));}

    private void btnFlash(Button button)
    {
        btn = button;
        Handler handle = new Handler();
        Runnable r = new Runnable() {

            public void run() {
                btn.setPressed(true);
                btn.invalidate();
                btn.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {

                    public void run() {
                        btn.setPressed(false);
                        btn.invalidate();
                    }
                };
                handler1.postDelayed(r1,600);
            }
        };
        handle.postDelayed(r,600);
    }

    public void doPlay(View view)
    {
        ct.start();
        for(int i=0;i < sequenceCount;i++)
        {
            int x =getRandom(sequenceCount);

            Toast.makeText(this,"Number = "+x,Toast.LENGTH_SHORT).show();

            if(x==1)
                btnFlash(btnBlue);
            if(x==2)
                btnFlash(btnRed);
            if(x==3)
                btnFlash(btnGreen);
            if(x==4)
                btnFlash(btnPurple);

        }
    }
    private void rndBTN()
    {
        n = getRandom(sequenceCount);

        Toast.makeText(this,"Number = "+n,Toast.LENGTH_SHORT).show();

        switch (n)
        {
            case 1:
                btnFlash(btnBlue);
                gamesSequence[arrayIndex++]=Blue;
                break;
            case 2:
                btnFlash(btnRed);
                gamesSequence[arrayIndex++]=Red;
                break;
            case 3:
                btnFlash(btnGreen);
                gamesSequence[arrayIndex++]=Green;
                break;
            case 4:
                btnFlash(btnPurple);
                gamesSequence[arrayIndex++]=Purple;
                break;
        }
    }



}