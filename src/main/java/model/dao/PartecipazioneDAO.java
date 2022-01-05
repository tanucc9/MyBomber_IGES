package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import control.DriverManagerConnectionPool;
import model.bean.PartecipazioneBean;

public class PartecipazioneDAO {

		private static final String TABLE_NAME = "partecipazione";
		
		
		public synchronized void doSave(PartecipazioneBean e) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			

			String insertSQL = "insert into " + TABLE_NAME
					+ " (e_mail, nome_evento) values (?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, e.getUtente());
				preparedStatement.setString(2, e.getEvento());	
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
		
		
		public synchronized PartecipazioneBean doRetrieveByKey(String email,String evento){
			 
			 Connection conn = null;
			 PreparedStatement preparedStatement = null;
			 try {
				PartecipazioneBean bean = new PartecipazioneBean(); 
				conn = DriverManagerConnectionPool.getConnection();
				preparedStatement = conn.
						prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE e_mail = ? AND nome_evento = ?");
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, evento);
			
				ResultSet rs = preparedStatement.executeQuery();

				// 4. Prendi il risultato
				if(rs.next())
				{
					bean.setUtente(rs.getString("e_mail"));
					bean.setEvento(rs.getString("nome_evento"));
					
					
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
		
			
			public synchronized ArrayList<PartecipazioneBean> doRetrieveAll() throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				ArrayList<PartecipazioneBean> partecipazioni = new ArrayList<PartecipazioneBean>();

				String selectSQL = "SELECT * FROM " + TABLE_NAME;
				
				

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);

					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						PartecipazioneBean bean = new PartecipazioneBean(); 
						bean.setUtente(rs.getString("e_mail"));
						bean.setEvento(rs.getString("nome_evento"));
						
						partecipazioni.add(bean);
						
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
				return partecipazioni;
			}
			
			
			public synchronized void doUpdate(PartecipazioneBean e) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String updateSQL ="UPDATE " + TABLE_NAME +
		            " SET e_mail = ?, nome_evento = ? WHERE e_mail = ? AND nome_evento = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, e.getUtente());
				preparedStatement.setString(2, e.getEvento());
				preparedStatement.setString(3, e.getUtente());
				preparedStatement.setString(4, e.getEvento());
						
				

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
			
			
			public synchronized boolean doDelete(String email,String evento) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				int result = 0;

				String deleteSQL = "delete from " + TABLE_NAME + " where e_mail = ? AND nome_evento = ?";

				try {
					connection = DriverManagerConnectionPool.getConnection();
					connection.setAutoCommit(true);
					preparedStatement = connection.prepareStatement(deleteSQL);
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, evento);
				
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
			
			
			public synchronized ArrayList<PartecipazioneBean> doRetrieveByEvento(String evento){
				 
				 Connection conn = null;
				 PreparedStatement preparedStatement = null; 
				 ArrayList<PartecipazioneBean> partecipanti = new ArrayList<PartecipazioneBean>();
				 String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE nome_evento = ?";
				 
				 
				 try {
					conn = DriverManagerConnectionPool.getConnection();
					preparedStatement = conn.prepareStatement(selectSQL);
					preparedStatement.setString(1, evento);
				
					ResultSet rs = preparedStatement.executeQuery();

					// 4. Prendi il risultato
					if(rs.next()) {
						PartecipazioneBean bean = new PartecipazioneBean();
						bean.setUtente(rs.getString("e_mail"));
						bean.setEvento(rs.getString("nome_evento"));
						partecipanti.add(bean);
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
				return partecipanti;
			 }
			
			
			/*public synchronized int count(String evento) {
				 
				 Connection conn = null;
				 PreparedStatement preparedStatement = null; 
				 ArrayList<PartecipazioneBean> partecipanti = new ArrayList<PartecipazioneBean>();
				 String selectSQL = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE nome_evento = ?";
				 
				 try {
						conn = DriverManagerConnectionPool.getConnection();
						preparedStatement = conn.prepareStatement(selectSQL);
						preparedStatement.setString(1, evento);
					
						ResultSet rs = preparedStatement.executeQuery();

						return;
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
			}*/
			
	}

