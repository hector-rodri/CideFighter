package com.shridhar.personalGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.shridhar.personalGame.utils.GameUtils1;


public class Sprite1 implements GameUtils1 {
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected int speed;
	protected Image image;
	protected BufferedImage img;
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, w, h, null);
	}
	
	public void loadImage() {
		try {
			img = ImageIO.read(Sprite1.class.getResource(GAME_IMAGE));
			System.out.println("Image is loaded....");
		}
		catch(IOException e) {
			System.out.println("Image is not Available");
		}
	}
	
	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public Sprite1() {
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	

}
