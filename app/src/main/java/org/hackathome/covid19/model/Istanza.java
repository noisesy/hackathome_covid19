package org.hackathome.covid19.model;

import java.util.Date;

//TODO: choose a better name
//TODO: validate fields with Milo
public class Istanza {
    public Date date;
    public String name;
    public String surname;
    public String univocalCode;
    public float temperature;
    public boolean hasTosseSecca;
    public boolean hasInappetenza;
    public boolean hasDifficoltàRespiratoria;
}
