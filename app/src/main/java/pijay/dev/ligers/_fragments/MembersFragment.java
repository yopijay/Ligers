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

public class MembersFragment extends Fragment {

    //Layout
    View members;

    //TextViews
    TextView member_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        members =  inflater.inflate(R.layout.fragment_members, container, false);

        init();

        return members;
    }

    public void init() {
        member_title = (TextView) CustomFieldDesign.design(member_title, R.id.member_title_txt, 20, members);
        CustomFont.Monoround.setFont(member_title, MembersFragment.this);
    }
}
