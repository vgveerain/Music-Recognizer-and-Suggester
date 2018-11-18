package com.bumos.vgvee.musician;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.acrcloud.rec.sdk.ACRCloudClient;
import com.acrcloud.rec.sdk.ACRCloudConfig;
import com.acrcloud.rec.sdk.IACRCloudListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActual extends AppCompatActivity implements IACRCloudListener {

    private ACRCloudClient mClient;
    private ACRCloudConfig mConfig;

//    private APIresponse apiResponse;
    private APIresponse3 apIresponse3;
//    private ArrayList<APIresponse3> apIresponse3;
    private APIresponse2 apIresponse2 = new APIresponse2();
    private ArrayList<String> resultsss;
    private ArrayList<data> datasss  = new ArrayList<>();

    private boolean mProcessing = false;
    private boolean initState = false;

    private String path = "";
    private String recommendations = "";

    private long startTime = 0;
    private long stopTime = 0;

    EditText searchEditText;
    ImageButton searchButton;
    ImageButton startButton;

    TextView recommended;

    String recommende = "[\"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[855327],\\\"data\\\":[[\\\"SOFUYML12A6D4FCA77\\\",\\\"Wanderlust\\\",\\\"All That Remains Reloaded\\\",\\\"Fozzy\\\",2005]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[902471],\\\"data\\\":[[\\\"SOJSTSK12A6D4FCA69\\\",\\\"Nameless Faceless\\\",\\\"All That Remains Reloaded\\\",\\\"Fozzy\\\",2005]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[902462],\\\"data\\\":[[\\\"SOAVJFY12AF72A64CC\\\",\\\"It's A Lie\\\",\\\"All That Remains Reloaded\\\",\\\"Fozzy\\\",2005]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[869625],\\\"data\\\":[[\\\"SOLMXGX12AF72A7D85\\\",\\\"Tribute\\\",\\\"Proud Like A God\\\",\\\"Guano Apes\\\",1997]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[763622],\\\"data\\\":[[\\\"SOXOIGJ12A8C13A286\\\",\\\"Nur die Stimmen\\\",\\\"Live\\\",\\\"Xavier Naidoo\\\",1999]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[224960],\\\"data\\\":[[\\\"SOXCYWI12AB0187EB1\\\",\\\"Historia de Amor\\\",\\\"Arcangel\\\",\\\"Arcangel\\\",0]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[389900],\\\"data\\\":[[\\\"SOCIPQI12A67020958\\\",\\\"Death of Seasons\\\",\\\"Sing The Sorrow\\\",\\\"AFI\\\",2003]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[402731],\\\"data\\\":[[\\\"SOAFIAT12AB018D816\\\",\\\"Sexcrime (Nineteen Eighty-Four)\\\",\\\"Playlist: 80s Hits\\\",\\\"Eurythmics\\\",1984]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[500990],\\\"data\\\":[[\\\"SOOTJOY12A58A7CA59\\\",\\\"No Good (Attack The Radical)  (LP Version)\\\",\\\"Vulgar Display Of Power\\\",\\\"Pantera\\\",1992]]}\", \"{\\\"columns\\\":[\\\"song_id\\\",\\\"title\\\",\\\"release\\\",\\\"artist_name\\\",\\\"year\\\"],\\\"index\\\":[393119],\\\"data\\\":[[\\\"SOHXOFF12A58A76A22\\\",\\\"Ready Or Not (Rock Solid Album Version)\\\",\\\"Rock Solid Absolutely Live\\\",\\\"DeGarmo & Key\\\",0]]}\"]";

    public MainActual() {
    }
//    TextView previouslyHeard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_actual);

        recommended = findViewById(R.id.recom);
//        previouslyHeard = findViewById(R.id.prevHeard);

        path = Environment.getExternalStorageDirectory().toString()
                + "/acrcloud/model";

        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //Add code for UI elements
//        searchEditText = findViewById(R.id.search_edit_text);
//        searchButton = findViewById(R.id.search_button_1);
        startButton = findViewById(R.id.start1);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate));
                start();
            }
        });

        this.mConfig = new ACRCloudConfig();
        this.mConfig.acrcloudListener = this;

        // If you implement IACRCloudResultWithAudioListener and override "onResult(ACRCloudResult result)", you can get the Audio data.
        //this.mConfig.acrcloudResultWithAudioListener = this;

        this.mConfig.context = this;
        this.mConfig.host = "identify-us-west-2.acrcloud.com";
        this.mConfig.dbPath = path; // offline db path, you can change it with other path which this app can access.
        this.mConfig.accessKey = "61276e73c10dcacf28104ba76d0fb4d8";
        this.mConfig.accessSecret = "ugSMHogitcimT3MPUlQ0gXbfbkvAwSWQGYMuBpzI";
        this.mConfig.protocol = ACRCloudConfig.ACRCloudNetworkProtocol.PROTOCOL_HTTP; // PROTOCOL_HTTPS
        this.mConfig.reqMode = ACRCloudConfig.ACRCloudRecMode.REC_MODE_REMOTE;
        //this.mConfig.reqMode = ACRCloudConfig.ACRCloudRecMode.REC_MODE_LOCAL;
        //this.mConfig.reqMode = ACRCloudConfig.ACRCloudRecMode.REC_MODE_BOTH;

        this.mClient = new ACRCloudClient();
        // If reqMode is REC_MODE_LOCAL or REC_MODE_BOTH,
        // the function initWithConfig is used to load offline db, and it may cost long time.
        this.initState = this.mClient.initWithConfig(this.mConfig);
        if (this.initState) {
            this.mClient.startPreRecord(3000); //start prerecord, you can call "this.mClient.stopPreRecord()" to stop prerecord.
        }

