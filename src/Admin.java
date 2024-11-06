import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class Admin extends UnicastRemoteObject implements AdminInterface {

    private String id;
    private LinkedList<String> processosOcorrendo = new LinkedList<>();
    private HashMap<Integer, Cliente> clientes = new HashMap<>();

    public Admin() throws RemoteException {
        this.id = UUID.randomUUID().toString();
        System.out.println("Admin %s foi criado.".formatted(this.id));
    }

    public synchronized HashMap<Integer, Cliente> getClientes() throws RemoteException {
        return this.clientes;
    }

    public synchronized String getId() throws RemoteException {
        return id;
    }

    public synchronized boolean abrirConta(Integer numeroConta, String nomeCliente, String requestId) throws RemoteException {
        if (clientes.containsKey(numeroConta)) {
            System.out.println("Cliente já existente! Request ID: " + requestId);
            return false;
        } else {
            if (processosOcorrendo.contains(requestId)) {
                System.out.println("Processo já está ocorrendo! Tente novamente depois!");
                return false;
            }
            processosOcorrendo.add(requestId);
            try {
                Cliente c = new Cliente(numeroConta, nomeCliente);
                clientes.put(numeroConta, c);
                System.out.println("Conta criada com sucesso para cliente: " + nomeCliente + ". Request ID: " + requestId);
                System.out.println(clientes);
                return true;
            } finally {
                processosOcorrendo.remove(requestId);
            }
        }
    }

    public synchronized boolean fecharConta(Integer numeroConta) throws RemoteException {
        if (!clientes.containsKey(numeroConta)) {
            System.out.println("ID não corresponde a nenhum cliente inscrito!");
            return false;
        } else {
            System.out.println(clientes);
            clientes.remove(numeroConta);
            return true;
        }
    }

    public synchronized boolean sacar(Integer numeroConta, double valor, String requestID) throws RemoteException {
        if (valor <= 0.0) {
            System.out.println("Impossível sacar nada!");
            return false;
        }
        if (clientes.containsKey(numeroConta)) {
            if (clientes.get(numeroConta).saldo < valor) {
                System.out.println("Não foi possível fazer o saque! Saldo muito baixo!");
                return false;
            } else {
                if (processosOcorrendo.contains(requestID)) {
                    System.out.println("Processo já está ocorrendo! Tente novamente depois!");
                    return false;
                }
                processosOcorrendo.add(requestID);
                try {
                    System.out.println(clientes.get(numeroConta));
                    clientes.get(numeroConta).saca(valor);
                    return true;
                } finally {
                    processosOcorrendo.remove(requestID);
                }
            }
        }
        System.out.println("Cliente não encontrado!");
        return false;
    }

    public synchronized boolean depositar(Integer numeroConta, double valor, String requestID) throws RemoteException {
        if (valor <= 0.0) {
            System.out.println("Impossível depositar nada!");
            return false;
        }
        if (!clientes.containsKey(numeroConta)) {
            System.out.println("Cliente não encontrado!");
            return false;
        }

        if (processosOcorrendo.contains(requestID)) {
            System.out.println("Processo já está ocorrendo! Tente novamente depois!");
            return false;
        }
        processosOcorrendo.add(requestID);
        try {
            clientes.get(numeroConta).deposita(valor);
            System.out.println(clientes.get(numeroConta));
            return true;
        } finally {
            processosOcorrendo.remove(requestID);
        }
    }

    public synchronized double consultarSaldo(Integer numeroConta) throws RemoteException {
        if (!clientes.containsKey(numeroConta)) {
            System.out.println("Cliente não encontrado!");
            return -1;
        }
        Cliente cliente = clientes.get(numeroConta);
        System.out.println("Saldo do cliente " + numeroConta + ": " + cliente.saldo);
        return cliente.saldo;
    }
}
