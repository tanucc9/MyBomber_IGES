package unita.model;

import control.cron.EventStateUpdater;
import junit.framework.TestCase;
import model.evento.EventoBean;
import org.junit.Before;

import java.sql.Date;

public class TestStatoEventoBean extends TestCase {

	private EventoBean e1, e2;
	private EventStateUpdater updater;

	@Before
	public void setUp() {
		e1 = new EventoBean();
		e1.setNome("evento3");
		e1.setDescrizione("Prova descrizione");
		e1.setStruttura("playk");
		e1.setData(Date.valueOf("2022-01-29"));
		e1.setOra(22);
		e1.setGestore("gino@gino.it");
		e1.setOrganizzatore("simone@simone.it");
		e1.setStato("completato");
		e1.setValutazione(0);
		e1.setNumPartecipanti(1);

		e2 = new EventoBean();
		e2.setNome("evento4");
		e2.setDescrizione("Prova descrizione 2");
		e2.setStruttura("keycloak");
		e2.setData(Date.valueOf("2022-02-14"));
		e2.setOra(7);
		e2.setGestore("pino@pino.it");
		e2.setOrganizzatore("giuseppe@franchi.it");
		e2.setStato("attivo");
		e2.setValutazione(0);
		e2.setNumPartecipanti(1);

		updater = new EventStateUpdater();
	}

	public void testStatoIniziato() {
		long fiveMinutesAfterStart = e1.getStartTimeMillis() + 5 * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(e1, fiveMinutesAfterStart);
		assertNotNull(updEvento);
		assertEquals(updEvento.getStato(), "iniziato");
	}

	public void testStatoFinito() {
		long oneHoursAndMoreAfterStart = e1.getStartTimeMillis() + 65 * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(e1, oneHoursAndMoreAfterStart);
		assertNotNull(updEvento);
		assertEquals(updEvento.getStato(), "finito");
	}

	public void testStatoNonAncoraAnnullato() {
		long afterTwoMinutes = e2.getStartTimeMillis() + 2L * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(e2, afterTwoMinutes);
		assertNull(updEvento);
	}

	public void testStatoAnnullato() {
		long afterTwelveMinutes = e2.getStartTimeMillis() + 12L * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(e2, afterTwelveMinutes);
		assertNotNull(updEvento);
		assertEquals(updEvento.getStato(), "annullato");
	}

}
