/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otelo.logica;

/**
 *
 * @author UTN
 */
public class Fichas extends Jugador {
    
    private Jugador propietario;
    private Colores color;
    
    public Colores getColor() {
        return color;
    }

    public Fichas(String nombre, Colores colores, int cantFichas) {
        super(nombre, colores, cantFichas);
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }
    
    
}
