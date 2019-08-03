package com.Qcpu.Tetsquad;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.content.Intent;
import android.os.Handler;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SplashActivity extends Activity {
	
	ProgressBar pBar;
	int progress = 0;
	Handler handler = new Handler();
	
	AdapterViewFlipper avf;
	int IMG[] = {
		R.drawable.ligers_logo,
		R.drawable.ligers_new,
		R.drawable.ligers_claw
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
		//Fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.splash_form);
		
		pBar = (ProgressBar) findViewById(R.id.progressBar);
		
		//Customized progress bar
		pBar.getProgressDrawable().setColorFilter(Color.parseColor("#0091EA"),PorterDuff.Mode.SRC_IN);
		
		//Progress bar function
		new Thread(new Runnable() {
			public void run() {
				while(progress < 1000) {
					progress += 1;
					
					handler.post(new Runnable() {
						public void run () {
							pBar.setProgress(progress);
							
							int load = 0;
							load = progress;
							
							if (load == 100) {
								Intent login= new Intent(SplashActivity.this, LoginActivity.class);
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
		
		//Image Slider
		avf = (AdapterViewFlipper) findViewById(R.id.avfSlider);
		
		CustomAdapter ca = new CustomAdapter(getApplicationContext(), IMG);
		
		avf.setAdapter(ca);
		avf.setFlipInterval(1000);
		avf.setAutoStart(true);
    }
}

class CustomAdapter extends BaseAdapter {
	
	Context ctx;
	int images[];
	LayoutInflater inflate;
	
	public CustomAdapter(Context applicationContext, int images[]) {
		this.ctx = applicationContext;
		this.images = images;
		inflate = (LayoutInflater.from(applicationContext));
	}

	@Override
	public int getCount()
	{
		return images.length;
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		view = inflate.inflate(R.layout.splash_slider, null);
		ImageView imgv = (ImageView) view.findViewById(R.id.image);
		imgv.setImageResource(images[position]);
		
		return view;
	}
}
