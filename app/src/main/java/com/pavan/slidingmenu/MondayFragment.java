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
 * Created by vipul on 25/3/15.
 */
public class MondayFragment extends Fragment {

    VipulDatabaseAdapterMonday vipulHelper;
    Button save,view;
    EditText ml[] = new EditText[20];
    EditText ms[] = new EditText[20];
    ArrayList arrayList;
    Iterator itr;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.monday_layout, container, false);
        vipulHelper = new VipulDatabaseAdapterMonday(getActivity().getApplicationContext());
        vipulHelper.initialize();
        save = (Button) v.findViewById(R.id.monsave);
        view = (Button) v.findViewById(R.id.monview);
        ml[0] = (EditText) v.findViewById(R.id.ml8);
        ml[1] = (EditText) v.findViewById(R.id.ml9);
        ml[2] = (EditText) v.findViewById(R.id.ml10);
        ml[3] = (EditText) v.findViewById(R.id.ml11);
        ml[4] = (EditText) v.findViewById(R.id.ml12);
        ml[5] = (EditText) v.findViewById(R.id.ml1);
        ml[6] = (EditText) v.findViewById(R.id.ml2);
        ml[7] = (EditText) v.findViewById(R.id.ml3);
        ml[8] = (EditText) v.findViewById(R.id.ml4);
        ms[0] = (EditText) v.findViewById(R.id.ms8);
        ms[1] = (EditText) v.findViewById(R.id.ms9);
        ms[2] = (EditText) v.findViewById(R.id.ms10);
        ms[3] = (EditText) v.findViewById(R.id.ms11);
        ms[4] = (EditText) v.findViewById(R.id.ms12);
        ms[5] = (EditText) v.findViewById(R.id.ms1);
        ms[6] = (EditText) v.findViewById(R.id.ms2);
        ms[7] = (EditText) v.findViewById(R.id.ms3);
        ms[8] = (EditText) v.findViewById(R.id.ms4);
        arrayList = vipulHelper.getData();
        itr = arrayList.iterator();
        if(!arrayList.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                ml[i].setText((String) arrayList.get(i*2));
            }
            for (int i = 0; i < 9; i++) {
                ms[i].setText((String) arrayList.get(i*2+1));
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
                    room_no[i] = ml[i].getText().toString();
                    subject[i] = ms[i].getText().toString();
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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String data = vipulHelper.getData();
               //Message.message(getActivity(),data);
                int i=0;
                ArrayList arrayList = vipulHelper.getData();
                Iterator itr1 = arrayList.iterator();
                StringBuffer buffer = new StringBuffer();
                while(itr1.hasNext()){
                    //Message.message(getActivity(), (String) itr.next());
                    buffer.append(itr1.next()+"\n");
                    i++;
                }
                Message.message(getActivity(),buffer.toString());
            }
        });
        return v;
    }
}



