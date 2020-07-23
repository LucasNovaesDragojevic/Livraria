package br.com.livraria.loja.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.time.ZonedDateTime;

import javax.servlet.http.Part;

import lombok.Getter;

@Getter
public class FileSaver 
{
	private final String serverPath = "C:\\Livraria";
	
	public String write(Part file, String path)
	{
		String relativePath = (path + "/" + ZonedDateTime.now().toInstant().toEpochMilli() + "-" + file.getSubmittedFileName()).replace(" ", "-");
		try 
		{
			file.write(serverPath + "/" + relativePath);
			return relativePath;
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		}
	}

	public static void transfer(Path source, OutputStream outputStream) 
	{
		try 
		{
			FileInputStream input = new FileInputStream(source.toFile());
			try (	ReadableByteChannel inputChannel = Channels.newChannel(input);
					WritableByteChannel outputChannel = Channels.newChannel(outputStream);
				)
			{
				ByteBuffer buffer = ByteBuffer.allocate(1024 * 10);
				while (inputChannel.read(buffer) != -1)
				{
					buffer.flip();
					outputChannel.write(buffer);
					buffer.clear();
				}
			}
			catch (IOException e) 
			{
				throw new RuntimeException(e);
			}
		} 
		catch (FileNotFoundException e) 
		{
			throw new RuntimeException(e);
		}
	}
}
