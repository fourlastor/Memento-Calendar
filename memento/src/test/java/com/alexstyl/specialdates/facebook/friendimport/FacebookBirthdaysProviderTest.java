package com.alexstyl.specialdates.facebook.friendimport;

import com.alexstyl.specialdates.CrashAndErrorTracker;
import com.alexstyl.specialdates.date.ContactEvent;
import com.alexstyl.specialdates.date.DateParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FacebookBirthdaysProviderTest {

    private static final String CALENDAR_URL = "https://www.facebook.com/ical/b.php?locale=en_US&uid=100010984206219&key=AQCdZHp_7d_O82DZ";
    private CrashAndErrorTracker tracker = new SystemLogTracker();
    private DateParser parser = new DateParser(new SystemLogTracker());

    @Test
    public void parseMockCalendar() throws CalendarFetcherException, MalformedURLException {
        FacebookContactFactory factory = new FacebookContactFactory(parser);
        FacebookBirthdaysProvider fetcher = new FacebookBirthdaysProvider(new FacebookCalendarLoader(), new ContactEventSerialiser(factory, tracker));
        URL url = new URL(CALENDAR_URL);

        List<ContactEvent> contacts = fetcher.fetchCalendarFrom(url);
        assertThat(contacts).isNotEmpty();
    }

    @Test
    public void parsingRandomURLreturnsNoEvents() throws CalendarFetcherException, MalformedURLException {
        FacebookContactFactory factory = new FacebookContactFactory(parser);
        FacebookBirthdaysProvider fetcher = new FacebookBirthdaysProvider(new FacebookCalendarLoader(), new ContactEventSerialiser(factory, tracker));
        URL url = new URL("https://www.google.com");

        List<ContactEvent> contactEvents = fetcher.fetchCalendarFrom(url);
        assertThat(contactEvents.isEmpty());

    }
}
