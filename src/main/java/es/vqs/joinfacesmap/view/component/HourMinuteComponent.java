package es.vqs.joinfacesmap.view.component;

import java.io.IOException;
import java.math.BigDecimal;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.commons.lang3.StringUtils;

@FacesComponent(createTag = true, tagName = "hourMinuteComponent", namespace = "http://example.com/tags")
public class HourMinuteComponent extends UIComponentBase {
	
	@Override
	public String getFamily() {
		return "Greeting";
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		BigDecimal seconds = BigDecimal.valueOf((Long) this.getAttributes().get("value"));
		BigDecimal var3600 = new BigDecimal("3600");
		BigDecimal var60 = new BigDecimal("60");

		
		BigDecimal hours = seconds.divide(var3600, BigDecimal.ROUND_FLOOR);
		BigDecimal myremainder = seconds.remainder(var3600);
		BigDecimal minutes = myremainder.divide(var60, BigDecimal.ROUND_FLOOR);
		
		String minutesString = StringUtils.leftPad(String.valueOf(minutes), 2, "0");

		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("h:outputText", this);
		writer.write(hours + ":" + minutesString);
		writer.endElement("h:outputText");
	}
}