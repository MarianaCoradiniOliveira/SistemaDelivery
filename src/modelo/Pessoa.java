package modelo;

public class Pessoa {
	
	private static int counter = 1 ; //variável contadora
	 
	private String nome;
	private String cpf;
	private String endereco;
	
	public Pessoa(String nome, String cpf, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		
		counter += 1;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	public String toString() {
		return "\nNome: " + this.getNome() + 
				"\nCPF: " + this.getCpf() +
				"\nEndereco: " + this.getEndereco();
	}
}