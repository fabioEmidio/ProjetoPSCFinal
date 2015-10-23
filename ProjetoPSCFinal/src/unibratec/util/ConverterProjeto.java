package unibratec.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.unibratec.basica.Projeto;
import br.com.unibratec.fachada.Fachada;
import br.com.unibratec.fachada.IFachada;

@FacesConverter(value = "converterProjeto", forClass = Projeto.class)
public class ConverterProjeto implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		IFachada fachada = Fachada.getInstance();
		return fachada.consultarProjetoPorId(Integer.parseInt(string));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Projeto projeto = (Projeto) object;
		return String.valueOf(projeto.getCodigo());
	}

}
