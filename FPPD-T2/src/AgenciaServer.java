import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AgenciaServer {

    public static void main(String[] args) {
        try {
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("RMI registry pronto.");
            } catch (RemoteException e) {
                System.out.println("RMI registry já está rodando.");
            }
            Agencia agencia = new Agencia();
            System.out.println("Agência IACON criada com sucesso!");

            Naming.rebind("Agencia", agencia);
            System.out.println("Agência registrada no RMI registry com o nome 'Agencia'.");

        } catch (Exception e) {
            System.out.println("Erro no AgenciaServer:");
            e.printStackTrace();
        }
    }
}
