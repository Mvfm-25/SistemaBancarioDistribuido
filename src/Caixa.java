import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Caixa extends UnicastRemoteObject implements CaixaInterface {
    Scanner teclado = new Scanner(System.in);
    Agencia iacon = new Agencia();

    public Caixa() throws RemoteException {
        super();
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
