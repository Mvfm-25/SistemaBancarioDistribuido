import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CaixaInterface extends Remote {
    // Operações de saque, depósito e consulta de saldo
    boolean login(Integer numeroConta, String nomeCliente, AgenciaInterface agencia);
    boolean sacar(Integer numeroConta, double valor, AgenciaInterface agencia) throws RemoteException;
    boolean depositar(Integer numeroConta, double valor, AgenciaInterface agencia) throws RemoteException;
    double consultarSaldo(Integer numeroConta, AgenciaInterface agencia) throws RemoteException;
}
