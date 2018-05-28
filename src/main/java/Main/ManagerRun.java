package Main;

import Tup.Device;
import Tup.Environment;
import Tup.User;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;
import org.apache.commons.lang3.StringUtils;

import javax.jws.soap.SOAPBinding;
import java.rmi.RemoteException;
import java.util.*;
import Tup.Lookup;

public class ManagerRun {
    public static boolean running = true;
    static Scanner in = new Scanner(System.in);
    static final String line = new String(new char[48]).replace('\0', '-');
    static JavaSpace space;
    static Hashtable<String ,Integer > enviroments = new Hashtable<String, Integer>();
    static Hashtable<String ,String > users = new Hashtable<String, String>();
    static Hashtable<String ,String > devices = new Hashtable<String, String>();


    public static void main(String[] args) {
        try {
            System.out.println("Procurando pelo servico JavaSpace...");
            Lookup finder = new Lookup(JavaSpace.class);
            space = (JavaSpace) finder.getService();
            if (space == null) {
                System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
                System.exit(-1);
            }
            System.out.println("O servico JavaSpace foi encontrado.");

            while (running){
                clear();
                title("Agenda de Contatos");
                System.out.println("00 - Listar Ambientes;");
                System.out.println("01 - Criar Ambiente;");
                System.out.println("02 - Excluir Ambiente;");
                System.out.println("03 - Listar Usuarios;");
                System.out.println("04 - Criar Usuario;");
                System.out.println("05 - Excluir Usuario;");
                System.out.println("06 - Mover Usuario;");
                System.out.println("07 - Listar Dispositivos;");
                System.out.println("08 - Criar Dispositivo;");
                System.out.println("09 - Excluir Dispositivo;");
                System.out.println("10 - Mover Dispositivo;");
                System.out.print("Escolha uma opcao: ");
                int i;
                try {
                    i = in.nextInt();
                    switch (i) {
                        case 0 :
                            listEnvironment();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break ;
                        case 1 :
                            createEnvironment();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break ;
                        case 2 :
                            deleteEnvironment();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break ;
                        case 3:
                            listUser();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break;
                        case 4 :
                            createUser();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break;
                        case 5:
                            deleteUser();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break;
                        case 6:
                            moveUser();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break;
                        case 7:
                            listDevice();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break;
                        case 8:
                            createDevice();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break;
                        case 9:
                            deleteDevice();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break;
                        case 10:
                            moveDevice();
                            System.out.print("Pressione enter para continuar.... ");
                            System.in.read();
                            break;
                    }
                }catch (InputMismatchException ex){
                    System.err.println("Digite um numero inteiro!!");
                    in.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteEnvironment() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        title("Remover Ambiente");
        System.out.print("Digite o nome do ambiente: ");
        String name = in.next();
        Environment env = new Environment();
        env.name = name;
        env = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
        if(env == null) {
            System.out.println(" Ambiente nao existe.");
            return;
        }
        User user = new User();
        user.environment = name;
        user = (User) space.readIfExists(user, null, Long.MAX_VALUE);
        if(user != null ){
            System.out.println(" Ambiente possui usuarios, mova-os antes de excluir.");
            return;
        }

        Device dev = new Device();
        dev.environment = name;
        dev = (Device) space.readIfExists(dev,null,Long.MAX_VALUE);
        if(dev != null){
            System.out.println(" Ambiente possui dispositivos, mova-os antes de excluir.");
            return;
        }
        env = new Environment();
        env.name = name;
        env = (Environment) space.take(env,null,Lease.FOREVER);
        System.out.println(" Ambiente excluido com sucesso!");



    }

    private static void deleteUser() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        title("Remover Usuario");
        System.out.print("Digite o nome do usuario: ");
        String name = in.next();

        User user = new User();
        user.name = name;
        user = (User) space.readIfExists(user, null, Long.MAX_VALUE);
        if(user == null ){
            System.out.println(" Usuario informado nao existe.");
            return;
        }

        user = new User();
        user.name = name;
        user = (User) space.take(user,null,Lease.FOREVER);
        System.out.println(" Usuario excluido com sucesso!");

    }

    private static void deleteDevice() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        title("Remover Dispositivo");
        System.out.print("Digite o nome do dispositivo: ");
        String name = in.next();

        Device dev = new Device();
        dev.name = name;
        dev = (Device) space.readIfExists(dev, null, Long.MAX_VALUE);
        if(dev == null ){
            System.out.println(" Dispositivo informado nao existe.");
            return;
        }

        dev = new Device();
        dev.name = name;
        dev = (Device) space.take(dev,null,Lease.FOREVER);
        System.out.println(" Dispositivo excluido com sucesso!");

    }

    public static void listEnvironment() throws RemoteException, TransactionException, InterruptedException, UnusableEntryException {
        title("Lista de Ambientes");
        getExistingEnvironments();
        int i = 1;
        for (String st:enviroments.keySet()) {
            System.out.println(i+"| "+st);
            i++;

        }

    }

    public static void listUser() throws RemoteException, TransactionException, InterruptedException, UnusableEntryException {
        title("Lista de Usuarios");
        getExistingUsers();
        int i = 1;
        for (String st:users.keySet()) {
            System.out.println(i+"| "+st+ " - Ambiente: "+users.getOrDefault(st,""));
            i++;

        }

    }

    public static void listDevice() throws RemoteException, TransactionException, InterruptedException, UnusableEntryException {
        title("Lista de Dispositivos");
        getExistingDevices();
        int i = 1;
        for (String st:devices.keySet()) {
            System.out.println(i+"| "+st+ " - Ambiente: "+devices.getOrDefault(st,""));
            i++;

        }

    }

    public static void createEnvironment() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        title("Criar Ambientes");
        int envCount = 1;
        Environment env = new Environment();
        env.name = "amb"+envCount;
        Environment result;
        result = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
        while (result != null) {
            envCount++;
            env.name = "amb"+envCount;
            result = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
        }
        result = new Environment();
        result.name = "amb"+envCount;
        result.chat = "";
        space.write(result,null,Lease.FOREVER);
        System.out.println("\t Ambiente Criado com sucesso ");
    }

