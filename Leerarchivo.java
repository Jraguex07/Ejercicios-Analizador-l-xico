
package leerarchivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leerarchivo {
    public static void main(String[] args) {
        String filename = "tu_archivo.cpp";
        List<String> keywords = Arrays.asList("while", "if", "return", "cout", "cin"); // Lista de palabras clave

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\SEMESTRE 6\\COMPILADORES\\JAVA\\Anali_lexico\\src\\anali_lexico\\Analizador.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+|\\b");
                for (String token : tokens) {
                    token = token.trim();
                    if (token.isEmpty()) {
                        continue;
                    }

                    if (keywords.contains(token)) {
                        System.out.println("Palabra clave: " + token);
                    } else if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                        System.out.println("Identificador: " + token);
                    } else if (token.matches("\\+|\\-|\\*|\\/|%|&&|\\|\\||>|<|==|!=")) {
                        System.out.println("Operador: " + token);
                    } else if (token.matches("\\{|\\}|\\(|\\)|\\[|\\]")) {
                        System.out.println("Símbolo especial: " + token);
                    } else {
                        // Puedes agregar más patrones para otros tipos de tokens
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

