package exercicios;

import java.util.ArrayList;
import java.util.Random;

public class Exercicio6 {

	public Exercicio6() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args)
	{
		ArrayList<String> operacoes = new ArrayList<String>();
		Random rand  = new Random();
		for(int i =1;i<=10;i++)
		{
			for(int j = 1;j<=100;j++){
				int num = rand.nextInt();
				operacoes.add(String.valueOf(num));
				//Operando de exemplo(necessário para funcionamento da árvore)
				//Mude o operando de "+" para "*" para obter um resultado "infinito"! : )
				operacoes.add("+");
			}
			ArvoreBaskhara raiz2 = new ArvoreBaskhara(null, 0);
			ArvoreBaskhara arvore =  ArvoreBaskhara.CriarArvore(raiz2,operacoes);
			System.out.println(ArvoreBaskhara.NoduloEstaBalanceado(arvore) ? "A árvore está balanceada!" : "A árvore não está balanceda :c");
			String resultado = ArvoreBaskhara.PercorrerArvereBaskhara(arvore);
			try{
				System.out.println("Série: "+ i +" Conta: "+resultado +"\nResultado da conta: "+Eval.eval(resultado)+"\n----------\n");
			}
			catch(Exception e)
			{
					System.out.println("Não foi possível processar o valor desta expressão resultante da leitura da árvore:\n"+ resultado);
			}
			operacoes.clear();
		}
		
	}
	
	
	
	
	
}
