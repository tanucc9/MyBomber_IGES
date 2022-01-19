package unità.model;

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
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;

public class TestGestoreDAO extends TestCase{

	private GestoreBean bean;
	private GestoreDAO tester = new GestoreDAO();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		bean =new GestoreBean();
		bean.setEmail("zgaetano@olio.it");
		bean.setNome("gaetano");
		bean.setCognome("rossi");
		bean.setPassword("gaetano");
		bean.setTelefono("3923415443");
		bean.setStruttura("playo");
		
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
	public void testDoRetrieveByStruttura() {
		GestoreBean gi=new GestoreBean();
		gi.setEmail("gino@gino.it");
		gi.setNome("gino");
		gi.setCognome("pozzo");
		gi.setPassword("gino");
		gi.setTelefono("3923415443");
		gi.setStruttura("playk");
		assertEquals(gi.toString(), tester.doRetrieveByStruttura(gi.getStruttura()).toString());
	}
	
	@Test
	public void testDoSave() {
		GestoreBean inserito = tester.doRetrieveByKey(bean.getEmail());
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
		GestoreBean mod = tester.doRetrieveByKey(bean.getEmail());
		assertEquals(bean.toString(), mod.toString());
	}

	@Test
	public void testDoRetrieveAll() throws SQLException {
		
		ArrayList<String> list = new ArrayList<String>();
		GestoreBean g2=new GestoreBean();
		GestoreBean g3=new GestoreBean();
		GestoreBean g4=new GestoreBean();
		g2.setEmail("gino@gino.it");
		g2.setNome("gino");
		g2.setCognome("pozzo");
		g2.setPassword("gino");
		g2.setTelefono("3923415443");
		g2.setStruttura("playk");
		
		g3.setEmail("bario@bario.it");
		g3.setNome("piero");
		g3.setCognome("rossi");
		g3.setPassword("bario");
		g3.setTelefono("3223415443");
		g3.setStruttura("playb");
		
		list.add(g3.toString());
		list.add(g2.toString());
		
		g4.setEmail("olio@olio.it");
		g4.setNome("olio");
		g4.setCognome("rossi");
		g4.setPassword("olio");
		g4.setTelefono("3923415443");
		g4.setStruttura("playo");
		
		list.add(g4.toString());
		
		list.add(bean.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<GestoreBean> gestori = tester.doRetrieveAll();
		for(GestoreBean p: gestori)
			list2.add(p.toString());
		
		assertEquals(list, list2);
		
	}

}
