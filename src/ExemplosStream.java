import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class Curso {
    private String nome;
    private int alunos;

    public Curso (String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }
}

public class ExemplosStream {
	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));
		
		cursos.sort(Comparator.comparing(Curso::getAlunos));
		
		cursos.forEach(c -> System.out.println(c.getNome()));

		//stream = corrente, fluxo de objetos!!
		//tudo que é feito no stream não impacta na coleção original!
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.forEach(c -> System.out.println(c.getNome()))
			;
		
		//interface fluente! devolve o mesmo objeto stream
		cursos.stream()
		.filter(c -> c.getAlunos() >= 100)
		.map(c -> c.getAlunos())
		.forEach(System.out::println);
		;
		
		int sum = cursos.stream()
		.filter(c -> c.getAlunos() >= 100)
		.mapToInt(c -> c.getAlunos())
		.sum()
		;
		
		System.out.println(sum);
		
		//como fazer para transformar a stream de cursos em stream de nomes dos cursos?
		Stream<String> nomes = cursos.stream().map(Curso::getNome);
	}
	
}
