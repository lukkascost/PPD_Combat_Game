import Agenda.*;
import Agenda.ContactsNodePackage.unknown_user;
import Agenda.ContactsNodePackage.user_exists;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import java.util.Hashtable;
import java.util.Set;

public  class ContactsNodeImpl extends ContactsNodePOA{
    public Hashtable data = new Hashtable();

    public ContactsNodeImpl(String instanceName) {
        if(instanceName.equals("Agenda1")){
            getIsAlive("Agenda2");
            if(!data.isEmpty()) return;
            getIsAlive("Agenda3");
            return;
        }
        if(instanceName.equals("Agenda2")){
            getIsAlive("Agenda3");
            if(!data.isEmpty()) return;
            getIsAlive("Agenda1");
            return;
        }
        if(instanceName.equals("Agenda3")){
            getIsAlive("Agenda2");
            if(!data.isEmpty()) return;
            getIsAlive("Agenda1");
            return;
        }
    }

    public Contact[] data() {
        if (data.isEmpty())return new Contact[0];

        Contact[] contacts = new Contact[this.data.size()];
        Set<String> keys = data.keySet();
        int i = 0;
        for(String key: keys){
            contacts[i] = new Contact(key,data.get(key).toString());
            i++;
        }
        return contacts;    }


    public void data(Contact[] newData) {
        this.data= new Hashtable();
        for (Contact c: newData) {
            data.put(c.name,c.number);
        }
    }

    public boolean addContact(Contact contact) throws user_exists {
        if (data.containsKey(contact.name)) throw new user_exists(contact);
        data.put(contact.name,contact.number);
        setIsAlive("Agenda1", data());
        setIsAlive("Agenda2", data());
        setIsAlive("Agenda3", data());

        return true;
    }

    public boolean deleteContact(Contact contact) throws unknown_user {
        if (!data.containsKey(contact.name)) throw new unknown_user(contact);
        data.remove(contact.name);

        setIsAlive("Agenda1", data());
        setIsAlive("Agenda2", data());
        setIsAlive("Agenda3", data());

        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) throws unknown_user {
        if (!data.containsKey(oldContact.name)) throw new unknown_user(oldContact);
        data.replace(oldContact.name,oldContact.number,newContact.number);

        setIsAlive("Agenda1", data());
        setIsAlive("Agenda2", data());
        setIsAlive("Agenda3", data());
        return true;
    }
    public String searchContact(String name) throws unknown_user {
        if (!data.containsKey(name)) throw new unknown_user(new Contact(name,""));

        return (String) this.data.get(name);
    }

    public void setIsAlive(String node, Contact[] data ){
        ORB orb = ORB.init(new String[0], null);
        org.omg.CORBA.Object obj = null;

        try {
            obj = orb.resolve_initial_references("NameService");
        } catch (InvalidName invalidName) {
            return;
        }

        NamingContext naming = NamingContextHelper.narrow(obj);
        NameComponent[] name = {new NameComponent(node, "Exemplo")};
        org.omg.CORBA.Object objRef = null;
        try {
            objRef = naming.resolve(name);
            ContactsNode objectRemote = ContactsNodeHelper.narrow(objRef);
            objectRemote.data(data);
        } catch (Exception notFound) {
            return;
        }
    }
    public void getIsAlive(String node ){
        ORB orb = ORB.init(new String[0], null);
        org.omg.CORBA.Object obj = null;

        try {
            obj = orb.resolve_initial_references("NameService");
        } catch (InvalidName invalidName) {
            return;
        }

        NamingContext naming = NamingContextHelper.narrow(obj);
        NameComponent[] name = {new NameComponent(node, "Exemplo")};
        org.omg.CORBA.Object objRef = null;
        try {
            objRef = naming.resolve(name);
            ContactsNode objectRemote = ContactsNodeHelper.narrow(objRef);
            this.data(objectRemote.data());
        } catch (Exception notFound) {
            return;
        }
    }
}
