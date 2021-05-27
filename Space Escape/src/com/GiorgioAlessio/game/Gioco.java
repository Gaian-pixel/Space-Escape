package com.GiorgioAlessio.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.awt.image.BufferedImage;

import com.GiorgioAlessio.game.display.Display;
import com.GiorgioAlessio.game.grafica.Camera;
//import com.GiorgioAlessio.game.grafica.FoglioSprite;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.input.GestoreMouse;
import com.GiorgioAlessio.game.input.GestoreTasti;
import com.GiorgioAlessio.game.stati.Stato;
import com.GiorgioAlessio.game.stati.StatoMenù;

//Definizione della classe "Gioco". "Runnable" indica che questa classe avrà più thread (processi)
public class Gioco implements Runnable
{
  //Istanziamento le variabili display (istanza della classe Display), titolo,
  //larghezza e altezza della finestra
  private Display display;
  private String titolo;
  private int larghezza,altezza;
  
  //Variabile booleana utilizzata per eseguire il ciclo in "run"
  private boolean esecuzione = false;
  
  //Istanziamento dell'attributo "processo" di tipo Thread
  private Thread processo;
  
  //Istanziamento di bufferStrategico e di grafica, che verranno usate per
  //disegnare sulla nostra telaGrafica
  private BufferStrategy bufferStrategico;
  private Graphics disegnatore;
  
  //Codice temporaneo per caricare le immagini sul nostro gioco
  //private BufferedImage testImmagine;
  
  //Dichiarazione dell'attributo "foglio" di tipo "FoglioSprite".
  //private FoglioSprite foglio;
  
  /*Dichiarazione degli attributi "statiGioco" di tipo "Stato".*/
  public Stato statoGioco;
  public Stato statoMenu;
  public Stato statoInput;
  public Stato statoIntro;
  public Stato statoMorte;
  public Stato statoVittoria;
  public Stato statoConnessione;
  
  /*Dichiarazione degli attributi per l'input.*/
  private GestoreTasti gestoreTasti;
  private GestoreMouse gestoreMouse;
  
  /*Dichiarazione della Camera di Gioco. In questo modo possiamo passare la camera ad ogni
   classe e spostarla a nostro piacimento.*/
  private Camera camera;
  
  public static Maneggiatore maneggiatore;

  //Costruttore della classe Gioco
  public Gioco(String titolo, int larghezza, int altezza)
  {
      //Inizializzazione degli attributi
      this.larghezza = larghezza;
      this.altezza = altezza;
      this.titolo = titolo;
      gestoreTasti = new GestoreTasti();
      gestoreMouse = new GestoreMouse();
  }
  
  /*Getter.*/
  public GestoreTasti getGestoreTasti()
  {
	  return gestoreTasti;
  }
  
  public GestoreMouse getGestoreMouse()
  {
	  return gestoreMouse;
  }
  
  public Camera getCamera()
  {
	return camera;
  }
  
  public int getLarghezza()
  {
	return larghezza;
  }
  
