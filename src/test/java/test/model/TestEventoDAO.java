package test.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.bean.EventoBean;
import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.dao.EventoDAO;
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;

public class TestEventoDAO extends TestCase{

	private EventoBean bean;
	private EventoDAO tester = new EventoDAO();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		bean =new EventoBean();
		bean.setNome("evento12");
		bean.setDescrizione("mitico evento");
		bean.setStruttura("playk");
		bean.setData(Date.valueOf("2023-01-03"));
		bean.setOra(2);
		bean.setGestore("gino@gino.it");
		bean.setOrganizzatore("simone@simone.it");
		bean.setStato("completato");
		bean.setValutazione(0);
		bean.setNumPartecipanti(10);
		tester.doSave(bean);
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		tester.doDelete(bean.getNome());
	}
	
	@Test
	public void testDoRetrieveByKey() {
		assertEquals(bean.toString(), tester.doRetrieveByKey(bean.getNome()).toString());
	}
	
	@Test
	public void testDoSave() {
		EventoBean inserito = tester.doRetrieveByKey(bean.getNome());
		assertEquals(bean.toString(), inserito.toString());
	}


	@Test
	public void testDoDelete() throws SQLException {
		tester.doDelete(bean.getNome());
		assertEquals(null, tester.doRetrieveByKey(bean.getNome()));
	}

	@Test
	public void testDoUpdate() throws SQLException {
		bean.setDescrizione("bellissimo");
		tester.doUpdate(bean);
		EventoBean mod = tester.doRetrieveByKey(bean.getNome());
		assertEquals(bean.toString(), mod.toString());
	}

	@Test
	public void testDoRetrieveAll() throws SQLException {
		
		ArrayList<String> list = new ArrayList<String>();
		
		EventoBean g2=new EventoBean();
		EventoBean g3=new EventoBean();
		EventoBean g4=new EventoBean();
		
		g2.setNome("Evento 333");
		g2.setDescrizione("Prova descrizione");
		g2.setStruttura("playk");
		g2.setData(Date.valueOf("2022-01-16"));
		g2.setOra(22);
		g2.setGestore("gino@gino.it");
		g2.setOrganizzatore("simone@simone.it");
		g2.setStato("richiesta");
		g2.setValutazione(0);
		g2.setNumPartecipanti(0);
		
		list.add(g2.toString());
		list.add(bean.toString());
		
		g4.setNome("evento2");
		g4.setDescrizione("grande evento");
		g4.setStruttura("playk");
		g4.setData(Date.valueOf("2022-01-03"));
		g4.setOra(2);
		g4.setGestore("gino@gino.it");
		g4.setOrganizzatore("simone@simone.it");
		g4.setStato("completato");
		g4.setValutazione(0);
		g4.setNumPartecipanti(10);
		
		g3.setNome("evento3");
		g3.setDescrizione("sdfghgfds");
		g3.setStruttura("playk");
		g3.setData(Date.valueOf("2022-01-15"));
		g3.setOra(1);
		g3.setGestore("gino@gino.it");
		g3.setOrganizzatore("simone@simone.it");
		g3.setStato("attivo");
		g3.setValutazione(0);
		g3.setNumPartecipanti(3);
		
		list.add(g4.toString());
		
		list.add(g3.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<EventoBean> eventi = tester.doRetrieveAll();
		for(EventoBean p: eventi)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}
	/*
	@Test
	public void testDoRetrieveEventiRecenti() throws SQLException {
		// nel database simone deve aver partecipato all'evento2
		ArrayList<String> list = new ArrayList<String>();
		
		EventoBean gi=new EventoBean();
		
		gi.setNome("evento2");
		gi.setDescrizione("grande evento");
		gi.setStruttura("playk");
		gi.setData(Date.valueOf("2022-01-03"));
		gi.setOra(2);
		gi.setGestore("gino@gino.it");
		gi.setOrganizzatore("simone@simone.it");
		gi.setStato("completato");
		gi.setValutazione(0);
		gi.setNumPartecipanti(10);
		
		list.add(gi.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<EventoBean> eventi = tester.doRetrieveEventiRecenti(gi.getOrganizzatore());
		for(EventoBean p: eventi)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}
	*/
	
	@Test
	public void testDoRetrieveEventiAttivi() throws SQLException {
		// nel database simone deve aver partecipato all'evento2
		ArrayList<String> list = new ArrayList<String>();
		
		EventoBean g3=new EventoBean();
		g3.setNome("evento3");
		g3.setDescrizione("sdfghgfds");
		g3.setStruttura("playk");
		g3.setData(Date.valueOf("2022-01-15"));
		g3.setOra(1);
		g3.setGestore("gino@gino.it");
		g3.setOrganizzatore("simone@simone.it");
		g3.setStato("attivo");
		g3.setValutazione(0);
		g3.setNumPartecipanti(3);
		
		
		list.add(g3.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<EventoBean> eventi = tester.doRetrieveEventiAttivi();
		for(EventoBean p: eventi)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}
	
	@Test
	public void testDoRetrieveRichieste() throws SQLException {
		// nel database simone deve aver partecipato all'evento2
		ArrayList<String> list = new ArrayList<String>();
		
		EventoBean g2=new EventoBean();
		g2.setNome("Evento 333");
		g2.setDescrizione("Prova descrizione");
		g2.setStruttura("playk");
		g2.setData(Date.valueOf("2022-01-16"));
		g2.setOra(22);
		g2.setGestore("gino@gino.it");
		g2.setOrganizzatore("simone@simone.it");
		g2.setStato("richiesta");
		g2.setValutazione(0);
		g2.setNumPartecipanti(0);
		
		
		list.add(g2.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<EventoBean> eventi = tester.doRetrieveRichieste(g2.getGestore());
		for(EventoBean p: eventi)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}
	
	@Test
	public void testDoRetrieveEventiGestore() throws SQLException {
		// nel database simone deve aver partecipato all'evento2
    ArrayList<String> list = new ArrayList<String>();
		
		EventoBean g3=new EventoBean();
		EventoBean g4=new EventoBean();
		
		list.add(bean.toString());
		
		g4.setNome("evento2");
		g4.setDescrizione("grande evento");
		g4.setStruttura("playk");
		g4.setData(Date.valueOf("2022-01-03"));
		g4.setOra(2);
		g4.setGestore("gino@gino.it");
		g4.setOrganizzatore("simone@simone.it");
		g4.setStato("completato");
		g4.setValutazione(0);
		g4.setNumPartecipanti(10);
		
		g3.setNome("evento3");
		g3.setDescrizione("sdfghgfds");
		g3.setStruttura("playk");
		g3.setData(Date.valueOf("2022-01-15"));
		g3.setOra(1);
		g3.setGestore("gino@gino.it");
		g3.setOrganizzatore("simone@simone.it");
		g3.setStato("attivo");
		g3.setValutazione(0);
		g3.setNumPartecipanti(3);
		
		list.add(g3.toString());
		
		list.add(g4.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<EventoBean> eventi = tester.doRetrieveEventiGestore(g3.getGestore());
		for(EventoBean p: eventi)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}
	
	@Test
	public void testDoRetrieveCronologia() throws SQLException {
		
    ArrayList<String> list = new ArrayList<String>();
		
		EventoBean g4=new EventoBean();
		
		g4.setNome("evento2");
		g4.setDescrizione("grande evento");
		g4.setStruttura("playk");
		g4.setData(Date.valueOf("2022-01-03"));
		g4.setOra(2);
		g4.setGestore("gino@gino.it");
		g4.setOrganizzatore("simone@simone.it");
		g4.setStato("completato");
		g4.setValutazione(0);
		g4.setNumPartecipanti(10);
		
		list.add(bean.toString());
		list.add(g4.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<EventoBean> eventi = tester.doRetrieveCronologia(g4.getOrganizzatore());
		for(EventoBean p: eventi)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}
	
	@Test
	public void testDoRetrieveEventi() throws SQLException {
	//piero deve esistere e non partecipare a evento3	
    ArrayList<String> list = new ArrayList<String>();
		
		EventoBean g3=new EventoBean();
		
		g3.setNome("evento3");
		g3.setDescrizione("sdfghgfds");
		g3.setStruttura("playk");
		g3.setData(Date.valueOf("2022-01-15"));
		g3.setOra(1);
		g3.setGestore("gino@gino.it");
		g3.setOrganizzatore("simone@simone.it");
		g3.setStato("attivo");
		g3.setValutazione(0);
		g3.setNumPartecipanti(3);
		list.add(g3.toString());
		ArrayList<String> list2 = new ArrayList<String>();	
		ArrayList<EventoBean> eventi = tester.doRetrieveEventi("piero@piero.it");
		for(EventoBean p: eventi)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}
	/*
	@Test
	public void testDoRetrieveEventiRecentiGiocatore() throws SQLException {
	//simone deve aver partecipato solo a gli eventi già accettati evento3 e evento2	
    ArrayList<String> list = new ArrayList<String>();
		
		EventoBean g3=new EventoBean();
		EventoBean g4=new EventoBean();
		
		g3.setNome("evento3");
		g3.setDescrizione("sdfghgfds");
		g3.setStruttura("playk");
		g3.setData(Date.valueOf("2022-01-15"));
		g3.setOra(1);
		g3.setGestore("gino@gino.it");
		g3.setOrganizzatore("simone@simone.it");
		g3.setStato("attivo");
		g3.setValutazione(0);
		g3.setNumPartecipanti(3);
		
		list.add(g3.toString());
		
		g4.setNome("evento2");
		g4.setDescrizione("grande evento");
		g4.setStruttura("playk");
		g4.setData(Date.valueOf("2022-01-03"));
		g4.setOra(2);
		g4.setGestore("gino@gino.it");
		g4.setOrganizzatore("simone@simone.it");
		g4.setStato("completato");
		g4.setValutazione(0);
		g4.setNumPartecipanti(10);
		
		list.add(g4.toString());
		ArrayList<String> list2 = new ArrayList<String>();	
		ArrayList<EventoBean> eventi = tester.doRetrieveEventi(g4.getOrganizzatore());
		for(EventoBean p: eventi)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}
   */
}  
//do retrive conologia prima controllava lo stato concluso 
