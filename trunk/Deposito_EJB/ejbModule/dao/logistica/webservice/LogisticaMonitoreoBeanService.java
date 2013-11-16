package dao.logistica.webservice;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.6 2013-11-15T21:09:09.024-03:00
 * Generated source version: 2.4.6
 * 
 */
@WebServiceClient(name = "LogisticaMonitoreoBeanService", targetNamespace = "http://webservice/")
public class LogisticaMonitoreoBeanService extends Service {

	public final static QName SERVICE = new QName("http://webservice/", "LogisticaMonitoreoBeanService");
	public final static QName LogisticaMonitoreoWSPort = new QName("http://webservice/", "LogisticaMonitoreoWSPort");

	public LogisticaMonitoreoBeanService(URL wsdlLocation) {
		super(wsdlLocation, SERVICE);
	}

	// public LogisticaMonitoreoBeanService(URL wsdlLocation, QName serviceName)
	// {
	// super(wsdlLocation, serviceName);
	// }

	/**
	 * 
	 * @return returns LogisticaMonitoreoWS
	 */
	@WebEndpoint(name = "LogisticaMonitoreoWSPort")
	public LogisticaMonitoreoWS getLogisticaMonitoreoWSPort() {
		return super.getPort(LogisticaMonitoreoWSPort, LogisticaMonitoreoWS.class);
	}

	/**
	 * 
	 * @param features
	 *            A list of {@link javax.xml.ws.WebServiceFeature} to configure
	 *            on the proxy. Supported features not in the
	 *            <code>features</code> parameter will have their default
	 *            values.
	 * @return returns LogisticaMonitoreoWS
	 */
	@WebEndpoint(name = "LogisticaMonitoreoWSPort")
	public LogisticaMonitoreoWS getLogisticaMonitoreoWSPort(WebServiceFeature... features) {
		return super.getPort(LogisticaMonitoreoWSPort, LogisticaMonitoreoWS.class, features);
	}

}
