package com.shridhar.personalGame;

import javax.swing.JFrame;

import com.shridhar.personalGame.utils.GameUtils1;

public class GameFrame1 extends JFrame implements GameUtils1 { // Define la clase GameFrame1 que extiende JFrame 
																//e implementa GameUtils1, se utiliza para construir el frame del juego
	public GameFrame1() { // Constructor de la clase
		setSize(FRAME_WIDTH, FRAME_HEIGHT); // Establece el tamaño del frame
		setTitle(TITLE); // Establece el título del frame
		setLocationRelativeTo(null); // Establece la posición del frame en el centro
		setResizable(false); // Evita que el frame sea redimensionable
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece la operación por defecto al cerrar el frame
		Board1 board1 = new Board1(); // Crea un nuevo objeto de la clase Board1
		this.add(board1); // Añadimos el objeto al frame
		setVisible(true); // Hacemos visible el frame

	}
	

}
