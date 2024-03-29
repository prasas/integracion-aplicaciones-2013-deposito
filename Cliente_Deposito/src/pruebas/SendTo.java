package pruebas;

import javax.jms.JMSException;

import clientes.GenericQueueClient;
import clientes.GenericRestClient;

public class SendTo {
	
	static String portal = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><articulo><codigo>1000010</codigo><codigoDeposito>6</codigoDeposito><nombre>test</nombre><descripcion>un articulo</descripcion><marca>Una Marca</marca><origen>Argentina</origen><precio>0.001</precio><tipo>Infantil</tipo><fecha>2013-11-16 10:10:10</fecha><fotoURL>http://www.google.com.ar</fotoURL><color></color><edadRecomendada>15-90</edadRecomendada><fichaTecnica></fichaTecnica><talle></talle><material></material></articulo>";
	static String despacho = "<articulo><codigo>1000010</codigo><nombre>test</nombre><idModulo>6</idModulo></articulo>";
	static String monitoreo ="<log><fecha>2013-11-16 10:10:10</fecha><idModulo>6</idModulo><mensaje>Este es un mensaje g6</mensaje></log>";		
	static String despachoRest ="{ idModulo:6, idSolicitud:1000, items: [ { codigo:1, cantidad: 100 }, {codigo:2, cantidad: 200} ] }";		
	
	
	

	public static void main(String[] args) {

		String mensajePortal = portal;
		String mensajeDespacho = despacho;
		String mensajeMonitoreo = monitoreo;
		String mensajeDespachoRest = despachoRest;
		//enviarMensajePrueba(mensajePortal, "/queue/deposito",,"deposito","deposito1234");
//		enviarMensajePrueba(mensajeMonitoreo,"jms/queue/log", "172.16.174.43","log","log1234");
//		enviarMensajePrueba(mensajeDespacho, "jms/queue/nuevosArticulos","172.16.174.38","despacho","despacho123");
//	"http://172.16.174.38:8080/tpia_despacho-site/rest/despachador/recibirArticulos")
		enviarMensajeRest("172.16.174.38","8080","tpia_despacho-site/rest/despachador/recibirArticulos",mensajeDespachoRest);
	}
	
	private static void enviarMensajePrueba(String mensaje,String queueName, String ip, String user, String pass) {
		try {
			GenericQueueClient cola = new GenericQueueClient(queueName, ip, "4447", user, pass);
			cola.enviar(mensaje);
			cola.cerrarConexion();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void enviarMensajeRest(String ip, String port, String metodo, String mensaje) {
		GenericRestClient client = new GenericRestClient(ip, port, metodo);
		try {
			String respuesta = client.enviar(mensaje);
			System.out.println(respuesta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
