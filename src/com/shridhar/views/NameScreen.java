package com.shridhar.views;//Paquete donde se encuentra la clase

import javax.swing.*;//Imports necesarios para la interfaz gráfica y el diseño
import com.shridhar.personalGame.Board1; 
import java.awt.*; 
import java.awt.event.*;

public class NameScreen extends JFrame {//Define la clase que hereda de JFrame
    public JTextField nombreJugador1;//Campos de texto donde los jugadores escribirán sus nombres
    public JTextField nombreJugador2;

    public NameScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Cierra la aplicación al cerrar la ventana
        setBounds(100, 100, 501, 447);//Establece el tamaño y posición inicial de la ventana
        setLocationRelativeTo(null);//Centra la ventana en la pantalla
        setResizable(false);//Impide cambiar el tamaño de la ventana

        //PANEL PRINCIPAL
        JPanel panelPrincipal = new JPanel();//Crea el panel principal de la ventana
        panelPrincipal.setBackground(new Color(44, 150, 57));//Establece el color Cide
        setContentPane(panelPrincipal);//El panel es el contenido principal de la ventana
        panelPrincipal.setLayout(null);//Usa un diseño absoluto para posicionar los componentes manualmente

        //TITULO
        JLabel titulo = new JLabel("INTRODUEIX EL TEU NOM:");//Crea un título
        titulo.setForeground(Color.black);//Color del texto 
        titulo.setFont(new Font("Tahoma", Font.BOLD, 20));//Fuente y tamaño
        titulo.setBounds(120, 20, 400, 46);//Posición y tamaño
        panelPrincipal.add(titulo);

        //JUGADOR 1
        JLabel jugador1 = new JLabel("JUGADOR 1");//Etiqueta para el primer jugador
        jugador1.setFont(new Font("Tahoma", Font.BOLD, 19));//Fuente y tamaño
        jugador1.setBounds(20, 75, 200, 29);//Posición y tamaño
        panelPrincipal.add(jugador1);

        nombreJugador1 = new JTextField();//Campo de texto para el nombre del jugador
        nombreJugador1.setFont(new Font("Tahoma", Font.PLAIN, 16));//Fuente y tamaño
        nombreJugador1.setBounds(20, 115, 200, 30);//Posición y tamaño
        panelPrincipal.add(nombreJugador1);

        //JUGADOR 2
        JLabel jugador2 = new JLabel("JUGADOR 2");//Etiqueta para el segundo jugador
        jugador2.setFont(new Font("Tahoma", Font.BOLD, 19));//Fuente y tamaño
        jugador2.setBounds(280, 75, 200, 29);//Posición y tamaño
        panelPrincipal.add(jugador2);

        nombreJugador2 = new JTextField();//Campo de texto para el nombre del jugador
        nombreJugador2.setFont(new Font("Tahoma", Font.PLAIN, 16));//Fuente y tamaño
        nombreJugador2.setBounds(280, 115, 200, 30);//Posición y tamaño
        panelPrincipal.add(nombreJugador2);

        //BOTÓN GUARDAR
        JButton botonGuardar = new JButton("GUARDAR");//Botón para botonGuardar los nombres
        botonGuardar.setFont(new Font("Tahoma", Font.BOLD, 18));//Fuente y tamaño
        botonGuardar.setForeground(new Color(44, 150, 57));//Color del texto
        botonGuardar.setBackground(new Color(190, 190, 190));//Color de fondo
        botonGuardar.setFocusPainted(false);//Quita el contorno al hacer clic
        botonGuardar.setBorderPainted(false);//Quita el borde del botón
        botonGuardar.setBounds(175, 200, 150, 40);//Posición y tamaño
        botonGuardar.addActionListener(new ActionListener() {//Cuando se hace clic en el botón, se ejecuta el método guardar
            @Override
            public void actionPerformed(ActionEvent event) {
                guardar();
            }
        });
        panelPrincipal.add(botonGuardar);

        //BOTÓN SALIR
        JButton botonSalir = new JButton("SORTIR");//Botón para botonSalir
        botonSalir.setFont(new Font("Tahoma", Font.BOLD, 18));//Fuente y tamaño
        botonSalir.setBackground(new Color(190, 190, 190));// Color de fondo
        botonSalir.setForeground(new Color(44, 150, 57));//Color del texto
        botonSalir.setBounds(175, 260, 150, 40);//Posición y tamaño
        botonSalir.addActionListener(new ActionListener() {//Cuando se hace clic en el botón, se ejecuta el método salir
            @Override
            public void actionPerformed(ActionEvent event) {
                salir();
            }
        });
        panelPrincipal.add(botonSalir);
    }

    public void guardar() {//Método para guardar los nombres de los jugadores.
        String jugadorNombre1 = nombreJugador1.getText().trim();//Obtiene el nombre de los jugadores
        String jugadorNombre2 = nombreJugador2.getText().trim(); 

        if (jugadorNombre1.isEmpty() || jugadorNombre2.isEmpty()) {//Comprueba si ningún campo de texto está vacío
            JOptionPane.showMessageDialog(this, "Si us plau, introdueix els noms de tots els jugadors!", "Error", JOptionPane.ERROR_MESSAGE);
            return;//Muestra un mensaje de error
        }
        

        Board1 board1 = new Board1();//Crea una ventana de juego
        board1.setPlayerNames(jugadorNombre1.toUpperCase(), jugadorNombre2.toUpperCase());//Establece los nombres introducidos
        JFrame game = new JFrame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        game.setSize(800, 600); 
        game.setLocationRelativeTo(null); 
        game.add(board1);
        game.setVisible(true);
        
        //Asegurarse de que se cierre la ventana actual
        this.setVisible(false);//Oculta la ventana actual
        this.dispose();//Libera los recursos de la ventana actual

    }

    public void salir() {//Método para salir de la pantalla
        this.setVisible(false);//Oculta la ventana actual
        this.dispose();
        GameScreen1 frame = new GameScreen1();//Crea una nueva ventana del menu inicial
        frame.setVisible(true);//Muestra la nueva pantalla.
        frame.setLocationRelativeTo(null);
    }

}
