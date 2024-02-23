package br.com.vainobanco;

public interface ContaBancaria {
	String getNumero();

	String getAgencia();

	String getNomeTitular();

	String getCpfTitular();

	void sacar(double valor);

	void depositar(double valor);

	void transferir(ContaBancaria destino, double valor);
}
