package Ex_04_05_TAD_Arvore_Binaria_Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex_04_05_TAD_Arvore_Binaria_Fontes.BTNode;
import Ex_04_05_TAD_Arvore_Binaria_Fontes.LinkedBinaryTree;
import Ex_04_05_TAD_Arvore_Binaria_Interfaces.BTPosition;

/*Atividade Continua 03
 * 
 * Nome dos Integrantes:
 * Caio Victor dos Santos - 1904013
 * Cristhian Ocampo Quinteiro – 1902919
 * Thiago Souza do Amparo – 1904089
 * 
 * Indice dos Exercicios:
 * 
 * Questão 4: Arquivo LinkedBinaryTree, LinkedBinaryTreeTest.
 * 
 * Questão 5: Arquivo LinkedBinaryTree a partir da linha 245, Arquivo LinkedBinaryTreeTest a partir da linha 112.
 * 
 * 		A): Arquivo LinkedBinaryTree, linha 247.
 * 
 * 		B): Arquivo LinkedBinaryTree, linha 296.
 * 
 * 		C): Arquivo LinkedBinaryTree, linha 326.
 * 
 * 		D): Arquivo LinkedBinaryTree, linha 357.
 * 
 * 		E): Arquivo LinkedBinaryTree, linha 400.
 * 
 * 		F): Arquivo LinkedBinaryTree, linha 431, Arquivo LinkedBinaryTreeTest, linha 112.
 * 
 * 		G): Arquivo LinkedBinaryTree, linha 451, Arquivo LinkedBinaryTreeTest, linha 220.
 * 
 * 		H): Arquivo LinkedBinaryTree, linha 513.
 * 
 * 		I): Arquivo LinkedBinaryTree, linha 548.
 * 
 * 		J): Arquivo LinkedBinaryTree, linha 576
 * 
 * 		K): Arquivo LinkedBinaryTree, linha 623.
 * 
 */

class LinkedBinaryTreeTest {	
	
	//Método que irá criar os nodos da árvore binaria.
	public BTPosition<String> createNodeSon(String element, BTPosition<String> parent, BTPosition<String> left, BTPosition<String> right) {
		
		//Retorna o nodo criado.
		return new BTNode<String>(element, parent, left, right);
	}
	
	public LinkedBinaryTree<String> createTreeBinaria() {
		
		//Instancia uma árvore binaria.
		LinkedBinaryTree<String> arvoreBinaria = new LinkedBinaryTree<String>();
		
		//Adiciona uma raiz a árvore.
		arvoreBinaria.addRoot("Davi");
		
		//Armazena a referencia da raiz da árvore.
		BTNode<String> raiz = (BTNode<String>) arvoreBinaria.root();
		
		//Cria o filho left e o filho right do nodo raiz.
		raiz.setLeft(createNodeSon("Fernanda", raiz, null, null));
		raiz.setRight(createNodeSon("Pedro", raiz, null, null));
		
		//Armazena a referencia dos filhos da raiz.
		BTNode<String> fernanda = (BTNode<String>) raiz.getLeft();
		BTNode<String> pedro = (BTNode<String>) raiz.getRight();
		
		//Cria o filho left e o filho right do filho esquerdo do nodo raiz (Fernanda).
		raiz.getLeft().setLeft(createNodeSon("João", fernanda, null, null));
		raiz.getLeft().setRight(createNodeSon("Maria", fernanda, null, null));		
		
		//Cria o filho left e o filho right do filho direito do nodo raiz (Pedro).
		raiz.getRight().setLeft(createNodeSon("Paulo", pedro, null, null));
		raiz.getRight().setRight(createNodeSon("Paula", pedro, null, null));
		
		//Armazena a referencia do joão.
		BTNode<String> joao = (BTNode<String>) fernanda.getLeft();
		
		//Armazena a referencia da paula.
		BTNode<String> paula = (BTNode<String>) pedro.getRight();
		
		//Cria o filho esquerdo do joão.		
		joao.setLeft(createNodeSon("David", joao, null, null));
		
		//Cria o filho direito da paula.
		paula.setRight(createNodeSon("Patrick", paula, null, null));
		
		//Retorna a árvore binaria criada.
		return arvoreBinaria;
	}
	
	@Test
	void testArvoreBinaria() {
		
		//Chama o método createTreeBinaria para criar a árvore binaria.
		LinkedBinaryTree<String> arvoreBinaria = createTreeBinaria();
		
		//Testa se Davi é o root da árvore.
		assertEquals("Davi", arvoreBinaria.root().element(), "Deve retornar Davi");
		
		//Testa se a raiz tem filho, isso é se ela é um nodo interno.
		assertTrue(arvoreBinaria.isInternal(arvoreBinaria.root()));
		
		//Armazena a referencia do filho esquerdo da raiz
		BTNode<String> fernanda = (BTNode<String>) arvoreBinaria.left(arvoreBinaria.root());
		
		//Testa se o filho esquerdo da raiz é a Fernanda.
		assertEquals("Fernanda", fernanda.element(), "Deve retornar Fernanda");
		
		//Armazena a referencia do filho esquerdo da Fernanda.
		BTNode<String> joao = (BTNode<String>) fernanda.getLeft();
		
		//Testa se o filho da Fernanda é João.
		assertEquals("João", joao.element().toString(), "Deve retornar João");
		
		//Testa se o nodo pai de João é a Fernanda.
		assertEquals("Fernanda", joao.getParent().element());
		
		//Testa se a ordem da árvore binaria está correta.
		assertEquals("[Davi, Fernanda, João, David, Maria, Pedro, Paulo, Paula, Patrick]", arvoreBinaria.toString());		
	}
	
