package Ex_04_05_TAD_Arvore_Binaria_Fontes;

import java.util.Iterator;

import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoArvoreNaoVazia;
import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoArvoreVazia;
import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoIndiceForaLimites;
import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoPosicaoInvalida;
import Ex_04_05_TAD_Arvore_Binaria_Interfaces.BTPosition;
import Ex_04_05_TAD_Arvore_Binaria_Interfaces.BinaryTree;
import Ex_04_05_TAD_Arvore_Binaria_Interfaces.Position;
import Ex_04_05_TAD_Arvore_Binaria_Interfaces.PositionList;

public class LinkedBinaryTree<TIPO> implements BinaryTree<TIPO> {
	
	protected BTPosition<TIPO> root;
	protected int size;
	
	public LinkedBinaryTree() {

		root = null;
		size = 0;
	}
	
	public int size() {return size;}
	
	public boolean isInternal(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		checkPosition(posNode);
		return (hasLeft(posNode) || hasRight(posNode));
	}
	
	public boolean isRoot(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		checkPosition(posNode);
		return (posNode == root());
	}
	
	public boolean hasLeft(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);
		return (posNodeValidado.getLeft() != null);
	}
	
	public Position<TIPO> root() throws ExcecaoArvoreVazia {

		if (root == null) throw new ExcecaoArvoreVazia("A �rvore est� vazia!");
		return root;
	}
	
	public Position<TIPO> left(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);
		Position<TIPO> leftPos = (Position<TIPO>) posNodeValidado.getLeft();
		if (leftPos == null) throw new ExcecaoIndiceForaLimites("N�o h� filhos a esquerda!");
		return leftPos;
	}
	
	public Position<TIPO> parent(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);
		Position<TIPO> parentPos = (Position<TIPO>) posNodeValidado.getParent();
		if (parentPos == null) throw new ExcecaoIndiceForaLimites("N�o h� pai para esse nodo!");
		return parentPos;
	}
	
	public Iterable<Position<TIPO>> children(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		PositionList<Position<TIPO>> children = new NodePositionList<Position<TIPO>>();
		if (hasLeft(posNode)) children.addLast(left(posNode));
		if (hasRight(posNode)) children.addLast(right(posNode));
		return children;
	}
	
	public Iterable<Position<TIPO>> positionsInorder() {

		PositionList<Position<TIPO>> positions = new NodePositionList<Position<TIPO>>();
		if (size != 0) inorderPositions(root(), positions);
		return positions;
	}
	
	public void inorderPositions(Position<TIPO> posNode, PositionList<Position<TIPO>> posArvore) throws ExcecaoPosicaoInvalida{
		
		if(hasLeft(posNode)) {inorderPositions(left(posNode), posArvore);}
		posArvore.addLast(posNode);
		if(hasRight(posNode)) {inorderPositions(right(posNode), posArvore);}
	}
	
	public Iterable<Position<TIPO>> positions() {

		PositionList<Position<TIPO>> positions = new NodePositionList<Position<TIPO>>();
		if (size != 0) preorderPositions(root(), positions);
		return positions;
	}
	
	public Iterator<TIPO> iterator() {

		Iterable<Position<TIPO>> positions = positions();
		PositionList<TIPO> elements = new NodePositionList<TIPO>();
		for (Position<TIPO> pos : positions) elements.addLast(pos.element());
		return elements.iterator();
	}
	
	public TIPO replace(Position<TIPO> posNode, TIPO elemento) throws ExcecaoPosicaoInvalida {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);
		TIPO elementoTemp = posNode.element();
		posNodeValidado.setElement(elemento);
		return elementoTemp;
	}
	
	public Position<TIPO> sibling(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);
		BTPosition<TIPO> parentPos = posNodeValidado.getParent();

		if (parentPos != null) {

			BTPosition<TIPO> sibPos;	
			BTPosition<TIPO> leftPos = parentPos.getLeft();
	
			if (leftPos == posNodeValidado) {sibPos = parentPos.getRight();}	
			else {sibPos = parentPos.getLeft();}
	
			if (sibPos != null) {return sibPos;}
		}
		throw new ExcecaoIndiceForaLimites("N�o h� irm�o para esse nodo!");
	}
	
	public Position<TIPO> addRoot(TIPO elemento) throws ExcecaoArvoreNaoVazia {

		if (!isEmpty()) {throw new ExcecaoArvoreNaoVazia("A �rvore j� tem uma raiz!");}

		size = 1;
		root = createNode(elemento, null, null, null);
		return root;
	}
	
	public Position<TIPO> insertLeft(Position<TIPO> posNode, TIPO elemento) throws ExcecaoPosicaoInvalida {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);
		Position<TIPO> leftPos = (Position<TIPO>) posNodeValidado.getLeft();

		if (leftPos != null) {throw new ExcecaoPosicaoInvalida("Esse n� j� tem um filho esquerdo!");}

		BTPosition<TIPO> novoNode = createNode(elemento, posNodeValidado, null, null);
		posNodeValidado.setLeft(novoNode);

		size++;
		return novoNode;
	}
	
	public TIPO remove(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);

		BTPosition<TIPO> leftPos = posNodeValidado.getLeft();
		BTPosition<TIPO> rightPos = posNodeValidado.getRight();

		if (leftPos != null && rightPos != null) {throw new ExcecaoPosicaoInvalida("N�o � poss�vel remover um n� com dois filhos!");}

		BTPosition<TIPO> filhoUnico;

		if (leftPos != null) {filhoUnico = leftPos;}
		else if (rightPos != null) filhoUnico = rightPos;
		else {filhoUnico = null;}

		if (posNodeValidado == root) {
	
			if (filhoUnico != null) {filhoUnico.setParent(null);}	
			root = filhoUnico;
			
		} else {

			BTPosition<TIPO> paiNode = posNodeValidado.getParent();
	
			if (posNodeValidado == paiNode.getLeft()) {paiNode.setLeft(filhoUnico);}	
			else {paiNode.setRight(filhoUnico);}
	
			if (filhoUnico != null) {filhoUnico.setParent(paiNode);}
		}
		size--;
		return posNode.element();
	}
	
	public void attach(Position<TIPO> posNode, BinaryTree<TIPO> ARVORE1, BinaryTree<TIPO> ARVORE2) throws ExcecaoPosicaoInvalida {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);

		if (isInternal(posNode)) throw new ExcecaoPosicaoInvalida("N�o � poss�vel anexar sobre n�s internos");

		if (!ARVORE1.isEmpty()) {
	
			BTPosition<TIPO> raiz1 = checkPosition(ARVORE1.root());	
			posNodeValidado.setLeft(raiz1);	
			raiz1.setParent(posNodeValidado);
		}
		if (!ARVORE2.isEmpty()) {

			BTPosition<TIPO> raiz2 = checkPosition(ARVORE2.root());	
			posNodeValidado.setRight(raiz2);	
			raiz2.setParent(posNodeValidado);
		}
	}
	
	protected BTPosition<TIPO> checkPosition(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		if (posNode == null || !(posNode instanceof BTPosition)) throw new ExcecaoPosicaoInvalida("Essa posi��o � inv�lida");
		return (BTPosition<TIPO>) posNode;
	}
	
	protected BTPosition<TIPO> createNode(TIPO element, BTPosition<TIPO> parent, BTPosition<TIPO> left, BTPosition<TIPO> right) {
		
		return new BTNode<TIPO>(element, parent, left, right);
	}
	
	protected void preorderPositions(Position<TIPO> posNode, PositionList<Position<TIPO>> posTree) throws ExcecaoPosicaoInvalida {

		posTree.addLast(posNode);

		if (hasLeft(posNode)) preorderPositions(left(posNode), posTree);
		if (hasRight(posNode)) preorderPositions(right(posNode), posTree);
	}
	
	public boolean isEmpty() {return (size == 0);}
	
	public boolean isExternal(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {return !isInternal(posNode);}
	
	public Position<TIPO> right(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);
		Position<TIPO> rightPos = (Position<TIPO>) posNodeValidado.getRight();
		
		if (rightPos == null) throw new ExcecaoIndiceForaLimites("N�o h� filhos a direita!");

		return rightPos;
	}
	
	public boolean hasRight(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		BTPosition<TIPO> posNodeValidado = checkPosition(posNode);
		return (posNodeValidado.getRight() != null);
	}
	
	//Exercicio 5 - Implementa��o dos M�todos (buildExpression, binaryPreorder, binaryPostorder, evaluateExpression, inorder, makerBTSearch, eulerTour, printExpression).
	
	//Exercicio 5 - Quest�o A): Ir� construir uma �rvore binaria de express�es.
	public LinkedBinaryTree<String> buildExpression(String expressao){
		
		//Pilha que ir� armazenar os elementos da express�o passada no paramentro.
		Pilha<LinkedBinaryTree<String>> pilha = new Pilha<LinkedBinaryTree<String>>();
		
		//Cria um arranjo de chars que recebe a string(expressao) informada.
		char caracteres[] = expressao.toCharArray();
		
		//La�o que percorre os elementos do arranjo de chars.
		for(int i = 0; i < expressao.length(); i++) {
			
			//Verifica se o caracterer atual da express�o � uma variavel ou operador.
			if(caracteres[i] != '(' && caracteres[i] != ')') {
				
				//Cria uma nova �rvore binaria vazia.
				LinkedBinaryTree<String> TREE = new LinkedBinaryTree<String>();
				
				//Adiciona o elemento da express�o na raiz da �rvore.
				TREE.addRoot(String.valueOf(caracteres[i]));
				
				//Adiciona a �rvore criada na pilha.
				pilha.push(TREE);
			
			//Verifica se o caracterer atual da express�o � um '(' (Parenteses aberto), caso seja o la�o continua.
			}else if(caracteres[i] == '(') {}
			
			//Caso seja um ')' (Parenteses fechado).
			else {
				
				//Coloca o ultimo elemento da pilha na variavel, (uma variavel da express�o).
				LinkedBinaryTree<String> TREE2 = pilha.pop();
				
				//Coloca o ultimo elemento da pilha na variavel, (um operador da express�o).
				LinkedBinaryTree<String> TREEOPERADOR = pilha.pop();
				
				//Coloca o ultimo elemento da pilha na variavel, (uma variavel da express�o).
				LinkedBinaryTree<String> TREE1 = pilha.pop();
				
				//Cria uma nova �rvore com variaveis e operador.
				TREEOPERADOR.attach(TREEOPERADOR.root(), TREE1, TREE2);
				
				//Adiciona a nova �rvore criada na pilha.
				pilha.push(TREEOPERADOR);
			}
		}
		return pilha.pop(); //Retorna a �rvore de express�es, que est� no topo da pilha.
	}
	
	//Exercicio 5 - Quest�o B): Ir� selecionar os elementos da �rvore binaria e concatena-los em uma String utilizando o preOrder.
	public String binaryPreorder(BinaryTree<TIPO> ARVORE, Position<TIPO> posNode) {
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expressao = posNode.element().toString();
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(ARVORE.hasLeft(posNode)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TIPO> filhoPosNode = ARVORE.left(posNode);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expressao += binaryPreorder(ARVORE, filhoPosNode);
		}
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(ARVORE.hasRight(posNode)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TIPO> filhoPosNode = ARVORE.right(posNode);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expressao += binaryPreorder(ARVORE, filhoPosNode);			
		}
		
		//Retorna a variavel com os elementos da arvore.
		return expressao;		
	}
	
	//Exercicio 5 - Quest�o C): Ir� selecionar os elementos da �rvore binaria e concatena-los em uma String utilizando o posToOrder.
	public String binaryPostorder(BinaryTree<TIPO> ARVORE, Position<TIPO> posNode) {
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expressao = "";
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(ARVORE.hasLeft(posNode)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TIPO> filhoPosNode = ARVORE.left(posNode);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expressao += binaryPostorder(ARVORE, filhoPosNode);
		}
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(ARVORE.hasRight(posNode)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TIPO> filhoPosNode = ARVORE.right(posNode);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expressao += binaryPostorder(ARVORE, filhoPosNode);			
		}
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		expressao += posNode.element().toString();			
		return expressao; //Retorna a variavel com os elementos da arvore.
	}
	
	//Exercicio 5 - Quest�o D): Ir� calcular os elementos da �rvore binaria de express�es.
	public double evaluateExpression(BinaryTree<TIPO> ARVORE, Position<TIPO> posNode) {
		
		//Verifica se o nodo tem filhos.
		if(ARVORE.isInternal(posNode)) {
			
			//Armazena o elemento do nodo na variavel, tal elemento deve ser um operador aritmetico.
			String operador = posNode.element().toString();
			
			//Chama recursivamente o m�todo evaluateExpression at� ele retornar um o elemento do ultimo filho da esquerda, tal elemento deve ser uma variavel.
			Double x = evaluateExpression(ARVORE, ARVORE.left(posNode));
			
			//Chama recursivamente o m�todo evaluateExpression at� ele retornar um o elemento do ultimo filho da direita, tal elemento deve ser uma variavel.
			Double y = evaluateExpression(ARVORE, ARVORE.right(posNode));
			
			//Variavel que ir� armazenar o calculo dos filhos.
			double calculo = 0;
			
			//Verifica qual ser� o tipo de opera��o aritmetica a ser realizada.
			switch(operador) {				
				case "+": //Soma.
					calculo = x + y; //Armazena o valor da soma.
					break;
				case "-": //Subtra��o.
					calculo =  x - y; //Armazena o valor da subtra��o.
					break;
				case "*": //Multiplica��o.
					calculo =  x * y; //Armazena o valor da multiplica��o.
					break;
				case "/": // Divis�o.
					calculo = x / y; //Armazena o valor da divis�o.
					break;
			}
			
			return calculo; //Retorna o valor calculado dos filhos.
			
		//Caso seja o ultimo filho.		
		}else {
			
			return Double.valueOf(posNode.element().toString()); //Retorna o valor convertido para double.
		}		
	}
	
	//Exercicio 5 - Quest�o E): Ir� selecionar os elementos da �rvore binaria e concatena-los em uma String utilizando o inorder.
	public String inorder(BinaryTree<TIPO> ARVORE, Position<TIPO> posNode) {
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expressao = "";
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(ARVORE.hasLeft(posNode)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TIPO> filhoPosNode = ARVORE.left(posNode);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expressao += inorder(ARVORE, filhoPosNode);
		}
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		expressao += posNode.element().toString();
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(ARVORE.hasRight(posNode)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TIPO> filhoPosNode = ARVORE.right(posNode);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expressao += inorder(ARVORE, filhoPosNode);			
		}
		return expressao; //Retorna a variavel com os elementos da arvore.
	}	
	
	//Exercicio 5 - Quest�o F): Ir� selecionar os elementos da �rvore binaria e concatena-los em uma String utilizando o positionsInorder.
	public String toStringInOrder(LinkedBinaryTree<String> ARVORE) {
		
		//String que armazenar� os elementos dos nodos da �rvore.
		String elementos = "";
		
		//La�o de repeti��o que percorer� os nodos da �rvore em ordem.
		for(Position<String> filho: ARVORE.positionsInorder()) {
			
			//Concatena os nodos da �rvore seguida de uma ",".
			elementos += ", " + filho.element().toString();
		}
		
		//Remove a virgula e o espaco do ultimo elemento da string.
		elementos = (elementos.length() == 0 ? elementos : elementos.substring(2));
		
		//Retorna a string de elementos.
		return "[" + elementos + "]";
	}
	
	//Exercicio 5 - Quest�o G): Ir� imprimir no console a �rvore binaria, de acordo com seus nodos.
	public void pintTreeBinary(LinkedBinaryTree<TIPO> ARVORE) {
		
		//Instancia uma lista de nodos, para armazenar os nodos.
		PositionList<Position<TIPO>> positions = new NodePositionList<Position<TIPO>>();
		
		//Usando o m�todo inorderPositions preenche a lista com os nodos em ordem (inOrden).
		if (size != 0) inorderPositions(root(), positions);
		
		//Cria uma matriz de acordo com o tamanho da �rvore, Linhas (altura da �rvoe) Colunas(tamanho da lista de nodos).
		String plano[][] = new String[height(ARVORE, ARVORE.root()) + 1][positions.size()];		
        
		//Percorre a matriz preenchendo ela com espa�os vazios.
        for(int i = 0; i < plano.length; i++)
            for(int j = 0; j < plano[0].length; j++){plano[i][j] = " ";}
        
        //Variavel que guarda a quantidade de nodos que foram visitados.
        int visitas = 0;
        
        //Esse la�o percorrer� a lista de nodos.
        for(Position<TIPO> filho: positions) {
        	
        	//Para cada nodo, preenche-se a matriz, sendo a profundidade do nodo a linha e quantidade de visitas a coluna que ser� guardada o elemento do nodo.
        	plano[depth(ARVORE, filho)][visitas] = filho.element().toString();
        	
        	//Acrescenta +1 na variavel visitas.
        	visitas++;
        }
        
      //Percorre a matriz imprimindo a na tela do console.
        for(int i = 0; i < plano.length; i++){
            for(int j = 0; j < plano[0].length; j++){
                
                System.out.print(plano[i][j] + " "); //Imprimi o elemento da matriz seguido de um espa�o em branco.
                
            }            
            System.out.println(); //A cada final de linha simula uma quebra de linha.             
        } 				
	}
	
	//M�todo depth - Ir� calcular a profundidade de um nodo da �rvore.
	public int depth(LinkedBinaryTree<TIPO> ARVORE, Position<TIPO> posInicial) {
		
		if(ARVORE.isRoot(posInicial)) {return 0;} //Verifica se posi��o informada � a raiz da arvore, caso seja retorna 0.
		else {return 1 + depth(ARVORE, ARVORE.parent(posInicial));} //Caso contrario, chama recursivamente at� encontrar a raiz, sempre somando +1 e retorna essa soma.		
	}
	
	//M�todo height - Ir� calcular a altura de uma �rvore.
	public int height(LinkedBinaryTree<TIPO> ARVORE, Position<TIPO> posInicial) {
		
		if(ARVORE.isExternal(posInicial)) {return 0;} //Verifica se o nodo da �rvore � interno, caso seja retorna 0.
		else {
			
			int height = 0; //Armazena a soma recursiva da altura dos nodos.
			
			//Percorre a �rvore e utiliza o Math.max para calcular a altura junto com uma chamada recursiva.
			for(Position<TIPO> filho : ARVORE.children(posInicial)) {height = Math.max(height, height(ARVORE, filho));}
			
			return 1 + height; //Retorna a altura da �rvore.
		}
	}
	
	//Exercicio 5 - Quest�o H): Ir� selecionar os elementos da �rvore binaria e concatena-los em uma String utilizando o eulerTour.	
	public String eulerTour(BinaryTree<TIPO> ARVORE, Position<TIPO> posNode) {		
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expressao = "";		
		expressao += posNode.element().toString();
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(ARVORE.hasLeft(posNode)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TIPO> filhoPosNode = ARVORE.left(posNode);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expressao += eulerTour(ARVORE, filhoPosNode);
		}
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		expressao += posNode.element().toString();

		//Verifica se o nodo da �rvore tem um filho a direita.
		if(ARVORE.hasRight(posNode)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TIPO> filhoPosNode = ARVORE.right(posNode);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expressao += eulerTour(ARVORE, filhoPosNode);			
		}
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		expressao += posNode.element().toString();
		return expressao; //Retorna a variavel com os elementos da arvore.
	}
	
	//Exercicio 5 - Quest�o I): Ir� imprimir a express�o representada pela �rvore.
	public String printExpression(BinaryTree<TIPO> ARVORE, Position<TIPO> posNode) {
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expressao = "";	
		
		//Verifica se o nodo � interno, caso seja concatena a string com "(" (parenteses aberto).
		if(ARVORE.isInternal(posNode)) {expressao += "(";}
		
		//Verifica se o nodo � filho da esquerda, caso seja concatena o elemento recursivamente com a string.
		if(ARVORE.hasLeft(posNode)) {expressao += printExpression(ARVORE, ARVORE.left(posNode));}
		
		//Verifica se o nodo � interno, caso seja concatena o elemento recursivamente com a string.
		if(ARVORE.isInternal(posNode)) {expressao += posNode.element().toString();}
		
		//caso contrario, concatena apenas o elemento do nodo com a string.
		else {expressao += posNode.element().toString();}
		
		//Verifica se o nodo � filho da direita, caso seja concatena o elemento recursivamente com a string.
		if(ARVORE.hasRight(posNode)) {expressao += printExpression(ARVORE, ARVORE.right(posNode));}
		
		//Verifica se o nodo � interno, caso seja concatena a string com ")" (parenteses fechado).
		if(ARVORE.isInternal(posNode)) {expressao += ")";}
		
		//Retorna a variavel com a espress�o que representa a �rvore.
		return expressao;		
	}
	
	//Exercicio 5 - Quest�o J): Ir� contar a quantidade de nodos externos e do lado esquerdo de uma �rvore binaria.
	public int countLeft(BinaryTree<TIPO> ARVORE, Position<TIPO> posNode) {
		
		//Variavel armazenar� a quantidade de nodos esquerdos e externos da arvore.
		int quantNodos = 0;		
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(ARVORE.hasLeft(posNode)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TIPO> filhoPosNode = ARVORE.left(posNode);
			
			//Chama novamente o m�todo recursivamente e soma o valor recebido.
			quantNodos += countLeft(ARVORE, filhoPosNode);
		}
		
		//Verifica se o nodo atual � a raiz da �rvore.
		if(posNode == ARVORE.root()) {
			
			//Caso seja, retorna a quantidade de nodos da �rvore.
			return quantNodos;
		
		//Caso contrario.
		}else {
			
			//Verifica se o nodo atual � externo.
			if(ARVORE.isExternal(posNode)) {
				
				//Caso seja soma + 1;
				quantNodos += 1;
			}
		}
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(ARVORE.hasRight(posNode)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TIPO> filhoPosNode = ARVORE.right(posNode);
			
			//Chama novamente o m�todo recursivamente e soma o valor recebido.
			quantNodos += countLeft(ARVORE, filhoPosNode);			
		}
		
		//Retorna a quantidade de nodos da �rvore.
		return quantNodos;
	}
	
	//Exercicio 5 - Quest�o K): Ir� contar a quantidade de nodos externos e do lado direito de uma �rvore binaria.
	public int countRight(BinaryTree<TIPO> ARVORE, Position<TIPO> posNode) {		
		
		//Variavel armazenar� a quantidade de nodos esquerdos e externos da arvore.
		int quantNodos = 0;			
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(ARVORE.hasRight(posNode)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TIPO> filhoPosNode = ARVORE.right(posNode);
			
			//Chama novamente o m�todo recursivamente e soma o valor recebido.
			quantNodos += countLeft(ARVORE, filhoPosNode);			
		}		
		
		//Verifica se o nodo atual � a raiz da �rvore.
		if(posNode == ARVORE.root()) {
			
			//Caso seja, retorna a quantidade de nodos da �rvore.
			return quantNodos;
		
		//Caso contrario.
		}else {
			
			//Verifica se o nodo atual � externo.
			if(ARVORE.isExternal(posNode)) {
				
				//Caso seja soma + 1;
				quantNodos += 1;
			}
		}		
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(ARVORE.hasLeft(posNode)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TIPO> filhoPosNode = ARVORE.left(posNode);
			
			//Chama novamente o m�todo recursivamente e soma o valor recebido.
			quantNodos += countLeft(ARVORE, filhoPosNode);
		}
		
		//Retorna a quantidade de nodos da �rvore.
		return quantNodos;
	}
	
	public String toString() {return toString(this);}
	
	public static <TIPO> String toString(LinkedBinaryTree<TIPO> ARVORE) {

		String s = "";

		for (TIPO i : ARVORE) { s += ", " + i; }

		s = (s.length() == 0 ? s : s.substring(2));

		return "[" + s + "]";
	}	
}
