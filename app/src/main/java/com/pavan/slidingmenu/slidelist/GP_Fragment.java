package com.pavan.slidingmenu.slidelist;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pavan.slidingmenu.R;
import com.pavan.slidingmenu.VipulDatabaseAdapterMonday;
import com.pavan.slidingmenu.VipulDatabaseAdapterSubject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

@SuppressLint("NewApi") public class GP_Fragment extends Fragment{

    EditText day,time,viewtxt;
    EditText sub[] = new EditText[10];
    Button button,show;
    public final static String subject[] = new String[10];
    public int count=0;
    ArrayList arrayList;
    Iterator itr;
    VipulDatabaseAdapterSubject vipulHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.gp_fragment, container, false);
        vipulHelper = new VipulDatabaseAdapterSubject(getActivity().getApplicationContext());
        vipulHelper.initialize();
        Calendar cal = Calendar.getInstance();
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        day=(EditText) rootView.findViewById(R.id.day);
        time=(EditText) rootView.findViewById(R.id.time);
        viewtxt=(EditText) rootView.findViewById(R.id.view);
        day.setText(String.valueOf(dayofweek));
        time.setText(String.valueOf(hourofday));
        sub[0] = (EditText) rootView. findViewById(R.id.sub1);
        sub[1] = (EditText) rootView. findViewById(R.id.sub2);
        sub[2] = (EditText) rootView. findViewById(R.id.sub3);
        sub[3] = (EditText) rootView. findViewById(R.id.sub4);
        sub[4] = (EditText) rootView. findViewById(R.id.sub5);
        sub[5] = (EditText) rootView. findViewById(R.id.sub6);
        sub[6] = (EditText) rootView. findViewById(R.id.sub7);
        button = (Button) rootView.findViewById(R.id.save);
        show = (Button) rootView.findViewById(R.id.show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Button Clicked",Toast.LENGTH_LONG).show();
                count = 0;
                for(int i=0;i<10;i++)
                {
                    if(sub[i]!=null) {
                        subject[i] = sub[i].getText().toString();
                        count++;
                    }
                }
                vipulHelper.checkdb();
                long id [] = new long[10];
                for(int i=0;i<count;i++)
                    id[i] = vipulHelper.insertData(subject[i],"4","1");
                Toast.makeText(getActivity(),String.valueOf(count),Toast.LENGTH_LONG).show();

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = vipulHelper.getData();
                itr = arrayList.iterator();
                if(!arrayList.isEmpty()){
                    viewtxt.setText((String) arrayList.get(6));
                }
            }
        });
        return rootView;
    }
}
