package test.model;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.bean.PartecipazioneBean;
import model.dao.PartecipazioneDAO;


public class TestPartecipazioneDAO extends TestCase{

	private PartecipazioneBean bean;
	private PartecipazioneDAO tester = new PartecipazioneDAO();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		bean = new PartecipazioneBean();
		bean.setEvento("evento2");
		bean.setUtente("gio4@email.it");
		tester.doSave(bean);
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		tester.doDelete(bean.getUtente(), bean.getEvento());
	}
	
	@Test
	public void testDoRetrieveByKey() {
		assertEquals(bean.toString(), tester.doRetrieveByKey(bean.getUtente(), bean.getEvento()).toString());
	}
	
	
	@Test
	public void testDoSave() {
		PartecipazioneBean inserito = tester.doRetrieveByKey(bean.getUtente(), bean.getEvento());
		assertEquals(bean.toString(), inserito.toString());
	}


	@Test
	public void testDoDelete() throws SQLException {
		tester.doDelete(bean.getUtente(), bean.getEvento());
		assertEquals(null, tester.doRetrieveByKey(bean.getUtente(), bean.getEvento()));
	}
	

	/*@Test
	public void testDoRetrieveAll() throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		PartecipazioneBean p2 = new PartecipazioneBean();
		PartecipazioneBean p3 = new PartecipazioneBean();
		p2.setEvento("simoneEvento");
		p2.setUtente("simone@simone.it");
		
		list.add(p2.toString());
		list.add(bean.toString());
		
		p3.setEvento("simoneEvento");
		p3.setUtente("pasquale@pasquale.it");
		list.add(g3.toString());
		
		ArrayList<String> list2 = new ArrayList<String>();		
		ArrayList<PartecipazioneBean> partecipazioni = tester.doRetrieveAll();
		for(PartecipazioneBean p: partecipazioni)
			list2.add(p.toString());
		
		assertEquals(list, list2);
	}*/
	
	/*@Test
	public void testDoRetrieveByEvento() throws SQLException {
		ArrayList<PartecipazioneBean>list=new ArrayList<PartecipazioneBean>();
		PartecipazioneBean p2 = new PartecipazioneBean();
		PartecipazioneBean p3 = new PartecipazioneBean();
		p2.setEvento("simoneEvento");
		p2.setUtente("simone@simone.it");
		
		list.add(p2);
			
		p3.setEvento("simoneEvento");
		p3.setUtente("pasquale@pasquale.it");
		list.add(g3);
		
		assertEquals(list, tester.doRetrieveByEvento("simoneEvento"));
	}*/
	
	/*@Test
	public void testDoGetPartecipanti() throws SQLException {
		ArrayList<PartecipazioneBean>list=new ArrayList<PartecipazioneBean>();
		PartecipazioneBean p2 = new PartecipazioneBean();
		PartecipazioneBean p3 = new PartecipazioneBean();
		p2.setEvento("simoneEvento");
		p2.setUtente("simone@simone.it");
		
		list.add(p2);
			
		p3.setEvento("simoneEvento");
		p3.setUtente("pasquale@pasquale.it");
		list.add(g3);
		
		assertEquals(list, tester.doRetrieveByEvento("simoneEvento"));
	}*/

}

