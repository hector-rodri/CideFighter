package com.shridhar.personalGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.shridhar.personalGame.utils.GameUtils1;


public class Sprite1 implements GameUtils1 { // Define la clase Sprite1 que implementa GameUtils1, es utilizada para manejar los sprites de los personajes y el fondo
	protected int x; // Posición en x
	protected int y; // Posición en y
	protected int w; // Ancho
	protected int h; // Alto
	protected int speed; // Velocidad
	protected Image image; // Imagen
	protected BufferedImage img; // Imagen en buffer
	
	public void draw(Graphics g) { // Método para dibujar el sprite
		g.drawImage(image, x, y, w, h, null);
	}
	
	public void loadImage(Sprite1 personaje) { // Método para cargar la imagen del sprite
		if(personaje instanceof Ken){ // Si el personaje es Ken
			try {
				img = ImageIO.read(Sprite1.class.getResource(KEN_IMAGE));
				System.out.println("Image is loaded....");
			}
			catch(IOException e) {
				System.out.println("Image is not Available");
			}
		}
		else if(personaje instanceof Ryu){ // Si el personaje es Ryu
			try {
				img = ImageIO.read(Sprite1.class.getResource(RYU_IMAGE));
				System.out.println("Image is loaded....");
			}
			catch(IOException e) {
				System.out.println("Image is not Available");
			}
		}
		else if(personaje instanceof Camera1){ //Si el objeto es la cámara, para poder visualizar el juego
			try {
				img = ImageIO.read(Sprite1.class.getResource(BG_IMAGE));
				System.out.println("Image is loaded....");
			}
			catch(IOException e) {
				System.out.println("Image is not Available");
			}
		}
	}
	
	public BufferedImage getImg() { // getter de la imagen en buffer
		return img;
	}

	public void setImg(BufferedImage img) { // setter de la imagen en buffer
		this.img = img;
	}

	public Sprite1() { //Constructor vacío de la clase
		
	}

	public int getX() { // getter de la posición en x
		return x;
	}

	public void setX(int x) { // setter de la posición en x
		this.x = x;
	}

	public int getY() { // getter de la posición en y
		return y;
	}

	public void setY(int y) { // setter de la posición en y
		this.y = y;
	}

	public int getW() { // getter del ancho
		return w;
	}

	public void setW(int w) { // setter del ancho
		this.w = w;
	}

	public int getH() { // getter del alto
		return h;
	}

	public void setH(int h) { // setter del alto
		this.h = h;
	}

	public int getSpeed() { // getter de la velocidad
		return speed;
	}

	public void setSpeed(int speed) { // setter de la velocidad
		this.speed = speed;
	}

	public Image getImage() { // getter de la imagen
		return image;
	}

	public void setImage(Image image) { // setter de la imagen
		this.image = image;
	}
	

}