    public static void createUser() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        title("Criar Usuario");
        int usrCount = 1;
        User user = new User();
        user.name = "user"+usrCount;
        User result;
        result = (User) space.readIfExists(user, null, Long.MAX_VALUE);

        while (result != null) {
            usrCount++;
            user.name = "user"+usrCount;
            result = (User) space.readIfExists(user, null, Long.MAX_VALUE);
        }
        result = new User();
        result.name = "user"+usrCount;

        Environment env;
        env = getEnvironmentFromKeyboard();
        result.environment = env.name;
        space.write(result,null,Lease.FOREVER);
        System.out.println("\t Usuario criado com sucesso ");


    }

    public static void createDevice() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        title("Criar Dispositivo");
        int devCount = 1;
        Device dev = new Device();
        dev.name = "disp"+devCount;
        Device result;
        result = (Device) space.readIfExists(dev, null, Long.MAX_VALUE);

        while (result != null) {
            devCount++;
            dev.name = "disp"+devCount;
            result = (Device) space.readIfExists(dev, null, Long.MAX_VALUE);
        }
        result = new Device();
        result.name = "disp"+devCount;

        Environment env;
        env = getEnvironmentFromKeyboard();
        result.environment = env.name;
        space.write(result,null,Lease.FOREVER);
        System.out.println("\t Dispositivo criado com sucesso ");


    }

    private static Environment getEnvironmentFromKeyboard() throws UnusableEntryException, TransactionException, InterruptedException, RemoteException {
        Environment env;
        String name;
        do {
            env = new Environment();
            System.out.print("Digite o nome do ambiente: ");
            name = in.next();
            env.name = name;
            env = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
            if(env == null){
                System.out.println("Ambiente incorreto, digite novamente.");
            }
        }while(env==null);
        return env;
    }

    public static void moveUser() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        title("Mover Usuario");
        System.out.print("Digite o nome do usuario: ");
        String name = in.next();

        User user = new User();
        user.name = name;
        user = (User) space.readIfExists(user, null, Long.MAX_VALUE);
        if(user == null ){
            System.out.println(" Usuario informado nao existe.");
            return;
        }
        System.out.print("Digite o nome do ambiente destino: ");
        name = in.next();

        Environment env = new Environment();
        env.name = name;
        if (name.equals(user.environment)){
            System.out.println(" Voce nao pode mover o usuario para o ambiente ao qual ele ja está inserido.");
            return;
        }
        env = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
        if(env == null) {
            System.out.println(" Ambiente nao existe.");
            return;
        }

        user = (User) space.takeIfExists(user,null,Long.MAX_VALUE);
        user.environment  = env.name;

        space.write(user,null, Lease.FOREVER);
        System.out.println("Usuario movido com sucesso!");

    }

    public static void moveDevice() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        title("Mover Dispositivo");
        System.out.print("Digite o nome do dispositivo: ");
        String name = in.next();

        Device dev = new Device();
        dev.name = name;
        dev = (Device) space.readIfExists(dev, null, Long.MAX_VALUE);
        if(dev == null ){
            System.out.println(" Dispositivo informado nao existe.");
            return;
        }
        System.out.print("Digite o nome do ambiente destino: ");
        name = in.next();

        Environment env = new Environment();
        env.name = name;
        if (name.equals(dev.environment)){
            System.out.println(" Voce nao pode mover o dispositivo para o ambiente ao qual ele ja está inserido.");
            return;
        }
        env = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
        if(env == null) {
            System.out.println(" Ambiente nao existe.");
            return;
        }

        dev = (Device) space.takeIfExists(dev,null,Long.MAX_VALUE);
        dev.environment  = env.name;

        space.write(dev,null, Lease.FOREVER);
        System.out.println("Dispositivo movido com sucesso!");

    }

    public static void clear(){
        for (int i = 0; i < 100; i++) {
            System.out.println("\t");
        }
    }

    public static void title(String titleString){
        System.out.println(line);
        System.out.println(StringUtils.center(titleString, 50));
        System.out.println(line);
    }

    public static void getExistingEnvironments() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        List<Environment> environmentList = new ArrayList<>();
        enviroments = new Hashtable<>();
        Environment result;
        do{
            result = new Environment();
            result = (Environment) space.takeIfExists(result, null, Long.MAX_VALUE);
            if (result != null){
                environmentList.add(result);
                enviroments.put(result.name,0);
            }
        }while (result!= null);

        for (Environment e:environmentList) {
            space.write(e,null, Lease.FOREVER);
        }


    }

    public static void getExistingUsers() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        List<User> userList = new ArrayList<>();
        users = new Hashtable<>();
        User result;
        do{
            result = new User();
            result = (User) space.takeIfExists(result, null, Long.MAX_VALUE);
            if (result != null){
                userList.add(result);
                users.put(result.name,result.environment);
            }
        }while (result!= null);

        for (User u :userList) {
            space.write( u ,null, Lease.FOREVER);
        }

    }

    public static void getExistingDevices() throws TransactionException, UnusableEntryException, RemoteException, InterruptedException {
        List<Device> deviceList = new ArrayList<>();
        devices = new Hashtable<>();
        Device result;
        do{
            result = new Device();
            result = (Device) space.takeIfExists(result, null, Long.MAX_VALUE);
            if (result != null){
                deviceList.add(result);
                devices.put(result.name,result.environment);
            }
        }
        while (result!= null);
        for (Device d : deviceList) {
            space.write( d ,null, Lease.FOREVER);
        }

    }

}