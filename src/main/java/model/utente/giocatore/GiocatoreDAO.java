package model.utente.giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import control.DriverManagerConnectionPool;

public class GiocatoreDAO {

		private static final String TABLE_NAME = "giocatore";
		
		
		public synchronized void doSave(GiocatoreBean e) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			

			String insertSQL = "insert into " + TABLE_NAME
					+ " (username, e_mail, nome, cognome, password_giocatore, telefono, data_nascita, nazione_residenza, provincia_residenza, citta_residenza, cap_residenza, valutazione) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, e.getUsername());
				preparedStatement.setString(2, e.getEmail());
				preparedStatement.setString(3, e.getNome());
				preparedStatement.setString(4, e.getCognome());
				preparedStatement.setString(5, e.getPassword());
				preparedStatement.setString(6, e.getTelefono());
				preparedStatement.setDate(7, e.getDataNascita());
				
				preparedStatement.setString(8, e.getNazioneResidenza());
				preparedStatement.setString(9, e.getProvinciaResidenza());
				preparedStatement.setString(10, e.getCittaResidenza());
				preparedStatement.setString(11, e.getCapResidenza());
				preparedStatement.setFloat(12, e.getValutazione());
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
		
		
		public synchronized GiocatoreBean doRetrieveByKey(String email){
			 
			 Connection conn = null;
			 PreparedStatement ps = null;
			 try {
				GiocatoreBean bean = new GiocatoreBean(); 
				conn = DriverManagerConnectionPool.getConnection();
				ps = conn.
						prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE e_mail = ?");
				ps.setString(1, email);
						
				ResultSet rs = ps.executeQuery();

				// 4. Prendi il risultato
				if(rs.next())
				{
					bean.setUsername(rs.getString("username"));
					bean.setEmail(rs.getString("e_mail"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setPassword(rs.getString("password_giocatore"));
					bean.setTelefono(rs.getString("telefono"));
					bean.setDataNascita(rs.getDate("data_nascita"));
					bean.setNazioneResidenza(rs.getString("nazione_residenza"));
					bean.setProvinciaResidenza(rs.getString("provincia_residenza"));
					bean.setCittaResidenza(rs.getString("citta_residenza"));
					bean.setCapResidenza(rs.getString("cap_residenza"));
					bean.setValutazione(rs.getFloat("valutazione"));
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
		public synchronized GiocatoreBean doRetrieveByUsername(String username){
			 
			 Connection conn = null;
			 PreparedStatement ps = null;
			 try {
				GiocatoreBean bean = new GiocatoreBean(); 
				conn = DriverManagerConnectionPool.getConnection();
				ps = conn.
						prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?");
				ps.setString(1, username);
						
				ResultSet rs = ps.executeQuery();

				// 4. Prendi il risultato
				if(rs.next())
				{
					bean.setUsername(rs.getString("username"));
					bean.setEmail(rs.getString("e_mail"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setPassword(rs.getString("password_giocatore"));
					bean.setTelefono(rs.getString("telefono"));
					bean.setDataNascita(rs.getDate("data_nascita"));
					bean.setNazioneResidenza(rs.getString("nazione_residenza"));
					bean.setProvinciaResidenza(rs.getString("provincia_residenza"));
					bean.setCittaResidenza(rs.getString("citta_residenza"));
					bean.setCapResidenza(rs.getString("cap_residenza"));
					bean.setValutazione(rs.getFloat("valutazione"));
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
			
			public synchronized ArrayList<GiocatoreBean> doRetrieveAll() throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				ArrayList<GiocatoreBean> giocatori = new ArrayList<GiocatoreBean>();

				String selectSQL = "SELECT * FROM " + TABLE_NAME;
				
				

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);

					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						GiocatoreBean bean = new GiocatoreBean(); 
						bean.setUsername(rs.getString("username"));
						bean.setEmail(rs.getString("e_mail"));
						bean.setNome(rs.getString("nome"));
						bean.setCognome(rs.getString("cognome"));
						bean.setPassword(rs.getString("password_giocatore"));
						bean.setTelefono(rs.getString("telefono"));
						bean.setDataNascita(rs.getDate("data_nascita"));
						bean.setNazioneResidenza(rs.getString("nazione_residenza"));
						bean.setProvinciaResidenza(rs.getString("provincia_residenza"));
						bean.setCittaResidenza(rs.getString("citta_residenza"));
						bean.setCapResidenza(rs.getString("cap_residenza"));
						bean.setValutazione(rs.getFloat("valutazione"));
						giocatori.add(bean);
						
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
				return giocatori;
			}
			
			
			public synchronized void doUpdate(GiocatoreBean e) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String updateSQL ="UPDATE " + TABLE_NAME +
		            " SET username = ?, e_mail = ?, nome = ? , cognome = ?, password_giocatore = ?, telefono = ?, data_nascita = ?, nazione_residenza = ?, provincia_residenza = ?, citta_residenza = ?, cap_residenza = ?, valutazione = ? WHERE e_mail = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, e.getUsername());
				preparedStatement.setString(2, e.getEmail());
				preparedStatement.setString(3, e.getNome());
				preparedStatement.setString(4, e.getCognome());
				preparedStatement.setString(5, e.getPassword());
				preparedStatement.setString(6, e.getTelefono());
				preparedStatement.setDate(7, e.getDataNascita());		
				preparedStatement.setString(8, e.getNazioneResidenza());
				preparedStatement.setString(9, e.getProvinciaResidenza());
				preparedStatement.setString(10, e.getCittaResidenza());
				preparedStatement.setString(11, e.getCapResidenza());
				preparedStatement.setFloat(12, e.getValutazione());
				preparedStatement.setString(13, e.getEmail());
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
	}

