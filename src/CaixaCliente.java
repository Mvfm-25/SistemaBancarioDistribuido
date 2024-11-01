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
            AgenciaInterface agencia = (AgenciaInterface) Naming.lookup("//" + args[0] + "/Agencia");

            agencia.abrirConta(01, "John");
            agencia.abrirConta(02, "Optimus");
            agencia.depositar(02, 1984);

            System.out.print("Digite o número da conta: ");
            Integer numeroConta = teclado.nextInt();
            System.out.print("Digite o valor para depositar");
            double valor = teclado.nextDouble();



            CaixaInterface caixa = new Caixa();
            boolean loginSucesso = caixa.depositar(numeroConta, valor, agencia);

            System.out.println("Conta : " + agencia.getNomeConta(numeroConta));
            System.out.println("Saldo atual : " + agencia.consultarSaldo(numeroConta));

            agencia.sacar(02, 500);
            System.out.println("Conta : " + agencia.getNomeConta(02));
            System.out.println("Saldo atual : " + agencia.consultarSaldo(02));

        } catch (Exception e) {
            System.out.println("Erro ao conectar com o servidor de Agência.");
            e.printStackTrace();
        }

        teclado.close();
    }
}
