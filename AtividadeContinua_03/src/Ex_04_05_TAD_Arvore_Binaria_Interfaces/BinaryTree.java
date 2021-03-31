package Ex_04_05_TAD_Arvore_Binaria_Interfaces;

import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoIndiceForaLimites;
import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoPosicaoInvalida;

public interface BinaryTree<TIPO> extends BTree<TIPO> {
	
	public Position<TIPO> left(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites;
	
	public Position<TIPO> right(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites;
	
	public boolean hasLeft(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;
	
	public boolean hasRight(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;
}
