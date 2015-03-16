package exercicios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArvoreBaskhara {
    public ArvoreBaskhara(String Number, String NumberOperator,  ArvoreBaskhara direita, String LeftRightBinaryOperator, ArvoreBaskhara esquerda){
        this.LeftRightBinaryOperator = LeftRightBinaryOperator;
        this.Number = Number;
        this.NumberOperator = NumberOperator;
        this.direita = direita;
        this.esquerda = esquerda;
    }
    public ArvoreBaskhara(String Number){
        this.Number = Number;
    }
    String LeftRightBinaryOperator,Number,NumberOperator;
    ArvoreBaskhara direita = null, esquerda = null;
    public void setNoduloEsquerdo(ArvoreBaskhara nodulo) // Adicionar árvore
    {
    	this.esquerda = nodulo;
    }
    public void setNoduloDireito(ArvoreBaskhara nodulo) // Adicionar árvore
    {
    	this.direita = nodulo;
    }
    public void setOperandoDireitoEsquerdo(String operando)
    {
    	this.LeftRightBinaryOperator = operando;
    }
    public void setOperandoNodulo(String operando)
    {
    	this.NumberOperator = operando;
    }
    //Deleta o nódulo e sub nódulos
    public static void DeletarNodulo(ArvoreBaskhara arvereBaskhara)
    {
        try{
        	if(arvereBaskhara.direita!=null)
        	{
        		DeletarNodulo(arvereBaskhara.direita);
        	}
        	if(arvereBaskhara.esquerda!=null)
        	{
        		DeletarNodulo(arvereBaskhara.esquerda);
        	}
        	arvereBaskhara = null;
        }
        catch(Exception ex)
        {
            System.out.println("Erro: "+ ex.getMessage());
        }
        
    }
    //Preencher Nodulo
    public static void CriarArvore(ArvoreBaskhara raiz, String[] numerosSeguidosDeOperandos)
    {
    	int contadorNodulo=0,contadorVetor=0;
    	ArvoreBaskhara noduloAtual = raiz;
    	while(contadorVetor+1<numerosSeguidosDeOperandos.length)
    	{
    		while(contadorNodulo<3)
    		{
    			contadorNodulo++;
    			
    			
    			if(contadorNodulo==1)
    			{
    				
    				//Regex para saber se o próximo elemento é um número ou operando
    			
    				Pattern pattern = Pattern.compile("[0-9]");
    				Matcher matcher = pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]);
    				if(matcher.matches())//Caso a próxima posição seja um número
    				{
    					if(noduloAtual.Number==null && pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]).matches()) 
						{
							noduloAtual.Number = numerosSeguidosDeOperandos[contadorVetor];
							contadorVetor++;
						}
    					if(noduloAtual.NumberOperator==null && !pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]).matches()) 
    					{
    						noduloAtual.NumberOperator = numerosSeguidosDeOperandos[contadorVetor];
    						contadorVetor++;
    					}
    					
    				}
    				else
    				{
    					if(contadorVetor+2<=numerosSeguidosDeOperandos.length){
							if(noduloAtual.Number==null && noduloAtual.NumberOperator==null) {
								noduloAtual.Number = numerosSeguidosDeOperandos[contadorVetor+1];
								noduloAtual.NumberOperator = numerosSeguidosDeOperandos[contadorVetor];
								contadorVetor += 2;
							}
							else if(noduloAtual.NumberOperator==null && !pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]).matches())
							{	
								noduloAtual.NumberOperator = numerosSeguidosDeOperandos[contadorVetor];
								contadorVetor++;
							}
    					}
    				}
    				if(contadorVetor + 7 <= numerosSeguidosDeOperandos.length)
    				{
	    				
    					matcher = pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]);
	    				if(matcher.matches())//Caso a próxima posição seja um número
	    				{
	    					ArvoreBaskhara novoNoduloDireito = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor],numerosSeguidosDeOperandos[contadorVetor+1],new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+2]),numerosSeguidosDeOperandos[contadorVetor+3],new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+4]));
	    					contadorVetor += 5;
	    					noduloAtual.LeftRightBinaryOperator = numerosSeguidosDeOperandos[contadorVetor];
	    					contadorVetor++;
	    					ArvoreBaskhara novoNoduloEsquerdo = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor]);
	    					contadorVetor ++;
	    					noduloAtual.setNoduloDireito(novoNoduloDireito);
	    					noduloAtual.setNoduloEsquerdo(novoNoduloEsquerdo);
	    					noduloAtual = novoNoduloEsquerdo;
	    					break;
	    				}
	    				else // Caso a próxima posição seja um operando
	    				{   //criar apenas um numero no esquerdo
	    					ArvoreBaskhara novoNoduloDireito = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+1],numerosSeguidosDeOperandos[contadorVetor],new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+3]),numerosSeguidosDeOperandos[contadorVetor+2],new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+5]));
	    					noduloAtual.LeftRightBinaryOperator = numerosSeguidosDeOperandos[contadorVetor+4];
	    					contadorVetor += 6;
	    					//Porque apenas 1 variável na criação do nódulo esquerdo?
	    					//Porque esta árvore é ordenada, e portanto, irá sempre preencher o nódulo da direita completamente e irá preencher
	    					//o nódulo da esquerda parcialmente, de forma que ele será o próximo nódulo a ser preenchido caso
	    					// ainda tenham posições suficientes no vetor a serem lidas.
	    					ArvoreBaskhara novoNoduloEsquerdo = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor]);
	    					contadorVetor++;
	    					noduloAtual.setNoduloDireito(novoNoduloDireito);
	    					noduloAtual.setNoduloEsquerdo(novoNoduloEsquerdo);
	    					noduloAtual = novoNoduloEsquerdo;
	    					break;
	    				}
    				
    				}
    				else if(contadorVetor + 4 <= numerosSeguidosDeOperandos.length)	
        			{
        				
    					matcher = pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]);
	    				if(matcher.matches())//Caso a próxima posição seja um número
	    				{
	    					ArvoreBaskhara novoNoduloDireito = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor]);
	    					noduloAtual.LeftRightBinaryOperator = numerosSeguidosDeOperandos[contadorVetor+1];
	    					ArvoreBaskhara novoNoduloEsquerdo = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+2]);
	    					novoNoduloEsquerdo.NumberOperator = numerosSeguidosDeOperandos[contadorVetor+3];
	    					contadorVetor += 4;
	    					noduloAtual.setNoduloDireito(novoNoduloDireito);
	    					noduloAtual.setNoduloEsquerdo(novoNoduloEsquerdo);
	    					noduloAtual = novoNoduloEsquerdo;
	    					break;
	    					
	    				}
	    				else // Caso a próxima posição seja um operando
	    				{   
	    					ArvoreBaskhara novoNoduloDireito = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+1]);
	    					noduloAtual.LeftRightBinaryOperator = numerosSeguidosDeOperandos[contadorVetor];
	    					ArvoreBaskhara novoNoduloEsquerdo = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+3]);
	    					novoNoduloEsquerdo.NumberOperator = numerosSeguidosDeOperandos[contadorVetor+2];
	    					contadorVetor += 4;
	    					noduloAtual.setNoduloDireito(novoNoduloDireito);
	    					noduloAtual.setNoduloEsquerdo(novoNoduloEsquerdo);
	    					noduloAtual = novoNoduloEsquerdo;
	    					break;
	    				}
        			}
    				else if(contadorVetor + 2 <= numerosSeguidosDeOperandos.length)
    				{
    					matcher = pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]);
	    				if(matcher.matches())//Caso a próxima posição seja um número
	    				{
	    					try{
	    						ArvoreBaskhara novoNoduloEsquerdo = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+1]);
	    						contadorVetor++;
	    						try{
		    						novoNoduloEsquerdo.NumberOperator = numerosSeguidosDeOperandos[contadorVetor+1];
		    						contadorVetor++;
		    					}
		    					catch(Exception ex)
		    					{
		    						
		    					}
	    						if(noduloAtual.esquerda!=null){ noduloAtual.setNoduloEsquerdo(novoNoduloEsquerdo);} else{noduloAtual.setNoduloDireito(novoNoduloEsquerdo); };
		    					noduloAtual = novoNoduloEsquerdo;
		    					break;
	    					}
	    					catch(Exception ex)
	    					{
	    					
	    					}
    						
	    					
	    				}
	    				else // Caso a próxima posição seja um operando
	    				{   
	    					ArvoreBaskhara novoNoduloEsquerdo = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+2]);
	    					novoNoduloEsquerdo.NumberOperator = numerosSeguidosDeOperandos[contadorVetor+1];
	    					contadorVetor += 2;
	    					if(noduloAtual.esquerda!=null){ noduloAtual.setNoduloEsquerdo(novoNoduloEsquerdo);} else{noduloAtual.setNoduloDireito(novoNoduloEsquerdo); };
	    					noduloAtual = novoNoduloEsquerdo;
	    					break;
	    				}
    				}
    				else if(contadorVetor + 1 <= numerosSeguidosDeOperandos.length)
    				{
    					matcher = pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]);
	    				if(matcher.matches())//Caso a próxima posição seja um número
	    				{
	    					ArvoreBaskhara novoNoduloEsquerdo = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+1]);
	    					contadorVetor++;
	    					if(noduloAtual.esquerda!=null){ noduloAtual.setNoduloEsquerdo(novoNoduloEsquerdo);} else{noduloAtual.setNoduloDireito(novoNoduloEsquerdo); };
	    					noduloAtual = novoNoduloEsquerdo;
	    					break;
	    				}
	    				else
	    				{
	    					contadorVetor++;
	    					break;
	    				}
    					
    				}
    				else
    				{
    					if(pattern.matcher(numerosSeguidosDeOperandos[contadorVetor]).matches())
    					{
    						ArvoreBaskhara novoNoduloEsquerdo = new ArvoreBaskhara(numerosSeguidosDeOperandos[contadorVetor+1]);
    						contadorVetor++;
    						if(noduloAtual.esquerda!=null){ if(noduloAtual.esquerda!=null){ noduloAtual.setNoduloEsquerdo(novoNoduloEsquerdo);} else{noduloAtual.setNoduloDireito(novoNoduloEsquerdo); };} else{noduloAtual.setNoduloDireito(novoNoduloEsquerdo); };
    					}
    				}
    				
    			}
    		}
    		contadorNodulo = 0;
    	}
    }
    //Percorre a arvore
    public static String PercorrerArvereBaskhara(ArvoreBaskhara arvereBaskhara)
    {
        try{
        	String resultado;
            if(arvereBaskhara.direita == null && arvereBaskhara.esquerda == null)
            {
            	resultado = arvereBaskhara.Number;
                return resultado.replace("--", "+").replace("++", "+");
            }
            else if(arvereBaskhara.direita != null && arvereBaskhara.esquerda == null)
            {
               
            	resultado ="(" + arvereBaskhara.Number + arvereBaskhara.NumberOperator + PercorrerArvereBaskhara(arvereBaskhara.direita) + ")";
            	return resultado.replace("--", "+").replace("++", "+");
            }
            else if(arvereBaskhara.direita == null && arvereBaskhara.esquerda != null)
            {
               resultado = "(" + arvereBaskhara.Number + arvereBaskhara.NumberOperator + PercorrerArvereBaskhara(arvereBaskhara.esquerda) + ")";
               return resultado.replace("--", "+").replace("++", "+");
            }
            else
            {
                resultado = "(" + arvereBaskhara.Number + arvereBaskhara.NumberOperator + "(" + PercorrerArvereBaskhara(arvereBaskhara.direita) + arvereBaskhara.LeftRightBinaryOperator + PercorrerArvereBaskhara(arvereBaskhara.esquerda) + ")"+ ")";
                return resultado.replace("--", "+").replace("++", "+");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Erro: "+ ex.getMessage());
            return "0";
        }
        
    }
    ///Le equações em string e retorna o resultado
    public static double eval(final String str) {
        class Parser {
            int pos = -1, c;

            void eatChar() {
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            void eatSpace() {
                while (Character.isWhitespace(c)) eatChar();
            }

            double parse() {
                eatChar();
                double v = parseExpression();
                if (c != -1) throw new RuntimeException("Unexpected: " + (char)c);
                return v;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor | term brackets
            // factor = brackets | number | factor `^` factor
            // brackets = `(` expression `)`

            double parseExpression() {
                double v = parseTerm();
                for (;;) {
                    eatSpace();
                    if (c == '+') { // addition
                        eatChar();
                        v += parseTerm();
                    } else if (c == '-') { // subtraction
                        eatChar();
                        v -= parseTerm();
                    } else {
                        return v;
                    }
                }
            }

            double parseTerm() {
                double v = parseFactor();
                for (;;) {
                    eatSpace();
                    if (c == '/') { // division
                        eatChar();
                        v /= parseFactor();
                    } else if (c == '*' || c == '(') { // multiplication
                        if (c == '*') eatChar();
                        v *= parseFactor();
                    } else {
                        return v;
                    }
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;
                eatSpace();
                if (c == '(') { // brackets
                    eatChar();
                    v = parseExpression();
                    if (c == ')') eatChar();
                } else { // numbers
                    if (c == '+' || c == '-') { // unary plus & minus
                        negate = c == '-';
                        eatChar();
                        eatSpace();
                    }
                    StringBuilder sb = new StringBuilder();
                    while ((c >= '0' && c <= '9') || c == '.') {
                        sb.append((char)c);
                        eatChar();
                    }
                    if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)c);
                    v = Double.parseDouble(sb.toString());
                }
                eatSpace();
                if (c == '^') { // exponentiation
                    eatChar();
                    v = Math.pow(v, parseFactor());
                }
                if (negate) v = -v; // exponentiation has higher priority than unary minus: -3^2=-9
                return v;
            }
        }
        return new Parser().parse();
    }
    
    
}
