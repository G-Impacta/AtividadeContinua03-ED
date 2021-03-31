package Ex_04_05_TAD_Arvore_Binaria_Interfaces;

import Ex_04_05_TAD_Arvore_Binaria_Excecoes.PilhaExcecao_PilhaVazia;

public interface PilhaInterface<TIPO> {
	
	public int size();
	
	public boolean isEmpty();
	
	public TIPO top() throws PilhaExcecao_PilhaVazia;
	
	public TIPO pop() throws PilhaExcecao_PilhaVazia;
	
	public void push(TIPO elemento);
}
