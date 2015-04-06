package com.pavan.slidingmenu.slidelist;

import com.pavan.slidingmenu.R;
import com.pavan.slidingmenu.TimeTable;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class TB_Fragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.tb_fragment, container, false);
        Intent i = new Intent(getActivity(), TimeTable.class);
        startActivity(i);
		return rootView;
	}
}
