package pl.konczak.nzoz.ereceptaapp.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtil {

    private static final String BASIC_DATE_FORMAT = "yyyyMMdd";
    private static final String BASIC_DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    private static final DateTimeFormatter BASIC_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(BASIC_DATE_TIME_FORMAT);
    private static final DateTimeFormatter BASIC_DATE_FORMATTER = DateTimeFormatter.ofPattern(BASIC_DATE_FORMAT);

    public static String formatAsBasicDateTime(final LocalDateTime localDateTime) {
        return BASIC_DATE_TIME_FORMATTER.format(localDateTime);
    }

    public static String formatAsBasicDate(final LocalDate localDate) {
        return BASIC_DATE_FORMATTER.format(localDate);
    }

    public static String formatAsBasicDate(final LocalDateTime localDateTime) {
        return BASIC_DATE_FORMATTER.format(localDateTime.toLocalDate());
    }

    private DateTimeUtil() {
    }
}
