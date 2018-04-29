
import Agenda.Contact;
import Agenda.ContactsNode;
import Agenda.ContactsNodeHelper;
import Agenda.ContactsNodePackage.unknown_user;
import Agenda.ContactsNodePackage.user_exists;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.ORB;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Cliente {
    static String connectedIn = "No_Nodes";
    static ContactsNode contactsNode = null;
    static final String[] nodeNames = new String[]{"Agenda1","Agenda2", "Agenda3"};
    static final String line = new String(new char[48]).replace('\0', '-');
    static Scanner in = new Scanner(System.in);

    public static void main(String args[]) throws InterruptedException {
        selectNode();

        if(connectedIn.equals("No_Nodes")){
            System.err.println("Não existe instancia iniciada de agenda!!!");
            System.exit(0);
        }
        boolean exec = true ;
        while(exec && !connectedIn.equals("No_Nodes")) {
            clear();
            title("Agenda de Contatos");
            System.out.println("0 - Listar contatos;");
            System.out.println("1 - Procurar contato;");
            System.out.println("2 - Inserir Contato;");
            System.out.println("3 - Editar Contato;");
            System.out.println("4 - Excluir Contato;");
            System.out.println("5 - Encerrar");
            System.out.print("Escolha uma opcao: ");
            int i;
            try {
                i = in.nextInt();
                selectNode();
                clear();
                switch (i) {
                    case 0:
                        listContacts();
                        System.out.print("Pressione enter para continuar.... ");
                        System.in.read();
                        break;
                    case 1:
                        searchContacts();
                        System.out.print("Pressione enter para continuar.... ");
                        System.in.read();
                        break;
                    case 2:
                        addContact();
                        System.out.print("Pressione enter para continuar.... ");
                        System.in.read();
                        break;
                    case 3:
                        editContact();
                        System.out.print("Pressione enter para continuar.... ");
                        System.in.read();
                        break;
                    case 4:
                        deleteContact();
                        System.out.print("Pressione enter para continuar.... ");
                        System.in.read();
                        break;
                    case 5:
                        exec = false;
                        break;
                    default:
                        System.err.println("O numero digitado nao e uma opcao valida!");
                        break;
                }
            }catch (InputMismatchException ex){

                System.err.println("Digite um numero inteiro!!");
                in.next();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static synchronized void selectNode(){
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

    public static void addContact(){
        title("Adicionar novo");
        System.out.print("Digite o nome do contato: ");
        String name = in.next();
        System.out.print("Digite o numero do contato: ");
        String number = in.next();
        try {
            contactsNode.addContact(new Contact(name,number));
            System.out.println("USUARIO CADASTRADO COM SUCESSO!!!");
        } catch (Agenda.ContactsNodePackage.user_exists user_exists) {
            System.err.println("Outro usuario com mesmo nome ja cadastrado!!!");
        }

    }

    public static void deleteContact(){
        title("Deletar Contato");
        System.out.print("Digite o nome do contato: ");
        String name = in.next();
        try {
            contactsNode.deleteContact(new Contact(name,""));
            System.out.println("USUARIO EXCLUIDO COM SUCESSO!!!");
        } catch (Agenda.ContactsNodePackage.unknown_user unknown_user) {
            System.err.println("O usuario informado nao existe!!!");
        }
    }
    public static void listContacts(){
        title("Lista de Contatos");
        int i = 1;
        for (Contact c :contactsNode.data()){
            System.out.println(i+"| "+c.name+" - "+c.number);
            i++;
        }

    }
    public static void searchContacts(){
        System.out.print("Digite o nome do contato: ");
        String name = in.next();
        try {
            String number = contactsNode.searchContact(name);
            title("Usuarios encontrados");
            System.out.println( "\t"+name+ " - "+number);
        } catch (Agenda.ContactsNodePackage.unknown_user unknown_user) {
            System.err.println("O usuario procurado nao existe!!!");
        }
    }

    public static void editContact(){
        title("Edicao de Contatos");
        System.out.print("Digite o nome do contato a ser editado: ");
        String oldName = in.next();
        System.out.print("Digite o novo numero do contato: ");
        String number = in.next();

        try {
            contactsNode.updateContact(new Contact(oldName,""),new Contact(oldName,number));
        } catch (Agenda.ContactsNodePackage.unknown_user unknown_user) {
            System.err.println("O usuario informado nao existe!!!");
        }
    }

    public static void clear(){
        for (int i = 0; i < 100; i++) {
            System.out.println("\t");
        }
    }

    public static void title(String titleString){
        System.out.println(line + "   - "+connectedIn);
        System.out.println(StringUtils.center(titleString, 50));
        System.out.println(line);
    }
}
