package Ex_02_03_TAD_Arvore_Generica_Fontes;

import java.util.Iterator;

import Ex_02_03_TAD_Arvore_Generica_Excecoes.ExcecaoArvoreNaoVazia;
import Ex_02_03_TAD_Arvore_Generica_Excecoes.ExcecaoArvoreVazia;
import Ex_02_03_TAD_Arvore_Generica_Excecoes.ExcecaoIndiceForaLimites;
import Ex_02_03_TAD_Arvore_Generica_Excecoes.ExcecaoPosicaoInvalida;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.Position;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.PositionList;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.Tree;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.TreePosition;

public class LinkedTree<TIPO> implements Tree<TIPO> {
	
	protected TreePosition<TIPO> root;
	protected int size;
	
	public LinkedTree() {
		
		root = null;
		size = 0;
	}
	
	public int size() {return size;}
	
	public boolean isEmpty() {return (size == 0);}
	
	public boolean isInternal(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {return !isExternal(posNode);}
	
	public boolean isExternal(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		TreePosition<TIPO> posNodeValidado = checkPosition(posNode);
		return (posNodeValidado.getChildren() == null) || posNodeValidado.getChildren().isEmpty();
	}
	
	public boolean isRoot(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		checkPosition(posNode);
		return (posNode == root());
	}
	
	public TreePosition<TIPO> root() throws ExcecaoArvoreVazia {

		if (root == null) throw new ExcecaoArvoreVazia("A �rvore est� vazia!");
		return root;
	}
	
	public TreePosition<TIPO> parent(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites {

		TreePosition<TIPO> posNodeValidado = checkPosition(posNode);
		TreePosition<TIPO> posParentNode = posNodeValidado.getParent();
		
		if (posParentNode == null) throw new ExcecaoIndiceForaLimites("Este nodo n�o tem pai!");
		return posParentNode;
	}
	
	public Iterable<Position<TIPO>> children(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		TreePosition<TIPO> posNodeValidado = checkPosition(posNode);
		return posNodeValidado.getChildren();
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
	
	public TIPO replace(Position<TIPO> posNode, TIPO element) throws ExcecaoPosicaoInvalida {

		TreePosition<TIPO> posNodeValidado = checkPosition(posNode);
		TIPO nodeTemp = posNode.element();
		posNodeValidado.setElement(element);
		return nodeTemp;
	}
	
	public TreePosition<TIPO> addRoot(TIPO element) throws ExcecaoArvoreNaoVazia {

		if (!isEmpty()) throw new ExcecaoArvoreNaoVazia("A �rvore j� tem uma raiz!");
		size = 1;
		root = createNode(element, null, null);
		return root;
	}
	
	public void swapElements(Position<TIPO> posNodeP, Position<TIPO> posNodeS) throws ExcecaoPosicaoInvalida {

		TreePosition<TIPO> posNodePValidado = checkPosition(posNodeP);
		TreePosition<TIPO> posNodeSValidado = checkPosition(posNodeS);

		TIPO elementTemp = posNodeS.element();

		posNodeSValidado.setElement(posNodeP.element());
		posNodePValidado.setElement(elementTemp);
	}
	
	protected TreePosition<TIPO> checkPosition(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida {

		if (posNode == null || !(posNode instanceof TreePosition)) throw new ExcecaoPosicaoInvalida("Est� posi��o � invalida!");
		return (TreePosition<TIPO>) posNode;
	}
	
	protected TreePosition<TIPO> createNode(TIPO element, TreePosition<TIPO> parent, PositionList<Position<TIPO>> children) {

		return new TreeNode<TIPO>(element, parent, children);
	}
	
	protected void preorderPositions(Position<TIPO> posNode, PositionList<Position<TIPO>> posNodeList) throws ExcecaoPosicaoInvalida {

		posNodeList.addLast(posNode);
		for (Position<TIPO> indice : children(posNode)) preorderPositions(indice, posNodeList);
	}
	
	//Exercicio 3 - Implementa��o dos M�todos (parentheticRepresentation, toStringPostorder, diskSpace, depth, height1, height2).
	
	//Exercicio 3 - Quest�o A): M�todo parentheticRepresentation - Ir� imprimir a representa��o da �rvore Generica.
	public String parentheticRepresentation (Tree<TIPO> ARVORE, Position<TIPO> posInicial) {
		
		String impressaoArvore = posInicial.element().toString(); //Variavel que armazenar� a string da �rvore, inicia com a raiz da arvore.
		
		if (ARVORE.isInternal(posInicial)) { //Verifica se o nodo da �rvore � interno.
			
			Boolean firstSon = true; //Variavel flag, que ser� true quando for o primeiro filho de uma sub raiz da �rvore.
			int depth = 0; //Contar� a profundidade de cada nodo da �rvore, usada para identar os elementos da �rvore.
			
			for (Position<TIPO> filho : ARVORE.children(posInicial)) { //La�o de intera��o com cada filho da �rvore.
				
				depth = depth(ARVORE, posInicial); //Armazena a profundidade do filho atual da �rvore.
				
				if (firstSon) { //Verifica se o filho atual � o primeiro filho da sub raiz da �rvore.
					
					impressaoArvore += "(\n\t"; //Concatena a variavel de impress�o com a identa��o(Enter + Tabula��o).					
					for(int i = 0; i < depth; i++) {impressaoArvore += "\t";} //De acordo com a profundidade do nodo aplica a tabula��o.					
					impressaoArvore += parentheticRepresentation(ARVORE, filho); //Concatena com o elemento do nodo da �rvore.
					
					firstSon = false; //Altera a variavel flag para false.
					
				}else { //Caso n�o seja o primeiro filho.
					
					impressaoArvore += ",\n\t"; //Concatena a variavel de impress�o com a identa��o(Enter + Tabula��o).
					for(int i = 0; i < depth; i++) {impressaoArvore += "\t";} //De acordo com a profundidade do nodo aplica a tabula��o.
					impressaoArvore += parentheticRepresentation(ARVORE, filho);} //Concatena com o elemento do nodo da �rvore.		
			}
			
			impressaoArvore += "\n"; //Concatena a variavel de impress�o com a identa��o(Enter).
			for(int i = 0; i < depth; i++) {impressaoArvore += "\t";} //De acordo com a profundidade do nodo aplica a tabula��o.
			impressaoArvore += ")"; //Concatena com o fechamento do parenteses.
		}		
		return impressaoArvore; // Retorna a variavel que armazena a representa��o da �rvore.
	}
	
	//Exercicio 3 - Quest�o B): M�todo toStringPostorder - Ir� imprimir a representa��o da �rvore Generica com base no algoritmo posOrder.
	public String toStringPostorder(Tree<TIPO> ARVORE, Position<TIPO> posInicial) {
		
		String impressaoArvore = ""; //Variavel que armazenar� a string da �rvore, inicia vazia.
		
		//La�o de intera��o com cada filho da �rvore.			  //Concatena recursivamente com os elementos atuais dos nodos
		for (Position<TIPO> filho : ARVORE.children(posInicial)) {impressaoArvore += toStringPostorder(ARVORE, filho);}
		impressaoArvore += posInicial.element() + ",\n"; //Concatena com virgula e quebra de linha.		
		
		return impressaoArvore; // Retorna a variavel que armazena a representa��o da �rvore.
	}
	
	//Exercicio 3 - Quest�o C): M�todo diskSpace - Ir� calcular o tamanho do espa�o utilizado pelos arquivos da �rvore.	
	public int diskSpace(LinkedTree<String> ARVORE, TreePosition<String> posInicial) {
		
		//Separa o tamanho do arquivo do nome do arquivo.
		String kbyte = posInicial.element().toString().split(" ")[1];		
		String name = posInicial.element().toString().split(" ")[0];
		
		//Converte o tamanho do arquivo para inteiro.
		int tamanho = Integer.valueOf(kbyte);
		
		//La�o que percorerr� todos os nodos da arvore.
		for (Position<String> node : posInicial.getChildren()) {			
			
			//Soma recursiva sobre os tamanhos do arquivo encontrado na �rvore.
			tamanho += diskSpace(ARVORE, (TreePosition<String>) node);	
		}
		
		//Verifica se o nodo � interno.
		if (ARVORE.isInternal(posInicial)) {
			
			//Caso seja imprimi na tela do console, junto com o tamanho atual.
			System.out.println(name+ ": " + tamanho);
		}
		//Retorna o tamanho total caulculado.
		return tamanho;
	}
	
	//Exercicio 3 - Quest�o D): M�todo depth - Ir� calcular a profundidade de um nodo da �rvore.
	public int depth(Tree<TIPO> ARVORE, Position<TIPO> posInicial) {
		
		if(ARVORE.isRoot(posInicial)) {return 0;} //Verifica se posi��o informada � a raiz da arvore, caso seja retorna 0.
		else {return 1 + depth(ARVORE, ARVORE.parent(posInicial));} //Caso contrario, chama recursivamente at� encontrar a raiz, sempre somando +1 e retorna essa soma.		
	}
	
	//Exercicio 3 - Quest�o E): M�todo height1 - Ir� calcular a altura de uma �rvore (m�todo menos eficiente).
	public int height1(Tree<TIPO> ARVORE) {
		
		int height = 0; //Ir� armazenar a altura da �rvore.
		
		for(Position<TIPO> filho : ARVORE.positions()) { //La�o que percorre todas as posi��es da �rvore.
			
			//Verifica se a posi��o filho � um nodo externo, caso seja utiliza o Math.max para calcular a altura junto com uma chamada recursiva do depth.
			if(ARVORE.isExternal(filho)) {height = Math.max(height, depth(ARVORE, filho));}
		}
		
		return height; //Retorna a altura da �rvore.
	}
	
	//Exercicio 3 - Quest�o F): M�todo height2 - Ir� calcular a altura de uma �rvore (m�todo mais eficiente).
	public int height2(Tree<TIPO> ARVORE, Position<TIPO> posInicial) {
		
		if(ARVORE.isExternal(posInicial)) {return 0;} //Verifica se o nodo da �rvore � interno, caso seja retorna 0.
		else {
			int height = 0; //Armazena a soma recursiva da altura dos nodos.
			//Percorre a �rvore e utiliza o Math.max para calcular a altura junto com uma chamada recursiva.
			for(Position<TIPO> filho : ARVORE.children(posInicial)) {height = Math.max(height, height2(ARVORE, filho));}
			return 1 + height; //Retorna a altura da �rvore.
		}
	}	
	
	public String toString() {return toString(this);}
	
	public static <TIPO> String toString(LinkedTree<TIPO> ARVORE) {

		String s = "";

		for (TIPO i : ARVORE) { s += ", " + i; }

		s = (s.length() == 0 ? s : s.substring(2));

		return "[" + s + "]";
	}
}
