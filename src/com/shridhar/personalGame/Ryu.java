package com.shridhar.personalGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ryu extends Sprite1 implements IPlayer1 {
	private int mode;
	public boolean ryudamage;
	public static int ryucounter = 300;
	Ken ken;

	public boolean isRyudamage() {
		return ryudamage;
	}

	public void setRyudamage(boolean ryudamage) {
		this.ryudamage = ryudamage;
	}

	public static int getRyucounter() {
		return ryucounter;
	}

	public static void setRyucounter(int ryucounter) {
		Ryu.ryucounter = ryucounter;
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
	
	Ryu(int x, int y){
		loadImage(Ryu.this);
		loadWalk();
		loadPunch();
		loadJump();
		loadFall();
		loadKick();
		loadPower();
		mode = WALK;
		this.x = x;
		this.w = this.h = 150;
		this.y = FLOOR-this.h;
		
	}
	
	BufferedImage walkImages [] = new BufferedImage[6];
	public void loadWalk() {
		walkImages[0] = img.getSubimage(5, 641, 63, 89);
		walkImages[1]= img.getSubimage(70, 636, 69, 93);
		walkImages[2]= img.getSubimage(144, 635, 67, 95);
		walkImages[3]= img.getSubimage(221, 634, 60, 95);
		walkImages[4]= img.getSubimage(296, 635, 54, 94);
		walkImages[5]= img.getSubimage(364, 636, 56, 93);

	}
	
	BufferedImage punchImages[] = new BufferedImage[5];
	public void loadPunch() {
		punchImages[0]= img.getSubimage(273, 1421, 65, 94);
		punchImages[1]= img.getSubimage(345, 1421, 71, 94);
		punchImages[2]= img.getSubimage(421, 1422, 109, 93);
		punchImages[3]= img.getSubimage(345, 1421, 71, 94);
		punchImages[4]= img.getSubimage(273, 1421, 65, 94);
	}
	
	BufferedImage fallImages[] = new BufferedImage[8];
	public void loadFall() {
		// fallImages[0]= img.getSubimage(951, 1127, 43, 74);
		// fallImages[1]= img.getSubimage(372, 1825, 45, 59);
		// fallImages[2]= img.getSubimage(861, 1151, 72, 39);
		// fallImages[3]= img.getSubimage(442, 1830, 72, 42);
		// fallImages[4]= img.getSubimage(539, 1849, 74, 27);
		// fallImages[5]= img.getSubimage(539, 1849, 74, 27);
		// fallImages[6]= img.getSubimage(539, 1849, 74, 27);
		// fallImages[7]= img.getSubimage(539, 1849, 74, 27);
	}

	BufferedImage jumpImages[] = new BufferedImage[7];
	public void loadJump() {
		jumpImages[0]= img.getSubimage(721, 818, 63, 108);
		jumpImages[1]= img.getSubimage(791, 775, 64, 88);
		jumpImages[2]= img.getSubimage(861, 748, 61, 70);
		jumpImages[3]= img.getSubimage(925, 739, 62, 65);
		jumpImages[4]= img.getSubimage(1000, 750, 64, 86);
		jumpImages[5]= img.getSubimage(1070, 765, 63, 116);
		jumpImages[6]= img.getSubimage(721, 818, 63, 108);
	}
	
	BufferedImage kickImages[] = new BufferedImage[7];
	public void loadKick() {
		kickImages[0]= img.getSubimage(430, 2559, 65, 94);
		kickImages[1]= img.getSubimage(497, 2559, 67, 94);
		kickImages[2]= img.getSubimage(566, 2559, 65, 94);
		kickImages[3]= img.getSubimage(656, 2563, 118, 90);
		kickImages[4]= img.getSubimage(656, 2563, 118, 90);
		kickImages[5]= img.getSubimage(566, 2559, 65, 94);
		kickImages[6]= img.getSubimage(497, 2559, 67, 94);
		
		
	}
	
	BufferedImage powerImages[] = new BufferedImage[1];
	public void loadPower() {
		powerImages[0]= img.getSubimage(925, 5105, 133, 129);

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
	
	private int walkIndex = 0;
	private void drawWalk(Graphics g) {
		g.drawImage(walkImages[walkIndex], x, y, w, h, null);
		walkIndex++;
		if(walkIndex==walkImages.length) {
			walkIndex = 0;
		}
	}
	
	private int fallIndex = 0;
	int mydelay1 = 1;
	private void drawFall(Graphics g) {
		if(mydelay1==2) {
		g.drawImage(fallImages[fallIndex], x, y, w, h, null);
		fallIndex++;
		if(fallIndex==fallImages.length) {
			fallIndex = 0;
			mode = WALK;
		}
		mydelay1 = 1;
		}
		mydelay1++;
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


	private int kickIndex = 0;
	private void drawKick(Graphics g) {
		g.drawImage(kickImages[kickIndex], x, y, w, h, null);
		kickIndex++;
		if(kickIndex==kickImages.length) {
			kickIndex = 0;
			mode = WALK;
		}
	}
	
	private int powerIndex = 0;
	private void drawPower(Graphics g) {
		g.drawImage(powerImages[powerIndex], x, y, w, h, null);
		powerIndex++;
		if(powerIndex==powerImages.length) {
			powerIndex = 0;
			mode = WALK;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		switch (getMode()) { //Switch case para cambiar el sprite de Ryu segun su modo
			case (WALK):
				drawWalk(g);
				break;
			case (PUNCH):
				drawPunch(g);
				break;
			case (FALL):
				drawFall(g);
				break;
			case (KICK):
				drawKick(g);
				break;
			case (POWER):
				drawPower(g);
				break;
			case (JUMP):
				drawJump(g);
				break;
		}
}
	
	int barwidth = 300;
	public int drawProgress(Graphics g) {
		barwidth = barwidth-20;
		g.setColor(Color.RED);
		g.fillRect(40, 40, barwidth, 40);
		return barwidth;
	}

}
