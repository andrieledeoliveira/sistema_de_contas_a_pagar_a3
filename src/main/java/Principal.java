
import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    // Variavel global para podermos utlizar o scanner em qualquer lugar
    private final static Scanner sc = new Scanner(System.in);
    private static int N = 0;
    // Dados das Contas
    static String[] documentos = new String[100];
    static String[] descricao = new String[100];
    static String[] beneficiario = new String[100];
    static float[] valorDebito = new float[100];
    static LocalDate[] dataVencimento = new LocalDate[100];

    /**
     * Exibe o menu de cadastro de contas, permitindo ao usuário realizar
     * operações de inclusão, alteração, consulta e exclusão de contas. Verifica
     * a disponibilidade de espaço nos vetores antes de permitir uma nova
     * inclusão e possibilita retornar ao menu anterior.
     */
    static void printCadastroDeContas() {
        int opcao;
        do {
            printCabecalho();
            System.out.println("Cadastro de contas");
            System.out.println("1 - Inclusao");
            System.out.println("2 - Alteracao");
            System.out.println("3 - Consulta");
            System.out.println("4 - Exclusao");
            System.out.println("0 - Retornar");
            System.out.print("opcao : ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    if (N < 100) {
                       inclusao ();
                    } else {
                        System.out.println("Cadastro cheio!");
                    }
                    break;
                case 2:
                      alteracao();
                    break;
                case 3:
                      consulta() ;
                    break;
                            
                case 4:
                     exclusao() ;
                    break;
                    
                default:
                    System.out.println("Opcao Invalida");
            }
        } while (opcao != 0);
    }

    /**
     * Permite cadastrar novas contas no sistema, solicitando os dados de
     * documento, descrição, beneficiário, valor e data de vencimento. Verifica
     * se o documento já está cadastrado, realiza a validação do valor e da data
     * informados e solicita a confirmação da inclusão. Caso confirmada,
     * armazena os dados nos respectivos vetores e atualiza a quantidade de
     * contas cadastradas. Permite realizar novas inclusões enquanto o usuário
     * desejar.
     */
    static void inclusao() {
        char opcao1;
        char opcao2;
        do {
            printCabecalho();
            System.out.println("Inclusao de conta");
            System.out.println("Documento:");
            String documentoPesquisado = String.valueOf(sc.nextLine());

            // Validação de documento
            int posicaoPesquisada = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisada == -1) {
                documentos[N] = documentoPesquisado;
                System.out.println("Descricao:");
                descricao[N] = sc.nextLine();
                System.out.println("Beneficiario:");
                beneficiario[N] = sc.nextLine();
                System.out.println("Valor:");
                //Validação para checar se o valor é positivo
                float valor = validarValor(Float.parseFloat(sc.nextLine()));
                valorDebito[N] = valor;
                // Validação de data
                System.out.println("Vencimento: (XXXX-XX-XX)");
                LocalDate data = validaData(LocalDate.parse(sc.nextLine()));
                dataVencimento[N] = data;
                System.out.print("Confirma a inclusao ( S/N ) ? ");
                opcao1 = sc.nextLine().charAt(0);
                if (opcao1 == 's' || opcao1 == 'S') {
                    System.out.println("Conta cadastrada com sucesso!");
                    mostraDados(N);
                    N++;
                } else {
                    System.out.println("Informacoes descartadas");
                }
            } else {
                System.out.println("Documeento ja cadastrado");
            }
            System.out.println("Nova inclusao ( S/N ) ?");
            opcao2 = sc.nextLine().charAt(0);
        } while (opcao2 != 'n' && opcao2 != 'N');
    }

    static void alteracao() {
        char opcao1;
        char opcao2;
        do {
            printCabecalho();
            System.out.println("Alteracao de conta");
            System.out.println("Digite o numero do documento que deseja alterar:");
            String documentoPesquisado = sc.nextLine();
            int posicaoPesquisada = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisada != -1) {
                System.out.println("Descricao");
                String novaDescricao = sc.nextLine();
                System.out.println("Beneficiario");
                String novoBeneficiario = sc.nextLine();
                System.out.println("Valor:");
                //Validação para checar se o valor é positivo
                float novoValor = validarValor(Float.parseFloat(sc.nextLine()));
                System.out.println("Vencimento: (XXXX-XX-XX) ");
                LocalDate novaData = LocalDate.parse(sc.nextLine());
                System.out.print("Confirma alteracao ( S/N ) ? ");
                opcao1 = sc.nextLine().charAt(0);
                if (opcao1 == 's' || opcao1 == 'S') {
                    descricao[posicaoPesquisada] = novaDescricao;
                    beneficiario[posicaoPesquisada] = novoBeneficiario;
                    valorDebito[posicaoPesquisada] = novoValor;
                    dataVencimento[posicaoPesquisada] = novaData;
                } else {
                    System.out.println("Informacoes descartadas");
                }
            } else {
                System.err.println("Nenhuma conta encontrada com o documento" + documentoPesquisado);
            }
            System.out.println("Nova alteracao ( S/N ) ?");
            opcao2 = sc.nextLine().charAt(0);
        } while (opcao2 != 'n' && opcao2 != 'N');
    }

    /**
     * Realiza a consulta de uma conta cadastrada pelo número do documento.
     * Solicita ao usuário o documento que deseja pesquisar e utiliza o método
     * pesquisaDocumento() para localizar a conta. Caso encontre, exibe todas as
     * informações da conta. Caso contrário, informa que nenhuma conta foi
     * encontrada.
     */
    static void consulta() {
        char opcao1;
        do {
            printCabecalho();
            System.out.println("Consulta de conta");
            System.out.println("Digite o numero do documento que deseja pesquisar:");
            String documentoPesquisado = sc.nextLine();
            int posicaoPesquisa = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisa != -1) {
                System.out.println("Conta encontrada com sucesso!");
                mostraDados(posicaoPesquisa);
            } else {
                System.out.println("Nenhuma conta encontrada com o documento " + documentoPesquisado);
            }
            System.out.println("Nova pesquisa ( S / N) ");
            opcao1 = sc.nextLine().charAt(0);
        } while (opcao1 != 'n' && opcao1 != 'N');
    }
    /**
     * Permite excluir uma conta cadastrada no sistema por meio da pesquisa pelo
     * número do documento. Exibe os dados da conta encontrada, solicita a
     * confirmação da exclusão e, caso confirmada, remove seus dados dos
     * vetores, reorganizando as posições e atualizando a quantidade de contas
     * cadastradas. Permite realizar novas exclusões enquanto o usuário desejar.
     */
    static void exclusao() {
        char opcao1;
        char opcao2;
        do {
            printCabecalho();
            System.out.println("Exclusao de conta");
            System.out.println("Digite o numero do documento:");
            String documentoPesquisado = sc.nextLine();
            int posicaoPesquisa = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisa != -1) {
                System.out.println("Conta encontrada com sucesso!");
                mostraDados(posicaoPesquisa);
                System.out.print("Confirma exclusao ( S/N ) ? ");
                opcao1 = sc.nextLine().charAt(0);
                if (opcao1 == 's' || opcao1 == 'S') {
                    for (int i = posicaoPesquisa; i < N - 1; i++) {
                        documentos[i] = documentos[i + 1];
                        descricao[i] = descricao[i + 1];
                        beneficiario[i] = beneficiario[i + 1];
                        valorDebito[i] = valorDebito[i + 1];
                        dataVencimento[i] = dataVencimento[i + 1];
                    }
                    N--;
                    System.out.println("Exclusao concluida com sucesso");
                } else {
                    System.out.println("Exclusao descartada - dados continuam salvos");
                }
            } else {
                System.err.println("Nenhuma conta encontrada com o documento" + documentoPesquisado);
            }
            System.out.println("Nova exclusao ( S/N ) ?");
            opcao2 = sc.nextLine().charAt(0);
        } while (opcao2 != 'n' && opcao2 != 'N');
    }

    /**
     * Pesquisa um documento no vetor de documentos cadastrados. Percorre os
     * registros existentes e compara cada documento com o valor informado,
     * retornando a posição correspondente caso encontre uma correspondência.
     *
     * @param documentoPesquisado Número do documento que será pesquisado.
     * @return Índice da posição em que o documento foi encontrado no vetor.
     * Retorna -1 caso nenhum documento correspondente seja encontrado.
     */
    static int pesquisaDocumento(String documentoPesquisado) {
        // Recebe esse valor por conta de que se o documento não existir sabe-se pelo valor iniciado
        int posicao = -1;
        for (int i = 0; i < N; i++) {
            if (documentos[i].equals(documentoPesquisado)) {
                posicao = i;
            }
        }
        return posicao;
    }

    /**
     * Valida o valor informado, verificando se é positivo. Caso seja negativo,
     * solicita um novo valor ao usuário.
     *
     * @param valor valor informado para validação.
     * @return valor validado.
     */
    static float validarValor(Float valor) {
        if (valor < 0) {
            System.err.println("Valor precisa ser positivo");
            System.out.println("Valor:");
            valor = Float.parseFloat(sc.nextLine());
        }
        return valor;
    }

    /**
     * Valida a data de vencimento informada, verificando se não é anterior à
     * data atual. Caso seja retroativa, solicita uma nova data ao usuário.
     *
     * @param data data de vencimento a ser validada.
     * @return data de vencimento válida.
     */
    static LocalDate validaData(LocalDate data) {
        LocalDate hoje = LocalDate.now();
        if (data.isBefore(hoje)) {
            System.err.println("Data nao pode ser retroativa");
            System.out.println("Vencimento: (XXXX-XX-XX) ");
            return LocalDate.parse(sc.nextLine());
        }
        return data;
    }

    /**
     * Exibe os dados de uma conta cadastrada no sistema, incluindo documento,
     * descrição, beneficiário, valor e data de vencimento.
     *
     * @param endereco posição da conta nos vetores de armazenamento.
     */
    static void mostraDados(int endereco) {
        System.out.println("Documento    : " + documentos[endereco]);
        System.out.println("Descricao    : " + descricao[endereco]);
        System.out.println("Beneficiario : " + beneficiario[endereco]);
        System.out.println("Valor        : " + valorDebito[endereco]);
        System.out.println("Vencimento   : " + dataVencimento[endereco]);
    }

    /**
     * Exibe o menu de baixa de contas, permitindo ao usuário pesquisar uma
     * conta pelo número do documento, informar o valor pago e atualizar o saldo
     * devedor. Solicita a confirmação da operação e permite realizar novas
     * baixas enquanto o usuário desejar.
     */
    static void printBaixaDeContas() {
        char opcao1;
        char opcao2;
        do {
            printCabecalho();
            System.out.println("Movimentacao - Baixa de Contas");
            System.out.println("Digite o Numero do documento que queira dar baixa");
            String documentoPesquisado = sc.nextLine();
            int posicaoPesquisa = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisa != -1) {
                System.out.println("Documento   : " + documentos[posicaoPesquisa]);
                System.out.println("Valor atual : " + valorDebito[posicaoPesquisa]);
                System.out.println("Qual valor voce pagou? ");
                float valorPago = validarValor(Float.parseFloat(sc.nextLine()));
                if (valorPago > valorDebito[posicaoPesquisa]) {
                    System.err.println("Valor nao pode ser maior que o da conta");
                    System.out.println("Valor : ");
                    valorPago = validarValor(Float.parseFloat(sc.nextLine()));
                }
                System.out.println("Confirma baixa ( S / N )");
                opcao1 = sc.nextLine().charAt(0);
                if (opcao1 == 's' || opcao1 == 'S') {
                    float valorFinal = valorDebito[posicaoPesquisa] - valorPago;
                    valorDebito[posicaoPesquisa] = valorFinal;
                    System.out.println("Valor final :" + valorFinal);
                } else {
                    System.out.println("Valor nao foi subtraido!");
                }
            } else {
                System.out.println("Nenhuma conta encontrada!");
            }
            System.out.println("nova baixa ( S / N )");
            opcao2 = sc.nextLine().charAt(0);
        } while (opcao2 != 'n' && opcao2 != 'N');
    }
    
    /**
     * Exibe o menu de relatórios de contas, permitindo ao usuário consultar as
     * contas a pagar, visualizar as contas atrasadas e gerar o balanço de
     * despesas. Permite retornar ao menu anterior por meio da opção de saída.
     */
     static void printRelatoriosDeContas() {
        int opcao;
        do {
            printCabecalho();
            System.out.println("Relatorios");
            System.out.println("1 - Contas a pagar");
            System.out.println("2 - Contas atrasadas");
            System.out.println("3 - Balanco de despesas");
            System.out.println("0 - Retornar");
            System.out.print("Opcao : ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    contasPagar();
                    break;
                case 2:
                    contasAtrasadas();
                    break;
                case 3:
                    balancoDeDespesas();
                    break;
            }
        } while (opcao != 0); 
     }
    
       /**
     * Exibe a relação de todas as contas cadastradas no sistema, apresentando
     * suas respectivas datas de vencimento, valores, documentos, descrições e
     * beneficiários. Caso não existam contas cadastradas, informa ao usuário.
     */
      static void contasPagar() {
       printCabecalho();
        System.out.println("Contas a pagar por periodo");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Vencimento\tValor\t\tDocumento\tDescricao\tBeneficiario");
        System.out.println("---------------------------------------------------------------------------------");

        if (N == 0) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for (int i = 0; i < N; i++) {
                System.out.println(dataVencimento[i] + "\t" + valorDebito[i] + "\t\t" + documentos[i] + "\t\t" + descricao[i] + "\t\t" + beneficiario[i]);
            }
        }
        System.out.println("-----------------------------------------------------------------------------------");    
      }
      
     /**
     * Exibe a relação de contas atrasadas, verificando quais possuem data de
     * vencimento anterior à data atual. Apresenta os dados das contas
     * encontradas ou informa caso não existam contas atrasadas.
     */
    static void contasAtrasadas() {
      printCabecalho();
        System.out.println("Contas atrasadas");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Vencimento \t Valor \t\t Documento \t Descricao \t Beneficiario");
        System.out.println("-----------------------------------------------------------------------------------------------");

        LocalDate hoje = LocalDate.now();
        int encontrouConta = 0;

        for (int i = 0; i < N; i++) {
            if (dataVencimento[i] != null && dataVencimento[i].isBefore(hoje)) {
                System.out.println(dataVencimento[i] + "\t" + valorDebito[i] + "\t\t" + documentos[i] + "\t\t" + descricao[i] + "\t\t" + beneficiario[i]);
                encontrouConta++;
            }
        }

        if (encontrouConta == 0) {
            System.out.println("Nenhuma conta atrasada encontrada.");
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
    }
    
      /**
     * Exibe o balanço mensal de despesas do período, calculando o total de
     * débitos de cada mês e identificando o beneficiário com o maior valor de
     * despesa correspondente.
     */
    static void balancoDeDespesas() {
     printCabecalho();
        System.out.println("Balanco de despesas no periodo");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Mes\t\tTotal de despesas\tMaior beneficiario");
        System.out.println("-----------------------------------------------------------------------------------------------");

        String[] meses = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        for (int m = 1; m <= 12; m++) {
            double totalMes = 0;
            String maiorBeneficiarioMes = "-";
            double maiorValor = 0;

            for (int i = 0; i < N; i++) {
                if (dataVencimento[i] != null && dataVencimento[i].getMonthValue() == m) {
                    totalMes += valorDebito[i];
                    if (valorDebito[i] > maiorValor) {
                        maiorValor = valorDebito[i];
                        maiorBeneficiarioMes = beneficiario[i];
                    }
                }
            }

            System.out.println(meses[m - 1] + "\t\t" + totalMes + "\t\t\t" + maiorBeneficiarioMes);
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
    }   
    
     
    /**
     * Exibe uma mensagem informando que a aplicação está sendo finalizada.
     */
    static void printFinalizacao() {
        System.out.println("Finalizando aplicacao...");
    }

    /**
     * Exibe o cabeçalho do sistema, contendo o nome da empresa e a
     * identificação do sistema de contas a pagar.
     */
    static void printCabecalho() {
        System.out.println("\nXYZ Comercio de Produtos LTDA.");
        System.out.println("Sistema de contas a pagar\n");
    }

    public static void main(String[] args) {
        int opcao;
        System.out.println("Andriele");
        System.out.println("Enzo");
        System.out.println("Guilherme");
        // Menu inicial
        do {
            printCabecalho();
            System.out.println("Menu principal\n");
            System.out.println("1 - Cadastro de contas");
            System.out.println("2 - Baixa de contas");
            System.out.println("3 - Relatorios");
            System.out.println("0 - Finalizar");
            System.out.print("opcao : ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    printCadastroDeContas();
                    break;
                case 2:
                    printBaixaDeContas();
                    break;
                case 3:
                    printRelatoriosDeContas();
                    break;
                case 0:
                    printFinalizacao();
                    break;
                default:
                    System.err.println("Opcao Invalida\n");
            }
        } while (opcao != 0);
    }
}
