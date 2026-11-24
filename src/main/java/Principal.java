
import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    // Variavel global para podermos utlizar o scanner em qualquer lugar
    private final static Scanner sc = new Scanner(System.in);
    private static int N = 0;
    // Dados das Contas
    static String[] documentos = new String[100];
    static String[] descricao = new String[100];
    static String[] benediciario = new String[100];
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
            System.out.println("CADASTRO DE CONTAS");
            System.out.println("1 - INCLUSÃO");
            System.out.println("2 - ALTERAÇÃO");
            System.out.println("3 - CONSULTA");
            System.out.println("4 - EXCLUSÃO");
            System.out.println("0 - RETORNAR");
            System.out.print("OPÇÃO : ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    if (N < 100) {
                        ;
                    } else {
                        System.out.println("Cadastro cheio!");
                    }
                    break;
                case 2:
                      ;
                    break;
                case 3:
                      ;
                    break;
                case 4:
                      ;
                    break;
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
            System.out.println("INCLUSÃO DE CONTA");
            System.out.println("DOCUMENTO:");
            String documentoPesquisado = String.valueOf(sc.nextLine());

            // Validação de documento
            int posicaoPesquisada = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisada == -1) {
                documentos[N] = documentoPesquisado;
                System.out.println("DESCRIÇÃO:");
                descricao[N] = sc.nextLine();
                System.out.println("BENEFEICIÁRIO:");
                benediciario[N] = sc.nextLine();
                System.out.println("VALOR:");
                //Validação para checar se o valor é positivo
                float valor = validarValor(Float.parseFloat(sc.nextLine()));
                valorDebito[N] = valor;
                // Validação de data
                System.out.println("VENCIMENTO: (XXXX-XX-XX)");
                LocalDate data = validaData(LocalDate.parse(sc.nextLine()));
                dataVencimento[N] = data;
                System.out.print("CONFIRMA INCLUSÃO ( S/N ) ? ");
                opcao1 = sc.nextLine().charAt(0);
                if (opcao1 == 's' || opcao1 == 'S') {
                    System.out.println("CONTA CADASTRADA COM SUCESSO!");
                    mostraDados(N);
                    N++;
                } else {
                    System.out.println("Informações descartadas");
                }
            } else {
                System.out.println("DOCUMENTO JA CADASTRADO");
            }
            System.out.println("NOVA INCLUSÃO ( S/N ) ?");
            opcao2 = sc.nextLine().charAt(0);
        } while (opcao2 != 'n' && opcao2 != 'N');
    }

    static void alteracao() {
        char opcao1;
        char opcao2;
        do {
            printCabecalho();
            System.out.println("ALTERAÇÃO DE CONTA");
            System.out.println("DIGITE O NÚMERO DO DOCUMENTO QUE DESEJA ALTERAR:");
            String documentoPesquisado = sc.nextLine();
            int posicaoPesquisada = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisada != -1) {
                System.out.println("DESCRICAO");
                String novaDescricao = sc.nextLine();
                System.out.println("BENEFICIARIO");
                String novoBeneficiario = sc.nextLine();
                System.out.println("VALOR:");
                //Validação para checar se o valor é positivo
                float novoValor = validarValor(Float.parseFloat(sc.nextLine()));
                System.out.println("VENCIMENTO: (XXXX-XX-XX) ");
                LocalDate novaData = LocalDate.parse(sc.nextLine());
                System.out.print("CONFIRMA ALTERACAO ( S/N ) ? ");
                opcao1 = sc.nextLine().charAt(0);
                if (opcao1 == 's' || opcao1 == 'S') {
                    descricao[posicaoPesquisada] = novaDescricao;
                    benediciario[posicaoPesquisada] = novoBeneficiario;
                    valorDebito[posicaoPesquisada] = novoValor;
                    dataVencimento[posicaoPesquisada] = novaData;
                } else {
                    System.out.println("Informações descartadas");
                }
            } else {
                System.err.println("NENHUMA CONTA ENCONTRADA COM O DOCUMENTO" + documentoPesquisado);
            }
            System.out.println("NOVA ALTERACAO ( S/N ) ?");
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
            System.out.println("CONSULTA DE CONTA");
            System.out.println("DIGITE O NÚMERO DO DOCUMENTO QUE DESEJA PESQUISAR:");
            String documentoPesquisado = sc.nextLine();
            int posicaoPesquisa = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisa != -1) {
                System.out.println("CONTA ENCONTRADA COM SUCESSO!");
                mostraDados(posicaoPesquisa);
            } else {
                System.out.println("NENHUMA CONTA ENCONTRADA COM O DOCUMENTO " + documentoPesquisado);
            }
            System.out.println("NOVA PESQUISA ( S / N) ");
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
            System.out.println("EXCLUSÃO DE CONTA");
            System.out.println("DIGITE O NÚMERO DO DOCUMENTO QUE DESEJA EXCLUIR:");
            String documentoPesquisado = sc.nextLine();
            int posicaoPesquisa = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisa != -1) {
                System.out.println("CONTA ENCONTRADA COM SUCESSO!");
                mostraDados(posicaoPesquisa);
                System.out.print("CONFIRMA EXCLUSAO ( S/N ) ? ");
                opcao1 = sc.nextLine().charAt(0);
                if (opcao1 == 's' || opcao1 == 'S') {
                    for (int i = posicaoPesquisa; i < N - 1; i++) {
                        documentos[i] = documentos[i + 1];
                        descricao[i] = descricao[i + 1];
                        benediciario[i] = benediciario[i + 1];
                        valorDebito[i] = valorDebito[i + 1];
                        dataVencimento[i] = dataVencimento[i + 1];
                    }
                    N--;
                    System.out.println("EXCLUSAO CONCLUÍDA COM SUCESSO");
                } else {
                    System.out.println("EXCLUSAO DESCARTADA - DADOS CONTINUAM SALVOS");
                }
            } else {
                System.err.println("NENHUMA CONTA ENCONTRADA COM O DOCUMENTO" + documentoPesquisado);
            }
            System.out.println("NOVA EXCLUSAO ( S/N ) ?");
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
            System.err.println("VALOR PRECISA SER POSITIVO");
            System.out.println("VALOR:");
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
            System.err.println("DATA NAO PODE SER RETROATIVA");
            System.out.println("VENCIMENTO: (XXXX-XX-XX) ");
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
        System.out.println("DOCUMENTO    : " + documentos[endereco]);
        System.out.println("DESCRICAO    : " + descricao[endereco]);
        System.out.println("BENEFICIARIO : " + benediciario[endereco]);
        System.out.println("VALOR        : " + valorDebito[endereco]);
        System.out.println("VENCIMENTO   : " + dataVencimento[endereco]);
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
            System.out.println("MOVIMENTACAO - BAIXA DE CONTAS");
            System.out.println("DIGITE O NUMERO DO DOCUMENTO QUE QUEIRA DAR BAIXA");
            String documentoPesquisado = sc.nextLine();
            int posicaoPesquisa = pesquisaDocumento(documentoPesquisado);
            if (posicaoPesquisa != -1) {
                System.out.println("DOCUMENTO   : " + documentos[posicaoPesquisa]);
                System.out.println("VALOR ATUAL : " + valorDebito[posicaoPesquisa]);
                System.out.println("QUAL VALOR VOCE PAGOU? ");
                float valorPago = validarValor(Float.parseFloat(sc.nextLine()));
                if (valorPago > valorDebito[posicaoPesquisa]) {
                    System.err.println("VALOR NÃO PODE SER MAIOR QUE O DA CONTA");
                    System.out.println("VALOR : ");
                    valorPago = validarValor(Float.parseFloat(sc.nextLine()));
                }
                System.out.println("CONFIRMA BAIXA ( S / N )");
                opcao1 = sc.nextLine().charAt(0);
                if (opcao1 == 's' || opcao1 == 'S') {
                    float valorFinal = valorDebito[posicaoPesquisa] - valorPago;
                    valorDebito[posicaoPesquisa] = valorFinal;
                    System.out.println("VALOR FINAL :" + valorFinal);
                } else {
                    System.out.println("VALOR NAO FOI SUBTRAIDO!");
                }
            } else {
                System.out.println("NENHUMA CONTA ENCONTRADO!");
            }
            System.out.println("NOVA BAIXA ( S / N )");
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
            System.out.println("RELATÓRIOS");
            System.out.println("1 - CONTAS A PAGAR");
            System.out.println("2 - CONTAS A ATRASADAS");
            System.out.println("3 - BALANÇO DE DESPESAS");
            System.out.println("0 - RETORNAR");
            System.out.print("OPÇÃO : ");
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
        System.out.println("CONTAS A PAGAR POR PERÍODO");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("VENCIMENTO\tVALOR\t\tDOCUMENTO\tDESCRIÇÃO\tBENEFICIÁRIO");
        System.out.println("---------------------------------------------------------------------------------");

        if (N == 0) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for (int i = 0; i < N; i++) {
                System.out.println(dataVencimento[i] + "\t" + valorDebito[i] + "\t\t" + documentos[i] + "\t\t" + descricao[i] + "\t\t" + benediciario[i]);
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
        System.out.println("CONTAS A ATRASADAS");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("VENCIMENTO \t VALOR \t\t DOCUMENTO \t DESCRIÇÃO \t BENEFICIÁRIO");
        System.out.println("-----------------------------------------------------------------------------------------------");

        LocalDate hoje = LocalDate.now();
        int encontrouConta = 0;

        for (int i = 0; i < N; i++) {
            if (dataVencimento[i] != null && dataVencimento[i].isBefore(hoje)) {
                System.out.println(dataVencimento[i] + "\t" + valorDebito[i] + "\t\t" + documentos[i] + "\t\t" + descricao[i] + "\t\t" + benediciario[i]);
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
        System.out.println("BALANÇO DE DESPESAS NO PERÍODO");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("MÊS\t\tTOTAL DE DESPESAS\tMAIOR BENEFICIÁRIO");
        System.out.println("-----------------------------------------------------------------------------------------------");

        String[] meses = {"JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO",
            "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"};

        for (int m = 1; m <= 12; m++) {
            double totalMes = 0;
            String maiorBeneficiarioMes = "-";
            double maiorValor = 0;

            for (int i = 0; i < N; i++) {
                if (dataVencimento[i] != null && dataVencimento[i].getMonthValue() == m) {
                    totalMes += valorDebito[i];
                    if (valorDebito[i] > maiorValor) {
                        maiorValor = valorDebito[i];
                        maiorBeneficiarioMes = benediciario[i];
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
        System.out.println("FINALIZANDO APLICAÇÃO...");
    }

    /**
     * Exibe o cabeçalho do sistema, contendo o nome da empresa e a
     * identificação do sistema de contas a pagar.
     */
    static void printCabecalho() {
        System.out.println("\nXYZ COMERCIO DE PRODUTOS LTDA.");
        System.out.println("SISTEMA DE CONTAS A PAGAR\n");
    }

    public static void main(String[] args) {
        int opcao;
        System.out.println("Andriele");
        System.out.println("Enzo");
        System.out.println("Guilherme");
        // Menu inicial
        do {
            printCabecalho();
            System.out.println("MENU PRINCIPAL\n");
            System.out.println("1 - CADASTRO DE CONTAS");
            System.out.println("2 - BAIXA DE CONTAS");
            System.out.println("3 - RELATÓRIOS");
            System.out.println("0 - FINALIZAR");
            System.out.print("OPÇÃO : ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    printBaixaDeContas();
                    break;
                case 3:
                    printRelatoriosDeContas();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Opção Inválida\n");
            }
        } while (opcao != 0);
    }
}
