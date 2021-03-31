package Ex_04_05_TAD_Arvore_Binaria_Fontes;

import Ex_04_05_TAD_Arvore_Binaria_Excecoes.PilhaExcecao_PilhaVazia;
import Ex_04_05_TAD_Arvore_Binaria_Interfaces.PilhaInterface;

public class Pilha<TIPO> implements PilhaInterface<TIPO> {
	
	protected Node<TIPO> top;
	protected int size;
	
	public Pilha() {
		
		top = null;
		size = 0;
	}

	public int size() {return size;}

	public boolean isEmpty() {return top == null;}

	public TIPO top() throws PilhaExcecao_PilhaVazia {
		
		if(isEmpty()) {
			throw new PilhaExcecao_PilhaVazia("A pilha está vazia!");
		}
		
		return top.getElement();
	}

	public TIPO pop() throws PilhaExcecao_PilhaVazia {		
		
		if(isEmpty()) {
			throw new PilhaExcecao_PilhaVazia("A pilha está vazia!");
		}
		
		Node<TIPO> referenciaTopoTemporario = top;
		TIPO elementoTopoTemporario = top.getElement();
		
		top = top.getNext();
		
		referenciaTopoTemporario.setNext(null);
		size--;
		
		return elementoTopoTemporario;
	}

	public void push(TIPO elemento) {
		
		Node<TIPO> novoNode = new Node<TIPO> (elemento, top);
		top = novoNode;
		size++;		
	}
	
	public String toString() {
		
		Node<TIPO> nodeTop = top;
		
		String pilhaElementos = "[";
		
		while(nodeTop != null) {
			
			pilhaElementos += nodeTop.getElement();
			nodeTop = nodeTop.getNext();
			
			if(nodeTop != null) {
				pilhaElementos += ", ";
			}
		}
		
		pilhaElementos += "]";
		return pilhaElementos;		
	}
}
