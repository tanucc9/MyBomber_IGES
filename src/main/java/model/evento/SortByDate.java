package model.evento;

import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class SortByDate.
 */
public class SortByDate implements Comparator<EventoBean> {

  /**
   * Compare.
   *
   * @param a the a
   * @param b the b
   * @return the int
   */
  @Override
  public int compare(EventoBean a, EventoBean b) {
    if (a.precedenza(b)) {
      return -1;
    }
    if (b.precedenza(a)) {
      return 1;
    }
    return 0;
  }
}
