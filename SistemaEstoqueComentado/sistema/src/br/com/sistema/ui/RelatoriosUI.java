package br.com.sistema.ui;

import br.com.sistema.negocio.Estoque;
import br.com.sistema.negocio.Produto;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class RelatoriosUI {
    private Scanner teclado;
    private Estoque estoque;
    private int acao;


    public RelatoriosUI(Estoque estoque) {
        this.teclado = new Scanner(System.in);
        this.teclado.useLocale(Locale.ENGLISH);
        this.estoque = estoque;
    }

    //Unico método além do construtor que tem que ser visivel, é o que faz o sistema rodar
    //e fica aqui até resolver sair
    public void execute() {
        do {
            this.definirAcao();
            switch (this.acao) {
                case 1:
                    this.inserirProduto();
                    break;
                case 2:
                    this.adicionarQuantidadeProduto();
                    break;
                case 3:
                    this.retiraQuantidadeProduto();
                    break;
                case 4:
                    this.listarProdutos();
                    break;
                case 5:
                    this.listarProdutosComQuantidadeBaixa();
                    break;
                case 6:
                    System.out.println("At� logo!");
                    break;
            }
        } while (this.acao != 6);
    }

    //A ação poderia ser só uma variavel temporaria e dai esse método retornaria ela
    private void definirAcao() {
        System.out.println("Escolha uma op��o: ");
        System.out.println("1 - Inserir produto no estoque ");
        System.out.println("2 - Adicionar quantidade de um produto ");
        System.out.println("3 - Retirar quantidade de produto");
        System.out.println("4 - Listar produtos");
        System.out.println("5 - Listar produtos com quantidade baixa");
        System.out.println("6 - Sair");
        System.out.print("Op��o - ");
        this.acao = this.teclado.nextInt();
    }

    //Como a valida��o � de responsabilidade do estoque aqui n�o precisa validar só ver se conseguiu inserir ou não
    private void inserirProduto() {
        this.listarProdutos();
        Produto paraInserir = this.pedeDadosProdutos();
        //Tentar inserir no estoque
        boolean inseriu = this.estoque.adicionaProduto(paraInserir);
        //Se não conseguir avisar sobre o id
        if (!inseriu) {
            System.out.println("Id repetido, os ids não devem se repetir!");
        } else {
            this.listarProdutos();
        }
    }

    //Não sei se tem algo relevante para se comentar sobre isso, se ficar com alguma duvida me avisa xD
    private Produto pedeDadosProdutos() {
        System.out.println("Digite o id do novo produto: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Digite o nome do novo produto: ");
        String nome = teclado.nextLine();
        System.out.println("Digite a quantidade do novo produto: ");
        int quantidade = teclado.nextInt();
        System.out.println("Digite a quantidade minima do novo produto: ");
        int quantidadeMinima = teclado.nextInt();
        System.out.println("Digite o valor do novo produto: ");
        double valor = teclado.nextDouble();

        Produto novo = new Produto(id, nome, quantidade, quantidadeMinima, valor);
        return novo;
    }

    //Os ifs dentro dessa funções de quantidades poderiam estar em uma função separada
    //e ficaria mais semantico, mas como não vai ser feito nada mais além disso não pareceu necessario
    private void adicionarQuantidadeProduto() {
        boolean temProduto = this.listarProdutos();
        if (temProduto) {
            Produto paraAumentarQuantidade = this.selecionaProduto();
            System.out.print("Digite a quantidade que deseja adicionar: ");
            int quantidadeAdicionar = teclado.nextInt();
            paraAumentarQuantidade.adiciona(quantidadeAdicionar);
            this.listarProdutos();
        }
    }

    //Tanto nessa quanto na função anterior se lista os produtos para mostrar as alterações que foram ou não feitas
    private void retiraQuantidadeProduto() {
        boolean temProduto = this.listarProdutos();
        if (temProduto) {
            Produto paraDiminuirQuantidade = this.selecionaProduto();
            System.out.print("Digite a quantidade que deseja remover: ");
            int quantidadeRetirar = teclado.nextInt();
            boolean retirou = paraDiminuirQuantidade.remove(quantidadeRetirar);
            if (!retirou) {
                System.out.println("Quantidade m�xima excedida!");
            }
            this.listarProdutos();
        }

    }

    //O tratamento da exceção lançada na busca de produto por id é tratada aqui
    private Produto selecionaProduto() {
        System.out.print("Digite o id do produto desejado: ");
        int idProduto = teclado.nextInt();
        Produto selecionado = null;
        try {
            selecionado = this.estoque.buscaPorId(idProduto);

        } catch (NullPointerException npe) {
            npe.getStackTrace();
            System.out.println("Id inv�lido");
        }
        return selecionado;
    }

    //Pareceu util retornar um boolean parar não executar algumas funções
    private boolean listarProdutos() {
        if (this.estoque.getQuantidade() > 0) {
            System.out.println("Produtos em estoque:");
            for (Produto produto : this.estoque.getProdutos()) {
                System.out.println("Id - " + produto.getId() + ", Produto - " + produto.getNome() + ", Quantidade - " + produto.getQuantidade()
                        + ", Valor - " + produto.getValor() + ", Quantidade minima - " + produto.getQuantidadeMinima());
            }
            return true;
        } else {
            System.out.println("Seu estoque est� vazio!");
            return false;
        }
    }

    //Simplesmente lista produtos em quantidade baixa, se existirem produtos em quantidade baixa
    private void listarProdutosComQuantidadeBaixa() {

        List<Produto> comEstoqueBaixo = estoque.getProdutosQuantidadeBaixa();
        if (comEstoqueBaixo.size() > 0) {
            System.out.println("Produtos com estoque baixo:");
            for (Produto produto : comEstoqueBaixo) {
                System.out.println("Id - " + produto.getId() + ", Produto - " + produto.getNome() + ", Quantidade - " + produto.getQuantidade()
                        + ", Valor - " + produto.getValor() + ", Quantidade minima - " + produto.getQuantidadeMinima());
            }
        } else {
            System.out.println("Sem produtos com quantidade baixa!");
        }
    }
}