	//Exercicio 5 - Questão F): Irá criar uma árvore binaria de busca.
	public LinkedBinaryTree<String> makerBTSearch(){
		
		//Instancia uma árvore binaria.
		LinkedBinaryTree<String> arvoreBinaria = new LinkedBinaryTree<String>();		
		
		//Adiciona uma raiz a árvore.
		arvoreBinaria.addRoot("58");		
		
		//Armazena a referencia da raiz da árvore.
		BTNode<String> raiz = (BTNode<String>) arvoreBinaria.root();
		
		//Cria o filho left e o filho right do nodo raiz.
		raiz.setLeft(createNodeSon("31", raiz, null, null));
		raiz.setRight(createNodeSon("90", raiz, null, null));
		
		//Armazena a referencia dos filhos da raiz.
		BTNode<String> F1 = (BTNode<String>) raiz.getLeft();
		BTNode<String> F2 = (BTNode<String>) raiz.getRight();
		
		//Cria e armazena a referencia dos filhos de F1.
		F1.setLeft(createNodeSon("25", F1, null, null));
		F1.setRight(createNodeSon("42", F1, null, null));
		
		//Armazena a referencia dos netos de F1.
		BTNode<String> F3 = (BTNode<String>) F1.getLeft();
		BTNode<String> F4 = (BTNode<String>) F1.getRight();
		
		//Cria e armazena a referencia dos filhos de F2.
		F3.setLeft(createNodeSon("12", F3, null, null));
		F4.setLeft(createNodeSon("36", F4, null, null));
		
		//Cria e armazena a referencia dos filhos de F2.
		F2.setLeft(createNodeSon("62", F2, null, null));
		
		//Armazena a referencia dos netos de F2.
		BTNode<String> F5 = (BTNode<String>) F2.getLeft();
		
		F5.setRight(createNodeSon("75", F5, null, null));
		
		//Retorna a árvore binaria criada.
		return arvoreBinaria;		
	}
	
	@Test
	void testmakerBTSearch() {		
		
		//Chama o método makerBTSearch para criar a árvore binaria.
		LinkedBinaryTree<String> arvoreBinaria = makerBTSearch();
		
		//Testa se 58 é o root da árvore.
		assertEquals("58", arvoreBinaria.root().element(), "Deve retornar 58");		
		
		//Testa se a ordem da árvore binaria está correta.
		assertEquals("[58, 31, 25, 12, 42, 36, 90, 62, 75]", arvoreBinaria.toString());
		
		//Exercicio 5 - Questão F): Percorre a árvore InOrdem.
		assertEquals("[12, 25, 31, 36, 42, 58, 62, 75, 90]", arvoreBinaria.toStringInOrder(arvoreBinaria));		
	}

	@Test
	void testBuildExpression() {
		
		//Instancia uma árvore binaria.
		LinkedBinaryTree<String> arvore = new LinkedBinaryTree<String>();
		
		//Cria uma árvore binaria de expressões.
		LinkedBinaryTree<String> arvoreExpressoes = arvore.buildExpression("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))");
		
		//Testa se a ordem da árvore binaria de expressões está correta.
		assertEquals("[-, /, *, +, 3, 1, 3, +, -, 9, 5, 2, +, *, 3, -, 7, 4, 6]", arvoreExpressoes.toString());
		
		//Testa se o método binaryPreorder está funcionando corretamente.
		assertEquals("-/*+313+-952+*3-746", arvoreExpressoes.binaryPreorder(arvoreExpressoes, arvoreExpressoes.root()));		
		
		//Testa se o método binaryPostorder está funcionando corretamente.
		assertEquals("31+3*95-2+/374-*6+-", arvoreExpressoes.binaryPostorder(arvoreExpressoes, arvoreExpressoes.root()));		
		
		//Testa se o método evaluateExpression está funcionando corretamente.
		assertEquals(-13.0, arvoreExpressoes.evaluateExpression(arvoreExpressoes, arvoreExpressoes.root()));		
		
		//Testa se o método inorder está funcionando corretamente.
		assertEquals("3+1*3/9-5+2-3*7-4+6", arvoreExpressoes.inorder(arvoreExpressoes, arvoreExpressoes.root()));		
		
		//Testa se o método eulerTour está funcionando corretamente.
		assertEquals("-/*+333+111+*333*/+-999-555-+222+/-+*333*-777-444-*+666+-", arvoreExpressoes.eulerTour(arvoreExpressoes, arvoreExpressoes.root()));
		
		//Testa se o método printExpression está funcionando corretamente.
		assertEquals("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))", arvoreExpressoes.printExpression(arvoreExpressoes, arvoreExpressoes.root()));		
		
		//Testa se o método countLeft está funcionando corretamente (Conta os nodes esquerdos e sem filhos da árvore binaria).
		assertEquals(6, arvoreExpressoes.countLeft(arvoreExpressoes, arvoreExpressoes.root()));
		
		//Testa se o método countRight está funcionando corretamente (Conta os nodes esquerdos e sem filhos da árvore binaria).
		assertEquals(4, arvoreExpressoes.countRight(arvoreExpressoes, arvoreExpressoes.root()));
		
		//Exercicio 5 - Questão G): Irá imprimir no console a árvore binaria, de acordo com seus nodos.
		arvoreExpressoes.pintTreeBinary(arvoreExpressoes);
	}
}
