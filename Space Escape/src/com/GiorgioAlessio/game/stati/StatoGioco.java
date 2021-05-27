package com.GiorgioAlessio.game.stati;

import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.input.GestoreTasti;
import com.GiorgioAlessio.game.mondi.Mondo;

/*Definizione della classe "StatoGioco", sottoclasse di "Stato", al cui interno vengono
 definit i metodi "aggiornamento" e "renderizzazione".*/
public class StatoGioco extends Stato
{
	private Mondo mondo;
	private BarraDiStato barraDiStato;
	
	private long momentoAvvio,momentoChiusura;
	
	public StatoGioco(Maneggiatore maneggiatore)
	{
		super(maneggiatore);
		mondo = new Mondo(maneggiatore, "Risorse/Mondi/Mondo1.txt");
		
		barraDiStato = new BarraDiStato(maneggiatore, 0, 600-64, 600, 64);
		
		maneggiatore.setMondo(mondo);
		
		momentoAvvio = System.currentTimeMillis();
	}
	
	@Override
	public void aggiornamento()
	{
		mondo.aggiornamento();
		barraDiStato.aggiornamento();
		
		if(GestoreTasti.pausa)
		{
			Stato.setStato(new StatoPausa(maneggiatore));
		}
	}
	
	@Override
	public void renderizzazione(Graphics disegnatore)
	{
		mondo.renderizzazione(disegnatore);
		barraDiStato.renderizzazione(disegnatore);
	}

	public long getMomentoAvvio()
	{
		return momentoAvvio;
	}

	public void setMomentoAvvio(long momentoAvvio)
	{
		this.momentoAvvio = momentoAvvio;
	}

	public long getMomentoChiusura()
	{
		return momentoChiusura;
	}

	public void setMomentoChiusura(long momentoChiusura)
	{
		this.momentoChiusura = momentoChiusura;
	}
	
}
