package unibratec.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import unibratec.basica.Aluno;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;

@FacesConverter(value = "converterAluno", forClass = Aluno.class)
public class ConverterAluno implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		IFachada fachada = Fachada.getInstance();
		return fachada.consultarAlunoPorId(Integer.parseInt(string));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Aluno aluno = (Aluno)object;
		return String.valueOf(aluno.getCodigo());
	}

}
