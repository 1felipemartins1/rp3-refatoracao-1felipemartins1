import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Conta {

    private Cliente cliente;

    // Dados da conta
    private int numAgencia;
    private int numConta;
    private String gerente;

    // renomeado para deixar claro que é o saldo
    private double saldo;

    private List<Operacao> operacoes;

    public Conta(String nomeCliente, String cpfCliente, String telefoneCliente, int numAgencia, int numConta, String gerente, double saldo) {
        this.cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente);
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.gerente = gerente;
        this.saldo = saldo;

        this.operacoes = new ArrayList<>();
    }

    public Conta() {
        this(null, null, null, 0, 0, null, 0);
    }

    // Mantém a mesma API pública para compatibilidade
    public void realizarOperacao(char tipo, int valor) {
        Operacao op = new Operacao(tipo, valor);
        this.operacoes.add(op);

        if (tipo == 'd')
            deposit(valor);
        else if (tipo == 's')
            withdraw(valor);
    }

    private void deposit(double v) {
        this.saldo += v;
    }

    private void withdraw(double v) {
        this.saldo -= v;
    }

    public String toString() {
        String dadosCliente = this.cliente.toString();

        String dadosConta = String.format("Ag.: %d\nConta: %d\nGerente: %s\nSaldo: %.2f",
                this.numAgencia, this.numConta, this.gerente, this.saldo);

        StringBuilder sb = new StringBuilder();
        for (Operacao op : this.operacoes) {
            sb.append(op.toString()).append("\n");
        }

        return "-----CLIENTE-----\n" +
                dadosCliente +
                "\n\n" +
                "-----CONTA-----\n" +
                dadosConta +
                "\n\n" +
                "-----EXTRATO-----\n" +
                sb.toString() +
                "\n";
    }
}
