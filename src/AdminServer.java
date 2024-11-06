//Sistema Bancário Distribuído v.0007
//C: MVFM       UA:061124

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AdminServer {
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Uso : java AdminServer <IP || Hostname>");
            System.exit(1);
        }
        try {
            System.setProperty("java.rmi.server.hostname", args[0]);
            LocateRegistry.createRegistry(1099);
            System.out.println("Registro RMI criado.");

            Naming.rebind("rmi://" + args[0] + "/Admin", new Admin());
            Naming.rebind("rmi://" + args[0] + "/Agencia", new Agencia(args[0]));
            Naming.rebind("rmi://" + args[0] + "/Caixa", new Caixa(args[0]));
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
