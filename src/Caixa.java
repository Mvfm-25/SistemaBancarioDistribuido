import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.UUID;

public class Caixa extends UnicastRemoteObject implements CaixaInterface {
    Scanner teclado = new Scanner(System.in);
    AgenciaInterface iacon;
    String caixaID;

    public Caixa(String hostname) throws RemoteException {
        try {
            iacon = (AgenciaInterface)  Naming.lookup("//" + hostname + "/Agencia");
            caixaID = UUID.randomUUID().toString();
            System.out.println("Caixa %s foi criado!" .formatted(caixaID));
            System.out.println("Caixa conectada a Agencia %s.".formatted(iacon.getId()));
        } catch (Exception e) {
            System.out.println("Erro ao criar Caixa...");
            e.printStackTrace();
        }

    }

    public boolean sacar(Integer numeroConta, double valor) throws RemoteException {
        return iacon.sacar(numeroConta, valor);
    }

    public boolean depositar(Integer numeroConta, double valor) throws RemoteException {
        return iacon.depositar(numeroConta, valor);
    }

    public double consultarSaldo(Integer numeroConta) throws RemoteException {
        return iacon.consultarSaldo(numeroConta);
    }
}
