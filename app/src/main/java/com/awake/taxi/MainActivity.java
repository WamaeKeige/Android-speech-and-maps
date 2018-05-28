package com.awake.taxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.List;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SPEAK_REQUEST = 10;
    TextView txtsource;
    Button btntalk, btnlaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtsource = (TextView) findViewById(R.id.textView);
        btntalk = (Button) findViewById(R.id.button);
        btnlaunch = (Button) findViewById(R.id.button2);
        PackageManager packageManager = this.getPackageManager();
        List<ResolveInfo> listOfInformation = packageManager.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

        if (listOfInformation.size() > 0) {

            Toast.makeText(MainActivity.this, "Your device supports speech recognition", Toast.LENGTH_SHORT).show();
            listenToUserVoice();
        } else {

            Toast.makeText(MainActivity.this, "Your device DOES NOT supports speech recognition", Toast.LENGTH_SHORT).show();
        }


    }

    private void listenToUserVoice() {
        Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Talk To Me");
        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //maximum results from users speech
        voiceIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        startActivityForResult(voiceIntent, SPEAK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(resultCode, requestCode, data);
        if (requestCode == SPEAK_REQUEST && resultCode == RESULT_OK) {
            ArrayList<String> voiceWords = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            float[] confidlevels = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
            int index = 0;
            for (String userWord : voiceWords) {
                if (confidlevels != null && index < confidlevels.length) {
                    txtsource.setText(userWord + "-" + confidlevels[index]);
                }
            }
        }

        btntalk.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                listenToUserVoice();
            }
        });

        btnlaunch.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
