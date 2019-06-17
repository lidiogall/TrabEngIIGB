package br.com.sistema.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import br.com.sistema.negocio.Produto;


class ProdutoTest {

    private Produto produto = new Produto(1, "Produto de teste", 5, 0, 25.0);

    @Test
    public void deveAdicionarQuantidade() {

        this.produto.adiciona(3);
        assertEquals(8, this.produto.getQuantidade());
    }

    @Test
    public void deveRemoverQuantidade(){

        boolean retorno = this.produto.remove(2);

        assertTrue(retorno);
        assertEquals(3,this.produto.getQuantidade());
    }

    @Test
    public void naoDeveRemoverMaisDoQueTem(){

        boolean retorno = this.produto.remove(6);

        assertFalse(retorno);
        assertEquals(5,this.produto.getQuantidade());
    }

}