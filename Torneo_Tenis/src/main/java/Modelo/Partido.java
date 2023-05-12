package Modelo;
import java.util.Random;


public class Partido {

    private String nombreTorneo;
    private int cantSets;
    private Tenista tenista1;
    private Tenista tenista2;
    private Random random;



    // Constructor ------------------------------------------
    public Partido(String nombreTorneo, int cantSets, Tenista tenista1, Tenista tenista2) {
        this.nombreTorneo = nombreTorneo;
        this.cantSets = cantSets;
        this.tenista1 = tenista1;
        this.tenista2 = tenista2;
        this.random = new Random();
        saqueRandom();

    }


    // Getters ----------------------------------------------

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public int getCantSets() {
        return cantSets;
    }

    public Tenista getTenista1() {
        return tenista1;
    }

    public Tenista getTenista2() {
        return tenista2;
    }

    public int getSetsJugados() {
        int setsJugados = tenista1.getSets() + tenista2.getSets();
        return setsJugados;
    }




    // Utilidad ---------------------------------------------

    /**
     * Elige aleatoriamente que tenista empieza el saque
     */
    public void saqueRandom(){
        int saqueRandom = random.nextInt(2);

        if (saqueRandom == 0) {
            tenista1.setLeTocaSacar(true);
            tenista2.setLeTocaSacar(false);
        } else {
            tenista1.setLeTocaSacar(false);
            tenista2.setLeTocaSacar(true);
        }
    }

    /**
     * Cambia el tenista que saca
     */
    public void cambiarSaque(){
        if (tenista1.leTocaSacar()){
            tenista1.setLeTocaSacar(false);
            tenista2.setLeTocaSacar(true);
        }else {
            tenista1.setLeTocaSacar(true);
            tenista2.setLeTocaSacar(false);
        }
    }



    /**
     * asigna aleatoriamente un punto a uno de los jugadores
     * @return  true:   si el ganador al ganar un juego, gana el set
     *          false:  si el ganador al ganar un juego, no gana el set
     */
    public void asignarPuntoRandom(){
        int puntoRandom = random.nextInt(100);
        // Si el tenista1 gano el punto
        if (puntoRandom <= tenista1.getProbabilidad_ganar()){
            agregarPunto(tenista1, tenista2);
        // Si el tenista2 gana el punto
        }else {
            agregarPunto(tenista2, tenista1);
        }
    }





    /**
     * Comprueba si es que el tenista al ganar un juego, gana el set
     * @param ganadorDelJuego: es el tenista con el que compruebo si gana el set
     * @param perdedorDelJuego: es el tenista que perdio el juego
     * @return  true:   si el ganador al ganar un juego, gana el set
     *          false:  si el ganador al ganar un juego, no gana el set
     */
    public boolean esGanarSet(Tenista ganadorDelJuego, Tenista perdedorDelJuego){
        return ((ganadorDelJuego.getJuegos() == 6 && perdedorDelJuego.getJuegos() <= 4)
            || ganadorDelJuego.getJuegos() == 7);
    }



    /**
     * Asigna un punto al ganador del punto y si gano el juego le suma 1 a los juegos ganados.
     * Si gana el juego vacia los puntos de los dos jugadores
     * Si gana el set vacia los puntos de los dos jugadores
     * @param ganadorDelPunto: es el tenista que gano un punto
     * @param perdedorDelPunto: es el tenista que perdio el punto
     * @return  true: si al sumar el punto el tenista gano el set
     *          false: si al sumar el punto el tenista no gano el set
     */
    public void agregarPunto(Tenista ganadorDelPunto, Tenista perdedorDelPunto){
        // Si el tenista2 esta en ventaja
        if (perdedorDelPunto.estaEnVentaja()){
            // Le quita la ventaja al tenista2
            perdedorDelPunto.restarPunto();
        }else {
            ganadorDelPunto.ganarPunto();
            // Si al ganar el punto, gana un juego
            if (esGanarJuego(ganadorDelPunto, perdedorDelPunto)){
                ganadorDelPunto.ganarJuego();
                perdedorDelPunto.reiniciarPuntos();
                // Si al ganar el juego, gana el set
                if (esGanarSet(ganadorDelPunto, perdedorDelPunto)){
                    ganadorDelPunto.setJuegosSetAnterior(ganadorDelPunto.getJuegos());
                    perdedorDelPunto.setJuegosSetAnterior(perdedorDelPunto.getJuegos());
                    perdedorDelPunto.reiniciarJuegos();
                    ganadorDelPunto.ganarSet();
                }
            }
        }
    }




    /**
     * Reinicia el puntaje de los tenistas
     */
    public void reiniciarPuntaje(){
        tenista1.reiniciarPuntuacion();
        tenista2.reiniciarPuntuacion();
        saqueRandom();
    }


    /**
     * Comprueba si es que el tenista al ganar un punto, gana el juego
     * @param ganadorDelPunto: es el tenista con el que compruebo si gano el juego
     * @param perdedorDelPunto: es el tenista que perdio el punto
     * @return  true:   si el ganador al ganar un punto, gana el juego
     *          false:  si el ganador al ganar un punto, no gana el juego
     */
    public boolean esGanarJuego(Tenista ganadorDelPunto, Tenista perdedorDelPunto){
        return  (ganadorDelPunto.getPuntos() >= 3
                && (ganadorDelPunto.getPuntos() - perdedorDelPunto.getPuntos()) >= 2);
    }






}
