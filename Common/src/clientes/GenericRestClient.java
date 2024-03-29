package clientes;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GenericRestClient {

	private String ip;
	private String port;
	private String metodo;

	public GenericRestClient(){}
	
	public GenericRestClient(String ip, String port, String metodo) {
		this.ip = ip;
		this.port = port;
		this.metodo = metodo;
	}

	public String enviar(String mensaje) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://" + ip + ":" + 8080 +  metodo);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(new StringEntity(mensaje));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String respuesta = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		response.close();

		return respuesta;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
}
