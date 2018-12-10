package com.example.bbx22.projet3.turbines;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public abstract class Turbine {

    public List<List<Double>> FN = new ArrayList<>();
    public List<Double> puissance = new ArrayList<>();
    public List<Double> F_N = new ArrayList<>();
    public List<Integer> X_n = new ArrayList<>();



    protected double CalculElevation_aval_en_fonction_du_debit_total(double Qtot)
    {

        return -1 * pow(10, -7)*pow(Qtot, 3) + 0.0002*pow(Qtot, 2) - 0.0897*Qtot + 154.4;

    }

    protected double CalculHauteurDeChuteNette(double elevation_amont, double Sn, double Qtot)
    {

        return elevation_amont - CalculElevation_aval_en_fonction_du_debit_total(Qtot) - 0.5*pow(10,-5)*pow(Sn,2);    //Eam-Eav(Qtot)-0.5e^-5*Q^2

    }
}
