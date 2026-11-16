
package habilidades;

// Habilidad eléctrica de la Anguila.
public class Electricidad {

    private int potencia;
    private boolean activa;

    public Electricidad() {
        this.potencia = 25;
        this.activa = false;
    }

    public void activar() {
        activa = true;
    }

    public void desactivar() {
        activa = false;
    }

    public int getPotencia() {
        return activa ? potencia : 0;
    }

    public boolean estaActiva() {
        return activa;
    }
}
