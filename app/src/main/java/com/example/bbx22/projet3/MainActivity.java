package com.example.bbx22.projet3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button resoudre = findViewById(R.id.resoudre);
        resoudre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Problème optimisé!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, resultat.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
