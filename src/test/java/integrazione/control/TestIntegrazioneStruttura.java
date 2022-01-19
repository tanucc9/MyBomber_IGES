package integrazione.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import control.StrutturaServlet;
import model.bean.StrutturaBean;
import model.dao.StrutturaDAO;

public class TestIntegrazioneStruttura {
	@Mock
	private HttpServletRequest req;
	
	@Mock
	private HttpServletResponse res;
	
	@Mock
	private ServletContext ctx;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private RequestDispatcher rd;
	
	
	private StrutturaServlet servlet;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		servlet= new StrutturaServlet();
		when(req.getSession()).thenReturn(session);

	}
	
	@Test
	public void cercaCronologiaGestore() throws ServletException, IOException, SQLException {
		StrutturaBean bean =new StrutturaBean();
		bean.setNome("playh");
		bean.setIndirizzo("via giorgio 21");
		bean.setNazione("italia");
		bean.setCitta("napoli");
		bean.setCap("80098");
		bean.setProvincia("napoli");
		bean.setTelefono("3122122148");
		
		ArrayList<StrutturaBean> list = new ArrayList<StrutturaBean>();
		ArrayList<String> liststr = new ArrayList<String>();
		
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
	
		list.add(g2);
		liststr.add(g2.toString());
		list.add(bean);
		liststr.add(bean.toString());
		
		g3.setNome("playk");
		g3.setIndirizzo("via andrea 21");
		g3.setNazione("italia");
		g3.setCitta("napoli");
		g3.setCap("80098");
		g3.setProvincia("napoli");
		g3.setTelefono("3122122143");
		
		liststr.add(g3.toString());
		list.add(g3);
		
		g4.setNome("playo");
		g4.setIndirizzo("via giacomo 21");
		g4.setNazione("italia");
		g4.setCitta("napoli");
		g4.setCap("80098");
		g4.setProvincia("napoli");
		g4.setTelefono("3122122112");
		
		list.add(g4);
		liststr.add(g4.toString());
		//mariello
		when(req.getAttribute("strutture")).thenReturn(list);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./CreaEvento.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
		ArrayList<StrutturaBean> cev= new ArrayList<StrutturaBean>();
		ArrayList<String> stcev= new ArrayList<String>();
		cev=(ArrayList<StrutturaBean>)req.getAttribute("strutture");
		for(StrutturaBean s : cev)
		{
			stcev.add(s.toString());
		}
		
		assertEquals(stcev,liststr);
		
	}
	
	@Test
	public void testing() throws ServletException, IOException, SQLException {
        servlet.sdao=new StrutturaDAO();
		when(req.getRequestDispatcher(res.encodeRedirectURL("./CreaEvento.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
			
	}
}
