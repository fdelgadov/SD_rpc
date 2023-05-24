
public class CalculatorImpl implements Calculator{
    @Override
    public float sumar(Float numero1, Float numero2) {
        return numero1 + numero2;
    }

    @Override
    public float restar(Float numero1, Float numero2) {
        return numero1 - numero2;
    }

    @Override
    public float multiplicar(Float numero1, Float numero2) {
        return numero1 * numero2;
    }

    @Override
    public float dividir(Float numero1, Float numero2) {
        return numero1 / numero2;
    }
}
