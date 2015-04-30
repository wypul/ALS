package com.pavan.slidingmenu;

import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vidursachdeva on 06/04/15.
 */

@SuppressLint("NewApi") public class Subject1 extends Fragment{
    private View mView;
    private GraphicalView mGraphView;
    VipulDatabaseAdapterSubject vipulSubject;
    public ArrayList subject;
    Iterator itrSub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.subject1, container, false);
        mView = rootView;
        initData();
        return rootView;
    }
    private void initData() {

        vipulSubject = new VipulDatabaseAdapterSubject(getActivity().getApplicationContext());
        String subject_1=null;

        subject = vipulSubject.getData();
        itrSub = subject.iterator();
        if (!subject.isEmpty()) {
                subject_1 = (String) subject.get(0);
            }
        String present = vipulSubject.getPresent(subject_1);
        String absent = vipulSubject.getAbsent(subject_1);
        String[] codename = {
                "Absents=","Present",
        };

        double[] values = {Double.parseDouble(absent),Double.parseDouble(present)};
        String[] colors = {
                "#ff4444", "#99cc00", "#aa66cc", "#33b5e5", "#ffbb33"
        };


        CategorySeries series = new CategorySeries("Android Platform Version");
        int length = codename.length;
        for (int i = 0; i < length; i++) {
            series.add(codename[i], values[i]);
        }

        DefaultRenderer renderer = new DefaultRenderer();
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(Color.parseColor(colors[i]));

            renderer.addSeriesRenderer(seriesRenderer);
        }

        renderer.setChartTitleTextSize(30);
        renderer.setChartTitle("Attendance Chart");
        renderer.setLabelsTextSize(30);
        renderer.setLabelsColor(Color.GRAY);
        renderer.setLegendTextSize(30);

        drawChart(series, renderer);
    }

    private void drawChart(CategorySeries series,
                           DefaultRenderer renderer) {

        if (null == mGraphView) {
            mGraphView =
                    ChartFactory.getPieChartView(getActivity(), series, renderer);

            RelativeLayout container =
                    (RelativeLayout) mView.findViewById(R.id.graph_container);

            container.addView(mGraphView);
        } else {
            mGraphView.repaint();
        }
    }

}


