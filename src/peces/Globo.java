
package peces;

import java.awt.Color;
import java.awt.Graphics;
import habilidades.Expansion;

/**
 * Clase derivada de Pez.
 *
 * Hereda los atributos y comportamientos generales de la clase base
 * e incorpora una habilidad especializada de expansión.
 */
public class Globo extends Pez {

    // Objeto encargado de administrar la habilidad de expansión.
    private Expansion expansion;

    // Representación gráfica del pez en su estado normal.
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

    // Representación gráfica del pez cuando activa su expansión.
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

    /**
     * Constructor de la clase Globo.
     *
     * Utiliza super() para invocar el constructor de Pez
     * e inicializar los atributos heredados.
     *
     * También inicializa el objeto encargado de la expansión.
     */
    public Globo(int x, int y) {
        super("Globo", x, y, 0, 100, Color.CYAN);
        expansion = new Expansion();
    }

    /**
     * Sobrescribe el método abstracto dibujar() heredado de Pez.
     *
     * Selecciona el sprite correspondiente según el estado
     * de la habilidad de expansión.
     */
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

    // Activa la habilidad de expansión.
    public void inflar() {
        expansion.activar();
    }

    // Desactiva la habilidad de expansión.
    public void desinflar() {
        expansion.desactivar();
    }

    // Consulta si la habilidad de expansión está activa.
    public boolean estaInflado() {
        return expansion.estaActiva();
    }

    // Obtiene el aumento configurado para la expansión.
    public int getAumento() {
        return expansion.getAumento();
    }
}
