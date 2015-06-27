package com.example.jessielhacke.finalprojectandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Jessiel Hacke on 6/14/2015.
 */
public class SplashScreen extends Activity {

    private int progressStatus = 0;
    private Handler handler = new Handler();
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Splash screen view
        setContentView(R.layout.splash);

        progressBar = (ProgressBar) findViewById(R.id.progressBarSplash);
        textView = (TextView) findViewById(R.id.textViewProgress);

        final SplashScreen sSplashScreen = this;

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                finish();
                Intent intent = new Intent();
                intent.setClass(sSplashScreen, LoginMain.class);
                startActivity(intent);
            }
        }).start();

    }
}
