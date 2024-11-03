import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CaixaCliente {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        if (args.length != 1) {
            System.out.println("Uso: java CaixaCliente <hostname>");
            System.exit(1);
        }

        try {
            CaixaInterface caixa = (CaixaInterface) Naming.lookup("//" + args[0] + "/Caixa");
            System.out.println("Saldo da conta 01 : " + caixa.consultarSaldo(1));
            System.out.println("Depositando R$200... " + caixa.depositar(1, 200));
            System.out.println("Saldo da conta 01 : " + caixa.consultarSaldo(1));

        } catch (Exception e) {
            System.out.println("NotasCliente falhou...");
            e.printStackTrace();
        }
    }
}