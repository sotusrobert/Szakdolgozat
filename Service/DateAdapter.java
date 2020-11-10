/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceProgram.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author ACER
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    
	private static Logger logger = Logger
			.getLogger(DateAdapter.class.getName());
	private SimpleDateFormat format;

	public DateAdapter() {
		format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	@Override
	public String marshal(Date d) throws Exception {
		logger.info(d.toString());

		try {
			return format.format(d);
		} catch (Exception e) {
			logger.log(Level.WARNING,
					String.format("Failed to format date %s", d.toString()), e);
			return null;
		}
	}

	@Override
	public Date unmarshal(String d) throws Exception {
		logger.info(d);

		if (d == null) {
			return null;
		}

		try {
			return format.parse(d);
		} catch (ParseException e) {
			logger.log(Level.WARNING,
					String.format("Failed to parse string %s", d), e);
			return null;
		}
	}
}
