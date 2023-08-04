package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import modelo.Conta;
import modelo.Pessoa;
import modelo.Produto;

public class Delivery {

	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Produto> produtos;
	private static ArrayList<Conta> contas;
	static ArrayList<Conta> contaDelivery;
	private static Map<Produto, Integer> carrinho;
	

	public static void main(String[] args) {
		produtos = new ArrayList<>();
		contas = new ArrayList<>();
		carrinho = new HashMap<>();
		contaDelivery = new ArrayList<Conta>();
		ArrayList<Produto> conta;
		menu();
	}

	public static void menu() {

		System.out.println("------------------------------------------------------");
		System.out.println("--------Bem vindos a nossa Pizzaria Delivery ---------");
		System.out.println("------------------------------------------------------");
		System.out.println("***** Selecione uma operacao que deseja realizar *****");
		System.out.println("------------------------------------------------------");
		System.out.println("|              Opcao 1 - Cadastrar Produto           |");
		System.out.println("|              Opcao 2 - Cadastrar Cliente           |");
		System.out.println("|              Opcao 3 - Listar Produto              |");
		System.out.println("|              Opcao 4 - Comprar Produtos            |");
		System.out.println("|              Opcao 5 - Ver Carrinho                |");
		System.out.println("|              Opcao 6 - Sair                        |");

		int option = input.nextInt(); // opçao que o usuario digitar

		switch (option) {
		case 1:
			cadastrarProduto();
			break;
			
		case 2:
			cadastrarCliente();
			break;

		case 3:
			listarProduto();
			break;

		case 4:
			comprarProduto();
			break;

		case 5:
			verCarrinho();
			break;

		case 6:
			System.out.println("Volte sempre!");
			System.exit(0);

		default:
			System.out.println("Opcao Invalida!");
			menu();
			break;
		}
	}

	private static void cadastrarProduto() {
		System.out.println("Nome do produto: ");
		String nome = input.next();

		System.out.println("Preco do produto: ");
		Double preco = input.nextDouble();

		Produto produto = new Produto(nome, preco);
		produtos.add(produto);

		System.out.println(produto.getNome() + "cadastrado com sucesso!!!");
		menu();
	}
	
	public static void cadastrarCliente() {
		System.out.println("\nNome: ");
		String nome = input.next(); // armazena oq o usuário digitou

		System.out.println("\nCPF: ");
		String cpf = input.next(); // armazena oq o usuário digitou

		System.out.println("\nEndereco: ");
		String endereco = input.next(); // armazena oq o usuário digitou

		Pessoa pessoa = new Pessoa(nome, cpf, endereco); // instacia uma pessoa e passa os dados p ela
		Conta conta = new Conta(pessoa);

		contaDelivery.add(conta); // adiciona a conta criada na lista de contas
		System.out.println("Sua conta foi criada com sucesso!");
		menu();
	}
	

	private static void listarProduto() {
		if (produtos.size() > 0) {
			System.out.println("Lista de produtos \n");

			for (Produto p : produtos) { // pra cada produto dentro da minha lista de produtos
				System.out.println(p); // mostra os produtos
			} 
		
		
		} else {
			System.out.println("Nenhum produto cadastrado!");
		}

		menu();
	}

	private static void comprarProduto() {
		if (produtos.size() > 0) {// verifica o tamanho da lista (linha 101)
			System.out.println("Código do produto: \n"); // pede pro usuario digitar o codigo do produto

			// primeiro mostra os produtos disponiveis antes de mostrar o campo pro usuario
			// digitar
			System.out.println("----------- Produtos Disponiveis -----------");

			for (Produto p : produtos) { // pra cada produto dentro da minha lista de produtos
				System.out.println(p + "\n"); // mostra os produtos
			}
			int id = Integer.parseInt(input.next()); // se tem produto (linha 94) e armazena na variavel id
			boolean isPresent = false; // é declado uma variavel isPresente, pois o produto não esta armazenado no
										// carrinho

			for (Produto p : produtos) { // pra cada produto dentro da minha lista de produtos
				if (p.getId() == id) { // compara o id que o usuario digitou com a lista de produtos
					int quantidade = 0;
					try {
						quantidade = carrinho.get(p); // checa se o produto ja esta no carrinho
						carrinho.put(p, quantidade + 1); // se está, acrescenta a quantidade, se não, acrescenta pela
															// primeira vez
					} catch (NullPointerException e) {
						// se o produto for o primeiro no carrinho
						carrinho.put(p, 1);
					}
					System.out.println(p.getNome() + "adicionado ao carrinho!");
					isPresent = true; // mostra que o produto foi add

					if (isPresent) { // apos add, pergunta se o usuario quer continuar comprando
						System.out.println("Deseja adicionar outro produto ao carrinho?");
						System.out.println("Digite 1 para sim ou 0 para finalizar a compra. \n");
						int option = Integer.parseInt(input.next());

						if (option == 1) { // verifica a opcao que o usuario digitou
							comprarProduto();
						} else {
							finalizarCompra();
						}
					}
				} else {
					System.out.println("Produto não encontrado");
					menu();
				}
			}
		} else {
			System.out.println("Não existe esse produto cadastrado!");
			menu();
		}
	}

	private static void verCarrinho() {
		System.out.println("-------- Produtos no seu carrinho --------");

		if (carrinho.size() > 0) {
			for (Produto p : carrinho.keySet()) { // para cada produto dentro do carrinho
				System.out.println("Produto: " + p + "\nQuantidade: " + carrinho.get(p)); // mostra-se o produto e a
																							// quantidade
			}
		} else {
			System.out.println("Carrinho vazio");
		}
		menu();
	}

	private static void finalizarCompra() {
		Double valorTotal = 0.0; // recebe o valor total da compra
		System.out.println("Seus Produtos: ");

		for (Produto p : carrinho.keySet()) { // para cada produto dentro do carrinho
			int quantidade = carrinho.get(p); // checa se o produto ja esta no carrinho
			valorTotal += p.getPreco() * quantidade; // pega o preço do produto e multiplica pela quantidde
			System.out.println(p);// mostra o produto
			System.out.println("Quantidade: " + quantidade); // mostra a quantidade
			System.out.println("-------------------------------");
		}
		System.out.println("O valor da sua compra é: " + utils.Utils.doubleToString(valorTotal));
		carrinho.clear();
		System.out.println("Obrigado pela preferencia!");
		System.out.println("Seu pedido já saiu para entrega!");
		System.out.println("Volte sempre!!!!");
		menu();
	}
}
