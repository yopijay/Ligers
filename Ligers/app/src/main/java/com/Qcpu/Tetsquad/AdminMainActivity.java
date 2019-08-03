package com.Qcpu.Tetsquad;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.graphics.Typeface;
import mehdi.sakout.fancybuttons.FancyButton;
import android.view.View;
import com.sdsmdg.tastytoast.TastyToast;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;

public class AdminMainActivity extends Activity {
	
	//TextVuews
	TextView fullname, type;
	
	//Buttons
	FancyButton profile, message, notif, logs;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Fullscreen
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.admin_main);
		
		alertPopup();
		
		//Fonts
		Typeface CenturyGothic = Typeface.createFromAsset(getAssets(), "fonts/Century Gothic.ttf");
		Typeface Gentona = Typeface.createFromAsset(getAssets(), "fonts/gentona.otf");
		
		//TextViews
		fullname = (TextView) findViewById(R.id.user_fullname);
		fullname.setTypeface(Gentona);
		
		type = (TextView) findViewById(R.id.user_type);
		type.setTypeface(CenturyGothic);
		
		//Buttons
		profile = (FancyButton) findViewById(R.id.profileBtn);
		profile.setText("Edit Profile");
		profile.setTextSize(15);
		profile.setRadius(20);
		profile.setCustomTextFont("Century Gothic.ttf");
		
		/*message = (FancyButton) findViewById(R.id.messageBtn);
		message.setIconResource("\uf03a");
		message.setFontIconSize(30);
		message.setText("");
		message.setRadius(100);
		
		notif = (FancyButton) findViewById(R.id.notificationBtn);
		notif.setIconResource("\uf03a");
		notif.setFontIconSize(30);
		notif.setText("");
		notif.setRadius(100);
		
		logs = (FancyButton) findViewById(R.id.logsBtn);
		logs.setIconResource("\uf03a");
		logs.setFontIconSize(30);
		logs.setText("");
		logs.setRadius(100);*/
	}
	
	public void alertPopup() {
		AlertDialog.Builder alert = new AlertDialog.Builder(AdminMainActivity.this);
		View alertView = getLayoutInflater().inflate(R.layout.alert_welcome, null);
		
		alert.setView(alertView);
		alert.create().show();
	}
}
