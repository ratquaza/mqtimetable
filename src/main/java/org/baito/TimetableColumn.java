package org.baito;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;

public class TimetableColumn {

    public ArrayList<Class> classes = new ArrayList<>();

    public TimetableColumn(HtmlPage page, DomElement column) {
        DomElement body = null;
        for (DomElement i : column.getChildElements()) {
            if (i.getId().equalsIgnoreCase(column.getId() + "_Body")) {
                body = i;
                break;
            }
        }

        ArrayList<Class> toAdd = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            if (page.getElementById(body.getId() + "_" + i) != null) {
                toAdd.add(new Class(page, page.getElementById(body.getId() + "_" + i)));
            } else {
                break;
            }
        }

        while (toAdd.size() > 0) {
            Class earliest = null;
            for (Class i : toAdd) {
                if (earliest == null) {
                    earliest = i;
                } else {
                    int eStartHour = Integer.parseInt(earliest.start.substring(0, earliest.start.indexOf(":")));
                    eStartHour = earliest.start.contains("pm") ? eStartHour + 12 : eStartHour;
                    int cStartHour = Integer.parseInt(i.start.substring(0, i.start.indexOf(":")));
                    cStartHour = i.start.contains("pm") ? cStartHour + 12 : cStartHour;
                    if (cStartHour < eStartHour) {
                        earliest = i;
                    }
                }
            }
            classes.add(earliest);
            toAdd.remove(earliest);
        }
    }

}
