package pijay.dev.ligers._fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;
import pijay.dev.ligers.R;
import pijay.dev.ligers._class.CustomFieldDesign;
import pijay.dev.ligers._class.CustomFont;

public class DashboardFragment extends Fragment {

    //Layout
    View dashboard;

    //TextView
    TextView dashboard_title_txt;
    TextView month_title;
    TextView day_title;
    TextView year_title;
    TextView month_value;
    TextView day_value;
    TextView year_value;
    TextView attendance_txt;
    TextView funds_txt;
    TextView members_txt;

    //Buttons

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dashboard =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        init();
        displayCurrentDate();

        return dashboard;
    }

    public void init() {
        dashboard_title_txt = (TextView) CustomFieldDesign.design(dashboard_title_txt, R.id.dashboard_title_txt, 20, dashboard);
        CustomFont.Monoround.setFont(dashboard_title_txt, DashboardFragment.this);

        //TextViews
        month_title = (TextView) CustomFieldDesign.design(month_title, R.id.month_title, 16, dashboard);
        day_title = (TextView) CustomFieldDesign.design(day_title, R.id.day_title, 16, dashboard);
        year_title = (TextView) CustomFieldDesign.design(year_title, R.id.year_title, 16, dashboard);

        attendance_txt = (TextView) CustomFieldDesign.design(attendance_txt, R.id.attendance_title_txt, 18, dashboard);
        funds_txt = (TextView) CustomFieldDesign.design(funds_txt, R.id.funds_title_txt, 18, dashboard);
        members_txt = (TextView) CustomFieldDesign.design(members_txt, R.id.member_title_txt, 18, dashboard);

        month_value = (TextView) CustomFieldDesign.design(month_value, R.id.month_value, 50, dashboard);
        day_value = (TextView) CustomFieldDesign.design(day_value, R.id.day_value, 50, dashboard);
        year_value = (TextView) CustomFieldDesign.design(year_value, R.id.year_value, 50, dashboard);

        CustomFont.LatoBold.setFont(month_title, DashboardFragment.this);
        CustomFont.LatoBold.setFont(day_title, DashboardFragment.this);
        CustomFont.LatoBold.setFont(year_title, DashboardFragment.this);

        CustomFont.LatoBold.setFont(attendance_txt, DashboardFragment.this);
        CustomFont.LatoBold.setFont(funds_txt, DashboardFragment.this);
        CustomFont.LatoBold.setFont(members_txt, DashboardFragment.this);

        CustomFont.LatoLight.setFont(month_value, DashboardFragment.this);
        CustomFont.LatoLight.setFont(day_value, DashboardFragment.this);
        CustomFont.LatoLight.setFont(year_value, DashboardFragment.this);

        //Buttons
    }

    public void displayCurrentDate() {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        int year = Calendar.getInstance().get(Calendar.YEAR) % 100;

        month_value.setText(String.valueOf(month));
        day_value.setText(String.valueOf(day));
        year_value.setText(String.valueOf(year));
    }
}
