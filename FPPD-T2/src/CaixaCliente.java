import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;

public class CaixaCliente {
    Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("IACON AGENCIES - CLIENT.0001\n UA: 011124\n");



        try {
            Caixa usuario = new Caixa();
            System.out.println();
        }
        catch (RemoteException e){
            System.out.println("Erro ao se conectar ao caixa!");
        }
    }

}
