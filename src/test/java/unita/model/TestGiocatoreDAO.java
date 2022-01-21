package unita.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

public class TestGiocatoreDAO extends TestCase{

	private GiocatoreBean bean;
	private GiocatoreDAO tester = new GiocatoreDAO();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		bean =new GiocatoreBean();
		bean.setUsername("simone45");
		bean.setEmail("simon@simon.it");
		bean.setNome("Simone");
		bean.setCognome("Graziano");
		bean.setPassword("simone");
		bean.setTelefono("3324561273");
		bean.setDataNascita(Date.valueOf("2000-05-09"));
		bean.setNazioneResidenza("Italia");
		bean.setProvinciaResidenza("Napoli");
		bean.setCittaResidenza("Napoli");
		bean.setCapResidenza("80000");
		bean.setValutazione(0);
		tester.doSave(bean);
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		tester.doDelete(bean.getEmail());
	}
	
	@Test
	public void testDoRetrieveByKey() {
		assertEquals(bean.toString(), tester.doRetrieveByKey(bean.getEmail()).toString());
	}
	
	@Test
	public void testDoRetrieveByUsername() {
		assertEquals(bean.toString(), tester.doRetrieveByUsername(bean.getUsername()).toString());
	}
	
	@Test
	public void testDoSave() {
		GiocatoreBean inserito = tester.doRetrieveByKey(bean.getEmail());
		assertEquals(bean.toString(), inserito.toString());
	}


	@Test
	public void testDoDelete() throws SQLException {
		tester.doDelete(bean.getEmail());
		assertEquals(null, tester.doRetrieveByKey(bean.getEmail()));
	}

	@Test
	public void testDoUpdate() throws SQLException {
		bean.setNome("Pasquale");
		tester.doUpdate(bean);
		GiocatoreBean mod = tester.doRetrieveByKey(bean.getEmail());
		assertEquals(bean.toString(), mod.toString());
	}

	@Test
	public void testDoRetrieveAll() throws SQLException {
		ArrayList<String>list=new ArrayList<String>();
		GiocatoreBean g2 = new GiocatoreBean();
		GiocatoreBean g3 = new GiocatoreBean();
		GiocatoreBean g4 = new GiocatoreBean();
		GiocatoreBean g5 = new GiocatoreBean();
		g2.setUsername("gio");
		g2.setEmail("gio4@email.it");
		g2.setNome("Giovanni");
		g2.setCognome("Falco");
		g2.setPassword("Gio");
		g2.setTelefono("3334562167");
		g2.setDataNascita(Date.valueOf("2001-11-16"));
		g2.setNazioneResidenza("Italia");
		g2.setProvinciaResidenza("Caserta");
		g2.setCittaResidenza("Caserta");
		g2.setCapResidenza("89976");
		g2.setValutazione(0);
		list.add(g2.toString());
		
		g3.setUsername("mario");
		g3.setEmail("mario@mario.it");
		g3.setNome("Mario");
		g3.setCognome("Calabrese");
		g3.setPassword("mario");
		g3.setTelefono("3452167543");
		g3.setDataNascita(Date.valueOf("2000-03-03"));
		g3.setNazioneResidenza("Italia");
		g3.setProvinciaResidenza("Avellino");
		g3.setCittaResidenza("Avellino");
		g3.setCapResidenza("80076");
		g3.setValutazione(0);
		list.add(g3.toString());
		
		g4.setUsername("pino");
		g4.setEmail("pino@pino.it");
		g4.setNome("Pino");
		g4.setCognome("Inglese");
		g4.setPassword("pino");
		g4.setTelefono("3665423187");
		g4.setDataNascita(Date.valueOf("2000-09-09"));
		g4.setNazioneResidenza("Italia");
		g4.setProvinciaResidenza("Napoli");
		g4.setCittaResidenza("Napoli");
		g4.setCapResidenza("80000");
		g4.setValutazione(0);
		list.add(g4.toString());
		list.add(tester.doRetrieveByKey(bean.getEmail()).toString());
		
		g5.setUsername("simone");
		g5.setEmail("simone@simone.it");
		g5.setNome("Simone");
		g5.setCognome("Graziano");
		g5.setPassword("simone");
		g5.setTelefono("3324156789");
		g5.setDataNascita(Date.valueOf("1999-08-12"));
		g5.setNazioneResidenza("Italia");
		g5.setProvinciaResidenza("Milano");
		g5.setCittaResidenza("Milano");
		g5.setCapResidenza("78654");
		g5.setValutazione(0);
		list.add(g5.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<GiocatoreBean> giocatori = tester.doRetrieveAll();
		for(GiocatoreBean g: giocatori)
			list2.add(g.toString());
		
		assertEquals(list, list2);
	}

}
