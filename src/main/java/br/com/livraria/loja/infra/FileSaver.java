package br.com.livraria.loja.infra;

import java.io.IOException;
import java.time.ZonedDateTime;

import javax.servlet.http.Part;

public class FileSaver 
{
	private static final String SERVER_PATH = "C:\\Livraria";
	
	public String write(Part file, String path)
	{
		String relativePath = (path + "/" + ZonedDateTime.now().toInstant().toEpochMilli() + "-" + file.getSubmittedFileName()).replace(" ", "-");
		try 
		{
			file.write(SERVER_PATH + "/" + relativePath);
			return relativePath;
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		}
	}
}
