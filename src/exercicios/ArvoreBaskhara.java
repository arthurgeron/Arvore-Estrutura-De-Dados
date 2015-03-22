package exercicios;

import java.util.ArrayList;
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
    public static ArvoreBaskhara CriarArvore(ArvoreBaskhara raiz, ArrayList<String> vetorOperacoes)
	{
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Pattern validOperationOrNumber = Pattern.compile("[0-9]{1,}|[+-/*]");
		ArvoreBaskhara noduloAtual = raiz;
		
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
							noduloAtual.direita = new ArvoreBaskhara(operacaoOuNumero);
						}
						else
						{
							noduloAtual.esquerda = new ArvoreBaskhara(operacaoOuNumero);
						}
						if(noduloAtual.direita!=null && noduloAtual.esquerda!=null && noduloAtual.LeftRightBinaryOperator!=null)
						{
							noduloAtual = noduloAtual.esquerda;
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
