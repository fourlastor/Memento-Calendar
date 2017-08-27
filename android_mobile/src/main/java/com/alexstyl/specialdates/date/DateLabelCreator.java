package com.alexstyl.specialdates.date;

public interface DateLabelCreator {
    String createLabelWithoutYearFor(Date date);

    String createLabelWithYearPreferredFor(Date date);
}
