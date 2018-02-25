import javax.xml.ws.Endpoint;

import service.AGENCE_REGULATION_COMMERCE;

public class ServeurWs {

	public static void main(String[] args) {
		
		Endpoint.publish("http://localhost:8585/", new AGENCE_REGULATION_COMMERCE());

	}

}