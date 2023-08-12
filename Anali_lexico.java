
package anali_lexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Anali_lexico {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\SEMESTRE 6\\COMPILADORES\\JAVA\\Anali_lexico\\src\\anali_lexico\\entrada.txt"));
            String inputLine = reader.readLine();
            reader.close();
            
            ArrayList<Token> tokens = Anali_lexico(inputLine);
            
            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de entrada.");
        }
    }
    
    public static ArrayList<Token> Anali_lexico(String input) {
        ArrayList<Token> tokens = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("\\d+\\.\\d+|\\d+|\\*|\\+|\\(|\\)");
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            String tokenValue = matcher.group();
            TokenType tokenType = identifyTokenType(tokenValue);
            
            if (tokenType == TokenType.INVALID) {
                System.out.println("Error: Caracter no válido encontrado: " + tokenValue);
                tokens.clear();
                break;
            }
            
            tokens.add(new Token(tokenType, tokenValue));
        }
        
        return tokens;
    }
    
    public static TokenType identifyTokenType(String tokenValue) {
        if (tokenValue.matches("\\d+\\.\\d+")) {
            return TokenType.NUMERO_DECIMAL;
        } else if (tokenValue.matches("\\d+")) {
            return TokenType.NUMERO_ENTERO;
        } else if (tokenValue.equals("*") || tokenValue.equals("+") || tokenValue.equals("(") || tokenValue.equals(")")) {
            return TokenType.OPERADOR_O_PARENTESES;
        } else {
            return TokenType.INVALID;
        }
    }
}

enum TokenType {
    NUMERO_DECIMAL,
    NUMERO_ENTERO,
    OPERADOR_O_PARENTESES,
    INVALID
}

class Token {
    private TokenType type;
    private String value;
    
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return type + "\t\t" + value;
    }
}
