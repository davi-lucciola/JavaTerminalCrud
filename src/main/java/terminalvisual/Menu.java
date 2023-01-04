package terminalvisual;

import domain.Crud;
import domain.models.Input;
import domain.models.Profissional;

public class Menu {
    public static void topMenu() {
        System.out.println("===========" + " Cadastro de Profissionais " + "==========");
        System.out.println("[ 1 ] - Inserir Profissional");
        System.out.println("[ 2 ] - Consultar Profissionais");
        System.out.println("[ 3 ] - Consultar um Profissional");
        System.out.println("[ 4 ] - Alterar Profissional");
        System.out.println("[ 5 ] - Deletar Profissional");
        System.out.println("[ 6 ] - Sair");
        System.out.println("===============================================");
    }
    public static void menu() {
        mainloop: while (true) {
            topMenu();
            int opcao = Menu.getOption();
            switch (opcao) {
                case 1:
                    String nome = Input.next("Nome do novo profissional: ");
                    String profissao = Input.next("Profissão: ");
                    double salario = Input.nextDouble("Salario: R$");

                    if (Crud.addProfessional(nome, profissao, salario)) {
                        System.out.println("Profissional Adicionado com Sucesso!");
                    } else {
                        System.out.println("Houve um erro ao adicionar o profissional!");
                    }
                    break;
                case 2:
                    Crud.listProfessionals();
                    break;
                case 3:
                    int idConsulta = Input.nextInt("Digite o id: ");
                    Crud.listProfessionals(idConsulta);
                    break;
                case 4:
                    int idAlter = Input.nextInt("Digite o id: ");
                    if (Crud.listProfessionals(idAlter)) {
                        String novoNome = Input.next("Novo Nome: ");
                        String novaProfissao = Input.next("Nova Profissão: ");
                        double novoSalario = Input.nextDouble("Novo Salario: ");

                        Crud.alterProfessional(new Profissional(idAlter, novoNome, novaProfissao, novoSalario));
                    }
                    break;
                case 5:
                    int idDelete = Input.nextInt("Digite o id: ");
                    if (Crud.listProfessionals(idDelete)) {
                        if (Crud.deleteProfessional(idDelete)) {
                            System.out.printf("Profisional %d deletado com sucesso!\n", idDelete);
                        } else {
                            System.out.println("Não foi possivel deletar!");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break mainloop;
                default:
                    System.out.println("Essa opção não existe");
            }
        }
    }
    public static int getOption() {
        String opcao = "";
        while (isNumeric(opcao)) {
            opcao = Input.next("---> ");
        }
        return Integer.parseInt(opcao);
    }

    public static boolean isNumeric(String str) {
        for (char charactere : str.toCharArray()) {
            if ("123456789".contains(String.valueOf(charactere))) {
                return false;
            }
        }
        return true;
    }
}
