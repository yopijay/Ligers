package com.example.ligers;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.sdsmdg.tastytoast.TastyToast;

import org.w3c.dom.Text;

public class DashboardFragment extends Fragment {

    CardView cv_members;
    CardView cv_funds;
    CardView cv_attendance;

    TextView members_ttle;
    TextView funds_ttle;
    TextView attendance_ttle;

    TextView member_cnt;
    TextView fund_cnt;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View dashboard = inflater.inflate(R.layout.dashboard_page, container, false);

        //Fonts
        Typeface CenturyGothic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Century Gothic.ttf");

        members_ttle = dashboard.findViewById(R.id.title_members);
        members_ttle.setTypeface(CenturyGothic);

        member_cnt = dashboard.findViewById(R.id.count_members);
        member_cnt.setTypeface(CenturyGothic);

        cv_members = dashboard.findViewById(R.id.cv_member);
        cv_members.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View dashboard) {
                pageFragment(new MembersFragment(), "MEMBERS");
            }
        });

        funds_ttle = dashboard.findViewById(R.id.title_funds);
        funds_ttle.setTypeface(CenturyGothic);

        fund_cnt = dashboard.findViewById(R.id.count_funds);
        fund_cnt.setTypeface(CenturyGothic);

        cv_funds = dashboard.findViewById(R.id.cv_funds);
        cv_funds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View dashboard) {
                pageFragment(new FundsFragment(), "FUNDS");
            }
        });

        attendance_ttle = dashboard.findViewById(R.id.title_attendance);
        attendance_ttle.setTypeface(CenturyGothic);

        cv_attendance = dashboard.findViewById(R.id.cv_attendance);
        cv_attendance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View dashboard) {
                pageFragment(new AttendanceFragment(), "ATTENDANCE");
//                TastyToast.makeText(getActivity(), header_title.getText().toString(), TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
            }
        });

        return dashboard;
    }

    void pageFragment(Fragment pf, String title) {
        MainActivity.header_title.setText(title);

        FragmentTransaction page = getFragmentManager().beginTransaction();
        page.replace(R.id.pageContainer, pf);
        page.addToBackStack(null);

        page.commit();
    }
}
