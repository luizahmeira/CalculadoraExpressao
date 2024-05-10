package view;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculadora {
    
    /*
     * metodo que vai extrair os termos do campoExpressão! retorna uma fila bem bonitinha no metodo infixado(na ordem exata da expressao)
     * ... etapa (B) consiste em criar uma fila com todos os termos da expressão, na ordem
     * em que eles aparecem
     */
    
    public FilaLista<String> extrairTermos(String expressao){
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
    private static boolean isOperador(char dado) {
        return dado == '+' || dado == '-' || dado == '*' || dado == '/';
    }

    //fiz esse metodo para arrumar a parte dos calculos com numeros decimais
    private static Double decimal(String dado){
        double dado_formatado = Double.parseDouble(dado.replace(",", "."));
        return dado_formatado;
    }

    //Preciso criar um metodo que me verifique se o caracter da vez seja um operando
    private static boolean isNumero(String dado) {
        try {
            Double.parseDouble(dado.replace(",", "."));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    } 

    //esse metodo vai verificar qual a ordem dos operadores
    private static int precedencia(char caracter) {
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
                return 0; 
        }
    }

    /*
     * tranformando uma fila no modelo infixada em uma fila do modelo pos fixada
     * ...etapa C 
     */
    public FilaLista<String> gerarExprPosfixada(Fila<String> fila) {

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
                System.out.println("Deu algum erro na leitura da lista infixada.");
            }

        }

        //Desempilhando a pilha e adicionando os dados na ordem correta na fila nova
        while (!pilha.estaVazia()) {
            novaFila.inserir(Character.toString(pilha.pop()));
        }

        return novaFila;
    }

    /*
     * Etapa D
     * Esta etapa deverá resolver a expressão aritmética, partindo da fila que foi criada na etapa anterior.
     */
    public double calcularExprPosfixada(Fila<String> exprPosfixada) {

        //criação de uma pilha auxiliar
        PilhaLista<Double> pilhaAuxiliar = new PilhaLista<>();

        Double dadoDouble;

        //loop pela fila pós-fixada
        while (!exprPosfixada.estaVazia()) {

            String dado = exprPosfixada.retirar();
            
            //caso seja um OPERANDO: Empilhe-o na pilha auxiliar
            if (isNumero(dado)) { 
                dadoDouble = decimal(dado); //formata o dado de String para Double
            	pilhaAuxiliar.push(dadoDouble); //empilha o dado formatado
            } 
            //caso seja um OPERADOR: Desempilhe dois operandos da pilha e aplique o operador lido da fila com os operandos que foram desempilhados. Empilhe o resultado na pilha auxiliar.
            else {
                double operando2 = pilhaAuxiliar.pop(); //desempilhando os operadores...
                double operando1 = pilhaAuxiliar.pop(); //desempilhando os operadores...
                double resultado = ExecutarOperador(dado, operando1, operando2);
                pilhaAuxiliar.push(resultado); 
            }
        }

        return pilhaAuxiliar.pop(); //Depois de ter processado todos os termos da lista, a pilha conterá apenas um dado.
    }

    //executar a conta!!
    private static double ExecutarOperador(String operator, double operando1, double operando2) {

        //verificando qual operação realizar de acordo com o operador
        switch (operator) {
            case "+":
                return operando1 + operando2;
            case "-":
                return operando1 - operando2;
            case "*":
                return operando1 * operando2;
            case "/":
                if (operando2 == 0) {
                    throw new ArithmeticException("Não pode haver divisão por 0!!");
                } else {
                    return operando1 / operando2;
                }
            default:
                throw new IllegalArgumentException("Operador inválido!!");
        }
    }
}