package disciplina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorDisciplinas {
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

    public void recuperarDisciplinas()
    {
        FileReader fr = null;
        BufferedReader br = null; 

        try
        {
            fr = new FileReader("src/disciplinas/info.txt");
            br = new BufferedReader(fr);
            String linha = br.readLine();

            while(linha != null && linha.strip() != "")
            {
                String[] infos = linha.split("\t");
                disciplinas.add(new Disciplina(Integer.parseInt(infos[0]), infos[1], true));
                
                linha = br.readLine();
            }
            
        } catch (Exception e){ }
        
        try
        {
            br.close();
            fr.close();
        }
        catch(Exception e)
        { }
    }

    public boolean adicionarDisciplina() throws Exception 
    {
        Scanner s = new Scanner(System.in);

        int id = s.nextInt();
        String nome = s.next().strip().toUpperCase();
        
        for(Disciplina disciplina : disciplinas)
        {
            if(id == disciplina.getId() && nome == disciplina.getNome())
                return false;
        }

        disciplinas.add(new Disciplina(id, nome, false));

        return true;
    }

    public void listarDisciplinas()
    {
        for(int i = 0; i < disciplinas.size(); i++)
            System.out.println((i + 1) + " - " + disciplinas.get(i));
    }

    public void menu()
    {
        Scanner s = new Scanner(System.in);
        while(true)
        {
            int n;

            System.out.println("1- Listar disciplinas\n2- Adicionar disciplina\n0- Sair");

            while(true)
            {
                try
                {
                    n = Integer.parseInt(s.next().strip());
                    break;
                }
                catch(Exception e)
                {
                    // System.out.println("Numero de operacao invalido1");
                    e.printStackTrace();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                    }

                }
            }

            if(n == 0)
                break;
            
            switch(n)
            {
                case 1:
                    listarDisciplinas();
                    System.out.println("Escolha uma disciplina");
                    try
                    {
                        disciplinas.get(s.nextInt()-1).menu(s);
                    }
                    catch(Exception e)
                    { System.out.println("Posicao de disciplina invalida"); }
                    break;
                
                case 2:
                    System.out.println("Insira o id e o nome da disciplina");
                    try
                    {
                        boolean funcionou = adicionarDisciplina();
                        
                        if(!funcionou)
                            throw new Exception();
                    }catch (Exception e) {
                        System.out.println("Id invalido, tente novamente");
                    }
                    break;

                    
            }
        }
        s.close();
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
