package pijay.dev.ligers._fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pijay.dev.ligers.R;

public class MoreFragment extends Fragment {

    //Layout
    View more;

    //TextView

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        more = inflater.inflate(R.layout.fragment_more, container, false);

        init();

        return more;
    }

    public void init() {

    }
}
