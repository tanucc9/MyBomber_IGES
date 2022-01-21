package unita.model;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;

public class TestRecensioneDAO extends TestCase{
	
private RecensioneBean bean;
private RecensioneDAO tester = new RecensioneDAO();

@Before
public void setUp() throws Exception {
	super.setUp();
	bean = new RecensioneBean();
	bean.setRecensore("simone@simone.it");
	bean.setRecensito("pino@pino.it");
	bean.setEvento("Evento 333");
	bean.setRecensione(4);
	tester.doSave(bean);
}

@After
public void tearDown() throws Exception {
	super.tearDown();
	tester.doDelete(bean.getRecensore(), bean.getRecensito(), bean.getEvento());
}

@Test
public void testDoRetrieveByKey() {
	assertEquals(bean.toString(), tester.doRetrieveByKey(bean.getRecensore(), bean.getRecensito(), bean.getEvento()).toString());
}


@Test
public void testDoSave() {
	RecensioneBean inserito = tester.doRetrieveByKey(bean.getRecensore(), bean.getRecensito(), bean.getEvento());
	assertEquals(bean.toString(), inserito.toString());
}


@Test
public void testDoDelete() throws SQLException {
	tester.doDelete(bean.getRecensore(), bean.getRecensito(), bean.getEvento());
	assertEquals(null, tester.doRetrieveByKey(bean.getRecensore(), bean.getRecensito(), bean.getEvento()));
}


@Test
public void testDoUpdate() throws SQLException {
	bean.setDescrizione("bravo");
	tester.doUpdate(bean);
	RecensioneBean r2 = new RecensioneBean();
	r2 = tester.doRetrieveByKey(bean.getRecensore(), bean.getRecensito(), bean.getEvento());
	assertEquals(bean.toString(), r2.toString());
}


@Test
public void testDoRetrieveAll() throws SQLException {
	ArrayList<String> list = new ArrayList<String>();
	RecensioneBean r2 = new RecensioneBean();
	
	r2.setRecensore("pino@pino.it");
	r2.setRecensito("mario@mario.it");
	r2.setEvento("evento3");
	r2.setRecensione(2);
	r2.setDescrizione("scarso");
	list.add(r2.toString());
	
	r2.setRecensore("pino@pino.it");
	r2.setRecensito("simone@simone.it");
	r2.setEvento("evento3");
	r2.setRecensione(2);
	r2.setDescrizione("scarso");
	list.add(r2.toString());
	list.add(bean.toString());
	
	ArrayList<String> list2 = new ArrayList<String>();		
	ArrayList<RecensioneBean> recensioni = tester.doRetrieveAll();
	for(RecensioneBean r: recensioni)
		list2.add(r.toString());
	
	assertEquals(list, list2);
}


@Test
public void testDoRetrieveMedia() throws SQLException {
	float r1 = 2;
	String recensito = "simone@simone.it";
			
	float r2 = tester.doRetrieveMedia(recensito);
	
	assertEquals(r1, r2);
}


@Test
public void testDoRetrieveDaRecensire() throws SQLException {
	ArrayList<String> list = new ArrayList<String>();
	
	list.add("pino@pino.it");
	list.add("simone@simone.it");
	ArrayList<String> list2 = new ArrayList<String>();		
	list2 = tester.doRetrieveDaRecensire("mario@mario.it","evento3");
	
	assertEquals(list, list2);
}


@Test
public void testDoRetrieveRecensiti() throws SQLException {
	ArrayList<String> list = new ArrayList<String>();
	RecensioneBean r2 = new RecensioneBean();
	
	r2.setRecensore("pino@pino.it");
	r2.setRecensito("mario@mario.it");
	r2.setEvento("evento3");
	r2.setRecensione(2);
	r2.setDescrizione("scarso");
	list.add(r2.toString());
	
	r2.setRecensore("pino@pino.it");
	r2.setRecensito("simone@simone.it");
	r2.setEvento("evento3");
	r2.setRecensione(2);
	r2.setDescrizione("scarso");
	list.add(r2.toString());
	
	ArrayList<String> list2 = new ArrayList<String>();		
	ArrayList<RecensioneBean> recensioni = tester.doRetrieveRecensiti(r2.getRecensore(), r2.getEvento());
	for(RecensioneBean r: recensioni)
		list2.add(r.toString());
	
	assertEquals(list, list2);
}


}