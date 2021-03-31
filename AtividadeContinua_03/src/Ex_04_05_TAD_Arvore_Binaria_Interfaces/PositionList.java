package Ex_04_05_TAD_Arvore_Binaria_Interfaces;

import java.util.Iterator;

import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoIndiceForaLimites;
import Ex_04_05_TAD_Arvore_Binaria_Excecoes.ExcecaoPosicaoInvalida;

public interface PositionList<TIPO> extends Iterable<TIPO>{

	public int size();

	public boolean isEmpty();

	public Position<TIPO> first();

	public Position<TIPO> last();

	public Position<TIPO> next(Position<TIPO> posicao) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites;

	public Position<TIPO> prev(Position<TIPO> posicao) throws ExcecaoPosicaoInvalida, ExcecaoIndiceForaLimites;

	public void addFirst(TIPO elemento);

	public void addLast(TIPO elemento);

	public void addAfter(Position<TIPO> posicao, TIPO elemento) throws ExcecaoPosicaoInvalida;

	public void addBefore(Position<TIPO> posicao, TIPO elemento) throws ExcecaoPosicaoInvalida;

	public TIPO remove(Position<TIPO> posicao) throws ExcecaoPosicaoInvalida;

	public TIPO set(Position<TIPO> posicao, TIPO elemento) throws ExcecaoPosicaoInvalida;

	public Iterator<TIPO> iterator();
}
