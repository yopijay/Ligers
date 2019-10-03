package com.example.ligers;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.View;
import android.view.WindowManager;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.EditText;
import mehdi.sakout.fancybuttons.FancyButton;
import android.widget.Toast;
import android.view.View.OnClickListener;
import com.sdsmdg.tastytoast.TastyToast;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.content.Intent;
import android.view.Gravity;

import androidx.annotation.Nullable;

public class LoginActivity extends Activity implements OnClickListener {

    ViewGroup inputForms;
    ViewGroup buttonForms;

    //TextView
    TextView titleLogin;
    TextView snLbl;
    TextView passLbl;

    //EditText
    EditText sn;
    EditText pass;

    //Button
    FancyButton nextBtn;
    FancyButton loginBtn;
    FancyButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.login_form);

        //Fonts
        //Typeface Funbold = Typeface.createFromAsset(getAssets(), "fonts/Funbold.ttf");
        Typeface CenturyGothic = Typeface.createFromAsset(getAssets(), "fonts/Century Gothic.ttf");
        Typeface Gentona = Typeface.createFromAsset(getAssets(), "fonts/gentona.otf");
        //Typeface WickedMouse = Typeface.createFromAsset(getAssets(), "fonts/Wicked Mouse Demo.otf");

        inputForms = (ViewGroup) findViewById(R.id.loginContainer);
        buttonForms = (ViewGroup) findViewById(R.id.buttonContainer);


        //TextViews
        titleLogin = (TextView) findViewById(R.id.loginTitle);
        titleLogin.setTypeface(Gentona);

        snLbl = (TextView) findViewById(R.id.snLbl);
        snLbl.setTypeface(CenturyGothic);

        passLbl = (TextView) findViewById(R.id.passLbl);
        passLbl.setTypeface(CenturyGothic);

        //EditTexts
        sn = (EditText) findViewById(R.id.student_no);
        sn.setTypeface(CenturyGothic);

        pass = (EditText) findViewById(R.id.password);
        pass.setTypeface(CenturyGothic);

        //Buttons
        nextBtn = (FancyButton) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);
        nextBtn.setCustomTextFont("gentona.otf");
        nextBtn.setText("");
        nextBtn.setIconResource("\uf105");
        nextBtn.setFontIconSize(20);
        nextBtn.setRadius(10);

        loginBtn = (FancyButton) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        loginBtn.setText("LOGIN");
        loginBtn.setCustomTextFont("gentona.otf");
        loginBtn.setTextSize(20);
        loginBtn.setRadius(10);

        backBtn = (FancyButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
        backBtn.setCustomTextFont("gentona.otf");
        backBtn.setText("");
        backBtn.setIconResource("\uf104");
        backBtn.setFontIconSize(20);
        backBtn.setRadius(10);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.nextBtn:
                if(sn.getText().toString().isEmpty()) {
                    TastyToast.makeText(LoginActivity.this, "Student number is empty!", Toast.LENGTH_SHORT, TastyToast.ERROR).setGravity(Gravity.TOP, 0, 0);
                }
                else {
                    TransitionManager.beginDelayedTransition(inputForms);
                    TransitionManager.beginDelayedTransition(buttonForms);
                    snLbl.setVisibility(View.INVISIBLE);
                    sn.setVisibility(View.INVISIBLE);
                    nextBtn.setVisibility(View.INVISIBLE);

                    passLbl.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);
                    backBtn.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.loginBtn:
                if(pass.getText().toString().isEmpty()) {
                    TastyToast.makeText(this, "Password is empty!", Toast.LENGTH_SHORT, TastyToast.ERROR).setGravity(Gravity.TOP, 0, 0);
                }
                else {
                    //TastyToast.makeText(this, "YAHHHOOOOOOOO!", Toast.LENGTH_LONG, TastyToast.SUCCESS).setGravity(Gravity.TOP, 0, 0);
                    Intent admin_profile = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(admin_profile);
                }
                break;

            case R.id.backBtn:
                TransitionManager.beginDelayedTransition(inputForms);
                TransitionManager.beginDelayedTransition(buttonForms);
                sn.setText("");
                pass.setText("");

                snLbl.setVisibility(View.VISIBLE);
                sn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);

                passLbl.setVisibility(View.INVISIBLE);
                pass.setVisibility(View.INVISIBLE);
                loginBtn.setVisibility(View.INVISIBLE);
                backBtn.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
