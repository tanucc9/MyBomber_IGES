package unita.model;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;


public class TestEventoBean{

	 EventoBean bean;
	
	@Before
	public void setUp() throws Exception {
		bean =new EventoBean();
		bean.setNome("evento124");
		bean.setDescrizione("mitico evento");
		bean.setStruttura("playk");
		bean.setData(Date.valueOf("2023-01-03"));
		bean.setOra(2);
		bean.setGestore("gino@gino.it");
		bean.setOrganizzatore("simone@simone.it");
		bean.setStato("completato");
		bean.setValutazione(0);
		bean.setNumPartecipanti(10);
	}
	
	
	
	@Test
	public void rimouoviG() {
		bean.rimuoviG();
		assertEquals(bean.getNumPartecipanti(),9);
	}
	
	@Test
	public void isFinished() {
		bean.setStato("richiesta");
		boolean finito=bean.isFinished();
		assert(!finito);
	}
	@Test
	public void isFinishedSi() {
		bean.setStato("completato");
		bean.setData(Date.valueOf("2020-01-03"));
		boolean finito=bean.isFinished();
		assert(finito);
	}
	@Test
	public void isFinishedNoData() {
		bean.setStato("completato");
		bean.setData(Date.valueOf("2023-01-03"));
		boolean finito=bean.isFinished();
		assert(!finito);
	}
	@Test
	public void isFinishedNoCompletato() {
		bean.setStato("richiesta");
		bean.setData(Date.valueOf("2020-01-03"));
		boolean finito=bean.isFinished();
		assert(!finito);
	}
	@Test
	public void precedenza() throws SQLException {
		
		EventoBean b2 =new EventoBean();
		b2.setNome("evento12");
		b2.setDescrizione("mitico evento");
		b2.setStruttura("playk");
		b2.setData(Date.valueOf("2021-01-03"));
		b2.setOra(2);
		b2.setGestore("gino@gino.it");
		b2.setOrganizzatore("simone@simone.it");
		b2.setStato("completato");
		b2.setValutazione(0);
		b2.setNumPartecipanti(10);
		boolean p=b2.precedenza(bean);
		assert(p);
	}
	@Test
	public void noPrecedenza() throws SQLException {
		
		EventoBean b2 =new EventoBean();
		b2.setNome("evento12");
		b2.setDescrizione("mitico evento");
		b2.setStruttura("playk");
		b2.setData(Date.valueOf("2026-01-03"));
		b2.setOra(2);
		b2.setGestore("gino@gino.it");
		b2.setOrganizzatore("simone@simone.it");
		b2.setStato("completato");
		b2.setValutazione(0);
		b2.setNumPartecipanti(10);
		boolean p=b2.precedenza(bean);
		assert(!p);
	}
	@Test
    public void ordinaPerData() throws SQLException {
        ArrayList<EventoBean> lista= new ArrayList<EventoBean>();
        ArrayList<String> lr= new ArrayList<String>();
        EventoBean b2 =new EventoBean();
        b2.setNome("evento12");
        b2.setDescrizione("mitico evento");
        b2.setStruttura("playk");
        b2.setData(Date.valueOf("2026-01-03"));
        b2.setOra(2);
        b2.setGestore("gino@gino.it");
        b2.setOrganizzatore("simone@simone.it");
        b2.setStato("completato");
        b2.setValutazione(0);
        b2.setNumPartecipanti(10);
        lista.add(bean);
        lr.add(bean.toString());
        lista.add(b2);
        lr.add(b2.toString());
        ArrayList<EventoBean>ordinati=EventoBean.ordinaPerData(lista);
        ArrayList<String> or=new ArrayList<String>();
        for(EventoBean e: ordinati)
        {
            or.add(e.toString());
        }
        assertEquals(or,lr);
    }
	
	@Test
    public void ordinaPerDataR() throws SQLException {
        ArrayList<EventoBean> lista= new ArrayList<EventoBean>();
        ArrayList<String> lr= new ArrayList<String>();
        EventoBean b2 =new EventoBean();
        b2.setNome("evento12");
        b2.setDescrizione("mitico evento");
        b2.setStruttura("playk");
        b2.setData(Date.valueOf("2026-01-03"));
        b2.setOra(2);
        b2.setGestore("gino@gino.it");
        b2.setOrganizzatore("simone@simone.it");
        b2.setStato("completato");
        b2.setValutazione(0);
        b2.setNumPartecipanti(10);
        lista.add(bean);
        lr.add(b2.toString());
        lista.add(b2);
        lr.add(bean.toString());
        ArrayList<EventoBean>ordinati=EventoBean.ordinaPerDataR(lista);
        ArrayList<String> or=new ArrayList<String>();
        for(EventoBean e: ordinati)
        {
            or.add(e.toString());
        }
        assertEquals(or,lr);
    }
}  

