package MainPackage;

import WebService.IChatServiceImpl;

import javax.xml.ws.Endpoint;

public class Publicador {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ChatQueue", new IChatServiceImpl());
    }
}