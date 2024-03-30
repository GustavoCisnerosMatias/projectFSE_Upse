/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.util.StringConverter;


public class TemporadasConverte extends StringConverter<Temporadas>{

    @Override
    public String toString(Temporadas t) {
        return t == null ? null : t.getTipoTemporada();
    }

    @Override
    public Temporadas fromString(String string) {
        return null;
    }
    
}
