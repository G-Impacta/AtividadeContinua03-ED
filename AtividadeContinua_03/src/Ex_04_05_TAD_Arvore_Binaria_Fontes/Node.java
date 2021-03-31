package Ex_04_05_TAD_Arvore_Binaria_Fontes;

public class Node<TIPO> {
	
	private TIPO elemento;
	private Node<TIPO> next;
	
	public Node() {
		
		this(null, null);
	}
	
	public Node(TIPO elemento, Node<TIPO> next) {
		
		this.elemento = elemento;
		this.next = next;
	}
	
	public TIPO getElement() {return elemento;}
	
	public Node<TIPO> getNext() {return next;}
	
	public void setElement(TIPO novoElemento) {elemento = novoElemento;}
	
	public void setNext(Node<TIPO> novoElemento) {next = novoElemento;}

}
