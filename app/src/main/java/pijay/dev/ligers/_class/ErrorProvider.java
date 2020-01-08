package pijay.dev.ligers._class;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import pijay.dev.ligers.R;

public class ErrorProvider {

    private Object _context;
    private Dialog _dialog;
    private ViewGroup _viewgroup;
    private List<View> views =new ArrayList<>();

    public void setContext(Object context, ViewGroup viewgroup, Dialog dialog) {
        this._context = context;
        this._viewgroup = viewgroup;
        this._dialog = dialog;
    }

    public boolean check() {
        findAllEditText(_viewgroup);

        int counter = 0;

        for(View v: views) {

            if(_context instanceof Activity) {
                if(((MaskedEditText) v).getRawText().equals("000000") || ((EditText) v).getText().toString().isEmpty()) {
                    counter++;
                    v.setBackground(((Activity) _context).getDrawable(R.drawable.error_rounded_edittext));
                }
                else if(((MaskedEditText) v).getRawText().length() <= 5) {
                    counter++;
                    v.setBackground(((Activity) _context).getDrawable(R.drawable.error_rounded_edittext));
                }
            }
        }

        if(counter > 0) {
            return false;
        }
        return true;
    }

    private void findAllEditText(ViewGroup viewgroup) {
        int count = viewgroup.getChildCount();

        for(int i = 0; i < count; i++) {
            View view = viewgroup.getChildAt(i);

            if(view instanceof ViewGroup) {
                findAllEditText((ViewGroup) view);
            }
            else if(view instanceof MaskedEditText) {
                MaskedEditText maskedittext = (MaskedEditText) view;

                maskedittext.addTextChangedListener(setTextChangeEvent(maskedittext));
                views.add(maskedittext);
            }
            else if(view instanceof EditText) {
                EditText edittext = (EditText) view;

                edittext.addTextChangedListener(setTextChangeEvent(edittext));
                views.add(edittext);
            }
        }
    }

    private TextWatcher setTextChangeEvent(final View edittext) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int result = R.drawable.rounded_edittext;

                if(_context instanceof Activity) {
                    if(edittext instanceof MaskedEditText) {
                        if(((MaskedEditText) edittext).getRawText().equals("000000")) {
                            result = R.drawable.error_rounded_edittext;
                        }
                    }
                    else if(edittext instanceof EditText) {
                        if(((EditText) edittext).getText().toString().isEmpty()) {
                            result = R.drawable.error_rounded_edittext;
                        }
                    }
                    edittext.setBackground(((Activity) _context).getDrawable(result));
                }
            }
        };
    }
}
