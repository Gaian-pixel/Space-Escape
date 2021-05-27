package com.GiorgioAlessio.game.grafica;

//Importazione librerie necessarie
import java.awt.image.BufferedImage;

/*Creazione della classe "FoglioSprite" che gestisce gli spritesheet.*/
public class FoglioSprite
{
	/*Dichiarazione attributo "foglio", di tipo BufferedImage che caricherà il nostro foglio
	  con il nostro sprite.*/
	private BufferedImage foglio;
	
	/*Dichiarazione costruttore*/
	public FoglioSprite(BufferedImage foglio)
	{
		this.foglio = foglio;
	}
	
	/*Definizione del metodo "taglio" che prende come parametri le coordinate da cui iniziare a
	  ritagliare l'immagine e le dimensioni del taglio. Infine restituisce l'immagine tagliata.*/
	public BufferedImage taglio(int x, int y, int larghezza, int altezza)
	{
		return foglio.getSubimage(x, y, larghezza, altezza);
	}
}
