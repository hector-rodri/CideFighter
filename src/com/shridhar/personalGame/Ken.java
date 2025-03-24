package com.shridhar.personalGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Ken extends Sprite1 implements IPlayer1 { //Clase ken que extiende de Sprite1 e implementa IPlayer1
	private int mode;// Estado del personaje
	public boolean kendamage; //Booleano que indica si el personaje ha sido dañado, en este caso no es utilizado
	public static int kencounter = 300; //Contador de vida
	private boolean canAttack = true; // Booleano que decide si puede realizar la animación atacar o no
	private boolean canDealDamage = true;  // Booleano que decide si puede realizar daño o no

	
	public boolean isKendamage() { //Getter para kendamage
		return kendamage;
	}

	public void setKendamage(boolean kendamage) { //Setter para kendamage
		this.kendamage = kendamage;
	}

	public static int getKencounter() { //Getter para kencounter
		return kencounter;
	}

	public static void setKencounter(int kencounter) { //Setter para kencounter
		Ken.kencounter = kencounter;
	}

	public int getMode() { //Getter para mode
		return mode;
	}

	public void setMode(int mode) { //Setter para mode
		this.mode = mode;
	}
	 
    public void attackWithDelay(Runnable attackAction, int delayMs) {// Método para atacar con delay
    if (canAttack) {
        attackAction.run(); // Ejecuta el ataque
        canAttack = false;  // Bloquea nuevos ataques
        Timer timer = new Timer(delayMs, (e) -> canAttack = true); // Permite el siguiente ataque después del retraso
        timer.setRepeats(false); // No queremos repeticiones continuas
        timer.start(); // Inicia el Timer
    }
}

    // Enfriamiento para el daño
    public void resetDamageCooldown(int cooldownMs) { 
        canDealDamage = false;
        Timer timer = new Timer(cooldownMs, (e) -> canDealDamage = true);
		timer.setRepeats(false);
		timer.start();
    }
	
	
	public void move() {
		int newX = getX() + speed; // Calcula la nueva posición en X
		int screenWidth = 800;  // Ancho de la pantalla
		int characterWidth = getW(); // Ancho del personaje
		// Limita el movimiento dentro de la pantalla
		if (newX < 0) {
			newX = 0; // Evita que salga por la izquierda
		} else if (newX + characterWidth > screenWidth) {
			newX = screenWidth - characterWidth; // Evita que salga por la derecha
		}
		setX(newX); // Aplicar nueva posición
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

	Ken(int x, int y){ //Constructor de la clase Ken
		loadImage(Ken.this); //Carga la imagen del personaje
		loadWalk(); //Carga las imágenes de caminar
		loadPunch(); //Carga las imágenes de atacar
		loadJump(); //Carga las imágenes de saltar
		loadKick(); //Carga las imágenes de patear
		loadFall(); //Carga las imágenes de caer
		loadDefence(); //Carga las imágenes de defender
		mode = WALK; //Inicializa el modo en caminar
		this.x = x; //Posición en X
		this.w = this.h = 150; //Ancho y alto del personaje
		this.y = FLOOR-this.h; //Posición en Y
		
	}
	
    // Getter para canDealDamage
    public boolean isCanDealDamage() {
        return canDealDamage;
    }
	
	BufferedImage walkImages [] = new BufferedImage[6]; //Array de imágenes para caminar
	public void loadWalk() { //Método para cargar las imágenes de caminar
		walkImages[0] = img.getSubimage(6, 20, 56, 93);
		walkImages[1]= img.getSubimage(70, 19, 54, 94);
		walkImages[2]= img.getSubimage(135, 18, 59, 95);
		walkImages[3]= img.getSubimage(199, 19, 68, 94);
		walkImages[4]= img.getSubimage(275, 20, 70, 93);
		walkImages[5]= img.getSubimage(353, 25, 63, 88);
	}
	
	BufferedImage punchImages[] = new BufferedImage[6]; //Array de imágenes para puñetazo
	public void loadPunch() { //Método para cargar las imágenes de puñetazo
		punchImages[0]= img.getSubimage(697, 19, 77, 94);
		punchImages[1]= img.getSubimage(582, 18, 107, 95);
		punchImages[2]= img.getSubimage(582, 18, 107, 95);
		punchImages[3]= img.getSubimage(497, 19, 77, 94);
		punchImages[4]= img.getSubimage(423, 19, 66, 94);
		punchImages[5]= img.getSubimage(423, 19, 66, 94);
	}
	
	BufferedImage fallImages[] = new BufferedImage[4]; //Array de caída, en este caso no utilizado
	public void loadFall() { //Método para cargar las imágenes de caída
		fallImages[0]= img.getSubimage(217, 128, 64, 88);
		fallImages[1]= img.getSubimage(148, 146, 61, 70);
		fallImages[2]= img.getSubimage(78, 151, 62, 65);
		fallImages[3]= img.getSubimage(7, 130, 64, 86);
	}

	BufferedImage jumpImages[] = new BufferedImage[7]; //Array de imágenes para saltar
	public void loadJump(){ //Método para cargar las imágenes de saltar
		jumpImages[0]= img.getSubimage(7, 130, 64, 86);
		jumpImages[1]= img.getSubimage(78, 151, 62, 65);
		jumpImages[2]= img.getSubimage(78, 151, 62, 65);
		jumpImages[3]= img.getSubimage(148, 146, 61, 70);
		jumpImages[4]= img.getSubimage(148, 146, 61, 70);
		jumpImages[5]= img.getSubimage(217, 128, 64, 88);
		jumpImages[6]= img.getSubimage(217, 128, 64, 88);
	}
	
	BufferedImage defenceImages[] = new BufferedImage[6]; //Array de imágenes para defender
	public void loadDefence() { //Método para cargar las imágenes de defender
		defenceImages[0]= img.getSubimage(818, 253, 65, 92);
		defenceImages[1]= img.getSubimage(818, 253, 65, 92);
		defenceImages[2]= img.getSubimage(818, 253, 65, 92);
		defenceImages[3]= img.getSubimage(741, 254, 69, 91);
		defenceImages[4]= img.getSubimage(741, 254, 69, 91);
		defenceImages[5]= img.getSubimage(741, 254, 69, 91);		
	}

	BufferedImage kickImages[] = new BufferedImage[7]; //Array de imágenes para patear
	public void loadKick(){ //Método para cargar las imágenes de patear
		kickImages[0]= img.getSubimage(6, 264, 66, 94);
		kickImages[1]= img.getSubimage(80, 257, 83, 101);
		kickImages[2]= img.getSubimage(410, 257, 83, 99);
		kickImages[3]= img.getSubimage(343, 265, 59, 91);
		kickImages[4]= img.getSubimage(243, 270, 101, 86);
		kickImages[5]= img.getSubimage(171, 269, 55, 88);
		kickImages[6]= img.getSubimage(80, 257, 83, 101);
	}
	
	
	
	
	private int punchIndex = 0; //Índice para las imágenes de puñetazo y controlar qué imagenes se cargan en la animación
	private void drawPunch(Graphics g) { //Método para dibujar la animación de puñetazo
		g.drawImage(punchImages[punchIndex], x, y, w, h, null);
		punchIndex++;
		if(punchIndex==punchImages.length) {
			punchIndex = 0;
			mode = WALK; //Una vez termina la animación, vuelve al modo caminar
		}
	}
	
	private int jumpIndex = 0; //Índice para las imágenes de salto y controlar qué imagenes se cargan en la animación
	private void drawJump(Graphics g) { //Método para dibujar la animación de salto
		g.drawImage(jumpImages[jumpIndex], x, y, w, h, null);
		jumpIndex++;
		if(jumpIndex==jumpImages.length) {
			jumpIndex = 0;
			mode = WALK; //Una vez termina la animación, vuelve al modo caminar
		}
	}

	private int walkIndex = 0; //Índice para las imágenes de caminar y controlar qué imagenes se cargan en la animación
	private void drawWalk(Graphics g) { //Método para dibujar la animación de caminar
		g.drawImage(walkImages[walkIndex], x, y, w, h, null);
		walkIndex++;
		if(walkIndex==walkImages.length) {
			walkIndex = 0;
		}
	}

	private int kickIndex = 0; //Índice para las imágenes de patear y controlar qué imagenes se cargan en la animación
	private void drawKick(Graphics g) {	//Método para dibujar la animación de patear
		g.drawImage(kickImages[kickIndex], x, y, w, h, null);
		kickIndex++;
		if (kickIndex == kickImages.length) {
			kickIndex = 0;
			mode = WALK; //Una vez termina la animación, vuelve al modo caminar
		}
	}
	
	 
	private int fallIndex = 0; //Índice para las imágenes de caída y controlar qué imagenes se cargan en la animación
	int mydelay = 1; //Delay para la animación de caída
	private void drawFall(Graphics g) { //Método para dibujar la animación de caída
		if(mydelay==2) {
		g.drawImage(fallImages[fallIndex], x, y, w, h, null);
		fallIndex++;
		if(fallIndex==fallImages.length) {
			fallIndex = 0;
			mode = WALK; //Una vez termina la animación, vuelve al modo caminar
		}
		mydelay = 1;
		}
		mydelay++;
	}
	
	private int defenceIndex = 0; //Índice para las imágenes de defender y controlar qué imagenes se cargan en la animación
	private void drawDefence(Graphics g) { //Método para dibujar la animación de defender
		g.drawImage(defenceImages[defenceIndex], x, y, w, h, null);
		defenceIndex++;
		if(defenceIndex==defenceImages.length) {
			defenceIndex = 0;
			mode = WALK; //Una vez termina la animación, vuelve al modo caminar
		}
	}
	
	
	
	@Override
	public void draw(Graphics g) { //Método para dibujar el personaje
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
	
	int barwidth = 300; //Ancho de la barra de vida
	public int drawProgress(Graphics g) { //Método para dibujar la barra de vida
		barwidth = barwidth-20; //Disminuye la barra de vida
		g.setColor(Color.RED); //Color de la barra de vida cuando es drenada
		g.fillRect(400, 40, barwidth, 40); //Dibuja la barra de vida que ha sido dañada
		return barwidth;
	}
	

}
