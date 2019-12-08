package af.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {
	//字符串时间格式转Date格式
	/**
	 * @param pDate
	 * @param partten
	 * @return yyyy-MM-dd'T'HH:mm:ss+0900
	 */
	public static String convertFormatDate(String pDate, String partten) {
		if(!"1".equals(partten) && !"2".equals(partten)) {
			return null;
		}
		String strDate = pDate;
		if ("1".equals(partten)) {
			strDate = pDate + " " + "00:00:00";
		}
		if ("2".equals(partten)){
			strDate = pDate + " " + "23:59:59";
		}
		try {
			SimpleDateFormat sdf0=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf0.parse(strDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+0900");
			return sdf.format(date);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * DateからXMLGregorianCalendarへ変換
	 * @param Date date
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	/**
	 *  StringからDateへ変換
	 * @param String pDate
	 * @return Date
	 */
	public static Date String2Date(String pDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null; //初始化date
		try {
			date = sdf.parse(pDate); //Mon Jan 14 00:00:00 CST 2013
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
