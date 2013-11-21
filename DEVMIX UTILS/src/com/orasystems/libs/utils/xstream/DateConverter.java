package com.orasystems.libs.utils.xstream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DateConverter implements Converter {
	private SimpleDateFormat formatter = new SimpleDateFormat(
			"MMMM dd, yyyy HH:mm:ss");


	public DateConverter(String format){
		formatter = new SimpleDateFormat(format);
	}
	
	public boolean canConvert(@SuppressWarnings("rawtypes") Class clazz) {
		return Date.class.isAssignableFrom(clazz);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		Date date = (Date) value;
		writer.setValue(formatter.format(date));
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		GregorianCalendar calendar = new GregorianCalendar();
		try {
			calendar.setTime(formatter.parse(reader.getValue()));
		} catch (ParseException e) {
			throw new ConversionException(e.getMessage(), e);
		}
		return calendar;
	}
}
