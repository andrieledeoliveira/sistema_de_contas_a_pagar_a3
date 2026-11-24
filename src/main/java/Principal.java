
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
