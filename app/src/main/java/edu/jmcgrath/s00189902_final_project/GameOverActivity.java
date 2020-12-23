package edu.jmcgrath.s00189902_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void doRetry(View view)
    {
        Intent start = new Intent(this,MainActivity.class);
        MainActivity.sequenceCount = 4;
        startActivity(start);
    }

    public void doScores(View view)
    {

    }
}