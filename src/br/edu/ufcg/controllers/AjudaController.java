package br.edu.ufcg.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ufcg.entities.Ajuda;
import br.edu.ufcg.entities.AjudaPresencial;
import br.edu.ufcg.entities.OrdenaTutor;
import br.edu.ufcg.entities.Tutor;
import br.edu.ufcg.entities.AjudaOnline;

public class AjudaController {

	private List<Ajuda> ajudas;
	private Dados dados;

	public AjudaController(Dados dados) {
		this.ajudas = new ArrayList<Ajuda>();
		this.dados = dados;
	}

	public int pedirAjudaPresencial(String matrAluno, String disciplina,
	        String horario, String dia, String localInteresse) {
		String matrTutor = escolherTutorPresencial(horario, dia, localInteresse,
		        disciplina);
		this.ajudas.add(new AjudaPresencial(matrAluno, matrTutor, disciplina,
		        horario, dia, localInteresse));
		return this.ajudas.size();
	}

	public int pedirAjudaOnline(String matrAluno, String disciplina) {
		String matrTutor = escolherTutorOnline(disciplina);
		this.ajudas.add(new AjudaOnline(matrAluno, matrTutor, disciplina));
		return this.ajudas.size();
	}

	public String pegarTutor(int idAjuda) {
		return this.ajudas.get(idAjuda - 1).pegarTutor();
	}

	public String getInfoAjuda(int idAjuda, String atributo) {
		return this.ajudas.get(idAjuda - 1).getInfo(atributo);
	}

	public String avaliarTutor(int idAjuda, int nota) {
		String matricula = this.ajudas.get(idAjuda-1).getInfo("matr_tutor");
		return this.dados.getTutores()
		        .get(this.dados.getAlunos().get(matricula).getEmail())
		        .avaliarTutor(nota);

	}

	private String escolherTutorPresencial(String horario, String dia,
	        String local, String disciplina) {
		ArrayList<Tutor> temp = new ArrayList<Tutor>();
		for (Tutor t : this.dados.getTutores().values()) {
			if (t.consultaHorario(horario, dia) && t.consultaLocal(local)
			        && t.containsDisciplina(disciplina)) {
				temp.add(t);
			}
		}
		Collections.sort(temp, new OrdenaTutor(this.dados));
		return temp.get(0).getMatricula();

	}

	private String escolherTutorOnline(String disciplina) {
		ArrayList<Tutor> temp = new ArrayList<Tutor>();
		for (Tutor t : this.dados.getTutores().values()) {
			if (t.containsDisciplina(disciplina)) {
				temp.add(t);
			}
		}
		Collections.sort(temp, new OrdenaTutor(this.dados));
		return temp.get(0).getMatricula();
	}

}
