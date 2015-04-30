package com.pavan.slidingmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.achartengine.GraphicalView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vipul on 27/4/15.
 */
public class Subject4 extends Fragment {

    private View mView;
    private GraphicalView mGraphView;
    VipulDatabaseAdapterSubject vipulSubject;
    public ArrayList subject;
    Iterator itrSub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subject1, container, false);
        mView = rootView;
        //initData();
        return rootView;
    }
}
