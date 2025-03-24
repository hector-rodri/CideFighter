package com.shridhar.personalGame;

public interface IPlayer1 { // Interfaz IPlayer1 para definir los métodos que deben implementar los personajes
	int WALK = 1; // Constantes para los movimientos, cada movimiento tiene un valor entero y que es utilizado para describir el estado del personaje
	int PUNCH = 2;
	int FALL = 3;
	int KICK = 4;
	int DEFENCE = 5;
	int POWER = 6;
	int JUMP = 7;

	boolean isCanDealDamage();
}
