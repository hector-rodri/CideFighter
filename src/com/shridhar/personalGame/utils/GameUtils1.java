package com.shridhar.personalGame.utils;

public interface GameUtils1 { //Interfaz para almacenar constantes del juego.
    int FRAME_HEIGHT = 600; //Altura de la ventana
    int FRAME_WIDTH = 800; //Ancho de la ventana
    String TITLE = "Street Fighter"; //Título de la ventana del juego
    String KEN_IMAGE = "SpriteKen.png"; //Nombre del archivo de imagen para los personajes del juego y el fondo
    String RYU_IMAGE = "SpriteRyu.png"; 
    String BG_IMAGE = "StreetFighterBG.png"; 
    int FLOOR = FRAME_HEIGHT - 80; //Posición del suelo en el juego
    int DELAY = 100; //Retraso en milisegundos para el temporizador del juego
    int TIME_LEFT = 50; //Tiempo inicial restante para el juego
}