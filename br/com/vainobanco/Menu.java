package br.com.vainobanco;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;

public class Menu {
	private Banco banco;
	private Scanner scanner;

	public Menu(Banco banco) {
		this.banco = banco;
		scanner = new Scanner(System.in);
	}

	public void exibirMenu() {
		int opcao = -1;
		System.out.println("Bem vindo ao Sistema de Criação de Contas [Vai no Banco]\n" + "Selecione uma Opcão Abaixo");
		while (opcao != 0) {
			try {
				System.out.println("[1] Criar uma nova conta");
				System.out.println("[2] Dados de uma conta");
				System.out.println("[3] Realizar um depósito");
				System.out.println("[4] Realizar um saque");
				System.out.println("[5] Realizar transferência");
				System.out.println("[6] Exibir todas as contas");
				System.out.println("[7] Excluir uma conta");
				System.out.println("[0] Sair");
				System.out.print("Escolha uma opção: ");

				opcao = scanner.nextInt();
				scanner.nextLine();

				switch (opcao) {
				case 1:
					criarNovaConta();
					break;
				case 2:
					exibirDadosConta();
					break;
				case 3:
					realizarDeposito();
					break;
				case 4:
					realizarSaque();
					break;
				case 5:
					realizarTransferencia();
					break;
				case 6:
					exibirTodasAsContas();
					break;
				case 7:
					excluirConta();
					break;
				case 0:
					System.out.println("Encerrando o programa...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida. Por favor, digite um número inteiro.");
				scanner.next();
			} catch (Exception err) {
				System.out.println("Ocorreu um erro inesperado: " + err.getMessage());
			}
		}
		scanner.close();
	}

	private void criarNovaConta() {
		System.out.println("----- CRIAR NOVA CONTA -----");
		System.out.print("Digite o nome do titular: ");
		String nomeTitular = scanner.nextLine();
		String cpfTitular;
		do {
			System.out.print("Digite o CPF do titular: ");
			cpfTitular = scanner.nextLine();
			if (cpfTitular.length() < 11) {
				System.err.print("CPF INVÁLIDO.");
				System.out.println(" Por favor, tente novamente.");
			}
		} while (cpfTitular.length() != 11);
		System.out.print("Deseja criar uma conta corrente (C) ou poupança (P)? ");
		String tipoConta = scanner.nextLine();

		ContaBancaria novaConta = null;
		if (tipoConta.equalsIgnoreCase("C")) {
			novaConta = new ContaCorrente(nomeTitular, cpfTitular);
		} else if (tipoConta.equalsIgnoreCase("P")) {
			novaConta = new ContaPoupanca(nomeTitular, cpfTitular);
		} else {
			System.out.println("Tipo de conta inválido.");
			return;
		}

		banco.cadastrarConta(novaConta);
		System.out.println("Conta criada com sucesso!");
		System.out.println("Número da Conta: " + novaConta.getNumero());
		System.out.println("Agência: " + novaConta.getAgencia());
	}

	private void exibirDadosConta() {
		System.out.println("----- EXIBIR DADOS DA CONTA -----");
		System.out.print("Digite o número da conta: ");
		String numeroConta = scanner.nextLine();

		ContaBancaria conta = banco.buscarConta(numeroConta);
		if (conta != null) {
			System.out.println("Número da Conta: " + conta.getNumero());
			System.out.println(
					"Tipo da Conta: " + (conta instanceof ContaCorrente ? "Conta Corrente" : "Conta Poupança"));
			System.out.println("Agência: " + conta.getAgencia());
			System.out.println("Nome do Titular: " + conta.getNomeTitular());
			System.out.println("CPF do Titular: " + conta.getCpfTitular());
		} else {
			System.out.println("Conta não encontrada.");
		}
	}

	private void realizarDeposito() {
		System.out.println("----- REALIZAR DEPÓSITO -----");
		System.out.print("Digite o número da conta: ");
		String numeroConta = scanner.nextLine();

		ContaBancaria conta = banco.buscarConta(numeroConta);
		if (conta == null) {
			System.out.println("Conta não encontrada.");
			return;
		}

		System.out.print("Digite o valor a ser depositado: ");
		double valor = scanner.nextDouble();
		scanner.nextLine();

		if (conta instanceof ContaCorrente) {
			((ContaCorrente) conta).depositar(valor);
		} else if (conta instanceof ContaPoupanca) {
			((ContaPoupanca) conta).depositar(valor);
		} else {
			System.out.println("Operação de depósito não suportada para este tipo de conta.");
		}
	}

	private void realizarSaque() {
		System.out.println("----- REALIZAR SAQUE -----");
		System.out.print("Digite o número da conta: ");
		String numeroConta = scanner.nextLine();

		ContaBancaria conta = banco.buscarConta(numeroConta);
		if (conta == null) {
			System.out.println("Conta não encontrada.");
			return;
		}

		System.out.print("Digite o valor a ser sacado: ");
		double valor = scanner.nextDouble();
		scanner.nextLine();

		if (conta instanceof ContaCorrente) {
			((ContaCorrente) conta).sacar(valor);
		} else if (conta instanceof ContaPoupanca) {
			((ContaPoupanca) conta).sacar(valor);
		} else {
			System.out.println("Operação de saque não suportada para este tipo de conta.");
		}
	}

	private void realizarTransferencia() {
		System.out.println("----- REALIZAR TRANSFERÊNCIA -----");
		System.out.print("Digite o número da conta de origem: ");
		String numeroContaOrigem = scanner.nextLine();

		System.out.print("Digite o número da conta de destino: ");
		String numeroContaDestino = scanner.nextLine();

		ContaBancaria contaOrigem = banco.buscarConta(numeroContaOrigem);
		ContaBancaria contaDestino = banco.buscarConta(numeroContaDestino);

		if (contaOrigem == null || contaDestino == null) {
			System.out.println("Conta de origem ou conta de destino não encontrada.");
			return;
		}

		System.out.print("Digite o valor a ser transferido: ");
		double valor = scanner.nextDouble();
		scanner.nextLine();

		if (contaOrigem instanceof ContaCorrente) {
			((ContaCorrente) contaOrigem).transferir(contaDestino, valor);
		} else if (contaOrigem instanceof ContaPoupanca) {
			((ContaPoupanca) contaOrigem).transferir(contaDestino, valor);
		} else {
			System.out.println("Operação de transferência não suportada para este tipo de conta.");
		}
	}

	private void exibirTodasAsContas() {
		System.out.println("----- TODAS AS CONTAS -----");
		for (Map.Entry<String, ContaBancaria> entry : banco.getContas().entrySet()) {
			String numeroConta = entry.getKey();
			ContaBancaria conta = entry.getValue();
			System.out.println("Número da Conta: " + numeroConta);
			System.out.println(
					"Tipo da Conta: " + (conta instanceof ContaCorrente ? "Conta Corrente" : "Conta Poupança"));
			System.out.println("Agência: " + conta.getAgencia());
			System.out.println("Nome do Titular: " + conta.getNomeTitular());
			System.out.println("CPF do Titular: " + conta.getCpfTitular());
			System.out.println("------------------------------------");
		}
	}

	private void excluirConta() {
		System.out.println("----- EXCLUIR CONTA -----");
	    System.out.print("Digite o número da conta que deseja excluir: ");
	    String numeroConta = scanner.nextLine();

	    ContaBancaria conta = banco.buscarConta(numeroConta);
	    if (conta != null) {
	        banco.excluirConta(numeroConta);
	        System.out.println("Conta excluída com sucesso!");
	    } else {
	        System.out.println("Conta não encontrada.");
	    }
	}
}
