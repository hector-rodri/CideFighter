package com.shridhar.personalGame;

import java.awt.Graphics;

public class Camera1 extends Sprite1 {
	public Camera1() {
		loadImage(this);
		this.x = 0;
		this.y = 0;
		this.h = FRAME_HEIGHT;
		this.w = FRAME_WIDTH;
	}
	
	public void drawBG(Graphics g) {
		g.drawImage(img, 0, 0, w, h, null);
	}

}
