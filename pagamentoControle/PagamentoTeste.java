package pagamentoControle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import pagamentoControle.PagamentoForma.FormaPagamento;

public class PagamentoTeste {

	// Variáveis de pagamento
	private String cpf;
	private double valorPagamento;
	private FormaPagamento formaPagamento;
	private boolean recorrente;
	private LocalDateTime dataHoraPagamento;
	private String statusPagamento;
	private String idTransacao;
	private String descricaoPagamento;
	private double valorDesconto;
	private double valorFinal;
	private LocalDate dataVencimento; // Nova variável para a data de vencimento
	private double valorMulta; // Nova variável para armazenar o valor da multa
	private Object metodoPagamento;

	// Lista para armazenar pagamentos realizados
	private static List<Pagamento> pagamentosRealizados = new ArrayList<>();

	// Lista para armazenar tickets
	private static List<Ticket> ticket = new ArrayList<>();

	public PagamentoTeste(String cpf, double valorPagamento, FormaPagamento formaPagamento, boolean recorrente,
			LocalDateTime dataHoraPagamento, String statusPagamento, String idTransacao, String descricaoPagamento,
			double valorDesconto, double valorFinal, LocalDate dataVencimento, double valorMulta) {
		super();
		this.cpf = cpf;
		this.valorPagamento = valorPagamento;
		this.formaPagamento = formaPagamento;
		this.recorrente = recorrente;
		this.dataHoraPagamento = dataHoraPagamento;
		this.statusPagamento = statusPagamento;
		this.idTransacao = idTransacao;
		this.descricaoPagamento = descricaoPagamento;
		this.valorDesconto = valorDesconto;
		this.valorFinal = valorFinal;
		this.dataVencimento = dataVencimento;
		this.valorMulta = valorMulta;
	}

	public Ticket abrirTicket(String descricaoProblema) {
		Ticket novoTicket = new Ticket(this.idTransacao, descricaoProblema);
		ticket.add(novoTicket);
		System.out.println("Ticket aberto com sucesso. ID do Ticket: " + novoTicket.getIdTicket());
		return novoTicket;
	}

	// Método para exibir todos os tickets relacionados a este pagamento
	public void exibirTicketsRelacionados() {
		System.out.println("Tickets relacionados ao pagamento " + this.idTransacao + ":");
		for (Ticket ticket : ticket) {
			if (ticket.relacionadoComTransacao(this.idTransacao)) {
				ticket.exibirDetalhes();
			}
		}
	}

	// Método para realizar o pagamento
	public boolean realizarPagamento() {
		if (validarPagamento()) {
			this.statusPagamento = "Concluído";
			System.out.println("Pagamento realizado com sucesso!");
			return true;
		} else {
			this.statusPagamento = "Falhou";
			System.out.println("Pagamento falhou.");
			return false;
		}
	}

	// Método Desconto
	public void aplicarDesconto(double percentualDesconto) {
		if (percentualDesconto < 0 || percentualDesconto > 100) {
			System.out.println("Percentual de desconto inválido!");
			return;
		}

		double valorDesconto = (this.valorPagamento * percentualDesconto) / 10;
		this.valorPagamento -= valorDesconto;

		System.out.printf("Desconto de %.2f%% aplicado. Valor do pagamento atualizado para: R$ %.2f%n",
				percentualDesconto, this.valorPagamento);
	}

	// Método para aplicar a multa por atraso
	public void aplicarMulta(double percentualMulta, double valorFixoMulta) {
		LocalDate hoje = LocalDate.now();
		if (hoje.isAfter(this.dataVencimento)) {
			long diasAtraso = ChronoUnit.DAYS.between(this.dataVencimento, hoje);
			this.valorMulta = (this.valorPagamento * percentualMulta / 100) + (diasAtraso * valorFixoMulta);
			this.valorFinal += this.valorMulta;

			System.out.printf(
					"Multa por atraso de %.2f%% + R$ %.2f por dia de atraso aplicado. Valor da multa: R$ %.2f%n",
					percentualMulta, valorFixoMulta, this.valorMulta);
		} else {
			System.out.println("Pagamento realizado dentro do prazo, nenhuma multa aplicada.");
		}
	}

	public void definirFormaPagamento(FormaPagamento formaPagamento) {
		if (formaPagamento == null) {
			System.out.println("Forma de pagamento não pode ser nula.");
			return;
		}
		this.formaPagamento = formaPagamento;
		System.out.println("Forma de pagamento definida para: " + formaPagamento);
	}

	// Método para definir se o pagamento é recorrente
	public void definirRecorrente(boolean recorrente) {
		this.recorrente = recorrente;
		System.out.println("Pagamento definido como " + (recorrente ? "recorrente" : "não recorrente"));
	}

	// Método para validar os dados do pagamento
	private boolean validarPagamento() {
		if (cpf == null || cpf.isEmpty()) {
			System.out.println("CPF inválido!");
			return false;
		}
		if (valorPagamento <= 0) {
			System.out.println("Valor do pagamento inválido!");
			return false;
		}
		Object metodoPagamento = null;
		System.out.println("Método de pagamento inválido!");
		return false;
	}

	// Método para verificar o status do pagamento
	public String verificarStatusPagamento() {
		return this.statusPagamento;
	}

	// Método para cancelar um pagamento (se ainda estiver pendente)
	public boolean cancelarPagamento() {
		if ("Pendente".equals(this.statusPagamento)) {
			this.statusPagamento = "Cancelado";
			System.out.println("Pagamento cancelado com sucesso.");
			return true;
		} else {
			System.out.println("Não é possível cancelar um pagamento que já foi " + this.statusPagamento);
			return false;
		}
	}

	// Método para gerar um ID de transação único (simples exemplo)
	private String gerarIdTransacao() {
		return "TRANSAÇÃO" + LocalDateTime.now().toString().replaceAll("[-:.T]", "");
	}

	// Método para gerar um recibo do pagamento
	public String gerarRecibo() {
		return String.format(
				"Recibo de Pagamento%n" + "-------------------%n" + "CPF: %s%n" + "Valor: R$ %.2f%n" + "Método: %s%n"
						+ "Data: %s%n" + "Status: %s%n" + "ID da Transação: %s%n" + "Descrição: %s%n",
				this.cpf, this.valorFinal, this.metodoPagamento, this.dataHoraPagamento, this.statusPagamento,
				this.idTransacao, this.descricaoPagamento);
	}

	// Método para exibir os pagamentos realizados
	public static void mostrarPagamentosRealizados() {
		if (pagamentosRealizados.isEmpty()) {
			System.out.println("Nenhum pagamento realizado.");
		} else {
			System.out.println("Pagamentos realizados:");
			for (Pagamento pagamento : pagamentosRealizados) {
				System.out.println(pagamento.gerarRecibo());
			}
		}
	}
}