import disciplina.Disciplina;

public class App {
    public static void main(String[] args) throws Exception {
        Disciplina d = new Disciplina(1, "POO", "/disciplinas/gab.txt");
        d.inserirNotas(2);
    }
}
