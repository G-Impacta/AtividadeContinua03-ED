package Ex_04_05_TAD_Arvore_Binaria_Fontes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Ex_04_05_TAD_Arvore_Binaria_Interfaces.Position;
import Ex_04_05_TAD_Arvore_Binaria_Interfaces.PositionList;

public class ElementIterator<TIPO> implements Iterator<TIPO> {	
	
	protected PositionList<TIPO> list;
	protected Position<TIPO> cursor;
	
	public ElementIterator(PositionList<TIPO> list) {
		
		this.list = list;
		cursor = (list.isEmpty()) ? null : list.first();
	}
	
	public boolean hasNext() {return(cursor != null);}
	
	public TIPO next() throws NoSuchElementException{
		
		if(cursor == null) {throw new NoSuchElementException("Não existe proximo elemento!");}
		
		TIPO toReturn = cursor.element();
		cursor = (cursor == list.last()) ? null : list.next(cursor);
		return toReturn;
	}
	
	public void remove() throws UnsupportedOperationException {

		throw new UnsupportedOperationException("remove");
	}
}
