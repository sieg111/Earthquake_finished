/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {
    List<EarthquakeFore> earthquakes;
//    public static final String LOG_TAG = EarthquakeActivity.class.getName();
private static final String SAMPLE_JSON_RESPONSE = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthquakeTask task = new EarthquakeTask();
        task.execute(SAMPLE_JSON_RESPONSE);

    }
    private class EarthquakeTask extends AsyncTask<String, Void, List<EarthquakeFore>> {

        @Override
        protected List<EarthquakeFore> doInBackground(String... urls) {
            //determine the first url is null or not.
            if(urls.length<1||urls[0]==null){
                return null;
            }
//             Perform the HTTP request for earthquake data and process the response.
            List<EarthquakeFore> earthquakeForeList=QueryUtils.fetchEarthquakeData(urls[0]);
            return earthquakeForeList;
        }

        @Override
        protected void onPostExecute(List<EarthquakeFore> earthquakeFores) {
            super.onPostExecute(earthquakeFores);
            updateUi(earthquakeFores);
        }
    }

    private void updateUi(List<EarthquakeFore> earthquakeFores) {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        final EarthquakeAdapter earthAdapter = new EarthquakeAdapter(this, earthquakeFores);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthAdapter);

        //item selected intent;
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthquakeFore currentEarthquake = earthAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                // 发送 Intent 以启动新活动
                startActivity(websiteIntent);
            }
        });

    }
}