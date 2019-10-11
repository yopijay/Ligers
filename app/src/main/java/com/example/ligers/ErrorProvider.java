package com.example.ligers;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class ErrorProvider  {

    private Fragment fragment;
    private Dialog dialog;
    private List<EditText> views = new ArrayList<>();
    private List<Integer> viewExceptions = new ArrayList<>();
    private ViewGroup vg;

    public void setContext(Fragment context, Dialog Dialog, ViewGroup viewGroup)
    {
        fragment =  context;
        dialog = Dialog;
        vg = viewGroup;
    }

    public void addException(int viewGroupId)
    {
        viewExceptions.add(viewGroupId);
    }

    boolean check()
    {
        vg = dialog.findViewById(R.id.parent_form);
        findAllEdittexts(vg);
        int ctr = 0;
        for (EditText v: views)
            if((v.getText().toString().isEmpty())){
                ctr ++;
                v.setBackground(fragment.getResources().getDrawable(R.drawable.error_rounded_edittext));
            }

        if(ctr > 0)
            return false;
        return true;
    }

    private void findAllEdittexts(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();

        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                findAllEdittexts((ViewGroup) view);
            }
            else if (view instanceof EditText) {
                EditText edittext = (EditText) view;
                edittext.addTextChangedListener(setTextChangedEvent(edittext));
                views.add(edittext);
                for(int id : viewExceptions)
                    if(view.getId() == id || viewGroup.getId() == id)
                        views.remove(edittext);
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
                int id = R.drawable.rounded_edittext;
                if(editText.getText().toString().isEmpty())
                    id = R.drawable.error_rounded_edittext;
                editText.setBackground(fragment.getResources().getDrawable(id));
            }
        };
    }

}