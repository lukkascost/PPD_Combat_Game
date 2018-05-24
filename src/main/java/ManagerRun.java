import Tup.Environment;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerRun {
    public static boolean running = true;
    static Scanner in = new Scanner(System.in);
    static final String line = new String(new char[48]).replace('\0', '-');
    static JavaSpace space;

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
                System.out.println("0 - Listar Ambiente;");
                System.out.println("1 - Criar Ambiente;");
                System.out.println("2 - Excluir Ambiente;");
                System.out.println("3 - Listar Usuario;");
                System.out.println("4 - Criar Usuario;");
                System.out.println("5 - Excluir Usuario;");
                System.out.println("5 - Mover Usuario;");
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

    public static void listEnvironment(){
        title("Lista de Ambientes");
        int envCount = 1;
        Environment env = new Environment();
        env.name = "amb"+envCount;
        Environment result;
        try {
            result = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
            while (result != null) {
                System.out.println(envCount + " - "+result.name);
                envCount++;
                env.name = "amb"+envCount;
                result = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
            }
        } catch (UnusableEntryException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void createEnvironment(){
        title("Criar Ambientes");
        int envCount = 1;
        Environment env = new Environment();
        env.name = "amb"+envCount;
        Environment result;
        try {
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

        } catch (UnusableEntryException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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

}
