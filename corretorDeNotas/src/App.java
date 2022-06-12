import disciplina.Disciplina;
import disciplina.GerenciadorDisciplinas;

public class App {
    public static void main(String[] args) throws Exception {
        GerenciadorDisciplinas ger = new GerenciadorDisciplinas();

        ger.recuperarDisciplinas();

        ger.menu();
        
    }
}
