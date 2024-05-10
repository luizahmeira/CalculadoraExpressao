package view;

public class FilaLista <T> implements Fila<T> {

    private ListaEncadeada<T> lista;

    public FilaLista(){
        lista = new ListaEncadeada<>();
    }

    /*
     * Retorna o elemento que está no inicio da fila
     * @return dado que está no inicio da fila
     */
    @Override
    public T peek() {
        if (estaVazia()){
            throw new FilaVaziaException();
        }
        return lista.getPrimeiro().getInfo();
    }

    /*
     * Insere um elemento na fila
     */
    @Override
    public void inserir(T valor) {
       lista.inserirNoFinal(valor);
    }

    public void inserirNoFinal(T valor){
        lista.inserirNoFinal(valor);
    }

    /*
     * avalia se a lista está vazia
     */
    @Override
    public boolean estaVazia() {
       return lista.EstaVazia();
    }

    /*
     * retira um elemento da fila
     */
    @Override
    public T retirar() {
        T valor;
        valor = peek();
        lista.retirar(valor);

        return valor;
    }

    /*
     * retira todos os elementos da fila
     */
    @Override
    public void liberar() {
    	lista = new ListaEncadeada<>();
       
    }

    /*
     *Retorna representação textual da fila, partindo 
     do inicio até o final.
     Os dados são separados por vírgula
     @return conteúdo da fila
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        NoLista<T> atual = lista.getPrimeiro();
        while (atual != null) {
            builder.append(atual.getInfo());
            if (atual.getProx() != null) {
                builder.append(", ");
            }
            atual = atual.getProx();
        }
        builder.append("]");
        return builder.toString();
    }


    
}
