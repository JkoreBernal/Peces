
package habilidades;

// Habilidad especial de rebote.
public class Rebote {

    private double fuerza;
    private boolean activa;

    public Rebote() {
        this.fuerza = 0.8;
        this.activa = false;
    }

    public void activar() {
        activa = true;
    }

    public void desactivar() {
        activa = false;
    }

    // Calcula la fuerza del rebote.
    public double calcularRebote(double velocidad) {
        if (activa) {
            return -velocidad * fuerza;
        }

        return 0;
    }

    public boolean estaActiva() {
        return activa;
    }
}
