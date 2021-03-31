package Ex_02_03_TAD_Arvore_Generica_Interfaces;

public interface TreePosition<TIPO> extends Position<TIPO>{

	public void setElement(TIPO elemento);

	public TIPO getElement();

	public PositionList<Position<TIPO>> getChildren();

	public void setChildren(PositionList<Position<TIPO>> posNodeFilho);

	public TreePosition<TIPO> getParent();

	public void setParent(TreePosition<TIPO> posNodePai);
}
