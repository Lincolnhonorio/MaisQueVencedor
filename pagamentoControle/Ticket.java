package pagamentoControle;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket{

	    private static int contador = 0; // Para gerar IDs únicos para cada ticket

	    private int idTicket;
	    private String idTransacao;
	    private String descricaoProblema;
	    private LocalDateTime dataAbertura;
	    private String status;
	    private List<String> comentarios;

	    // Construtor
	    public Ticket(String idTransacao, String descricaoProblema) {
	        this.idTicket = ++contador;
	        this.idTransacao = idTransacao;
	        this.descricaoProblema = descricaoProblema;
	        this.dataAbertura = LocalDateTime.now();
	        this.status = "Aberto";
	        this.comentarios = new ArrayList<>();
	    }

	    // Método para adicionar comentário ao ticket
	    public void adicionarComentario(String comentario) {
	        this.comentarios.add(comentario);
	    }

	    // Método para alterar o status do ticket
	    public void alterarStatus(String novoStatus) {
	        this.status = novoStatus;
	    }

	    // Método para exibir detalhes do ticket
	    public void exibirDetalhes() {
	        System.out.println("Ticket ID: " + idTicket);
	        System.out.println("ID da Transação: " + idTransacao);
	        System.out.println("Descrição do Problema: " + descricaoProblema);
	        System.out.println("Data de Abertura: " + dataAbertura);
	        System.out.println("Status: " + status);
	        System.out.println("Comentários:");
	        for (String comentario : comentarios) {
	            System.out.println("- " + comentario);
	        }
	    }

	    // Método para verificar se o ticket está relacionado a um pagamento específico
	    public boolean relacionadoComTransacao(String idTransacao) {
	        return this.idTransacao.equals(idTransacao);
	    }

	    // Getters e Setters
	    public int getIdTicket() {
	        return idTicket;
	    }

	    public String getIdTransacao() {
	        return idTransacao;
	    }

	    public String getDescricaoProblema() {
	        return descricaoProblema;
	    }

	    public LocalDateTime getDataAbertura() {
	        return dataAbertura;
	    }

	    public String getStatus() {
	        return status;
	    }
	}
