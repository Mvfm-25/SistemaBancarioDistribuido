import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Agencia extends UnicastRemoteObject implements AgenciaInterface{
    String nomeAgencia;
    Admin upTop = new Admin();

    public Agencia() throws RemoteException{
        nomeAgencia = "Agência IACON";
    }

    public boolean abrirConta(Integer numeroConta, String nomeCliente) throws RemoteException{
        return (upTop.abrirConta(numeroConta, nomeCliente));
    }

    public boolean fecharConta(Integer numeroConta) throws RemoteException{
        return (upTop.fecharConta(numeroConta));
    }

    public boolean sacar(Integer numeroConta, double valor) throws RemoteException{
        return (upTop.sacar(numeroConta, valor));
    }

    public boolean depositar(Integer numeroConta, double valor) throws RemoteException{
        return (upTop.depositar(numeroConta, valor));
    }

    public double consultarSaldo(Integer numeroConta) throws  RemoteException{
        return (upTop.consultarSaldo(numeroConta));
    }
}
