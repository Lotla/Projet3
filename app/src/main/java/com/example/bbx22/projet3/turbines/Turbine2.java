package com.example.bbx22.projet3.turbines;

import static java.lang.Math.pow;

public class Turbine2 extends Turbine {

    public Turbine2(){

    }


    public double P2(double Sn, double HauteurDeChuteNette)
    {

        return
                3.155                                                       //p00
                        +0.01667*Sn                                        //p10x
                        -0.1714*HauteurDeChuteNette                       //p01y
                        -0.000344*pow(Sn, 2)                               //p20x^2
                        +0.009491*Sn * HauteurDeChuteNette           //p11xy
                        +0.002316*pow(HauteurDeChuteNette, 2) ;                //p02y^2



    }
}
