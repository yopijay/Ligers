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

public class DashboardFragment extends Fragment {

    //Layout
    View dashboard;

    //TextView
    TextView dashboard_title_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dashboard =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        init();

        return dashboard;
    }

    public void init() {
        dashboard_title_txt = (TextView) CustomFieldDesign.design(dashboard_title_txt, R.id.dashboard_title_txt, 20, dashboard);
        CustomFont.Monoround.setFont(dashboard_title_txt, DashboardFragment.this);
    }
}
