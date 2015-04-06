package com.pavan.slidingmenu;

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
public class TuesdayFragment extends Fragment {
    VipulDatabaseAdapterTuesday vipulHelper;
    Button save;
    EditText tl[] = new EditText[20];
    EditText ts[] = new EditText[20];
    ArrayList arrayList;
    Iterator itr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tuesday_layout, container, false);
        vipulHelper = new VipulDatabaseAdapterTuesday(getActivity().getApplicationContext());
        vipulHelper.initialize();
        save = (Button) v.findViewById(R.id.tuessave);
        tl[0] = (EditText) v.findViewById(R.id.tl8);
        tl[1] = (EditText) v.findViewById(R.id.tl9);
        tl[2] = (EditText) v.findViewById(R.id.tl10);
        tl[3] = (EditText) v.findViewById(R.id.tl11);
        tl[4] = (EditText) v.findViewById(R.id.tl12);
        tl[5] = (EditText) v.findViewById(R.id.tl1);
        tl[6] = (EditText) v.findViewById(R.id.tl2);
        tl[7] = (EditText) v.findViewById(R.id.tl3);
        tl[8] = (EditText) v.findViewById(R.id.tl4);
        ts[0] = (EditText) v.findViewById(R.id.ts8);
        ts[1] = (EditText) v.findViewById(R.id.ts9);
        ts[2] = (EditText) v.findViewById(R.id.ts10);
        ts[3] = (EditText) v.findViewById(R.id.ts11);
        ts[4] = (EditText) v.findViewById(R.id.ts12);
        ts[5] = (EditText) v.findViewById(R.id.ts1);
        ts[6] = (EditText) v.findViewById(R.id.ts2);
        ts[7] = (EditText) v.findViewById(R.id.ts3);
        ts[8] = (EditText) v.findViewById(R.id.ts4);
        arrayList = vipulHelper.getData();
        itr = arrayList.iterator();
        if(!arrayList.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                tl[i].setText((String) arrayList.get(i*2));
            }
            for (int i = 0; i < 9; i++) {
                ts[i].setText((String) arrayList.get(i*2+1));
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
                    room_no[i] = tl[i].getText().toString();
                    subject[i] = ts[i].getText().toString();
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
