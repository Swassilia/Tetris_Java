package outil;

import static java.lang.Math.random;

public class Tool {
    //private int min, max;

    public static int monRandom(int min, int max)
    {
        int etendue = max - min + 1;
        int entierAlea = ((int) (Math.random() * etendue)) + min;

        return entierAlea;
    }



}
