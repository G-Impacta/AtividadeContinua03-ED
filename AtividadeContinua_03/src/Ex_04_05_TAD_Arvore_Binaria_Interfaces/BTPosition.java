package Ex_04_05_TAD_Arvore_Binaria_Interfaces;

public interface BTPosition<TIPO> extends Position<TIPO> {
	
	public TIPO element();
	
	public void setElement(TIPO elemento);
	
	public BTPosition<TIPO> getLeft();
	
	public void setLeft(BTPosition<TIPO> posNode);
	
	public BTPosition<TIPO> getRight();
	
	public void setRight(BTPosition<TIPO> posNode);
	
	public BTPosition<TIPO> getParent();
	
	public void setParent(BTPosition<TIPO> posNode);
}
