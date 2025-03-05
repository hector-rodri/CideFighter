package com.shridhar.personalGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ken extends Sprite1 implements IPlayer1 {
	private int mode;
	public boolean kendamage;
	public static int kencounter = 300;

	public boolean isKendamage() {
		return kendamage;
	}

	public void setKendamage(boolean kendamage) {
		this.kendamage = kendamage;
	}

	public static int getKencounter() {
		return kencounter;
	}

	public static void setKencounter(int kencounter) {
		Ken.kencounter = kencounter;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public void move() {
		int newX = getX() + speed; // Calcula la nueva posición en X
		int screenWidth = 800;  // Ancho de la pantalla
		int characterWidth = getW(); // Ancho del personaje
	
		// Limitar el movimiento dentro de la pantalla
		if (newX < 0) {
			newX = 0; // Evita que salga por la izquierda
		} else if (newX + characterWidth > screenWidth) {
			newX = screenWidth - characterWidth; // Evita que salga por la derecha
		}
	
		setX(newX); // Aplicar nueva posición
	
		// Si agregas movimiento en Y, haz lo mismo con los límites verticales
	}
	
	public void jump(){ // Método para hacer saltar al personaje
		if (y == FLOOR-h) {
			mode = JUMP;// Iniciar el salto
				new Thread(() -> {
					int jumpHeight = 125; // Altura del salto
					int jumpSpeed = 5; // Velocidad del salto

					// Subir
					for (int i = 0; i < jumpHeight; i += jumpSpeed) {
						y -= jumpSpeed;
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					// Bajar
					for (int i = 0; i < jumpHeight; i += jumpSpeed) {
						y += jumpSpeed;
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					// Volver al modo caminar
					mode = WALK;
				}).start();
			}
		}

	Ken(int x, int y){
		loadImage();
		loadWalk();
		loadPunch();
		
		loadFall();
		loadDefence();
		mode = WALK;
		this.x = x;
		this.w = this.h = 150;
		this.y = FLOOR-this.h;
		
	}
	
	BufferedImage walkImages [] = new BufferedImage[6];
	public void loadWalk() {
		walkImages[0] = img.getSubimage(6, 20, 56, 93);
		walkImages[1]= img.getSubimage(70, 19, 54, 94);
		walkImages[2]= img.getSubimage(135, 18, 59, 95);
		walkImages[3]= img.getSubimage(199, 19, 68, 94);
		walkImages[4]= img.getSubimage(275, 20, 70, 93);
		walkImages[5]= img.getSubimage(353, 25, 63, 88);
	}
	
	BufferedImage punchImages[] = new BufferedImage[4];
	public void loadPunch() {
		punchImages[0]= img.getSubimage(697, 19, 77, 94);
		punchImages[1]= img.getSubimage(582, 18, 107, 95);
		punchImages[2]= img.getSubimage(497, 19, 77, 94);
		punchImages[3]= img.getSubimage(423, 19, 66, 94);
		
	}
	
	BufferedImage fallImages[] = new BufferedImage[8];
	public void loadFall() {
		fallImages[0]= img.getSubimage(60, 2223, 33, 57);
		fallImages[1]= img.getSubimage(107, 2242, 55, 30);
		fallImages[2]= img.getSubimage(206, 2332, 55, 29);
		fallImages[3]= img.getSubimage(352, 2777, 57, 21);
		fallImages[4]= img.getSubimage(352, 2777, 57, 21);
		fallImages[5]= img.getSubimage(352, 2777, 57, 21);
		fallImages[6]= img.getSubimage(352, 2777, 57, 21);
		fallImages[7]= img.getSubimage(352, 2777, 57, 21);
//		fallImages[5]= img.getSubimage(791, 2865, 26, 48);
//		fallImages[6]= img.getSubimage(206, 2332, 55, 29);
//		fallImages[7]= img.getSubimage(59, 2301, 25, 69);
	}

	BufferedImage jumpImages[] = new BufferedImage[5];
	public void loadJump(){

	}
	
	BufferedImage defenceImages[] = new BufferedImage[3];
	public void loadDefence() {
		defenceImages[0]= img.getSubimage(274, 2152, 25, 69);
		defenceImages[1]= img.getSubimage(104, 2152, 26, 69);
		
	}
	
	
	
	
	private int punchIndex = 0;
	private void drawPunch(Graphics g) {
		g.drawImage(punchImages[punchIndex], x, y, w, h, null);
		punchIndex++;
		if(punchIndex>2) {
			punchIndex = 0;
			mode = WALK;
		}
	}
	
	private int walkIndex = 0;
	private void drawWalk(Graphics g) {
		g.drawImage(walkImages[walkIndex], x, y, w, h, null);
		walkIndex++;
		if(walkIndex>4) {
			walkIndex = 0;
		}
	}
	
	private int fallIndex = 0;
	int mydelay = 1;
	private void drawFall(Graphics g) {
		if(mydelay==2) {
		g.drawImage(fallImages[fallIndex], x, y, w, h, null);
		fallIndex++;
		if(fallIndex>7) {
			fallIndex = 0;
			mode = WALK;
		}
		mydelay = 1;
		}
		mydelay++;
	}
	
	private int defenceIndex = 0;
	private void drawDefence(Graphics g) {
		g.drawImage(defenceImages[defenceIndex], x, y, w, h, null);
		defenceIndex++;
		if(defenceIndex>1) {
			defenceIndex = 0;
			mode = WALK;
		}
	}
	
	
	
	@Override
	public void draw(Graphics g) {
		if(mode==WALK) {
			drawWalk(g);
		}
		else 
			if(mode==PUNCH) {
				drawPunch(g);
			}
			else 
				if(mode==FALL) {
					drawFall(g);
				}
				else 
					if(mode==DEFENCE) {
						drawDefence(g);
					}
				
	}
	
	int barwidth = 300;
	public int drawProgress(Graphics g) {
		barwidth = barwidth-20;
		g.setColor(Color.RED);
		g.fillRect(400, 40, barwidth, 40);
		return barwidth;
	}
	

}
