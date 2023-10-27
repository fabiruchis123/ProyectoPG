/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otelo;

import otelo.Colores;
/**
 *
 * @author UTN
 */
public class Tablero {
    
    private int tamano;
    private Fichas[][] casillas;

    public Tablero(int tamano, Fichas[][] casillas) {
        this.tamano = tamano;
        this.casillas = casillas;
        
        
        int centro = tamano / 2;
        
        casillas[centro - 1][centro - 1] = new Fichas(new Jugador("Jugador 1" , Colores.BLANCO, 1));
        casillas[centro][centro] = new Fichas(new Jugador("Jugador 1", Colores.MORADO,1));
        casillas[centro - 1][centro] = new Fichas(new Jugador("Jugador 2", Colores.BLANCO,1));
        casillas[centro][centro - 1] = new Fichas(new Jugador("Jugador 2", Colores.MORADO,1));
        
    }
    
     public void colocarFicha(int fila, int columna, Jugador jugador) {
        if (casillas[fila][columna] == null) {
            casillas[fila][columna] = new Fichas(jugador);
            evaluarYCambiarFichas(fila, columna, jugador, 0, -1); // Comienza a verificar hacia la izquierda
            evaluarYCambiarFichas(fila, columna, jugador, 0, 1); // Luego hacia la derecha
            evaluarYCambiarFichas(fila, columna, jugador, -1, 0); // Verifica hacia arriba
            evaluarYCambiarFichas(fila, columna, jugador, 1, 0); // Y hacia abajo
        }
    }
        
   
   private void evaluarYCambiarFichas(int fila, int columna, Jugador jugador, int direccionFila, int direccionColumna) {
        int filaActual = fila + direccionFila;
        int columnaActual = columna + direccionColumna;

        if (filaActual < 0 || filaActual >= tamano || columnaActual < 0 || columnaActual >= tamano) {
            return;
        }

        Fichas fichaActual = casillas[filaActual][columnaActual];

        if (fichaActual == null) {
            return; 
        }

        Jugador propietarioActual = fichaActual.getPropietario();

        if (propietarioActual == jugador) {
            // Cambiar el propietario de la ficha
            fichaActual.setPropietario(jugador);
        }
        evaluarYCambiarFichas(filaActual, columnaActual, jugador, direccionFila, direccionColumna);
    }
}
