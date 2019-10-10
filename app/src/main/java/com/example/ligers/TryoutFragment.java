package com.example.ligers;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mehdi.sakout.fancybuttons.FancyButton;

public class TryoutFragment extends Fragment {

    FancyButton addTryoutBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View tryout = inflater.inflate(R.layout.tryout_page, container, false);

        //Fonts
//        Typeface CenturyGothic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Century Gothic.ttf");

        addTryoutBtn = tryout.findViewById(R.id.addTryout);
        addTryoutBtn.setText("ADD TRY-OUT");
        addTryoutBtn.setCustomTextFont("Century Gothic.ttf");
        addTryoutBtn.setRadius(10);

        return tryout;
    }
}
