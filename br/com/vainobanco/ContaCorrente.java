package br.com.vainobanco;

import java.util.Random;

public class ContaCorrente extends Conta {

	private static final double SALDO_INICIAL = 500;
	private double limiteCredito;
	private double saldo;

	public ContaCorrente(String nomeTitular, String cpfTitular) {
		super(gerarNumeroConta(), gerarNumeroAgencia(), nomeTitular, cpfTitular);
		this.limiteCredito = 0;
		this.setSaldo(SALDO_INICIAL);
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public void sacar(double valor) {
		if (valor <= 0) {
			System.out.println("Valor de saque inválido.");
			return;
		}
		if (getSaldo() + limiteCredito >= valor) {
			setSaldo(getSaldo() - valor);
			System.out.println("Saque de R$" + valor + " realizado com sucesso.");
		} else {
			System.out.println("Saldo insuficiente para realizar o saque.");
		}

	}

	@Override
	public void depositar(double valor) {
		if (valor <= 0) {
			System.out.println("Valor de depósito inválido.");
			return;
		}
		setSaldo(getSaldo() + valor);
		System.out.println("Depósito de R$" + valor + " realizado com sucesso.");

	}

	@Override
	public void transferir(ContaBancaria destino, double valor) {
		if (destino == null || !(destino instanceof ContaCorrente)) {
			System.out.println("Conta de destino inválida.");
			return;
		}
		ContaCorrente contaDestino = (ContaCorrente) destino;
		if (valor <= 0) {
			System.out.println("Valor de transferência inválido.");
			return;
		}
		if (getSaldo() + limiteCredito >= valor) {
			setSaldo(getSaldo() - valor);
			contaDestino.setSaldo(contaDestino.getSaldo() + valor);
			System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
		} else {
			System.out.println("Saldo insuficiente para realizar a transferência.");
		}

	}

	private static String gerarNumeroConta() {
		Random random = new Random();
		int numeroDaConta = random.nextInt(90000) + 10000;
		return String.valueOf(numeroDaConta);
	}

	private static String gerarNumeroAgencia() {
		Random random = new Random();
		int numeroDaAgencia = random.nextInt(9) + 1;
		return String.valueOf(numeroDaAgencia);
	}

}
