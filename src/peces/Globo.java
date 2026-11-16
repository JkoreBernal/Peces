
package peces;

import java.awt.Color;
import java.awt.Graphics;
import habilidades.Expansion;

public class Globo extends Pez {

    private Expansion expansion;

    private final String[] spriteNormal = {
        "...S.S.S...",
        "..SBBBBBS..",
        ".SBBBBBBBS.",
        "SBBBBBBBBBS",
        "BBBBBBBBBBE",
        "SBBBBBBBBBS",
        ".SBBBBBBBS.",
        "..SBBBBBS..",
        "...S.S.S..."
    };

    private final String[] spriteGrande = {
        "......SSSSS......",
        "....SSBBBBBSS....",
        "...SBBBBBBBBBS...",
        "..SBBBBBBBBBBBS..",
        ".SBBBBBBBBBBBBBS.",
        "SBBBBBBBBBBBBBBBS",
        "SBBBBBBBBBBBBBBBS",
        "BBBBBBBBBBBBBBBBE",
        "SBBBBBBBBBBBBBBBS",
        "SBBBBBBBBBBBBBBBS",
        ".SBBBBBBBBBBBBBS.",
        "..SBBBBBBBBBBBS..",
        "...SBBBBBBBBBS...",
        "....SSBBBBBSS....",
        "......SSSSS......"
    };

    public Globo(int x, int y) {
        super("Globo", x, y, 0, 100, Color.CYAN);
        expansion = new Expansion();
    }

    @Override
    public void dibujar(Graphics g) {

        String[] sprite = expansion.estaActiva()
                ? spriteGrande
                : spriteNormal;

        int pixel = PIXEL;

        // Mantiene centrado el pez al cambiar de tamaño.
        int ancho = sprite[0].length() * pixel;
        int alto = sprite.length * pixel;

        int inicioX = (int)x - (ancho - spriteNormal[0].length() * PIXEL) / 2;
        int inicioY = (int)y - (alto - spriteNormal.length * PIXEL) / 2;

        for (int fila = 0; fila < sprite.length; fila++) {
            for (int columna = 0; columna < sprite[fila].length(); columna++) {

                char pixelActual = sprite[fila].charAt(columna);

                switch (pixelActual) {
                    case 'B':
                        g.setColor(color);
                        break;
                    case 'S':
                        g.setColor(Color.ORANGE);
                        break;
                    case 'E':
                        g.setColor(Color.BLACK);
                        break;
                    default:
                        continue;
                }

                g.fillRect(
                    inicioX + columna * pixel,
                    inicioY + fila * pixel,
                    pixel,
                    pixel
                );
            }
        }
    }

    public void inflar() {
        expansion.activar();
    }

    public void desinflar() {
        expansion.desactivar();
    }

    public boolean estaInflado() {
        return expansion.estaActiva();
    }

    public int getAumento() {
        return expansion.getAumento();
    }
}
