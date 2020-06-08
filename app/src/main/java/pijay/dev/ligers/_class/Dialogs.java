package pijay.dev.ligers._class;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import mehdi.sakout.fancybuttons.FancyButton;
import pijay.dev.ligers.R;
import pijay.dev.ligers._modules.MainActivity;

public class Dialogs implements View.OnClickListener {

    private Object _object;
    private Dialog otp;

    //TextViews
    TextView otp_detail_txt;
    TextView otp_resend_link;

    //EditTexts
    MaskedEditText otp_code_txtbox;

    //Buttons
    FancyButton otp_btn;

    //ViewGroup
    ViewGroup otp_form;

    //Others
    ErrorProvider error;
    private Context getContext() {

        if(_object instanceof Activity) {
            return ((Activity) _object);
        }
        else if(_object instanceof Fragment) {
            return ((Fragment) _object).getContext();

        }


        return ((Context) _object);
    }

    public Dialogs(Object object) {
        this._object = object;
    }

    public void otp() {
            otp = new Dialog(getContext());
            otp.requestWindowFeature(Window.FEATURE_NO_TITLE);

            otp.setContentView(R.layout.dialog_otp);
            otp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            otp.setCanceledOnTouchOutside(false);
            otp.show();


            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);

            int displayWidth = metrics.widthPixels;
            int displayHeight = metrics.heightPixels;

            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.copyFrom(otp.getWindow().getAttributes());

            int width = (int) (displayWidth * 0.9f);
            int height = (int) (displayHeight * 0.9f);

            params.width = width;
            params.height = height;

            otp.getWindow().setAttributes(params);

            otp_detail_txt = (TextView) CustomFieldDesign.design(otp_detail_txt, R.id.otp_detail_txt, 15, otp);
            CustomFont.LatoBold.setFont(otp_detail_txt, _object);
            otp_code_txtbox = (MaskedEditText) CustomFieldDesign.design(otp_code_txtbox, R.id.otp_code_txtbox, 25, otp);
            CustomFont.LatoLight.setFont(otp_code_txtbox, _object);
            otp_resend_link = (TextView) CustomFieldDesign.design(otp_resend_link, R.id.otp_resend_txt, 15, otp);
            CustomFont.LatoBold.setFont(otp_resend_link, _object);
            otp_btn = (FancyButton) CustomFieldDesign.design(otp_btn, R.id.otp_btn, 20, otp);
            otp_form = otp.findViewById(R.id.otp_form);
            otp_btn.setOnClickListener(Dialogs.this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.otp_btn:
                error = new ErrorProvider();
                error.setContext(_object, otp_form, otp);

                if(error.check()) {
                    Intent dashboard = new Intent((Activity) _object, MainActivity.class);
                    ((Activity) _object).startActivity(dashboard);

                    otp.dismiss();
                    ((Activity) _object).finish();
                }
                break;
        }
    }
}
