package modelo;


public class Conta {

		private static int contadorDeContas = 1;
		private int numeroConta;
		private Pessoa pessoa;
		
		
		public Conta(Pessoa pessoa) {
			this.numeroConta = contadorDeContas;
			this.pessoa = pessoa;
			contadorDeContas += 1;
		}

		public int getNumeroConta() {
			return numeroConta;
		}

		public void setNumeroConta(int numeroConta) {
			this.numeroConta = numeroConta;
		}

		public Pessoa getPessoa() {
			return pessoa;
		}

		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}
}
