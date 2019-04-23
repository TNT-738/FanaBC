package com.news.fanabc.Utils;

import android.content.Context;
import android.view.textclassifier.TextLinks;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NetworkManager {


    private static final int TIMEOUT_MS = 10000;
    private RequestQueue requestQueue;

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    private ResponseListener responseListener;
    public NetworkManager(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface ResponseListener {
        void onSuccessfullResponse(String Response);

        void onErrorResponse(String ErrorMessage);
    }
    public void HttpGetRequest(String Url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null)responseListener.onSuccessfullResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onErrorResponse(error.getMessage());
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.requestQueue.add(stringRequest);
    }


}
