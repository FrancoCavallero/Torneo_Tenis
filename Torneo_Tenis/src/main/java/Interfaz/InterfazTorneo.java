package Interfaz;

import Modelo.Tenista;
import Modelo.Partido;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class InterfazTorneo extends JFrame {
    private JLabel label_titulo;
    private JTextField txt_nombreToreneo;
    private JComboBox cmb_cantSets;
    private JLabel label_cantSets;
    private JLabel label_nombreTorneo;
    private JTextField txt_nombreJugador1;
    private JTextField txt_nombreJugador2;
    private JSlider slider_problabilidades;
    private JLabel label_jugador1;
    private JLabel label_jugador2;
    private JLabel label_probabilidadJugador1;
    private JLabel label_probabilidadJugador2;
    private JLabel label_probabilidad;
    private JPanel contentPanel;
    private JButton btn_empezarPartida;
    private JLabel label_error;


    public InterfazTorneo() {

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

        // Oculta el componente "label_error" inicialmente para que no sea visible
        label_error.setText(" ");


        // Agrega un KeyListener al contenedor principal
        contentPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Verifica si la tecla presionada es "Enter"
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Ejecuta la acción del botón
                    btn_empezarPartida.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        // Establece el foco en el contenedor principal para capturar eventos de teclado
        contentPanel.setFocusable(true);
        contentPanel.requestFocus();




        // Toma los valores del slider de probabilidad y se lo asigna a los labels
        slider_problabilidades.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider_problabilidades.getValue();
                if (value < 10){
                    label_probabilidadJugador1.setText(String.valueOf(100 - value + " %"));
                    label_probabilidadJugador2.setText("0" + value + " %");
                } else if (value > 90){
                    label_probabilidadJugador1.setText("0" + (100 - value + " %"));
                    label_probabilidadJugador2.setText(String.valueOf(value + " %"));
                }else {
                    label_probabilidadJugador1.setText(String.valueOf(100 - value + " %"));
                    label_probabilidadJugador2.setText(String.valueOf(value + " %"));
                }
            }
        });



        //
        btn_empezarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (estanCargadosDatos()){
                    Tenista tenista1 = new Tenista(txt_nombreJugador1.getText(),100 - slider_problabilidades.getValue());
                    Tenista tenista2 = new Tenista(txt_nombreJugador2.getText(), slider_problabilidades.getValue());
                    Partido partido = new Partido(txt_nombreToreneo.getText(), Integer.parseInt(cmb_cantSets.getSelectedItem().toString()), tenista1, tenista2);
                    detener();
                    InterfazPartido interfazPartido = new InterfazPartido(partido, obtenerseASiMismo());
                    interfazPartido.iniciar();
                }
            }
        });
    }


    // Verifica que todos los campos tengan datos

    /**
     * Verifica que todos los campos tengan datos, y muestra un mensaje de error si no estan cargados
     * @return  true:   si todos los campos estan cargados
     *          false:  si no todos los campoes estan cargados
     */
    public boolean estanCargadosDatos() {
        if ((!Objects.equals(txt_nombreToreneo.getText(), "")) && (!Objects.equals(txt_nombreJugador1.getText(), ""))
                && (!Objects.equals(txt_nombreJugador2.getText(), ""))){
            label_error.setText(" ");
            return true;
        }
        label_error.setText("Los campos no pueden estar vacios");
        return false;

    }

    public void iniciar() {
        this.setVisible(true);
    }

    public void detener() {
        this.setVisible(false);
    }

    public InterfazTorneo obtenerseASiMismo(){
        return this;
    }






}
