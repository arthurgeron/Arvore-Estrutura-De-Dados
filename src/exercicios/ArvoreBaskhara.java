package exercicios;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ArvoreBaskhara {
	String LeftRightBinaryOperator,Number,NumberOperator;
    ArvoreBaskhara direita = null, esquerda = null;
    int comprimentoNo;
	
	public ArvoreBaskhara(String Number, String NumberOperator,  ArvoreBaskhara direita, String LeftRightBinaryOperator, ArvoreBaskhara esquerda, int comprimentoNoAnterior){
        this.LeftRightBinaryOperator = LeftRightBinaryOperator;
        this.Number = Number;
        this.NumberOperator = NumberOperator;
        this.direita = direita;
        this.esquerda = esquerda;
       comprimentoNo = comprimentoNoAnterior + 1;
    }
    public ArvoreBaskhara(String Number, int comprimentoNoAnterior){
        this.Number = Number;
        comprimentoNo = comprimentoNoAnterior + 1;
    }
    
    
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
    public static ArvoreBaskhara acharExtremidadeDesbalanceada(ArvoreBaskhara raiz)
    {
    	ArvoreBaskhara noduloAtual = raiz;
    	if(noduloAtual.direita!=null && noduloAtual.esquerda!=null)
    	{
    		noduloAtual = CalcularMaiorComprimento(noduloAtual.direita)<=CalcularMaiorComprimento(noduloAtual.esquerda) ? noduloAtual.direita : noduloAtual.esquerda; 
    	}
    	else if(noduloAtual.direita == null || noduloAtual.esquerda == null)
    		return noduloAtual;
		while(true)
    	{
        	if(noduloAtual == null)
        		return noduloAtual;
        	if(noduloAtual.direita!=null && noduloAtual.esquerda!=null)
        	{
        		noduloAtual = CalcularMaiorComprimento(noduloAtual.direita)>=CalcularMaiorComprimento(noduloAtual.esquerda) ? noduloAtual.direita : noduloAtual.esquerda; 
        	}
        	else if(noduloAtual.esquerda!=null)
        	{
        		noduloAtual = noduloAtual.esquerda;
        	}
        	else if(noduloAtual.direita != null)
        	{
        		noduloAtual = noduloAtual.direita;
        	}
        	else
        		break;
    	}
		return noduloAtual;
    }
    
    public static int CalcularMaiorComprimento(ArvoreBaskhara nodulo)
    {
    	if(nodulo == null)
    		return 0;
    	if(nodulo.direita!=null && nodulo.esquerda!=null)
    	{
    		return CalcularMaiorComprimento(nodulo.direita)>=CalcularMaiorComprimento(nodulo.esquerda) ? CalcularMaiorComprimento(nodulo.direita) : CalcularMaiorComprimento(nodulo.esquerda); 
    	}
    	else if(nodulo.esquerda!=null)
    	{
    		return CalcularMaiorComprimento(nodulo.esquerda);
    	}
    	else if(nodulo.direita != null)
    	{
    		return CalcularMaiorComprimento(nodulo.direita);
    	}
    	else
    		return nodulo.comprimentoNo;
    }
    
    public static boolean NoduloEstaBalanceado(ArvoreBaskhara nodulo){
    	if(nodulo == null)
    		return true;
    	int resultado  = CalcularMaiorComprimento(nodulo.esquerda)>= CalcularMaiorComprimento(nodulo.direita) ?  CalcularMaiorComprimento(nodulo.esquerda) -  CalcularMaiorComprimento(nodulo.direita) :  CalcularMaiorComprimento(nodulo.direita) -  CalcularMaiorComprimento(nodulo.esquerda);
    	if(resultado>=-1 && resultado <= 1)
    		return true;
    	else 
    		return false;
    }
    //Preencher Nodulo
    public static ArvoreBaskhara CriarArvore(ArvoreBaskhara raiz, ArrayList<String> vetorOperacoes)
	{
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Pattern validOperationOrNumber = Pattern.compile("[0-9]{1,}|[+-/*]");
		ArvoreBaskhara noduloAtual = raiz, noduloAnterior = null;
		
		for(String operacaoOuNumero : vetorOperacoes)
		{
			if(validOperationOrNumber.matcher(operacaoOuNumero).matches())
			{
				if(noduloAtual.Number == null)
				{
					if(pattern.matcher(operacaoOuNumero).matches())
					{
						noduloAtual.Number = operacaoOuNumero;
					}
				}
				else if ( noduloAtual.NumberOperator == null)
				{
					if(!pattern.matcher(operacaoOuNumero).matches())
					{
						noduloAtual.NumberOperator = operacaoOuNumero;
					}
				}
				else if(pattern.matcher(operacaoOuNumero).matches())
				{
						if(noduloAtual.direita==null)
						{
							noduloAtual.direita = new ArvoreBaskhara(operacaoOuNumero,noduloAtual.comprimentoNo);
							if(!NoduloEstaBalanceado(raiz))
							{
								noduloAtual = acharExtremidadeDesbalanceada(raiz);
							}
							else if(!NoduloEstaBalanceado(noduloAnterior))
							{
								noduloAtual = acharExtremidadeDesbalanceada(noduloAnterior);
							}
						}
						else if(noduloAtual.esquerda == null)
						{
							noduloAtual.esquerda = new ArvoreBaskhara(operacaoOuNumero,noduloAtual.comprimentoNo);
							if(!NoduloEstaBalanceado(raiz))
							{
								noduloAtual = acharExtremidadeDesbalanceada(raiz);
							}
							else if(!NoduloEstaBalanceado(noduloAnterior))
							{
								noduloAtual = acharExtremidadeDesbalanceada(noduloAnterior);
							}
						}
						if(noduloAtual.direita!=null && noduloAtual.esquerda!=null)
						{
							if(CalcularMaiorComprimento(noduloAtual.direita)> CalcularMaiorComprimento(noduloAtual.esquerda))
							{
								noduloAtual = noduloAtual.esquerda;
							}
							else
							{
								noduloAtual = noduloAtual.direita;
							}
							if(!NoduloEstaBalanceado(raiz))
							{
								noduloAtual = acharExtremidadeDesbalanceada(raiz);
							}
							else if(!NoduloEstaBalanceado(noduloAnterior))
							{
								noduloAtual = acharExtremidadeDesbalanceada(noduloAnterior);
							}
						}
				}
				else // Caso seja um símbolo
				{
					if( noduloAtual.LeftRightBinaryOperator == null)
					{
						noduloAtual.LeftRightBinaryOperator = operacaoOuNumero;
					}
				}
			}
			noduloAnterior = noduloAtual;
		}
		return raiz;
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
            else if(arvereBaskhara.LeftRightBinaryOperator != null)
            {
                resultado = "(" + arvereBaskhara.Number + arvereBaskhara.NumberOperator + "(" + PercorrerArvereBaskhara(arvereBaskhara.direita) + arvereBaskhara.LeftRightBinaryOperator + PercorrerArvereBaskhara(arvereBaskhara.esquerda) + ")"+ ")";
                return resultado.replace("--", "+").replace("++", "+");
            }
            else if(arvereBaskhara.NumberOperator != null)
            {
            	resultado = "(" + arvereBaskhara.Number + arvereBaskhara.NumberOperator + "(" + PercorrerArvereBaskhara(arvereBaskhara.direita) + arvereBaskhara.NumberOperator + PercorrerArvereBaskhara(arvereBaskhara.esquerda) + ")"+ ")";
                return resultado.replace("--", "+").replace("++", "+");
            }
            else
            {
            	return "(0)";
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
