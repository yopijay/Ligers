package pijay.dev.ligers._fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pijay.dev.ligers._class.CustomFieldDesign;
import pijay.dev.ligers._class.CustomFont;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pijay.dev.ligers.R;

public class OthersFragment extends Fragment {

    //Layout
    View others;

    //TextView
    TextView other_title_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        others = inflater.inflate(R.layout.fragment_others, container, false);

        init();

        return others;
    }

    private void init() {
        other_title_txt = (TextView) CustomFieldDesign.design(other_title_txt, R.id.other_title_txt, 20, others);
        CustomFont.Monoround.setFont(other_title_txt, OthersFragment.this);
    }
}
