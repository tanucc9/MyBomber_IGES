package model.bean;

import java.util.Comparator;

public class SortByDate implements Comparator<EventoBean> {
    @Override
    public int compare(EventoBean a, EventoBean b) {
    	if(a.precedenza(b))
    	return -1;
    	if(b.precedenza(a))
        	return 1;
        return 0;
    }
}

