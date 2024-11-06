import java.rmi.Naming;
import java.rmi.RemoteException;

public class AgenciaCliente {

    public static void main(String[] args) {

        int cont = 0;
        int contMax = 3;

        if (args.length != 1) {
            System.out.println("Uso : java AgenciaCliente <hostname>");
            System.exit(1);
        }

        try {
            AgenciaInterface iacon = (AgenciaInterface) Naming.lookup("//" + args[0] + "/Agencia");
            System.out.println("Criando clientes...");

            // Tentar criar conta 01 com lógica de repetição e controle de exceções
            while (cont < contMax) {
                try {
                    System.out.println("Criando conta 01... \nID = 1 nomeConta = 'Optimus' " + iacon.abrirConta(1, "Optimus"));
                    break;
                } catch (RemoteException e) {
                    System.out.println("Erro ao criar conta 01. Tentando novamente...");
                    e.printStackTrace();
                    Thread.sleep(100 * (cont + 1));
                    cont++;
                    if (cont >= contMax) {
                        System.out.println("Tentativas máximas alcançadas. Falha ao criar conta 01.");
                        break;
                    }
                }
            }

            // Tentar criar conta 02 e fazer depósito
            cont = 0;
            while (cont < contMax) {
                try {
                    System.out.println("Criando conta 02...\nID = 2 nomeConta = 'Ultra Magnus' " + iacon.abrirConta(2, "Ultra Magnus"));
                    System.out.println("Depositando R$1000 em 02... " + iacon.depositar(2, 1000));
                    break;
                } catch (RemoteException e) {
                    System.out.println("Erro ao criar conta 02 ou ao depositar. Tentando novamente...");
                    e.printStackTrace();
                    Thread.sleep(100 * (cont + 1));
                    cont++;
                    if (cont >= contMax) {
                        System.out.println("Tentativas máximas alcançadas. Falha ao criar conta 02.");
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("AgenciaCliente falhou...");
            e.printStackTrace();
        }
    }
}
