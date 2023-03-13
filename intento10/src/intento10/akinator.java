/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package intento10;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Rosita
 */
public class akinator extends javax.swing.JFrame {

     public Nodo raiz;
     public boolean presionado=false;
        URL url;
        Object[] options = {"Sí", "No"};
         ImageIcon normal = new ImageIcon(getClass().getResource("normal.png"));
         ImageIcon casi = new ImageIcon(getClass().getResource("casi.png"));
         ImageIcon fallo = new ImageIcon(getClass().getResource("falle.png"));
         ImageIcon atino = new ImageIcon(getClass().getResource("atino.png"));
         ImageIcon inicio = new ImageIcon(getClass().getResource("inicio.png"));
         ImageIcon chat = new ImageIcon(getClass().getResource("chat.ico"));
         ImageIcon limpieza;
        Icon icono = new ImageIcon(chat.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));

    public akinator() {
   
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("akinatorfoto.png")).getImage());

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
    public void jugar() {
     fondo.setIcon(normal);

    if (raiz == null) {
        raiz = new Nodo(new personaje("Goku", "puede transformarse en un Super Saiyan","https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/059edf98-d44a-4996-bcc6-d57401143bb5/d1samv0-08e99b8e-9fde-476d-873b-6d99e9e51093.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzA1OWVkZjk4LWQ0NGEtNDk5Ni1iY2M2LWQ1NzQwMTE0M2JiNVwvZDFzYW12MC0wOGU5OWI4ZS05ZmRlLTQ3NmQtODczYi02ZDk5ZTllNTEwOTMuanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.InTiOqv8pPUKTFHzN9e5XeAChP2eI9WkE-4u1s7giTQ"));
    }
    while (true) {
         btnjugar.setVisible(false);
        Nodo nodoActual = raiz;
        while (nodoActual.getIzquierda() != null || nodoActual.getDerecha() != null) {
            txt.setText(nodoActual.getPersonaje().getCaracteristica());
            int respuestaUsuario = JOptionPane.showOptionDialog(null, nodoActual.getPersonaje().getCaracteristica(), "menu de respuestas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
            if (respuestaUsuario == JOptionPane.YES_OPTION) {
                fondo.setIcon(casi);
                nodoActual = nodoActual.getIzquierda();
            } else if (respuestaUsuario == JOptionPane.NO_OPTION) {
                 fondo.setIcon(casi);
                nodoActual = nodoActual.getDerecha();
            }
        }
        txt.setText("¿Es " + nodoActual.getPersonaje().getNombre() + "?");
        int respuestaUsuario = JOptionPane.showOptionDialog(null, "", "menu de respuestas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if (respuestaUsuario == JOptionPane.YES_OPTION) {
            imagen.setVisible(true);
            fondo.setIcon(atino);
              try {
            // Crear una URL a la imagen en línea
            URL url = new URL(nodoActual.getPersonaje().getUrl());
            ImageIcon personaje= new ImageIcon(url);
            // Obtener la imagen del ImageIcon y ajustar su tamaño
            Image imagen1 = personaje.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            // Crear un nuevo ImageIcon a partir de la imagen redimensionada
            ImageIcon imagenajustada = new ImageIcon(imagen1);
            imagen.setIcon(imagenajustada);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
            
            txt.setText("¡Sí adiviné!");
            
        } else if (respuestaUsuario == JOptionPane.NO_OPTION) {
            aprender(nodoActual);
            fondo.setIcon(fallo);
        }
        txt.setText("¿Quieres jugar de nuevo?");
        respuestaUsuario = JOptionPane.showOptionDialog(null, "", "menu de respuestas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
          
            imagen.setIcon(null); // limpiar la imagen
            imagen.setVisible(false);
        if (respuestaUsuario != JOptionPane.YES_OPTION) {
            imagen.setIcon(null); // limpiar la imagen
            imagen.setVisible(false);
            txt.setText("");
            fondo.setIcon(inicio);
            
            break;
        }
          fondo.setIcon(normal);
    }
    btnjugar.setVisible(true);
}

    public void aprender(Nodo nodoActual) {
     txt.setText("No sé quién es el personaje !truste! ¿Quién es?");
     String nombrePersonaje = JOptionPane.showInputDialog(null, "");
     txt.setText("Escribe una pregunta que me permita diferenciar a " + nodoActual.getPersonaje().getNombre() + " de " + nombrePersonaje);
     String pregunta = JOptionPane.showInputDialog(null, "");
     txt.setText("¿Cuál es la respuesta para " + nombrePersonaje + "?");
     int respuestaUsuario = JOptionPane.showOptionDialog(null, "", "menu de respuestas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
     txt.setText("Escribe una url de la imagen del personaje " + nombrePersonaje);
     String url = JOptionPane.showInputDialog(null, "");

    personaje personajeNuevo = new personaje(nombrePersonaje, "",url);
    Nodo nodoNuevo = new Nodo(personajeNuevo);
    Nodo nodoPregunta = new Nodo(new personaje("", pregunta,""));

    if (respuestaUsuario == JOptionPane.YES_OPTION) {
        nodoPregunta.setIzquierda(nodoNuevo);
        nodoPregunta.setDerecha(nodoActual);
    } else if (respuestaUsuario == JOptionPane.NO_OPTION) {
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
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnjugar = new javax.swing.JButton();
        txt = new javax.swing.JLabel();
        imagen = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnjugar.setBackground(new java.awt.Color(0, 0, 0));
        btnjugar.setForeground(new java.awt.Color(255, 255, 255));
        btnjugar.setText("EMPEZAR");
        btnjugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjugarActionPerformed(evt);
            }
        });
        jPanel1.add(btnjugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 225, 30));

        txt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 6, 410, 220));
        jPanel1.add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 106, 350, 320));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intento10/inicio.png"))); // NOI18N
        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 730, 460));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnjugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnjugarActionPerformed
        // TODO add your handling code here:
          jugar();
         
         
    }//GEN-LAST:event_btnjugarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(akinator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(akinator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(akinator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(akinator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new akinator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnjugar;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel imagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txt;
    // End of variables declaration//GEN-END:variables


}
