package com.GiorgioAlessio.game.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.GiorgioAlessio.game.Maneggiatore;

public abstract class OggettoUi 
{
	protected Maneggiatore maneggiatore;
	protected float x,y;
	protected int larghezza,altezza;
	protected Rectangle bordo;
	protected boolean sovrapposizione = false;
	protected String nome = "";
	protected boolean scrivibile = false;
	
	public OggettoUi(Maneggiatore maneggiatore, float x, float y, int larghezza, int altezza)
	{
		this.maneggiatore = maneggiatore;
		this.x = x;
		this.y = y;
		this.larghezza = larghezza;
		this.altezza = altezza;
		bordo = new Rectangle((int) x, (int) y, larghezza,altezza);
	}

	public abstract void aggiornamento();
	
	public abstract void renderizzazione(Graphics disegnatore);
	
	public abstract void onClick();
	
	public void movimentoMouse(MouseEvent e)
	{
		if(bordo.contains(e.getX(), e.getY()))
		{
			sovrapposizione = true;	
		}
		else
		{
			sovrapposizione = false;
		}	
	}
	
	public void rilascioMouse(MouseEvent e)
	{
		if(sovrapposizione)
		{
			onClick();
		}
	}
	
	//getter e setters
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getLarghezza() {
		return larghezza;
	}

	public void setLarghezza(int larghezza) {
		this.larghezza = larghezza;
	}

	public int getAltezza() {
		return altezza;
	}

	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}

	public boolean isSovrapposizione() {
		return sovrapposizione;
	}

	public void setSovrapposizione(boolean sovrapposizione) {
		this.sovrapposizione = sovrapposizione;
	}

	public boolean isScrivibile() {
		return scrivibile;
	}

	public void setScrivibile(boolean scrivibile) {
		this.scrivibile = scrivibile;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
