package com.pavan.slidingmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pavan.slidingmenu.slidelist.GP_Fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vipul on 25/3/15.
 */
public class WednesdayFragment extends Fragment {

    VipulDatabaseAdapterWednesday vipulHelper;
    VipulDatabaseAdapterSubject vipulSubject;
    GP_Fragment gp_fragment;
    Button save,view;
    //    EditText ml[] = new EditText[20];
    Spinner sub_spinner[] = new Spinner[20];
    Spinner location[] = new Spinner[20];
    public ArrayList arrayList,subjectList;
    public String sub_selected[] = new String[20];
    public String location_selected[] = new String[20];
    Iterator itr,itrSub;
    final List<String> list=new ArrayList<String>();
    final List<String> location_list=new ArrayList<String>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.wednesday_layout, container, false);
        vipulHelper = new VipulDatabaseAdapterWednesday(getActivity().getApplicationContext());
        vipulHelper.initialize();
        vipulSubject = new VipulDatabaseAdapterSubject(getActivity().getApplicationContext());
        list.clear();
        location_list.clear();
        save = (Button) v.findViewById(R.id.save);

        location[0] = (Spinner) v.findViewById(R.id.location8);
        location[1] = (Spinner) v.findViewById(R.id.location9);
        location[2] = (Spinner) v.findViewById(R.id.location10);
        location[3] = (Spinner) v.findViewById(R.id.location11);
        location[4] = (Spinner) v.findViewById(R.id.location12);
        location[5] = (Spinner) v.findViewById(R.id.location1);
        location[6] = (Spinner) v.findViewById(R.id.location2);
        location[7] = (Spinner) v.findViewById(R.id.location3);
        location[8] = (Spinner) v.findViewById(R.id.location4);

        sub_spinner[0] = (Spinner) v.findViewById(R.id.spinner8);
        sub_spinner[1] = (Spinner) v.findViewById(R.id.spinner9);
        sub_spinner[2] = (Spinner) v.findViewById(R.id.spinner10);
        sub_spinner[3] = (Spinner) v.findViewById(R.id.spinner11);
        sub_spinner[4] = (Spinner) v.findViewById(R.id.spinner12);
        sub_spinner[5] = (Spinner) v.findViewById(R.id.spinner1);
        sub_spinner[6] = (Spinner) v.findViewById(R.id.spinner2);
        sub_spinner[7] = (Spinner) v.findViewById(R.id.spinner3);
        sub_spinner[8] = (Spinner) v.findViewById(R.id.spinner4);

        location_list.add("");
        location_list.add("B-block");
        location_list.add("C-block");
        location_list.add("D-block");
        location_list.add("E-block");
        location_list.add("F-block");

        for(int i=0;i<9;i++) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, location_list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            location[i].setAdapter(adapter);
        }

        for(int k=0;k<9;k++) {
            final int finalK = k;
            location[k].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    location_selected[finalK] = adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }


        gp_fragment = new GP_Fragment();
        int count = gp_fragment.count;
        list.add("");
        try {
            subjectList = vipulSubject.getData();
            itrSub = subjectList.iterator();
            if (!subjectList.isEmpty()) {
                for (int i = 0; i < count; i++) {
                    list.add(((String) subjectList.get(i * 3)));
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        for(int i=0;i<9;i++) {
            ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, list);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sub_spinner[i].setAdapter(adp1);
        }

        for(int k=0;k<9;k++) {
            final int finalK = k;
            sub_spinner[k].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getActivity().getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
                    sub_selected[finalK] = arg0.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }

            });
        }


        arrayList = vipulHelper.getData();
        itr = arrayList.iterator();
        String defaultvalue;
        if(!arrayList.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                defaultvalue = (String) arrayList.get(i * 2 );
                int index = location_list.indexOf(defaultvalue);
                if (index != -1)
                    location[i].setSelection(index);
            }
            for (int i = 0; i < 9; i++) {
                defaultvalue = (String) arrayList.get(i * 2 + 1);
                int index = list.indexOf(defaultvalue);
                if (index != -1)
                    sub_spinner[i].setSelection(index);
            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room_no[] = new String[20];
                String subject[] = new String[20];
                long id[] = new long[20];
                vipulHelper.checkdb();
                try {
                    for (int i = 0; i < 9; i++) {
                        //room_no[i] = ml[i].getText().toString();
                        room_no[i] = location_selected[i];
                        subject[i] = sub_selected[i];
                        id[i] = vipulHelper.insertData(room_no[i], subject[i]);
                    }
                }catch (Exception e){}
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



