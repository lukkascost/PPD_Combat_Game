package MainPackage;


import java.io.IOException;
import java.rmi.registry.LocateRegistry;

public class DNSRun {
    public static void main(String[] args) throws IOException {
        LocateRegistry.createRegistry(4040);
        while(true);
    }
}