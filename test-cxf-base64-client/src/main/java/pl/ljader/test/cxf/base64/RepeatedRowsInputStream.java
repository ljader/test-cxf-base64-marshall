package pl.ljader.test.cxf.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class RepeatedRowsInputStream extends InputStream {
	private final Logger LOG = Logger.getLogger(getClass());

	private int rows;

	private int rowNr = 0;
	private int colPos = 0;

	byte[] oneRow = null;

	public RepeatedRowsInputStream(int rows) {
		this.rows = rows;
	}

	@Override
	public int read() throws IOException {
		if (oneRow == null || colPos - 1 == oneRow.length) {
			if (rowNr < rows)
				oneRow = generateRow();
			else
				return -1;
		}

		int toReturn = 10;
		if (colPos < oneRow.length) {
			toReturn = oneRow[colPos++] & 0xff;
		} else {
			colPos++;
		}

		if (LOG.isTraceEnabled()) {
			LOG.trace("toReturn <" + toReturn + ">");
		}
		return toReturn;
	}

	private byte[] generateRow() throws IOException {
		int tenPercent = rows / 10;
		if ((rowNr % tenPercent) == 0) {
			LOG.info("generateRow - rows done:"  + rowNr);
		}
		
		rowNr++;
		colPos = 0;
		byte[] outArray = null;

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			String oneRow = "lorem_ipsum_".concat(Long.toString(System.currentTimeMillis()));
			baos.write(oneRow.getBytes("UTF-8"));
			outArray = baos.toByteArray();
		}

		return outArray;
	}

}
