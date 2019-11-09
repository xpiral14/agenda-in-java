import java.util.ArrayList;

/**
 * Classe responsável por armazenar e manipular objetos do tipo Pessoa.
 */
public class Agenda {
    /**
     * Objeto que representa uma lista de pessoas. É nela que se armazenará todos os
     * objetos "Pessoa" na memória."
     */
    private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

    /**
     * Método para adicionar objetos do tipo pessoa na lista "pessoas"
     * 
     * @param pessoa objeto pessoa a ser adicionado.
     */
    public void setPessoas(Pessoa pessoa) {

        this.pessoas.add(pessoa);
    }

    /**
     * Exclui uma pessoa pelo seu atributo código, caso não ache o objeto, um erro é
     * lançado para ser tratado.
     * 
     * @param codigo atributo código da pessoa que será comparado para ser excluido.
     * @return retorna verdadeiro caso uma pessoa seja deletada.
     * @throws Error Exceção lançada caso não ache o objeto Pessoa para deletar
     */
    Boolean excluirPessoa(String codigo) throws Error {
        for (int i = 0; i < pessoas.size(); i++) {
            if (this.pessoas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                pessoas.remove(i);
                return true;
            }
        }
        throw new Error("Pessoa com o codigo " + codigo + " nao encontrada");
    }

    /**
     * Busca um objeto Pessoa através de seu atributo nome
     * 
     * @param nome Nome que servirá para buscar na lista de pessoas.
     * @return Retorna o objeto pessoa encontrada ou null caso não encontrada.
     */
    Pessoa buscarPessoa(String nome) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (this.pessoas.get(i).getNome().equalsIgnoreCase(nome)) {
                return pessoas.get(i);
            }
        }
        return null;
    }

    /**
     * Busca pessoa através do atributo código.
     * 
     * @param codigo atributo codigo da pessoa a ser encontrada.
     * @return retorna um objeto da classe Pessoa ou null caso a pessoa não seja
     *         encontrada.
     */
    Pessoa buscarPessoaCod(String codigo) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (this.pessoas.get(i).getCodigo().equals(codigo)) {
                return pessoas.get(i);
            }
        }
        return null;
    }

    /**
     * Altera um objeto pessoa através de seus métodos de alteração.
     * 
     * @param cod   atributo código a ser alterado.
     * @param nome  atributo nome a ser alterado.
     * @param sexo  atributo sexo a ser alterado.
     * @param email atributo email a ser alterado.
     * @return retorna true caso a alteração seja realizada com sucesso.
     * @throws Error Lança um erro caso a alteração não dê certo.
     */
    Boolean alteraPessoa(String cod, String nome, String sexo, String email) {

        for (int i = 0; i < pessoas.size(); i++) {
            if (this.pessoas.get(i).getCodigo().equals(cod)) {
                this.pessoas.get(i).setNome(nome);
                this.pessoas.get(i).setSexo(sexo);
                this.pessoas.get(i).setEmail(email);
                return true;
            }
        }
        throw new Error("Pessoa com o codigo " + cod + " nao encontrada");
    }

    /**
     * Lista todas as pessoas dentro do objeto de Lista "agenda".
     */
    public void todasPessoas() {
        for (int i = 0; i < this.pessoas.size(); i++) {
            System.out.println(this.pessoas.get(i).toString());
        }
    }
}