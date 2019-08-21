package es.vqs.joinfacesmap.view.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

@FacesConverter("timeConverter")
public class TimeConverter implements Converter<Long> {

	@Override
	public Long getAsObject(FacesContext context, UIComponent component, String value) {
		String[] splitter;
		int hours = 0;
		int minutes = 0;
		boolean hasSeparator = value.contains(":");
		boolean hasDecimal = value.contains(".") || value.contains(",");

		try {

			if (!hasSeparator && !hasDecimal) {
				minutes = Integer.valueOf(StringUtils.right(value, 2).trim());
				if (value.length() > 2) {
					hours = Integer.valueOf(value.substring(0, value.length() - 2));
				}
			} else if (hasSeparator && !hasDecimal) {
				splitter = value.split(":");
				hours = Integer.valueOf(splitter[0]);
				minutes = Integer.valueOf(splitter[1]);
			} else if (!hasSeparator && hasDecimal) {
				if (value.indexOf(".") == 0 || value.indexOf(",") == 0) {
					value = "0" + value;
				}
				int indexOfDecimal = value.indexOf(".");
				hours = Integer.valueOf(value.substring(0, indexOfDecimal));
				minutes = Integer.valueOf(value.substring(indexOfDecimal + 1)) * 60 / 100;
			}

			if (minutes > 59) {
				minutes = 59;
			}

		} catch (NumberFormatException ne) {
			hours = 0;
			minutes = 0;
		}

		return hours * 3600l + minutes * 60l;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Long value) {
		BigDecimal seconds = BigDecimal.valueOf(value);
		BigDecimal var3600 = new BigDecimal("3600");
		BigDecimal var60 = new BigDecimal("60");

		BigDecimal hours = seconds.divide(var3600, BigDecimal.ROUND_FLOOR);
		BigDecimal myremainder = seconds.remainder(var3600);
		BigDecimal minutes = myremainder.divide(var60, BigDecimal.ROUND_FLOOR);

		String minutesString = StringUtils.leftPad(String.valueOf(minutes), 2, "0");
		return hours + ":" + minutesString;
	}

}