/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otelo;

/**
 *
 * @author UTN
 */
public class Fichas {
    
    private Jugador propietario;
    private Colores color;

    public Jugador getPropietario() {
        return propietario;
    }

    public Fichas(Jugador propietario, Colores color) {
        this.propietario = propietario;
        this.color = color;
        
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }
    
    
}
