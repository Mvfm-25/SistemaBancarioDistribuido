import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Caixa extends UnicastRemoteObject implements CaixaInterface {
    Scanner teclado = new Scanner(System.in);

    public Caixa() throws RemoteException {
        super();  // Chama o construtor de UnicastRemoteObject
    }

    // Agora 'agencia' é do tipo 'AgenciaInterface', não 'Agencia'
    public boolean login(Integer numeroConta, String nomeCliente, AgenciaInterface agencia) {
        try {
            if (agencia.consultarSaldo(numeroConta) >= 0) {
                System.out.println("Login bem-sucedido.");
                return true;
            } else {
                System.out.println("Cliente não encontrado!");
                return false;
            }
        } catch (RemoteException e) {
            System.out.println("Erro ao entrar na conta!");
            return false;
        }
    }

    // Usando 'AgenciaInterface' como o tipo da 'agencia' aqui também
    public boolean sacar(Integer numeroConta, double valor, AgenciaInterface agencia) throws RemoteException {
        return agencia.sacar(numeroConta, valor);
    }

    public boolean depositar(Integer numeroConta, double valor, AgenciaInterface agencia) throws RemoteException {
        return agencia.depositar(numeroConta, valor);
    }

    public double consultarSaldo(Integer numeroConta, AgenciaInterface agencia) throws RemoteException {
        return agencia.consultarSaldo(numeroConta);
    }
}