//        try {
//            // Create a URL for the desired page
//            URL url = new URL("https://raw.githubusercontent.com/vgveerain/Music-Recognizer-and-Suggester/master/recommendations.txt");
//
//            // Read all the text returned by the server
//            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//            String str;
//            while ((str = in.readLine()) != null) {
//                // str is one line of text; readLine() strips the newline character(s)
//                recommendations = str;
//            }
//            in.close();
//        } catch (MalformedURLException e) {
//        } catch (IOException e) {
//        }

        Gson gson = new Gson();
//        apIresponse3.APIresponse3 = gson.fromJson(recommende, APIresponse3.class);
//        resultsss = gson.fromJson(recommende, APIresponse3.class);
        resultsss = gson.fromJson(recommende, new TypeToken<List<String>>(){}.getType());
        Log.e("TAG",resultsss.get(0));

//        for(String x : apIresponse3.APIresponse3){
//            apIresponse2.add(gson.fromJson(x,APIresponse2.class));
//        }
        for(String x : resultsss){
//            data y = gson.fromJson(x, data.class);
//            datasss.add(y);
            try {
                JSONObject j = new JSONObject(x);
                JSONArray j1 = j.getJSONArray("data");
                JSONArray j2 = new JSONArray();
                for(int i=0;i<1;i++){
                    j2 = (JSONArray) j1.get(i);
                }
                ArrayList<String> y = new ArrayList<>();
                y.add((String) j2.get(0));
                y.add((String) j2.get(1));
                y.add((String) j2.get(2));
                y.add((String) j2.get(3));
                data z = new data(y);
                datasss.add(z);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        previouslyHeard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActual.this, com.bumos.vgvee.musician.previouslyHeard.class);
////                intent.putExtra("data", apIresponse2);
//            }
//        });

        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apIresponse2.data = datasss;
                Intent intent = new Intent(MainActual.this, recommendedActivity.class);
                intent.putExtra("data", apIresponse2);
                startActivity(intent);
            }
        });
    }

    public void start() {
        if (!this.initState) {
            Toast.makeText(this, "init error", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!mProcessing) {
            mProcessing = true;
//            mVolume.setText("");
//            mResult.setText("");
            if (this.mClient == null || !this.mClient.startRecognize()) {
                mProcessing = false;
//                mResult.setText("start error!");
            }
            startTime = System.currentTimeMillis();
        }
    }

    protected void stop() {
        if (mProcessing && this.mClient != null) {
            this.mClient.stopRecordToRecognize();
        }
        mProcessing = false;

        stopTime = System.currentTimeMillis();
    }

    protected void cancel() {
        if (mProcessing && this.mClient != null) {
            mProcessing = false;
            this.mClient.cancel();
//            tv_time.setText("");
//            mResult.setText("");
        }
    }


    @Override
    public void onResult(String result) {
        if (this.mClient != null) {
            this.mClient.cancel();
            mProcessing = false;
        }

        String tres = "\n";

//        Gson gson = new Gson();
//        apiResponse = gson.fromJson(result, APIresponse.class);
//
//        if(apiResponse.status.code == 0) {
//            Log.e("TAG", apiResponse.status.msg);
//            Intent intent = new Intent(this, resultActivity.class);
//            intent.putExtra("apiresponse", apiResponse);
//            startActivity(intent);
//        }else{
//            Toast.makeText(this, "No Result Found, Retry?", Toast.LENGTH_SHORT).show();
//        }
        startButton.clearAnimation();
        String a = new String();
        String t = new String();
        String al = new String();

        try {
            JSONObject j = new JSONObject(result);
            Log.e("TAG ACR",result);
            JSONObject j1 = j.getJSONObject("status");
            int j2 = j1.getInt("code");
            if(j2 == 0){
                JSONObject metadata = j.getJSONObject("metadata");
                //
                if (metadata.has("humming")) {
                    JSONArray hummings = metadata.getJSONArray("humming");
                    for(int i=0; i<hummings.length(); i++) {
                        JSONObject tt = (JSONObject) hummings.get(i);
                        String title;
                        title = tt.getString("title");
                        JSONArray artistt = tt.getJSONArray("artists");
                        JSONObject art = (JSONObject) artistt.get(0);
                        String artist;
                        artist = art.getString("name");
                        tres = tres + (i+1) + ".  " + title + "\n";
                    }
                }
                if (metadata.has("music")) {
                    JSONArray musics = metadata.getJSONArray("music");
                    for(int i=0; i<musics.length(); i++) {
                        JSONObject tt = (JSONObject) musics.get(i);
                        String title = tt.getString("title");
                        JSONArray artistt = tt.getJSONArray("artists");
                        JSONObject art = (JSONObject) artistt.get(0);
                        String artist = art.getString("name");
                        JSONObject alb = tt.getJSONObject("album");
                        al = alb.getString("name");
                        a = artist;
                        t = title;
                        tres = tres + (i+1) + ".  Title: " + title + "    Artist: " + artist + "\n";
                    }
                }
                if (metadata.has("streams")) {
                    JSONArray musics = metadata.getJSONArray("streams");
                    for(int i=0; i<musics.length(); i++) {
                        JSONObject tt = (JSONObject) musics.get(i);
                        String title = tt.getString("title");
                        String channelId = tt.getString("channel_id");
                        tres = tres + (i+1) + ".  Title: " + title + "    Channel Id: " + channelId + "\n";
                    }
                }
                if (metadata.has("custom_files")) {
                    JSONArray musics = metadata.getJSONArray("custom_files");
                    for(int i=0; i<musics.length(); i++) {
                        JSONObject tt = (JSONObject) musics.get(i);
                        String title = tt.getString("title");
                        tres = tres + (i+1) + ".  Title: " + title + "\n";
                    }
                }
                tres = tres + "\n\n" + result;
            }else{
                tres = result;
            }
        } catch (JSONException e) {
            tres = result;
            e.printStackTrace();
        }

        Intent intent = new Intent(this, ScrollingActivity.class);
        intent.putExtra("title", t);
        intent.putExtra("artist", a);
        intent.putExtra("album", al);
        startActivity(intent);

        Log.e("TAG", tres);
    }

    @Override
    public void onVolumeChanged(double v) {
        long time = (System.currentTimeMillis() - startTime) / 1000;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "release");
        if (this.mClient != null) {
            this.mClient.release();
            this.initState = false;
            this.mClient = null;
        }
    }
}
