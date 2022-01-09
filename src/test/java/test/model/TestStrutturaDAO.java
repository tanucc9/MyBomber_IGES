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
import model.bean.StrutturaBean;
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;
import model.dao.StrutturaDAO;

public class TestStrutturaDAO extends TestCase{

	private StrutturaBean bean;
	private StrutturaDAO tester = new StrutturaDAO();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		bean =new StrutturaBean();
		bean.setNome("playo");
		bean.setIndirizzo("via giacomo 21");
		bean.setNazione("italia");
		bean.setCitta("napoli");
		bean.setCap("80098");
		bean.setProvincia("napoli");
		bean.setTelefono("3122122112");
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
		StrutturaBean inserito = tester.doRetrieveByKey(bean.getNome());
		assertEquals(bean.toString(), inserito.toString());
	}


	@Test
	public void testDoDelete() throws SQLException {
		tester.doDelete(bean.getNome());
		assertEquals(null, tester.doRetrieveByKey(bean.getNome()));
	}

	@Test
	public void testDoUpdate() throws SQLException {
		bean.setTelefono("3211233221");
		tester.doUpdate(bean);
		StrutturaBean mod = tester.doRetrieveByKey(bean.getNome());
		assertEquals(bean.toString(), mod.toString());
	}

	/*@Test
	public void testDoRetrieveAll() throws SQLException {
		ArrayList<StrutturaBean>list=new ArrayList<StrutturaBean>();
		StrutturaBean g2=new StrutturaBean();
		StrutturaBean g3=new StrutturaBean();
		bean.setNome("playb");
		bean.setIndirizzo("via piero 21");
		bean.setNazione("italia");
		bean.setCitta("napoli");
		bean.setCap("80098");
		bean.setProvincia("napoli");
		bean.setTelefono("1233211221");
		
		list.add(g2);
		list.add(bean);
		
		bean.setNome("playk");
		bean.setIndirizzo("via andrea 21");
		bean.setNazione("italia");
		bean.setCitta("napoli");
		bean.setCap("80098");
		bean.setProvincia("napoli");
		bean.setTelefono("3122122143");
		
		list.add(g3);
		
		System.out.println(tester.doRetrieveByKey(bean.getEmail()).getEmail());
		assertEquals(list, tester.doRetrieveAll());
	}*/

}
