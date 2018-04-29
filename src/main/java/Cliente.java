
import Agenda.*;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.*;


public class Cliente {

    public static void main(String args[]) {
        try {
            ContactsNode contactsNode1;
            ContactsNode contactsNode2;
            ContactsNode contactsNode3;
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object obj = null;

            obj = orb.resolve_initial_references("NameService");

            NamingContext naming = NamingContextHelper.narrow(obj);
                NameComponent[] name1 = {new NameComponent("Agenda1", "Exemplo")};
                NameComponent[] name2 = {new NameComponent("Agenda2", "Exemplo")};
                NameComponent[] name3 = {new NameComponent("Agenda3", "Exemplo")};

                org.omg.CORBA.Object objRef1 = naming.resolve(name1);
                org.omg.CORBA.Object objRef2 = naming.resolve(name2);
                org.omg.CORBA.Object objRef3 = naming.resolve(name3);

                contactsNode1 = ContactsNodeHelper.narrow(objRef1);
                contactsNode2 = ContactsNodeHelper.narrow(objRef2);
                contactsNode3 = ContactsNodeHelper.narrow(objRef3);

                contactsNode1.addContact(new Contact("lucas1","085997667126"));
                contactsNode1.addContact(new Contact("lucas2","085997667126"));
                contactsNode1.addContact(new Contact("lucas3","085997667126"));

                System.out.println("ok");

        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (Agenda.user_exists user_exists) {
            user_exists.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }

    }

}
