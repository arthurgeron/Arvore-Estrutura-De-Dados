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
		ArvoreBaskhara raiz2 = new ArvoreBaskhara(null, 0);
		ArvoreBaskhara arvore =  ArvoreBaskhara.CriarArvore(raiz2,operacoes);
		System.out.println(ArvoreBaskhara.NoduloEstaBalanceado(arvore) ? "A �rvore est� balanceada!" : "A �rvore n�o est� balanceda :c");
		String resultado = ArvoreBaskhara.PercorrerArvereBaskhara(arvore);
		try{
		System.out.println("Conta: "+resultado +"\nResultado da conta: "+Eval.eval(resultado));
		}
		catch(Exception e)
		{
			System.out.println("N�o foi poss�vel processar o valor desta express�o resultante da leitura da �rvore:\n"+ resultado);
		}
	}
	
	
	
	
	
}
