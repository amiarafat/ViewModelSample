package com.arafat.viewmodelsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    String apiUrl ="https://ifconfig.co/json";

    TextView tv1,tv2, tv3;
    String ip, Country,latlng;

    SamleViewModel model;
    String res="";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context =getApplicationContext();

        tv1 =findViewById(R.id.textView);
        tv2 =findViewById(R.id.textView2);
        tv3 =findViewById(R.id.textView3);

        //getConfigValue();
        model = ViewModelProviders.of(MainActivity.this).get(SamleViewModel.class);

/*
        final Observer<String> countryOb = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                tv2.setText("Country: "+s);
                Log.d("data::",s);
            }
        };
        model.getCountryName().observe(this, countryOb);

*/

        final Observer<IpInfoModel> ipInfoOb = new Observer<IpInfoModel>() {
            @Override
            public void onChanged(@Nullable IpInfoModel ipInfoModel) {

                Log.d("data::",ipInfoModel.getCountryName()+"---"+ipInfoModel.getIp());

                tv1.setText("IP: "+ipInfoModel.getIp());
                tv2.setText("Country: "+ipInfoModel.getCountryName());
                tv3.setText("Location: : "+ipInfoModel.getLoc());

            }
        };
        model.getIpConfig().observe(this,ipInfoOb);

    }


}
