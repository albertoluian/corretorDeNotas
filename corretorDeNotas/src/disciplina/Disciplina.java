package disciplina;

import java.io.*;
import java.util.*;

public class Disciplina {
    private int id;
    private String nome;
    private String caminhoGabarito;
    private String caminhoDiretorio;

    public Disciplina(int id, String nome, String caminhoGabarito) {
        File file = new File("corretorDeNotas/src/disciplinas");
        System.out.println(file.getPath());
        file.mkdir();
        File nomeDir = new File(file, nome);
        nomeDir.mkdir();
        File idDir = new File(nomeDir, "" + id);
        idDir.mkdir();
        FileWriter out = null;
        BufferedWriter bw = null;
        try {
            out = new FileWriter(file.getPath() + "/info.txt", true);
        } catch (Exception e) {
        }
        try {
            bw = new BufferedWriter(out);
            bw.write(id+"\t"+nome+"\t"+caminhoGabarito+"\t"+idDir.getPath());
            bw.newLine();
        } catch (Exception e) {
        }
        
        this.id = id;
        this.nome = nome;
        this.caminhoGabarito = caminhoGabarito;
        this.caminhoDiretorio = idDir.getPath();
        try {
            out = new FileWriter(caminhoDiretorio + "/notas.txt");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void inserirNotas(int quantidade) {
        Scanner sc = new Scanner(System.in);
        FileWriter out = null;
        BufferedWriter bw = null;
        try {
            out = new FileWriter(this.caminhoDiretorio + "/notas.txt");
        } catch (Exception e) {
        }
        try {
            bw = new BufferedWriter(out);
        } catch (Exception e) {
        }
        for (int i = 0; i < quantidade; i++) {
            try {
                
                System.out.println("Digite o nome do aluno");
                String nome = sc.next();
                System.out.println("Digite as respostas do aluno");
                String respostas = sc.next();

                bw.write(respostas + "\t" + nome);
                bw.newLine();

            } catch (Exception e) {

            }
        }
        sc.close();

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
