import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Caixa extends UnicastRemoteObject implements CaixaInterface {
    Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Agencia iacon = new Agencia();
            System.out.println("Conectado à agência!");
        }
        catch (RemoteException e){
            System.out.println("Erro ao se conectar com a agência!");
        }

    }

    public void login(Integer numeroConta, String nomeCliente, Agencia agencia){
        try {
            agencia.abrirConta(numeroConta, nomeCliente);
        }
        catch (RemoteException e){
            System.out.println("Erro ao entrar na conta!");
        }
    }

    public boolean sacar(Integer numeroConta, double valor, Agencia agencia) throws RemoteException{
        return  agencia.sacar(numeroConta, valor);
    }

    public boolean depositar(Integer numeroConta, double valor, Agencia agencia) throws RemoteException{
        return agencia.depositar(numeroConta, valor);
    }

    public double consultarSaldo(Integer numeroConta, Agencia agencia) throws RemoteException{
        return agencia.consultarSaldo(numeroConta);
    }
}