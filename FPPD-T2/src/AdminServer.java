import java.rmi.Naming;
import java.rmi.RemoteException;


public class AdminServer {

    public static void main(String[] args) {
        System.out.println("IACON AGENCIES - SV.0001\n UA: 011124");

        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI rodando!");
        }
        catch (RemoteException e){
            System.out.println("Erro! RMI já está rodando!");
        }

        try {
            Agencia admin = new Agencia();
            System.out.println("Admin rodando!");
        } catch (Exception e) {
            System.out.println("Erro! Administração falhou!");
            e.printStackTrace();
        }
    }

}
