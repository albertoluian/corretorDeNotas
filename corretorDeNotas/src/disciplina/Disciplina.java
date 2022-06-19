package disciplina;

import java.io.*;
import java.util.*;

public class Disciplina {
    private int id;
    private String nome;
    private String caminhoDiretorio;

    public Disciplina(int id, String nome, boolean backup) {
        File file = new File("src/disciplinas");
        // System.out.println(file.getPath());
        file.mkdir();
        File nomeDir = new File(file, nome);
        nomeDir.mkdir();
        File idDir = new File(nomeDir, "" + id);
        idDir.mkdir();
        FileWriter out = null;
        BufferedWriter bw = null;

        if(!backup)
        {
            try {
                out = new FileWriter(file.getPath() + "/info.txt", true);
                
                bw = new BufferedWriter(out);
                bw.write(id+"\t"+nome+"\t" + "\t"+idDir.getPath());
                bw.newLine();

                bw.close();
            } catch (Exception e) {
            }
        }
        
        this.id = id;
        this.nome = nome;
        this.caminhoDiretorio = idDir.getPath();
        try {
            out = new FileWriter(caminhoDiretorio + "/"+ nome+ ".txt", true);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public void inserirNotas(int quantidade, Scanner sc) {
        FileWriter out = null;
        BufferedWriter bw = null;
        try {
            out = new FileWriter(this.caminhoDiretorio + "/"+ nome+ ".txt", true);
            bw = new BufferedWriter(out);
        } catch (Exception e) {
        }
        for (int i = 0; i < quantidade; i++) {
            try {
                
                System.out.println("Digite o nome do aluno");
                String nome = sc.next();
                System.out.println("Digite as respostas do aluno");

                int tamanho = 0;
                
                while(tamanho < 10)
                {
                    byte a = (byte) System.in.read();

                    if('a' <= a && a <= 'z')
                    {
                        a += 'A' - 'a';
                    }
                    // System.out.println(a + "         a");
                    if(a != '\n')
                    {
                        bw.write(a);
                        tamanho++;
                    }
                }


                bw.write("\t" + nome + '\n');
                // bw.newLine();

            } catch (Exception e) {

            }
        }

        try {
            bw.close();
            out.close();
        } catch (IOException e) { }
    }

    public String mostrarNotasAlf()
    {
        FileReader fr = null;
        BufferedReader br = null; 

        String notas = null;

        try
        {
            fr = new FileReader(caminhoDiretorio + "/alfabetica.txt");
            br = new BufferedReader(fr);
            String linha = br.readLine();

            notas = "";

            while(linha != null && linha.strip() != "")
            {   
                notas += linha + '\n';
                linha = br.readLine();
            }
            
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
        try
        {
            br.close();
            fr.close();
        }
        catch(Exception e)
        { e.printStackTrace();}

        return notas;
    }
    public String mostrarNotasDec()
    {
        FileReader fr = null;
        BufferedReader br = null; 

        String notas = null;

        try
        {
            fr = new FileReader(caminhoDiretorio + "/decrescente.txt");
            br = new BufferedReader(fr);
            String linha = br.readLine();

            notas = "";

            while(linha != null && linha.strip() != "")
            {   
                notas += linha + '\n';
                linha = br.readLine();
            }
            
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
        try
        {
            br.close();
            fr.close();
        }
        catch(Exception e)
        { e.printStackTrace();}

        return notas;
    }

    public void corrigir(String caminhoGabarito)
    {
        FileReader fr = null;
        BufferedReader br = null; 

        String gabarito = null;

        ArrayList<String> notas = new ArrayList<String>();

        try
        {
            fr = new FileReader(caminhoGabarito);
            br = new BufferedReader(fr);
            gabarito = br.readLine().strip().toUpperCase();

            br.close();
            fr.close();
            
        } catch (IOException e)
        {
            System.out.println("Caminho invalido");
            return;
        }
        
        FileWriter fw = null;
        BufferedWriter bw = null;
        
        try
        {
            fw = new FileWriter(caminhoDiretorio + "/alfabetica.txt");
            bw = new BufferedWriter(fw);
            
            fr = new FileReader(caminhoDiretorio + "/"+ nome+ ".txt");
            br = new BufferedReader(fr);

            String linha = br.readLine();

            int somaNotas = 0,
                nAlunos = 0;

            while(linha != null && linha.strip() != "")
            {
                String[] infos = linha.split("\t");
                
                nAlunos++;

                int nota =0;
                for(int i = 0; i < 10; i++)
                {
                    if(infos[0].charAt(i) == gabarito.charAt(i))
                        nota++;
                }

                if(infos[0].equals("VVVVVVVVVV") || infos[0].equals("FFFFFFFFFF"))
                {
                    nota = 0;
                }

                somaNotas += nota;

                notas.add(infos[1] + "\t" + nota);

                linha = br.readLine();
            }
            
            double media = nAlunos != 0 ? ((double)somaNotas / nAlunos) : 0;

            Collections.sort(notas); // Achamos isso aqui que aparentemente ordena

            for(String nota: notas)
            {
                bw.write(nota);
                bw.newLine();
            }

            bw.write("" + media);
            Collections.sort(notas, new Comparador());
            bw.close();
            fw.close();
            fw = new FileWriter(caminhoDiretorio + "/decrescente.txt");
            bw = new BufferedWriter(fw);
            for(String nota: notas)
            {
                bw.write(nota);
                bw.newLine();
            }

            bw.write("" + media);
            bw.close();
            fw.close();
            
            br.close();
            fr.close();
        } catch (Exception e)
        {
            
        }
    }

    public void menu(Scanner sc)
    {
        // Scanner sc = new Scanner(System.in);
        
        while(true)
        {
            int i;

            System.out.println("1- Inserir notas\n2- Corrigir\n3- Listar por ordem alfabetica\n4- Listar por media\n0- Sair");

            while(true)
            {
                try
                {
                    i = Integer.parseInt(sc.next().strip());
                    break;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println("Numero de operacao invalido");
                }
            }

            if( i == 0)
                break;
            
            switch(i)
            {
                case 1:
                    System.out.println("Insira a quantidade de notas a serem inseridas");
                    while(true)
                    {
                        try
                        {
                            int nAlunos = Integer.parseInt(sc.next().strip());
                            inserirNotas(nAlunos, sc);
                            break;
                        }
                        catch(Exception e)
                        {
                            System.out.println("Insira um valor numerico");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Informe o caminho do gabarito");
                    corrigir(sc.next());
                    break;

                case 3:
                    System.out.println( mostrarNotasAlf());
                    break;
                case 4: // Mostrar por ordem das notas
                    System.out.println( mostrarNotasDec());
                    break;
            }
        }
        // sc.close();
    }

    @Override
    public String toString()
    {
        return nome + " " + id;
    }

    public String getCaminhoDiretorio() {
        return caminhoDiretorio;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setCaminhoDiretorio(String caminhoDiretorio) {
        this.caminhoDiretorio = caminhoDiretorio;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
