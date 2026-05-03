public class CompteBancari {
    private String titular;
    private String iban;
    private double saldo;

    public CompteBancari(String titular, String iban, double saldoInicial) {
        if (titular == null || titular.isBlank())
            throw new IllegalArgumentException("El titular és obligatori");
        if (iban == null || iban.isBlank())
            throw new IllegalArgumentException("L'IBAN és obligatori");
        if (saldoInicial < 0)
            throw new IllegalArgumentException("El saldo inicial no pot ser negatiu");

        this.titular = titular;
        this.iban = iban;
        this.saldo = saldoInicial;
    }

    public void ingressar(double quantitat) {
        validarQuantitatPositiva(quantitat);
        saldo += quantitat;
        mostrarEstatTransaccio("Ingrés", quantitat);
    }

    public void retirar(double quantitat) {
        validarQuantitatPositiva(quantitat);
        if (quantitat > saldo)
            throw new IllegalArgumentException("Saldo insuficient");

        saldo -= quantitat;
        mostrarEstatTransaccio("Retirada", quantitat);
    }

    private void validarQuantitatPositiva(double quantitat) {
        if (quantitat <= 0)
            throw new IllegalArgumentException("La quantitat ha de ser positiva");
    }

    private void mostrarEstatTransaccio(String tipus, double quantitat) {
        System.out.println(tipus + " realitzat: " + quantitat);
        System.out.println("Estat del compte: " + getEstatSaldo());
    }

    public String getEstatSaldo() {
        if (saldo < 1000)
            return "Saldo baix";
        if (saldo < 5000)
            return "Saldo normal";
        return "Saldo alt";
    }

    // Getters...
    public String getTitular() {
        return titular;
    }

    public String getIban() {
        return iban;
    }

    public double getSaldo() {
        return saldo;
    }
}