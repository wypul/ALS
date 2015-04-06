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
 * Created by vipul on 29/3/15.
 */
public class ThursdayFragment extends Fragment {
    VipulDatabaseAdapterThursday vipulHelper;
    Button save,view;
    EditText thl[] = new EditText[20];
    EditText ths[] = new EditText[20];
    ArrayList arrayList;
    Iterator itr;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.thursday_layout, container, false);
        vipulHelper = new VipulDatabaseAdapterThursday(getActivity().getApplicationContext());
        vipulHelper.initialize();
        save = (Button) v.findViewById(R.id.thsave);
        thl[0] = (EditText) v.findViewById(R.id.thl8);
        thl[1] = (EditText) v.findViewById(R.id.thl9);
        thl[2] = (EditText) v.findViewById(R.id.thl10);
        thl[3] = (EditText) v.findViewById(R.id.thl11);
        thl[4] = (EditText) v.findViewById(R.id.thl12);
        thl[5] = (EditText) v.findViewById(R.id.thl1);
        thl[6] = (EditText) v.findViewById(R.id.thl2);
        thl[7] = (EditText) v.findViewById(R.id.thl3);
        thl[8] = (EditText) v.findViewById(R.id.thl4);
        ths[0] = (EditText) v.findViewById(R.id.ths8);
        ths[1] = (EditText) v.findViewById(R.id.ths9);
        ths[2] = (EditText) v.findViewById(R.id.ths10);
        ths[3] = (EditText) v.findViewById(R.id.ths11);
        ths[4] = (EditText) v.findViewById(R.id.ths12);
        ths[5] = (EditText) v.findViewById(R.id.ths1);
        ths[6] = (EditText) v.findViewById(R.id.ths2);
        ths[7] = (EditText) v.findViewById(R.id.ths3);
        ths[8] = (EditText) v.findViewById(R.id.ths4);
        arrayList = vipulHelper.getData();
        itr = arrayList.iterator();
        if(!arrayList.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                thl[i].setText((String) arrayList.get(i*2));
            }
            for (int i = 0; i < 9; i++) {
                ths[i].setText((String) arrayList.get(i*2+1));
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
                    room_no[i] = thl[i].getText().toString();
                    subject[i] = ths[i].getText().toString();
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
