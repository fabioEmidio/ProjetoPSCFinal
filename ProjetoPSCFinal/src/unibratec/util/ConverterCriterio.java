package unibratec.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.unibratec.basica.Criterio;
import br.com.unibratec.fachada.Fachada;
import br.com.unibratec.fachada.IFachada;

@FacesConverter(value = "converterCriterio", forClass = Criterio.class)
public class ConverterCriterio implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		IFachada fachada = Fachada.getInstance();
		return fachada.consultarCriterioPorId(Integer.parseInt(string));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Criterio criterio = (Criterio) object;
		return String.valueOf(criterio.getCodigo());
	}

}
