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

import com.pavan.slidingmenu.MondayFragment;
import com.pavan.slidingmenu.R;
import com.pavan.slidingmenu.VipulDatabaseAdapterMonday;
import com.pavan.slidingmenu.VipulDatabaseAdapterSubject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

@SuppressLint("NewApi") public class GP_Fragment extends Fragment{

    EditText day,time,viewtxt,showattendance;
    EditText sub[] = new EditText[10];
    Button button,show,markattendance;
    public final static String subject[] = new String[10];
    public static int count=0;
    ArrayList arrayList;
    Iterator itr;
    int dayofweek,hourofday;
    VipulDatabaseAdapterSubject vipulHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.gp_fragment, container, false);
        vipulHelper = new VipulDatabaseAdapterSubject(getActivity().getApplicationContext());
        vipulHelper.initialize();
        Calendar cal = Calendar.getInstance();
        dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        hourofday = cal.get(Calendar.HOUR_OF_DAY);
        day=(EditText) rootView.findViewById(R.id.day);
        time=(EditText) rootView.findViewById(R.id.time);
        viewtxt=(EditText) rootView.findViewById(R.id.view);
        showattendance=(EditText) rootView.findViewById(R.id.viewattendance);
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
        markattendance = (Button) rootView.findViewById(R.id.markattendance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                for(int i=0;i<10;i++)
                {
                    if(sub[i]!=null) {
                        subject[i] = sub[i].getText().toString();
                     //   count++;
                    }
                }
                for(int i=0;i<10;i++)
                {
                    if(subject[i]!=null && !subject[i].isEmpty())
                        count++;
                }
                vipulHelper.checkdb();
                long id [] = new long[10];
                for(int i=0;i<count;i++)
                    id[i] = vipulHelper.insertData(subject[i],"4","1");

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = vipulHelper.getData();
                itr = arrayList.iterator();
                if(!arrayList.isEmpty()){
                    viewtxt.setText((String) arrayList.get(1));
                }
                String present = (String)arrayList.get(1);
                present = String.valueOf(Integer.valueOf(present) + 1);
                try {
                    vipulHelper.updatePresent(present,"q");
                    arrayList = vipulHelper.getData();
                    itr = arrayList.iterator();
                    //Toast.makeText(getActivity(),(String)arrayList.get(1),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        markattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject,present;
                switch (dayofweek) {
                    case 2:
                        MondayFragment mondayFragment = new MondayFragment();
                        //subject = (String)mondayFragment.arrayList.get((hourofday-8)*2+1);
                        subject = "w";
                        present = vipulHelper.getPresent(subject);
                        Toast.makeText(getActivity(),present, Toast.LENGTH_LONG).show();
                        if(present!=null)
                            present = String.valueOf(Integer.valueOf(present) + 1);
                        try {
                            vipulHelper.updatePresent(present, subject);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        Toast.makeText(getActivity(),present, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        return rootView;
    }
}
