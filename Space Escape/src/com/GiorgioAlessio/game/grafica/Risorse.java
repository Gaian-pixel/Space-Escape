package com.GiorgioAlessio.game.grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import com.GiorgioAlessio.game.items.Item;

//Dichiarazione della classe "Risorse".
public class Risorse
{
	/*Dichiarazione delle costanti "larghezza " e "altezza" che indicano la larghezza
	 * e l'altezza fisse di ogni immagine tagliata.*/
	private static final int larghezzaRiquadro = 64, altezzaRiquadro = 64;
	private static final int larghezzaPlayer = 128, altezzaPlayer = 128;
	private static final int larghezzaNemico = 128, altezzaNemico = 128;
	private static final int larghezzaBtn = 128, altezzaBtn = 128;
	private static final int larghezzaEntità = 64, altezzaEntità = 64;
	private static final int larghezzaItem = 64, altezzaItem = 64;
	public static final int larghezzaVignetta = 441, altezzaVignetta = 180;
	
	/*Dichiarazione delle immagini che ci servono*/
	public static BufferedImage vuoto,pavimento,pavimentoCavi,pavimentoCratere,pavimentoSangue;
	public static BufferedImage muro[],muroGraffio[],muroCavi[],muroCratere[];
	public static BufferedImage porta[],spigolo[],spigoloInverso[];
	public static BufferedImage oblòSu[],oblòSx[],oblòGiù[],oblòDx[];
	
	public static BufferedImage portaSu[],portaGiù[],portaDestra[],portaSinistra[];
	
	public static BufferedImage roccia,legno,inventario;
	public static BufferedImage mediKit,garza,ammo,pass,vittoria;
	
	public static BufferedImage scrivania,ologramma[],sedia;
	public static BufferedImage[] cassaDavanti;
	public static BufferedImage[] cassaDietro;
	public static BufferedImage[] cassaSinistra;
	public static BufferedImage[] cassaDestra;
	
	public static BufferedImage testaPlayer;
	public static BufferedImage[] player_fermo;
	public static BufferedImage[] player_su;
	public static BufferedImage[] player_sinistra;
	public static BufferedImage[] player_giù;
	public static BufferedImage[] player_destra;
	
	public static BufferedImage[] nemico_fermo;
	public static BufferedImage[] nemico_su;
	public static BufferedImage[] nemico_sinistra;
	public static BufferedImage[] nemico_giù;
	public static BufferedImage[] nemico_destra;
	public static BufferedImage[] nemico_morto;
	
	public static BufferedImage[] bottone_start;
	
	//Fonts
	public static Font fontInventario;
	public static Font fontDanno;
	public static Font fontErrore;
	public static Font fontMenùConnessione;
	
	public static Color coloreSfondo = new Color(51,51,51);
	public static Color coloreScritte = new Color(255,255,255);
	
	//Item
	public static Item itemMediKit;
	public static Item itemGarza;
	public static Item itemAmmo;
	public static Item itemPass;
	public static Item itemVittoria;
	
	public static BufferedImage logo;
	public static BufferedImage vignetta[];
	
	public static BufferedImage vignettaSfondo;
	
