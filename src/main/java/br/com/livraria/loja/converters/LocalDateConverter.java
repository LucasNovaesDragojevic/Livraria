package br.com.livraria.loja.converters;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter<Object>
{

    private DateTimeConverter converter = new DateTimeConverter();

    public LocalDateConverter() 
    {
        converter.setPattern("dd/MM/yyyy");
        converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) 
	{
		if (value == null || value.isEmpty())
		{
			 return null;
		}
		LocalDate localDate = null;
		try
		{
			localDate = LocalDate.parse(value.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.of("America/Sao_Paulo")));
		}
		catch(DateTimeParseException e)
		{
			throw new ConverterException("O formato da data deve ser DD/MM/AAAA.");
		}
		return localDate;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) 
	{
		if (value == null) 
		{
			return "";
		}

		return ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.of("America/Sao_Paulo")));
	}
}
