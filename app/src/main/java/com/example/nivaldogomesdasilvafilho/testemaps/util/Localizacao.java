package com.example.nivaldogomesdasilvafilho.testemaps.util;

import java.io.Serializable;

/**
 * Created by nivaldogomesdasilvafilho on 1/13/17.
 */

public class Localizacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private double latitude;
    private double longitutude;

    public Localizacao(double latitude, double longitutude) {
        this.latitude = latitude;
        this.longitutude = longitutude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getLatitudeString() {
        return Double.toString(latitude);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitutude() {
        return longitutude;
    }

    public String getLongitutudeString() {
        return Double.toString(longitutude);
    }

    public void setLongitutude(double longitutude) {
        this.longitutude = longitutude;
    }
}
