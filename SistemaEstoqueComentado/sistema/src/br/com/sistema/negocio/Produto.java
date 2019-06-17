package br.com.sistema.negocio;


public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private int quantidadeMinima;
    private double valor;

    //A quantidade minima poderia ser opicional e setar ela sempre em 0 talvez
    public Produto(Integer id, String nome, int quantidade, int quantidadeMinima, double valor) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.valor = valor;
    }

    public String getNome() {

        return this.nome;
    }

    public int getQuantidade() {

        return this.quantidade;
    }

    public int getQuantidadeMinima() {
        return this.quantidadeMinima;
    }

    public double getValor() {

        return this.valor;
    }

    public int getId() {

        return this.id;
    }

    public void adiciona(int quantidade) {
        this.quantidade += quantidade;
    }

    //Caso exceda o estoque ele não retira a quantidade de produtos
    public boolean remove(int quantidade) {
        if (this.quantidade - quantidade < 0) {
            return false;
        }
        this.quantidade -= quantidade;
        return true;
    }

    public void alteraQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

}
