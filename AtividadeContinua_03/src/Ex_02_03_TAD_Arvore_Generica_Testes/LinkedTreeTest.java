package Ex_02_03_TAD_Arvore_Generica_Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex_02_03_TAD_Arvore_Generica_Fontes.LinkedTree;
import Ex_02_03_TAD_Arvore_Generica_Fontes.NodePositionList;
import Ex_02_03_TAD_Arvore_Generica_Fontes.TreeNode;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.Position;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.PositionList;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.TreePosition;

/*Atividade Continua 03
 * 
 * Nome dos Integrantes:
 * Caio Victor dos Santos - 1904013
 * Cristhian Ocampo Quinteiro – 1902919
 * Thiago Souza do Amparo – 1904089
 * 
 * Indice dos Exercicios:
 * 
 * Questão 2: Arquivo LinkedTree, LinkedTreeTest.
 * 
 * Questão 3: Arquivo LinkedTree a partir da linha 124.
 * 
 * 		A): Arquivo LinkedTree, linha 126.
 * 
 *  	B): Arquivo LinkedTree, linha 162.
 *  
 *   	C): Arquivo LinkedTree, linha 174, Arquivo LinkedTreeTest, linha 146.
 *   
 * 		D): Arquivo LinkedTree, linha 201.
 * 
 *  	E): Arquivo LinkedTree, linha 208.
 *  
 * 		F): Arquivo LinkedTree, linha 222.
 */

class LinkedTreeTest {

	@Test
	void test() {
		
		TreePosition<String> raiz;
		Position<Position<String>> p, s;
		PositionList<Position<String>> filhos;
		LinkedTree<String> T = criarArvoreT();
		
		System.out.println(T.parentheticRepresentation(T, T.root()));
		System.out.println(T.toStringPostorder(T, T.root()));
		
		assertFalse(T.isEmpty());
		assertEquals(4, T.height1(T), "Altura da Árvore T");
		assertEquals(4, T.height2(T, T.root()), "Altura da Árvore T");
		assertEquals("[Eletronics R'Us, P&D, Vendas, Internacional, Canadá, América do Sul, "
				+ "Ultramar, África, Europa, Ásia, Austrália, Nacional, Compras, Manufatura, TV, CD, Tuner]", T.toString(), "Pré-ordem da Árvore T ");
		
		raiz = T.root();
		filhos = raiz.getChildren();
		p = filhos.first();
		
		assertEquals("P&D", p.element().element(), "P&D");
		assertTrue(T.isExternal(p.element()));
		assertEquals(raiz, T.parent(p.element()), "Deve ser a raiz");

		s = filhos.next(p);

		assertEquals("Vendas", s.element().element(), "Vendas");
		assertTrue(T.isInternal(s.element()));
		assertEquals(1, T.depth(T, s.element()), "");
		
		T.replace(p.element(), "Pesquisa e Desenvolvimento");

		assertEquals("[Eletronics R'Us, Pesquisa e Desenvolvimento, Vendas, Internacional, Canadá, América do Sul, "
				+ "Ultramar, África, Europa, Ásia, Austrália, Nacional, Compras, Manufatura, TV, CD, Tuner]",T.toString(), "Pré-ordem da Árvore T ");
		
		assertTrue(T.isRoot(raiz));
		
		T.swapElements(p.element(), s.element());
		
		assertEquals("[Eletronics R'Us, Vendas, Pesquisa e Desenvolvimento, Internacional, Canadá, América do Sul, "
				+ "Ultramar, África, Europa, Ásia, Austrália, Nacional, Compras, Manufatura, TV, CD, Tuner]",T.toString(), "Pré-ordem da Árvore T ");		
	}	
	
	private TreeNode<String> criarFilho(TreeNode<String> raiz, String elemento) {

		PositionList<Position<String>> filhos;
		TreeNode<String> novoNodo;

		filhos = raiz.getChildren();

		novoNodo = new TreeNode<String>();

		novoNodo.setElement(elemento);
		novoNodo.setParent(raiz);
		novoNodo.setChildren(new NodePositionList<Position<String>>());
		filhos.addLast(novoNodo);

		return novoNodo;
	}
	
