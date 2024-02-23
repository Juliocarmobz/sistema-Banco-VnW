package br.com.vainobanco;

import java.util.HashMap;
import java.util.Map;


public class Banco {
	private Map<String, ContaBancaria> contas;

	public Banco() {
		contas = new HashMap<>();
	}

	public void cadastrarConta(ContaBancaria conta) {
		contas.put(conta.getNumero(), conta);
	}

	public void excluirConta(String numeroConta) {
		contas.remove(numeroConta);
	}

	public ContaBancaria buscarConta(String numeroConta) {
		return contas.get(numeroConta);
	}

	public Map<String, ContaBancaria> getContas() {
		return contas;
	}
}
