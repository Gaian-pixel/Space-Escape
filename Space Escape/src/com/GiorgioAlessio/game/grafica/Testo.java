package com.GiorgioAlessio.game.grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Testo
{
	public static void disegnaStringa(Graphics disegnatore, String testo, int posizioneX, int posizioneY, boolean centrato, Color colore, Font font)
	{
		disegnatore.setColor(colore);
		disegnatore.setFont(font);
		
		int x = posizioneX;
		int y = posizioneY;
		
		if(centrato)
		{
			FontMetrics metricaFont = disegnatore.getFontMetrics(font);
			
			x = posizioneX - metricaFont.stringWidth(testo) / 2;
			y = (posizioneY - metricaFont.getHeight() / 2) + metricaFont.getAscent();
		}
		
		disegnatore.drawString(testo, x, y);
	}
}