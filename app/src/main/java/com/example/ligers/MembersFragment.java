package com.example.ligers;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
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
                  CustomMessage.confirmMessage(getActivity(), SweetAlertDialog.WARNING_TYPE, "DONE?", "Are you sure you want to save this form?", "YES!", "CANCEL!", add_member)
                          .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                              @Override
                              public void onClick(final SweetAlertDialog exitAlertDialog) {
                                  //Date format
                                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                  //Generate random string
                                  StringBuilder sb = new StringBuilder();

                                  for(int i = 0; i < 8; i++) {
                                      int randCharAt = random.nextInt(data.length());
                                      char randChar = data.charAt(randCharAt);

                                      sb.append(randChar);
                                  }

                                  dr_user = FirebaseDatabase.getInstance().getReference().child("tbl_user");
                                  dr_user_info = FirebaseDatabase.getInstance().getReference().child("tbl_user_info");

                                  dr_user.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                          user_id = (dataSnapshot.getChildrenCount());
                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                      }
                                  });

                                  dr_user_info.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                          user_info_id = (dataSnapshot.getChildrenCount());
                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                      }
                                  });

                                  user = new tbl_user();
                                  user.setId((int) user_id + 1);
                                  user.setStudent_no(snTxtbox.getText().toString().trim());
                                  user.setPassword(sb.toString());
                                  user.setStatus("student");
                                  user.setUser_type("member");
                                  user.setCreated_by(0);
                                  user.setModified_by(0);
                                  user.setDate_created(sdf.format(new Date()));
                                  user.setDate_modified("0000-00-00");
                                  dr_user.child(String.valueOf(user_id + 1)).setValue(user);

                                  user_info = new tbl_user_info();
                                  user_info.setId((int) user_info_id + 1);
                                  user_info.setUser_id((int) user_id + 1);
                                  user_info.setFirstname(fnTxtbox.getText().toString().trim());
                                  user_info.setMiddlename(mnTxtbox.getText().toString().trim());
                                  user_info.setLastname(lnTxtbox.getText().toString().trim());
                                  user_info.setYear_level(yearSpnr.getText().toString());
                                  user_info.setContact_no(contactTxtbox.getText().toString().trim());
                                  user_info.setEmail(emailTxtbox.getText().toString().trim());
                                  user_info.setAddress(addressTxtbox.getText().toString().trim());
                                  user_info.setCampus(campusSpnr.getText().toString());
                                  user_info.setCourse(courseSpnr.getText().toString());
                                  user_info.setMother_fname(motherFnTxtbox.getText().toString().trim());
                                  user_info.setMother_lname(motherLnTxtbox.getText().toString().trim());
                                  user_info.setMother_contact(motherContactTxtbox.getText().toString().trim());
                                  user_info.setFather_fname(fatherFnTxtbox.getText().toString().trim());
                                  user_info.setFather_lname(fatherLnTxtbox.getText().toString().trim());
                                  user_info.setFather_contact(fatherContactTxtbox.getText().toString().trim());
                                  user_info.setCreated_by(0);
                                  user_info.setModified_by(0);
                                  user_info.setDate_created(sdf.format(new Date()));
                                  user_info.setDate_modified("0000-00-00");
                                  dr_user_info.child(String.valueOf(user_info_id + 1)).setValue(user_info);

                                  new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                          .setTitleText("SAVED!")
                                          .setContentText("Credentials successfully saved!")
                                          .setConfirmText("DONE!")
                                          .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                              @Override
                                              public void onClick(SweetAlertDialog successAlertDialog) {
                                                  successAlertDialog.dismissWithAnimation();
                                                  exitAlertDialog.dismissWithAnimation();
                                                  add_member.dismiss();
                                              }
                                          })
                                          .show();
                              }
                          })
                          .show();
                }
                break;

            case R.id.cancel_btn:
                CustomMessage.confirmMessage(getActivity(), SweetAlertDialog.WARNING_TYPE, "EXIT?", "Are you sure you want to exit this form?", "YES!", "NO!", add_member)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog closeAlertDialog) {
                                closeAlertDialog.dismissWithAnimation();
                                add_member.dismiss();
                            }
                        })
                        .show();
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
        yearSpnr = (MaterialSpinner) designSpinner(dialog, yearSpnr, R.id.year_spnr, 12, CenturyGothic, Arrays.asList("-", "1ST", "2ND", "3RD", "4TH"));
        campusSpnr = (MaterialSpinner) designSpinner(dialog, campusSpnr, R.id.campus_spnr, 12, CenturyGothic, Arrays.asList("-", "San Bartolome Campus", "San Francisco Campus", "Batasan Campus"));
        courseSpnr = (MaterialSpinner) designSpinner(dialog, courseSpnr, R.id.course_spnr, 12, CenturyGothic, Arrays.asList("-", "BSIT", "BSEntrep", "BSIE", "BSEcE", "BSA"));

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
}
