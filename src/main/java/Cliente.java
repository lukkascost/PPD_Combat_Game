
import Agenda.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;


public class Cliente {
    static String connectedIn = "No_Nodes";
    static ContactsNode contactsNode = null;
    static final String[] nodeNames = new String[]{"Agenda1","Agenda2", "Agenda3"};


    public static void main(String args[]) {
        selectNode();
        Contact contato1 = new Contact("lucas","997667126");
        for(Contact c:contactsNode.data()){
            System.out.printf(c.name+" - "+c.number);
        }
        try {
            contactsNode.addContact(contato1);
        } catch (Agenda.user_exists user_exists) {
            System.out.println("Contato já existe: "+ user_exists.contact.name+ " - "+user_exists.contact.number);
        }
        System.out.printf(connectedIn);
    }
    public static void selectNode(){
        for(String node : nodeNames) {
            try {
                ORB orb = ORB.init(new String[0], null);
                org.omg.CORBA.Object obj = null;
                obj = orb.resolve_initial_references("NameService");
                NamingContext naming = NamingContextHelper.narrow(obj);
                NameComponent[] name = {new NameComponent(node, "Exemplo")};
                org.omg.CORBA.Object objRef = null;
                objRef = naming.resolve(name);
                contactsNode = ContactsNodeHelper.narrow(objRef);
                contactsNode.data();
                connectedIn = node;
                break;
            } catch (Exception notFound) {
                connectedIn = "No_Nodes";
            }
        }
    }

}
