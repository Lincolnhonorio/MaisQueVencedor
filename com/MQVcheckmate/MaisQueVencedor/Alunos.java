package com.MQVcheckmate.MaisQueVencedor;

public class Alunos {

	private int documentoId;
    private String alunoNome;
    private String graduacao;
    private int horaAula;
    
	public Alunos() {
		super();
	}
	
	public Alunos(int documentoId, String alunoNome, String graduacao, int horaAula) {
		super();
		this.documentoId = documentoId;
		this.alunoNome = alunoNome;
		this.graduacao = graduacao;
		this.horaAula = horaAula;
	}
	public int getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(int documentoId) {
		this.documentoId = documentoId;
	}
	public String getAlunoNome() {
		return alunoNome;
	}
	public void setAlunoNome(String alunoNome) {
		this.alunoNome = alunoNome;
	}
	public String getGraduacao() {
		return graduacao;
	}
	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}
	public int getHoraAula() {
		return horaAula;
	}
	public void setHoraAula(int horaAula) {
		this.horaAula = horaAula;
	}
}
