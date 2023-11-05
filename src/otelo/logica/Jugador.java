/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otelo.logica;

/**
 *
 * @author UTN
 */
public class Jugador {
    private String nombre;
    private Colores colores;
    private int cantFichas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Colores getColores() {
        return colores;
    }

    public void setColores(Colores colores) {
        this.colores = colores;
    }

    public int getCantFichas() {
        return cantFichas;
    }

    public void setCantFichas(int cantFichas) {
        this.cantFichas = cantFichas;
    }

    public Jugador(String nombre, Colores colores, int cantFichas) {
        this.nombre = nombre;
        this.colores= colores;
        this.cantFichas = 2;
    }
    
    public void agregarFicha() {
        cantFichas++;
    }

    public void restarFicha() {
        cantFichas--;
    }
    public int Puntuacion() {
        return cantFichas;
    }
    
}
