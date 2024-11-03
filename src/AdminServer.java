import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AdminServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Registro RMI criado.");

            // AdminInterface admin = new Admin();

            Naming.rebind("Admin", new Admin());
            Naming.rebind("Agencia", new Agencia("localhost"));
            Naming.rebind("Caixa", new Caixa("localhost"));
            System.out.println("Serviços prontos!");
        } catch (RemoteException e) {
            System.err.println("Erro nos servidores: ");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro geral nos servidores: ");
            e.printStackTrace();
        }
    }
}
