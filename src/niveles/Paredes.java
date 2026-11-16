
package niveles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

// Controla las paredes del escenario.
public class Paredes {

    // Dibuja los límites laterales.
    public void dibujar(Graphics2D g, int ancho) {

        g.setColor(new Color(100, 210, 230, 170));
        g.setStroke(new BasicStroke(5));

        // Pared izquierda.
        g.drawLine(8, 70, 8, 440);

        // Pared derecha.
        g.drawLine(ancho - 8, 70, ancho - 8, 440);

        // Restaurar grosor.
        g.setStroke(new BasicStroke(1));
    }
}
