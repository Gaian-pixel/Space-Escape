package com.GiorgioAlessio.game.grafica;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CaricatoreImmagini
{
    //Metodo che carica e mostra l'immagine. Prende come parametro il percorso
    //dell'immagine
    public static BufferedImage caricaImmagine(String percorsoImmagine)
    {
        try
        {
            //Ritorna l'immagine che si vuole stampare bufferizzata
            return ImageIO.read(CaricatoreImmagini.class.getResource(percorsoImmagine));
        }
        catch (IOException ex) 
        {
            ex.printStackTrace();
            System.exit(1);
        }
        
        return null;
    }
}