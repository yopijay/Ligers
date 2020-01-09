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

public class AttendanceFragment extends Fragment {

    //Layouts
    View attendance;

    //TextViews
    TextView attendance_title_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        attendance =  inflater.inflate(R.layout.fragment_attendance, container, false);

        init();

        return attendance;
    }

    public void init() {
        attendance_title_txt = (TextView) CustomFieldDesign.design(attendance_title_txt, R.id.attendance_title_txt, 20, attendance);
        CustomFont.Monoround.setFont(attendance_title_txt, AttendanceFragment.this);
    }
}
