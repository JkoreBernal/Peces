
package peces;

import java.awt.Color;
import java.awt.Graphics;
import habilidades.Rebote;

/**
 * Clase derivada de Pez.
 *
 * Hereda los atributos y comportamientos generales de la clase base
 * e incorpora una habilidad especializada de rebote.
 */
public class Betta extends Pez {

    // Objeto encargado de administrar la habilidad de rebote.
    private Rebote rebote;

    /**
     * Constructor de la clase Betta.
     *
     * Utiliza super() para invocar el constructor de Pez
     * e inicializar los atributos heredados.
     *
     * También inicializa el objeto encargado de la habilidad de rebote.
     */
    public Betta(int x, int y) {
        super("Betta", x, y, 0, 150, Color.RED);
        rebote = new Rebote();
    }

    /**
     * Sobrescribe el método abstracto dibujar() heredado de Pez.
     *
     * Implementa la representación gráfica específica del pez Betta
     * mediante un sprite personalizado.
     */
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

    // Activa la habilidad de rebote.
    public void activarRebote() {
        rebote.activar();
    }

    // Desactiva la habilidad de rebote.
    public void desactivarRebote() {
        rebote.desactivar();
    }

    // Consulta si la habilidad de rebote se encuentra activa.
    public boolean tieneRebote() {
        return rebote.estaActiva();
    }

    // Calcula la velocidad resultante mediante la habilidad de rebote.
    public double calcularRebote(double velocidad) {
        return rebote.calcularRebote(velocidad);
    }
}
