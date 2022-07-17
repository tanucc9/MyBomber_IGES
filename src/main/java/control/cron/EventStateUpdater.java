package control.cron;

import model.evento.EventoBean;
import model.evento.EventoDAO;

import java.sql.Date;
import java.util.List;

public class EventStateUpdater {

	public void updateEventStateByDateTime(Date now) {
		EventoDAO eventoDAO = new EventoDAO();
		try {
			// attivo & inizio passato => anullato
			// completato & inizio passato => iniziato
			// iniziato & inizio+durata passato => finito
			List<EventoBean> eventi = eventoDAO.doRetrieveAll();
			long millisDurataEvento = 60L * 60 * 1000; // Durata evento fissa a 1h.
			Date oneHourInTheFuture = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
			for (EventoBean eventoBean : eventi) {
				Date inizioEvento = new Date(eventoBean.getData().getTime() + 60L * 60 * eventoBean.getOra());
				if (eventoBean.getStato().equals("attivo") && inizioEvento.before(now)) {
					eventoBean.setStato("annullato");
					eventoDAO.doUpdate(eventoBean);
					System.out.println("Evento Code=" + eventoBean.getCode()
							+ " would have started but is missing people => set to \"annullato\"");
				} else if (eventoBean.getStato().equals("completato") && inizioEvento.before(now)) {
					eventoBean.setStato("iniziato");
					eventoDAO.doUpdate(eventoBean);
					System.out.println("Evento Code=" + eventoBean.getCode() + " just started");
				} else if (eventoBean.getStato().equals("iniziato")) {
					Date fineEvento = new Date(inizioEvento.getTime() + millisDurataEvento);
					if (fineEvento.before(now)) {
						eventoBean.setStato("finito");
						eventoDAO.doUpdate(eventoBean);
						System.out.println("Evento Code=" + eventoBean.getCode()
								+ " started one hour ago => set to \"finito\"");
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Unable to refresh \"Eventi\" states");
		}
	}

}
