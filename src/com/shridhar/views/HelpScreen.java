package com.shridhar.views;//Paquete donde se encuentra la clase

import javax.swing.border.EmptyBorder;//Importa las clases necesarias para la interfaz gráfica
import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;

public class HelpScreen extends JFrame {//Clase HelpScreen que extiende JFrame

	private JPanel contentPane;

	public HelpScreen() {//Constructor de la clase

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Define que la ventana se cierre completamente al salir
		setBounds(100, 100, 501, 447);//Define la posición y tamaño de la ventana
		contentPane = new JPanel();//Inicializa el panel principal
		contentPane.setBackground(new Color(44, 150, 57));//Establece el color de fondo del panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//Agrega un borde vacío al panel
		setContentPane(contentPane);//Establece el panel como contenido principal
		contentPane.setLayout(null);//Desactiva el poder de diseño automático
        setResizable(false); // Evita que la ventana sea redimensionable
 
		JLabel labelInstructions = new JLabel("INSTRUCCIONS");//Etiqueta para el título
		labelInstructions.setForeground(Color.black);//Color del texto negro
		labelInstructions.setFont(new Font("Tahoma", Font.BOLD, 25));//Fuente y tamaño
		labelInstructions.setBounds(157, 20, 200, 46);//Posición y tamaño de la etiqueta
		contentPane.add(labelInstructions);

		JLabel jugador1 = new JLabel("RYU JUGADOR 1");//Etiqueta para el jugador 1
		jugador1.setFont(new Font("Tahoma", Font.BOLD, 19));//Fuente y tamaño
		jugador1.setBounds(20, 75, 304, 29);//Posición y tamaño
		jugador1.setForeground(new Color(49, 47, 47));//Color del texto
		contentPane.add(jugador1);//Se añade al panel

		//Etiqueta de la tecla (W) para saltar
		JLabel labelVkupIncrease = new JLabel("Tecla (W) :  Salt");
		labelVkupIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18)); 
		labelVkupIncrease.setBounds(20, 100, 304, 29); 
		labelVkupIncrease.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkupIncrease);

		//Etiqueta de la tecla (A) para moverse a la izquierda
		JLabel labelVkrightIncrease = new JLabel("Tecla (A) :  Esquerra");
		labelVkrightIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVkrightIncrease.setBounds(20, 135, 304, 29);
		labelVkrightIncrease.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkrightIncrease);

		//Etiqueta de la tecla (D) para moverse a la derecha
		JLabel labelVkdownDecrease = new JLabel("Tecla (D) :  Dreta");
		labelVkdownDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVkdownDecrease.setBounds(20, 170, 304, 29);
		labelVkdownDecrease.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkdownDecrease);

		//Etiqueta de la tecla (G) para golpear
		JLabel labelVkleftDecrease = new JLabel("Tecla (G) :  Cop");
		labelVkleftDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVkleftDecrease.setBounds(20, 205, 304, 29);
		labelVkleftDecrease.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkleftDecrease);

		//Etiqueta de la tecla (H) para dar una patada
		JLabel labelVkrIncrease = new JLabel("Tecla (H) :  Potada");
		labelVkrIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVkrIncrease.setBounds(20, 240, 304, 29);
		labelVkrIncrease.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkrIncrease);

		//Etiqueta de la tecla (E) para defenderse
		JLabel labelVklDecrease = new JLabel("Tecla (E) :  Defensa't");
		labelVklDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVklDecrease.setBounds(20, 275, 304, 29);
		labelVklDecrease.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVklDecrease);

		//Etiqueta para el jugador 2
		JLabel jugador2 = new JLabel("KEN JUGADOR 2");
		jugador2.setFont(new Font("Tahoma", Font.BOLD, 19));
		jugador2.setBounds(280, 75, 304, 29);
		jugador2.setForeground(new Color(49, 47, 47));
		contentPane.add(jugador2);

		//Etiqueta de la tecla de flecha arriba para saltar
		JLabel labelVkupIncrease2 = new JLabel("Fletxa :  Salt");
		labelVkupIncrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVkupIncrease2.setBounds(280, 100, 304, 29);
		labelVkupIncrease2.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkupIncrease2);

		//Etiqueta de la tecla de flecha izquierda para moverse a la izquierda
		JLabel labelVkrightIncrease2 = new JLabel("Fletxa :  Esquerra");
		labelVkrightIncrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVkrightIncrease2.setBounds(280, 135, 304, 29);
		labelVkrightIncrease2.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkrightIncrease2);
		
		//Etiqueta de la tecla de flecha derecha para moverse a la derecha
		JLabel labelVkleftDecrease2 = new JLabel("Fletxa :  Dreta");
		labelVkleftDecrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVkleftDecrease2.setBounds(280, 170, 304, 29);
		labelVkleftDecrease2.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkleftDecrease2);
		
		//Etiqueta de la tecla (1) para golpear
		JLabel labelVkrIncrease2 = new JLabel("NumPad (1) :  Cop");
		labelVkrIncrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVkrIncrease2.setBounds(280, 205, 304, 29);
		labelVkrIncrease2.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVkrIncrease2);
		
		//Etiqueta de la tecla (2) para dar una patada
		JLabel labelVklDecrease2 = new JLabel("NumPad (2) :  Potada");
		labelVklDecrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVklDecrease2.setBounds(280, 240, 304, 29);
		labelVklDecrease2.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVklDecrease2);
		
		//Etiqueta de la tecla (3) para defenderse
		JLabel labelVklPoderespecial2 = new JLabel("NumPad (3) :  Defensa't");
		labelVklPoderespecial2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		labelVklPoderespecial2.setBounds(280, 275, 304, 29);
		labelVklPoderespecial2.setForeground(new Color(235, 235, 235));
		contentPane.add(labelVklPoderespecial2);
		
		//Botón para salir de la pantalla de ayuda
		JButton buttonExit = new JButton("SORTIR");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		buttonExit.setFont(new Font("Tahoma", Font.BOLD, 28));
		buttonExit.setBackground(new Color(235, 235, 235));//COLOR FONDO BOTÓN
		buttonExit.setForeground(new Color(44, 150, 57));//COLOR LETRA
		buttonExit.setBounds(177, 365, 150, 40);//UBICACIÓN DEL BOTÓN
		contentPane.add(buttonExit);
		
		try { //Fuente Cide tamaño 18
			Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/shridhar/personalGame/junegull.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			Font buttonFont = font.deriveFont(Font.ITALIC, 18f);
			
			labelVklPoderespecial2.setFont(buttonFont);
			labelVklDecrease2.setFont(buttonFont);
			labelVkrIncrease2.setFont(buttonFont);
			labelVkleftDecrease2.setFont(buttonFont);
			labelVkrightIncrease2.setFont(buttonFont);
			labelVkupIncrease2.setFont(buttonFont);
			labelVklDecrease.setFont(buttonFont);
			labelVkrIncrease.setFont(buttonFont);
			labelVkleftDecrease.setFont(buttonFont);
			labelVkdownDecrease.setFont(buttonFont);
			labelVkrightIncrease.setFont(buttonFont);
			labelVkupIncrease.setFont(buttonFont);


		} catch (Exception e) {
			e.printStackTrace();
		}

		try { //Fuente Cide tamaño 26
			Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/shridhar/personalGame/junegull.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			Font buttonFont = font.deriveFont(Font.BOLD, 26f);
			
			buttonExit.setFont(buttonFont);
			jugador2.setFont(buttonFont);
			jugador1.setFont(buttonFont);
			labelInstructions.setFont(buttonFont);
			


		} catch (Exception e) {
			e.printStackTrace(); //Muestra el error en consola si no se carga la fuente
		}
	}
	//Método para salir de la pantalla de ayuda
	private void exit() {
		this.setVisible(false);
		this.dispose();
		GameScreen1 frame = new GameScreen1();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
