package com.GiorgioAlessio.game.grafica;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class CaricatoreFonts
{
	public static Font caricaFont(String percorso,float dimensioni)
	{
		try
		{
			return Font.createFont(Font.TRUETYPE_FONT, new File(percorso)).deriveFont(Font.PLAIN,dimensioni);
		}
		catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}