import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Admin extends UnicastRemoteObject implements AdminInterface {

    HashMap<Integer, Cliente> clientes;

    public Admin() throws RemoteException{
        clientes = new HashMap<>();
    }

    public boolean abrirConta(Integer numeroConta, String nomeCliente) throws RemoteException {
        for(int i = 0; i < 10; i++){
            if (clientes.containsKey(numeroConta)){
                System.out.println("Cliente já existente!");
                return false;
            }
        }
        Cliente c = new Cliente(numeroConta, nomeCliente);
        clientes.put(numeroConta, c);
        return true;
    }

    public boolean fecharConta(Integer numeroConta) throws RemoteException{
        if(!clientes.containsKey(numeroConta)){
            System.out.println("ID não corresponde à nenhum cliente inscrito!");
            return false;
        } else {
            clientes.remove(numeroConta);
            return true;
        }
    }

    public boolean sacar(Integer numeroConta, double valor) throws RemoteException{
        if(clientes.containsKey(numeroConta)){
            if(clientes.get(numeroConta).saldo < valor){
                System.out.println("Não foi possível fazer o saldo! Saldo muito baixo!");
                return false;
            } else {
                clientes.get(numeroConta).saca(valor);
                return true;
            }
        }
        System.out.println("Cliente não encontrado!");
        return false;
    }

    public boolean depositar(Integer numeroConta, double valor) throws RemoteException{
        if(!clientes.containsKey(numeroConta)){
            System.out.println("Cliente não encontrado!");
            return false;
        }
        clientes.get(numeroConta).deposita(valor);
        return true;
    }

    public double consultarSaldo(Integer numeroConta) throws  RemoteException{
        if(!clientes.containsKey(numeroConta)){
            System.out.println("Cliente não encontrado!");
            return -1;
        }
        return clientes.get(numeroConta).saldo;
    }

}
