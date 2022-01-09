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
		bean.setUsername("simone");
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
		assertEquals(bean, tester.doRetrieveByKey(bean.getEmail()));
	}
	
	@Test
	public void testDoRetrieveByUsername() {
		assertEquals(bean, tester.doRetrieveByUsername(bean.getUsername()));
	}
	
	@Test
	public void testDoSave() {
		GiocatoreBean inserito = tester.doRetrieveByKey(bean.getEmail());
		assertEquals(bean, inserito);
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
		assertEquals(bean, mod);
	}

	/*@Test
	public void testDoRetrieveAll() throws SQLException {
		ArrayList<GiocatoreBean>list=new ArrayList<GiocatoreBean>();
		GiocatoreBean g2=new GiocatoreBean();
		bean.setUsername("pino");
		bean.setEmail("pino1@pino.it");
		bean.setNome("Pino");
		bean.setCognome("Inglese");
		bean.setPassword("pino");
		bean.setTelefono("3665423187");
		bean.setDataNascita(Date.valueOf("2000-09-09"));
		bean.setNazioneResidenza("Italia");
		bean.setProvinciaResidenza("Napoli");
		bean.setCittaResidenza("Napoli");
		bean.setCapResidenza("80000");
		bean.setValutazione(0);
		list.add(g2);
		list.add(bean);
		
		g2=new GiocatoreBean();
		bean.setUsername("mario");
		bean.setEmail("mario@mario.it.it");
		bean.setNome("mario");
		bean.setCognome("calabrese");
		bean.setPassword("mario");
		bean.setTelefono("3452167543");
		bean.setDataNascita(Date.valueOf("2000-03-03"));
		bean.setNazioneResidenza("Italia");
		bean.setProvinciaResidenza("Avellino");
		bean.setCittaResidenza("Avellino");
		bean.setCapResidenza("80076");
		bean.setValutazione(0);
		list.add(g2);
		
		System.out.println(tester.doRetrieveByKey(bean.getEmail()).getEmail());
		assertEquals(list, tester.doRetrieveAll());
	}*/

}
