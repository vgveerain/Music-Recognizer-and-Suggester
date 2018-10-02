package com.bumos.vgvee.musician;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rapidapi.rapidconnect.Argument;
import com.rapidapi.rapidconnect.RapidApiConnect;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class resultActivity extends AppCompatActivity {

    RelativeLayout wrapper;
    ImageView resultAlbumArt;
    TextView textTitle, textArtist, textLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String a = intent.getExtras().getString("artist");
        String t = intent.getExtras().getString("title");
        String al = intent.getExtras().getString("album");

        wrapper = findViewById(R.id.albumArtWrapper);
        resultAlbumArt = findViewById(R.id.resultAlbumArt);
        textTitle = findViewById(R.id.textTitle);
        textArtist = findViewById(R.id.textArtist);
        textLyrics = findViewById(R.id.textLyrics);

        textTitle.setText(t);
        textArtist.setText(a);

        RapidApiConnect connect = new RapidApiConnect("default-application_5b597296e4b093a6a7fb9226", "2fb6a690-7756-4252-967f-e88c0c44cbff");

        Map<String, Argument> body = new HashMap<String, Argument>();

        body.put("artist", new Argument("data", a));
        body.put("album", new Argument("data", al));
        body.put("apiKey", new Argument("data", "40b14e10f3a1b73ba2384311055fe745"));


        try {
            Map<String, Object> response = connect.call("LastFm", "getAlbumInfo", body);
            if(response.get("success") != null) {
                Log.e("TAG2", String.valueOf(new JSONObject(response)));

            } else{
                Log.e("TAG2", "Nahi Degi.....API response");

            }
        } catch(Exception e){

        }
    }
}
