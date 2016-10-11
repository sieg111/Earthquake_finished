package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.List;

import android.support.v4.content.ContextCompat;
/**
 * Created by Administrator on 9/29/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<EarthquakeFore> {
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Activity context, List<EarthquakeFore> earthquakeFore) {
        super(context, 0, earthquakeFore);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_earth_item, parent, false);
        }
        //   Get the {@link EarthquakeFore} object located at this position in the list
        EarthquakeFore currentearthquakefore= getItem(position);

        // 使用视图 ID magnitude 找到 TextView
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.earth_mag);
        // 格式化震级使其显示一位小数
        String formattedMagnitude = formatMagnitude(currentearthquakefore.getMagitude());
        magnitudeView.setText(formattedMagnitude);

        // 为震级圆圈设置正确的背景颜色。
        // 从 TextView 获取背景，该背景是一个 GradientDrawable。
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // 根据当前的地震震级获取相应的背景颜色
        int magnitudeColor = getMagnitudeColor(currentearthquakefore.getMagitude());

        // 设置震级圆圈的颜色
        magnitudeCircle.setColor(magnitudeColor);


        String originalLocation = currentearthquakefore.getLocation();
        String primaryLocation;
        String locationOffset;
//      spilt the string and separate the location.
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        //setTextView to location.
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);


        TextView timeTextView = (TextView) listItemView.findViewById(R.id.earth_time);
        timeTextView.setText(currentearthquakefore.getmOccurtime());


        return listItemView;
    }

    private int getMagnitudeColor(Double magitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magitude);

        switch (magnitudeFloor)
        {
            case 0:
            case 1:  magnitudeColorResourceId = R.color.magnitude1; break;
            case 2:  magnitudeColorResourceId = R.color.magnitude2; break;
            case 3:  magnitudeColorResourceId = R.color.magnitude3; break;
            case 4:  magnitudeColorResourceId = R.color.magnitude4; break;
            case 5:  magnitudeColorResourceId = R.color.magnitude5; break;
            case 6:  magnitudeColorResourceId = R.color.magnitude6; break;
            case 7:  magnitudeColorResourceId = R.color.magnitude7; break;
            case 8:  magnitudeColorResourceId = R.color.magnitude8; break;
            case 9:  magnitudeColorResourceId = R.color.magnitude9; break;
            default: magnitudeColorResourceId = R.color.magnitude10plus; break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);

    }

    //
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}