package pijay.dev.ligers._modules;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.sdsmdg.tastytoast.TastyToast;

import pijay.dev.ligers.R;
import pijay.dev.ligers._class.CustomFieldDesign;
import pijay.dev.ligers._class.CustomFont;
import pijay.dev.ligers._fragments.AttendanceFragment;
import pijay.dev.ligers._fragments.FundsFragment;
import pijay.dev.ligers._fragments.MembersFragment;
import pijay.dev.ligers._fragments.MoreFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static SpaceNavigationView bottomnav;
    Fragment page;
    private Toolbar toolbar;

    //TextViews
    TextView title_txt;

    //Buttons
    CircularImageView profile_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_module);

        init();

        page = new MembersFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();

        bottomnav = findViewById(R.id.bottom_nav);
        bottomnav.initWithSaveInstanceState(savedInstanceState);
        bottomnav.addSpaceItem(new SpaceItem("MEMBERS", R.drawable.member_icon));
        bottomnav.addSpaceItem(new SpaceItem("FUNDS", R.drawable.funds_icon));
        bottomnav.addSpaceItem(new SpaceItem("ATTENDANCE", R.drawable.calendar_icon));
        bottomnav.addSpaceItem(new SpaceItem("MORE", R.drawable.more_icon));
        bottomnav.showIconOnly();

        bottomnav.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if(itemName.equals("MEMBERS")) {
                    page = new MembersFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
                }
                else if(itemName.equals("FUNDS")) {
                    page = new FundsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
                }
                else if(itemName.equals("ATTENDANCE")) {
                    page = new AttendanceFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
                }
                else if(itemName.equals("MORE")) {
                    page = new MoreFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                if(itemName.equals("MEMBERS")) {
                    page = new MembersFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
                }
                else if(itemName.equals("FUNDS")) {
                    page = new FundsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
                }
                else if(itemName.equals("ATTENDANCE")) {
                    page = new AttendanceFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
                }
                else if(itemName.equals("MORE")) {
                    page = new MoreFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
                }
            }
        });
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.profile_btn:
                TastyToast.makeText(MainActivity.this, "Profile button clicked!", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                break;
        }
    }

    public void init() {
        toolbar = findViewById(R.id.header);
        setSupportActionBar(toolbar);

        title_txt = (TextView) CustomFieldDesign.design(title_txt, R.id.header_title, 25, MainActivity.this);
        CustomFont.Monoround.setFont(title_txt, MainActivity.this);

        profile_btn = (CircularImageView) CustomFieldDesign.design(profile_btn, R.id.profile_btn, 0, MainActivity.this);
        profile_btn.setOnClickListener(MainActivity.this);
    }
}
