package org.baito;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import java.util.ArrayList;

public class Timetable {

    private ArrayList<TimetableColumn> columns = new ArrayList<>();

    public Timetable(String username, String password) throws Exception {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = client.getPage("https://mq-edu-web.t1cloud.com/T1SMDefault/WebApps/eStudent/login.aspx");
        HtmlInput u = page.getElementByName("ctl00$Content$txtUserName$txtText");
        u.setValueAttribute(username);
        HtmlInput p = page.getElementByName("ctl00$Content$txtPassword$txtText");
        p.setValueAttribute(password);
        HtmlSubmitInput button = page.getElementByName("ctl00$Content$cmdLogin");
        button.click();
        HtmlPage finalPage = client.getPage("https://mq-edu-web.t1cloud.com/T1SMDefault/WebApps/eStudent/SM/StudentTtable10.aspx?r=MQ.ESTU.UGSTUDNT&f=MQ.EST.TIMETBL.WEB");
        columns.add(new TimetableColumn(finalPage, finalPage.getElementById("ctl00_Content_ctlTimetableMain_MonDayCol")));
        columns.add(new TimetableColumn(finalPage, finalPage.getElementById("ctl00_Content_ctlTimetableMain_TueDayCol")));
        columns.add(new TimetableColumn(finalPage, finalPage.getElementById("ctl00_Content_ctlTimetableMain_WedDayCol")));
        columns.add(new TimetableColumn(finalPage, finalPage.getElementById("ctl00_Content_ctlTimetableMain_ThuDayCol")));
        columns.add(new TimetableColumn(finalPage, finalPage.getElementById("ctl00_Content_ctlTimetableMain_FriDayCol")));
    }

    public ArrayList<Class> getClass(TimetableDay day) {
        return columns.get(day.index).classes;
    }
}
