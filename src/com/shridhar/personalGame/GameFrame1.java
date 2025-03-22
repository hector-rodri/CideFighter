package com.shridhar.personalGame;

import javax.swing.JFrame;

import com.shridhar.personalGame.utils.GameUtils1;

public class GameFrame1 extends JFrame implements GameUtils1 {
	public GameFrame1() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle(TITLE);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board1 board1 = new Board1();
		this.add(board1);
		setVisible(true);

	}
	

}
