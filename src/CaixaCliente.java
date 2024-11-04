import java.rmi.Naming;
import java.util.Scanner;

public class CaixaCliente {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        if (args.length != 1) {
            System.out.println("Uso: java CaixaCliente <hostname>");
            System.exit(1);
        }

        try {
            System.out.println("Conectando com CaixaInterface...");
            CaixaInterface caixa = (CaixaInterface) Naming.lookup("//" + args[0] + "/Caixa");

            System.out.println("Saldo da conta 01 : " + caixa.consultarSaldo(1));
            System.out.println("Depositando R$200... " + caixa.depositar(1, 200));
            System.out.println("Saldo da conta 01 : " + caixa.consultarSaldo(1));

            System.out.println("Retirando R$200..." + caixa.sacar(1, 200));
            System.out.println("Saldo da conta 01 : " + caixa.consultarSaldo(1));
            System.out.println("Retirando R$400... : " + caixa.sacar(1, 400));
            System.out.println("Depositando -R$400 na conta 01..." + caixa.depositar(1, -400));
            System.out.println("Saldo da conta 01 : " + caixa.consultarSaldo(1));

            System.out.println("Depositando R$300 na conta 03..." + caixa.depositar(3, 300));

        } catch (Exception e) {
            System.out.println("CaixaCliente falhou...");
            e.printStackTrace();
        }
    }
}
