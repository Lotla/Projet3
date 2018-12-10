package com.example.bbx22.projet3.turbines;

import static java.lang.Math.pow;

public class Turbine3 extends Turbine {

    public Turbine3(){

    }


    public double P3(double Sn, double HauteurDeChuteNette)
    {

        return
                5.718                                                       //p00
                        +0.02396*Sn                                         //p10x
                        -0.3724*HauteurDeChuteNette                       //p01y
                        -0.0003691*pow(Sn, 2)                               //p20x^2
                        +0.009429*Sn * HauteurDeChuteNette           //p11xy
                        +0.006015*pow(HauteurDeChuteNette, 2)      ;           //p02y^2



    }
}
