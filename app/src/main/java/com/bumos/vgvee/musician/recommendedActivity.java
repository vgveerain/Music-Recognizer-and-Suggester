package com.bumos.vgvee.musician;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class recommendedActivity extends AppCompatActivity {

    APIresponse2 apIresponse2;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);

        apIresponse2 = getIntent().getExtras().getParcelable("data");
        recyclerView = findViewById(R.id.recomRV);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new myAdapter(this,apIresponse2.data));
    }
}
