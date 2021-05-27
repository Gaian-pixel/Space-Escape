package com.GiorgioAlessio.game.display;

//Importa le librerie necessarie
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public final class Display
{
	  //Istanziamento di "cornice" (istanza della classe JFrame) e di telaGRafica (istanza 
	  //della classe Canvas). "cornice" è la cornice della finestra, mentre canvas 
	  //è il supporto per i "disegni" del nostro gioco. Le operazioni grafiche 
	  //verranno eseguite su quest'ultimo oggetto
	  private JFrame cornice;
	  Canvas telaGrafica;
	  
	  //Istanziamento degli attributi titolo, larghezza e altezza necessari per il
	  //frame
	  private String titolo;
	  private int larghezza,altezza;
	
	  //Costruttore della classe display
	  public Display(String titolo, int larghezza, int altezza)
	  {
	      //Inizializzazione delle variabili
	      this.titolo = titolo;
	      this.larghezza = larghezza;
	      this.altezza = altezza;
	      
	      //Richiamo al metodo "creaFinestra", che definisce le caratteristiche della
	      //nostra finestra
	      creaFinestra();
	  }
	  
	  //Definizione dei metodi getter 
	  public Canvas getTelaGrafica()
	  {
	      return telaGrafica;
	  }
	  
	  public JFrame getCornice()
	  {
		  return cornice;
	  }
	  
	  public void creaFinestra()
	  {
	      //Creazione della nostra cornice
	      cornice = new JFrame(titolo);
	      cornice.setSize(altezza, larghezza);
	      cornice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      //Definizione delle caratteristiche della nostra cornice
	      //Il metodo "setResizable" indica che la cornice non è ridimensionabile.
	      //Il metodo "setLocationRelativeTo" indica che la nostra cornice 
	      //deve comparire al centro del nostro schermo.
	      cornice.setResizable(false);
	      cornice.setLocationRelativeTo(null);
	      
	      //Rende la cornice visibile
	      cornice.setVisible(true);
	      
	      //Creazione della nostra tela grafica delle dimensioni della cornice.
	      telaGrafica = new Canvas();
	      telaGrafica.setPreferredSize(new Dimension((int)larghezza,(int)altezza));
	      telaGrafica.setMaximumSize(new Dimension((int)larghezza,(int)altezza));
	      telaGrafica.setMinimumSize(new Dimension((int)larghezza,(int)altezza));
	      
	      /*Impedisce al dispositivo di concentrarsi sul disegno in modo da
	       * mandare l'attenzione degli eventi alla cornice.*/
	      telaGrafica.setFocusable(false);
	      
	      //Inserimento della tela grafica nella cornice e riadeguamento delle dimensioni
	      cornice.add(telaGrafica);
	      cornice.pack();
	  }
}
