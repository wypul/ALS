package com.pavan.slidingmenu;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vipul on 28/3/15.
 */
public class WednesdayFragment extends Fragment {

        VipulDatabaseAdapterWednesday vipulHelper;
        Button save,view;
        EditText wl[] = new EditText[20];
        EditText ws[] = new EditText[20];
        ArrayList arrayList;
        Iterator itr;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.wednesday_layout, container, false);
        vipulHelper = new VipulDatabaseAdapterWednesday(getActivity().getApplicationContext());
        vipulHelper.initialize();
        save = (Button) v.findViewById(R.id.wedsave);
        wl[0] = (EditText) v.findViewById(R.id.wl8);
        wl[1] = (EditText) v.findViewById(R.id.wl9);
        wl[2] = (EditText) v.findViewById(R.id.wl10);
        wl[3] = (EditText) v.findViewById(R.id.wl11);
        wl[4] = (EditText) v.findViewById(R.id.wl12);
        wl[5] = (EditText) v.findViewById(R.id.wl1);
        wl[6] = (EditText) v.findViewById(R.id.wl2);
        wl[7] = (EditText) v.findViewById(R.id.wl3);
        wl[8] = (EditText) v.findViewById(R.id.wl4);
        ws[0] = (EditText) v.findViewById(R.id.ws8);
        ws[1] = (EditText) v.findViewById(R.id.ws9);
        ws[2] = (EditText) v.findViewById(R.id.ws10);
        ws[3] = (EditText) v.findViewById(R.id.ws11);
        ws[4] = (EditText) v.findViewById(R.id.ws12);
        ws[5] = (EditText) v.findViewById(R.id.ws1);
        ws[6] = (EditText) v.findViewById(R.id.ws2);
        ws[7] = (EditText) v.findViewById(R.id.ws3);
        ws[8] = (EditText) v.findViewById(R.id.ws4);
        arrayList = vipulHelper.getData();
        itr = arrayList.iterator();
        if(!arrayList.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                wl[i].setText((String) arrayList.get(i*2));
            }
            for (int i = 0; i < 9; i++) {
                ws[i].setText((String) arrayList.get(i*2+1));
            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room_no[] = new String[20];
                String subject[] = new String[20];
                long id[] = new long[20];
                vipulHelper.checkdb();
                for(int i=0;i<9;i++) {
                    room_no[i] = wl[i].getText().toString();
                    subject[i] = ws[i].getText().toString();
                    id[i] = vipulHelper.insertData(room_no[i], subject[i]);
                }
                int flag = 0;
                for(int i=0;i<9;i++){
                    if(id[i]<0)
                    {
                        Message.message(getActivity(),"Unsuccessful");
                        flag = 1;
                        break;
                    }
                }
                if(flag==0)
                    Message.message(getActivity(),"Successfully inserted row");
            }
        });

        return v;
    }
}
