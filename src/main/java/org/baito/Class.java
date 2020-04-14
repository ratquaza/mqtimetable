package org.baito;

import com.gargoylesoftware.htmlunit.html.*;

public class Class {

    public String start;
    public String end;
    public String unitcode;
    public String type;
    public String stream;
    public String location;

    public Class(HtmlPage page, DomElement element) {
        DomElement cell = page.getElementById(element.getId() + "_ClassInnerPanel");
        HtmlInput startTime = page.getElementByName(element.getId().replace("_","$") + "$HiddenStartTm");
        start = startTime.getDefaultValue();
        HtmlInput endTime = page.getElementByName(element.getId().replace("_","$") + "$HiddenEndTm");
        end = endTime.getDefaultValue();
        HtmlElement code = page.getHtmlElementById(element.getId() + "_HeaderPanel");
        unitcode = code.getTextContent().trim();
        HtmlElement content = page.getHtmlElementById(element.getId() + "_BodyContentPanel");
        for (DomElement i : content.getChildElements()) {
            if (i instanceof HtmlSpan) {
                HtmlSpan span = (HtmlSpan) i;
                if (span.getAttribute("class").equalsIgnoreCase("cssTtableClsSlotWhat")) {
                    type = span.getTextContent();
                } else if (span.getAttribute("class").equalsIgnoreCase("cssTtableClsSlotWhere")) {
                    location = span.getTextContent();
                }
            }
        }
    }

    public String toString() {
        return unitcode + " " + start + " - " + end + " " + type + " " + " " + location;
    }

}
