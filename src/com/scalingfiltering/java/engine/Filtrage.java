package com.scalingfiltering.java.engine;

import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Filtrage {

    public int user = 700;
    public int movie = 200000;
    public static float[][] tab = new float[700][200000];
    public static int i;
    public static int j;

    public static float[][] tab_p; // prediction table
    public static float[][] tab_s; // semilatite table
    static float[][] tab_w;
    static int[][] w; // calssmesnt of semilarité
    static float[] tab_m; // table of moyen
    static float[] som_w; // some de weit(semilarité)
    public static float[] moy_err; // moyenne d'erreur

    static float s = 0;
    static float v = 0;
    static float pr = 0;
    static float p = 0;
    static float p2 = 0;
    static int h = 0;

    static double m = 0;
    static int nbr_0 = 0;

    public static void loadTable(File file) {

        List<List<String>> mat = new ArrayList<>();
        // this gives you a 2-dimensional array of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try {
            inputStream = new Scanner(file);

            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(";");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // the following code lets you iterate through the 2-dimensional array
        int lineNo = 1;
        for (List<String> line : lines) {
            int columnNo = 1;

            int index = 1;
            for (String value : line) {

                String[] values = value.split(",");
                //  System.out.println(columnNo);
                // this adds the currently parsed line to the 2-dimensional string array
                List<String> data = new ArrayList<String>();
                List<String> d = new ArrayList<String>();
                //  System.out.println(columnNo);

                if (lineNo > 1) {

                    int i = Integer.parseInt(values[0]);
                    int j = Integer.parseInt(values[1]);
                    double val = Double.parseDouble(values[2]);
                    //String g=values[2];
                    // System.out.println(values[0]+"|"+values[1]+"|"+values[2]);
                    tab[i][j] = (float) val;

                }
                //line.add(Arrays.asList(values));
                //  System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                columnNo++;

            }
            lineNo++;
        }
    }

    public static void calculate(int nbrColumn, int nbrRow) {
        // initialize variables
        i = nbrColumn;
        j = nbrRow;

        tab_p = new float[i][j];
        tab_s = new float[i][i];
        tab_w = new float[i][i];
        w = new int[i][i];
        tab_m = new float[i];
        som_w = new float[i];
        moy_err = new float[i];

        // calculate
        calcSimularite();
        calcPreduction();
    }

    public static void calcSimularite() {
        // calcul S
        for (int l = 1; l < i; l++) {
            for (int k = 1; k < j; k++) {

                //  if (tab[l][k]==0) {
                v = 0;
                int m = 1;
                while (m < i) {
                    s = 0;
                    pr = 0;
                    p = 0;
                    p2 = 0;

                    if (m != l) {

                        for (int n = 1; n < j; n++) {
                            if ((n != k) && (tab[m][n] != 0) && (tab[l][n] != 0)) {
                                pr = pr + tab[l][n] * tab[m][n];
                            }
                        }
                        for (int n = 1; n < j; n++) {
                            if ((n != k) && (tab[m][n] != 0) && (tab[l][n] != 0)) {
                                p2 = p2 + tab[m][n] * tab[m][n];
                            }
                        }
                        for (int n = 1; n < j; n++) {
                            if ((n != k) && (tab[m][n] != 0) && (tab[l][n] != 0)) {
                                p = p + tab[l][n] * tab[l][n];
                            }
                        }
                        s = (float) (pr / (sqrt(p) * sqrt(p2)));
                        tab_s[l][m] = s;
                        //System.out.println(s);
                        if (s > v) {
                            v = s;
                            h = m;

                        }
                    } else {
                        tab_s[l][m] = 0;
                    }
                    m++;

                }
                //              System.out.println(v+"      **utilisateur voisin est **  "+h);
                //    }

            }
        }      //System.out.println( "" );
        //System.out.println( "tableau de similarité" );
        for (int l = 1; l < i; l++) {
            //System.out.println( "" );
            for (int k = 1; k < i; k++) {
                tab_s[l][k] = (float) (Math.round(tab_s[l][k] * 100)) / 100;
                // System.out.print( tab_s[l][k]+"|" );
            }
        }
        // calcule prediction
        //1 calcul moyenne

        for (int l = 1; l < i; l++) {
            nbr_0 = 0;
            m = 0;
            for (int k = 1; k < j; k++) {
                if (tab[l][k] == 0) {
                    nbr_0++;
                }
                m = m + tab[l][k];
            }

            tab_m[l] = (float) (m / ((j - 1) - nbr_0));
        }
        // System.out.println("" );
        //System.out.println( "tableau de moyenne" );
        for (int l = 1; l < i; l++) {
            //System.out.println("__"+ tab_m[l] );
        }
        //2 calcule W

        for (int l = 1; l < i; l++) {
            for (int k = 1; k < i; k++) {
                tab_w[l][k] = tab_s[l][k];
            }
        }
        for (int l = 1; l < i; l++) {
            for (int k = 1; k < i; k++) {
                w[l][k] = k;
            }
        }
        //trier W

        for (int l = 1; l < i; l++) {
            double temp1 = 0;
            int temp2 = 0;
            boolean t = false;
            while (t == false) {
                t = true;
                for (int k = 1; k < i - 1; k++) {
                    if (tab_w[l][k] < tab_w[l][k + 1]) {
                        temp1 = tab_w[l][k];
                        tab_w[l][k] = tab_w[l][k + 1];
                        tab_w[l][k + 1] = (float) temp1;
                        // trier indice
                        temp2 = w[l][k];
                        w[l][k] = w[l][k + 1];
                        w[l][k + 1] = temp2;
                        t = false;
                    }

                }
            }
        }  //  System.out.println( "" );
        // System.out.println( "tableau de weit" );
        for (int l = 1; l < i; l++) {
            //  System.out.println( "" );
            som_w[l] = 0;
            for (int k = 1; k < i; k++) {
                //  System.out.print("_"+ tab_w[l][k]+"|" );
                som_w[l] = som_w[l] + tab_w[l][k];
            }
            //   System.out.print("****"+ som_w[l] );
        }
    }

    public static void calcPreduction() {
        // preduction
        for (int l = 1; l < i; l++) {
            for (int k = 1; k < j; k++) {
                double som = 0;
                //  if (tab[l][k]==0) {
                for (int n = 1; n < i; n++) {
                    if (tab[w[l][n]][k] != 0) {
                        //    System.out.println("/////"+tab_s[l][w[l][n]]+"******"+tab[w[l][n]][k]+"&&&&&&&&"+tab_m[w[l][n]] );
                        som = som + tab_s[l][w[l][n]] * (tab[w[l][n]][k] - tab_m[w[l][n]]);
                    }
                }
                // System.out.print("/////"+som+"******"+tab_m[l] );
                nbr_0 = 0;
                m = 0;
                for (int a = 1; a < j; a++) {
                    if (tab[l][a] == 0 | k == a) {
                        nbr_0++;
                    }
                    if (k != a) {
                        m = m + tab[l][a];
                    }
                }
                m = m / (j - nbr_0 - 1);
                // System.out.println("moyenne est "+m+j+nbr_0);
                // System.out.println("&&&&&"+m);
                tab_p[l][k] = (float) (m + (som / som_w[l]));
                //  }

            }
        }
        // System.out.println( "" );
        System.out.println("Calcule matrice de préduction ");
        for (int l = 1; l < i; l++) {
            //System.out.println("");
            for (int k = 1; k < j; k++) {
                if (tab_p[l][k] == 0) {
                    tab_p[l][k] = tab[l][k];
                } else {
                    tab_p[l][k] = ((float) (Math.round(tab_p[l][k] * 100)) / 100);
                }
                //System.out.print("__" + tab_p[l][k]);
            }
        }
    }

    public static void calcAverageError() {
        System.out.println("Calculate moyen d'erreur !");
        for (int l = 1; l < i; l++) {
            double n, err = 0;
            for (int k = 1; k < j; k++) {
                if (tab[l][k] != 0) {
                    n = tab_p[l][k] - tab[l][k];
                    n = sqrt(n * n);
                    err = err + n;
                }
            }
            moy_err[l] = (float) err / tab_m[l];
            if(String.valueOf(moy_err[l]).equalsIgnoreCase("NaN"))
                moy_err[l] = 0f;
            //System.out.println(moy_err[l] + "|");
        }
    }
}
