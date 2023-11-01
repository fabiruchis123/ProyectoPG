/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otelo;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author UTN
 */
public class Juego {
    
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private int tamano;
    private Fichas[][] casillas;
    private Colores colores;
    
     public static int[][] obtenerTableroInicial() {
        int[][] tablero = new int[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                tablero[i][j] = 0;
            }
        }
        tablero[5][5] = 2;
        tablero[5][6] = 1;
        tablero[6][5] = 1;
        tablero[6][6] = 2;
        return tablero;
    }
     
    public void inicializarTablero() {
    int centro = tablero.getTamano() / 2;
    tablero.colocarFicha(centro - 1, centro - 1, jugador1, Colores.BLANCO);
    tablero.colocarFicha(centro, centro, jugador1, Colores.BLANCO);
    tablero.colocarFicha(centro - 1, centro, jugador2, Colores.MORADO);
    tablero.colocarFicha(centro, centro - 1, jugador2, Colores.MORADO);
}
    
    private void evaluarFicha(int fila, int columna, Jugador jugador, int direccionFila, int direccionColumna) {
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
        evaluarFicha(filaActual, columnaActual, jugador, direccionFila, direccionColumna); 
        cambiarFicha(filaActual, columnaActual, jugador);
    }
}
    private void cambiarFicha(int fila, int columna, Jugador jugador) {
    Fichas fichaActual = casillas[fila][columna];
    fichaActual.setPropietario(jugador);
    evaluarFicha(fila, columna, jugador, fila, columna);
    
   }
    
    public void realizarMovimiento(int fila, int columna, Colores color) {
    if (movimientoValido(fila, columna)) {
        tablero.colocarFicha(fila, columna, jugadorActual, color);
        realizarMovimiento(fila, columna, color);
        int puntuacionJugador1 = jugador1.Puntuacion();
        int puntuacionJugador2 = jugador2.Puntuacion();
        cambiarTurno();
    }
}
    private boolean movimientoValido(int fila, int columna) {
    if (fila < 1 || fila >= 12 || columna < 1 || columna >= 12) {
        return false;
    }
    
    Fichas fichaActual = tablero.obtenerFichas(fila, columna);
    
    if (fichaActual != null) {
        return false;
    }
    return movimientoValido(fila - 1, columna - 1) ||
           movimientoValido(fila - 1, columna) ||
           movimientoValido(fila - 1, columna + 1) ||
           movimientoValido(fila, columna - 1) ||
           movimientoValido(fila, columna + 1) ||
           movimientoValido(fila + 1, columna - 1) ||
           movimientoValido(fila + 1, columna) ||
           movimientoValido(fila + 1, columna + 1);
}
    private void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }

     public static ArrayList<Point> MovimientosPosibles(int[][] tablero, int jugador) {
        ArrayList<Point> resultado = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (puedeJugar(tablero, jugador, i, j)) {
                    resultado.add(new Point(i, j));
                }
            }
        }
        return resultado;
    }
       public static int obtenerTotalFichas(int[][] tablero) {
        int c = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (tablero[i][j] != 0) {
                    c++;
                }
            }
        }
        return c;
    }
       
     public static ArrayList<Point> fichasReversibles(int[][] tablero, int jugador, int i, int j) {
     ArrayList<Point> fichasReversibles = new ArrayList<>();
     int oponente = (jugador == 1) ? 2 : 1;
    
        int[][] direcciones = {
        {-1, 0}, {1, 0},   
        {0, -1}, {0, 1},   
        {-1, -1}, {-1, 1}, 
        {1, -1}, {1, 1}, 
        {0, -2}, {0, 2},   
        {2, 0}, {-2, 0}    
    };

    for (int[] direccion : direcciones) {
        int di = direccion[0];
        int dj = direccion[1];
        int mi = i + di;
        int mj = j + dj;
        int c = 0;
        ArrayList<Point> fichasReversibless = new ArrayList<>();

        while (mi >= 0 && mi < 12 && mj >= 0 && mj < 12 && tablero[mi][mj] == oponente) {
            fichasReversibless.add(new Point(mi, mj));
            mi += di;
            mj += dj;
            c++;
        }
        if (mi >= 0 && mi < 12 && mj >= 0 && mj < 12 && tablero[mi][mj] == jugador && c > 0) {
            fichasReversibles.addAll(fichasReversibless);
        }
    }

    return fichasReversibles;
}
         
    public static boolean puedeJugar(int[][] tablero, int jugador, int i, int j) {
    if (tablero[i][j] != 0) {
        return false;
    }
    
    int oponente = ((jugador == 1) ? 2 : 1);
  
    int[][] direcciones = {
        {-1, 0}, {1, 0},  
        {0, -1}, {0, 1},    
        {-1, -1}, {-1, 1},   
        {1, -1}, {1, 1},  
        {0, -2}, {0, 2},     
        {2, 0}, {-2, 0}      
    };
    
    for (int[] direccion : direcciones) {
        int Cr = direccion[0];
        int Jc = direccion[1];
        if (puedeJugarr(tablero, jugador, i, j, Cr, Jc, oponente)) {
            return true;
        }
    }
    
    return false;
 }
    private static boolean puedeJugarr(int[][] tablero, int jugador, int i, int j, int Cr, int Jc, int oponente) {
    int mi = i + Cr;
    int mj = j + Jc;
    int c = 0;
    
    while (mi >= 0 && mi < 12 && mj >= 0 && mj < 12 && tablero[mi][mj] == oponente) {
        mi += Cr;
        mj += Jc;
        c++;
    }
    if (mi >= 0 && mi < 12 && mj >= 0 && mj < 12 && tablero[mi][mj] == jugador && c > 0) {
        return true;
    }
    return false;
   }
    public static int[][] TableroDespuesDeMovimientos(int[][] tablero, Point movimiento, int jugador) {
        int[][] nuevoTablero = new int[12][12];
        for (int k = 0; k < 12; k++) {
            for (int l = 0; l < 12; l++) {
                nuevoTablero[k][l] = tablero[k][l];
            }
        }
        nuevoTablero[movimiento.x][movimiento.y] = jugador;

        ArrayList<Point> puntosReversibles = fichasReversibles(nuevoTablero, jugador, movimiento.x, movimiento.y);
        for (Point punto : puntosReversibles) {
            nuevoTablero[punto.x][punto.y] = jugador;
        }

        return nuevoTablero;
    }
    public static ArrayList<Point> encontrarFichas(int[][] tablero, int jugador, int fila, int columna) {
    ArrayList<Point> fichasEstables = new ArrayList<>();
    int oponente = (jugador == 1) ? 2 : 1;

    int[][] direcciones = {
        {-1, 0}, {1, 0},   
        {0, -1}, {0, 1},  
        {-1, -1}, {-1, 1},
        {1, -1}, {1, 1} 
    };

    for (int[] direccion : direcciones) {
    ArrayList<Point> fichasEstabless = buscarFichas(tablero, jugador, fila, columna, direccion[0], direccion[1], oponente);
    fichasEstables.addAll(fichasEstabless);
    }

    return fichasEstables;
  }
    public static ArrayList<Point> buscarFichas(int[][] tablero, int jugador, int fila, int columna, int deltaFila, int deltaColumna, int oponente) {
    ArrayList<Point> fichasEstables = new ArrayList<>();
    int filaActual = fila + deltaFila;
    int columnaActual = columna + deltaColumna;

    while (filaActual >= 0 && filaActual < 12 && columnaActual >= 0 && columnaActual < 12 && tablero[filaActual][columnaActual] == oponente) {
        fichasEstables.add(new Point(filaActual, columnaActual));
        filaActual += deltaFila;
        columnaActual += deltaColumna;
    }

    if (filaActual >= 0 && filaActual < 12 && columnaActual >= 0 && columnaActual < 12 && tablero[filaActual][columnaActual] == jugador) {
        return fichasEstables;
    } else {
        return new ArrayList<>(); 
    }
  }
    public static ArrayList<Point> obtenerCasillas(int[][] tablero, int jugador) {
    ArrayList<Point> casillasFrontera = new ArrayList<>();

    int oponente = (jugador == 1) ? 2 : 1;

    for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 12; j++) {
            if (tablero[i][j] == oponente) {
                ArrayList<Point> casillasPosibles = new ArrayList<>();

               
                int[][] direcciones = {
                    {-1, 0}, {1, 0},    
                    {0, -1}, {0, 1},     
                    {-1, -1}, {-1, 1},   
                    {1, -1}, {1, 1}      
                };
                for (int[] direccion : direcciones) {
                    int dFila = direccion[0];
                    int dColumna = direccion[1];
                    int nuevaFila = i + dFila;
                    int nuevaColumna = j + dColumna;

    if (nuevaFila >= 0 && nuevaFila < 12 && nuevaColumna >= 0 && nuevaColumna < 12 && tablero[nuevaFila][nuevaColumna] == 0) {
                        casillasPosibles.add(new Point(nuevaFila, nuevaColumna));
                    }
                }

                for (Point posibleCasilla : casillasPosibles) {
                    boolean redundante = false;
                    for (Point casillaFrontera : casillasFrontera) {
                        if (casillaFrontera.equals(posibleCasilla)) {
                            redundante = true;
                            break;
                        }
                    }
                    if (!redundante) {
                        casillasFrontera.add(posibleCasilla);
                    }
                }
            }
        }
    }
    return casillasFrontera;
   }
    public Jugador comprobarGanador(){
        int contJugador1 = jugador1.getCantFichas();
        int contJugador2 = jugador2.getCantFichas();
        
        if (contJugador1 > contJugador2) {
            return jugador1;
            
        }else if (contJugador2 > contJugador1) {
            return jugador2;
        }
        return null;
    }
    public static boolean tieneMovimientos(int[][] tablero, int jugador) {
        return MovimientosPosibles(tablero, jugador).size() > 0;
    }
         public static boolean juegoTerminado(int[][] tablero) {
        return !(tieneMovimientos(tablero, 1) || tieneMovimientos(tablero, 2));
    }
         
}
