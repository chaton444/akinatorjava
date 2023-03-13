package intento10;

import java.util.Scanner;

public class AdivinanzaDePersonajes {
    private static Nodo raiz;
    private static Scanner sc = new Scanner(System.in);
    public String primerpersonaje="";

    public static void main(String[] args) {
        jugar();
    }
    // nuestro metodo empieza con un nodo llamado raiz el cual si es nulo inicialmente va a enviar como parametros a goku con una
    //pregunta que caracteriza a goku a nuestra clase persona
    //despues entra en un ciclo while que se ejecuta hasta que el usuario decida no jugar más 
    //En este ciclo el nodo actual se inicializa en la raiz del arbol, y luego se sigue descendiendo por 
    //el arbol preguntando al usuario por la caracteristica del personaje actual  moviendose a la izquierda 
    //o derecha segun la respuesta dada hasta que se llega a una hoja del arbol que es un nodo sin hijos
    //Una vez que se ha llegado a una hoja se pregunta al usuario si el personaje adivinado es correcto
    //Si lo es, se muestra un mensaje de exito, y si no se llama al metodo aprender() para añadir el nuevo personaje al arbol
    //Finalmente  se pregunta al usuario si quiere jugar de nuevo. Si no quiere, se sale del ciclo while y el metodo termina
    private static void jugar() {
        System.out.println("Piensa en un personaje y yo trataré de adivinar quien es.");
        if (raiz == null) {
        raiz = new Nodo(new personaje("Goku", "puede transformarse en un Super Saiyan","https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/059edf98-d44a-4996-bcc6-d57401143bb5/d1samv0-08e99b8e-9fde-476d-873b-6d99e9e51093.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzA1OWVkZjk4LWQ0NGEtNDk5Ni1iY2M2LWQ1NzQwMTE0M2JiNVwvZDFzYW12MC0wOGU5OWI4ZS05ZmRlLTQ3NmQtODczYi02ZDk5ZTllNTEwOTMuanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.InTiOqv8pPUKTFHzN9e5XeAChP2eI9WkE-4u1s7giTQ"));
        }
        while (true) {
            Nodo nodoActual = raiz;
            while (nodoActual.getIzquierda() != null || nodoActual.getDerecha() != null) {
                System.out.println(nodoActual.getPersonaje().getCaracteristica() + " (s/n)");
                String respuesta = sc.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    nodoActual = nodoActual.getIzquierda();
                } else if (respuesta.equalsIgnoreCase("n")) {
                    nodoActual = nodoActual.getDerecha();
                }
            }
            System.out.println("¿Es " + nodoActual.getPersonaje().getNombre() + "? (s/n)");
            String respuestaFinal = sc.nextLine();
            if (respuestaFinal.equalsIgnoreCase("s")) {
                System.out.println("¡Si  adivine!");
            } else if (respuestaFinal.equalsIgnoreCase("n")) {
                aprender(nodoActual);
            }
            System.out.println("¿Quieres jugar de nuevo? (s/n)");
            String respuestaReiniciar = sc.nextLine();
            if (!respuestaReiniciar.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

    private static void aprender(Nodo nodoActual) {
        System.out.println("No sé quien es el personaje :/ ¿Quien es?");
        String nombrePersonaje = sc.nextLine();
        System.out.println("Escribe una pregunta que me permita diferenciar a " + nodoActual.getPersonaje().getNombre() + " de " + nombrePersonaje);
        String pregunta = sc.nextLine();
        System.out.println("¿Cual es la respuesta para " + nombrePersonaje + "? (s/n)");
        String respuestaNueva = sc.nextLine();

        // Crear nuevos nodos para el personaje nuevo y la pregunta nueva
        personaje personajeNuevo = new personaje(nombrePersonaje, "","");
        Nodo nodoNuevo = new Nodo(personajeNuevo);
        Nodo nodoPregunta = new Nodo(new personaje("", pregunta,""));

        if (respuestaNueva.equalsIgnoreCase("s")) {
            nodoPregunta.setIzquierda(nodoNuevo);
            nodoPregunta.setDerecha(nodoActual);
        } else if (respuestaNueva.equalsIgnoreCase("n")) {
            nodoPregunta.setIzquierda(nodoActual);
            nodoPregunta.setDerecha(nodoNuevo);
            
        }

        // Conectar la pregunta nueva al árbol
        if (nodoActual == raiz) {
            raiz = nodoPregunta;
        } else {
            Nodo padre = buscarPadre(raiz, nodoActual);
            
            if (padre.getIzquierda() == nodoActual) {
                padre.setIzquierda(nodoPregunta);
            } else {
                padre.setDerecha(nodoPregunta);
            }
        }
          Nodo nodoPadre = buscarPadre(raiz, nodoActual);


    while (nodoPadre != null) {
        nodoPadre = nodoPadre.balancear();
        nodoPadre = buscarPadre(raiz, nodoPadre);
    }
    }
    private static Nodo buscarPadre(Nodo nodoActual, Nodo nodoHijo) {
    if (nodoActual.getIzquierda() == nodoHijo || nodoActual.getDerecha() == nodoHijo) {
        return nodoActual;
    }
    Nodo izquierda = null;
    Nodo derecha = null;
    if (nodoActual.getIzquierda() != null) {
        izquierda = buscarPadre(nodoActual.getIzquierda(), nodoHijo);
    }
    if (nodoActual.getDerecha() != null) {
        derecha = buscarPadre(nodoActual.getDerecha(), nodoHijo);
    }
    if (izquierda != null) {
        return izquierda;
    } else {
        return derecha;
    }
}
    
  
}