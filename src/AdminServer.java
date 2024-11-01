import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AdminServer {
    public static void main(String[] args) {
        try {
            // Criando o registro RMI na porta 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("Registro RMI criado.");

            // Instanciando o objeto Admin
            AdminInterface admin = new Admin();

            // Registrando o objeto no serviço de nomes RMI
            Naming.rebind("rmi://localhost/AdminService", admin);
            System.out.println("AdminService pronto para receber requisições.");
        } catch (RemoteException e) {
            System.err.println("Erro no servidor de Administração:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro geral no AdminServer:");
            e.printStackTrace();
        }
    }
}
