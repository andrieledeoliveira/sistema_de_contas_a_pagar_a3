
import java.time.LocalDate;


public class Principal {
    
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
                    break;
                case 3:
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Opção Inválida\n");
            }
        } while (opcao != 0);
    }
}
