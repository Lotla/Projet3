package com.example.bbx22.projet3;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bbx22.projet3.turbines.Turbine;
import com.example.bbx22.projet3.turbines.Turbine1;
import com.example.bbx22.projet3.turbines.Turbine2;
import com.example.bbx22.projet3.turbines.Turbine3;
import com.example.bbx22.projet3.turbines.Turbine4;
import com.example.bbx22.projet3.turbines.Turbine5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class BaseActivity extends AppCompatActivity {

    final int NBLIGNES = 200;


    AlertDialog.Builder alertDialogBuilder;

    List<Integer> Sn = new ArrayList<>();
    List<Integer> Xn = new ArrayList<>();

   // double Qtot[]=  {548.958435058593, 545.389892578125, 546.872924804687, 546.872924804687, 546.190734863281, 550.084838867187, 549.554992675781, 541.214233398437, 547.211791992187, 549.072387695312, 549.910766601562, 547.921569824218, 545.982849121093, 544.9501953125, 543.387329101562, 544.986877441406, 544.59326171875, 545.33056640625, 547.237243652343, 546.969116210937};
    //double Eam[] =  {172.110000610351, 172.110000610351, 172.110000610351, 172.110000610351, 172.119995117187, 172.119995117187, 172.110000610351, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.119995117187, 172.130004882812, 172.130004882812, 172.130004882812, 172.119995117187, 172.130004882812};

    Double Qtot[] = new Double[1];
    Double Eam[] = new Double[1];

    double puissance1[] = new double[20];
    double puissance2[] = new double[20];
    double puissance3[] = new double[20];
    double puissance4[] = new double[20];
    double puissance5[] = new double[20];

    double hnette1;
    double hnette2;
    double hnette3;
    double hnette4;
    double hnette5;

    double Q1;
    double Q2;
    double Q3;
    double Q4;
    double Q5;

    int Q [] = new int[5];
    double Hnette [] = new double[5];





    EditText textDebitTotal;
    EditText textElevationAmont;

    EditText textQ1;
    EditText textP1;
    EditText textHnette1;
    EditText textDebitMax1;
    EditText textQ2;
    EditText textP2;
    EditText textHnette2;
    EditText textDebitMax2;
    EditText textQ3;
    EditText textP3;
    EditText textHnette3;
    EditText textDebitMax3;
    EditText textQ4;
    EditText textP4;
    EditText textHnette4;
    EditText textDebitMax4;
    EditText textQ5;
    EditText textP5;
    EditText textHnette5;
    EditText textDebitMax5;

    List<Integer> turbinesSelected = new ArrayList<>();

    Turbine1 turbine1 = new Turbine1();
    Turbine2 turbine2 = new Turbine2();
    Turbine3 turbine3 = new Turbine3();
    Turbine4 turbine4 = new Turbine4();
    Turbine5 turbine5 = new Turbine5();



    public void ResoudreAlgo() {


        // debut de la programmation dynamique

        for (int n = 0; n < Qtot.length; n++) {

            Q1 = 0.0;
            hnette1 = 0.0;
            puissance1[n] = 0.0;
            Q2 = 0.0;
            hnette2 = 0.0;
            puissance2[n] = 0.0;
            Q3 = 0.0;
            hnette3 = 0.0;
            puissance3[n] = 0.0;
            Q4 = 0.0;
            hnette4 = 0.0;
            puissance4[n] = 0.0;
            Q5 = 0.0;
            hnette5 = 0.0;
            puissance5[n] = 0.0;

            Sn = new ArrayList<>();
            Xn = new ArrayList<>();

            // remplir Sn[]
            for (int i = 0; i <= Qtot[n] / 5; i++) {
                Sn.add(5 * i);

            }

            // remplir Xn[]
            for (int i = 0; i <= Qtot[n] / 5; i++)    // 160/5=32
            {
                Xn.add(5 * i);

            }

            for (Integer indiceTurbines : turbinesSelected) {


                if(turbinesSelected.indexOf(indiceTurbines) == 0){

                    for (Integer aSn1 : Sn) {

                        handleTurbines(indiceTurbines).puissance.add(handlePuissance(indiceTurbines,aSn1, handleTurbines(indiceTurbines).CalculHauteurDeChuteNette(Eam[n], aSn1, Qtot[n])));

                    }

                    handleTurbines(indiceTurbines).X_n = cloneListInteger(Sn);
                    handleTurbines(indiceTurbines).F_N = cloneListDouble(handleTurbines(indiceTurbines).puissance);
                }
                else if(turbinesSelected.indexOf(indiceTurbines) == turbinesSelected.size()-1){

                    // Calcul de gn
                    for (Integer aSn : Sn) {

                        handleTurbines(indiceTurbines).puissance.add(handlePuissance(indiceTurbines, aSn, handleTurbines(indiceTurbines).CalculHauteurDeChuteNette(Eam[n], aSn, Qtot[n])));

                    }

                    double max = 0.0;
                    List<Double> FN_COlONNES = new ArrayList<>();
                    for (int j = 0; j < Xn.size() ; j++) {


                        int indice_superieur = turbinesSelected.get(turbinesSelected.indexOf(indiceTurbines )-1);
                        FN_COlONNES.add(handleTurbines(indiceTurbines).puissance.get(j) + handleTurbines(indice_superieur).F_N.get(Sn.size()-1-j));


                        if (FN_COlONNES.get(j) > max){
                            handleTurbines(indiceTurbines).X_n.add(Xn.get(j));
                            max = FN_COlONNES.get(j);
                            handleTurbines(indiceTurbines).F_N.add(max);
                        }


                    }
                    int num = handleTurbines(indiceTurbines).X_n.get(0);
                    handleTurbines(indiceTurbines).FN.add(FN_COlONNES);

                }
                else{
                    // Calcul de gn
                    for (Integer aSn1 : Sn) {

                        handleTurbines(indiceTurbines).puissance.add(handlePuissance(indiceTurbines,aSn1, handleTurbines(indiceTurbines).CalculHauteurDeChuteNette(Eam[n], aSn1, Qtot[n])));

                    }

                    // calcul de Fn
                    for (int i = 0; i < Sn.size(); i++) {

                        double max = 0.0;
                        List<Double> FN_COlONNES = new ArrayList<>();
                        for (int j = 0; j < Xn.size() ; j++) {

                            if(j>i){

                                FN_COlONNES.add(0.0);
                            }
                            else{


                                int indice_superieur = turbinesSelected.get(turbinesSelected.indexOf(indiceTurbines )-1);
                                FN_COlONNES.add(handleTurbines(indiceTurbines).puissance.get(j) + handleTurbines(indice_superieur).F_N.get(i-j));


                                if (FN_COlONNES.get(j) > max){
                                    handleTurbines(indiceTurbines).X_n.add(Xn.get(j));
                                    max = FN_COlONNES.get(j);
                                    handleTurbines(indiceTurbines).F_N.add(max);
                                }
                            }


                        }

                        handleTurbines(indiceTurbines).FN.add(FN_COlONNES);
                    }
                }


            }

            // Algorithme pour recuperer les valeurs à turbiner
            int k = n + 1;
            int l = 0;
            System.out.println("Ligne " + k + "\n **************************************************\n*****************************************");
            int rang = 0;

            Collections.reverse(turbinesSelected);

            for(Integer indiceTurbines : turbinesSelected){

                if(turbinesSelected.indexOf(indiceTurbines) == 0){

                    //int size = handleTurbines(indiceTurbines).X_n.size();

                    Q[l] = handleTurbines(indiceTurbines).X_n.get(handleTurbines(indiceTurbines).X_n.size() - 1);

                    if (handleDebitMax(indiceTurbines) != -1){
                        if (Q[l] > handleDebitMax(indiceTurbines))
                            Q[l]  = handleDebitMax(indiceTurbines);
                    }


                    handleP(indiceTurbines)[n] = handlePuissance(indiceTurbines, Q[l], turbine1.CalculHauteurDeChuteNette(Eam[n], handleTurbines(indiceTurbines).X_n.get(handleTurbines(indiceTurbines).X_n.size() - 1), Qtot[n]));
                    Hnette[l] = turbine1.CalculHauteurDeChuteNette(Eam[n], handleTurbines(indiceTurbines).X_n.get(handleTurbines(indiceTurbines).X_n.size() - 1), Qtot[n]);
                    System.out.println("La turbine " + indiceTurbines + " doit turbiner un débit de " + Q[l] + " m^3/s" + " et obtient une puissance de " + handleP(indiceTurbines)[n] + " MW");

                    rang = Sn.get(Sn.size()-1) - Q[l];
                }
                else {

                    Q[l] = handleTurbines(indiceTurbines).X_n.get(rang / 5);

                    if (handleDebitMax(indiceTurbines) != -1){
                        if (Q[l] > handleDebitMax(indiceTurbines))
                            Q[l]  = handleDebitMax(indiceTurbines);
                    }


                    Hnette[l] = handleTurbines(indiceTurbines).CalculHauteurDeChuteNette(Eam[n], Q[l], Qtot[n]);
                    handleP(indiceTurbines)[n] = handlePuissance(indiceTurbines,Q[l], Hnette[l] );
                    System.out.println("La turbine " + indiceTurbines + " doit turbiner un débit de " + Q[l] + " m^3/s" + " et obtient une puissance de " + handleP(indiceTurbines)[n] + " MW");

                    rang = Sn.get(rang / 5) - Q[l];
                }

                l++;

            }



            // affecter les différentes valeurs de chaque turbine

            int i = 0;
            for(Integer indiceTurbines : turbinesSelected){


                switch (indiceTurbines){

                    case 1 :
                        Q1 = Q[i];
                        hnette1 = Hnette[i];
                        break;
                    case 2 :
                        Q2 = Q[i];
                        hnette2 = Hnette[i];
                        break;
                    case 3 :
                        Q3 = Q[i];
                        hnette3 = Hnette[i];
                        break;
                    case 4:
                        Q4 = Q[i];
                        hnette4 = Hnette[i];
                        break;
                    case 5:
                        Q5 = Q[i];
                        hnette5 = Hnette[i];
                        break;

                }

                i++;
            }



            Collections.reverse(turbinesSelected);



            for (Integer indiceTurbines : turbinesSelected){

                handleTurbines(indiceTurbines).F_N = new ArrayList<>();
                handleTurbines(indiceTurbines).FN = new ArrayList<>();
                handleTurbines(indiceTurbines).puissance = new ArrayList<>();
                handleTurbines(indiceTurbines).X_n = new ArrayList<>();
            }

            Q = new int[5];
            Hnette = new double[5];


        }

    }

    public static List<Double> cloneListDouble(List<Double> list) {
        List<Double> clone = new ArrayList<Double>(list.size());
        for (Double item : list){
            clone.add(new Double(item));
        }
        return clone;
    }

    public static List<Integer> cloneListInteger(List<Integer> list) {
        List<Integer> clone = new ArrayList<Integer>(list.size());
        for (Integer item : list){
            clone.add(new Integer(item));
        }
        return clone;
    }


   public Turbine handleTurbines (int position){

        Turbine turbine = new Turbine();

        switch (position){
            case 1 :
                return turbine1;
            case 2 :
                return turbine2;
            case 3 :
                return turbine3;
            case 4:
                return turbine4;
            case 5:
                return turbine5;
        }

        return turbine;
    }

    public double handlePuissance(int position, double Sn, double HauteurDeChuteNette){

        switch (position){
            case 1 :
                return turbine1.P1(Sn,HauteurDeChuteNette);
            case 2 :
                return turbine2.P2(Sn,HauteurDeChuteNette);
            case 3 :
                return turbine3.P3(Sn,HauteurDeChuteNette);
            case 4:
                return turbine4.P4(Sn,HauteurDeChuteNette);
            case 5:
                return turbine5.P5(Sn,HauteurDeChuteNette);
        }

        return 0.0;
    }

    public double [] handleP(int position){

        double p [] = {};
        switch (position){
            case 1 :
                return puissance1;
            case 2 :
                return puissance2;
            case 3 :
                return puissance3;
            case 4:
                return puissance4;
            case 5:
                return puissance5;
        }

        return p;
    }

    public int handleDebitMax(int position){

        switch (position){
            case 1 :

                try{
                    return Integer.parseInt(textDebitMax1.getText().toString());
                }catch (Exception e){

                }

            case 2 :
                try{
                    return Integer.parseInt(textDebitMax2.getText().toString());
                }catch (Exception e){

                }
            case 3 :
                try{
                    return Integer.parseInt(textDebitMax3.getText().toString());
                }catch (Exception e){

                }
            case 4:
                try{
                    return Integer.parseInt(textDebitMax4.getText().toString());
                }catch (Exception e){

                }
            case 5:
                try{
                    return Integer.parseInt(textDebitMax5.getText().toString());
                }catch (Exception e){

                }
        }

        return -1;
    }




}
