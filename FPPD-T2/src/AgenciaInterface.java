import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AgenciaInterface extends Remote {
    // Operações para abertura e fechamento de conta
    boolean abrirConta(Integer numeroConta, String nomeCliente) throws RemoteException;
    boolean fecharConta(Integer numeroConta) throws RemoteException;

    // Operações de saque, depósito e consulta de saldo
    boolean sacar(Integer numeroConta, double valor) throws RemoteException;
    boolean depositar(Integer numeroConta, double valor) throws RemoteException;
    double consultarSaldo(Integer numeroConta) throws RemoteException;
}
