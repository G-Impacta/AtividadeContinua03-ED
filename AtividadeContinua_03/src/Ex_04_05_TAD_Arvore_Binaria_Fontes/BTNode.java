package Ex_04_05_TAD_Arvore_Binaria_Fontes;

import Ex_04_05_TAD_Arvore_Binaria_Interfaces.BTPosition;

public class BTNode<TIPO> implements BTPosition<TIPO>{
	
	private TIPO element;
	private BTPosition<TIPO> left, right, parent;
	
	public BTNode(TIPO element, BTPosition<TIPO> parent, BTPosition<TIPO> left, BTPosition<TIPO> right) {

		setElement(element);
		setParent(parent);
		setLeft(left);
		setRight(right);
	}
	
	public TIPO element() {return element;}
	
	public void setElement(TIPO elemento) {element = elemento;}
	
	public BTPosition<TIPO> getLeft() {return left;}
	
	public void setLeft(BTPosition<TIPO> posNode) {left = posNode;}
	
	public BTPosition<TIPO> getRight() {return right;}
	
	public void setRight(BTPosition<TIPO> posNode) {right = posNode;}
	
	public BTPosition<TIPO> getParent() {return parent;}
	
	public void setParent(BTPosition<TIPO> posNode) {parent = posNode;}
}
