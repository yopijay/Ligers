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
import at.markushi.ui.CircleButton;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.app.Dialog;

public class AdminMainActivity extends Activity implements View.OnClickListener {
	
	//TextVuews
	TextView fullname, type, alertTitle, alertMessage;
	
	//Buttons
	FancyButton profile, okBtn;
	CircleButton message, notif, logs;

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
		profile.setOnClickListener(this);
		
		message = (CircleButton) findViewById(R.id.messageBtn);
		message.setOnClickListener(this);
		
		notif = (CircleButton) findViewById(R.id.notifBtn);
		notif.setOnClickListener(this);
		
		logs = (CircleButton) findViewById(R.id.logsBtn);
		logs.setOnClickListener(this);
	}
	
	public void alertPopup() {

		//Fonts
		Typeface CenturyGothic = Typeface.createFromAsset(getAssets(), "fonts/Century Gothic.ttf");
		Typeface Gentona = Typeface.createFromAsset(getAssets(), "fonts/gentona.otf");
		
		
		final Dialog alertSuccess = new Dialog(AdminMainActivity.this);
		alertSuccess.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		alertSuccess.setContentView(R.layout.alert_welcome);
		alertSuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		alertTitle = (TextView) alertSuccess.findViewById(R.id.alertTitle);
		alertTitle.setTypeface(CenturyGothic);
		alertTitle.setTextSize(25);
		
		alertMessage = (TextView) alertSuccess.findViewById(R.id.alertInfo);
		alertMessage.setTypeface(CenturyGothic);
		alertMessage.setTextSize(15);
		
		okBtn = (FancyButton) alertSuccess.findViewById(R.id.okAlertBtn);
		okBtn.setRadius(10);
		okBtn.setText("OK");
		okBtn.setCustomTextFont("Century Gothic.ttf");
		okBtn.setTextSize(17);
		okBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				alertSuccess.dismiss();
			}	
		});
		
		alertSuccess.show();
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.profileBtn:
				TastyToast.makeText(this, "Hi! Im Prpfile Button!", Toast.LENGTH_SHORT, TastyToast.INFO);
			break;
			
			case R.id.messageBtn:
				TastyToast.makeText(this, "Hi! Im Message Button", Toast.LENGTH_SHORT, TastyToast.INFO);
			break;
			
			case R.id.notifBtn:
				TastyToast.makeText(this, "Hi! Im Notification Button", Toast.LENGTH_SHORT, TastyToast.INFO);
			break;
			
			case R.id.logsBtn:
				TastyToast.makeText(this, "Hi! Im Logs Button", Toast.LENGTH_SHORT, TastyToast.INFO);
			break;
		}
	}
}
