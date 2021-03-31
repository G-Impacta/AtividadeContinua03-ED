package Ex_02_03_TAD_Arvore_Generica_Fontes;

import Ex_02_03_TAD_Arvore_Generica_Interfaces.Position;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.PositionList;
import Ex_02_03_TAD_Arvore_Generica_Interfaces.TreePosition;

public class TreeNode<TIPO> implements TreePosition<TIPO> {
	
	private TIPO element;
	private TreePosition<TIPO> parent;
	private PositionList<Position<TIPO>> children;

	public TreeNode() {}

	public TreeNode(TIPO element, TreePosition<TIPO> parent, PositionList<Position<TIPO>> children) {
	
		setElement(element);	
		setParent(parent);	
		setChildren(children);
	}

	public TIPO element() {return element;}

	public void setElement(TIPO element) {this.element = element;}

	public TIPO getElement() {return element;}

	public PositionList<Position<TIPO>> getChildren() {return children;}

	public void setChildren(PositionList<Position<TIPO>> children) {this.children = children;}

	public TreePosition<TIPO> getParent() {return parent;}

	public void setParent(TreePosition<TIPO> parent) {this.parent = parent;}
}
