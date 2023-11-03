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

    public int getTamano() {
        return tamano;
    }

    public Fichas[][] getCasillas() {
        return casillas;
    }
    
    

    public Tablero(int tamano, Fichas[][] casillas) {
        this.tamano = tamano;
        this.casillas = casillas;
         
    }
    
     public void colocarFicha(int fila, int columna, Jugador jugador, Colores color,String nombre) {
        if (casillas[fila][columna] == null) {
        casillas[fila][columna] = new Fichas(nombre, color, fila);
    }
}
        
   
//   private void evaluarYCambiarFichas(int fila, int columna, Jugador jugador, int direccionFila, int direccionColumna) {
//        int filaActual = fila + direccionFila;
//        int columnaActual = columna + direccionColumna;
//        
//        if (filaActual < 0 || filaActual >= tamano || columnaActual < 0 || columnaActual >= tamano) {
//            return;
//        }
//
//        Fichas fichaActual = casillas[filaActual][columnaActual];
//
//        if (fichaActual == null) {
//            return; 
//        }
//
//        Jugador propietarioActual = fichaActual.getPropietario();
//
//        if (propietarioActual == jugador) {
//            fichaActual.setPropietario(jugador);
//        }
//        evaluarYCambiarFichas(filaActual, columnaActual, jugador, direccionFila, direccionColumna);
//    }
   
   public Fichas obtenerFichas(int fila, int columna){
       return casillas[fila][columna];
   }
}
