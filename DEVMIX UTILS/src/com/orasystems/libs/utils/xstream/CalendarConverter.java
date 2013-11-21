package com.orasystems.libs.utils.xstream;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.util.Log;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CalendarConverter implements Converter {

    private Locale locale;

    public CalendarConverter(Locale locale) {
            super();
            this.locale = locale;
    }

    public boolean canConvert(@SuppressWarnings("rawtypes") Class clazz) {
            return Calendar.class.isAssignableFrom(clazz);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
                    MarshallingContext context) {
            Calendar calendar = (Calendar) value;
            Date date = calendar.getTime();
            DateFormat formatter;
        	formatter = DateFormat.getDateInstance(DateFormat.FULL,
                        this.locale);
        	writer.setValue(formatter.format(date));
        	Log.w(CalendarConverter.this.getClass().toString(),formatter.format(date));
    }

    public Object unmarshal(HierarchicalStreamReader reader,
                    UnmarshallingContext context) {
            GregorianCalendar calendar = new GregorianCalendar();
            DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL,
                            this.locale);
            try {
                    calendar.setTime(formatter.parse(reader.getValue()));
            } catch (ParseException e) {
                    throw new ConversionException(e.getMessage(), e);
            }
            return calendar;
    }

}