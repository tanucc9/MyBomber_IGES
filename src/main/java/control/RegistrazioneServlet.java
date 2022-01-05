package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.bean.StrutturaBean;
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;
import model.dao.PartecipazioneDAO;
import model.dao.StrutturaDAO;

/**
 * Servlet implementation class EsempioServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrazioneServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request
                .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
      dispatcher.forward(request, response);
      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cf = (String) request.getParameter("cf");
		if(cf.equals("giocatore"))
		{
		// TODO Auto-generated method stub
		try {
			   
		       String nome = (String) request.getParameter("nome");
		       String cognome = (String) request.getParameter("cognome");
		       String email = (String) request.getParameter("email");
		       String username = (String) request.getParameter("username");
		       String password = (String) request.getParameter("password");  
		       String nazione = (String) request.getParameter("nazione");
		       String provincia = (String) request.getParameter("provincia");
		       String citta = (String) request.getParameter("citta");
		       String cap = (String) request.getParameter("cap");
		       String telefono = (String) request.getParameter("telefono");
		       String data = (String) request.getParameter("data");
		       GiocatoreDAO gd=new GiocatoreDAO();
		       GiocatoreBean testEmail= gd.doRetrieveByKey(email);
		       GiocatoreBean testUsername= gd.doRetrieveByUsername(username);
		       
		       if(testEmail==null && testUsername==null)
		       {
		    	   GiocatoreBean g =new GiocatoreBean();
					g.setCapResidenza(cap);
					g.setCittaResidenza(citta);
					g.setCognome(cognome);			
					g.setDataNascita(java.sql.Date.valueOf(data));
					g.setEmail(email);
					g.setNazioneResidenza(nazione);
					g.setNome(nome);
					g.setPassword(password);
					g.setProvinciaResidenza(provincia);
					g.setTelefono(telefono);
					g.setUsername(username);
					float t=0;
					g.setValutazione(t);
		            gd.doSave(g);
	                GiocatoreBean test=gd.doRetrieveByKey(email);
		           if (test!=null)
		           {
		        	   request.getSession().setAttribute("giocatore",test);
		        	   RequestDispatcher dispatcher = request
		                       .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
		             dispatcher.forward(request, response);
		           }
		      	        
		           else 
		           {	 
		        	   request.setAttribute("errorReg","errore"); 
		        	   RequestDispatcher dispatcher = request
		                       .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
		             dispatcher.forward(request, response);
		           }
		       }
		 else {
		        if(testEmail!=null) {
		        	
		    	       request.setAttribute("emailRe","errorEmail");  
		      
		       }
		        
		       if(testUsername!=null) {
		    	   
		    	       request.setAttribute("userRe","errorUser");  
		    	        
		       }
		       RequestDispatcher dispatcher = request
	                   .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
	               dispatcher.forward(request, response);
		      }
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		}
		else {
			
			try {
				   String email = (String) request.getParameter("emailG");
				   String telefono = (String) request.getParameter("telefonoGestore");
			       String nome = (String) request.getParameter("nomeG");
			       String cognome = (String) request.getParameter("cognomeG"); 
			       String nomeStruttura = (String) request.getParameter("strutturaG");
			       String nazione = (String) request.getParameter("nazioneG");
			       String provincia = (String) request.getParameter("provinciaG");
			       String citta = (String) request.getParameter("cittaG");
			       String cap = (String) request.getParameter("capG");
			       String indirizzo = (String) request.getParameter("indirizzoG");
			       String telefonoStruttura = (String) request.getParameter("telefonoStruttura");
			       String password = (String) request.getParameter("passwordG");
			       GestoreDAO gd=new GestoreDAO();
			       StrutturaDAO sd=new StrutturaDAO();
			       GestoreBean testEmail= gd.doRetrieveByKey(email);
			       StrutturaBean testStruttura= sd.doRetrieveByKey(nomeStruttura);
			       
			    
	            
			       
			       if(testEmail==null && testStruttura==null)
			       {
			       GestoreBean ges =new GestoreBean();
				   StrutturaBean sb=new StrutturaBean();
				    sb.setNome(nomeStruttura);			
					sb.setIndirizzo(indirizzo);
					sb.setNazione(nazione);
					sb.setProvincia(provincia);
					sb.setCitta(citta);
					sb.setCap(cap);
					sb.setTelefono(telefonoStruttura);
					sd.doSave(sb);
					
					ges.setCognome(cognome);			
					ges.setEmail(email);
					ges.setNome(nome);
					ges.setPassword(password);
					ges.setTelefono(telefono);
					ges.setStruttura(nomeStruttura);
					
				
					
			
		             
		             
		            gd.doSave(ges);
		            /*request.setAttribute("errorReg",pippo);
	                
		        	   RequestDispatcher dispatcher1 = request
		                       .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
		             dispatcher1.forward(request, response);
		            */
	                GestoreBean test=gd.doRetrieveByKey(email);
	                StrutturaBean testS=sd.doRetrieveByKey(nomeStruttura);
	        
	     
		           if ((test!=null)&& (testS!=null))
		           {
		        	   request.getSession().setAttribute("gestore",test);
		        	   RequestDispatcher dispatcher = request
		                       .getRequestDispatcher(response.encodeRedirectURL("./RichiesteEventi.jsp"));
		             dispatcher.forward(request, response);
		           }
		      	        
		           else 
		           {	 
		        	   request.setAttribute("errorReg","errore"); 
		        	   RequestDispatcher dispatcher = request
		                       .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
		             dispatcher.forward(request, response);
		           }
			      
			       }
			       else {
			    	   if(testEmail!=null) {
				    	   request.setAttribute("emailRe","errorEmail");  
				       }
				       if(testStruttura!=null) {
				    	   request.setAttribute("strutturaRe","errorStruttura");  
				       }
				       RequestDispatcher dispatcher = request
			                   .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
			         dispatcher.forward(request, response);
			       }
			} catch (SQLException e) {
				e.getStackTrace();
			}
			
		     }
		     
	}

}
