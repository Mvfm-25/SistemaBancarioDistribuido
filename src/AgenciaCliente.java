//Sistema Bancário Distribuído v.0007
//C: MVFM       UA:061124

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class AgenciaCliente {

    public static void main(String[] args) {

        int cont = 0;
        int contMax = 3;
        if (args.length != 1) {
            System.out.println("Uso : java AgenciaCliente <hostname>");
            System.exit(1);
        }

        //Abrir Contas 01, 02.
        //Tentar & falhar à criar uma segunda conta 01.
        try {
            AgenciaInterface iacon = (AgenciaInterface) Naming.lookup("//" + args[0] + "/Agencia");
            System.out.println("Criando clientes...");

            while (true){
                try{
                    System.out.println("Criando conta 01... \nID = 1 nomeConta = 'Optimus' " + iacon.abrirConta(1, "Optimus"));
                    break;
                } catch (RemoteException e){
                    Thread.sleep(10);
                    System.out.println("Erro! Processo já está em execução!\nTentando novamente...");
                    if(++cont >= contMax){
                        cont = 0;
                        System.out.println("Tentativas máximas alcançada...");
                        e.printStackTrace();
                        break;
                    }
                }
            }

            while (true){
                try{
                    System.out.println("Criando conta 02...\nID = 2 nomeConta = 'Ultra Magnus' " + iacon.abrirConta(2, "Ultra Magnus"));
                    System.out.println("Depositando R$1000 em 02... " + iacon.depositar(2, 1000));
                    break;
                } catch (RemoteException e){
                    Thread.sleep(10);
                    System.out.println("Erro! Processo já está em execução!\nTentando novamente...");
                    if(++cont >= contMax){
                        cont = 0;
                        System.out.println("Tentativas máximas alcançada...");
                        e.printStackTrace();
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
