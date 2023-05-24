
public class BancoImpl implements Banco {
    float saldo = 5000;
    @Override
    public synchronized float depositar(Float c) {
        saldo = saldo + c;
        return saldo;
    }

    @Override
    public synchronized float retirar(Float c) {
        saldo = saldo - c;
        return saldo;
    }
    
}
