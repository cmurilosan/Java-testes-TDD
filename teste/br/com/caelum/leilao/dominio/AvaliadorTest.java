package br.com.caelum.leilao.dominio;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @Before
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 5").constroi();

        leiloeiro.avalia(leilao);
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new Leilao("Playstation 5 novo");

        leilao.propoe(new Lance(joao,250.0));
        leilao.propoe(new Lance(jose,300.0));
        leilao.propoe(new Lance(maria,400.0));

        // executando a acao
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Leilao leilao = new Leilao("Apple Watch 5");

        leilao.propoe(new Lance(joao,1000.0));

        leiloeiro.avalia(leilao);

        assertEquals(1000.0,leiloeiro.getMaiorLance(),0.00001);
        assertEquals(1000.0,leiloeiro.getMenorLance(),0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3,maiores.size());

        assertThat(maiores, hasItems(
                new Lance(maria, 400),
                new Lance(joao, 300),
                new Lance(maria, 200)
        ));
    }
}
