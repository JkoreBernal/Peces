
package peces;

import java.awt.Color;
import java.awt.Graphics;

// Clase padre de todos los peces.
public abstract class Pez {

    protected String nombre;
    protected double x, y;
    protected double velocidadX, velocidadY;
    protected int resistencia;
    protected Color color;
    protected boolean lanzado;

    protected final int PIXEL = 5;

    // Constructor.
    public Pez(String nombre, int x, int y,
               int velocidad, int resistencia, Color color) {

        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.velocidadX = velocidad;
        this.velocidadY = 0;
        this.resistencia = resistencia;
        this.color = color;
        this.lanzado = false;
    }

    // Cada pez dibujará su propio sprite.
    public abstract void dibujar(Graphics g);

    // Lanza al personaje.
    public void lanzar(double fuerzaX, double fuerzaY) {
        velocidadX = fuerzaX;
        velocidadY = fuerzaY;
        lanzado = true;
    }

    // Actualiza la posición.
    public void mover() {
        if (lanzado) {
            velocidadY += 0.25;
            x += velocidadX;
            y += velocidadY;
        }
    }

    // Reinicia la posición y el movimiento.
    public void reiniciar(int nuevaX, int nuevaY) {
        x = nuevaX;
        y = nuevaY;
        velocidadX = 0;
        velocidadY = 0;
        lanzado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(double velocidadY) {
        this.velocidadY = velocidadY;
    }

    public boolean isLanzado() {
        return lanzado;
    }

    public void setLanzado(boolean lanzado) {
        this.lanzado = lanzado;
    }
}
