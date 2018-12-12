package com.example.bbx22.projet3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bbx22.projet3.turbines.Turbine1;
import com.example.bbx22.projet3.turbines.Turbine2;
import com.example.bbx22.projet3.turbines.Turbine3;
import com.example.bbx22.projet3.turbines.Turbine4;
import com.example.bbx22.projet3.turbines.Turbine5;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends BaseActivity {

    private Button resoudre;
    private ImageView imageTurbine1;
    private ImageView imageTurbine2;
    private ImageView imageTurbine3;
    private ImageView imageTurbine4;
    private ImageView imageTurbine5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turbinesSelected = new ArrayList<>();


        //Selectionner les turbines par defaut

        for (int i = 5 ; i>=1 ; i--){
            turbinesSelected.add(i);
        }

        // Lier la vue Aux Objets
        imageTurbine1 = findViewById(R.id.imageTurbine1);
        imageTurbine2 = findViewById(R.id.imageTurbine2);
        imageTurbine3 = findViewById(R.id.imageTurbine3);
        imageTurbine4 = findViewById(R.id.imageTurbine4);
        imageTurbine5 = findViewById(R.id.imageTurbine5);

        textDebitTotal = findViewById(R.id.debitTotal);
        textElevationAmont = findViewById(R.id.elevationAmont);

        textQ1 = findViewById(R.id.Q1);
        //textQ1.setEnabled(false);
        textQ1.setMaxLines(1);
        textP1 = findViewById(R.id.P1);
        //textP1.setEnabled(false);
        textP1.setMaxLines(1);
        textHnette1 = findViewById(R.id.Hnette1);
        textHnette1.setMaxLines(1);
        //textHnette1.setEnabled(false);
        textDebitMax1 = findViewById(R.id.debitMax1);
        textDebitMax1.setMaxLines(1);

        textQ2 = findViewById(R.id.Q2);
        //textQ2.setEnabled(false);
        textQ2.setMaxLines(1);
        textP2 = findViewById(R.id.P2);
        //textP2.setEnabled(false);
        textP2.setMaxLines(1);
        textHnette2 = findViewById(R.id.Hnette2);
        //textP2.setEnabled(false);
        textP2.setMaxLines(1);
        textDebitMax2 = findViewById(R.id.debitMax2);
        textDebitMax2.setMaxLines(1);

        textQ3 = findViewById(R.id.Q3);
        textQ3.setMaxLines(1);
        textP3 = findViewById(R.id.P3);
        textHnette3 = findViewById(R.id.Hnette3);
        textDebitMax3 = findViewById(R.id.debitMax3);

        textQ4 = findViewById(R.id.Q4);
        textQ4.setMaxLines(1);
        textP4 = findViewById(R.id.P4);
        textHnette4 = findViewById(R.id.Hnette4);
        textDebitMax4 = findViewById(R.id.debitMax4);

        textQ5 = findViewById(R.id.Q5);
        textQ5.setMaxLines(1);
        textP5 = findViewById(R.id.P5);
        textHnette5 = findViewById(R.id.Hnette5);
        textDebitMax5 = findViewById(R.id.debitMax5);

        resoudre = findViewById(R.id.resoudre);


        alertDialogBuilder = new AlertDialog.Builder(this);

        disableTurbine1();
        disableTurbine2();
        disableTurbine3();
        disableTurbine4();
        disableTurbine5();

       resoudre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Problème optimisé!", Toast.LENGTH_SHORT).show();

                // animate turbines

                RotateAnimation animation = new RotateAnimation(
                        0, 720,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                animation.setDuration(1000);
                animation.setRepeatCount(Animation.RELATIVE_TO_SELF);

                if(turbinesSelected.indexOf(1) != -1)
                    imageTurbine1.startAnimation(animation);

                if(turbinesSelected.indexOf(2) != -1)
                    imageTurbine2.setAnimation(animation);

                if(turbinesSelected.indexOf(3) != -1)
                    imageTurbine3.setAnimation(animation);

                if(turbinesSelected.indexOf(4) != -1)
                    imageTurbine4.setAnimation(animation);

                if(turbinesSelected.indexOf(5) != -1)
                    imageTurbine5.setAnimation(animation);



                Qtot[0] = Double.parseDouble(textDebitTotal.getText().toString());
                Eam[0] = Double.parseDouble(textElevationAmont.getText().toString());


                ResoudreAlgo();

                DecimalFormat df = new DecimalFormat("0.0");
                df.setRoundingMode(RoundingMode.DOWN);

                //aficher les resultats
                textHnette1.setText(String.valueOf(df.format(hnette1)));
                textQ1.setText(String.valueOf(df.format(Q1)));
                textP1.setText(String.valueOf(df.format(puissance1[0])));

                textHnette2.setText(String.valueOf(df.format(hnette2)));
                textQ2.setText(String.valueOf(df.format(Q2)));
                textP2.setText(String.valueOf(df.format(puissance2[0])));

                textHnette3.setText(String.valueOf(df.format(hnette3)));
                textQ3.setText(String.valueOf(df.format(Q3)));
                textP3.setText(String.valueOf(df.format(puissance3[0])));

                textHnette4.setText(String.valueOf(df.format(hnette4)));
                textQ4.setText(String.valueOf(df.format(Q4)));
                textP4.setText(String.valueOf(df.format(puissance4[0])));

                textHnette5.setText(String.valueOf(df.format(hnette5)));
                textQ5.setText(String.valueOf(df.format(Q5)));
                textP5.setText(String.valueOf(df.format(puissance5[0])));

            }
        });


    }






    private void disableTurbine1(){

        imageTurbine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(turbinesSelected.indexOf(1) == -1)

                    alertDialogBuilder.setMessage("Voulez vous reactiver la turbine 1");
                else
                    alertDialogBuilder.setMessage("Voulez vous desactiver la turbine 1");

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(turbinesSelected.indexOf(1) == -1){

                            imageTurbine1.setImageResource(R.drawable.turbine);
                            turbinesSelected.add(1);
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                        }
                        else{
                            turbinesSelected.remove(turbinesSelected.indexOf(1));
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                            imageTurbine1.setImageResource(R.drawable.turbine_grey);

                            //Initialize text Values
                            textHnette1.setText("0.0");
                            textQ1.setText("0.0");
                            textP1.setText("0.0");

                        }

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDialogBuilder.show();
            }
        });

    }

    private void disableTurbine2(){

        imageTurbine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(turbinesSelected.indexOf(2) == -1)

                    alertDialogBuilder.setMessage("Voulez vous reactiver la turbine 2");
                else
                    alertDialogBuilder.setMessage("Voulez vous desactiver la turbine 2");

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(turbinesSelected.indexOf(2) == -1){

                            imageTurbine2.setImageResource(R.drawable.turbine);
                            turbinesSelected.add(2);
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                        }
                        else{
                            turbinesSelected.remove(turbinesSelected.indexOf(2));
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                            imageTurbine2.setImageResource(R.drawable.turbine_grey);

                            //Initialize text Values
                            textHnette2.setText("0.0");
                            textQ2.setText("0.0");
                            textP2.setText("0.0");

                        }


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDialogBuilder.show();
            }
        });

    }

    private void disableTurbine3(){

        imageTurbine3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(turbinesSelected.indexOf(3) == -1)

                    alertDialogBuilder.setMessage("Voulez vous reactiver la turbine 3");
                else
                    alertDialogBuilder.setMessage("Voulez vous desactiver la turbine 3");

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(turbinesSelected.indexOf(3) == -1){

                            imageTurbine3.setImageResource(R.drawable.turbine);
                            turbinesSelected.add(3);
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                        }
                        else{
                            turbinesSelected.remove(turbinesSelected.indexOf(3));
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                            imageTurbine3.setImageResource(R.drawable.turbine_grey);

                            //Initialize text Values
                            textHnette3.setText("0.0");
                            textQ3.setText("0.0");
                            textP3.setText("0.0");

                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDialogBuilder.show();
            }
        });

    }
    private void disableTurbine5(){

        imageTurbine5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(turbinesSelected.indexOf(5) == -1)

                    alertDialogBuilder.setMessage("Voulez vous reactiver la turbine 5");
                else
                    alertDialogBuilder.setMessage("Voulez vous desactiver la turbine 5");

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(turbinesSelected.indexOf(5) == -1){

                            imageTurbine5.setImageResource(R.drawable.turbine);
                            turbinesSelected.add(5);
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                        }
                        else{
                            turbinesSelected.remove(turbinesSelected.indexOf(5));
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                            imageTurbine5.setImageResource(R.drawable.turbine_grey);

                            //Initialize text Values
                            textHnette5.setText("0.0");
                            textQ5.setText("0.0");
                            textP5.setText("0.0");

                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDialogBuilder.show();
            }
        });

    }
    private void disableTurbine4(){

        imageTurbine4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(turbinesSelected.indexOf(4) == -1)

                    alertDialogBuilder.setMessage("Voulez vous reactiver la turbine 4");
                else
                    alertDialogBuilder.setMessage("Voulez vous desactiver la turbine 4");

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(turbinesSelected.indexOf(4) == -1){

                            imageTurbine4.setImageResource(R.drawable.turbine);
                            turbinesSelected.add(4);
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                        }
                        else{
                            turbinesSelected.remove(turbinesSelected.indexOf(4));
                            Collections.sort(turbinesSelected,Collections.<Integer>reverseOrder());
                            imageTurbine4.setImageResource(R.drawable.turbine_grey);

                            //Initialize text Values
                            textHnette4.setText("0.0");
                            textQ4.setText("0.0");
                            textP4.setText("0.0");

                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDialogBuilder.show();
            }
        });

    }



}
