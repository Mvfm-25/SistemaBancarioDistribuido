import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Agencia extends UnicastRemoteObject implements AgenciaInterface{
    AdminInterface upTop;
    String nomeAgencia;
    String hostname;
    String idAgencia;

    public Agencia(String hostname) throws RemoteException{
        try {
            upTop = (AdminInterface) Naming.lookup("//" + hostname + "/Admin");
            nomeAgencia = "Agência IACON";
            this.idAgencia = UUID.randomUUID().toString();
            System.out.println("Agencia %s foi criada".formatted(idAgencia));
            System.out.println("Agencia conectada ao Admin %s.".formatted(upTop.getId()));
        } catch (Exception e) {
            System.out.println("Erro ao criar Agencia...");
            e.printStackTrace();
        }

    }

    public String getId() throws RemoteException {
        return idAgencia;
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

    public String getNomeConta(Integer i) throws RemoteException{
        return upTop.getClientes().get(i).nomeCliente;
    }

}
