package br.com.seuzestore.systems.model;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CPF {
	//padrão CPF
	static String REGEX = "^(\\d{3})\\.?(\\d{3})\\.?(\\d{3})\\-?(\\d{2}$)$|^(\\d{11})";
						  
	static Pattern FORMAT = Pattern.compile(REGEX) ;
	static Boolean answer = true;
	
           
	                
	public static Boolean isCPF(String cpf) {
        Character letter;
        
		for(int i=0; i<cpf.length(); i++) {
			letter = cpf.charAt(i);
			if(!Character.isDigit(letter)) {
				answer = false;
				break;
			}
		}
		
		if ((cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999"))) {
        	answer = false;
        	System.out.println("Padrão CPF incorreto");
        }else {
        	 Matcher check = FORMAT.matcher(cpf);
             if(check.find()){
             	
             	answer = true;
             }else {
             	System.out.println("Padrão CPF incorreto");
             	answer = false;
             }
        	
        }
        
       
        return answer;
	}
        
	
            
	public static Boolean checkCpf(String cpf) {
        char pos10, pos11;
        int sum, i, result, num, weight;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sum = 0;
            weight = 10;
            for (i=0; i<9; i++) {
            	// converte o i-esimo caractere do CPF em um numero:
            	// por exemplo, transforma o caractere '0' no inteiro 0
            	// (48 eh a posicao de '0' na tabela ASCII)
            	if(cpf.charAt(i) != '.' || cpf.charAt(i) != '-' || cpf.charAt(i) != '/') {
            		num = (int)(cpf.charAt(i) - 48);
            		sum = sum + (num * weight);
            		weight = weight - 1;
            	}
            }

            result = 11 - (sum % 11);
            if ((result == 10) || (result == 11))
                pos10 = '0';
            else pos10 = (char)(result + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sum = 0;
            weight = 11;
            for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            sum = sum + (num * weight);
            weight = weight - 1;
            }

            result = 11 - (sum % 11);
            if ((result == 10) || (result == 11))
                 pos11 = '0';
            else pos11 = (char)(result + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((pos10 == cpf.charAt(9)) && (pos11 == cpf.charAt(10)))
            	answer = true;
            else answer = false;
                } catch (InputMismatchException erro) {
                	System.out.println("CPF inválido!!!");
                	answer = false;
            }
        return answer;
        }
	
	public static String formatCpf(String cpf) {
		String answer = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
		return answer;
		
	}


}
