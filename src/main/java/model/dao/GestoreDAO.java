package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import control.DriverManagerConnectionPool;
import model.bean.GestoreBean;
import model.bean.StrutturaBean;

public class GestoreDAO {

	private static final String TABLE_NAME = "gestore";
	public synchronized void doSave(GestoreBean bean,StrutturaBean bean2) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		String insertSQL = "insert into " + TABLE_NAME  
				+ " (e_mail, nome, cognome, password_gestore, telefono, struttura) values (?, ?, ?, ?, ?, ?)";
		
		String insertSQL2 = "insert into struttura"  
				+ " (nome, indirizzo, nazione, citta, cap, provincia, telefono) values (?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement2 = connection.prepareStatement(insertSQL2);
			preparedStatement2.setString(1, bean2.getNome());
			preparedStatement2.setString(2, bean2.getIndirizzo());
			preparedStatement2.setString(3, bean2.getNazione());
			preparedStatement2.setString(4, bean2.getCitta());
			preparedStatement2.setString(5, bean2.getCap());
			preparedStatement2.setString(6, bean2.getProvincia());
			preparedStatement2.setString(7, bean2.getTelefono());
			preparedStatement2.executeUpdate();
			
			preparedStatement.setString(1, bean.getEmail());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getCognome());
			preparedStatement.setString(3, bean.getPassword());
			preparedStatement.setString(5, bean.getTelefono());
			preparedStatement.setString(6, bean.getStruttura());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	public synchronized void doSave(GestoreBean bean) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "insert into " + TABLE_NAME  
				+ " (e_mail, nome, cognome, password_gestore, telefono, struttura) values (?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getEmail());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getCognome());
			preparedStatement.setString(4, bean.getPassword());
			preparedStatement.setString(5, bean.getTelefono());
			preparedStatement.setString(6, bean.getStruttura());
			preparedStatement.executeUpdate();
            
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}

		
	}
	

	public synchronized GestoreBean doRetrieveByKey(String email){
		 
		 Connection conn = null;
		 PreparedStatement preparedStatement = null;
		 try {
			GestoreBean bean = new GestoreBean(); 
			conn = DriverManagerConnectionPool.getConnection();
			preparedStatement = conn.
					prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE e_mail = ?");
			preparedStatement.setString(1, email);
					
			ResultSet rs = preparedStatement.executeQuery();

			// 4. Prendi il risultato
			if(rs.next())
			{
				bean.setEmail(rs.getString("e_mail"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password_gestore"));;
				bean.setTelefono(rs.getString("telefono"));
				bean.setStruttura(rs.getString("struttura"));
				return bean;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	 }
	
	
	
	
	public synchronized void doUpdate(GestoreBean bean) throws SQLException {

		Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	String updateSQL ="UPDATE " + TABLE_NAME +
            " SET e_mail = ?, nome = ?, cognome = ?, password_gestore = ? , telefono = ?, struttura = ? WHERE e_mail = ?";
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		preparedStatement.setString(1, bean.getEmail());
		preparedStatement.setString(2, bean.getNome());
		preparedStatement.setString(3, bean.getCognome());
		preparedStatement.setString(4, bean.getPassword());
		preparedStatement.setString(5, bean.getTelefono());
		preparedStatement.setString(6, bean.getStruttura());
		preparedStatement.setString(7, bean.getEmail());
	    preparedStatement.executeUpdate();

	   connection.commit();
	   } 
	finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} 
		finally {
			if (connection != null)
				connection.close();
			}
		} 
	}
	
	
	public synchronized boolean doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "delete from " + TABLE_NAME + " where e_mail = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	
	public synchronized ArrayList<GestoreBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<GestoreBean> gestori = new ArrayList<GestoreBean>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		
		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				GestoreBean bean = new GestoreBean();
				
				
				bean.setEmail(rs.getString("e_mail"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password_gestore"));;
				bean.setTelefono(rs.getString("telefono"));
				bean.setStruttura(rs.getString("struttura"));
				gestori.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return gestori;
	}
	
	
	public synchronized GestoreBean doRetrieveByStruttura(String struttura){
		 
		 Connection conn = null;
		 PreparedStatement preparedStatement = null;
		 
		 
		 try { 
			GestoreBean bean = new GestoreBean();
			conn = DriverManagerConnectionPool.getConnection();
			preparedStatement = conn.
					prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE struttura = ?");
			preparedStatement.setString(1, struttura);
					
			ResultSet rs = preparedStatement.executeQuery();

			// 4. Prendi il risultato
			if(rs.next())
			{
				bean.setEmail(rs.getString("e_mail"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password_gestore"));;
				bean.setTelefono(rs.getString("telefono"));
				bean.setStruttura(rs.getString("struttura"));
				return bean;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	 }
	
}