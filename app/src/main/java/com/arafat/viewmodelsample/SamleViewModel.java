package com.arafat.viewmodelsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SamleViewModel extends AndroidViewModel {

    private final String apiUrl= "https://ifconfig.co/json";
    public String Country ="Bangladesh";
    public String ip = "192.168.0.1";
    public String loc="Banani , Dhaka";

    IpInfoModel infoModel =new IpInfoModel();

    /*public MutableLiveData<String> CountryName;
    public MutableLiveData<String> getCountryName(){

        if(CountryName == null){
            CountryName = new MutableLiveData<>();

            getIpInfo();
        }

        return CountryName;
    }*/

    public MutableLiveData<IpInfoModel> ipConfig;
    public MutableLiveData<IpInfoModel> getIpConfig(){

        if (ipConfig == null){
            ipConfig = new MutableLiveData<>();
            getIpInfo();
        }

        return ipConfig;
    }




    private void getIpInfo() {

        StringRequest request = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("res::",response);

                try {

                    JSONObject jsonObject =new JSONObject(response);


                    Country =jsonObject.getString("country");
                    ip =jsonObject.getString("ip");
                    loc =jsonObject.getString("latitude")+" , "+jsonObject.getString("longitude");

                    //CountryName.setValue(Country);

                   infoModel.setCountryName(Country);
                   infoModel.setIp(ip);
                   infoModel.setLoc(loc);

                   ipConfig.setValue(infoModel);



                }catch (Exception e){

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
        requestQueue.add(request);
    }


    public SamleViewModel(@NonNull Application application) {
        super(application);

    }

}
