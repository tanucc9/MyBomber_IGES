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
		bean.setNome("playh");
		bean.setIndirizzo("via giorgio 21");
		bean.setNazione("italia");
		bean.setCitta("napoli");
		bean.setCap("80098");
		bean.setProvincia("napoli");
		bean.setTelefono("3122122148");
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
		bean.setTelefono("3122122112");
		tester.doUpdate(bean);
	}

	@Test
	public void testDoRetrieveAll() throws SQLException {
		
		ArrayList<String> list = new ArrayList<String>();
		StrutturaBean g2=new StrutturaBean();
		StrutturaBean g3=new StrutturaBean();
		StrutturaBean g4=new StrutturaBean();
		g2.setNome("playb");
		g2.setIndirizzo("via piero 21");
		g2.setNazione("italia");
		g2.setCitta("napoli");
		g2.setCap("80098");
		g2.setProvincia("napoli");
		g2.setTelefono("1233211221");
	
		list.add(g2.toString());
		list.add(bean.toString());
		
		g3.setNome("playk");
		g3.setIndirizzo("via andrea 21");
		g3.setNazione("italia");
		g3.setCitta("napoli");
		g3.setCap("80098");
		g3.setProvincia("napoli");
		g3.setTelefono("3122122143");
		
		
		list.add(g3.toString());
		
		g4.setNome("playo");
		g4.setIndirizzo("via giacomo 21");
		g4.setNazione("italia");
		g4.setCitta("napoli");
		g4.setCap("80098");
		g4.setProvincia("napoli");
		g4.setTelefono("3122122112");
		
		list.add(g4.toString());
		
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<StrutturaBean> strutture = tester.doRetrieveAll();
		for(StrutturaBean p: strutture)
			list2.add(p.toString());
		
		assertEquals(list, list2);
	}
	@Test
	public void testDoRetrieveAllNomi() throws SQLException {
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("playb");
		list.add("playh");
		list.add("playk");
		list.add("playo");
		
		
		ArrayList<String> list2 = new ArrayList<String>();		
		list2= tester.doRetrieveAllNomi();
		assertEquals(list, list2);
	}

}
