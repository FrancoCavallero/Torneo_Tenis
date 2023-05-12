package Modelo;

public class Tenista {
    private String nombre;
    private int probabilidad_ganar;
    private boolean leTocaSacar;

    // La variable puntos es el indice de puntosString
    private int puntos;
    private final static String[] puntosString = {"00", "15", "30", "40", "AD"};
    private int juegos;
    private int sets;
    private int juegosSetAnterior;




    // Constructores ------------------------------------
    public Tenista(String nombre, int probabilidad_ganar) {
        this.nombre = nombre;
        this.probabilidad_ganar = probabilidad_ganar;
        this.leTocaSacar = false;
        this.puntos = 0;
        this.juegos = 0;
        this.sets   = 0;
        this.juegosSetAnterior = 0;
    }


    // Setters ------------------------------------------
    public void setLeTocaSacar(boolean leTocaSacar) {
        this.leTocaSacar = leTocaSacar;
    }

    public void setJuegosSetAnterior(int juegosSetAnterior) {
        this.juegosSetAnterior = juegosSetAnterior;
    }

    // Getters ------------------------------------------
    public boolean leTocaSacar() {
        return leTocaSacar;
    }

    public String getNombre() {
        return nombre;
    }

    public int getProbabilidad_ganar() {
        return probabilidad_ganar;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getJuegos() {
        return juegos;
    }

    public int getSets() {
        return sets;
    }

    public String getPuntosString(){
        return puntosString[puntos];
    }

    public int getJuegosSetAnterior() {
        return juegosSetAnterior;
    }

    // Utilidad ---------------------------------------------


    /**
     * Aumenta en uno los puntos ganados
     */
    public void ganarPunto(){
        puntos ++;
    }

    /**
     * Disminuye en uno los puntos ganados
     */
    public void restarPunto(){
        if (puntos > 0){
            puntos --;
        }
    }

    /**
     *  Aumenta en uno los juegos ganados
     */
    public void ganarJuego(){
        juegos ++;
        puntos = 0;
    }

    /**
     *  Aumenta en uno los juegos ganados
     */
    public void ganarSet(){
        sets ++;
        juegos = 0;

    }

    /**
     * Comprueba si el tenista esta en ventaja
     * @return  true: si esta en ventaja
     *          false: si no esta en ventaja
     */
    public boolean estaEnVentaja() {
        return puntos == 4;
    }

    /**
     * Reinicia el valor de los puntos
     */
    public void reiniciarPuntos(){
        puntos = 0;
    }

    /**
     * Reinicia el valor de los juegos
     */
    public void reiniciarJuegos(){
        juegos = 0;
    }

    /**
     * Reinicia todos los valores de puntuacion
     */
    public void reiniciarPuntuacion(){
        puntos = 0;
        juegos = 0;
        sets = 0;
        juegosSetAnterior = 0;
    }

}
