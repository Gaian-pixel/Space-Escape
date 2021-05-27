package com.GiorgioAlessio.game;

//Importa i pacchetti necessari

public class Launcher
{
  public static void main(String[] args)
  {
      //Crea una nuova istanza della classe "Gioco" e gli passa come parametro
	  //il titolo della finestra e le sue dimensioni
	  Gioco gioco = new Gioco("Space Escape",600,600);
	  //Richiamo al metodo "avvio" che avvia un processo del nostro gioco "Gioco"
	  gioco.avvio();
  }
}