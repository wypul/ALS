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
public class FridayFragment extends Fragment {

    VipulDatabaseAdapterFriday vipulHelper;
    Button save;
    EditText fl[] = new EditText[20];
    EditText fs[] = new EditText[20];
    ArrayList arrayList;
    Iterator itr;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.friday_layout, container, false);
        vipulHelper = new VipulDatabaseAdapterFriday(getActivity().getApplicationContext());
        vipulHelper.initialize();
        save = (Button) v.findViewById(R.id.frisave);
        fl[0] = (EditText) v.findViewById(R.id.fl8);
        fl[1] = (EditText) v.findViewById(R.id.fl9);
        fl[2] = (EditText) v.findViewById(R.id.fl10);
        fl[3] = (EditText) v.findViewById(R.id.fl11);
        fl[4] = (EditText) v.findViewById(R.id.fl12);
        fl[5] = (EditText) v.findViewById(R.id.fl1);
        fl[6] = (EditText) v.findViewById(R.id.fl2);
        fl[7] = (EditText) v.findViewById(R.id.fl3);
        fl[8] = (EditText) v.findViewById(R.id.fl4);
        fs[0] = (EditText) v.findViewById(R.id.fs8);
        fs[1] = (EditText) v.findViewById(R.id.fs9);
        fs[2] = (EditText) v.findViewById(R.id.fs10);
        fs[3] = (EditText) v.findViewById(R.id.fs11);
        fs[4] = (EditText) v.findViewById(R.id.fs12);
        fs[5] = (EditText) v.findViewById(R.id.fs1);
        fs[6] = (EditText) v.findViewById(R.id.fs2);
        fs[7] = (EditText) v.findViewById(R.id.fs3);
        fs[8] = (EditText) v.findViewById(R.id.fs4);
        arrayList = vipulHelper.getData();
        itr = arrayList.iterator();
        if(!arrayList.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                fl[i].setText((String) arrayList.get(i*2));
            }
            for (int i = 0; i < 9; i++) {
                fs[i].setText((String) arrayList.get(i*2+1));
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
                    room_no[i] = fl[i].getText().toString();
                    subject[i] = fs[i].getText().toString();
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
