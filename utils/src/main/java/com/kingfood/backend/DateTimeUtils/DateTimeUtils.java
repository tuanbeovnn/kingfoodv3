package com.kingfood.backend.DateTimeUtils;

import com.kingfood.backend.exceptions.CommonUtils;
import com.kingfood.backend.exceptions.CustomException;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

    public static String getDateTimeNow(String format) {
        if (format == null) {
            format = "dd/MM/yyyy HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date now = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(now);
    }

    public static String formatDate(Date date, String format) {
        if (format == null) {
            format = "dd/MM/yyyy HH:mm:ss";
        }
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);

    }

    public static Date getDateTimeNow() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * formatDateTimeQuery : format String date use query in database
     *
     * @param date : string date input of function
     * @return String {java.lang.String}
     * @throws ParseException
     */
    public static String formatDateTimeQuery(String date) throws ParseException {
        String dateNew = date;
        if (date.trim().contains("-")) {
            dateNew = date.trim().replace("-", "/");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateNew);
        return formatter.format(date1);
    }

    /**
     * convertStringRequestToTimesTamp : convert string date client request to Timestamp
     *
     * @param date       : string date client request
     * @param dateFormat : format of date (client request)
     * @return Timestamp {java.sql.Timestamp}
     */
    public static Timestamp convertStringRequestToTimesTamp(String date, String dateFormat) {
        try {
            if (StringUtils.isBlank(date)) {
                return null;
            } else {
                DateFormat formatter = new SimpleDateFormat(dateFormat);
                Timestamp result = null;
                if (date.contains("T")) {
                    java.sql.Date dateAfterFormat = (java.sql.Date) formatter.parse(date.trim().replaceAll("Z$", "+0000"));
                    result = new Timestamp(dateAfterFormat.getTime());
                } else {
                    Date dateAfterFormat = formatter.parse(date);
                    result = new Timestamp(dateAfterFormat.getTime());
                }
                return result;
            }
        } catch (Exception e) {
            throw new CustomException("convert date to timestamp fail", CommonUtils.putError("date", "ERR_007"));
        }
    }

    /**
     * compareAfterDateTimeNow : compare date after vs date now
     *
     * @param date : date for compare
     * @return Boolean
     */
    public static Boolean compareAfterDateTimeNow(Date date) {
        Date dateNow = new Date(System.currentTimeMillis());
        if (date.after(dateNow)) {
            return true;
        }
        return false;
    }

    /**
     * equalsDateTimeNow
     *
     * @param date
     * @return
     */
    public static Boolean equalsDateTimeNow(Date date) {
        Date dateNow = new Date(System.currentTimeMillis());
        if (date.equals(dateNow)) {
            return true;
        }
        return false;
    }

    /**
     * compareBeforeDateTimeNow : compare date before vs date now
     *
     * @param date : date for compare
     * @return Boolean
     */
    public static Boolean compareBeforeDateTimeNow(Date date) {
        Date dateNow = new Date(System.currentTimeMillis());
        if (date.before(dateNow)) {
            return true;
        }
        return false;
    }

    /**
     * minusDayAndDateTimeNow : minus date in database vs date now
     *
     * @param date : date in database
     * @return Long {java.lang.Long}
     */
    public static Long minusDayAndDateTimeNow(Date date) {
        Date dateNow = new Date(System.currentTimeMillis());
        Calendar calendarDateEnd = Calendar.getInstance();
        Calendar calendarDateNow = Calendar.getInstance();
        calendarDateEnd.setTime(date);
        calendarDateNow.setTime(dateNow);
        Long addTime = 2L;
        if ((calendarDateEnd.get(Calendar.DAY_OF_MONTH) - calendarDateNow.get(Calendar.DAY_OF_MONTH)) == 0) {
            addTime = 1L;
        }
        if ((calendarDateEnd.get(Calendar.DAY_OF_MONTH) - calendarDateNow.get(Calendar.DAY_OF_MONTH)) < 0) {
            addTime = 0L;
        }
        return (calendarDateEnd.getTime().getTime() - calendarDateNow.getTime().getTime()) / (24 * 3600 * 1000) + addTime;
    }

    /**
     * convertDateToStringUseFormatDate
     *
     * @param date   : date for format
     * @param format : String format of date
     * @return String {java.lang.String}
     */
    public static String convertDateToStringUseFormatDate(Date date, String format) {
        if (format == null) {
            format = "dd-MM-yyyy";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * convertToHoursMinuteSecond
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String convertToHoursMinuteSecond(Date startDate, Date endDate) {
        StringBuilder result = new StringBuilder();
        long duration = endDate.getTime() - startDate.getTime();
        long house = TimeUnit.MILLISECONDS.toHours(duration);
        result.append(house).append(":");
        long minute = TimeUnit.MILLISECONDS.toMinutes(duration) - (house * 60);
        result.append(minute).append(":");
        long second = TimeUnit.MILLISECONDS.toSeconds(duration) - (minute * 60);
        result.append(second);
        return result.toString();
    }

    /**
     * getSecond
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getSecond(Date startDate, Date endDate) {
        LocalDateTime ldtStart = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtEndDate = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        return (int) Duration.between(ldtStart, ldtEndDate).getSeconds() / 60 + 1;
    }

    /**
     * getDayWeek
     *
     * @param date: data to get day week
     * @return {int}
     */
    public static int getDayWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }


}



