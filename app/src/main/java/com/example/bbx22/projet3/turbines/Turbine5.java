package com.example.bbx22.projet3.turbines;

import static java.lang.Math.pow;

public class Turbine5 extends Turbine {

    public Turbine5(){


    }

    public double P5(double Sn, double HauteurDeChuteNette)
    {

        return(8.02                                                      //p00
                -0.005384*Sn                                         //p10x
                -0.5023*HauteurDeChuteNette                        //p01y
                -0.0004186*pow(Sn, 2)                                //p20x^2
                +0.01076*Sn * HauteurDeChuteNette            //p11xy
                +0.007844*pow(HauteurDeChuteNette, 2));                 //p02y^2



    }




}