	public LinkedTree<String> criarArvoreT() {

		LinkedTree<String> T = new LinkedTree<String>();
		TreeNode<String> raiz, v, m, i, u;	
		
		T.addRoot("Eletronics R'Us");		
		raiz = (TreeNode<String>) T.root();
		raiz.setChildren(new NodePositionList<Position<String>>());

		// Filhos da raiz: Eletronic R'Us

		criarFilho(raiz, "P&D");
		v = criarFilho(raiz, "Vendas");
		criarFilho(raiz, "Compras");
		m = criarFilho(raiz, "Manufatura");

		// Filhos de Vendas

		i = criarFilho(v, "Internacional");
		criarFilho(v, "Nacional");

		// Filhos de Internacional

		criarFilho(i, "Canadá");
		criarFilho(i, "América do Sul");
		u = criarFilho(i, "Ultramar");

		// Filhos de Ultramar

		criarFilho(u, "África");
		criarFilho(u, "Europa");
		criarFilho(u, "Ásia");
		criarFilho(u, "Austrália");

		// Filhos de Manufatura

		criarFilho(m, "TV");
		criarFilho(m, "CD");
		criarFilho(m, "Tuner");

		return T;
	}
	
	public LinkedTree<String> createTreeDiscNode(){
		
		//Instancia e cria uma árvore generica.
		LinkedTree<String> treeDiscNode = new LinkedTree<String>();		
		
		//Adiciona um nodo a raiz da árvore.
		treeDiscNode.addRoot("/usuário/rt/cursos/ 1");
		
		//Guarda a referencia da raiz.
		TreeNode<String> raiz = (TreeNode<String>) treeDiscNode.root();
		
		//Instancia e cria um lista de filhos para a raiz.
		raiz.setChildren(new NodePositionList<Position<String>>());
		
		//Cria os filhos da raiz.
		TreeNode<String> F1 = criarFilho(raiz, "cs016/ 2");
		TreeNode<String> F2 = criarFilho(raiz, "cs252/ 1");
		
		//Cria os filhos do filho F1 da raiz.
		criarFilho(F1, "notas 8");
		TreeNode<String> F4 = criarFilho(F1, "temas/ 1");
		TreeNode<String> F5 = criarFilho(F1, "programas/ 1");
		
		//Cria os filhos do filho F4 de F1.
		criarFilho(F4, "hw1 3");
		criarFilho(F4, "hw2 2");
		criarFilho(F4, "hw3 4");
		
		//Cria os filhos do filho F5 de F1.
		criarFilho(F5, "pr1 57");
		criarFilho(F5, "pr2 97");
		criarFilho(F5, "pr3 74");		
		
		//Cria os filhos do filho F2 da raiz.
		TreeNode<String> F3 = criarFilho(F2, "projetos/ 1");
		criarFilho(F2, "notas 3");
		
		//Cria os filhos do filho F3 de F2.
		TreeNode<String> F7 = criarFilho(F3, "trabalhos/ 1");
		TreeNode<String> F8 = criarFilho(F3, "demos/ 1");
		
		//Cria os filhos do filho F7 de F3.
		criarFilho(F7, "comprebaixo 26");
		criarFilho(F7, "vendaalto 55");
		
		//Cria os filhos do filho F8 de F3.
		criarFilho(F8, "mercado 4786");		
		
		//Retorna a árvore criada.
		return treeDiscNode;		
	}
	
	@Test
	void testTreeDiscNode() {
		
		//Instancia e cria uma árvore de DiscNode.
		LinkedTree<String> treeDiscNode = createTreeDiscNode();
		
		//Imprimi a árvore criada no console.
		System.out.println(treeDiscNode.parentheticRepresentation(treeDiscNode, treeDiscNode.root()));
		
		//Separador.
		System.out.println();
		
		//Imprimi o nome e tamnho dos nodos da arvore e testa o tamanho total da arvore.
		assertEquals(5124, treeDiscNode.diskSpace(treeDiscNode, treeDiscNode.root()));		
		
		//Separador.
		System.out.println();
	}
}
