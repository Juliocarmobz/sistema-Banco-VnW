package br.com.vainobanco;


public abstract class Conta implements ContaBancaria {

	private String numero;
	private String agencia;
	private String nomeTitular;
	private String cpfTitular;

	public Conta(String numero, String agencia, String nomeTitular, String cpfTitular) {
		super();
		this.numero = numero;
		this.agencia = agencia;
		this.nomeTitular = nomeTitular;
		this.cpfTitular = cpfTitular;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getNumero() {
		return numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public String getCpfTitular() {
		return cpfTitular;
	}


}