  public int getAltezza()
  {
	return altezza;
  }

public Stato getStatoGioco()
{
	return statoGioco;
}

public void setStatoGioco(Stato statoGioco)
{
	this.statoGioco = statoGioco;
}

public Stato getStatoMenu()
{
	return statoMenu;
}

public void setStatoMenu(Stato statoMenu)
{
	this.statoMenu = statoMenu;
}

public Stato getStatoInput()
{
	return statoInput;
}

public void setStatoInput(Stato statoInput)
{
	this.statoInput = statoInput;
}

public Stato getStatoMorte()
{
	return statoMorte;
}

public void setStatoMorte(Stato statoMorte)
{
	this.statoMorte = statoMorte;
}

public Stato getStatoConnessione()
{
	return statoConnessione;
}

public void setStatoConnessione(Stato statoConnessione)
{
	this.statoConnessione = statoConnessione;
}

public Stato getStatoVittoria() {
	return statoVittoria;
}

public void setStatoVittoria(Stato statoVittoria) {
	this.statoVittoria = statoVittoria;
}

public Stato getStatoIntro() {
	return statoIntro;
}

public void setStatoIntro(Stato statoIntro) {
	this.statoIntro = statoIntro;
}

public void resettaStati()
{
	setStatoGioco(null);
	setStatoMenu(null);
	setStatoInput(null);
	setStatoMorte(null);
	setStatoConnessione(null);
	setStatoVittoria(null);
}

//Definizione del metodo "inizializzazione"
  private void inizializzazione()
  {
      //Costruzione della Istanza display
      display = new Display(titolo,larghezza,altezza);
      
      /*Viene aggiunto il nostro gestore degli input da tastiera
       * alla nostra cornice.*/
      display.getCornice().addKeyListener(gestoreTasti);
      display.getCornice().addMouseListener(gestoreMouse);
      display.getCornice().addMouseMotionListener(gestoreMouse);
      display.getTelaGrafica().addMouseListener(gestoreMouse);
      display.getTelaGrafica().addMouseMotionListener(gestoreMouse);
      
      //Codice temporaneo per caricare la nostra immagine nel nostro programma
      /*testImmagine = CaricatoreImmagini.caricaImmagini("/Textures/Runner Stickman.png");*/
      
      //Inizializzazione dell'attributo "foglio". Gli viene passato come parametro 
      //"testImmagine" contenente l'immagine del nostro sprite sheet.
      /*foglio = new FoglioSprite(testImmagine);*/
      
      /*Inizializzazione della classe Risorse.*/
      Risorse.inizializzazione();
      
      maneggiatore = new Maneggiatore(this);
      /*Inizializzazione Camera di Gioco*/
      camera = new Camera(maneggiatore,0,0);
      
      /*Inizializzazione degli stati di gioco.*/
      statoMenu = new StatoMenù(maneggiatore);
      Stato.setStato(statoMenu);
  }

//Metodo che aggiorna le variabili di gioco ogni giro di ciclo "while" nel metodo "run"
  private void aggiornamento()
  {
	  gestoreTasti.aggiornamento();
	  
	  /*Se lo stato di gioco esiste eseguiamo il suo aggiornamento.*/
      if(Stato.getStato() != null)
      {
    	  Stato.getStato().aggiornamento();
      }
  }
  
  //Metodo che aggiorna la grafica di gioco ogni giro di ciclo "while" nel metodo "run"
  private void render()
  {
      //Applicazione di un buffer "davanti" alla tela grafica. Prima di disegnare 
      //sulla tela grafica il programma disegnerà i nostri oggetti grafici sul buffer 
      //che, in seguito, andrà a disegnare i nostri oggetti grafici sulla tela
      bufferStrategico = display.getTelaGrafica().getBufferStrategy();
      
      //Se il buffer è vuoto (== null) vengono creati 3 buffer "strategici"
      if(bufferStrategico == null)
      {
          display.getTelaGrafica().createBufferStrategy(3);
          return;
      }
      
      //Viene definito l'oggetto "disegnatore" di tipo Graphic che disegnerà i
      //nostri oggetti grafici sui nostri buffer. Può disegnare qualsiasi forma
      //geometrica
      disegnatore = bufferStrategico.getDrawGraphics();
      
      //La riga seguente pulisce lo schermo
      disegnatore.clearRect(0, 0, larghezza, altezza);
      
      //Comando per cambiare colore dell'oggetto "disegnatore". Qualsiasi cosa
      //disengata dal disegnatore dopo questo comando avrà questo colore.
      
      /*disegnatore.setColor(Color.orange);*/
      
      //Viene disegnato un rettangolo ripieno. Le prime due cifre indicano la posizione 
      //da cui il nostro disegnatore dovrà iniziare a disegnare il rettangolo
      //mentre le altre due cifre indicano le dimensioni del rettangolo
      
      /*disegnatore.fillRect(10, 50, 50, 70);*/
      
      //Viene disegnato un rettangolo vuoto. I parametri hanno gli stessi valori
      //del comando "fillRect()"
      
      /*disegnatore.setColor(Color.blue);
      disegnatore.drawRect(0,0,10,10);*/
      
      //I comandi seguenti disegnano un cerchio seguendo la stessa logica dei
      //rettangoli, di colore verde
      
      /*disegnatore.setColor(Color.green);
      disegnatore.fillOval(50, 10, 50, 50);*/
      
      /*Utilizziamo il disegnatore per caricare un'immagine. Il primo parametro
      del comando è l'immagine stessa, il secondo e il terzo parametro sono 
      le coordinate dell'immagine (x,y) mentre l'ultimo parametro è l'osservatore
      dell'immagine (utilizzo ignoto, verrà impostato a "null" in quanto non
      viene usato)*/
      //disegnatore.drawImage(testImmagine,10,10,null);
      
      /*Comando che disegna uno dei nostri sprite attraverso la classe "FoglioSprite".*/
      //disegnatore.drawImage(foglio.taglio(32, 0, 32, 32), 10, 10,null);
      
      /*Viene disegnato un albero dalla classe "Risorse" attraverso il disegnatore.*/
      //disegnatore.drawImage(Risorse.omino, x, y,null);
      
      /*Se lo stato di gioco esiste eseguiamo la sua renderizzazione.*/
      if(Stato.getStato() != null)
      {
    	  Stato.getStato().renderizzazione(disegnatore);
      }
      
      //Viene mostrato il rettangolo
      bufferStrategico.show();
      disegnatore.dispose();
  }
  
