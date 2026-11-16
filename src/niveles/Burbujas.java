
package niveles;

import java.awt.Color;
import java.awt.Graphics2D;

// Controla las burbujas del escenario.
public class Burbujas {

    private int[] x = {
        40, 110, 190, 270, 350, 440, 510, 630
    };

    private int[] y = {
        350, 280, 390, 320, 250, 370, 290, 400
    };

    private int[] radio = {
        7, 5, 9, 6, 8, 5, 10, 6
    };

    // Actualiza el movimiento de las burbujas.
    public void actualizar() {

        for (int i = 0; i < y.length; i++) {

            y[i]--;

            if (y[i] < 100) {
                y[i] = 435;
            }
        }
    }

    // Dibuja las burbujas.
    public void dibujar(Graphics2D g) {

        g.setColor(new Color(210, 245, 255, 150));

        for (int i = 0; i < y.length; i++) {

            int r = radio[i];

            g.drawOval(x[i], y[i], r * 2, r * 2);

            g.fillOval(
                x[i] + 3,
                y[i] + 3,
                3,
                3
            );
        }
    }
}
