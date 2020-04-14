package org.baito;

public enum TimetableDay {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4);

    public int index;

    TimetableDay(int ind) {
        index = ind;
    }
}
