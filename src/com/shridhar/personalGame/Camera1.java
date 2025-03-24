package com.shridhar.personalGame;

import java.awt.Graphics;

public class Camera1 extends Sprite1 { // Define la clase Camera1 que extiende Sprite1, es utilizada para manejar la cámara del juego
	// Constructor de la clase
	public Camera1() { 
		loadImage(this); // Carga la imagen del fondo
		this.x = 0; // Posición en x
		this.y = 0; // Posición en y
		this.h = FRAME_HEIGHT; // Alto
		this.w = FRAME_WIDTH; // Ancho
	}
	
	public void drawBG(Graphics g) { // Método para dibujar el fondo
		g.drawImage(img, 0, 0, w, h, null);
	}

}
