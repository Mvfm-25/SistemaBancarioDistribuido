import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public interface AdminInterface extends Remote {
    String getId() throws RemoteException;
    HashMap<Integer, Cliente> getClientes() throws RemoteException;

    // Operação para abrir uma nova conta
    boolean abrirConta(Integer numeroConta, String nomeCliente, String requestId) throws RemoteException;

    // Operação para fechar uma conta existente
    boolean fecharConta(Integer numeroConta) throws RemoteException;

    // Operações de saque, depósito e consulta de saldo
    boolean sacar(Integer numeroConta, double valor,String requestID) throws RemoteException;
    boolean depositar(Integer numeroConta, double valor, String requestID) throws RemoteException;
    double consultarSaldo(Integer numeroConta) throws RemoteException;
}
