import java.rmi.Naming;
import java.rmi.RemoteException;

public class CaixaCliente {

    public static void main(String[] args) {

        int cont = 0;
        int contMax = 3;

        if (args.length != 1) {
            System.out.println("Uso: java CaixaCliente <hostname>");
            System.exit(1);
        }

        try {
            System.out.println("Conectando com CaixaInterface...");
            CaixaInterface caixa = (CaixaInterface) Naming.lookup("//" + args[0] + "/Caixa");

            // Consultar saldo da conta 01
            System.out.println("Consultando saldo de 01... Saldo: " + caixa.consultarSaldo(1));

            // Tentativa de depósito com lógica de repetição e controle de exceções
            while (cont < contMax) {
                try {
                    System.out.println("Depositando R$200 em 01... " + caixa.depositar(1, 200));
                    break; // Se a operação for bem-sucedida, sair do loop
                } catch (RemoteException e) {
                    System.out.println("Erro ao depositar. Tentando novamente...");
                    e.printStackTrace();
                    Thread.sleep(100 * (cont + 1)); // Aumenta o tempo de espera a cada tentativa
                    cont++;
                    if (cont >= contMax) {
                        System.out.println("Tentativas máximas alcançadas. Falha ao depositar.");
                        break;
                    }
                }
            }
            System.out.println("Consultando saldo de 01... Saldo: " + caixa.consultarSaldo(1));

            // Consultar saldo da conta 02
            System.out.println("Consultando saldo de 02... Saldo: " + caixa.consultarSaldo(2));
            cont = 0; // Reiniciar o contador de tentativas
            while (cont < contMax) {
                try {
                    System.out.println("Retirando R$300... " + caixa.sacar(2, 300));
                    break;
                } catch (RemoteException e) {
                    System.out.println("Erro ao sacar. Tentando novamente...");
                    e.printStackTrace();
                    Thread.sleep(100 * (cont + 1)); // Aumenta o tempo de espera a cada tentativa
                    cont++;
                    if (cont >= contMax) {
                        System.out.println("Tentativas máximas alcançadas. Falha ao sacar.");
                        break;
                    }
                }
            }
            System.out.println("Consultando saldo de 02... Saldo: " + caixa.consultarSaldo(2));

        } catch (Exception e) {
            System.out.println("CaixaCliente falhou...");
            e.printStackTrace();
        }
    }
}
