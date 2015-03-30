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
    	String a = "",b = "" ,c = ""; // Vari�veis da equa��o
    	System.out.println("Digite separando por ENTER o valor de A, B e C da equa��o de segundo grau.\nOBS: n�meros negativos s�o aceitos.");
    	//leitura do valor das vari�veis
    	a = scanner.next();
        b = scanner.next();
        c = scanner.next();
        scanner.close();
        
       //Cria o n�dlo com a parte 4AC de delta
        ArvoreBaskhara nodulo4ACDelta = new ArvoreBaskhara("4","*",new ArvoreBaskhara(a, 0),"*",new ArvoreBaskhara(c, 0),0);
       //Calcula o valor de delta b�-4ac e salva em double
        Double resultadoEequacaoDelta = Eval.eval(PercorrerArvereBaskhara(new ArvoreBaskhara("1","*",new ArvoreBaskhara(b+"*"+b,0), "-" , nodulo4ACDelta, 0)));
        //Cria o n�dulo com o resultado da raiz quadrada de delta
        ArvoreBaskhara noduloDelta = new ArvoreBaskhara(String.valueOf(Math.sqrt(resultadoEequacaoDelta)),0);
        //Cria o n�dulo com -B + Raiz quadrada de Delta
        ArvoreBaskhara nodulo = new ArvoreBaskhara("-"+b,"+",new ArvoreBaskhara("1", 0), "*" , noduloDelta,0);
        //Cria a raiz, com a equa��o completa
        ArvoreBaskhara raiz = new ArvoreBaskhara("1","*",nodulo,"/",new ArvoreBaskhara("2 * "+a, 0),0);
       //Cria um n�dulo com a mesma equa��o de 'nodulo' por�m com o a raiz de delta negativa
        ArvoreBaskhara noduloDeltaNegativo = new ArvoreBaskhara("-"+b,"-",new ArvoreBaskhara("1", 0), "*" , noduloDelta,0);
      try
      {
        if(resultadoEequacaoDelta<0) // Verifica se o delta � negativo
        {
        	System.out.println("A equa��o n�o tem solu��o, pois o delta � negativo.");
        }
        else
        {
	        System.out.println("Equa��o com delta positivo: "+ PercorrerArvereBaskhara(raiz));
	        System.out.println("O valor de x0 � : " + Eval.eval(PercorrerArvereBaskhara(raiz)));
	        raiz.setNoduloDireito(noduloDeltaNegativo);
	        System.out.println("Equa��o com delta negativo: "+ PercorrerArvereBaskhara(raiz));
	        System.out.println("O valor de x1 � : " + Eval.eval(PercorrerArvereBaskhara(raiz)));
        }
      }
      catch(Exception exception)
      {
    	  System.out.println("O sistema n�o conseguiu calcular o valor desta equa��o de baskhara.\n Verifique o formato das informa��es digitadas e tente novamente.");
      }
    }  
      //Percorre a �rvore
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
