package com.example.android.quakereport;

/**
 * Created by Administrator on 9/29/2016.
 */
public class EarthquakeFore {

    private  double mMagnitude;
    private  String mLocation;
    private  String mOccurtime;
    private String  mUrl;

    public EarthquakeFore(Double Magnitude, String Location, String Occurtime,String url){
        mMagnitude=Magnitude;
        mLocation=Location;
        mOccurtime=Occurtime;
        mUrl=url;
    }

    public Double getMagitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public String getmOccurtime(){
        return mOccurtime;
    }
    public String getmUrl(){
        return mUrl;
    }
}
