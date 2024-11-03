import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class AgenciaCliente {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        if(args.length != 1){
            System.out.println("Uso : java AgenciaCliente <hostname>");
            System.exit(1);
        }

        try {
            AgenciaInterface iacon = (AgenciaInterface) Naming.lookup("//" +args[0] + "/Agencia");
            System.out.println("Criando clientes...");
            iacon.abrirConta(1, "Optimus");
            iacon.abrirConta(2, "Metroplex");
            System.out.println("Clientes criados!");

        } catch (Exception e) {
            System.out.println("AgenciaCliente falhou...");
            e.printStackTrace();
        }
    }
}