	public static void inizializzazione()
	{
		/*Salvataggio del nostro spritesheet nella variabile foglio e successiva sua
		  suddivisione nelle immagini che ci servono.*/
		FoglioSprite foglioRiquadri = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/TileMondo.png"));
		FoglioSprite foglioPlayer = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/Giocatore.png"));
		FoglioSprite foglioNemico = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/Nemico.png"));
		FoglioSprite foglioBottone = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/Bottone.png"));
		FoglioSprite foglioEntità = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/Entità.png"));
		FoglioSprite foglioItems = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/Items.png"));
		FoglioSprite foglioNemicoMorto = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/NemicoMorto.png"));
		FoglioSprite foglioAnimPorta = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/Animazione Porta.png"));
		FoglioSprite foglioVignetta = new FoglioSprite(CaricatoreImmagini.caricaImmagine("/Textures/Vignetta Spritesheet.png"));
		
		vuoto = CaricatoreImmagini.caricaImmagine("/Textures/Void.png");
		logo = CaricatoreImmagini.caricaImmagine("/Textures/Logo.png");
		
		player_fermo = new BufferedImage[4];
		player_su = new BufferedImage[8];
		player_sinistra = new BufferedImage[8];
		player_giù = new BufferedImage[8];
		player_destra = new BufferedImage[8];
		
		nemico_fermo = new BufferedImage[4];
		nemico_su = new BufferedImage[8];
		nemico_sinistra = new BufferedImage[8];
		nemico_giù = new BufferedImage[8];
		nemico_destra = new BufferedImage[8];
		nemico_morto = new BufferedImage[4];
		
		muro = new BufferedImage[4];
		muroGraffio = new BufferedImage[4];
		muroCavi = new BufferedImage[4];
		muroCratere = new BufferedImage[4];
		porta = new BufferedImage[4];
		
		/*oblò1 = new BufferedImage[4];
		oblò2 = new BufferedImage[4];*/
		
		oblòSu = new BufferedImage[2];
		oblòSx = new BufferedImage[2];
		oblòGiù = new BufferedImage[2];
		oblòDx = new BufferedImage[2];
		
		ologramma = new BufferedImage[2];
		
		spigolo = new BufferedImage[4];
		spigoloInverso = new BufferedImage[4];

		bottone_start = new BufferedImage[2];
		
		cassaDavanti = new BufferedImage[2];
		cassaDietro = new BufferedImage[2];
		cassaDestra = new BufferedImage[2];
		cassaSinistra = new BufferedImage[2];
		
		portaSu = new BufferedImage[4];
		portaGiù = new BufferedImage[4];
		portaDestra = new BufferedImage[4];
		portaSinistra = new BufferedImage[4];
		
		vignetta = new BufferedImage[20];
		
		bottone_start[0] = foglioBottone.taglio(0, 0, larghezzaBtn, altezzaBtn);
		bottone_start[1] = foglioBottone.taglio(0, altezzaBtn, larghezzaBtn, altezzaBtn);
		
		testaPlayer = CaricatoreImmagini.caricaImmagine("/Textures/Giocatore Busto.png");
		
		player_fermo[0] = foglioPlayer.taglio(0, 0, larghezzaPlayer, altezzaPlayer);
		player_fermo[1] = foglioPlayer.taglio(0, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		player_fermo[2] = foglioPlayer.taglio(0, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		player_fermo[3] = foglioPlayer.taglio(0, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		
		player_giù[0] = foglioPlayer.taglio(larghezzaPlayer, 0, larghezzaPlayer, altezzaPlayer);
		player_giù[1] = foglioPlayer.taglio(larghezzaPlayer * 2, 0, larghezzaPlayer, altezzaPlayer);
		player_giù[2] = foglioPlayer.taglio(larghezzaPlayer * 3, 0, larghezzaPlayer, altezzaPlayer);
		player_giù[3] = foglioPlayer.taglio(larghezzaPlayer * 4, 0, larghezzaPlayer, altezzaPlayer);
		player_giù[4] = foglioPlayer.taglio(larghezzaPlayer * 5, 0, larghezzaPlayer, altezzaPlayer);
		player_giù[5] = foglioPlayer.taglio(larghezzaPlayer * 6, 0, larghezzaPlayer, altezzaPlayer);
		player_giù[6] = foglioPlayer.taglio(larghezzaPlayer * 7, 0, larghezzaPlayer, altezzaPlayer);
		player_giù[7] = foglioPlayer.taglio(larghezzaPlayer * 8, 0, larghezzaPlayer, altezzaPlayer);
		
		player_destra[0] = foglioPlayer.taglio(larghezzaPlayer, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		player_destra[1] = foglioPlayer.taglio(larghezzaPlayer * 2, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		player_destra[2] = foglioPlayer.taglio(larghezzaPlayer * 3, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		player_destra[3] = foglioPlayer.taglio(larghezzaPlayer * 4, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		player_destra[4] = foglioPlayer.taglio(larghezzaPlayer * 5, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		player_destra[5] = foglioPlayer.taglio(larghezzaPlayer * 6, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		player_destra[6] = foglioPlayer.taglio(larghezzaPlayer * 7, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		player_destra[7] = foglioPlayer.taglio(larghezzaPlayer * 8, altezzaPlayer, larghezzaPlayer, altezzaPlayer);
		
		player_sinistra[0] = foglioPlayer.taglio(larghezzaPlayer, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		player_sinistra[1] = foglioPlayer.taglio(larghezzaPlayer * 2, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		player_sinistra[2] = foglioPlayer.taglio(larghezzaPlayer * 3, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		player_sinistra[3] = foglioPlayer.taglio(larghezzaPlayer * 4, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		player_sinistra[4] = foglioPlayer.taglio(larghezzaPlayer * 5, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		player_sinistra[5] = foglioPlayer.taglio(larghezzaPlayer * 6, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		player_sinistra[6] = foglioPlayer.taglio(larghezzaPlayer * 7, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		player_sinistra[7] = foglioPlayer.taglio(larghezzaPlayer * 8, altezzaPlayer * 2, larghezzaPlayer, altezzaPlayer);
		
		player_su[0] = foglioPlayer.taglio(larghezzaPlayer, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		player_su[1] = foglioPlayer.taglio(larghezzaPlayer * 2, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		player_su[2] = foglioPlayer.taglio(larghezzaPlayer * 3, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		player_su[3] = foglioPlayer.taglio(larghezzaPlayer * 4, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		player_su[4] = foglioPlayer.taglio(larghezzaPlayer * 5, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		player_su[5] = foglioPlayer.taglio(larghezzaPlayer * 6, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		player_su[6] = foglioPlayer.taglio(larghezzaPlayer * 7, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		player_su[7] = foglioPlayer.taglio(larghezzaPlayer * 8, altezzaPlayer * 3, larghezzaPlayer, altezzaPlayer);
		
		//---------------------------------------------------------------------------------------------------
		
		nemico_fermo[0] = foglioNemico.taglio(0, 0, larghezzaNemico, altezzaNemico);
		nemico_fermo[1] = foglioNemico.taglio(0, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_fermo[2] = foglioNemico.taglio(0, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		nemico_fermo[3] = foglioNemico.taglio(0, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		
		nemico_giù[0] = foglioNemico.taglio(larghezzaNemico, 0, larghezzaNemico, altezzaNemico);
		nemico_giù[1] = foglioNemico.taglio(larghezzaNemico * 2, 0, larghezzaNemico, altezzaNemico);
		nemico_giù[2] = foglioNemico.taglio(larghezzaNemico * 3, 0, larghezzaNemico, altezzaNemico);
		nemico_giù[3] = foglioNemico.taglio(larghezzaNemico * 4, 0, larghezzaNemico, altezzaNemico);
		nemico_giù[4] = foglioNemico.taglio(larghezzaNemico * 5, 0, larghezzaNemico, altezzaNemico);
		nemico_giù[5] = foglioNemico.taglio(larghezzaNemico * 6, 0, larghezzaNemico, altezzaNemico);
		nemico_giù[6] = foglioNemico.taglio(larghezzaNemico * 7, 0, larghezzaNemico, altezzaNemico);
		nemico_giù[7] = foglioNemico.taglio(larghezzaNemico * 8, 0, larghezzaNemico, altezzaNemico);
		
		nemico_destra[0] = foglioNemico.taglio(larghezzaNemico, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_destra[1] = foglioNemico.taglio(larghezzaNemico * 2, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_destra[2] = foglioNemico.taglio(larghezzaNemico * 3, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_destra[3] = foglioNemico.taglio(larghezzaNemico * 4, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_destra[4] = foglioNemico.taglio(larghezzaNemico * 5, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_destra[5] = foglioNemico.taglio(larghezzaNemico * 6, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_destra[6] = foglioNemico.taglio(larghezzaNemico * 7, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_destra[7] = foglioNemico.taglio(larghezzaNemico * 8, altezzaNemico, larghezzaNemico, altezzaNemico);
		
		nemico_sinistra[0] = foglioNemico.taglio(larghezzaNemico, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		nemico_sinistra[1] = foglioNemico.taglio(larghezzaNemico * 2, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		nemico_sinistra[2] = foglioNemico.taglio(larghezzaNemico * 3, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		nemico_sinistra[3] = foglioNemico.taglio(larghezzaNemico * 4, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		nemico_sinistra[4] = foglioNemico.taglio(larghezzaNemico * 5, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		nemico_sinistra[5] = foglioNemico.taglio(larghezzaNemico * 6, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		nemico_sinistra[6] = foglioNemico.taglio(larghezzaNemico * 7, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		nemico_sinistra[7] = foglioNemico.taglio(larghezzaNemico * 8, altezzaNemico * 2, larghezzaNemico, altezzaNemico);
		
		nemico_su[0] = foglioNemico.taglio(larghezzaNemico, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		nemico_su[1] = foglioNemico.taglio(larghezzaNemico * 2, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		nemico_su[2] = foglioNemico.taglio(larghezzaNemico * 3, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		nemico_su[3] = foglioNemico.taglio(larghezzaNemico * 4, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		nemico_su[4] = foglioNemico.taglio(larghezzaNemico * 5, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		nemico_su[5] = foglioNemico.taglio(larghezzaNemico * 6, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		nemico_su[6] = foglioNemico.taglio(larghezzaNemico * 7, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		nemico_su[7] = foglioNemico.taglio(larghezzaNemico * 8, altezzaNemico * 3, larghezzaNemico, altezzaNemico);
		
		nemico_morto[0] = foglioNemicoMorto.taglio(0, 0, larghezzaNemico, altezzaNemico);
		nemico_morto[1] = foglioNemicoMorto.taglio(larghezzaNemico, 0, larghezzaNemico, altezzaNemico);
		nemico_morto[2] = foglioNemicoMorto.taglio(0, altezzaNemico, larghezzaNemico, altezzaNemico);
		nemico_morto[3] = foglioNemicoMorto.taglio(larghezzaNemico, altezzaNemico, larghezzaNemico, altezzaNemico);
		
		/*public static BufferedImage pavimento,pavimentoCavi,pavimentoCratere;
	public static BufferedImage muro,muroGraffi,muroCavi,muroCratere;
	public static BufferedImage porta,oblò1,oblò2;
	private static final int larghezzaRiquadro = 64, altezzaRiquadro = 64;*/
		
		pavimento = foglioRiquadri.taglio(0, 0, larghezzaRiquadro, altezzaRiquadro);
		pavimentoCavi = foglioRiquadri.taglio(larghezzaRiquadro, 0, larghezzaRiquadro, altezzaRiquadro);
		pavimentoCratere = foglioRiquadri.taglio(larghezzaRiquadro * 2, 0, larghezzaRiquadro, altezzaRiquadro);
		pavimentoSangue = foglioRiquadri.taglio(larghezzaRiquadro * 3, 0, larghezzaRiquadro, altezzaRiquadro);
		
		muro[0] = foglioRiquadri.taglio(0, altezzaRiquadro, larghezzaRiquadro, altezzaRiquadro);
		muro[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro, larghezzaRiquadro, altezzaRiquadro);
		muro[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro, larghezzaRiquadro, altezzaRiquadro);
		muro[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro, larghezzaRiquadro, altezzaRiquadro);
		
		muroGraffio[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 2, larghezzaRiquadro, altezzaRiquadro);
		muroGraffio[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 2, larghezzaRiquadro, altezzaRiquadro);
		muroGraffio[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 2, larghezzaRiquadro, altezzaRiquadro);
		muroGraffio[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 2, larghezzaRiquadro, altezzaRiquadro);
		
		muroCavi[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 3, larghezzaRiquadro, altezzaRiquadro);
		muroCavi[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 3, larghezzaRiquadro, altezzaRiquadro);
		muroCavi[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 3, larghezzaRiquadro, altezzaRiquadro);
		muroCavi[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 3, larghezzaRiquadro, altezzaRiquadro);
		
		muroCratere[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 4, larghezzaRiquadro, altezzaRiquadro);
		muroCratere[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 4, larghezzaRiquadro, altezzaRiquadro);
		muroCratere[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 4, larghezzaRiquadro, altezzaRiquadro);
		muroCratere[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 4, larghezzaRiquadro, altezzaRiquadro);
		
		porta[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 5, larghezzaRiquadro, altezzaRiquadro);
		porta[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 5, larghezzaRiquadro, altezzaRiquadro);
		porta[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 5, larghezzaRiquadro, altezzaRiquadro);
		porta[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 5, larghezzaRiquadro, altezzaRiquadro);
		
		/*oblò1[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 6, larghezzaRiquadro, altezzaRiquadro);
		oblò1[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 6, larghezzaRiquadro, altezzaRiquadro);
		oblò1[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 6, larghezzaRiquadro, altezzaRiquadro);
		oblò1[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 6, larghezzaRiquadro, altezzaRiquadro);
		
		oblò2[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 7, larghezzaRiquadro, altezzaRiquadro);
		oblò2[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 7, larghezzaRiquadro, altezzaRiquadro);
		oblò2[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 7, larghezzaRiquadro, altezzaRiquadro);
		oblò2[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 7, larghezzaRiquadro, altezzaRiquadro);*/
		
		oblòSu[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 6, larghezzaRiquadro, altezzaRiquadro);
		oblòSu[1] = foglioRiquadri.taglio(0, altezzaRiquadro * 7, larghezzaRiquadro, altezzaRiquadro);
		
		oblòSx[0] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 6, larghezzaRiquadro, altezzaRiquadro);
		oblòSx[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 7, larghezzaRiquadro, altezzaRiquadro);
				
		oblòGiù[0] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 6, larghezzaRiquadro, altezzaRiquadro);
		oblòGiù[1] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 7, larghezzaRiquadro, altezzaRiquadro);
		
		oblòDx[0] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 6, larghezzaRiquadro, altezzaRiquadro);
		oblòDx[1] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 7, larghezzaRiquadro, altezzaRiquadro);
		
		spigolo[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 8, larghezzaRiquadro, altezzaRiquadro);
		spigolo[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 8, larghezzaRiquadro, altezzaRiquadro);
		spigolo[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 8, larghezzaRiquadro, altezzaRiquadro);
		spigolo[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 8, larghezzaRiquadro, altezzaRiquadro);
		
		spigoloInverso[0] = foglioRiquadri.taglio(0, altezzaRiquadro * 9, larghezzaRiquadro, altezzaRiquadro);
		spigoloInverso[1] = foglioRiquadri.taglio(larghezzaRiquadro, altezzaRiquadro * 9, larghezzaRiquadro, altezzaRiquadro);
		spigoloInverso[2] = foglioRiquadri.taglio(larghezzaRiquadro * 2, altezzaRiquadro * 9, larghezzaRiquadro, altezzaRiquadro);
		spigoloInverso[3] = foglioRiquadri.taglio(larghezzaRiquadro * 3, altezzaRiquadro * 9, larghezzaRiquadro, altezzaRiquadro);
		
		scrivania = foglioEntità.taglio(0, 0, larghezzaEntità, altezzaEntità);
		ologramma[0] = foglioEntità.taglio(larghezzaEntità, 0, larghezzaEntità, altezzaEntità);
		ologramma[1] = foglioEntità.taglio(larghezzaEntità*2, 0, larghezzaEntità, altezzaEntità);
		sedia = foglioEntità.taglio(0, altezzaEntità, larghezzaEntità, altezzaEntità);
		
		cassaDavanti[0] = foglioEntità.taglio(larghezzaEntità, altezzaEntità, larghezzaEntità, altezzaEntità);
		cassaDavanti[1] = foglioEntità.taglio(larghezzaEntità*2, altezzaEntità, larghezzaEntità, altezzaEntità);
		cassaSinistra[0] = foglioEntità.taglio(0, altezzaEntità*2, larghezzaEntità, altezzaEntità);
		cassaSinistra[1] = foglioEntità.taglio(larghezzaEntità, altezzaEntità*2, larghezzaEntità, altezzaEntità);
		cassaDestra[0] = foglioEntità.taglio(larghezzaEntità*2, altezzaEntità*2, larghezzaEntità, altezzaEntità);
		cassaDestra[1] = foglioEntità.taglio(0, altezzaEntità*3, larghezzaEntità, altezzaEntità);
		cassaDietro[0] = foglioEntità.taglio(larghezzaEntità, altezzaEntità*3, larghezzaEntità, altezzaEntità);
		cassaDietro[1] = foglioEntità.taglio(larghezzaEntità*2, altezzaEntità*3, larghezzaEntità, altezzaEntità);
		
		portaSu[0] = foglioAnimPorta.taglio(0, 0, larghezzaRiquadro, altezzaRiquadro);
		portaSu[1] = foglioAnimPorta.taglio(larghezzaRiquadro, 0, larghezzaRiquadro, altezzaRiquadro);
		portaSu[2] = foglioAnimPorta.taglio(larghezzaRiquadro*2, 0, larghezzaRiquadro, altezzaRiquadro);
		portaSu[3] = foglioAnimPorta.taglio(larghezzaRiquadro*3, 0, larghezzaRiquadro, altezzaRiquadro);
		
		portaDestra[0] = foglioAnimPorta.taglio(0, altezzaRiquadro, larghezzaRiquadro, altezzaRiquadro);
		portaDestra[1] = foglioAnimPorta.taglio(larghezzaRiquadro, altezzaRiquadro, larghezzaRiquadro, altezzaRiquadro);
		portaDestra[2] = foglioAnimPorta.taglio(larghezzaRiquadro*2, altezzaRiquadro, larghezzaRiquadro, altezzaRiquadro);
		portaDestra[3] = foglioAnimPorta.taglio(larghezzaRiquadro*3, altezzaRiquadro, larghezzaRiquadro, altezzaRiquadro);
		
		portaGiù[0] = foglioAnimPorta.taglio(0, altezzaRiquadro*2, larghezzaRiquadro, altezzaRiquadro);
		portaGiù[1] = foglioAnimPorta.taglio(larghezzaRiquadro, altezzaRiquadro*2, larghezzaRiquadro, altezzaRiquadro);
		portaGiù[2] = foglioAnimPorta.taglio(larghezzaRiquadro*2, altezzaRiquadro*2, larghezzaRiquadro, altezzaRiquadro);
		portaGiù[3] = foglioAnimPorta.taglio(larghezzaRiquadro*3, altezzaRiquadro*2, larghezzaRiquadro, altezzaRiquadro);
		
		portaSinistra[0] = foglioAnimPorta.taglio(0, altezzaRiquadro*3, larghezzaRiquadro, altezzaRiquadro);
		portaSinistra[1] = foglioAnimPorta.taglio(larghezzaRiquadro, altezzaRiquadro*3, larghezzaRiquadro, altezzaRiquadro);
		portaSinistra[2] = foglioAnimPorta.taglio(larghezzaRiquadro*2, altezzaRiquadro*3, larghezzaRiquadro, altezzaRiquadro);
		portaSinistra[3] = foglioAnimPorta.taglio(larghezzaRiquadro*3, altezzaRiquadro*3, larghezzaRiquadro, altezzaRiquadro);
		
		for(int i=0; i<5; i++)
		{
			vignetta[i] = foglioVignetta.taglio(larghezzaVignetta*i, 0, larghezzaVignetta, altezzaVignetta);
		}
		
		for(int i=5; i<10; i++)
		{
			vignetta[i] = foglioVignetta.taglio(larghezzaVignetta*(i-5), altezzaVignetta, larghezzaVignetta, altezzaVignetta);
		}
		
		for(int i=10; i<15; i++)
		{
			vignetta[i] = foglioVignetta.taglio(larghezzaVignetta*(i-10), altezzaVignetta*2, larghezzaVignetta, altezzaVignetta);
		}
		
		for(int i=15; i<20; i++)
		{
			vignetta[i] = foglioVignetta.taglio(larghezzaVignetta*4, altezzaVignetta*2, 
					larghezzaVignetta, altezzaVignetta);
		}
		
		vignettaSfondo = CaricatoreImmagini.caricaImmagine("/Textures/Vignetta Intro.png");
		
		mediKit = foglioItems.taglio(0, 0, larghezzaItem, altezzaItem);
		garza = foglioItems.taglio(larghezzaItem, 0, larghezzaItem, altezzaItem);
		ammo = foglioItems.taglio(0, altezzaItem, larghezzaItem, altezzaItem);
		pass = foglioItems.taglio(larghezzaItem, altezzaItem, larghezzaItem, altezzaItem);
		vittoria = CaricatoreImmagini.caricaImmagine("/Textures/itemVittoria.png");
		
		inventario = CaricatoreImmagini.caricaImmagine("/Textures/inventoryScreen.png");
		fontInventario = CaricatoreFonts.caricaFont("Risorse/Fonts/AbsoluteZeroItalic-R1oE.ttf",28);
		fontDanno = CaricatoreFonts.caricaFont("Risorse/Fonts/AbsoluteZeroItalic-R1oE.ttf",18);
		fontMenùConnessione = CaricatoreFonts.caricaFont("Risorse/Fonts/AbsoluteZeroItalic-R1oE.ttf",14);
		fontErrore = CaricatoreFonts.caricaFont("Risorse/Fonts/AbsoluteZeroItalic-R1oE.ttf",10);
		
		itemMediKit = new Item(Risorse.mediKit, "MediKit", 0, 300);
		itemGarza = new Item(Risorse.garza, "Garza", 1, 300);
		itemAmmo = new Item(Risorse.ammo, "Ammo", 2, 1000);
		itemPass = new Item(Risorse.pass, "Pass", 3, 300);
		itemVittoria = new Item(Risorse.vittoria, "Vittoria", 4, 300);
	}
}
