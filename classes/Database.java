import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Database
 */
public class Database {

    /**
     * 
     * @param caminho recebe o caminho do arquivo que será lido.
     * @param line    recebe a linha que será salvo no arquivo txt.
     * @throws IOException
     * @throws Error
     */
    public static void salvaDados(String caminho, String line) throws IOException, Error {
        // Classe responsavel para ler arquivos de texto.
        FileReader arq = new FileReader(caminho);

        // classe responsavel por criar um buff para manipular o arquivo .txt
        BufferedReader lerArq = new BufferedReader(arq);

        // linha recebe uma linha do arquivo .txt
        String linha = lerArq.readLine();

        String codPessoaArquivo;
        String codPessoaSalvar;
        // recebe o parametro de linha e divide a string nele em ";". Isso significa que
        // um array
        // de string será criado. Ele receberá 4 itens nesta disposição:
        // codigo;nome;sexo;email//
        // um array ficaria nessa forma: [codigo, nome, sexo, email]
        String[] dadosPessoa = line.split(";");

        // Classe que vamos usar para adicionar linhas no arquivo txt onde estão os
        // dados.
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(caminho, true));

        // a posição 2 é responsável pelo sexo, então, antes de salvar, verificamos se o
        // padrão
        // do sexo achado se encaixa em "M" ou "F". Caso não seja, um erro é lançado.
        if (!dadosPessoa[2].equals("M") && !dadosPessoa[2].equals("F")) {
            // fecha os arquivos
            lerArq.close();
            buffWrite.close();
            // o erro a ser lançado caso os padrões não se encaixem
            throw new Error("Valor \"" + dadosPessoa[2] + "\" nao bate com os valores 'M' ou 'F'");
        }
        // verifica se a linha é igual a nulo, se for, isso significa que não existe
        // nada no arquivo .txt,
        // logo, seta o valor de "line" na primeira linha do arquivo.
        if (linha == null) {
            // método que adiciona a linha.
            buffWrite.append(line + "\n");
        } else {
            // se linha não é nula, significa que já existe pessoas adicionadas no arquivo,
            // então precisamos
            // ver se od codigo dela não é igual ao código da que estamos adicionando agora.
            while (linha != null) {
                // mesma explicação das linhas 31 a 33.
                dadosPessoa = line.split(";");
                // codigo que está contido no arquivo de texto.
                codPessoaArquivo = linha.split(";")[0];
                // codigo da linha que estamos tentando adicionar agora
                codPessoaSalvar = dadosPessoa[0];

                // verificamos se os codigos da linha que queremos adicionar e ada linha do
                // arquivo .txt
                // são iguais, caso sejam, um erro é lançado.
                if (codPessoaArquivo.equals(codPessoaSalvar)) {
                    lerArq.close();
                    buffWrite.close();
                    throw new Error("Pessoa com o id \"" + codPessoaSalvar + "\" ja existe no 'banco de dados'");
                }
                linha = lerArq.readLine();
            }
            // se nenhum erro é lançado, significa que deu tudo certo, logo adicionamos a
            // nova linha no
            // arquivo .txt
            buffWrite.append(line + "\n");
        }
        // fechamento dos arquivos
        lerArq.close();
        buffWrite.close();
    }

    public static void cadastraPorTXT(String caminho, Agenda agenda) throws Error {
        // criamos um array que receberá os dados de cada linha presente no arquivo .txt
        // que adicionaremos.
        String[] dados = new String[4];
        // contador que mostrará quantas pessoas foram adicionadas.
        int totalAdicionado = 0;

        try {
            // objeto leitura de arquivos de texto que recebe o caminho especificado no
            // parametro.
            FileReader arq = new FileReader(caminho);
            // Objeto que cria uma "stream", permitindo uma leitura otimizada do arquivo
            // .txt
            BufferedReader lerArq = new BufferedReader(arq);

            // O objeto lerá o arquivo txt em que salvaremos os dados do arquivo txt que
            // passamos no parametro.
            FileWriter arqPessoas = new FileWriter("./database/pessoas.txt", true);
            BufferedWriter registraPessoa = new BufferedWriter(arqPessoas);

            // Lê a primeira linha do arquivo que iremos adicionar
            String linha = lerArq.readLine();
            // se a primeira linha for diferente de nulo, então significa que há dados para
            // adicionarmos.
            while (linha != null) {
                // dados recebe a um array de string dividido pelo caractere ";", então recebe
                // os dados nessa forma: [cod, nome, sexo, email]
                dados = linha.split(";");

                // verifica se já não temos uma pessoa com o codigo passado na linha do arquivo
                // .txtdentro da nossa lista.
                if (agenda.buscarPessoaCod(dados[0]) != null) {
                    registraPessoa.close();
                    arq.close();
                    lerArq.close();
                    throw new Error("Pessoa com o id \"" + dados[0] + "\" ja existe!");
                } else {
                    // verificação para ver se o sexo se encaixa nos padrões do programa
                    Boolean testaSexo = !dados[2].equals("M") && !dados[2].equals("F");

                    // caso não se encaixe, então um erro é lançado.
                    if (testaSexo) {
                        registraPessoa.close();
                        arq.close();
                        lerArq.close();
                        throw new Error("Valor \"" + dados[2] + "\" nao bate com os valores 'M' ou 'F'");
                    }
                }
                // se nenhum erro foi encontrado, então o arquivo .txt do nosso programa
                // adiciona a nova linha passada pelo outro arquivo .txt
                registraPessoa.append(linha + "\n");

                // cria-se então um objeto Pessoa novo na memória do computador.
                Pessoa novaPessoa = new Pessoa(dados[0], dados[1], dados[2], dados[3]);

                // adiciona essa nova pessoa na nossa agenda.
                agenda.setPessoas(novaPessoa);
                // soma +1 para uma nova pessoa adicionada
                totalAdicionado++;

                // vai para a leitura da próxima linha até não existir mais linhas ou houver um
                // erro.
                linha = lerArq.readLine();
            }
            // fecha os arquivos.
            registraPessoa.close();
            arq.close();
            lerArq.close();
            // mostra o total de pessoas adicionadas.
            System.out.printf("Total de pessoas adicionadas: %d%n", totalAdicionado);

        } catch (IOException e) { // erro lançado caso o arquivo não sexa encontrado
            System.out.printf("Erro na abertura do arquivo: %n", e.getMessage());
        } catch (NullPointerException e) {// erro lançado caso o padrão da linha não se encaixe no padrão passado.
            System.out.println(
                    "Há um erro no seu arquivo. Por favor, corrija de forma que fique neste padrao: codigo;nome;sexo;email");
        } catch (ArrayIndexOutOfBoundsException e) {// erro lançado caso o padrão da linha não se encaixe no padrão
                                                    // passado.
            System.out.println(
                    "Há um erro no seu arquivo. Por favor, corrija de forma que fique neste padrao: codigo;nome;sexo;email");
        }
    }

    public static void buscaPessoaPorCod(String cod, String caminho) {
        try{
            FileReader arquivoBancoDeDados = new FileReader(caminho);
        BufferedReader bancoDeDados = new BufferedReader(arquivoBancoDeDados);

        String linha = bancoDeDados.readLine();
        String pessoaCodBD;
        while (linha != null) {
            pessoaCodBD = linha.split(";")[0];
            if (pessoaCodBD.equals(cod)) {
                arquivoBancoDeDados.close();
                bancoDeDados.close();
                throw new Error("pessoa com o código: \"" + cod + "\" ja existe");
            }
        }
        arquivoBancoDeDados.close();
        bancoDeDados.close();
        }catch(IOException e){
            String arquivo = caminho.split("/")[caminho.split("/").length];

            System.out.println("arquivo " + arquivo + " nao encontrado");
        }
    }

    // método que serve para carregar os dados do 'banco de dados' caso existam para
    // que fique mais facil adicionar os que estao dentro de /database/pessoas.txt
    public static void carregaDados(String caminho, Agenda agenda) {
        try {
            FileReader arquivoBancoDeDados = new FileReader(caminho);
            BufferedReader bancoDeDados = new BufferedReader(arquivoBancoDeDados);
            String linha = bancoDeDados.readLine();
            String[] dados;
            int totalPessoas = 0;
            Pessoa pessoaBancoDeDados;
            while (linha != null) {
                dados = linha.split(";");
                pessoaBancoDeDados = new Pessoa(dados[0], dados[1], dados[2], dados[3]);
                agenda.setPessoas(pessoaBancoDeDados);
                linha = bancoDeDados.readLine();
                totalPessoas++;
            }
            arquivoBancoDeDados.close();
            bancoDeDados.close();

            if (totalPessoas > 0) {
                System.out.println("Pessoas adicionadas pelo banco de dados: " + totalPessoas);
            }
        } catch (IOException e) {
            System.out.println("arquivo nao encontrado");
        }
    }
}