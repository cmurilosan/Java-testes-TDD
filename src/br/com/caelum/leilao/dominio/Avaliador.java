package br.com.caelum.leilao.dominio;

public class Avaliador {
    
    private double maiorDeTodos = Double.NEGATIVE_INFINITY; //Retornar o maior valor
    private double menorDeTodos = Double.POSITIVE_INFINITY; //Retornar o menor valor

    public void avalia(Leilao leilao) {

        for (Lance lance :
                leilao.getLances()) {
            if (lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            else if (lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
        }
    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorDeTodos() {
        return menorDeTodos;
    }
}
