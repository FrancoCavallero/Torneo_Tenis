import org.junit.Assert;
import org.junit.Test;
import Modelo.*;

public class Tests {

    @Test
    public void testGanarPunto() {
        Tenista tenista1 = new Tenista("Juan", 10);
        Tenista tenista2 = new Tenista("Juan2", 10);
        Partido partido = new Partido("Hola", 3, tenista1, tenista2);
        for (int i = 0; i < 2; i++) {
            partido.agregarPunto(tenista1, tenista2);
        }
        Assert.assertEquals(2, tenista1.getPuntos());
    }

    @Test
    public void testDesempatePuntos() {
        Tenista tenista1 = new Tenista("Juan", 10);
        Tenista tenista2 = new Tenista("Juan2", 10);
        Partido partido = new Partido("Hola", 3, tenista1, tenista2);
        for (int i = 0; i < 2; i++) {
            partido.agregarPunto(tenista1, tenista2);
        }
        for (int i = 0; i < 3; i++) {
            partido.agregarPunto(tenista2, tenista1);
        }
        Assert.assertEquals(2, tenista1.getPuntos());
        Assert.assertEquals(3, tenista2.getPuntos());
        partido.agregarPunto(tenista2, tenista1);
        Assert.assertEquals(0, tenista1.getPuntos());
        Assert.assertEquals(0, tenista2.getPuntos());
        Assert.assertEquals(1, tenista2.getJuegos());
    }

    @Test
    public void testGanarJuego() {
        Tenista tenista1 = new Tenista("Juan", 10);
        Tenista tenista2 = new Tenista("Juan2", 10);
        Partido partido = new Partido("Hola", 3, tenista1, tenista2);
        for (int i = 0; i < 4; i++) {
            partido.agregarPunto(tenista1, tenista2);
        }
        Assert.assertEquals(1, tenista1.getJuegos());
        Assert.assertEquals(1, tenista1.getPuntos());
    }

    @Test
    public void testDesempateJuegos() {
        Tenista tenista1 = new Tenista("Juan", 10);
        Tenista tenista2 = new Tenista("Juan2", 10);
        Partido partido = new Partido("Hola", 3, tenista1, tenista2);
        for (int i = 0; i < 15; i++) {
            partido.agregarPunto(tenista1, tenista2);
        }
        for (int i = 0; i < 20; i++) {
            partido.agregarPunto(tenista2, tenista1);
        }
        Assert.assertEquals(5, tenista1.getJuegos());
        Assert.assertEquals(6, tenista2.getJuegos());
        partido.agregarPunto(tenista2, tenista1);
        Assert.assertEquals(0, tenista1.getJuegos());
        Assert.assertEquals(0, tenista2.getJuegos());
        Assert.assertEquals(1, tenista2.getSets());
    }

    @Test
    public void testGanarSet() {
        Tenista tenista1 = new Tenista("Juan", 10);
        Tenista tenista2 = new Tenista("Juan2", 10);
        Partido partido = new Partido("Hola", 3, tenista1, tenista2);
        for (int i = 0; i < 22; i++) {
            partido.agregarPunto(tenista1, tenista2);
        }
        Assert.assertEquals(1, tenista1.getJuegos());
        Assert.assertEquals(1, tenista1.getPuntos());
        Assert.assertEquals(1, tenista1.getSets());
    }


    @Test
    public void testQuitarVentaja() {
        Tenista tenista1 = new Tenista("Juan", 10);
        Tenista tenista2 = new Tenista("Juan2", 10);
        Partido partido = new Partido("Hola", 3, tenista1, tenista2);
        for (int i = 0; i < 15; i++) {
            partido.agregarPunto(tenista1, tenista2);
            partido.agregarPunto(tenista2, tenista1);
        }
        Assert.assertEquals(3, tenista1.getPuntos());
        Assert.assertEquals(0, tenista1.getJuegos());
        Assert.assertEquals(0, tenista1.getSets());
        Assert.assertEquals(3, tenista2.getPuntos());
        Assert.assertEquals(0, tenista2.getJuegos());
        Assert.assertEquals(0, tenista2.getSets());
        partido.agregarPunto(tenista1, tenista2);
        partido.agregarPunto(tenista1, tenista2);
        Assert.assertEquals(0, tenista1.getPuntos());
        Assert.assertEquals(1, tenista1.getJuegos());
        Assert.assertEquals(0, tenista1.getSets());
        Assert.assertEquals(0, tenista2.getPuntos());
        Assert.assertEquals(0, tenista2.getJuegos());
        Assert.assertEquals(0, tenista2.getSets());
    }



}
