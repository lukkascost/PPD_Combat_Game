package MainPackage;


import RMI.ChatImplementation;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DNSRun {
    public static void main(String[] args) throws IOException {
        ChatImplementation obj = new ChatImplementation();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost/Chat",obj);
    }
}
