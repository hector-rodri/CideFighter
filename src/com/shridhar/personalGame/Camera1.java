package com.shridhar.personalGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.shridhar.personalGame.utils.GameUtils1;

public class Camera1 extends Sprite1 implements GameUtils1 {
	public Camera1() {
		loadImage();
		this.x = 0;
		this.y = 0;
		this.h = FRAME_HEIGHT;
		this.w = FRAME_WIDTH;
	}
	
	public void drawBG(Graphics g) {
		BufferedImage sub = img.getSubimage(24, 796, 384, 224);
		g.drawImage(sub, 0, 0, w, h, null);
	}

}
