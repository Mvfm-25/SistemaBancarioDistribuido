//Sistema Bancário Distribuído v.0007
//C: MVFM       UA:061124

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.ServiceLoader;

public class CaixaCliente {

    public static void main(String[] args) {

        int cont = 0;
        int contMax = 3;

        if (args.length != 1) {
            System.out.println("Uso: java CaixaCliente <hostname>");
            System.exit(1);
        }
        //Consultar saldo de 01
        //Depositar 200 em 01.
        //Consultar saldo de 01.

        //Consultar saldo de 02
        //Retirando 300 de 02.
        //Consultar saldo de 02
        try {
            System.out.println("Conectando com CaixaInterface...");
            CaixaInterface caixa = (CaixaInterface) Naming.lookup("//" + args[0] + "/Caixa");

            System.out.println("Consultando saldo de 01... Saldo: " + caixa.consultarSaldo(1));
            while(true){
                try{
                    System.out.println("Depositando R$200 em 01... " + caixa.depositar(1, 200));
                    break;
                } catch (RemoteException e){
                    Thread.sleep(10);
                    if(++cont >= contMax){
                        cont = 0;
                        System.out.println("Alcançada as quantidade máxima de tentativas...");
                        e.printStackTrace();
                        break;
                    }
                }
            }
            System.out.println("Consultando saldo de 01... Saldo: " + caixa.consultarSaldo(1));

            System.out.println("Consultando saldo de 02... Saldo: " + caixa.consultarSaldo(2));
            while (true){
                try{
                    System.out.println("Retirando R$300... " + caixa.sacar(2, 300));
                    break;
                } catch (RemoteException e){
                    Thread.sleep(10);
                    if(++cont >= contMax){
                        cont = 0;
                        System.out.println("Alcançada a quantidade máxima de tentativas...");
                        e.printStackTrace();
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
