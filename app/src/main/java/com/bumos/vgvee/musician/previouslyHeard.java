package com.bumos.vgvee.musician;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class previouslyHeard extends AppCompatActivity {

    RecyclerView recomRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previously_heard);

        recomRV = findViewById(R.id.recomRV);
    }
}
