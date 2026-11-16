
package niveles;

import java.awt.Graphics2D;

// Controla los efectos de movimiento de cámara.
public class Camara {

    private int retroceso = 0;

    // Activa el retroceso de cámara.
    public void activar() {
        retroceso = 16;
    }

    // Actualiza el efecto.
    public void actualizar() {

        if (retroceso > 0) {
            retroceso--;
        }
    }

    // Aplica el desplazamiento visual.
    public void aplicar(Graphics2D g) {

        if (retroceso > 0) {

            int desplazamiento =
                (retroceso % 2 == 0) ? 5 : -5;

            g.translate(desplazamiento, 0);
        }
    }

    // Reinicia la cámara.
    public void reiniciar() {
        retroceso = 0;
    }

    // Comprueba si está activa.
    public boolean estaActiva() {
        return retroceso > 0;
    }
}
