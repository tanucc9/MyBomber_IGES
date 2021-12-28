import bean.EventoBean;

public class EventoDAO {

	private static final String TABLE_NAME = "evento";
	
	
	public synchronized void doSave(EventoBean e) {
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
		
		public synchronized ArrayList<FotoBean> doRetrieveAll() throws SQLException {
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
					bean.setFoto(rs.getString("foto"));
					bean.setAttivitaIDAttivita(rs.getInt("attivitaIDattivita"));
					eventi.add
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
			return f;
		}
}
