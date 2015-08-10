package pl.ljader.test.cxf.base64;

import java.io.IOException;

import javax.jws.WebService;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.example.byteoperationsservice.ByteOperationsPortType;
import org.example.byteoperationsservice.CountBytesRequestType;
import org.example.byteoperationsservice.CountBytesResponseType;

@WebService(targetNamespace = "http://www.example.org/ByteOperationsService", portName = "ByteOperationsPort",
serviceName = "ByteOperationsService",
endpointInterface = "org.example.byteoperationsservice.ByteOperationsPortType",
wsdlLocation = "wsdl/ByteOperationsService.wsdl")
public class ByteOperationsImpl implements ByteOperationsPortType {
	private final Logger LOG = Logger.getLogger(getClass());

	@Override
	public CountBytesResponseType countBytes(CountBytesRequestType request) {
		LOG.info("START countBytes");
		byte[] bytes = null;
		try {
			// I don't worry about Java Heap Space exception here, since the client is problem for me
			bytes = IOUtils.toByteArray(request.getBytesToCount().getInputStream());
		} catch (IOException ioe) {
			LOG.warn("error reading bytes to count:", ioe);
		}
		int countedSize = bytes.length;
		LOG.info("STOP countBytes.size:" + countedSize);
		
		CountBytesResponseType resp = new CountBytesResponseType();
		resp.setSizeInBytes(countedSize);

		return resp;
	}

}
