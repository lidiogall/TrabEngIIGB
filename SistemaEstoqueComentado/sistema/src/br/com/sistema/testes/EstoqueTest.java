package br.com.sistema.testes;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import br.com.sistema.negocio.Estoque;
import br.com.sistema.negocio.Produto;


public class EstoqueTest extends Estoque {

    
	private Estoque estoque = new Estoque();
    private Produto p1 = new Produto(1, "Katana", 3, 0, 170.0);
    private Produto p2 = new Produto(1, "Livro Harry Potter", 6, 0, 50.0);

    @Test
    public void devePoderAdicionarNoEstoque() {

        //Tenta adicionar o produto depois verifica se voltou um true = adicionou e se tem um produto dentro do estoque
        boolean retorno = this.estoque.adicionaProduto(p1);
        assertTrue(retorno);
        assertEquals(1, this.estoque.getQuantidade());
    }

    @Test
    public void naoDeveAdicionarProdutoComMesmoId() {

        //Adiciona um produto depois tenta adicionar um produto com o mesmo id, nesse caso é o mesmo produto mas poderia
        //ser estanciado um novo só repetindo o id, até seria legal fazer isso
        boolean retorno = this.estoque.adicionaProduto(p1);
        retorno = this.estoque.adicionaProduto(p1);
        //Testa se o segundo retorno foi false = não adicionou o produto com id de um produto que já existe
        //e testa se só tem 1 produto no estoque
        assertFalse(retorno);
        assertEquals(1, this.estoque.getQuantidade());
    }

    @Test
    public void deveRemoverProduto(){

        //Teste para a remoção do produto
        boolean retorno = this.estoque.adicionaProduto(p1);
        retorno = this.estoque.removeProduto(p1);

        //Testa se realmente removeu = true e verifica se o estoque está vazio
        assertTrue(retorno);
        assertEquals(0, this.estoque.getQuantidade());
    }

    @Test
    public void naoDeveRemoverProdutoSeEleNaoEstiverNoEstoque(){

        boolean retorno = this.estoque.adicionaProduto(p1);
        retorno = this.estoque.removeProduto(p2);

        //Testa se não conseguiu remover o p2 que não foi inserido no estoque
        //e verifica se o p1 continua no estoque
        assertFalse(retorno);
        assertEquals(1, this.estoque.getQuantidade());
    }
}