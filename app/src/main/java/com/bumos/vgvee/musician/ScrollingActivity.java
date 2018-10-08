package com.bumos.vgvee.musician;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Utf8;

public class ScrollingActivity extends AppCompatActivity {

    ImageView iv ;
    ArrayList<String> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String a = intent.getExtras().getString("artist");
        String t = intent.getExtras().getString("title");
        String al = intent.getExtras().getString("album");
        Log.e("TAG",al+a);
        try {
            a=URLEncoder.encode(a,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            al=URLEncoder.encode(al,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        iv=findViewById(R.id.iv);
        String url="http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=515807b01dbb5295ac9323109168861f&artist="+a+"&album="+al+"&format=json";
        NetworkCall(url);

    }
    private void NetworkCall(String url){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e("TAG",result);
//                Gson gson=new Gson();
//                ImageResponse imageResponse=gson.fromJson(result,ImageResponse.class);
////                Log.e("TAG",imageResponse.getAlbum().toString());
//
//                ArrayList<image> images=imageResponse.album.getImage();
//                Log.e("TAG",images.get(1).getText());
                try {
                    JSONObject j = new JSONObject(result);
                    JSONArray jsonArray=j.getJSONArray("image");
                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject object=jsonArray.getJSONObject(i);
                        arrayList.add(object.getString("#text"));

                        Log.e("TAG3",arrayList.get(1));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
