package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import control.DriverManagerConnectionPool;
import model.bean.EventoBean;

public class EventoDAO {

		private static final String TABLE_NAME = "evento";
		
		
		public synchronized void doSave(EventoBean e) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			

			String insertSQL = "insert into " + TABLE_NAME
					+ " (nome, descrizione, struttura, data_evento, ora, e_mail_gestore , e_mail_utente) values (?, ?, ?, ?, ?, ?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, e.getNome());
				preparedStatement.setString(2, e.getDescrizione());
				preparedStatement.setString(3, e.getStruttura());
				preparedStatement.setDate(4, e.getData());
				preparedStatement.setTime(5, e.getOra());
				preparedStatement.setString(6, e.getGestore());
				preparedStatement.setString(7, e.getOrganizzatore());
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
		
		
		public synchronized EventoBean doRetrieveByKey(String nome){
			 
			 Connection conn = null;
			 PreparedStatement ps = null;
			 try {
				EventoBean bean = new EventoBean(); 
				conn = DriverManagerConnectionPool.getConnection();
				ps = conn.
						prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE nome = ?");
				ps.setString(1, nome);
						
				ResultSet rs = ps.executeQuery();

				// 4. Prendi il risultato
				if(rs.next())
				{
					bean.setNome(rs.getString("nome"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setStruttura(rs.getString("struttura"));
					bean.setData(rs.getDate("data_evento"));
					bean.setOra(rs.getTime("ora"));
					bean.setGestore(rs.getString("e_mail_gestore"));
					bean.setOrganizzatore(rs.getString("e_mail_utente"));
					return bean;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					ps.close();
					DriverManagerConnectionPool.releaseConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		 }
		
			
			public synchronized ArrayList<EventoBean> doRetrieveAll() throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				ArrayList<EventoBean> eventi = new ArrayList<EventoBean>();

				String selectSQL = "SELECT * FROM " + TABLE_NAME;
				
				

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);

					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						EventoBean bean = new EventoBean();
						bean.setNome(rs.getString("nome"));
						bean.setDescrizione(rs.getString("descrizione"));
						bean.setStruttura(rs.getString("struttura"));
						bean.setData(rs.getDate("data_evento"));
						bean.setOra(rs.getTime("ora"));
						bean.setGestore(rs.getString("e_mail_gestore"));
						bean.setOrganizzatore(rs.getString("e_mail_utente"));
						eventi.add(bean);
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
				return eventi;
			}
			
			
			public synchronized void doUpdate(EventoBean e) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String updateSQL ="UPDATE " + TABLE_NAME +
		            " SET nome = ?, descrizione = ?, struttura = ? , data_evento = ?, ora = ?, e_mail_gestore = ?, e_mail_utente = ? WHERE nome = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, e.getNome());
				preparedStatement.setString(2, e.getDescrizione());
				preparedStatement.setString(3, e.getStruttura());
				preparedStatement.setDate(4, e.getData());
				preparedStatement.setTime(5, e.getOra());
				preparedStatement.setString(6, e.getGestore());
				preparedStatement.setString(7, e.getOrganizzatore());
				preparedStatement.setString(8, e.getNome());
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
			
			
			public synchronized boolean doDelete(String nome) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				int result = 0;

				String deleteSQL = "delete from " + TABLE_NAME + " where nome = ?";

				try {
					connection = DriverManagerConnectionPool.getConnection();
					connection.setAutoCommit(true);
					preparedStatement = connection.prepareStatement(deleteSQL);
					preparedStatement.setString(1, nome);

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
	}

