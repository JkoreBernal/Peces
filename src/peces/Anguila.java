
package peces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import habilidades.Electricidad;

public class Anguila extends Pez {

    private Electricidad electricidad;

    // Sprite de la anguila.
    private final String[] sprite = {
        "......FFF.............",
        "....BBBBBBBB..........",
        "..BBBBBBBBBBBBBB......",
        ".BBBBBBBBBBBBBBBBBBE..",
        "BBBBBBBBBBBBBBBBBBBBB.",
        ".BBBBBBBBBBBBBBBBBBB..",
        "..BBBBBBBBBBBBBBBB....",
        "....BBBBBBBBBB........",
        "......BBBBBB..........",
        "........FFF..........."
    };

    public Anguila(int x, int y) {
        super("Anguila", x, y, 0, 120, new Color(220, 190, 35));
        electricidad = new Electricidad();
    }

    @Override
    public void dibujar(Graphics g) {

        for (int fila = 0; fila < sprite.length; fila++) {
            for (int columna = 0;
                 columna < sprite[fila].length(); columna++) {

                char pixel = sprite[fila].charAt(columna);

                switch (pixel) {
                    case 'B':
                        g.setColor(color);
                        break;
                    case 'F':
                        g.setColor(new Color(160, 115, 20));
                        break;
                    case 'E':
                        g.setColor(Color.BLACK);
                        break;
                    default:
                        continue;
                }

                g.fillRect(
                    (int) x + columna * PIXEL,
                    (int) y + fila * PIXEL,
                    PIXEL,
                    PIXEL
                );
            }
        }

        if (electricidad.estaActiva()) {
            dibujarDescargas(g);
        }
    }

    // Dibuja seis rayos alrededor de la anguila.
    private void dibujarDescargas(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(Color.CYAN);
        g2.setStroke(new BasicStroke(3));

        int centroX = (int) x + 55;
        int centroY = (int) y + 25;

        int[][] direcciones = {
            { 0, -65 },
            { 55, -35 },
            { 55, 35 },
            { 0, 65 },
            { -55, 35 },
            { -55, -35 }
        };

        for (int[] direccion : direcciones) {
            dibujarRayo(
                g2,
                centroX,
                centroY,
                centroX + direccion[0],
                centroY + direccion[1]
            );
        }

        g2.dispose();
    }

    // Dibuja un rayo con forma de zigzag.
    private void dibujarRayo(
            Graphics2D g,
            int x1, int y1,
            int x2, int y2) {

        int medioX = (x1 + x2) / 2;
        int medioY = (y1 + y2) / 2;

        int[] puntosX = {
            x1,
            x1 + (medioX - x1) / 2 + 5,
            medioX - 5,
            medioX + 5,
            x2
        };

        int[] puntosY = {
            y1,
            y1 + (medioY - y1) / 2 - 5,
            medioY + 5,
            medioY - 5,
            y2
        };

        g.drawPolyline(puntosX, puntosY, puntosX.length);
    }

    public void electrocutar() {
        electricidad.activar();
    }

    public void detenerElectricidad() {
        electricidad.desactivar();
    }

    public boolean tieneElectricidad() {
        return electricidad.estaActiva();
    }

    public int getPotencia() {
        return electricidad.getPotencia();
    }
}
