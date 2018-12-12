package com.example.bbx22.projet3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button simpleData = findViewById(R.id.button);
        Button ExcelData = findViewById(R.id.button2);

        simpleData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        ExcelData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartActivity.this, resultat.class);
                startActivity(myIntent);
            }
        });
    }
}
