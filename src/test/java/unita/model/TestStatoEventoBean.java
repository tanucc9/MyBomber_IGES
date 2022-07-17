package unita.model;

import control.cron.EventStateUpdater;
import junit.framework.TestCase;
import model.evento.EventoBean;
import org.junit.Before;

import java.sql.Date;

public class TestStatoEventoBean extends TestCase {

	private EventoBean evento;
	private EventStateUpdater updater;

	@Before
	public void setUp() {
		evento = new EventoBean();
		evento.setNome("evento3");
		evento.setDescrizione("Prova descrizione");
		evento.setStruttura("playk");
		evento.setData(Date.valueOf("2022-01-29"));
		evento.setOra(22);
		evento.setGestore("gino@gino.it");
		evento.setOrganizzatore("simone@simone.it");
		evento.setStato("completato");
		evento.setValutazione(0);
		evento.setNumPartecipanti(0);
		updater = new EventStateUpdater();
	}

	public void testStatoIniziato() {
		long fiveMinutesAfterStart = evento.getStartTimeMillis() + 5 * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(evento, fiveMinutesAfterStart);
		assertNotNull(updEvento);
		assertEquals(updEvento.getStato(), "iniziato");
	}

	public void testStatoFinito() {
		long oneHoursAndMoreAfterStart = evento.getStartTimeMillis() + 65 * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(evento, oneHoursAndMoreAfterStart);
		assertNotNull(updEvento);
		assertEquals(updEvento.getStato(), "finito");
	}

}
