import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
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
		
		//classe nova do java 8 para que pode ou nao conter um objeto
		//pode ajudar a evitar os "if null ..."
		Optional<Curso> optionalCurso = cursos.stream()
				.filter(c -> c.getAlunos()>=100)
				.findAny();
		
		Curso curso = optionalCurso.orElse(null);
		System.out.println(curso.getNome());
		
		//pode usar numa linha só
		cursos.stream()
			.filter(c -> c.getAlunos()>=100)
			.findAny()
			.ifPresent(c -> System.out.println(c.getNome()));
		
		//existe optional para numericos, com funcoes como average
		OptionalDouble media = cursos.stream()
				.filter(c -> c.getAlunos() >= 100)
				.mapToInt(Curso::getAlunos)
				.average();
		
		//na interface fluente, podemos usar o método collect do stream
		//passando Collectors.List, para atribuir o resultado das operações 
		//com a stream na lista original
		cursos = cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.collect(Collectors.toList());
		
		cursos.stream()
			.forEach(c -> System.out.println(c.getNome()));
		
		System.out.println("");
		System.out.println("biConsumer abaixo");
		System.out.println("");
		
		cursos.stream()
				.filter(c -> c.getAlunos() >= 100)
				.collect(Collectors.toMap(
						c -> c.getNome(),
						c -> c.getAlunos()))
				.forEach((nome, alunos) -> System.out.println(nome + " tem " + alunos));
		
			//	Map mapa = cursos 
			//	.stream() 
			//	.filter(c -> c.getAlunos() > 100) 
			//	.collect(Collectors.toMap(c -> c.getNome(), c -> c.getAlunos()));
				
		//é possível usar o parallelStream no lugar do stream, para usar threads em paralelo
		
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("a");
		
		strs.stream();	
		
	}
	
}


