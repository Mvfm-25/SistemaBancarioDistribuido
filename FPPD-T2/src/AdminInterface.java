import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminInterface extends Remote {
    // Operação para abrir uma nova conta
    boolean abrirConta(Integer numeroConta, String nomeCliente) throws RemoteException;

    // Operação para fechar uma conta existente
    boolean fecharConta(Integer numeroConta) throws RemoteException;

    // Operações de saque, depósito e consulta de saldo
    boolean sacar(Integer numeroConta, double valor) throws RemoteException;
    boolean depositar(Integer numeroConta, double valor) throws RemoteException;
    double consultarSaldo(Integer numeroConta) throws RemoteException;
}
