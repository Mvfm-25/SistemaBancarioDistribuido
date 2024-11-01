import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CaixaInterface extends Remote {
    // Operações de saque, depósito e consulta de saldo
    void login(Integer numeroConta, String nomeCliente, Agencia agencia);
    boolean sacar(Integer numeroConta, double valor, Agencia agencia) throws RemoteException;
    boolean depositar(Integer numeroConta, double valor, Agencia agencia) throws RemoteException;
    double consultarSaldo(Integer numeroConta, Agencia agencia) throws RemoteException;
}
