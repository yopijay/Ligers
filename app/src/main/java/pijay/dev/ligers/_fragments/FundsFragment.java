package pijay.dev.ligers._fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.Calendar;

import pijay.dev.ligers.R;
import pijay.dev.ligers._class.CustomFieldDesign;
import pijay.dev.ligers._class.CustomFont;

public class FundsFragment extends Fragment {

    //Layout
    View funds;

    //TextViews
    TextView funds_title_txt;
    TextView date_now_txt;
    TextView total_funds;
    TextView total_funds_title;
    TextView cash_onhand;
    TextView cash_onhand_title;
    TextView balance;
    TextView balance_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        funds =  inflater.inflate(R.layout.fragment_funds, container, false);

        init();
        displayCurrentDate();

        return funds;
    }

    public void init() {
        funds_title_txt = (TextView) CustomFieldDesign.design(funds_title_txt, R.id.funds_title_txt, 20, funds);
        date_now_txt = (TextView) CustomFieldDesign.design(date_now_txt, R.id.date_now_txt, 16, funds);

        total_funds = (TextView) CustomFieldDesign.design(total_funds, R.id.total_funds, 22, funds);
        cash_onhand = (TextView) CustomFieldDesign.design(cash_onhand, R.id.cash_onhand, 17, funds);
        balance = (TextView) CustomFieldDesign.design(balance, R.id.cash_balance, 17, funds);

        total_funds_title = (TextView) CustomFieldDesign.design(total_funds_title, R.id.total_funds_title, 15, funds);
        cash_onhand_title = (TextView) CustomFieldDesign.design(cash_onhand_title, R.id.cash_onhand_title, 12, funds);
        balance_title = (TextView) CustomFieldDesign.design(balance_title, R.id.balance_title, 12, funds);

        CustomFont.Monoround.setFont(funds_title_txt, FundsFragment.this);
        CustomFont.LatoLight.setFont(date_now_txt, FundsFragment.this);

        CustomFont.Monoround.setFont(total_funds, FundsFragment.this);
        CustomFont.Monoround.setFont(cash_onhand, FundsFragment.this);
        CustomFont.Monoround.setFont(balance, FundsFragment.this);

        CustomFont.LatoBold.setFont(total_funds_title, FundsFragment.this);
        CustomFont.LatoBold.setFont(cash_onhand_title, FundsFragment.this);
        CustomFont.LatoBold.setFont(balance_title, FundsFragment.this);
    }

    public void displayCurrentDate() {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        int year = Calendar.getInstance().get(Calendar.YEAR);

        date_now_txt.setText("Total Funds as of: " + month + " - " + day + " - " + year);
    }
}
