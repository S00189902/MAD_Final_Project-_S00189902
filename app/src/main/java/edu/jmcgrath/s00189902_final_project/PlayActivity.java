package edu.jmcgrath.s00189902_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity implements SensorEventListener
{

    private final double Up_Forward = 9.0;
    private final double Up_Backward = 6.0;

    boolean highlimit = false;
    int counter =0;

    Button btnblue,btnpurple,btngreen,btnred;
    private SensorManager msesnorManager;
    private Sensor mSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        btnblue = findViewById(R.id.btnBlue);
        btnpurple = findViewById(R.id.btnPurple);
        btngreen = findViewById(R.id.btnGreen);
        btnred = findViewById(R.id.btnRed);
        msesnorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = msesnorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    protected void onResume()
    {
        super.onResume();
        msesnorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause()
    {
        super.onPause();
        msesnorManager.unregisterListener(this);
    }



    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if((x > Up_Forward)&&(highlimit==false))
        {
            highlimit = true;
        }
        if((x < Up_Backward)&&(highlimit==true))
        {
            counter++;
            Toast.makeText(this,"Number = "+x,Toast.LENGTH_SHORT).show();
            highlimit=false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public static double round(double value,int places)
    {
        if(places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10,places);
        value = value * factor;
        long tmp=Math.round(value);
        return (double)tmp/factor;
    }

}