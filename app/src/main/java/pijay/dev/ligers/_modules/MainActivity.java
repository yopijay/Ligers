package pijay.dev.ligers._modules;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.sdsmdg.tastytoast.TastyToast;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;
import pijay.dev.ligers.R;
import pijay.dev.ligers._class.CustomFieldDesign;
import pijay.dev.ligers._class.CustomFont;
import pijay.dev.ligers._fragments.AttendanceFragment;
import pijay.dev.ligers._fragments.DashboardFragment;
import pijay.dev.ligers._fragments.FundsFragment;
import pijay.dev.ligers._fragments.MembersFragment;
import pijay.dev.ligers._fragments.OthersFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnItemSelectedListener {

    Fragment page;
    private Toolbar toolbar;

    //TextViews
    TextView title_txt;

    //Buttons
    CircularImageView profile_btn;

    //Others
    SmoothBottomBar bottom_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_module);

        init();

        setPage(new DashboardFragment());
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

        bottom_bar = findViewById(R.id.bottom_bar);
        bottom_bar.setOnItemSelectedListener(MainActivity.this);

        title_txt = (TextView) CustomFieldDesign.design(title_txt, R.id.header_title, 25, MainActivity.this);
        CustomFont.Monoround.setFont(title_txt, MainActivity.this);

        profile_btn = (CircularImageView) CustomFieldDesign.design(profile_btn, R.id.profile_btn, 0, MainActivity.this);
        profile_btn.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onItemSelect(int i) {
        switch (i) {
            case 0:
                setPage(new DashboardFragment());
                break;

            case 1:
                setPage(new FundsFragment());
                break;

            case 2:
                setPage(new AttendanceFragment());
                break;

            case 3:
                setPage(new MembersFragment());
                break;

            case 4:
                setPage(new OthersFragment());
                break;
        }
    }

    public void setPage(Fragment fragment) {
        page = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, page).commit();
    }
}
