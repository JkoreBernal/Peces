
import java.awt.Color;
import java.awt.Graphics;

// Representa al enemigo del videojuego.
public class Pulpo {

    // Atributos del pulpo.
    private int x;
    private int y;
    private Color color;

    // Constructor del enemigo.
    public Pulpo(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = new Color(150, 50, 180);
    }

    // Dibuja al pulpo en el escenario.
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

        g.setColor(Color.BLACK);
        g.fillOval(x + 13, y + 15, 4, 4);
        g.fillOval(x + 31, y + 15, 4, 4);
    }
}
