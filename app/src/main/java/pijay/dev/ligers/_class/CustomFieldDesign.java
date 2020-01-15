package pijay.dev.ligers._class;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import mehdi.sakout.fancybuttons.FancyButton;

public class CustomFieldDesign {

    public static View design(View field, int name, int size, Object form)  {

        if(form instanceof View) {
            field = ((View) form).findViewById(name);
        }
        else if(form instanceof  Dialog) {
            field = ((Dialog) form).findViewById(name);
        }
        else {
            field = ((Activity) form).findViewById(name);
        }

        if(field instanceof MaskedEditText) {
            ((MaskedEditText) field).setTextSize(size);
        }
        else if(field instanceof EditText) {
            ((EditText) field).setTextSize(size);
        }
        else if(field instanceof TextView) {
            ((TextView) field).setTextSize(size);
        }
        else if(field instanceof FancyButton) {
            ((FancyButton) field).setCustomTextFont("Monoround.otf");
            ((FancyButton) field).setTextSize(size);
            ((FancyButton) field).setTextAllCaps(true);
        }
        return field;
    }
}
