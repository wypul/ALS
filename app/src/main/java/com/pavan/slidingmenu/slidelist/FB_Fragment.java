package com.pavan.slidingmenu.slidelist;

import com.pavan.slidingmenu.MainActivity;
import com.pavan.slidingmenu.R;
import com.pavan.slidingmenu.Report;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi")
public class FB_Fragment extends Fragment  {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fb_fragment, container, false);
        Intent i = new Intent(getActivity(), Report.class);
        startActivity(i);
        return rootView;
    }
}


