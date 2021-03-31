package Ex_04_05_TAD_Arvore_Binaria_Interfaces;

import java.util.Iterator;

import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoArvoreVazia;
import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoIndiceForaLimites;
import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoPosicaoInvalida;

public interface BTree<TIPO> extends Iterable<TIPO> {
	
	public int size();
	
	public boolean isEmpty();
	
	public Iterator<TIPO> iterator();
	
	public Iterable<Position<TIPO>> positions();
	
	public TIPO replace(Position<TIPO> posNode, TIPO elemento) throws ExcecaoPosicaoInvalida;
	
	public Position<TIPO> root() throws ExcecaoArvoreVazia;
	
	public Position<TIPO> parent(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites;
	
	public Iterable<Position<TIPO>> children(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;
	
	public boolean isInternal(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;
	
	public boolean isExternal(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;
	
	public boolean isRoot(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;
}
