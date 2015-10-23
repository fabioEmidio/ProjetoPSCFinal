package unibratec.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterDatas")
public class ConverterDatas extends DateTimeConverter {

	public ConverterDatas(){
		setPattern("dd/MM/yyyy");
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.length() != getPattern().length()) {
			throw new ConverterException();
		}
		return super.getAsObject(context, component, value);
	}
}
