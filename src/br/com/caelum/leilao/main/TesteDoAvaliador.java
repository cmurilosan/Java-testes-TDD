package br.com.caelum.leilao.main;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteDoAvaliador {

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

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

        // comparando a saida com o esperado
        assertEquals(400,leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(250,leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Usuario fulano = new Usuario("Fulano");
        Leilao leilao = new Leilao("Apple Watch 5");

        leilao.propoe(new Lance(fulano,1000.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(1000.0,leiloeiro.getMaiorLance(),0.00001);
        assertEquals(1000.0,leiloeiro.getMenorLance(),0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario nina = new Usuario("Nina");
        Usuario gabi = new Usuario("Gabi");
        Leilao leilao = new Leilao("Havaianas de Pau");

        leilao.propoe(new Lance(nina, 100.0));
        leilao.propoe(new Lance(gabi, 200.0));
        leilao.propoe(new Lance(nina, 300.0));
        leilao.propoe(new Lance(gabi, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3,maiores.size());
        assertEquals(400.0,maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0,maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0,maiores.get(2).getValor(), 0.00001);
    }
}
