import domain.models.Input;
import terminalvisual.Menu;
import infrastructure.dao.ProfessionalDao;

public class Main {
    public static void main(String[] args) {
        Menu.menu();
        System.out.println("Fim do programa!");
        Input.close();
        ProfessionalDao.close();
    }
}