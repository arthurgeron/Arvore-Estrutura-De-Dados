package exercicios;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Exercicio2 {

	public static void main(String[] args)
	{
		ArrayList<String> operacoes = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		while(true)
		{
			String numeroOuOperacao;
			System.out.println("Digite um número ou uma operação para incluir em sua árvore, digite 0 para sair.");
			numeroOuOperacao=scanner.next();
			if(numeroOuOperacao.equals("0")) 
			{
				scanner.close();
				break;
			}
			operacoes.add(numeroOuOperacao);
		}
//		operacoes.add("1");
//		operacoes.add("+");
//		operacoes.add("3");
//		operacoes.add("+");
//		operacoes.add("4");
//		operacoes.add("+");
//		operacoes.add("+");
//		operacoes.add("5");
		ArvoreBaskhara raiz2 = new ArvoreBaskhara("1");
		ArvoreBaskhara.CriarArvore(raiz2,operacoes);
		System.out.println(ArvoreBaskhara.PercorrerArvereBaskhara(raiz2));
	}
	
	
	
	
	
}
