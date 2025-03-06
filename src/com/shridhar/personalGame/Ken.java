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
		loadJump();
		loadKick();
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
	
	BufferedImage fallImages[] = new BufferedImage[4];
	public void loadFall() {
		fallImages[0]= img.getSubimage(217, 128, 64, 88);
		fallImages[1]= img.getSubimage(148, 146, 61, 70);
		fallImages[2]= img.getSubimage(78, 151, 62, 65);
		fallImages[3]= img.getSubimage(7, 130, 64, 86);
	}

	BufferedImage jumpImages[] = new BufferedImage[4];
	public void loadJump(){
		jumpImages[0]= img.getSubimage(7, 130, 64, 86);
		jumpImages[1]= img.getSubimage(78, 151, 62, 65);
		jumpImages[2]= img.getSubimage(148, 146, 61, 70);
		jumpImages[3]= img.getSubimage(217, 128, 64, 88);
	}
	
	BufferedImage defenceImages[] = new BufferedImage[3];
	public void loadDefence() {
		defenceImages[0]= img.getSubimage(274, 2152, 25, 69);
		defenceImages[1]= img.getSubimage(104, 2152, 26, 69);
		
	}

	BufferedImage kickImages[] = new BufferedImage[7];
	public void loadKick(){
		kickImages[0]= img.getSubimage(6, 264, 66, 94);
		kickImages[1]= img.getSubimage(80, 257, 83, 101);
		kickImages[2]= img.getSubimage(410, 257, 83, 99);
		kickImages[3]= img.getSubimage(343, 265, 59, 91);
		kickImages[4]= img.getSubimage(243, 270, 101, 86);
		kickImages[5]= img.getSubimage(171, 269, 55, 88);
		kickImages[6]= img.getSubimage(80, 257, 83, 101);
	}
	
	
	
	
	private int punchIndex = 0;
	private void drawPunch(Graphics g) {
		g.drawImage(punchImages[punchIndex], x, y, w, h, null);
		punchIndex++;
		if(punchIndex==punchImages.length) {
			punchIndex = 0;
			mode = WALK;
		}
	}
	
	private int jumpIndex = 0;
	private void drawJump(Graphics g) {
		g.drawImage(jumpImages[jumpIndex], x, y, w, h, null);
		jumpIndex++;
		if(jumpIndex==jumpImages.length) {
			jumpIndex = 0;
			mode = WALK;
		}
	}

	private int walkIndex = 0;
	private void drawWalk(Graphics g) {
		g.drawImage(walkImages[walkIndex], x, y, w, h, null);
		walkIndex++;
		if(walkIndex==walkImages.length) {
			walkIndex = 0;
		}
	}

	private int kickIndex = 0;
	private void drawKick(Graphics g){
		g.drawImage(kickImages[kickIndex], x, y, w, h, null);
		kickIndex++;
		if(kickIndex==kickImages.length) {
			kickIndex = 0;
			mode = WALK;
		}
	}
	
	private int fallIndex = 0;
	int mydelay = 1;
	private void drawFall(Graphics g) {
		if(mydelay==2) {
		g.drawImage(fallImages[fallIndex], x, y, w, h, null);
		fallIndex++;
		if(fallIndex==fallImages.length) {
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
		if(defenceIndex==defenceImages.length) {
			defenceIndex = 0;
			mode = WALK;
		}
	}
	
	
	
	@Override
	public void draw(Graphics g) {
		switch(mode){
		case WALK:
			drawWalk(g);
			break;
		case PUNCH:
			drawPunch(g);
			break;
		case JUMP:
			drawJump(g);
			break;
		case FALL:
			drawFall(g);
			break;
		case KICK:
			drawKick(g);
			break;
		case DEFENCE:
			drawDefence(g);
			break;
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
