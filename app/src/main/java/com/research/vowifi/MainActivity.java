package com.research.vowifi;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaReco = new MediaRecorder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hello World !");
    }

    public void btnClick(View v) {
        Toast.makeText(getBaseContext(), "Mic started", Toast.LENGTH_LONG).show();
        startMic();

    }

    private void startMic() {

        try {
            mediaReco.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaReco.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/audiorecordtest" + System.currentTimeMillis() + ".3gp";
            File file = new File(mFileName);
            file.createNewFile();
            mediaReco.setOutputFile(mFileName);
            mediaReco.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaReco.prepare();
            mediaReco.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopMic(View v) {
        mediaReco.stop();
        mediaReco.release();
        Toast.makeText(getBaseContext(), "Mic stopped", Toast.LENGTH_LONG).show();
    }
}
