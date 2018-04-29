import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Server2 {
    public static void main(String[] args) {
        try{
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objPoa = orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(objPoa);
            org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
            NamingContext naming = NamingContextHelper.narrow(obj);
            ContactsNodeImpl ag1 = new ContactsNodeImpl("Agenda2");
            org.omg.CORBA.Object objRef1 = rootPOA.servant_to_reference(ag1);
            NameComponent[] name1 = {new NameComponent("Agenda2", "Exemplo")};
            naming.rebind(name1, objRef1);
            rootPOA.the_POAManager().activate();

            Thread.currentThread().sleep(5000);
            System.out.println("Server2 Pronto ...");
            orb.run();

        }catch(Exception ex)
        {
            System.out.println("Erro");
            ex.printStackTrace();
        }
    }
}
