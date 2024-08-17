package pagamentoControle;

public class Pagamento {

	public static void main(String[] args) {

		PagamentoTeste pagamento = new PagamentoTeste(null, 0, null, false, null, null, null, null, 0, 0, null, 0);
		
		// Realizar pagamento
        pagamento.realizarPagamento();

        // Verificar status
        System.out.println("Status do pagamento: " + pagamento.verificarStatusPagamento());

        // Gerar recibo
        System.out.println(pagamento.gerarRecibo());

        // Tentar cancelar o pagamento
        pagamento.cancelarPagamento();
        
        char[] mostrarPagamentosRealizados = null;
		System.out.println(mostrarPagamentosRealizados);
    }

	
	public char[] gerarRecibo() {
		return null;
	}
	
}
