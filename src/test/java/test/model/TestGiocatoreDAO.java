package test.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.bean.GiocatoreBean;
import model.dao.GiocatoreDAO;

public class TestGiocatoreDAO extends TestCase{

	private GiocatoreBean bean;
	private GiocatoreDAO tester = new GiocatoreDAO();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		bean =new GiocatoreBean();
		bean.setUsername("simone45");
		bean.setEmail("simone@simone.it");
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

	/*@Test
	public void testDoRetrieveAll() throws SQLException {
		ArrayList<GiocatoreBean>list=new ArrayList<GiocatoreBean>();
		GiocatoreBean g2=new GiocatoreBean();
		g2.setUsername("pino");
		g2.setEmail("pino1@pino.it");
		g2.setNome("Pino");
		g2.setCognome("Inglese");
		g2.setPassword("pino");
		g2.setTelefono("3665423187");
		g2.setDataNascita(Date.valueOf("2000-09-09"));
		g2.setNazioneResidenza("Italia");
		g2.setProvinciaResidenza("Napoli");
		g2.setCittaResidenza("Napoli");
		g2.setCapResidenza("80000");
		g2.setValutazione(0);
		list.add(g2);
		list.add(bean);
		
		g2=new GiocatoreBean();
		g2.setUsername("mario");
		g2.setEmail("mario@mario.it.it");
		g2.setNome("mario");
		g2.setCognome("calabrese");
		g2.setPassword("mario");
		g2.setTelefono("3452167543");
		g2.setDataNascita(Date.valueOf("2000-03-03"));
		g2.setNazioneResidenza("Italia");
		g2.setProvinciaResidenza("Avellino");
		g2.setCittaResidenza("Avellino");
		g2.setCapResidenza("80076");
		g2.setValutazione(0);
		g2.add(g2);
		
		assertEquals(list, tester.doRetrieveAll());
	}*/

}
