
package enemigos;

import java.awt.Color;
import java.awt.Graphics;
import peces.Pez;

// Representa al enemigo del videojuego.
public class Pulpo {

    private int x;
    private int y;
    private Color color;

    // Constructor.
    public Pulpo(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = new Color(150, 50, 180);
    }

    // Detecta si un pez golpea al pulpo.
    public boolean colisiona(Pez pez) {
        double px = pez.getX() + 25;
        double py = pez.getY() + 15;

        return px >= x - 25
            && px <= x + 100
            && py >= y - 25
            && py <= y + 100;
    }

    // Dibuja al pulpo.
    public void dibujar(Graphics g) {
        g.setColor(color);

        // Cabeza.
        g.fillOval(x, y, 45, 40);

        // Tentáculos.
        g.fillRect(x + 5, y + 30, 7, 20);
        g.fillRect(x + 18, y + 30, 7, 20);
        g.fillRect(x + 32, y + 30, 7, 20);

        // Ojos.
        g.setColor(Color.WHITE);
        g.fillOval(x + 9, y + 12, 10, 10);
        g.fillOval(x + 27, y + 12, 10, 10);

        // Pupilas.
        g.setColor(Color.BLACK);
        g.fillOval(x + 13, y + 15, 4, 4);
        g.fillOval(x + 31, y + 15, 4, 4);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
