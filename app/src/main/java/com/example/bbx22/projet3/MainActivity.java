package com.example.bbx22.projet3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bbx22.projet3.turbines.Turbine1;
import com.example.bbx22.projet3.turbines.Turbine2;
import com.example.bbx22.projet3.turbines.Turbine3;
import com.example.bbx22.projet3.turbines.Turbine4;
import com.example.bbx22.projet3.turbines.Turbine5;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    final int NBLIGNES = 200;

    final int DEBIT_TOTAL = 555;
    final int DEBIT_MAX = 160;

    List<Integer> Sn = new ArrayList<>();
    List<Integer> Xn = new ArrayList<>();
    ImageView imageTurbine1;
    ImageView imageTurbine2;
    ImageView imageTurbine3;
    ImageView imageTurbine4;
    ImageView imageTurbine5;

    double Qtot[]=  {548.958435058593, 545.389892578125, 546.872924804687, 546.872924804687, 546.190734863281, 550.084838867187, 549.554992675781, 541.214233398437, 547.211791992187, 549.072387695312, 549.910766601562, 547.921569824218, 545.982849121093, 544.9501953125, 543.387329101562, 544.986877441406, 544.59326171875, 545.33056640625, 547.237243652343, 546.969116210937};
    double Eam[] =  {172.110000610351, 172.110000610351, 172.110000610351, 172.110000610351, 172.119995117187, 172.119995117187, 172.110000610351, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.130004882812, 172.130004882812, 172.130004882812, 172.119995117187, 172.130004882812};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageTurbine1 = findViewById(R.id.imageTurbine1);
        imageTurbine2 = findViewById(R.id.imageTurbine2);
        imageTurbine3 = findViewById(R.id.imageTurbine3);
        imageTurbine4 = findViewById(R.id.imageTurbine4);
        imageTurbine5 = findViewById(R.id.imageTurbine5);

        Button resoudre = findViewById(R.id.resoudre);
        resoudre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(MainActivity.this, "Problème optimisé!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, resultat.class);
                MainActivity.this.startActivity(myIntent);*/

                RotateAnimation animation = new RotateAnimation(
                        0, 360,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                animation.setDuration(1000);
                animation.setRepeatCount(Animation.INFINITE);

                imageTurbine1.startAnimation(animation);

                imageTurbine2.setAnimation(animation);
                imageTurbine3.setAnimation(animation);
                imageTurbine4.setAnimation(animation);
                imageTurbine5.setAnimation(animation);
            }
        });


    }

    public void Resoudre() {
        Turbine1 turbine1 = new Turbine1();
        Turbine2 turbine2 = new Turbine2();
        Turbine3 turbine3 = new Turbine3();
        Turbine4 turbine4 = new Turbine4();
        Turbine5 turbine5 = new Turbine5();


        // remplir Sn[]
        for (int i = 0; i <= DEBIT_TOTAL / 5; i++) {
            Sn.add(5 * i);

        }

        // remplir Xn[]
        for (int i = 0; i <= DEBIT_MAX / 5; i++)    // 160/5=32
        {
            Xn.add(5 * i);

        }


        // debut de la programmation dynamique



        for (int n = 0; n < Qtot.length; n++) {

            for (int indice_N = 5; indice_N >= 1; indice_N--) {

                switch (indice_N) {

                    case 5:

                        // Calcul de gn
                        for (Integer aSn1 : Sn) {

                            turbine5.puissance.add(turbine5.P5(aSn1, turbine5.CalculHauteurDeChuteNette(Eam[n], aSn1, Qtot[n])));


                        }
                        turbine5.X_n = cloneListInteger(Sn);
                        turbine5.F_N = cloneListDouble(turbine5.puissance);

                        break;

                    case 4:

                        // Calcul de gn
                        for (Integer aSn : Sn) {

                            turbine4.puissance.add(turbine4.P4(aSn, turbine4.CalculHauteurDeChuteNette(Eam[n], aSn, Qtot[n])));

                        }

                        // calcul de Fn
                        for (int i = 0; i < Sn.size(); i++) {

                            double max = 0.0;
                            List<Double> FN_COlONNES = new ArrayList<>();
                            for (int j = 0; j < Xn.size(); j++) {

                                if (j > i) {

                                    FN_COlONNES.add(0.0);
                                } else {

                                    FN_COlONNES.add(turbine4.puissance.get(j) + turbine5.F_N.get(i - j));


                                    if (FN_COlONNES.get(j) > max) {
                                        turbine4.X_n.add(Xn.get(j));
                                        max = FN_COlONNES.get(j);
                                        turbine4.F_N.add(max);
                                    }
                                }


                            }

                            turbine4.FN.add(FN_COlONNES);
                        }


                        break;

                    case 3:

                        // Calcul de gn
                        for (Integer aSn : Sn) {

                            turbine3.puissance.add(turbine3.P3(aSn, turbine3.CalculHauteurDeChuteNette(Eam[n], aSn, Qtot[n])));

                        }

                        // calcul de Fn
                        for (int i = 0; i < Sn.size(); i++) {

                            double max = 0.0;
                            List<Double> FN_COlONNES = new ArrayList<>();
                            for (int j = 0; j < Xn.size(); j++) {

                                if (j > i) {

                                    FN_COlONNES.add(0.0);
                                } else {

                                    FN_COlONNES.add(turbine3.puissance.get(j) + turbine4.F_N.get(i - j));


                                    if (FN_COlONNES.get(j) > max) {
                                        turbine3.X_n.add(Xn.get(j));
                                        max = FN_COlONNES.get(j);
                                        turbine3.F_N.add(max);
                                    }
                                }


                            }

                            turbine3.FN.add(FN_COlONNES);
                        }

                        break;

                    case 2:

                        // Calcul de gn
                        for (Integer aSn : Sn) {

                            turbine2.puissance.add(turbine2.P2(aSn, turbine3.CalculHauteurDeChuteNette(Eam[n], aSn, Qtot[n])));

                        }

                        // calcul de Fn
                        for (int i = 0; i < Sn.size(); i++) {

                            double max = 0.0;
                            List<Double> FN_COlONNES = new ArrayList<>();
                            for (int j = 0; j < Xn.size(); j++) {

                                if (j > i) {

                                    FN_COlONNES.add(0.0);
                                } else {

                                    FN_COlONNES.add(turbine2.puissance.get(j) + turbine3.F_N.get(i - j));


                                    if (FN_COlONNES.get(j) > max) {
                                        turbine2.X_n.add(Xn.get(j));
                                        max = FN_COlONNES.get(j);
                                        turbine2.F_N.add(max);
                                    }
                                }

                            }

                            turbine2.FN.add(FN_COlONNES);
                        }
                        break;

                    case 1:

                        // Calcul de gn
                        for (Integer aSn : Sn) {

                            turbine1.puissance.add(turbine1.P1(aSn, turbine3.CalculHauteurDeChuteNette(Eam[n], aSn, Qtot[n])));

                        }

                        double max = 0.0;
                        List<Double> FN_COlONNES = new ArrayList<>();
                        for (int j = 0; j < Xn.size(); j++) {


                            FN_COlONNES.add(turbine1.puissance.get(j) + turbine2.F_N.get(Sn.size() - j));


                            if (FN_COlONNES.get(j) > max) {
                                turbine1.X_n.add(Xn.get(j));
                                max = FN_COlONNES.get(j);
                                turbine1.F_N.add(max);
                            }


                        }
                        turbine1.FN.add(FN_COlONNES);

                        break;
                }
            }

            // Algorithme pour recuperer les valeurs à turbiner
            int k = n + 1;
            System.out.println("Ligne " + k + "\n **************************************************\n*****************************************");
            int rang = 0;
            for (int indice_N = 1; indice_N <= 5; indice_N++) {


                switch (indice_N) {


                    case 1:
                        double puissance1 = turbine1.P1(turbine1.X_n.get(turbine1.X_n.size() - 1), turbine1.CalculHauteurDeChuteNette(Eam[n], turbine1.X_n.get(turbine1.X_n.size() - 1), Qtot[n]));
                        System.out.println("La turbine " + indice_N + " doit turbiner un débit de " + turbine1.X_n.get(turbine1.X_n.size() - 1) + " m^3/s" + " et obtient une puissance de " + puissance1 + " MW");

                        rang = Sn.get(Sn.size() - 1) - turbine1.X_n.get(turbine1.X_n.size() - 1);
                        break;
                    case 2:
                        double debit2 = turbine2.X_n.get(rang / 5);
                        double puissance2 = turbine2.P2(debit2, turbine2.CalculHauteurDeChuteNette(Eam[n], debit2, Qtot[n]));
                        System.out.println("La turbine " + indice_N + " doit turbiner un débit de " + turbine2.X_n.get(rang / 5) + " m^3/s" + " et obtient une puissance de " + puissance2 + " MW");
                        rang = Sn.get(rang / 5) - turbine2.X_n.get(rang / 5);
                        break;
                    case 3:
                        double debit3 = turbine3.X_n.get(rang / 5);
                        double puissance3 = turbine3.P3(debit3, turbine3.CalculHauteurDeChuteNette(Eam[n], debit3, Qtot[n]));
                        System.out.println("La turbine " + indice_N + " doit turbiner un débit de " + turbine3.X_n.get(rang / 5) + " m^3/s" + " et obtient une puissance de " + puissance3 + " MW");
                        rang = Sn.get(rang / 5) - turbine3.X_n.get(rang / 5);
                        break;
                    case 4:
                        double debit4 = turbine4.X_n.get(rang / 5);
                        double puissance4 = turbine4.P4(debit4, turbine4.CalculHauteurDeChuteNette(Eam[n], debit4, Qtot[n]));
                        System.out.println("La turbine " + indice_N + " doit turbiner un débit de " + turbine4.X_n.get(rang / 5) + " m^3/s" + " et obtient une puissance de " + puissance4 + " MW");
                        rang = Sn.get(rang / 5) - turbine4.X_n.get(rang / 5);
                        break;
                    case 5:
                        double debit5 = turbine5.X_n.get(rang / 5);
                        double puissance5 = turbine5.P5(debit5, turbine5.CalculHauteurDeChuteNette(Eam[n], debit5, Qtot[n]));
                        System.out.println("La turbine " + indice_N + " doit turbiner un débit de " + turbine5.X_n.get(rang / 5) + " m^3/s" + " et obtient une puissance de " + puissance5 + " MW");
                        rang = Sn.get(rang / 5) - turbine5.X_n.get(rang / 5);

                        break;
                }

            }

            turbine1.F_N = new ArrayList<>();
            turbine1.FN = new ArrayList<>();
            turbine1.puissance = new ArrayList<>();
            turbine1.X_n = new ArrayList<>();

            turbine2.F_N = new ArrayList<>();
            turbine2.FN = new ArrayList<>();
            turbine2.puissance = new ArrayList<>();
            turbine2.X_n = new ArrayList<>();

            turbine3.F_N = new ArrayList<>();
            turbine3.FN = new ArrayList<>();
            turbine3.puissance = new ArrayList<>();
            turbine3.X_n = new ArrayList<>();

            turbine4.F_N = new ArrayList<>();
            turbine4.FN = new ArrayList<>();
            turbine4.puissance = new ArrayList<>();
            turbine4.X_n = new ArrayList<>();

            turbine5.F_N = new ArrayList<>();
            turbine5.FN = new ArrayList<>();
            turbine5.puissance = new ArrayList<>();
            turbine5.X_n = new ArrayList<>();
        }

    }

    public static List<Integer> cloneListInteger(List<Integer> list) {
        List<Integer> clone = new ArrayList<Integer>(list.size());
        for (Integer item : list){
            clone.add(new Integer(item));
        }
        return clone;
    }

    public static List<Double> cloneListDouble(List<Double> list) {
        List<Double> clone = new ArrayList<Double>(list.size());
        for (Double item : list){
            clone.add(new Double(item));
        }
        return clone;
    }

}
