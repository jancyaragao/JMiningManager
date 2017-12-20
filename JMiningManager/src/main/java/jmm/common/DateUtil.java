package jmm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

	public static Date stringToDate(String data_texto) {
		Date data = null;

		try {
			data = new SimpleDateFormat("dd/MM/yyyy").parse(data_texto);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static String dateToString(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
	}

}
