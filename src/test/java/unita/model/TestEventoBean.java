package unita.model;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import model.evento.EventoBean;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class TestEventoBean.
 */
public class TestEventoBean {

  /** The bean. */
  EventoBean bean;

  /**
   * Sets the up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    bean = new EventoBean();
    bean.setNome("evento124");
    bean.setDescrizione("mitico evento");
    bean.setStruttura("playk");
    bean.setData(Date.valueOf("2023-01-03"));
    bean.setOra(2);
    bean.setGestore("gino@gino.it");
    bean.setOrganizzatore("simone@simone.it");
    bean.setStato("completato");
    bean.setValutazione(0);
    bean.setNumPartecipanti(10);
  }

  /**
   * Rimouovi G.
   */
  @Test
  public void rimouoviG() {
    bean.rimuoviG();
    assertEquals(bean.getNumPartecipanti(), 9);
  }

  /**
   * Precedenza.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void precedenza() throws SQLException {

    EventoBean b2 = new EventoBean();
    b2.setNome("evento12");
    b2.setDescrizione("mitico evento");
    b2.setStruttura("playk");
    b2.setData(Date.valueOf("2021-01-03"));
    b2.setOra(2);
    b2.setGestore("gino@gino.it");
    b2.setOrganizzatore("simone@simone.it");
    b2.setStato("completato");
    b2.setValutazione(0);
    b2.setNumPartecipanti(10);
    boolean p = b2.precedenza(bean);
    assert (p);
  }

  /**
   * No precedenza.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void noPrecedenza() throws SQLException {

    EventoBean b2 = new EventoBean();
    b2.setNome("evento12");
    b2.setDescrizione("mitico evento");
    b2.setStruttura("playk");
    b2.setData(Date.valueOf("2026-01-03"));
    b2.setOra(2);
    b2.setGestore("gino@gino.it");
    b2.setOrganizzatore("simone@simone.it");
    b2.setStato("completato");
    b2.setValutazione(0);
    b2.setNumPartecipanti(10);
    boolean p = b2.precedenza(bean);
    assert (!p);
  }

  /**
   * Ordina per data.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void ordinaPerData() throws SQLException {
    ArrayList<EventoBean> lista = new ArrayList<>();
    ArrayList<String> lr = new ArrayList<>();
    EventoBean b2 = new EventoBean();
    b2.setNome("evento12");
    b2.setDescrizione("mitico evento");
    b2.setStruttura("playk");
    b2.setData(Date.valueOf("2026-01-03"));
    b2.setOra(2);
    b2.setGestore("gino@gino.it");
    b2.setOrganizzatore("simone@simone.it");
    b2.setStato("completato");
    b2.setValutazione(0);
    b2.setNumPartecipanti(10);
    lista.add(bean);
    lr.add(bean.toString());
    lista.add(b2);
    lr.add(b2.toString());
    ArrayList<EventoBean> ordinati = EventoBean.ordinaPerData(lista);
    ArrayList<String> or = new ArrayList<>();
    for (EventoBean e : ordinati) {
      or.add(e.toString());
    }
    assertEquals(or, lr);
  }

  /**
   * Ordina per data R.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void ordinaPerDataR() throws SQLException {
    ArrayList<EventoBean> lista = new ArrayList<>();
    ArrayList<String> lr = new ArrayList<>();
    EventoBean b2 = new EventoBean();
    b2.setNome("evento12");
    b2.setDescrizione("mitico evento");
    b2.setStruttura("playk");
    b2.setData(Date.valueOf("2026-01-03"));
    b2.setOra(2);
    b2.setGestore("gino@gino.it");
    b2.setOrganizzatore("simone@simone.it");
    b2.setStato("completato");
    b2.setValutazione(0);
    b2.setNumPartecipanti(10);
    lista.add(bean);
    lr.add(b2.toString());
    lista.add(b2);
    lr.add(bean.toString());
    ArrayList<EventoBean> ordinati = EventoBean.ordinaPerDataR(lista);
    ArrayList<String> or = new ArrayList<>();
    for (EventoBean e : ordinati) {
      or.add(e.toString());
    }
    assertEquals(or, lr);
  }
}
