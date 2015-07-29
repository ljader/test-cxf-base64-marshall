package pl.ljader.test.cxf.base64;

import javax.jws.WebService;

import org.example.byteoperationsservice.ByteOperationsPortType;
import org.example.byteoperationsservice.CountBytesRequestType;
import org.example.byteoperationsservice.CountBytesResponseType;

@WebService(targetNamespace = "http://www.example.org/ByteOperationsService", portName = "ByteOperationsPort",
serviceName = "ByteOperationsService",
endpointInterface = "org.example.byteoperationsservice.ByteOperationsPortType",
wsdlLocation = "wsdl/ByteOperationsService.wsdl")
public class ByteOperationsImpl implements ByteOperationsPortType {

	@Override
	public CountBytesResponseType countBytes(CountBytesRequestType request) {
		CountBytesResponseType resp = new CountBytesResponseType();
		resp.setSizeInBytes(request.getBytesToCount().length);

		return resp;
	}

}
