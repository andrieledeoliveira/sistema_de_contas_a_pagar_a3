
public class Principal {

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
