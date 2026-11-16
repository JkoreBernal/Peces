
package niveles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Path2D;

// Controla las algas decorativas del escenario.
public class Algas {

    // Dibuja un alga individual.
    public void dibujar(
            Graphics2D g,
            int x,
            int y,
            int altura,
            Color color) {

        g.setColor(color);
        g.setStroke(new BasicStroke(5));

        Path2D alga = new Path2D.Double();

        alga.moveTo(x, y);

        alga.curveTo(
            x - 20, y - altura / 3,
            x + 20, y - altura * 2 / 3,
            x, y - altura
        );

        g.draw(alga);

        g.setColor(color.brighter());

        g.drawLine(
            x, y - altura / 3,
            x - 12, y - altura / 3 - 12
        );

        g.drawLine(
            x, y - altura * 2 / 3,
            x + 12, y - altura * 2 / 3 - 12
        );
    }

    // Dibuja todas las algas.
    public void dibujarTodas(Graphics2D g, int ancho) {

        dibujar(g, 25, 445, 110, new Color(25, 130, 75));

        dibujar(g, 55, 445, 80, new Color(40, 160, 90));

        dibujar(g, 180, 445, 95, new Color(20, 115, 70));

        dibujar(g, ancho - 40, 445, 120, new Color(25, 140, 75));

        dibujar(g, ancho - 75, 445, 85, new Color(40, 170, 95));
    }
}
