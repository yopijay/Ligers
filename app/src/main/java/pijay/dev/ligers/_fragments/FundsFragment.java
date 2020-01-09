package pijay.dev.ligers._fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pijay.dev.ligers.R;
import pijay.dev.ligers._class.CustomFieldDesign;
import pijay.dev.ligers._class.CustomFont;

public class FundsFragment extends Fragment {

    //Layout
    View funds;

    //TextViews
    TextView funds_title_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        funds =  inflater.inflate(R.layout.fragment_funds, container, false);

        init();

        return funds;
    }

    public void init() {
        funds_title_txt = (TextView) CustomFieldDesign.design(funds_title_txt, R.id.funds_title_txt, 20, funds);
        CustomFont.Monoround.setFont(funds_title_txt, FundsFragment.this);
    }
}
