package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * apenas uma classe que eu estava testando tudo
 */
public class Teste {

    //metodo que vai extrair os termos do campoExpressão! retorna uma fila bem bonitinha no metodo infixado(na ordem exata da expressao)
    public static FilaLista<String> extrairTermos(String expressao){
        //criação da fila!
        FilaLista<String> fila = new FilaLista<>();

        //Utilizando da criação de uma ER (Expressão Regular) para definir os padrões de encontro dos caracteres!!! (compiladores dando frutos aqui)
        String caracteres = "-?\\d*\\,?\\d+|[+\\-*/()]";

        //Definindo um padrão para encontrar os números inteiros, decimais, operadores e afins
        Pattern padrao = Pattern.compile(caracteres);

        //agora vamos encontrar os exementos da expressao apartir dos padrões definidos
        Matcher matcher = padrao.matcher(expressao);

        while (matcher.find()) {
            fila.inserir(matcher.group()); //adicionando a uma fila
        }

        return fila;
        
    }

    //Preciso criar um metodo que me verifique se o caracter da vez seja um operador
    public static boolean isOperador(char dado) {
        return dado == '+' || dado == '-' || dado == '*' || dado == '/';
    }

    //Preciso criar um metodo que me verifique se o caracter da vez seja um operando
    public static boolean isNumero(String dado) {
        try {
            Double.parseDouble(dado.replace(",", "."));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    

    //esse metodo vai verificar qual a ordem dos operadores
    public static int precedencia(char caracter) {
        switch (caracter) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default: 
                return 0; //definindo o (como menor precedencia)
        }
    }

    // Método para converter expressão infix para postfix
    public static FilaLista<String> gerarExprPosfixada(Fila<String> fila) {

        //criando uma nova lista para armazenar os termos na ordem pos fixada
        FilaLista<String> novaFila = new FilaLista<>();

        //criando uma pilha!! para armazenar a ordem dos operadores
        PilhaLista<Character> pilha = new PilhaLista<>();

        //loop pela lista infixada e adicionando os dados em uma pilha de acordo com seu precedencia
        while (!fila.estaVazia()) {

            //retirando o dado da fila e guardando seu valor na variável dado
            String dado = fila.retirar();
            
            //SE O DADO FOR UM NÚMERO
            //se o caracter for um operando(ou seja, um numero(int ou float)) ele ja adiciona na fila nova
            if (isNumero(dado)) {
                novaFila.inserir(dado);
            } 

            //SE O DADO FOR '('
            //se o dado for uma parentese abrindo, vai adicionar ele à pilha!
            else if (dado.charAt(0) == '(') {
                pilha.push('(');
            } 

            //SE O DADO FOR ')'
            //se o dado for um parentese fechando ele vai desempilhar os operadores da pilha até achar o parenteses abrindo
            else if (dado.charAt(0) == ')') {

                while (!pilha.estaVazia() && pilha.peek() != '(') {
                    novaFila.inserir(Character.toString(pilha.pop()));
                }

                pilha.pop(); // Descartar '('
            } 

            //SE O DADO FOR UM OPERADOR
            //se for um operador, ele vai desempilhar os operadores da pilha que tem procedencia maior ou igual a do dado atual
            else if (isOperador(dado.charAt(0))) {

                while (!pilha.estaVazia() && pilha.peek() != '(' && precedencia(pilha.peek()) >= precedencia(dado.charAt(0))) {
                    novaFila.inserir(Character.toString(pilha.pop()));
                }

                pilha.push(dado.charAt(0));
            }

            //caso dê merda
            else { 
                System.out.println("Deu algum erro ai na leitura da lista infixada.");
            }

        }

        //Desempilhando a pilha e adicionando os dados na ordem correta na fila nova
        while (!pilha.estaVazia()) {
            novaFila.inserir(Character.toString(pilha.pop()));
        }

        return novaFila;
    }

}
