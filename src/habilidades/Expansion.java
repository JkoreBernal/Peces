
package habilidades;

// Habilidad especial de expansión.
public class Expansion {

    private int aumento;
    private boolean activa;

    public Expansion() {
        this.aumento = 15;
        this.activa = false;
    }

    public void activar() {
        activa = true;
    }

    public void desactivar() {
        activa = false;
    }

    public int getAumento() {
        return activa ? aumento : 0;
    }

    public boolean estaActiva() {
        return activa;
    }
}
