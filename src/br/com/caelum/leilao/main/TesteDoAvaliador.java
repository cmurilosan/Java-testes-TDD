package br.com.caelum.leilao.main;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteDoAvaliador {

    public static void main(String[] args) {

        // cenario: 3 lances em ordem crescente
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5 novo");

        leilao.propoe(new Lance(joao,250.0));
        leilao.propoe(new Lance(jose,300.0));
        leilao.propoe(new Lance(maria,400.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // exibindo a saida
        System.out.println(leiloeiro.getMaiorLance());  //imprime 400.0
        System.out.println(leiloeiro.getMenorDeTodos());    //imprime 250.0
    }
}
