package control.cron;

import model.evento.EventoBean;
import model.evento.EventoDAO;

public class EventStateUpdater {

	public void updateEventStateByDateTime(long nowMillis) {
		EventoDAO eventoDAO = new EventoDAO();
		try {
			for (EventoBean eventoBean : eventoDAO.doRetrieveAll()) {
				EventoBean eventoToUpd = getEventoBeanWithNewState(eventoBean, nowMillis);
				if (eventoToUpd != null) {
					eventoDAO.doUpdate(eventoToUpd);
					System.out.println("Evento Code=" + eventoToUpd.getCode()
							+ " with state updated to \"" + eventoToUpd.getStato() + "\"");
				}
			}
		} catch (Exception e) {
			System.err.println("Unable to refresh \"Eventi\" states");
		}
	}

	public EventoBean getEventoBeanWithNewState(EventoBean eventoBean, long nowMillis) {
		// attivo & inizio passato => anullato
		// completato & inizio passato => iniziato
		// iniziato & inizio+durata passato => finito
		long inizioEvento = eventoBean.getStartTimeMillis();
		long tenMinutesMillis = 10L * 60 * 1000;
		if (eventoBean.getStato().equals("attivo") && (inizioEvento + tenMinutesMillis) <= nowMillis) {
			eventoBean.setStato("annullato");
			return eventoBean;
		} else {
			long millisDurataEvento = 60L * 60 * 1000; // Durata evento fissa a 1h.
			long fineMillis = eventoBean.getStartTimeMillis() + millisDurataEvento;
			if (eventoBean.getStato().equals("completato") && inizioEvento <= nowMillis) {
				if (fineMillis <= nowMillis) {
					eventoBean.setStato("finito");
					return eventoBean;
				}
				eventoBean.setStato("iniziato");
				return eventoBean;
			} else if (eventoBean.getStato().equals("iniziato")) {
				if (fineMillis <= nowMillis) {
					eventoBean.setStato("finito");
					return eventoBean;
				}
			}
		}
		return null;
	}

}
