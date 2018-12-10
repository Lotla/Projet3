package com.example.bbx22.projet3.turbines;

import static java.lang.Math.pow;

public class Turbine4 extends Turbine {

    public Turbine4(){

    }

    public double P4(double Sn, double HauteurDeChuteNette)
    {
        return
                7.164                                                       //p00
                        +0.01884*Sn                                        //p10x
                        -0.4814*HauteurDeChuteNette                     //p01y
                        -0.0003517*pow(Sn, 2)                               //p20x^2
                        +0.009932*Sn * HauteurDeChuteNette            //p11xy
                        +0.007999*pow(HauteurDeChuteNette, 2) ;                //p02y^2


    }
}
