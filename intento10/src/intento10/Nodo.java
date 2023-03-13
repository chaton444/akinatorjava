/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package intento10;

class Nodo {
    private personaje personaje;
    private Nodo izquierda;
    private Nodo derecha;
    private int altura;

    public Nodo(personaje personaje) {
        this.personaje = personaje;
        this.altura = 1;
    }

    public personaje getPersonaje() {
        return personaje;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
        actualizarAltura();
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
        actualizarAltura();
    }

    public int getAltura() {
        return altura;
    }

    public int getFactorBalance() {
        int alturaIzquierda = (izquierda == null) ? 0 : izquierda.altura;
        int alturaDerecha = (derecha == null) ? 0 : derecha.altura;
        return alturaIzquierda - alturaDerecha;
    }

    private void actualizarAltura() {
        int alturaIzquierda = (izquierda == null) ? 0 : izquierda.altura;
        int alturaDerecha = (derecha == null) ? 0 : derecha.altura;
        altura = 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    public Nodo rotarDerecha() {
        Nodo nuevaRaiz = izquierda;
        izquierda = nuevaRaiz.getDerecha();
        nuevaRaiz.setDerecha(this);
        actualizarAltura();
        nuevaRaiz.actualizarAltura();
        return nuevaRaiz;
    }

    public Nodo rotarIzquierda() {
        Nodo nuevaRaiz = derecha;
        derecha = nuevaRaiz.getIzquierda();
        nuevaRaiz.setIzquierda(this);
        actualizarAltura();
        nuevaRaiz.actualizarAltura();
        return nuevaRaiz;
    }

    public Nodo balancear() {
        actualizarAltura();
        int factorBalance = getFactorBalance();
        if (factorBalance > 1) {
            if (izquierda.getFactorBalance() < 0) {
                izquierda = izquierda.rotarIzquierda();
            }
            return rotarDerecha();
        } else if (factorBalance < -1) {
            if (derecha.getFactorBalance() > 0) {
                derecha = derecha.rotarDerecha();
            }
            return rotarIzquierda();
        }
        return this;
    }
}