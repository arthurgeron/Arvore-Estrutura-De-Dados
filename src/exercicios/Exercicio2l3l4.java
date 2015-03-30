package exercicios;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Exercicio2l3l4 {
	public static void main(String[] args)
	{
		ArrayList<String> operacoes = new ArrayList<String>();
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
					if(Pattern.compile("[0-9]{1,}|[+-/*]{1}").matcher(numeroOuOperacao).matches())
					{
						operacoes.add(numeroOuOperacao);
					}
					else
					{
						System.out.println("Carectere inválido digitado");
					}
				
	
		}
		ArvoreBaskhara raiz2 = new ArvoreBaskhara(null, 0);
		ArvoreBaskhara arvore =  ArvoreBaskhara.CriarArvore(raiz2,operacoes);
		System.out.println(ArvoreBaskhara.NoduloEstaBalanceado(arvore) ? "A árvore está balanceada!" : "A árvore não está balanceda :c");
		String resultado = ArvoreBaskhara.PercorrerArvereBaskhara(arvore);
		try{
		System.out.println("Conta: "+resultado +"\nResultado da conta: "+Eval.eval(resultado));
		}
		catch(Exception e)
		{
			System.out.println("Não foi possível processar o valor desta expressão resultante da leitura da árvore:\n"+ resultado);
		}
	}
	
	
	
	
	
}
