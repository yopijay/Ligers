package com.example.ligers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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
    public static TextView header_title;

    //Others
    CircularImageView profile_btn;
    public static SpaceNavigationView bottomnav;
    Fragment page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_form);

        init();

        page = new DashboardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();

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
                page = new TryoutFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if(itemName.equals("DASHBOARD")) {
                    setPage(new DashboardFragment(), "DASHBOARD");
                }
                else if(itemName.equals("FUNDS")) {
                    setPage(new FundsFragment(), "FUNDS");
                }
                else if(itemName.equals("MEMBERS")) {
                    setPage(new MembersFragment(), "MEMBERS");
                }
                else if(itemName.equals("ATTENDANCE")) {
                    setPage(new AttendanceFragment(), "ATTENDANCE");
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                if(itemName.equals("DASHBOARD")) {
                    setPage(new DashboardFragment(), "DASHBOARD");
                }
                else if(itemName.equals("FUNDS")) {
                    setPage(new FundsFragment(), "FUNDS");
                }
                else if(itemName.equals("MEMBERS")) {
                    setPage(new MembersFragment(), "MEMBERS");
                }
                else if(itemName.equals("ATTENDANCE")) {
                    setPage(new AttendanceFragment(), "ATTENDANCE");
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        String message = "";
        switch(v.getId()) {
            case R.id.profileBtn:

                break;
        }
    }

    public void init() {
        Typeface Gentona = Typeface.createFromAsset(getAssets(), "fonts/gentona.otf");
        Typeface CenturyGothic = Typeface.createFromAsset(getAssets(), "fonts/Century Gothic.ttf");

        toolbar = findViewById(R.id.header);
        setSupportActionBar(toolbar);

        header_title = findViewById(R.id.header_title);
        header_title.setTypeface(Gentona);

        //Clickable elements
        profile_btn = findViewById(R.id.profileBtn);
        profile_btn.setOnClickListener(MainActivity.this);
    }

    void setPage (Fragment pf, String title) {
        header_title.setText(title);
        getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, pf).commit();
    }
}
