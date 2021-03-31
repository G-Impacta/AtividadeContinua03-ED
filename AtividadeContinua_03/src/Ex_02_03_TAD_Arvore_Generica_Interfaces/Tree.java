package Ex_02_03_TAD_Arvore_Generica_Interfaces;

import java.util.Iterator;

import Ex_02_03_TAD_Arvore_Generica_Excecoes.ExcecaoArvoreVazia;
import Ex_02_03_TAD_Arvore_Generica_Excecoes.ExcecaoIndiceForaLimites;
import Ex_02_03_TAD_Arvore_Generica_Excecoes.ExcecaoPosicaoInvalida;

public interface Tree<TIPO> extends Iterable<TIPO>{
	
	public int size();
	
	public boolean isEmpty();
	
	public Iterator<TIPO> iterator();
	
	public Iterable<Position<TIPO>> positions();
	
	public TIPO replace(Position<TIPO> posNode, TIPO elemento) throws ExcecaoPosicaoInvalida;
	
	public TreePosition<TIPO> root() throws ExcecaoArvoreVazia;
	
	public TreePosition<TIPO> parent(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites;
	
	public Iterable<Position<TIPO>> children(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;

	public boolean isInternal(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;

	public boolean isExternal(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;

	public boolean isRoot(Position<TIPO> posNode) throws ExcecaoPosicaoInvalida;
}
