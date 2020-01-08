package pijay.dev.ligers._modules;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import mehdi.sakout.fancybuttons.FancyButton;
import pijay.dev.ligers.R;
import pijay.dev.ligers._class.CustomFieldDesign;
import pijay.dev.ligers._class.CustomFont;
import pijay.dev.ligers._class.Dialogs;
import pijay.dev.ligers._class.ErrorProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //ErrorProvider
    ErrorProvider error;

    //TextViews
    TextView sn_txt;
    TextView password_txt;
    TextView forgot_link;
    TextView pj_txt;
    TextView otp_txt;

    //EditTexts
    MaskedEditText sn_txtbox;
    EditText password_txtbox;

    //Buttons
    FancyButton login_btn;

    //ViewGroup
    ViewGroup login_form;

    //Dialogs
    Dialogs dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.login_module);

        init();
    }

    public void  init() {;

        //ViewGroup
        login_form = findViewById(R.id.login_form);

        //TextViews
        sn_txt = (TextView) CustomFieldDesign.design(sn_txt, R.id.sn_txt, 14, LoginActivity.this);
        CustomFont.LatoBold.setFont(sn_txt, LoginActivity.this);
//        CustomFieldDesign.design(password_txt, R.id.password_txt, 14, LatoBold, LoginActivity.this);
//        forgot_link = (TextView) CustomFieldDesign.design(forgot_link, R.id.forgot_link, 14, LatoBold, LoginActivity.this);
        pj_txt = (TextView) CustomFieldDesign.design(pj_txt, R.id.pj_txt, 13, LoginActivity.this);
        CustomFont.LatoLight.setFont(pj_txt, LoginActivity.this);
        otp_txt = (TextView) CustomFieldDesign.design(otp_txt, R.id.otp_txt, 13, LoginActivity.this);
        CustomFont.LatoBold.setFont(otp_txt, LoginActivity.this);

        //EditTexts
        sn_txtbox = (MaskedEditText) CustomFieldDesign.design(sn_txtbox, R.id.sn_txtbox, 22, LoginActivity.this);
        CustomFont.LatoLight.setFont(sn_txtbox, LoginActivity.this);

//        password_txtbox = (EditText) CustomFieldDesign.design(password_txtbox, R.id.password_txtbox, 16, LatoLight, LoginActivity.this);

        //Buttons
        login_btn = (FancyButton) CustomFieldDesign.design(login_btn, R.id.login_btn, 20, LoginActivity.this);
        login_btn.setOnClickListener(LoginActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.login_btn:
                error = new ErrorProvider();
                error.setContext(LoginActivity.this, login_form, null);

                if(error.check()) {
                    dialog = new Dialogs(LoginActivity.this);
                    dialog.otp();
                }
                break;
        }
    }
}