  //Definizione del metodo "Run". Questo metodo permette di eseguire più thread (processi) di questa classe.
  public void run()
  {
      //Richiamo al metodo "inizializzazione" che inizializza i comandi da eseguire
      inizializzazione();
      
      /*Definizione variabili che definiscono il tempo del ciclo.*/
      int fps = 60;
      double timePerTick = 1000000000 / fps;
      double delta = 0;
      long adesso;
      long lastTime = System.nanoTime();
      
      /*Variabili per contare gli FPS.*/
      long timer = 0;
      int ticks = 0;
      
      //Inizio di un ciclo infinito che esegue il nostro gioco fino a quando non viene
      //chiuso il gioco stesso
      while(esecuzione)
      {
    	  /*Definizione del tempo del ciclo.*/
    	  adesso = System.nanoTime();
    	  delta = delta + ((adesso - lastTime) / timePerTick);
    	  
    	  timer = adesso - lastTime;
    	  
    	  lastTime = adesso;
    	  
          //Richiamo ai metodi "aggiornamento" e "render"
    	  if(delta >= 1)
    	  {
	          aggiornamento();
	          render();
	          
	          delta--;
	          ticks++;
    	  }
    	  
    	  //Visualizzazione degli FPS correnti sulla console.
    	  if(timer >= 1000000000)
    	  {
    		  System.out.println("Ticks: " + ticks);
    		  ticks = 0;
    		  timer = 0;
    	  }
      }
      
      //Richiamo al metodo per chiudere il processo
      spegnimento();
  }
  
  //Definizione del metodo "avvio" che farà avviare i diversi processi
  public synchronized void avvio()
  {
      //Controllo che avvia un nuovo processo del gioco se la variabile booleana
      //"esecuzione" è uguale a false. Se questa variabile è uguale a "false" 
      //vuol dire che al momento non ci sono processi attivi e che quindi se ne
      //può creare un'altro
      if(!esecuzione)
      {
          esecuzione=true;
          
          //Viene istanziato un nuovo processo della classe "Gioco" (this) attraverso 
          //l'oggetto "processo"
          processo = new Thread(this);

          //Avvio del nuovo processo. Questo metodo richiamerà il metodo "run" che
          //contiene i comandi del gioco vero e proprio
          processo.start();
      }
  }
  
  //Definizione del metodo "spegnimento" che farà terminare i diversi processi
  public synchronized void spegnimento()
  {
      //Controllo che termina il processo attualmente attivo se la variabile "esecuzione"
      //è uguale a "true". Se questa variabile è uguale a "true" vuol dire che
      //ci sono processi attivi da terminare.
      if(esecuzione)
      {
          //Termina il nuovo processo correttamente
          try
          {
              //Giorgio dice che il metodo join lancia un sempre un eccezione per 
              //terminare il processo, ed effettivamente aveva ragione

              /*
              Definizione più accurata del metodo "join":
              Il metodo join viene utilizzato per aspettare la terminazione di un 
              thread, il metodo join mette in attesa il thread chiamante al thread
              da cui si invoca il metodo, se è già terminato ritorna direttamente.
              Anche join è un metodo bloccante quindi lancia un’eccezione verificata
              quando viene interrotto.

              Vediamo un esempio, dati due thread t1 e t2 con t2 visibile in t1.

              Eseguendo quest’istruzione:
              t2.join();

              Il metodo join mette in attesa il thread 1 fino alla terminazione
              del thread 2. Se al momento del’invocazione di join il thread 2 è già
              terminato ritorna immediatamente.
              */
              processo.join();
          }
          catch (InterruptedException ex)
          {
              ex.printStackTrace();
          }
      }
  }
}