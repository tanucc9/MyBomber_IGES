package test.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
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
		bean.setEmail("gino@gino.it");
		bean.setNome("gino");
		bean.setCognome("pozzo");
		bean.setPassword("gino");
		bean.setTelefono("3923415443");
		bean.setStruttura("playk");
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
		assertEquals(bean.toString(), tester.doRetrieveByStruttura(bean.getStruttura()).toString());
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

	/*@Test
	public void testDoRetrieveAll() throws SQLException {
		ArrayList<GestoreBean>list=new ArrayList<GestoreBean>();
		GestoreBean g2=new GestoreBean();
		GestoreBean g3=new GestoreBean();
		g2.setEmail("olio@olio.it");
		g2.setNome("olio");
		g2.setCognome("rossi");
		g2.setPassword("olio");
		g2.setTelefono("3923415443");
		g2.setStruttura("playo");
		
		list.add(g2);
		list.add(bean);
		
		g3=new GestoreBean();
		g3.setEmail("bario@bario.it");
		g3.setNome("piero");
		g3.setCognome("rossi");
		g3.setPassword("bario");
		g3.setTelefono("3223415443");
		g3.setStruttura("playb");
		
		list.add(g3);
		
		System.out.println(tester.doRetrieveByKey(bean.getEmail()).getEmail());
		assertEquals(list, tester.doRetrieveAll());
	}*/

}
