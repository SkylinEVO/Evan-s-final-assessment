package com.example.qgassessment.Finance;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.qgassessment.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class IncomeFragment extends Fragment {

    EditText recordType,recordMoney;
    TextView recordTime;

    AccountBean accountBean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        initView(view);
        accountBean = new AccountBean();
        setInitTime();
        accountBean.setType("其他");
        return view;

    }

    public void setInitTime() {        //获取当前时间并显示在time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault());
        String time = formatter.format(now);
        recordTime.setText(time);
        accountBean.setTime(time);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;     //+1符合中国国情
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        accountBean.setYear(year);
        accountBean.setMonth(month);
        accountBean.setDay(day);
    }
    public void initView(View view) {
        recordType = view.findViewById(R.id.record_income_type);
        recordMoney = view.findViewById(R.id.records_income_money);
        recordTime = view.findViewById(R.id.records_income_date);
        String moneyStr = recordMoney.getText().toString();
        if(!TextUtils.isEmpty(moneyStr)||moneyStr.equals("0")){
            getActivity().finish();
            return;
        }
    }
}