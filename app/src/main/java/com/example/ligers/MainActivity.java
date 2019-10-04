package com.example.ligers;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.sdsmdg.tastytoast.TastyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;

    //TextViews
    TextView header_title;
    TextView title_member;
    TextView member_count;
    TextView title_funds;
    TextView funds_count;
    TextView title_attendance;

    //CardView
    CardView cv_members;
    CardView cv_funds;
    CardView cv_attendance;

    //Others
    CircularImageView profile_btn;
    SpaceNavigationView bottomnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_form);

        init();

        bottomnav = findViewById(R.id.bottom_nav);
        bottomnav.initWithSaveInstanceState(savedInstanceState);
        bottomnav.addSpaceItem(new SpaceItem("DASHBOARD", R.drawable.ic_dashboard));
        bottomnav.addSpaceItem(new SpaceItem("FUNDS", R.drawable.ic_money));
        bottomnav.addSpaceItem(new SpaceItem("MEMBERS", R.drawable.ic_member));
        bottomnav.addSpaceItem(new SpaceItem("ATTENDANCE", R.drawable.ic_attendance));
        bottomnav.showIconOnly();

        bottomnav.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                bottomnav.setCentreButtonSelectable(true);
                bottomnav.setActiveCentreButtonBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.activeColor));
                header_title.setText(R.string.tryout_name);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if(itemName.equals("DASHBOARD")) {
                    header_title.setText(R.string.dashboard_name);
                }
                else if(itemName.equals("FUNDS")) {
                    header_title.setText(R.string.funds_name);
                }
                else if(itemName.equals("TRYOUT")) {
                    header_title.setText(R.string.tryout_name);
                }
                else if(itemName.equals("MEMBERS")) {
                    header_title.setText(R.string.members_name);
                }
                else if(itemName.equals("ATTENDANCE")) {
                    header_title.setText(R.string.attendance_name);
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                if(itemName.equals("DASHBOARD")) {
                    header_title.setText(R.string.dashboard_name);
                }
                else if(itemName.equals("FUNDS")) {
                    header_title.setText(R.string.funds_name);
                }
                else if(itemName.equals("TRYOUT")) {
                    header_title.setText(R.string.tryout_name);
                }
                else if(itemName.equals("MEMBERS")) {
                    header_title.setText(R.string.members_name);
                }
                else if(itemName.equals("ATTENDANCE")) {
                    header_title.setText(R.string.attendance_name);
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        String message = "";
        switch(v.getId()) {
            case R.id.profileBtn:
                message = "Profile";
                break;
            case R.id.cv_member:
                message = "Members";
                break;
            case R.id.cv_funds:
                message = "Funds";
                break;
            case R.id.cv_attendance:
                message = "Attendance";
                break;
        }

        TastyToast.makeText(MainActivity.this, message + " is Clicked!", Toast.LENGTH_SHORT, TastyToast.SUCCESS).setGravity(Gravity.TOP, 0, 0);
    }

    public void init() {
        Typeface Gentona = Typeface.createFromAsset(getAssets(), "fonts/gentona.otf");
        Typeface CenturyGothic = Typeface.createFromAsset(getAssets(), "fonts/Century Gothic.ttf");

        toolbar = findViewById(R.id.header);
        setSupportActionBar(toolbar);

        header_title = findViewById(R.id.header_title);
        header_title.setTypeface(Gentona);

        title_member = findViewById(R.id.title_members);
        title_member.setTypeface(CenturyGothic);
        title_funds = findViewById(R.id.title_funds);
        title_funds.setTypeface(CenturyGothic);
        title_attendance = findViewById(R.id.title_attendance);
        title_attendance.setTypeface(CenturyGothic);

        member_count = findViewById(R.id.count_members);
        member_count.setTypeface(CenturyGothic);
        funds_count = findViewById(R.id.count_funds);
        funds_count.setTypeface(CenturyGothic);

        //Clickable elements
        profile_btn = findViewById(R.id.profileBtn);
        profile_btn.setOnClickListener(this);

        cv_members = findViewById(R.id.cv_member);
        cv_members.setOnClickListener(this);
        cv_funds = findViewById(R.id.cv_funds);
        cv_funds.setOnClickListener(this);
        cv_attendance = findViewById(R.id.cv_attendance);
        cv_attendance.setOnClickListener(this);
    }
}
