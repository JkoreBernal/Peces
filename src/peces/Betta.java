
package peces;

import java.awt.Color;
import java.awt.Graphics;
import habilidades.Rebote;

// Clase hija que hereda de Pez.
public class Betta extends Pez {

    private Rebote rebote;

    public Betta(int x, int y) {
        super("Betta", x, y, 0, 150, Color.RED);
        rebote = new Rebote();
    }

    // Dibuja el sprite del pez Betta.
    @Override
    public void dibujar(Graphics g) {

        String[] sprite = {
            "...FFF......",
            "..BBBBBB....",
            "FBBBBBBBWE..",
            "..BBBBBBBB..",
            "...BBBB.....",
            "....FFF....."
        };

        for (int fila = 0; fila < sprite.length; fila++) {
            for (int columna = 0;
                 columna < sprite[fila].length(); columna++) {

                char pixel = sprite[fila].charAt(columna);

                switch (pixel) {
                    case 'B':
                        g.setColor(color);
                        break;
                    case 'F':
                        g.setColor(color.darker());
                        break;
                    case 'W':
                        g.setColor(Color.WHITE);
                        break;
                    case 'E':
                        g.setColor(Color.BLACK);
                        break;
                    default:
                        continue;
                }

                g.fillRect(
                    (int)x + columna * PIXEL,
                    (int)y + fila * PIXEL,
                    PIXEL,
                    PIXEL
                );
            }
        }
    }

    public void activarRebote() {
        rebote.activar();
    }

    public void desactivarRebote() {
        rebote.desactivar();
    }

    public boolean tieneRebote() {
        return rebote.estaActiva();
    }

    public double calcularRebote(double velocidad) {
        return rebote.calcularRebote(velocidad);
    }
}
