package br.com.sistema.negocio;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    public List<Produto> produtos;

    public Estoque() {
        produtos = new ArrayList<>();
    }

    public List<Produto> getProdutos() {

        return this.produtos;
    }

    //Pareceu ser util para sabermos se temos um estoque vazio ou caso precisa-se saber quantos produtos temos
    public int getQuantidade() {

        return produtos.size();
    }

    public boolean adicionaProduto(Produto produto) {
        //Para garntir que o produto recebido não tenha o mesmo id de um produto que já esteja no estoque
        boolean podeAdicionar = this.validaProduto(produto.getId());

        if (podeAdicionar) {
            this.produtos.add(produto);
            return true;
        }

        return false;
    }

    //O método retorna um boolean pois é interessante saber se foi possivel remover o produto
    public boolean removeProduto(Produto produto) {
        if (this.produtos.size() == 0) {
            return false;
        }
        return this.produtos.remove(produto);
    }

    //Privado pois é algo de uso interno da classe
    private boolean validaProduto(int idProduto) {
        if (this.produtos.size() == 0) {
            return true;
        }
        for (Produto produto : this.produtos) {
            if (produto.getId() == idProduto) {
                return false;
            }
        }
        return true;
    }

    //Foi criado pensando na utilidade de se alterar as quantidades do produto, pois teria que saber que produto que seria alterado
    public Produto buscaPorId(int idProduto) throws NullPointerException {
        for (Produto produto : this.produtos) {
            if (produto.getId() == idProduto) {
                return produto;
            }
        }
        //Joga a exceção pois o método precisa retornar um produto caso seja digitado um id de produto que exista
        //caso desse errado teria que ser retorna um produto null e isso é feio e teria que ser trado fora
        //com a exceção fica melhor de se entender o que aconteceu
        throw new NullPointerException();
    }

    //Pareceu ser melhor criar uma array list nova só com os produtos com quantidade em baixa, assim podendo iterar em cima dela
    //sem se preocupar
    public List<Produto> getProdutosQuantidadeBaixa() {
        List<Produto> emQuantidadebaixa = new ArrayList<>();
        for (Produto produto : this.produtos) {

            if (produto.getQuantidade() < produto.getQuantidadeMinima()) {
                emQuantidadebaixa.add(produto);
            }
        }
        return emQuantidadebaixa;
    }

}
