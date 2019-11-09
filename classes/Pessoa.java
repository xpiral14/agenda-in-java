/**
 * Classe que possui atributos de uma pessoa.
 */
public class Pessoa {
    private String codigo;
    private String nome;
    private String sexo;
    private String email;

    /**
     * Construtor responsável por construir um objeto Pessoa de forma mais rápida,
     * sem precisar usar os métodos "set"
     * 
     * @param codigo
     * @param nome
     * @param sexo
     * @param email
     */
    public Pessoa(String codigo, String nome, String sexo, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
    }

    /**
     * @return o código da pessoa
     */
    String getCodigo() {
        return codigo;
    }

    /**
     * seta um novo código.
     * 
     * @param codigo o novo código para o objeto.
     */
    void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return o email
     */
    String getEmail() {
        return this.email;
    }

    /**
     * @param email o novo email para o objeto
     */
    void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return o nome
     */
    String getNome() {
        return nome;
    }

    /**
     * @param nome o novo nome do objeto
     */
    void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return o sexo
     */
    String getSexo() {
        return sexo;
    }

    /**
     * @param sexo o novo sexo do objeto
     */
    void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return uma informação do objeto no formato de uma string.
     */
    public String toString() {
        return "codigo: " + this.codigo + " | nome: " + this.nome + " | sexo: " + this.sexo + " | email: " + this.email;
    }

    /**
     * 
     * @return uma string com os atributos do objeto para ser salvo no "banco de
     *         dados" que no caso é um arquivo .txt
     */
    public String paraFormatoBD() {
        return this.codigo + ";" + this.nome + ";" + this.sexo + ";" + this.email;
    }

}