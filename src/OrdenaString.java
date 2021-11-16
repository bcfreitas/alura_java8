import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class OrdenaString {
	public static void main(String[] args) {
		List<String> palavras = new ArrayList<String>();
		
		palavras.add("alura online");
		palavras.add("editora casa do codigo");
		palavras.add("caelum");

		//old way para comparar/ordenar até o Java 7
		Comparator<String> comparador = new ComparadorPorTamanho();
		//Collections.sort(palavras, comparador);
		//agora a própria interface List possui o sort:
		
		//em lugar de declarar uma classe que implementa a interface Comparator,
		//como ela é uma interface funcional, podemos usar lambda expressions:
		palavras.sort((s1, s2) -> {
		    if (s1.length() < s2.length())
		        return -1;
		    if (s1.length() > s2.length())
		        return 1;
		    return 0;
		});
		
		//também podemos simplificar usando o compare do Integer:
		palavras.sort((s1, s2) -> {
		    return Integer.compare(s1.length(), s2.length());
		});
		
		//e simplificar ainda mais:
		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		
		
		//se olharmos o source deste método, veremos
		//que foi usado o modificador default, que permite a evolução 
		//da interface sem quebrar código legado (exigindo implementação)
		//outra novidade é que há implementação no método da interface, o que
		//não era possível até o java 7.
		System.out.println(palavras);
		
		//old way enhanced for
		for(String p: palavras) {
			System.out.println(p);
		}
		
		//Nova forma: forEach (método da interface Iterable) com consumer
		//Normalmente em lugar de criar uma classe interna e chamá-la se utiliza 
		//classe anônima
		//Consumer<String> consumidor = new ImprimeNaLinha();
		palavras.forEach(new Consumer<String>() {
		    public void accept(String s) {
		        System.out.println(s);
		    }
		});
		
		//mas com o java 8 surgiu a expressão lambda.
		//o lambda se encaixa em contextos para substituir implementações 
		//de interfaces funcionais, que são as que possuem um único 
		//método abstrato (método sem corpo, e métodos sobrescritos 
		//de Object não contam).
		palavras.forEach((String s) -> {
		    System.out.println(s);
		});
		
		//além de nem precisar falar do método accept para este caso, também
		//podemos sumrimir a declaração do tipo do parâmetro, pois é inferido. 
		palavras.forEach((s) -> {
		    System.out.println(s);
		});
		
		//e quando há apenas um parâmetro, até os parênteses podem ser suprimidos.
		palavras.forEach(s -> {
		    System.out.println(s);
		});
		
		//e pra simplificar, tiramos até as chaves:
		palavras.forEach(s -> System.out.println(s));
	}
}


//A classe interna ficou desnecessária pois em seu lugar foi
//usada classe anônima
//class ImprimeNaLinha implements Consumer<String>{
//	@Override
//	public void accept(String s) {
//		System.out.println(s);
//	}
//}

class ComparadorPorTamanho implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		if(s1.length() < s2.length())
			return -1;
		if(s1.length() > s2.length())
			return 1;
		return 0;
	}
}