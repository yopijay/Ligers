package pijay.dev.ligers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterViewFlipper;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import pijay.dev.ligers._class.SliderAdapter;
import pijay.dev.ligers._modules.LoginActivity;

public class SplashActivity extends Activity {

    ProgressBar pBar;
    int progress = 0;
    Handler handler = new Handler();

    AdapterViewFlipper avf;
    int IMAGE[] = {
            R.drawable.img_ligers_claw,
            R.drawable.img_ligers_new,
            R.drawable.img_ligers_latest
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_form);

        pBar = findViewById(R.id.progressBar);

        //Customize progressbar
        pBar.getProgressDrawable().setColorFilter(Color.parseColor("#00B0FF"), PorterDuff.Mode.SRC_IN);

        //Progressbar Function
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress < 1000) {
                    progress += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pBar.setProgress(progress);

                            int load = progress;

                            if(load == 100) {
                                Intent login = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(login);
                                finish();
                            }
                        }
                    });

                    try {
                        Thread.sleep(30);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        avf = findViewById(R.id.avfSlider);

        SliderAdapter sa = new SliderAdapter(getApplicationContext(), IMAGE);

        avf.setAdapter(sa);
        avf.setFlipInterval(1000);
        avf.setAutoStart(true);
    }
}
