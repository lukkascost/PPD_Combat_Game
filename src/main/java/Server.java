import Agenda.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Server {

    public static void main(String[] args) {
        try{
            ORB orb = ORB.init(args,null);

            org.omg.CORBA.Object objPoa = orb.resolve_initial_references("RootPOA");

            POA rootPOA = POAHelper.narrow(objPoa);


            org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");

            NamingContext naming = NamingContextHelper.narrow(obj);

            ContactsNodeImpl ag1 = new ContactsNodeImpl();
            ContactsNodeImpl ag2 = new ContactsNodeImpl();
            ContactsNodeImpl ag3 = new ContactsNodeImpl();


            org.omg.CORBA.Object   objRef1 = rootPOA.servant_to_reference(ag1);
            org.omg.CORBA.Object   objRef2 = rootPOA.servant_to_reference(ag2);
            org.omg.CORBA.Object   objRef3 = rootPOA.servant_to_reference(ag3);


            NameComponent[] name1 = {new NameComponent("Agenda1","Exemplo")};
            NameComponent[] name2 = {new NameComponent("Agenda2","Exemplo")};
            NameComponent[] name3 = {new NameComponent("Agenda3","Exemplo")};

            naming.rebind(name1,objRef1);
            naming.rebind(name2,objRef2);
            naming.rebind(name3,objRef3);


            rootPOA.the_POAManager().activate();

            System.out.println("Server Pronto ...");

            orb.run();

        }catch (Exception ex){
            System.out.println("Erro");
            ex.printStackTrace();}
    }
}
