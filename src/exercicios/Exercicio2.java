package exercicios;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Exercicio2 {
	public static void main(String[] args)
	{
		ArrayList<String> operacoes = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		while(true)
		{
			String numeroOuOperacao;
			System.out.println("Digite um n�mero ou uma opera��o para incluir em sua �rvore, digite sair para sair.");
			numeroOuOperacao=scanner.next();
			if(numeroOuOperacao.equals("sair")) 
			{
				scanner.close();
				break;
			}
					if(Pattern.compile("[0-9]{1,}|[+-/*]{1}").matcher(numeroOuOperacao).matches())
					{
						operacoes.add(numeroOuOperacao);
					}
					else
					{
						System.out.println("Carectere inv�lido digitado");
					}
				
	
		}
		ArvoreBaskhara raiz2 = new ArvoreBaskhara(null);
		ArvoreBaskhara arvore =  ArvoreBaskhara.CriarArvore(raiz2,operacoes);
		String resultado = ArvoreBaskhara.PercorrerArvereBaskhara(arvore);
		System.out.println("Conta: "+resultado +"\nResultado da conta: "+ArvoreBaskhara.eval(resultado));
	}
	
	
	
	
	
}
