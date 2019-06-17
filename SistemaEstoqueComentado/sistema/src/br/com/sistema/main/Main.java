package br.com.sistema.main;

import br.com.sistema.negocio.Estoque;
import br.com.sistema.negocio.Produto;
import br.com.sistema.ui.RelatoriosUI;
//import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Produto p1 = new Produto(1, "Katana", 3, 0, 170.0);
		Produto p2 = new Produto(2, "Camisa", 10, 5, 30.0);
		Produto p3 = new Produto(3, "Livro Harry Potter e a Pedra Filosofal", 3, 5, 60.0);
		Estoque estoque = new Estoque();

		estoque.adicionaProduto(p1);
		estoque.adicionaProduto(p2);
		estoque.adicionaProduto(p3);
		RelatoriosUI relatoriosUI = new RelatoriosUI(estoque);

		relatoriosUI.execute();
	}

}
