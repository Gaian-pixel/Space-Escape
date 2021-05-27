package com.GiorgioAlessio.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.GiorgioAlessio.game.ui.GestoreUi;

public class GestoreMouse implements MouseListener, MouseMotionListener
{
	private boolean pressioneSinistra,pressioneDestra;
	private int mouseX,mouseY;
	private GestoreUi gestoreUi;
	
	public GestoreMouse()
	{
		
	}

	//Setter
	public void setGestoreUi(GestoreUi gestoreUi)
	{
		this.gestoreUi = gestoreUi;
	}
	
	//Getter
	public boolean isPressioneSinistra()
	{
		return pressioneSinistra;
	}

	public boolean isPressioneDestra()
	{
		return pressioneDestra;
	}
	
	public int getMouseX()
	{
		return mouseX;
	}

	public int getMouseY()
	{
		return mouseY;
	}

	//Metodi Di Suoperclasse
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			pressioneSinistra = true;
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			pressioneDestra = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			pressioneSinistra = false;
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			pressioneDestra = false;
		}
		
		if(gestoreUi != null)
		{
			gestoreUi.rilascioMouse(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(gestoreUi != null)
		{
			gestoreUi.movimentoMouse(e);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

}
