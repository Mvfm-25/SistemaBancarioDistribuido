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
            if(iacon.abrirConta(1, "Optimus")){
                System.out.println("Cliente 01 criado!");
            } else {
                System.out.println("Falha ao criar o cliente 01...");
            }
            if(iacon.abrirConta(2, "Ultra Magnus")){
                System.out.println("Cliente 02 criado!");
            } else  {
                System.out.println("Falha ao criar cliente 02...");
            }
            if(iacon.abrirConta(1, "Tarn")){
                System.out.println("A criação de outro cliente 01 não deveria estar funcionando... Verificar código!");
            } else {
                System.out.println("Negada a criação da conta para Tarn!");
            }

        } catch (Exception e) {
            System.out.println("AgenciaCliente falhou...");
            e.printStackTrace();
        }
    }
}
