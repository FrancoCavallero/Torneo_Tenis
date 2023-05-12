package Interfaz;

import Modelo.Partido;
import Modelo.Tenista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterfazPartido extends JFrame{


    private JPanel contentPanel;
    private JButton btn_volver;
    private JLabel label_NombreTorneo;
    private JLabel label_sacaJugador1;
    private JLabel label_sacaJugador2;
    private JLabel label_nombreJugador1;
    private JLabel label_nombreJugador2;
    private JLabel label_cantidadSetsGanadosJugador1;
    private JLabel label_cantidadSetsGanadosJugador2;
    private JLabel label_cantidadJuegosGanadosJugador1;
    private JLabel label_cantidadJuegosGanadosJugador2;
    private JLabel label_cantidadPuntosGanadosJugador1;
    private JLabel label_cantidadPuntosGanadosJugador2;
    private JLabel label_resultadoJugador1Set1;
    private JLabel label_resultadoJugador1Set2;
    private JLabel label_resultadoJugador1Set3;
    private JLabel label_resultadoJugador1Set4;
    private JLabel label_resultadoJugador1Set5;
    private JLabel label_resultadoJugador2Set1;
    private JLabel label_resultadoJugador2Set2;
    private JLabel label_resultadoJugador2Set3;
    private JLabel label_resultadoJugador2Set4;
    private JLabel label_resultadoJugador2Set5;
    private JButton btn_revancha;
    private JLabel label_ganador;
    private InterfazTorneo pantallaAnterior;
    private Partido partido;
    private Timer tiempo;
    private int cantidadSetsAGanar;


    public InterfazPartido(Partido partido, InterfazTorneo pantallaAnterior) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establece el tamaño de la ventana
        setSize(500, 320);

        // Ubica la ventana en el centro de la pantalla.
        setLocationRelativeTo(null);

        // Establece el icono de la ventana
        Image icon = Toolkit.getDefaultToolkit().getImage("src/Recursos/pelota.png");
        setIconImage(icon);

        // Establece el contenido del diálogo con el panel "contentPanel"
        setContentPane(contentPanel);

        // Establece la pantalla anterior, para luego poder volver a ella
        this.pantallaAnterior = pantallaAnterior;

        // Establece el partido
        this.partido = partido;
        mostrarSaque();
        mostrarNombreJugadoresYTorneo();

        // Oculta el boton de revancha
        btn_revancha.setVisible(false);

        // Establece los sets a ganar
        this.cantidadSetsAGanar = (partido.getCantSets() / 2) + 1;

        // Establece cada cuanto se va a ejecutar un punto
        this.tiempo = new Timer(400, null);
        tiempo.start();

        // Establecer un ActionListener para el objeto "tiempo"
        tiempo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                iniciarPartido();
            }
        });



        // Al presionar el boton Volver, vuelve a la ventana anterior
        btn_volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempo.stop();
                pantallaAnterior.iniciar();
                cerrar();
            }
        });


        btn_revancha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarLabelsSets();
                btn_revancha.setVisible(false);
                partido.reiniciarPuntaje();
                tiempo.start();
            }
        });
    }



    /**
     * Ejecuta el partido
     */
    public void iniciarPartido(){
        mostrarSaque();
       if (partido.getTenista1().getSets() < cantidadSetsAGanar && partido.getTenista2().getSets() < cantidadSetsAGanar) {
            // Si se termino el juego cambia el saque
            if (partido.getTenista1().getPuntos() == 0 && partido.getTenista2().getPuntos() == 0) {
                partido.cambiarSaque();
                mostrarSaque();
            }
            // Asigna el punto aleatoriamente
            partido.asignarPuntoRandom();
            mostrarPuntuacion(partido.getSetsJugados());
        }else {
            if (partido.getTenista1().getSets() == cantidadSetsAGanar){
                mostrarGandador(partido.getTenista1().getNombre());
            }else {
                mostrarGandador(partido.getTenista2().getNombre());
            }
            tiempo.stop();
            btn_revancha.setVisible(true);
        }

    }






    // Muestra a que jugador le toca sacar
    public void mostrarSaque(){
        label_sacaJugador1.setVisible(partido.getTenista1().leTocaSacar());
        label_sacaJugador2.setVisible(partido.getTenista2().leTocaSacar());
    }


    // Muestra que tenista gano
    public void mostrarGandador(String nombreTenista){
        label_ganador.setText("Ganador: " + nombreTenista);
    }


    // Muestra los datos de los juegos
    public void mostrarJuegos(){
        label_cantidadJuegosGanadosJugador1.setText(String.valueOf(partido.getTenista1().getJuegos()));
        label_cantidadJuegosGanadosJugador2.setText(String.valueOf(partido.getTenista2().getJuegos()));
    }

    // Muestra los datos de puntuacion
    public void mostrarPuntuacion(int cantSetsJugados){
        Tenista puntuacionTenista1 = partido.getTenista1();
        Tenista puntuacionTenista2 = partido.getTenista2();
        label_cantidadPuntosGanadosJugador1.setText(puntuacionTenista1.getPuntosString());
        label_cantidadPuntosGanadosJugador2.setText(puntuacionTenista2.getPuntosString());
        label_cantidadJuegosGanadosJugador1.setText(String.valueOf(puntuacionTenista1.getJuegos()));
        label_cantidadJuegosGanadosJugador2.setText(String.valueOf(puntuacionTenista2.getJuegos()));
        label_cantidadSetsGanadosJugador1.setText(String.valueOf(puntuacionTenista1.getSets()));
        label_cantidadSetsGanadosJugador2.setText(String.valueOf(puntuacionTenista2.getSets()));
        switch (cantSetsJugados) {
            case 0:
                label_resultadoJugador1Set1.setText(String.valueOf(puntuacionTenista1.getJuegos()));
                label_resultadoJugador2Set1.setText(String.valueOf(puntuacionTenista2.getJuegos()));
                break;
            case 1:
                label_resultadoJugador1Set1.setText(String.valueOf(puntuacionTenista1.getJuegosSetAnterior()));
                label_resultadoJugador2Set1.setText(String.valueOf(puntuacionTenista2.getJuegosSetAnterior()));
                label_resultadoJugador1Set2.setText(String.valueOf(puntuacionTenista1.getJuegos()));
                label_resultadoJugador2Set2.setText(String.valueOf(puntuacionTenista2.getJuegos()));
                break;
            case 2:
                label_resultadoJugador1Set2.setText(String.valueOf(puntuacionTenista1.getJuegosSetAnterior()));
                label_resultadoJugador2Set2.setText(String.valueOf(puntuacionTenista2.getJuegosSetAnterior()));
                label_resultadoJugador1Set3.setText(String.valueOf(puntuacionTenista1.getJuegos()));
                label_resultadoJugador2Set3.setText(String.valueOf(puntuacionTenista2.getJuegos()));
                break;
            case 3:
                label_resultadoJugador1Set3.setText(String.valueOf(puntuacionTenista1.getJuegosSetAnterior()));
                label_resultadoJugador2Set3.setText(String.valueOf(puntuacionTenista2.getJuegosSetAnterior()));
                label_resultadoJugador1Set4.setText(String.valueOf(puntuacionTenista1.getJuegos()));
                label_resultadoJugador2Set4.setText(String.valueOf(puntuacionTenista2.getJuegos()));
                break;
            case 4:
                label_resultadoJugador1Set4.setText(String.valueOf(puntuacionTenista1.getJuegosSetAnterior()));
                label_resultadoJugador2Set4.setText(String.valueOf(puntuacionTenista2.getJuegosSetAnterior()));
                label_resultadoJugador1Set5.setText(String.valueOf(puntuacionTenista1.getJuegos()));
                label_resultadoJugador2Set5.setText(String.valueOf(puntuacionTenista2.getJuegos()));
        }
    }


    // Vacia los labels de los sets
    public void vaciarLabelsSets(){
        label_resultadoJugador1Set1.setText(" ");
        label_resultadoJugador2Set1.setText(" ");
        label_resultadoJugador1Set2.setText(" ");
        label_resultadoJugador2Set2.setText(" ");
        label_resultadoJugador1Set3.setText(" ");
        label_resultadoJugador2Set3.setText(" ");
        label_resultadoJugador1Set4.setText(" ");
        label_resultadoJugador2Set4.setText(" ");
        label_resultadoJugador1Set5.setText(" ");
        label_resultadoJugador2Set5.setText(" ");
    }

    // Muestra los nombres de los jugadores y del torneo
    public void mostrarNombreJugadoresYTorneo(){
        label_NombreTorneo.setText(partido.getNombreTorneo());
        label_nombreJugador1.setText(partido.getTenista1().getNombre());
        label_nombreJugador2.setText(partido.getTenista2().getNombre());
    }


    public void iniciar(){
        this.setVisible(true);
    }

    public void detener(){
        this.setVisible(false);
    }

    public void cerrar(){
        this.dispose();
    }



}
