package com.example.bbx22.projet3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class resultat extends BaseActivity {

    public Button resoudre;
    private Fragment fragmentBar;
    private Button dataP1;
    private Button dataP2;
    private Button dataP3;
    private Button dataP4;
    private Button dataP5;
    public ScrollView scrollView;
    private ImageView imageTurbine1;
    private ImageView imageTurbine2;
    private ImageView imageTurbine3;
    private ImageView imageTurbine4;
    private ImageView imageTurbine5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat);

        turbinesSelected = new ArrayList<>();

        scrollView = findViewById(R.id.scrollView);

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


        textDebitMax1 = findViewById(R.id.debitMax1);
        textDebitMax1.setMaxLines(1);
        textDebitMax2 = findViewById(R.id.debitMax2);
        textDebitMax2.setMaxLines(1);
        textDebitMax3 = findViewById(R.id.debitMax3);
        textDebitMax4 = findViewById(R.id.debitMax4);
        textDebitMax5 = findViewById(R.id.debitMax5);

        resoudre = findViewById(R.id.resoudre);

        dataP1 = findViewById(R.id.visualizeP1Btn);
        dataP2 = findViewById(R.id.visualizeP2Btn);
        dataP3 = findViewById(R.id.visualizeP3Btn);
        dataP4 = findViewById(R.id.visualizeP4Btn);
        dataP5 = findViewById(R.id.visualizeP5Btn);


        alertDialogBuilder = new AlertDialog.Builder(this);

        disableTurbine1();
        disableTurbine2();
        disableTurbine3();
        disableTurbine4();
        disableTurbine5();



        resoudre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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



                 Qtot = new Double[]{548.958435058593, 545.389892578125, 546.872924804687, 546.872924804687, 546.190734863281, 550.084838867187, 549.554992675781, 541.214233398437, 547.211791992187, 549.072387695312, 549.910766601562, 547.921569824218, 545.982849121093, 544.9501953125, 543.387329101562, 544.986877441406, 544.59326171875, 545.33056640625, 547.237243652343, 546.969116210937};
                 Eam = new Double[]{172.110000610351, 172.110000610351, 172.110000610351, 172.110000610351, 172.119995117187, 172.119995117187, 172.110000610351, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.130004882812, 172.130004882812, 172.130004882812, 172.119995117187, 172.130004882812};





                ResoudreAlgo();

                dataP1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        scrollView.setVisibility(View.GONE);
                        resoudre.setVisibility(View.GONE);
                        showBarFragment(1);
                    }
                });
                dataP2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        scrollView.setVisibility(View.GONE);
                        resoudre.setVisibility(View.GONE);
                        showBarFragment(2);
                    }
                });
                dataP3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        scrollView.setVisibility(View.GONE);
                        resoudre.setVisibility(View.GONE);
                        showBarFragment(3);
                    }
                });
                dataP4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        scrollView.setVisibility(View.GONE);
                        resoudre.setVisibility(View.GONE);
                        showBarFragment(4);
                    }
                });
                dataP5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        scrollView.setVisibility(View.GONE);
                        resoudre.setVisibility(View.GONE);
                        showBarFragment(5);
                    }
                });


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

    private void showBarFragment(int indice){
         this.fragmentBar = BarChartFrag.newInstance(String.valueOf(indice));
        this.startTransactionFragment(this.fragmentBar);
    }
    public void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }


    public void removeFragment(Fragment fragment){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scrollView.setVisibility(View.VISIBLE);
        resoudre.setVisibility(View.VISIBLE);

    }
}

