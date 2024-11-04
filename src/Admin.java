import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

public class Admin extends UnicastRemoteObject implements AdminInterface {

    String id;
    HashMap<Integer, Cliente> clientes = new HashMap<>();

    public Admin() throws RemoteException {
        this.id = UUID.randomUUID().toString();
        System.out.println("Admin %s foi criado.".formatted(this.id));
    }

    public HashMap<Integer, Cliente> getClientes() throws RemoteException {
        return this.clientes;
    }

    public String getId() throws RemoteException {
        return id;
    }

    // Tornando o método abrirConta synchronized e incluindo o requestId
    public synchronized boolean abrirConta(Integer numeroConta, String nomeCliente, String requestId) throws RemoteException {
        if (clientes.containsKey(numeroConta)) {
            System.out.println("Cliente já existente! Request ID: " + requestId);
            return false;
        } else {
            Cliente c = new Cliente(numeroConta, nomeCliente);
            clientes.put(numeroConta, c);
            System.out.println("Conta criada com sucesso para cliente: " + nomeCliente + ". Request ID: " + requestId);
            System.out.println(clientes);
            return true;
        }
    }

    public boolean fecharConta(Integer numeroConta) throws RemoteException {
        if (!clientes.containsKey(numeroConta)) {
            System.out.println("ID não corresponde a nenhum cliente inscrito!");
            return false;
        } else {
            clientes.remove(numeroConta);
            return true;
        }
    }

    public boolean sacar(Integer numeroConta, double valor) throws RemoteException {
        if (valor <= 0.0) {
            System.out.println("Impossível sacar nada!");
            return false;
        }
        if (clientes.containsKey(numeroConta)) {
            if (clientes.get(numeroConta).saldo < valor) {
                System.out.println("Não foi possível fazer o saque! Saldo muito baixo!");
                return false;
            } else {
                clientes.get(numeroConta).saca(valor);
                return true;
            }
        }
        System.out.println("Cliente não encontrado!");
        return false;
    }

    public boolean depositar(Integer numeroConta, double valor) throws RemoteException {
        if (valor <= 0.0) {
            System.out.println("Impossível depositar nada!");
            return false;
        }
        if (!clientes.containsKey(numeroConta)) {
            System.out.println("Cliente não encontrado!");
            return false;
        }
        clientes.get(numeroConta).deposita(valor);
        return true;
    }

    public double consultarSaldo(Integer numeroConta) throws RemoteException {
        System.out.println(clientes);
        System.out.println("numeroConta recebido : " + numeroConta);
        System.out.println("Consultando saldo...\nCliente sendo consultado : " + clientes.get(numeroConta).nomeCliente);
        if (!clientes.containsKey(numeroConta)) {
            System.out.println("Cliente não encontrado!");
            return -1;
        }
        System.out.println("Saldo do cliente " + numeroConta + ": " + clientes.get(numeroConta).saldo);
        return clientes.get(numeroConta).saldo;
    }
}
