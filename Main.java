import java.io.IOException;
import java.util.Scanner;
import classes.Agenda;
/**
 * Main
 */

public class Main {
    
    // cria um objeto da classe Agenda.
    static Agenda agenda = new Agenda();

    // método principal do java para haver a execução dos códigos.
    public static void main(String[] args) {
        //Carrega as pessoas na agenda presentes no arquivo txt que funciona como banco de dados.
        Database.carregaDados("./database/pessoas.txt", agenda);
        // opção de escolha do menu.
        int option;
        // Cria um novo objeto da classe Scanner para haver entrada de dados.
        Scanner input = new Scanner(System.in);

        // começa um laço do tipo do ... while para que o menu possa funcionar de forma
        // que o programa só pare se o valor de option for igual a 7
        do {
            // mostra ao usuário um menu de escolha do que o programa pode fazer.
            System.out.print("---------------------------\nEscolha uma das opções:\n---------------------------\n"
                    + "[1] - Cadastrar pessoa\n" + "[2] - Alterar Pessoa\n" + "[3] - Listar pessoas cadastradas\n"
                    + "[4] - Cadastrar pessoas por arquivo .txt\n" + "[5] - listar pessoa pelo nome\n"
                    + "[6] - Excluir pessoa\n" + "[7] - finalizar programa\n");

            // depois de decidir o que fazer, o usuario escolhe uma opção, essa opção fica
            // salva na variável "option"
            option = input.nextInt();
            // o programa começa a ver qual foi o valor escolhido
            switch (option) {
            // se a opção 1 for escolhida esse código é executado.
            case 1: {
                String codigo;
                String nome;
                String sexo;
                String email;
                String linhaParaSalvar;
                System.out.println("---- CADASTRAR UMA PESSOA ----");

                // salva um código para salvar no objeto pessoa
                System.out.print("Digite o codigo:\n");
                codigo = input.next();

                input.nextLine();

                // salva um nome para salvar no objeto pessoa
                System.out.print("Digite o nome:\n");
                nome = input.nextLine();

                // salva um sexo para salvar no objeto pessoa
                System.out.print("Digite o sexo:\n");
                sexo = input.next();
                input.nextLine();

                // salva um email para salvar no objeto pessoa
                System.out.print("Digite o email:\n");
                email = input.next();

                // através do construtor (criado na linha 19 do arquivo Pessoa.java) cria-se um
                // novo objeto do tipo pessoa.
                Pessoa novaPessoa = new Pessoa(codigo, nome, sexo, email);

                //começa um bloco de "tentar, se nao conseguir, pegue o erro"
                try {
                    //essa variavel salva os dados na forma em que se pediu para salvar num arquivo .txt
                    linhaParaSalvar = codigo + ";" + nome + ";" + sexo.toUpperCase() + ";" + email;

                    //Classe criada para representar ações de um banco de dados, que no caso é um arquivo .txt
                    Database.salvaDados("./database/pessoas.txt", linhaParaSalvar);

                    //Salva o objeto "novaPessoa", do tipo Pessoa dentro da agenda
                    agenda.setPessoas(novaPessoa);
                    System.out.println("\nPessoa adicionada com sucesso!");
                } catch (IOException e) { // catch é a parte em que se pega os erros
                    System.out.println(
                            "\n**************************\n" + e.getMessage() + "\n**************************\n");
                } catch (Error e) { // trata outro erro que pode dar
                    System.out.println(
                            "\n**************************\n" + e.getMessage() + "\n**************************");
                }

            }
                break;
                //código executado caso a opção 2 seja executada.
            case 2: {
                
                String codigoPessoa;
                String nome;
                String sexo;
                String email;
                System.out.println("---- ALTERAR PESSOA ----");

                //entrada do código da pessoa
                System.out.println("Digite o codigo da pessoa: ");
                codigoPessoa = input.next();

                try {
                    // cria uma pessoa se for achado uma pessoa com o codigo especificado,
                    // se n for encontrado, o valor de "p" fica "null"
                    Pessoa p = agenda.buscarPessoaCod(codigoPessoa);
                    //Se for achado uma pessoa, então ela será alterada.
                    if (p != null) {
                        // entrada do nome
                        System.out.println("Digite o novo nome: ");
                        input.nextLine();
                        nome = input.nextLine();

                        //entrada do sexo
                        System.out.println("Digite o novo sexo: ");
                        sexo = input.nextLine();

                        //entrada do email
                        System.out.println("Digite o novo email: ");
                        email = input.nextLine();

                        //método responsavel por alterar uma pessoa;
                        agenda.alteraPessoa(codigoPessoa, nome, sexo, email);
                    } else {
                        //se não for achado, então um erro é lançado
                        throw new Error("pessoa nao existe!");
                    }

                } catch (Error e) {
                    // tratar o erro para nao quebrar o programa.
                    System.out.println(e.getMessage());
                }
            }
                break;
            case 3: {
                //opção executada caso a opção 3 seja escolhida.
                System.out.println("---- LISTAR PESSOAS CADASTRADAS ----");
                agenda.todasPessoas();
            }
                break;
            case 4: {
                //opção executada caso a opção 4 seja escolhida.
                String nomeArquivo;
                System.out.println("---- CADASTRAR PESSOAS POR AQUIVO .TXT ----");
                System.out.println("Nome do arquivo de texto: ");
                input.nextLine();
                nomeArquivo = input.next();
                try {
                    //Através da classe Database (criada para representar um banco de dados), salva-se os dados através de um arquivo .txt
                    Database.cadastraPorTXT(nomeArquivo, agenda);
                } catch (Error e) {
                    System.out.println(
                            "\n**************************\n" + e.getMessage() + "\n**************************");
                }

            }
                break;
            case 5: {
                //opção executada caso a opção 5 seja escolhida.
                System.out.println("---- LISTAR PESSOAS PELO NOME ----");
                String nome;
                System.out.println("Digite um nome:");
                input.nextLine();
                nome = input.nextLine();
                try {
                    Pessoa pessoaEncontrada = agenda.buscarPessoa(nome);
                    System.out.println(pessoaEncontrada);
                } catch (NullPointerException e) {
                    System.out.println("Pessoa nao encontrada");
                }
            }
                break;
            case 6: {
                //opção executada caso a opção 6 seja escolhida.
                System.out.println("---- EXCLUIR PESSOA ----");

                //pede para o usuario digitar um codigo que será usado para excluir uma pessoa
                System.out.println("digite o codigo da pessoa: ");
                String codigo = input.next();

                //o bloco try catch abaixo tentará excluir alguem, caso não consiga, lançará um erro
                // que será tratado pelo bloco "catch"
                try {
                    //metodo da agenda que exclui uma pessoa pelo seu código
                    agenda.excluirPessoa(codigo);

                    System.out.println("pessoa excluida com sucesso !");
                } catch (NullPointerException e) { //pega um erro caso dê um erro de processo.
                    System.out.println("Pessoa nao encontrada.");
                } catch (Error e) {
                    //pega um erro e mostra a mensagem dele.
                    System.out.println(e.getMessage());
                }
            }
                break;
            case 7: {
                //opção executada caso a opção 7 seja escolhida.
                System.out.println("Finalziando programa...");
            }
                break;
            default: {
                System.out.println("Opção inválida");
            }
                break;
            }
        } while (option != 7); //caso a opção seja = 7, então o laço é terminado

        //fecha a objeto de entrada de dados
        input.close();
    }

}