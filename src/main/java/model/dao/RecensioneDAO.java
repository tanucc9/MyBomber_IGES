package model.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import control.DriverManagerConnectionPool;
import model.bean.RecensioneBean;

public class RecensioneDAO {

		private static final String TABLE_NAME = "recensione";
		
		
		public synchronized void doSave(RecensioneBean e) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			

			String insertSQL = "insert into " + TABLE_NAME
					+ " (e_mail_recensore, e_mail_recensito, nome_evento, recensione, descrizione) values (?, ?, ?, ?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, e.getRecensore());
				preparedStatement.setString(2, e.getRecensito());
				preparedStatement.setString(3, e.getEvento());
				preparedStatement.setFloat(4, e.getRecensione());
				preparedStatement.setString(5, e.getDescrizione());
				
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
		
		
		public synchronized RecensioneBean doRetrieveByKey(String recensore,String recensito,String evento){
			 
			 Connection conn = null;
			 PreparedStatement preparedStatement = null;
			 try {
				RecensioneBean bean = new RecensioneBean(); 
				conn = DriverManagerConnectionPool.getConnection();
				preparedStatement = conn.
						prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE e_mail_recensore = ? AND e_mail_recensito = ? AND nome_evento = ?");
				preparedStatement.setString(1, recensore);
				preparedStatement.setString(2, recensito);
				preparedStatement.setString(3, evento);
				ResultSet rs = preparedStatement.executeQuery();

				// 4. Prendi il risultato
				if(rs.next())
				{
					bean.setRecensore(rs.getString("e_mail_recensore"));
					bean.setRecensito(rs.getString("e_mail_recensito"));
					bean.setEvento(rs.getString("nome_evento"));
					bean.setRecensione(rs.getFloat("recensione"));
					
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
		
			
			public synchronized ArrayList<RecensioneBean> doRetrieveAll() throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();

				String selectSQL = "SELECT * FROM " + TABLE_NAME;
				
				

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);

					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						RecensioneBean bean = new RecensioneBean(); 
						bean.setRecensore(rs.getString("e_mail_recensore"));
						bean.setRecensito(rs.getString("e_mail_recensito"));
						bean.setEvento(rs.getString("nome_evento"));
						bean.setRecensione(rs.getFloat("recensione"));
						recensioni.add(bean);
						
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
				return recensioni;
			}

			public synchronized Float doRetrieveMedia(String giocatore) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				String selectSQL = "SELECT AVG(valutazione) AS media FROM " + TABLE_NAME + "WHERE e_mail_recensito = ?";
				
				

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
                    preparedStatement.setString(1, giocatore);
					ResultSet rs = preparedStatement.executeQuery();
					float media=0;
					while (rs.next()) {
						
						media=rs.getFloat("media");
			            return media;
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
				return null;
			}			


			public synchronized ArrayList<String> doRetrieveDaRecensire(String recensore, String evento) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				ArrayList<String> daRecensire = new ArrayList<String>();

				String selectSQL = "SELECT e_mail FROM partecipazione WHERE nome_evento = ? AND e_mail != ALL " 
							+ "(SELECT e_mail_recensito FROM " +TABLE_NAME + " WHERE nome_evento = ? AND e_mail_recensore = ?) AND e_mail != ?";
				
				

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);

					preparedStatement.setString(1, evento);
					preparedStatement.setString(2, evento);
					preparedStatement.setString(3, recensore);
					preparedStatement.setString(4, recensore);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						String utente = rs.getString("e_mail");
						daRecensire.add(utente);
						
					}

				}finally {
				
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						if (connection != null)
							connection.close();
					}
				}
				return daRecensire;
			}
			
			
			public synchronized ArrayList<String> doRetrieveRecensiti(String recensore, String evento) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				ArrayList<String> recensiti = new ArrayList<String>();

				String selectSQL = "SELECT e_mail_recensito FROM " +TABLE_NAME + " WHERE nome_evento = ? AND e_mail_recensore = ?";
		
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);

					preparedStatement.setString(1, evento);
					preparedStatement.setString(2, recensore);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						String utente = rs.getString("e_mail");
						recensiti.add(utente);
						
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
				return recensiti;
			}
			
			
			public synchronized void doUpdate(RecensioneBean e) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String updateSQL ="UPDATE " + TABLE_NAME +
		            " SET e_mail_recensore = ?, e_mail_recensito = ?, nome_evento = ? , recensione = ? , descrizione = ? WHERE e_mail_recensore = ? AND e_mail_recensito = ? AND nome_evento = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, e.getRecensore());
				preparedStatement.setString(2, e.getRecensito());
				preparedStatement.setString(3, e.getEvento());
				preparedStatement.setString(4, e.getDescrizione());
				preparedStatement.setFloat(5, e.getRecensione());
				preparedStatement.setString(6, e.getRecensore());
				preparedStatement.setString(7, e.getRecensito());
				preparedStatement.setString(8, e.getEvento());		
				

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
			
			
			public synchronized boolean doDelete(String recensore,String recensito,String evento) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				int result = 0;

				String deleteSQL = "delete from " + TABLE_NAME + " where e_mail_recensore = ? AND e_mail_recensito = ? AND nome_evento = ?";

				try {
					connection = DriverManagerConnectionPool.getConnection();
					connection.setAutoCommit(true);
					preparedStatement = connection.prepareStatement(deleteSQL);
					preparedStatement.setString(1, recensore);
					preparedStatement.setString(2, recensito);
					preparedStatement.setString(3, evento);
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

