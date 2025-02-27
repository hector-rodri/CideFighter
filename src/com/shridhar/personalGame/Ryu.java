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
	
	Ryu(int x, int y){
		loadImage();
		loadWalk();
		loadPunch();
		loadFall();
		loadKick();
		loadPower();
		mode = WALK;
		this.x = x;
		this.w = this.h = 150;
		this.y = FLOOR-this.h;
		
	}
	
	BufferedImage walkImages [] = new BufferedImage[5];
	public void loadWalk() {
		walkImages[0] = img.getSubimage(8, 1150, 30, 73);
		walkImages[1]= img.getSubimage(72, 1145, 37, 78);
		walkImages[2]= img.getSubimage(136, 1144, 37, 79);
		walkImages[3]= img.getSubimage(204, 1145, 32, 78);
		walkImages[4]= img.getSubimage(268, 1144, 31, 79);
	}
	
	BufferedImage punchImages[] = new BufferedImage[3];
	public void loadPunch() {
		punchImages[0]= img.getSubimage(4, 1241, 37, 79);
		punchImages[1]= img.getSubimage(63, 1241, 53, 79);
		punchImages[2]= img.getSubimage(141, 1241, 37, 79);
	}
	
	BufferedImage fallImages[] = new BufferedImage[8];
	public void loadFall() {
		fallImages[0]= img.getSubimage(951, 1127, 43, 74);
		fallImages[1]= img.getSubimage(372, 1825, 45, 59);
		fallImages[2]= img.getSubimage(861, 1151, 72, 39);
		fallImages[3]= img.getSubimage(442, 1830, 72, 42);
		fallImages[4]= img.getSubimage(539, 1849, 74, 27);
		fallImages[5]= img.getSubimage(539, 1849, 74, 27);
		fallImages[6]= img.getSubimage(539, 1849, 74, 27);
		fallImages[7]= img.getSubimage(539, 1849, 74, 27);
	}

	BufferedImage jumpImages[] = new BufferedImage[5];
	public void loadJump() {
		jumpImages[0]= img.getSubimage(618, 1051, 30, 73);
		jumpImages[1]= img.getSubimage(682, 1034, 33, 90);
		jumpImages[2]= img.getSubimage(744, 1046, 29, 81);
		jumpImages[3]= img.getSubimage(796, 1057, 31, 67);
		jumpImages[4]= img.getSubimage(618, 1051, 30, 73);	
	}
	
	BufferedImage kickImages[] = new BufferedImage[5];
	public void loadKick() {
		kickImages[0]= img.getSubimage(5, 1347, 37, 79);
		kickImages[1]= img.getSubimage(1019, 1112, 33, 90);
		kickImages[2]= img.getSubimage(68, 1345, 39, 81);
		kickImages[3]= img.getSubimage(133, 1343, 67, 83);
		kickImages[4]= img.getSubimage(5, 1347, 37, 79);
		
	}
	
	BufferedImage powerImages[] = new BufferedImage[9];
	public void loadPower() {
		powerImages[0]= img.getSubimage(13, 1696, 50, 73);
		powerImages[1]= img.getSubimage(92, 1697, 51, 72);
		powerImages[2]= img.getSubimage(164, 1702, 62, 67);
		powerImages[3]= img.getSubimage(241, 1699, 34, 48);
		powerImages[4]= img.getSubimage(290, 1710, 37, 28);
		powerImages[5]= img.getSubimage(340, 1712, 33, 24);
		powerImages[6]= img.getSubimage(447, 1711, 33, 24);
		powerImages[7]= img.getSubimage(525, 1710, 18, 24);
		powerImages[8]= img.getSubimage(555, 1710, 33, 24);
		
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
	int mydelay1 = 1;
	private void drawFall(Graphics g) {
		if(mydelay1==2) {
		g.drawImage(fallImages[fallIndex], x, y, w, h, null);
		fallIndex++;
		if(fallIndex>7) {
			fallIndex = 0;
			mode = WALK;
		}
		mydelay1 = 1;
		}
		mydelay1++;
	}
	
	private int kickIndex = 0;
	private void drawKick(Graphics g) {
		g.drawImage(kickImages[kickIndex], x, y, w, h, null);
		kickIndex++;
		if(kickIndex>3) {
			kickIndex = 0;
			mode = WALK;
		}
	}
	
	private int powerIndex = 0;
	private void drawPower(Graphics g) {
		g.drawImage(powerImages[powerIndex], x, y, w, h, null);
		powerIndex++;
		if(powerIndex>8) {
			powerIndex = 0;
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
					if(mode==KICK) {
						drawKick(g);
					}
					else 
						if(mode==POWER) {
							drawPower(g);
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
