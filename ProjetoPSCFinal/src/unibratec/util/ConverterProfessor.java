package unibratec.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.unibratec.basica.Professor;
import br.com.unibratec.fachada.Fachada;
import br.com.unibratec.fachada.IFachada;

@FacesConverter(value = "converterProfessor", forClass = Professor.class)
public class ConverterProfessor implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		IFachada fachada = Fachada.getInstance();
		return fachada.consultarProfessorPorId(Integer.parseInt(string));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Professor professor = (Professor) object;
		return String.valueOf(professor.getCodigo());
	}
	
	

}
