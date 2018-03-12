package br.edu.ufcg.entities;

public abstract class Ajuda {
	protected String disciplina;
	protected String matrAluno;
	protected String matrTutor;
	protected EstadoAvaliacaoAjuda estadoAvaliacao;

	public Ajuda(String matrAluno, String matrTutor, String disciplina) {
		this.matrAluno = matrAluno;
		this.matrTutor = matrTutor;
		this.disciplina = disciplina;
		this.estadoAvaliacao = EstadoAvaliacaoAjuda.NAO_AVALIADA;
	}

	public abstract String getInfo(String atributo);

	public abstract String pegarTutor();

	public void avaliaAjuda() {
		this.estadoAvaliacao = EstadoAvaliacaoAjuda.AVALIADA;
	}
}
