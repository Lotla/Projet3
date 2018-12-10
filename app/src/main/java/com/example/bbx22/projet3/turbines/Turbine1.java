package com.example.bbx22.projet3.turbines;

import static java.lang.Math.pow;

public class Turbine1 extends Turbine {

    public Turbine1(){

    }

    public double P1(double Sn, double HauteurDeChuteNette)
    {

        return
                -3.493                                                      //p00
                        +0.04027*Sn                                        //p10x
                        +0.2229*HauteurDeChuteNette                        //p01y
                        -0.0004319*pow(Sn, 2)                              //p20x^2
                        +0.009012*Sn * HauteurDeChuteNette            //p11xy
                        -0.00355*pow(HauteurDeChuteNette, 2) ;                //p02y^2



    }

}
