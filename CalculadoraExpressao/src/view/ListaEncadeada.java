package view;

public class ListaEncadeada <T> {

    //LISTA ENCADEADA
    private NoLista<T> primeiro;
    private NoLista<T> ultimo;

    //construtor padrao cria lista vazia
    public ListaEncadeada(){
        primeiro = null;
        ultimo = null;
    }

    /*
     * return Primeiro nó da lista
     */
    public NoLista<T> getPrimeiro(){
        return primeiro;
    }
    
    /*
     * Insere um novo valor na lista
     * 
     * @param info valor a ser inserido na lista
     */
    public void inserir(T valor) {

        NoLista<T> novo = new NoLista<T>();

        novo.setInfo(valor);
        novo.setProx(primeiro);


        if(EstaVazia()){
            ultimo = novo;
        }

        this.primeiro = novo;
    }

    /*
     * exibe o conteúdo da lista encadeada
     */
    public void exibir(){
        NoLista<T> p = primeiro;
        while(p != null){
            System.out.println(p.getInfo());
            p = p.getProx();
        }
    }

    /*
     * Avalia se a lista está vazia ou não
     * 
     * @return True se a lista estiver vazia
     */
    public boolean EstaVazia() {
        return primeiro == null;

    }
    
    /*
     * Busca um nó na Lista encadeada
     * 
     * @param info dado a ser localizado
     * 
     * @return nó contendo o dado a ser localizado
     */

    public NoLista<T> buscar(T info){
        NoLista<T> p = primeiro;

        while(p != null){
            if(p.getInfo().equals(info)){
                return p;
            }

            p = p.getProx();
        }

        return null;
    }


    /*
     * Retira um dado na lista encadeada
     * 
     * @param info
     * Dado a ser retirado da lista encadeada
     */
    public void retirar(T valor) {
        NoLista<T> p = primeiro; 
        NoLista<T> anterior = null;
        

        while ((p != null) && (!p.getInfo().equals(valor))) { 
            anterior = p; //12
            p = p.getProx(); //pulando para o prox nó
        }
       
        if (p != null){
            if(anterior == null){
                this.primeiro = p.getProx();
            } else {
                anterior.setProx(p.getProx());
            }

            if(p == ultimo){
                ultimo = anterior;
            }
        } 
    }

    /*
     * retorna o valor de Ultimo
     */
    public T obterUltimo(){
        return ultimo.getInfo();
    }

    /*FAVOR VERIFICAR EU QUE FIZ */
    public boolean equals(Object lista){
        if(this == lista){
            return true;
        }

        if(lista == null){
            return false;
        }

        if(getClass() != lista.getClass()){
            return false;
        }

        ListaEncadeada<T> outraLista = (ListaEncadeada<T>) lista;   

        NoLista<T> p1 = this.getPrimeiro();
        NoLista<T> p2 = outraLista.getPrimeiro();

        while((p1 != null) && (p2 != null)){
            if(!(p1.getInfo().equals(p2.getInfo()))){
                return false;
            }

            p1 = p1.getProx();
            p2 = p2.getProx();

            if(!(p1.getInfo().equals(p2.getInfo()))){
                return false;
            }
        }

        return true;
    }

    /*
     * Insere um dado no final da lista encadeada
     * 
     * @param valor a ser inserido
     */
    public void inserirNoFinal(T valor){
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(valor);
        novo.setProx(null);

        if (EstaVazia()){
            primeiro = novo;
        } else {
            ultimo.setProx(novo);
        }

        ultimo = novo;
    }

    
    public String ToString() {

        String resultado = "";
        NoLista<T> p = primeiro;

        while (p != null) {
            if (p != primeiro) {
                resultado += ",";
            }
            
            resultado += p.getInfo().toString();
            p = p.getProx();
        }

        return resultado;


    }

    public int ObterComprimento(){

        NoLista<T> p = primeiro;

        int qtddeNos = 0;

        while(p!= null){
            p = p.getProx();
            qtddeNos++;
        }
        return qtddeNos;
    }

    
    public Object ObterNo(int pos) {

        if((pos < 0) || (pos >= ObterComprimento())) {
            throw new IndexOutOfBoundsException("Pos = " + pos);
        }

        NoLista<T> p = primeiro;

        //o meu
        /* 
        for (int i = 0; i < pos; i++) {
           p = p.getProx(); 
        } */

        //do professor
        while ((p!= null) && (pos > 0)){
            pos--;
            p = p.getProx();
        }

        if(p == null){
            throw new IndexOutOfBoundsException();
        }

        return p.getInfo();

    }

}

