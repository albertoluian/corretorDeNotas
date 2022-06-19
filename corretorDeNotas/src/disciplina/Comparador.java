package disciplina;

import java.util.Comparator;

class Comparador implements Comparator
{
    
    @Override
    public int compare(Object o1, Object o2) {
        String nota1 = ((String) o1).split("\t")[1];
        String nota2 = ((String) o2).split("\t")[1];
        return (Integer.parseInt(nota2) - Integer.parseInt(nota1));
    }
}