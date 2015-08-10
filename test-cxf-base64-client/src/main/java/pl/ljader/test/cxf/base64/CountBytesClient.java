package pl.ljader.test.cxf.base64;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.example.byteoperationsservice.ByteOperationsPortType;
import org.example.byteoperationsservice.ByteOperationsService;
import org.example.byteoperationsservice.CountBytesRequestType;
import org.example.byteoperationsservice.CountBytesResponseType;

public class CountBytesClient {
	private static final Logger LOG = Logger.getLogger(CountBytesClient.class);

	private static final QName SERVICE_NAME = new QName("http://www.example.org/ByteOperationsService",
			"ByteOperationsService");

	public static void main(String args[]) throws java.lang.Exception {
		LOG.info("CountBytesClient.Starting...");

		URL wsdlURL = ByteOperationsService.WSDL_LOCATION;
		if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
			File wsdlFile = new File(args[0]);
			try {
				if (wsdlFile.exists()) {
					wsdlURL = wsdlFile.toURI().toURL();
				} else {
					wsdlURL = new URL(args[0]);
				}
			} catch (MalformedURLException e) {
				LOG.error("error", e);
			}
		}

		ByteOperationsService ss = new ByteOperationsService(wsdlURL, SERVICE_NAME);
		ByteOperationsPortType port = ss.getByteOperationsPort();

		CountBytesRequestType req = simpleRequest();
		CountBytesResponseType resp = port.countBytes(req);

		LOG.info("CountBytesClient.SizeInBytes=" + resp.getSizeInBytes());
	}

	protected static CountBytesRequestType simpleRequest() throws UnsupportedEncodingException {
//		InputStream repeatedRowsStream = new RepeatedRowsInputStream(10);
		InputStream repeatedRowsStream = new RepeatedRowsInputStream(1024 * 1024);
		//ERROR: Java Heap Space
//		InputStream repeatedRowsStream = new RepeatedRowsInputStream(32 * 1024 * 1024);
		DataSource dataSource = new InputStreamDataSource(repeatedRowsStream );
		
		CountBytesRequestType req = new CountBytesRequestType();
		req.setBytesToCount(new DataHandler(dataSource));

		return req;
	}
}
