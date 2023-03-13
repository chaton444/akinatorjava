/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package intento10;

import java.net.URL;

/**
 *
 * @author Rosita
 */
public class personaje {
    private String nombre;
    private String caracteristica;
    private String url;
    

    public personaje(String nombre, String caracteristica, String url) {
        this.nombre = nombre;
        this.caracteristica = caracteristica;
        this.url=url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCaracteristica() {
        return caracteristica;
    }
    public String getUrl(){
    return url;
    }
}