public class Cliente {
    Integer numeroCliente;
    String nomeCliente;
    Double saldo;

    public Cliente(Integer num, String nome){
        numeroCliente = num;
        nomeCliente = nome;
    }
    public void saca(double q){
        saldo -= q;
    }
    public void deposita(double q){
        saldo += q;
    }


}
