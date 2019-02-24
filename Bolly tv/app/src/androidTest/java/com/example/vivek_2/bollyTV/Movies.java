package com.example.vivek_2.bollyTV;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class Movies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        String name=getIntent().getStringExtra("name");

        Toolbar toolbar=(Toolbar)findViewById(R.id.movie_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name);





        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
    }
}
