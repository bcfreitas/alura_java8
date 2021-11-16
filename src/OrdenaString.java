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
		palavras.sort(comparador);
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
		Consumer<String> consumidor = new ImprimeNaLinha();
		palavras.forEach(consumidor);
	}
}

class ImprimeNaLinha implements Consumer<String>{
	@Override
	public void accept(String s) {
		System.out.println(s);
	}
}

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