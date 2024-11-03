import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CaixaInterface extends Remote {
    // Operações de saque, depósito e consulta de saldo
    boolean sacar(Integer numeroConta, double valor) throws RemoteException;
    boolean depositar(Integer numeroConta, double valor) throws RemoteException;
    double consultarSaldo(Integer numeroConta) throws RemoteException;
}
