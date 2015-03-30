package exercicios;

import java.util.Scanner;
import java.lang.Math;

public class Exercicio1 {

    /**
     * @param args the command line arguments
     */
      public static void main ( String args [] )  
    {  
    	Scanner scanner = new Scanner(System.in);
    	String a = "",b = "" ,c = ""; // Variáveis da equação
    	System.out.println("Digite separando por ENTER o valor de A, B e C da equação de segundo grau.\nOBS: números negativos são aceitos.");
    	//leitura do valor das variáveis
    	a = scanner.next();
        b = scanner.next();
        c = scanner.next();
        scanner.close();
        
       //Cria o nódlo com a parte 4AC de delta
        ArvoreBaskhara nodulo4ACDelta = new ArvoreBaskhara("4","*",new ArvoreBaskhara(a, 0),"*",new ArvoreBaskhara(c, 0),0);
       //Calcula o valor de delta b²-4ac e salva em double
        Double resultadoEequacaoDelta = Eval.eval(PercorrerArvereBaskhara(new ArvoreBaskhara("1","*",new ArvoreBaskhara(b+"*"+b,0), "-" , nodulo4ACDelta, 0)));
        //Cria o nódulo com o resultado da raiz quadrada de delta
        ArvoreBaskhara noduloDelta = new ArvoreBaskhara(String.valueOf(Math.sqrt(resultadoEequacaoDelta)),0);
        //Cria o nódulo com -B + Raiz quadrada de Delta
        ArvoreBaskhara nodulo = new ArvoreBaskhara("-"+b,"+",new ArvoreBaskhara("1", 0), "*" , noduloDelta,0);
        //Cria a raiz, com a equação completa
        ArvoreBaskhara raiz = new ArvoreBaskhara("1","*",nodulo,"/",new ArvoreBaskhara("2 * "+a, 0),0);
       //Cria um nódulo com a mesma equação de 'nodulo' porém com o a raiz de delta negativa
        ArvoreBaskhara noduloDeltaNegativo = new ArvoreBaskhara("-"+b,"-",new ArvoreBaskhara("1", 0), "*" , noduloDelta,0);
      try
      {
        if(resultadoEequacaoDelta<0) // Verifica se o delta é negativo
        {
        	System.out.println("A equação não tem solução, pois o delta é negativo.");
        }
        else
        {
	        System.out.println("Equação com delta positivo: "+ PercorrerArvereBaskhara(raiz));
	        System.out.println("O valor de x0 é : " + Eval.eval(PercorrerArvereBaskhara(raiz)));
	        raiz.setNoduloDireito(noduloDeltaNegativo);
	        System.out.println("Equação com delta negativo: "+ PercorrerArvereBaskhara(raiz));
	        System.out.println("O valor de x1 é : " + Eval.eval(PercorrerArvereBaskhara(raiz)));
        }
      }
      catch(Exception exception)
      {
    	  System.out.println("O sistema não conseguiu calcular o valor desta equação de baskhara.\n Verifique o formato das informações digitadas e tente novamente.");
      }
    }  
      //Percorre a árvore
    public static String PercorrerArvereBaskhara(ArvoreBaskhara arvereBaskhara)
    {
        try{
        	String resultado;
            if(arvereBaskhara.direita == null && arvereBaskhara.esquerda == null)
            {
            	resultado = arvereBaskhara.Number;
                return resultado.replace("--", "+");
            }
            else if(arvereBaskhara.direita != null && arvereBaskhara.esquerda == null)
            {
               
            	resultado ="(" + arvereBaskhara.Number + arvereBaskhara.NumberOperator + PercorrerArvereBaskhara(arvereBaskhara.direita) + ")";
            	return resultado.replace("--", "+");
            }
            else if(arvereBaskhara.direita == null && arvereBaskhara.esquerda != null)
            {
               resultado = "(" + arvereBaskhara.Number + arvereBaskhara.NumberOperator + PercorrerArvereBaskhara(arvereBaskhara.esquerda) + ")";
               return resultado.replace("--", "+");
            }
            else
            {
                resultado = "(" + arvereBaskhara.Number + arvereBaskhara.NumberOperator + "(" + PercorrerArvereBaskhara(arvereBaskhara.direita) + arvereBaskhara.LeftRightBinaryOperator + PercorrerArvereBaskhara(arvereBaskhara.esquerda) + ")"+ ")";
                return resultado.replace("--", "+");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Erro: "+ ex.getMessage());
            return "0";
        }
        
    }
      
    
}
