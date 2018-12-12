package com.example.bbx22.projet3;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;



public class BarChartFrag extends SimpleFragment implements OnChartGestureListener {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private BarChart chart;
    @NonNull
    public static Fragment newInstance(String param1) {
        BarChartFrag barChartFrag = new BarChartFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        barChartFrag.setArguments(args);
        return barChartFrag;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_chart, container, false);



        // create a new chart object
        chart = new BarChart(getActivity());
        chart.getDescription().setEnabled(false);
        chart.setOnChartGestureListener(this);

        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");

        int indice = Integer.parseInt(mParam1);
        switch (indice){

            case 1:
                chart.setData(generateBarData(Integer.parseInt(mParam1),  ((resultat)getActivity()).puissance1));
                break;
            case 2:
                chart.setData(generateBarData(Integer.parseInt(mParam1),  ((resultat)getActivity()).puissance2));
                break;
            case 3:
                chart.setData(generateBarData(Integer.parseInt(mParam1),  ((resultat)getActivity()).puissance3));
                break;
            case 4:
                chart.setData(generateBarData(Integer.parseInt(mParam1),  ((resultat)getActivity()).puissance4));
                break;
            case 5:
                chart.setData(generateBarData(Integer.parseInt(mParam1),  ((resultat)getActivity()).puissance5));
                break;
        }



        Legend l = chart.getLegend();
        l.setTypeface(tf);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        // programmatically add the chart
        FrameLayout parent = view.findViewById(R.id.parentLayout);
        parent.addView(chart);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        final Fragment me = this;
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    ((resultat)getActivity()).scrollView.setVisibility(View.VISIBLE);
                    ((resultat)getActivity()).resoudre.setVisibility(View.VISIBLE);
                    ((resultat)getActivity()).removeFragment(me);
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START");
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END");
        chart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart long pressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart fling. VelocityX: " + velocityX + ", VelocityY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }



}