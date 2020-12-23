package edu.jmcgrath.s00189902_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {
    int finalScore;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        listView = findViewById(R.id.lvHiSCore);
        finalScore = getIntent().getIntExtra("score",-1);
        DatabaseHandler db = new DatabaseHandler(this);

        db.emptyHiScores();


        db.addHiScore(new HiScore("20 OCT 2020","Frodo",5));
        db.addHiScore(new HiScore("28 OCT 2020","Dobby",1));
        db.addHiScore(new HiScore("23 dec 2020","Jordan McGrath",finalScore));
        db.addHiScore(new HiScore("20 NOV 2020","DarthV",3));
        db.addHiScore(new HiScore("20 NOV 2020","Bob",2));
        db.addHiScore(new HiScore("22 NOV 2020","Gemma",15));
        db.addHiScore(new HiScore("30 NOV 2020","Joe",4));
        db.addHiScore(new HiScore("01 DEC 2020","DarthV",10));
        db.addHiScore(new HiScore("02 DEC 2020","Gandalf",132));

        List<HiScore> hiScores = db.getAllHiScores();

        for(HiScore hs : hiScores)
        {
            String log =
                    "Id: " + hs.getIdScore() +
                            ", Date: " + hs.getGdate() +
                            " , Player: " + hs.getpName() +
                            " , Score: " + hs.getScore();

            Log.i("Score: ", log);
        }
        Log.i("divider", "====================");

        HiScore singleScore = db.getHiScore(5);
        Log.i("High Score 5 is by ", singleScore.getpName() + " with a score of " +
                singleScore.getScore());

        Log.i("divider", "====================");

        // Calling SQL statement
        List<HiScore> top5HiScores = db.getTopFiveScores();

        for (HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getIdScore() +
                            ", Date: " + hs.getGdate() +
                            " , Player: " + hs.getpName() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }
        Log.i("divider", "====================");

        HiScore hiScore = top5HiScores.get(top5HiScores.size() - 1);
        // hiScore contains the 5th highest score
        Log.i("fifth Highest score: ", String.valueOf(hiScore.getScore()) );

        // simple test to add a hi score
        int myCurrentScore = 40;
        // if 5th highest score < myCurrentScore, then insert new score
        if (hiScore.getScore() < myCurrentScore) {
            db.addHiScore(new HiScore("08 DEC 2020", "Elrond", 40));
        }

        Log.i("divider", "====================");

        // Calling SQL statement
        top5HiScores = db.getTopFiveScores();
        List<String> scoresStr;
        scoresStr = new ArrayList<>();

        int j = 1;
        for (HiScore hs : top5HiScores) {

            String log =
                    "Id: " + hs.getIdScore() +
                            ", Date: " + hs.getGdate() +
                            " , Player: " + hs.getpName() +
                            " , Score: " + hs.getScore();

            // store score in string array
            scoresStr.add(j++ + " : "  +
                    hs.getpName() + "\t" +
                    hs.getScore());
            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");
        Log.i("divider", "Scores in list <>>");
        for (String ss : scoresStr) {
            Log.i("Score: ", ss);
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresStr);
        listView.setAdapter(itemsAdapter);

    }
}