package br.com.caelum.leilao.dominio;

public class Avaliador {
    
    private double maiorDeTodos = Double.NEGATIVE_INFINITY; //Retornar o maior valor
    private double menorDeTodos = Double.POSITIVE_INFINITY; //Retornar o menor valor
    private double media = 0;

    public void avalia(Leilao leilao) {

        double total = 0;
        for (Lance lance :
                leilao.getLances()) {
            if (lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if (lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
            total += lance.getValor();
        }
        if (total == 0) {
            media = 0;
            return;
        }
        media = total / leilao.getLances().size();
    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorDeTodos() {
        return menorDeTodos;
    }

    public double getMedia() {
        return media;
    }
}
