package pijay.dev.ligers._class;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public enum CustomFont {

    Monoround,
    LatoBold,
    LatoLight,
    CenturyGothic,
    SegoeLight;

    Context _context;
    public void setFont(View field, Object context) {
        _context = getContext(context);

        if(field instanceof MaskedEditText){
            ((MaskedEditText) field).setTypeface(Typeface.createFromAsset(_context.getAssets(), getFont()));
        }
        else if(field instanceof EditText) {
            ((EditText) field).setTypeface(Typeface.createFromAsset(_context.getAssets(), getFont()));
        }
        else if(field instanceof TextView) {
            ((TextView)field).setTypeface(Typeface.createFromAsset(_context.getAssets(), getFont()));
        }

    }

    private Context getContext(Object o){
        if(o instanceof  Activity) {
            return ((Activity) o);
        }
        else if (o instanceof  Fragment) {
            return ((Fragment) o).getContext();
        }
        return ((Context) o);
    }
    String getFont(){
        String path = "";

         switch (this) {

             case LatoLight:
                 path = "fonts/Lato-Light.ttf";
                 break;

             case LatoBold:
                 path = "fonts/Lato-Bold.ttf";
                 break;

             case CenturyGothic:
                 path = "fonts/Century-Gothic.ttf";
                 break;

             case SegoeLight:
                 path = "fonts/Segoe-Light.ttf";
                 break;

             case Monoround:
                 path = "fonts/Monoround.otf";
                 break;
         }
        return path;
    }
}
