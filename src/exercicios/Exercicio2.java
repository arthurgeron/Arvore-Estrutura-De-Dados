package exercicios;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Exercicio2 {
	public static void clearConsole()
	{
		for(int clear = 0; clear < 1000; clear++)
		{
		     System.out.println("\n") ;
		}
	}
	public static void main(String[] args)
	{
		ArrayList<String> operacoes = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		while(true)
		{
			String numeroOuOperacao;
			System.out.println("Digite um número ou uma operação para incluir em sua árvore, digite sair para sair.");
			numeroOuOperacao=scanner.next();
			if(numeroOuOperacao.equals("sair")) 
			{
				scanner.close();
				break;
			}
			if(operacoes.size()!=0)
			{
				if(!Pattern.compile("[0-9]").matcher(numeroOuOperacao).matches() && !Pattern.compile("[0-9]").matcher(operacoes.toArray()[operacoes.size()-1].toString()).matches())
				{
					System.out.println("Nunca coloque um número seguido de outro, ou uma operação seguida de outra");
				}
				else if(Pattern.compile("[0-9]").matcher(numeroOuOperacao).matches() && Pattern.compile("[0-9]").matcher(operacoes.toArray()[operacoes.size()-1].toString()).matches())
				{
					System.out.println("Nunca coloque um número seguido de outro, ou uma operação seguida de outra");
				}
				else
				{
					if(Pattern.compile("[0-9]{1,}|[+-/*]{1}").matcher(numeroOuOperacao).matches())
					{
						operacoes.add(numeroOuOperacao);
					}
					else
					{
						System.out.println("Carectere inválido digitado");
					}
				}
			}
			else
			{
				operacoes.add(numeroOuOperacao);
			}
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
		String resultado = ArvoreBaskhara.PercorrerArvereBaskhara(raiz2);
		clearConsole();
		System.out.println("Conta: "+resultado +"\nResultado da conta: "+ArvoreBaskhara.eval(resultado));
	}
	
	
	
	
	
}
