package domain;


import domain.models.Input;
import domain.models.Profissional;
import infrastructure.dao.ProfessionalDao;

import java.sql.SQLException;
import java.util.List;

public class Crud {
    public static void printProfessional(Profissional profissional) {
        System.out.printf("ID: %d\n", profissional.getId());
        System.out.printf("NOME: %s\n", profissional.getNome());
        System.out.printf("PROFISSAO: %s\n", profissional.getProfissao());
        System.out.printf("SALARIO: R$%.2f\n", profissional.getSalario());
        System.out.println("================================");
    }
    public static void listProfessionals() {
        List<Profissional> profissionals = new ProfessionalDao().getProfessional();
        if (profissionals.size() != 0) {
            System.out.println("================================");
            for (Profissional profissional : profissionals) {
                printProfessional(profissional);
            }
        } else {
            System.out.println("Não tem profissionais cadastrados!");
        }
    }

    public static boolean listProfessionals(int id) {
        try {
            Profissional profissional = new ProfessionalDao().getProfessional(id);
            printProfessional(profissional);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Não foi encontrado profissional com esse id!");
            return false;
        }
    }

    public static boolean addProfessional (String nome, String profissao, double salario) {
        ProfessionalDao professionalDao = new ProfessionalDao();
        return professionalDao.addProfessional(nome, profissao, salario);
    }

    public static void alterProfessional(Profissional profissional) {
        ProfessionalDao professionalDao = new ProfessionalDao();
        if (professionalDao.alterProfessional(profissional)) {
            System.out.printf("Profisional %d alterado com sucesso!\n", profissional.getId());
        } else {
            System.out.println("Não foi possivel alterar o usuario!");
        }
    }

    public static boolean deleteProfessional(int id) {
        ProfessionalDao professionalDao = new ProfessionalDao();
        if (professionalDao.deleteProfessional(id)) {
            return true;
        } else {
            return false;
        }
    }
}
