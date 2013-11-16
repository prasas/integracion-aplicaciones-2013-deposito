package wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-16T10:40:34.499-03:00
 * Generated source version: 2.4.6
 * 
 */
@WebServiceClient(name = "LogisticaMonitoreoBeanService", 
                  wsdlLocation = "file:/C:/DevProjects/integracionTPO/Deposito_EJB/ejbModule/dao/logistica/webservice/LogisticaMonitoreoWS.wsdl",
                  targetNamespace = "http://webservice/") 
public class LogisticaMonitoreoBeanService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webservice/", "LogisticaMonitoreoBeanService");
    public final static QName LogisticaMonitoreoWSPort = new QName("http://webservice/", "LogisticaMonitoreoWSPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/DevProjects/integracionTPO/Deposito_EJB/ejbModule/dao/logistica/webservice/LogisticaMonitoreoWS.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(LogisticaMonitoreoBeanService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/DevProjects/integracionTPO/Deposito_EJB/ejbModule/dao/logistica/webservice/LogisticaMonitoreoWS.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public LogisticaMonitoreoBeanService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public LogisticaMonitoreoBeanService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LogisticaMonitoreoBeanService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public LogisticaMonitoreoBeanService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public LogisticaMonitoreoBeanService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public LogisticaMonitoreoBeanService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns LogisticaMonitoreoWS
     */
    @WebEndpoint(name = "LogisticaMonitoreoWSPort")
    public LogisticaMonitoreoWS getLogisticaMonitoreoWSPort() {
        return super.getPort(LogisticaMonitoreoWSPort, LogisticaMonitoreoWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LogisticaMonitoreoWS
     */
    @WebEndpoint(name = "LogisticaMonitoreoWSPort")
    public LogisticaMonitoreoWS getLogisticaMonitoreoWSPort(WebServiceFeature... features) {
        return super.getPort(LogisticaMonitoreoWSPort, LogisticaMonitoreoWS.class, features);
    }

}
