package com.example.ligers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.renderscript.Type;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.sdsmdg.tastytoast.TastyToast;

import org.w3c.dom.Text;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import at.markushi.ui.CircleButton;
import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;

public class MembersFragment extends Fragment implements View.OnClickListener {

    //Fonts
    public Typeface CenturyGothic;
    public Typeface Gentona;

    //Circle Buttons
    CircleButton addMember;

    //Edit Texts
    EditText searchTxtbox;
    EditText snTxtbox;
    EditText fnTxtbox;
    EditText mnTxtbox;
    EditText lnTxtbox;
    EditText contactTxtbox;
    EditText emailTxtbox;
    EditText addressTxtbox;
    EditText motherFnTxtbox;
    EditText motherLnTxtbox;
    EditText motherContactTxtbox;
    EditText fatherFnTxtbox;
    EditText fatherLnTxtbox;
    EditText fatherContactTxtbox;

    //Spinner
    MaterialSpinner yearSpnr;
    MaterialSpinner campusSpnr;
    MaterialSpinner courseSpnr;

    //Text Views
    TextView personal_info;
    TextView family_background;

    //Fancy Buttons
    FancyButton cancelBtn;
    FancyButton doneBtn;

    //Database Reference
    DatabaseReference dr_user;
    DatabaseReference dr_user_info;


    //Tables
    tbl_user user;
    tbl_user_info user_info;

    //Random String
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String data = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    //Others
    ViewGroup vg;
    Dialog add_member;
    long user_id = 0;
    long user_info_id = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View member = inflater.inflate(R.layout.members_page, container, false);
        CenturyGothic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Century Gothic.ttf");
        Gentona = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gentona.otf");

        init(member);

        return member;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_member_btn:
                add_member_dialog();
                break;

            case R.id.done_btn:
                ErrorProvider error = new ErrorProvider();

                error.setContext(MembersFragment.this, add_member, vg);
                error.addException(R.id.mn_txtBox);
                error.addException(R.id.mother_fn_txtBox);
                error.addException(R.id.mother_ln_txtBox);
                error.addException(R.id.mother_contact_txtBox);
                error.addException(R.id.father_fn_txtBox);
                error.addException(R.id.father_ln_txtBox);
                error.addException(R.id.father_contact_txtBox);

                if(error.check()) {
                    confirmMessage(getActivity(), SweetAlertDialog.WARNING_TYPE, "DONE!", "Are you sure you want to save this form?", "YES!", "CANCEL!", add_member, "YES").show();
                }
                break;

