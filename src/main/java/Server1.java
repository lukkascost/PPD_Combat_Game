import Agenda.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Server1 {
    public static void main(String[] args) {
        try{
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objPoa = orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(objPoa);
            org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
            NamingContext naming = NamingContextHelper.narrow(obj);
            ContactsNodeImpl ag1 =  new  ContactsNodeImpl("Agenda1");
            org.omg.CORBA.Object objRef1 = rootPOA.servant_to_reference(ag1);
            NameComponent[] name1 = {new NameComponent("Agenda1", "Exemplo")};
            naming.rebind(name1, objRef1);
            rootPOA.the_POAManager().activate();
            Thread.currentThread().sleep(5000);
            System.out.println("Server1 Pronto ...");
            orb.run();
        }catch(Exception ex)
        {
            System.out.println("Erro");
            ex.printStackTrace();
        }
    }
}
