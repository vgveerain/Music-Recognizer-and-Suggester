package com.bumos.vgvee.musician;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    TextView infoTv;
    AppBarLayout applay;
    ArrayList<String> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        applay=findViewById(R.id.applay);
        infoTv=findViewById(R.id.infoTv);
        Intent intent = getIntent();
        String a = intent.getExtras().getString("artist");
        String t = intent.getExtras().getString("title");
        String al = intent.getExtras().getString("album");
        getSupportActionBar().setTitle(t);
        String s= "Artist: "+a+"\nTitle: "+t+"\nAlbum: "+al;
        infoTv.setText(s);
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
               
                try {
                    JSONObject j = new JSONObject(result);
                    JSONObject jalbum=j.getJSONObject("album");
                    JSONArray jimage=jalbum.getJSONArray("image");
                    for(int i=0;i<jimage.length();i++){
                        JSONObject im = jimage.getJSONObject(i);
                        String t = im.getString("#text");
                        arrayList.add(t);

                    }
                    Log.e("TAG Arraylist",arrayList.get(1));
                    ScrollingActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(arrayList.get(2)).into(iv);
                        }
                    });
//                    Picasso.get().load(arrayList.get(1)).into(iv);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