            case R.id.cancel_btn:
                confirmMessage(getActivity(), SweetAlertDialog.WARNING_TYPE, "EXIT", "Are you sure you want to exit this form?", "YES!", "NO!", add_member, "NO").show();
                break;
        }
    }

    void init(View form) {

        //Search box
        searchTxtbox = form.findViewById(R.id.search);
        searchTxtbox.setTypeface(CenturyGothic);
        searchTxtbox.setTextSize(15);

        addMember = form.findViewById(R.id.add_member_btn);
        addMember.setOnClickListener(this);
    }

    public void add_member_dialog() {
        add_member = new Dialog(getActivity());
        add_member.requestWindowFeature(Window.FEATURE_NO_TITLE);

        add_member.setContentView(R.layout.add_member);
        add_member.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        add_member.show();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int displayWidth = metrics.widthPixels;
        int displayHeight = metrics.heightPixels;

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(add_member.getWindow().getAttributes());

        int width = (int) (displayWidth * 0.9f);
        int height = (int) (displayHeight * 0.9f);

        params.width = width;
        params.height = height;

        add_member.getWindow().setAttributes(params);

        dialogInit(add_member);
    }

    void dialogInit(Dialog dialog) {

        //EditTexts
        snTxtbox = (EditText) designField(dialog, snTxtbox, R.id.sn_txtBox, 12, CenturyGothic);
        fnTxtbox = (EditText) designField(dialog, fnTxtbox, R.id.fn_txtBox, 12, CenturyGothic);
        mnTxtbox = (EditText) designField(dialog, mnTxtbox, R.id.mn_txtBox, 12, CenturyGothic);
        lnTxtbox = (EditText) designField(dialog, lnTxtbox, R.id.ln_txtBox, 12, CenturyGothic);
        contactTxtbox = (EditText) designField(dialog, contactTxtbox, R.id.contact_txtBox, 12, CenturyGothic);
        emailTxtbox = (EditText) designField(dialog, emailTxtbox, R.id.email_txtBox, 12, CenturyGothic);
        addressTxtbox = (EditText) designField(dialog, addressTxtbox, R.id.address_txtBox, 12, CenturyGothic);
        motherFnTxtbox = (EditText) designField(dialog, motherFnTxtbox, R.id.mother_fn_txtBox, 12, CenturyGothic);
        motherLnTxtbox = (EditText) designField(dialog, motherLnTxtbox, R.id.mother_ln_txtBox, 12, CenturyGothic);
        motherContactTxtbox = (EditText) designField(dialog, motherContactTxtbox, R.id.mother_contact_txtBox, 12, CenturyGothic);
        fatherFnTxtbox = (EditText) designField(dialog, fatherFnTxtbox, R.id.father_fn_txtBox, 12, CenturyGothic);
        fatherLnTxtbox = (EditText) designField(dialog, fatherLnTxtbox, R.id.father_ln_txtBox, 12, CenturyGothic);
        fatherContactTxtbox = (EditText) designField(dialog, fatherContactTxtbox, R.id.father_contact_txtBox, 12, CenturyGothic);

        //TextViews
        designField(dialog, personal_info, R.id.personal_info_title, 15, Gentona);
        designField(dialog, family_background, R.id.family_background_title, 15, Gentona);

        //Spinners
        designSpinner(dialog, yearSpnr, R.id.year_spnr, 12, CenturyGothic, Arrays.asList("-", "1ST", "2ND", "3RD", "4TH"));
        designSpinner(dialog, campusSpnr, R.id.campus_spnr, 12, CenturyGothic, Arrays.asList("-", "San Bartolome Campus", "San Francisco Campus", "Batasan Campus"));
        designSpinner(dialog, courseSpnr, R.id.course_spnr, 12, CenturyGothic, Arrays.asList("-", "BSIT", "BSEntrep", "BSIE", "BSEcE", "BSA"));

        //Buttons
        designButton(dialog, doneBtn, R.id.done_btn, 15, "gentona.otf", "DONE", 10);
        designButton(dialog, cancelBtn, R.id.cancel_btn, 15, "gentona.otf", "CANCEL", 10);
    }

    View designField(Dialog form, View field, int name, int size, Typeface font) {
        field = form.findViewById(name);
        if(field instanceof  EditText) {
            ((EditText) field).setTypeface(font);
            ((EditText) field).setTextSize(size);
        }
        else if(field instanceof TextView) {
            ((TextView) field).setTypeface(font);
            ((TextView) field).setTextSize(size);
        }

        return field;
    }

    View designSpinner(Dialog form, View field, int name, int size, Typeface font, List<String>items) {
        field = form.findViewById(name);
        if(field instanceof MaterialSpinner) {
            ((MaterialSpinner) field).setTypeface(font);
            ((MaterialSpinner) field).setTextSize(size);
            ((MaterialSpinner) field).setItems(items);
        }

        return  field;
    }

    View designButton(Dialog form, View field, int name, int size, String font, String label, int radius) {
        field = form.findViewById(name);
        if(field instanceof  FancyButton) {
            ((FancyButton) field).setCustomTextFont(font);
            ((FancyButton) field).setTextSize(size);
            ((FancyButton) field).setRadius(radius);
            ((FancyButton) field).setText(label);
            ((FancyButton) field).setOnClickListener(this);
        }

        return field;
    }

    SweetAlertDialog confirmMessage(final Context context, int type, String title, String content, String confirm, String cancel, final Dialog dialog, final String result) {
        return new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirm)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(final SweetAlertDialog exitAlertDialog) {

                        if(result == "YES") {
                            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("SAVED!")
                                    .setContentText("Credentials Successfully Saved!")
                                    .setConfirmText("DONE!")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog successAlertDialog) {
                                            exitAlertDialog.dismissWithAnimation();
                                            successAlertDialog.dismissWithAnimation();
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                        else if(result == "NO") {
                            exitAlertDialog.dismissWithAnimation();
                            dialog.dismiss();
                        }
                    }
                })
                .setCancelButton(cancel, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog closeAlertDialog) {
                        closeAlertDialog.dismissWithAnimation();
                    }
                });
    }
}
