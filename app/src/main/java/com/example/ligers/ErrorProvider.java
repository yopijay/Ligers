package com.example.ligers;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class ErrorProvider  {

    private Fragment fragment;
    private Dialog dialog;
    private List<EditText> views = new ArrayList<>();
    private ViewGroup vg;


    public void setContext(Fragment context, Dialog Dialog, ViewGroup viewGroup)
    {
        fragment =  context;
        dialog = Dialog;
        vg = viewGroup;
    }

    boolean check()
    {
        vg = dialog.findViewById(R.id.parent_form);
        findAllEdittexts(vg);
        int ctr = 0;
        for (EditText v: views)
        {
            //  v.setBackground(getResources().getDrawable(R.drawable.rounded_edittext));
            if((v.getText().toString().isEmpty())){
                ctr ++;
                v.setBackground(fragment.getResources().getDrawable(R.drawable.error_rounded_edittext));
            }
            // TastyToast.makeText(getActivity(), "Empty Spinner", TastyToast.LENGTH_SHORT, TastyToast.WARNING);

        }
        if(ctr > 0)
            return false;
        return true;
    }

    private void findAllEdittexts(ViewGroup viewGroup) {

        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup)
                findAllEdittexts((ViewGroup) view);
            else if (view instanceof EditText) {
                EditText edittext = (EditText) view;
                edittext.addTextChangedListener(setTextChangedEvent(edittext));
                views.add(edittext);
            }
        }
    }


    private TextWatcher setTextChangedEvent(final EditText editText)
    {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editText.getText().toString().isEmpty()) {
                    editText.setBackground(fragment.getResources().getDrawable(R.drawable.error_rounded_edittext));
                    return;
                }
                editText.setBackground(fragment.getResources().getDrawable(R.drawable.rounded_edittext));

            }

        };
    }


}
