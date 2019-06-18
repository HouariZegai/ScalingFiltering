package com.scalingfiltering.java.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.StringProperty;

public class Rating extends RecursiveTreeObject<Rating> {
//    public StringProperty p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
//            p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
//            p21, p22, p23, p24, p25, p26, p27, p28, p29, p30;

    public final StringProperty[] p;

    public Rating() {
        p = new StringProperty[50];
    }



//    public Rating(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13, String p14, String p15, String p16, String p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30) {
//        this.p1 = new SimpleStringProperty(p1);
//        this.p2 = new SimpleStringProperty(p2);
//        this.p3 = new SimpleStringProperty(p3);
//        this.p4 = new SimpleStringProperty(p4);
//        this.p5 = new SimpleStringProperty(p5);
//        this.p6 = new SimpleStringProperty(p6);
//        this.p7 = new SimpleStringProperty(p7);
//        this.p8 = new SimpleStringProperty(p8);
//        this.p9 = new SimpleStringProperty(p9);
//        this.p10 = new SimpleStringProperty(p10);
//        this.p11 = new SimpleStringProperty(p11);
//        this.p12 = new SimpleStringProperty(p12);
//        this.p13 = new SimpleStringProperty(p13);
//        this.p14 = new SimpleStringProperty(p14);
//        this.p15 = new SimpleStringProperty(p15);
//        this.p16 = new SimpleStringProperty(p16);
//        this.p17 = new SimpleStringProperty(p17);
//        this.p18 = new SimpleStringProperty(p18);
//        this.p19 = new SimpleStringProperty(p19);
//        this.p20 = new SimpleStringProperty(p20);
//        this.p21 = new SimpleStringProperty(p21);
//        this.p22 = new SimpleStringProperty(p22);
//        this.p23 = new SimpleStringProperty(p23);
//        this.p24 = new SimpleStringProperty(p24);
//        this.p25 = new SimpleStringProperty(p25);
//        this.p26 = new SimpleStringProperty(p26);
//        this.p27 = new SimpleStringProperty(p27);
//        this.p28 = new SimpleStringProperty(p28);
//        this.p29 = new SimpleStringProperty(p29);
//        this.p30 = new SimpleStringProperty(p30);
//    }
}
