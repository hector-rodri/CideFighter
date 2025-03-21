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

		JLabel lblInstructions = new JLabel("INSTRUCCIONS");//Etiqueta para el título
		lblInstructions.setForeground(Color.black);//Color del texto negro
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 25));//Fuente y tamaño
		lblInstructions.setBounds(157, 20, 200, 46);//Posición y tamaño de la etiqueta
		contentPane.add(lblInstructions);

		JLabel jugador1 = new JLabel("RYU JUGADOR 1");//Etiqueta para el jugador 1
		jugador1.setFont(new Font("Tahoma", Font.BOLD, 19));//Fuente y tamaño
		jugador1.setBounds(20, 75, 304, 29);//Posición y tamaño
		jugador1.setForeground(new Color(49, 47, 47));//Color del texto
		contentPane.add(jugador1);//Se añade al panel

		//Etiqueta de la tecla (W) para saltar
		JLabel lblVkupIncrease = new JLabel("Tecla (W) :  Salt");
		lblVkupIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18)); 
		lblVkupIncrease.setBounds(20, 100, 304, 29); 
		lblVkupIncrease.setForeground(new Color(235, 235, 235));
		contentPane.add(lblVkupIncrease);

		//Etiqueta de la tecla (A) para moverse a la izquierda
		JLabel lblVkrightIncrease = new JLabel("Tecla (A) :  Esquerra");
		lblVkrightIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrightIncrease.setBounds(20, 135, 304, 29);
		lblVkrightIncrease.setForeground(new Color(235, 235, 235));
		contentPane.add(lblVkrightIncrease);

		//Etiqueta de la tecla (S) para agacharse
		JLabel lblVkdownDecrease = new JLabel("Tecla (S) :  Acotar-se");
		lblVkdownDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkdownDecrease.setBounds(20, 170, 304, 29);
		lblVkdownDecrease.setForeground(new Color(235, 235, 235));
		contentPane.add(lblVkdownDecrease);

		//Etiqueta de la tecla (D) para moverse a la derecha
		JLabel lblVkleftDecrease = new JLabel("Tecla (D) :  Dreta");
		lblVkleftDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkleftDecrease.setBounds(20, 205, 304, 29);
		lblVkleftDecrease.setForeground(new Color(235, 235, 235));
		contentPane.add(lblVkleftDecrease);

		//Etiqueta de la tecla (G) para golpear
		JLabel lblVkrIncrease = new JLabel("Tecla (G) :  Cop");
		lblVkrIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrIncrease.setBounds(20, 240, 304, 29);
		lblVkrIncrease.setForeground(new Color(235, 235, 235));
		contentPane.add(lblVkrIncrease);

		//Etiqueta de la tecla (H) para dar una patada
		JLabel lblVklDecrease = new JLabel("Tecla (H) :  Potada");
		lblVklDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVklDecrease.setBounds(20, 275, 304, 29);
		lblVklDecrease.setForeground(new Color(235, 235, 235));
		contentPane.add(lblVklDecrease);

		//Etiqueta de la tecla (J) para usar poder especial
		JLabel ldlVklPoderespecial = new JLabel("Tecla (J) :  Poder");
		ldlVklPoderespecial.setFont(new Font("Tahoma", Font.ITALIC, 18));
		ldlVklPoderespecial.setBounds(20, 310, 304, 29);
		ldlVklPoderespecial.setForeground(new Color(235, 235, 235));
		contentPane.add(ldlVklPoderespecial);

		//Crea un botón para salir
		JButton btnExit = new JButton("SORTIR");
		btnExit.addActionListener(new ActionListener() {//Agrega el evento para salir al hacer clic en el botón
			@Override
			public void actionPerformed(ActionEvent e) {
				exit(); //Llama al método exit()
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 28)); //Establece la fuente del botón
		btnExit.setBackground(new Color(235, 235, 235)); //Color de fondo del botón
		btnExit.setForeground(new Color(44, 150, 57)); //Color del texto del botón
		btnExit.setBounds(177, 365, 150, 40); //Ubicación y tamaño del botón
		contentPane.add(btnExit); //Añade el botón al panel

		try { //Carga y aplica una fuente personalizada para ciertos elementos
			Font font = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/com/shridhar/personalGame/junegull.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			Font buttonFont = font.deriveFont(Font.ITALIC, 22f);

			//Aplica la fuente a todas las etiquetas de movimientos
			ldlVklPoderespecial.setFont(buttonFont);
			lblVklDecrease.setFont(buttonFont);
			lblVkrIncrease.setFont(buttonFont);
			lblVkleftDecrease.setFont(buttonFont);
			lblVkdownDecrease.setFont(buttonFont);
			lblVkrightIncrease.setFont(buttonFont);
			lblVkupIncrease.setFont(buttonFont);

		} catch (Exception e) {
			e.printStackTrace(); //Muestra error en caso de fallo al cargar la fuente
		}
	}

	private void exit() { //Método para salir de la pantalla de ayuda
		this.setVisible(false); //Oculta la ventana actual
		this.dispose(); //Libera los recursos de la ventana
		GameScreen1 frame = new GameScreen1(); //Crea una nueva ventana de juego
		frame.setVisible(true); //Muestra la ventana de juego
		frame.setLocationRelativeTo(null); //Centrar la ventana en la pantalla
	}
}
