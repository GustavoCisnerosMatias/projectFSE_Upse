/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.util.StringConverter;


public class TipoHabitacionConverte  extends StringConverter<TipoHabitacion>{

    @Override
    public String toString(TipoHabitacion t) {
        return t == null ? null : t.getTipoNombre();
    }

    @Override
    public TipoHabitacion fromString(String string) {
        return null;
    }
}
