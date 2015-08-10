package pl.ljader.test.cxf.base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

import org.apache.log4j.Logger;

public class InputStreamDataSource implements DataSource {
	private final Logger LOG = Logger.getLogger(getClass());
	private final InputStream inputStream;

	public InputStreamDataSource(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public String getContentType() {
		return "application/octet-stream";
	}

	@Override
	public InputStream getInputStream() throws IOException {
		LOG.info("START.getInputStream");
		return inputStream;
	}

	@Override
	public String getName() {
		return "InputStreamDataSource";
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		throw new RuntimeException("This DataSource does not handle returning OutputStream!");
	}

}
