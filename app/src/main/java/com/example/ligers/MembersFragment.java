package com.example.ligers;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.sdsmdg.tastytoast.TastyToast;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.markushi.ui.CircleButton;
import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;

public class MembersFragment extends Fragment {

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

    ViewGroup vg;

    Dialog add_member;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View member = inflater.inflate(R.layout.members_page, container, false);

        //Fonts
        Typeface CenturyGothic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Century Gothic.ttf");

        searchTxtbox = member.findViewById(R.id.search);
        searchTxtbox.setTypeface(CenturyGothic);
        searchTxtbox.setTextSize(15);

        addMember = member.findViewById(R.id.add_member_btn);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View member) {
                add_member_popup();
            }
        });

        return member;
    }

    public void add_member_popup() {
        //Fonts
        Typeface CenturyGothic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Century Gothic.ttf");
        Typeface Gentona = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gentona.otf");

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

        //TextViews
        designText(personal_info, add_member, Gentona, R.id.personal_info_title);
        designText(family_background, add_member, Gentona, R.id.family_background_title);

        //EditTexts
        designField(snTxtbox, add_member, CenturyGothic, R.id.sn_txtBox, 13);
        designField(fnTxtbox, add_member, CenturyGothic, R.id.fn_txtBox, 13);
        designField(mnTxtbox, add_member, CenturyGothic, R.id.mn_txtBox, 13);
        designField(lnTxtbox, add_member, CenturyGothic, R.id.ln_txtBox, 13);
        designField(contactTxtbox, add_member, CenturyGothic, R.id.contact_txtBox, 13);
        designField(emailTxtbox, add_member, CenturyGothic, R.id.email_txtBox, 13);
        designField(addressTxtbox, add_member, CenturyGothic, R.id.address_txtBox, 13);
        designField(motherFnTxtbox, add_member, CenturyGothic, R.id.mother_fn_txtBox, 13);
        designField(motherLnTxtbox, add_member, CenturyGothic, R.id.mother_ln_txtBox, 13);
        designField(motherContactTxtbox, add_member, CenturyGothic, R.id.mother_contact_txtBox, 13);
        designField(fatherFnTxtbox, add_member, CenturyGothic, R.id.father_fn_txtBox, 13);
        designField(fatherLnTxtbox, add_member, CenturyGothic, R.id.father_ln_txtBox, 13);
        designField(fatherContactTxtbox, add_member, CenturyGothic, R.id.father_contact_txtBox, 13);

        //Spinners
        yearSpnr = add_member.findViewById(R.id.year_spnr);
        yearSpnr.setTypeface(CenturyGothic);
        yearSpnr.setItems("-", "1ST", "2ND", "3RD", "4TH");

        campusSpnr = add_member.findViewById(R.id.campus_spnr);
        campusSpnr.setTypeface(CenturyGothic);
        campusSpnr.setItems("-", "San Bartolome Campus", "San Francisco Campus", "Batasan Campus");

        courseSpnr = add_member.findViewById(R.id.course_spnr);
        courseSpnr.setTypeface(CenturyGothic);
        courseSpnr.setItems("-", "BSIT", "BSEntrep", "BSIE", "BSEcE", "BSA");

        //FancyButtons
        doneBtn = add_member.findViewById(R.id.done_btn);
        doneBtn.setCustomTextFont("gentona.otf");
        doneBtn.setRadius(10);
        doneBtn.setText("DONE");
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View done) {
                ErrorProvider error = new ErrorProvider();

                error.setContext(MembersFragment.this, add_member, vg);
                if(error.check()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure you want to save this/these info?")
                            .setConfirmText("Yes")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(final SweetAlertDialog warningAlertDialog) {

                                    //Date format
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                                    //Generate random string
                                    StringBuilder sb = new StringBuilder();

                                    for (int i = 0; i < 8; i++) {
                                        int rndomCharAt = random.nextInt(data.length());
                                        char rndomChar = data.charAt(rndomCharAt);

                                        sb.append(rndomChar);
                                    }

                                    String curDate = df.format(new Date());

                                    snTxtbox = add_member.findViewById(R.id.sn_txtBox);
                                    yearSpnr = add_member.findViewById(R.id.year_spnr);
                                    fnTxtbox = add_member.findViewById(R.id.fn_txtBox);
                                    mnTxtbox = add_member.findViewById(R.id.mn_txtBox);
                                    lnTxtbox = add_member.findViewById(R.id.ln_txtBox);
                                    contactTxtbox = add_member.findViewById(R.id.contact_txtBox);
                                    emailTxtbox = add_member.findViewById(R.id.email_txtBox);
                                    campusSpnr = add_member.findViewById(R.id.campus_spnr);
                                    courseSpnr = add_member.findViewById(R.id.course_spnr);
                                    motherFnTxtbox = add_member.findViewById(R.id.mother_fn_txtBox);
                                    motherLnTxtbox = add_member.findViewById(R.id.mother_ln_txtBox);
                                    motherContactTxtbox = add_member.findViewById(R.id.mother_contact_txtBox);
                                    fatherFnTxtbox = add_member.findViewById(R.id.father_fn_txtBox);
                                    fatherLnTxtbox = add_member.findViewById(R.id.father_ln_txtBox);
                                    fatherContactTxtbox = add_member.findViewById(R.id.father_contact_txtBox);

                                    dr_user = FirebaseDatabase.getInstance().getReference().child("tbl_user");
                                    dr_user_info = FirebaseDatabase.getInstance().getReference().child("tbl_user_info");

                                    user = new tbl_user();

                                    user.setStudent_no(snTxtbox.getText().toString().trim());
                                    user.setPassword(sb.toString());
                                    user.setUser_type("member");
                                    user.setStatus("student");
                                    user.setCreated_by("14-0846");
                                    user.setModified_by("00-0000");
                                    user.setDate_created(curDate);
                                    user.setDate_modified("0000-00-00");
                                    dr_user.child(snTxtbox.getText().toString()).setValue(user);

                                    user_info = new tbl_user_info();

                                    user_info.setStudent_no(snTxtbox.getText().toString().trim());
                                    user_info.setYear_level(yearSpnr.getText().toString().trim());
                                    user_info.setFirstname(fnTxtbox.getText().toString().trim());
                                    user_info.setMiddlename(mnTxtbox.getText().toString().trim());
                                    user_info.setLastname(lnTxtbox.getText().toString().trim());
                                    user_info.setContact_no(contactTxtbox.getText().toString().trim());
                                    user_info.setEmail(emailTxtbox.getText().toString().trim());
                                    user_info.setCampus(campusSpnr.getText().toString().trim());
                                    user_info.setCourse(courseSpnr.getText().toString().trim());
                                    user_info.setMother_fname(motherFnTxtbox.getText().toString().trim());
                                    user_info.setMother_lname(motherLnTxtbox.getText().toString().trim());
                                    user_info.setMother_contact(motherContactTxtbox.getText().toString().trim());
                                    user_info.setFather_fname(fatherFnTxtbox.getText().toString().trim());
                                    user_info.setFather_lname(fatherLnTxtbox.getText().toString().trim());
                                    user_info.setFather_contact(fatherContactTxtbox.getText().toString().trim());
                                    user_info.setCreated_by("14-0846");
                                    user_info.setModified_by("00-0000");
                                    user_info.setDate_created(curDate);
                                    user_info.setDate_modified("0000-00-00");
                                    dr_user_info.child(snTxtbox.getText().toString()).setValue(user_info);

                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("DONE!")
                                            .setContentText("Successfully Saved!")
                                            .setConfirmText("OK!")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog successAlertDialog) {
                                                    successAlertDialog.dismissWithAnimation();
                                                    warningAlertDialog.dismissWithAnimation();
                                                    add_member.dismiss();
                                                }
                                            })
                                            .show();
                                }
                            })
                            .setCancelButton("Wait!", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
            }
        });

        cancelBtn = add_member.findViewById(R.id.cancel_btn);
        cancelBtn.setCustomTextFont("gentona.otf");
        cancelBtn.setRadius(10);
        cancelBtn.setText("CANCEL");
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View cancel) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("CANCEL?")
                        .setContentText("Are you sure you want exit this form?")
                        .setConfirmText("Yes")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                add_member.dismiss();
                            }
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });
    }

    void designText(TextView field, Dialog form, Typeface font, int name) {
        field = form.findViewById(name);
        field.setTypeface(font);
    }

    void designField(EditText field, Dialog form, Typeface font, int name, int size) {
        field = form.findViewById(name);
        field.setTypeface(font);
        field.setTextSize(size);
    }

    void designSpinner(MaterialSpinner field, Dialog form, Typeface font, int name, List<String>items) {
        field = form.findViewById(name);
        field.setTypeface(font);
        field.setItems(items);
    }
}
