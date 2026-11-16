
package niveles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GradientPaint;

// Controla el escenario submarino.
public class Escenario {

    // Dibuja el fondo del juego.
    public void dibujar(Graphics2D g, int ancho, int alto) {

        // Fondo de agua.
        GradientPaint agua = new GradientPaint(
            0, 0, new Color(5, 55, 115),
            0, 440, new Color(20, 155, 195)
        );

        g.setPaint(agua);
        g.fillRect(0, 0, ancho, alto);

        // Rayos de luz.
        dibujarRayos(g);

        // Suelo.
        g.setColor(new Color(205, 177, 112));
        g.fillRect(0, 440, ancho, 60);

        // Piedras.
        dibujarPiedras(g);
    }

    // Dibuja los rayos de luz submarinos.
    private void dibujarRayos(Graphics2D g) {

        g.setColor(new Color(150, 230, 255, 25));

        for (int i = 0; i < 5; i++) {

            int x = 50 + i * 140;

            int[] puntosX = {
                x, x + 35, x + 100, x + 65
            };

            int[] puntosY = {
                0, 0, 440, 440
            };

            g.fillPolygon(puntosX, puntosY, 4);
        }
    }

    // Dibuja las piedras del fondo.
    private void dibujarPiedras(Graphics2D g) {

        g.setColor(new Color(130, 115, 95));

        g.fillOval(150, 445, 35, 17);
        g.fillOval(300, 450, 45, 15);
        g.fillOval(460, 445, 30, 18);
    }
}
