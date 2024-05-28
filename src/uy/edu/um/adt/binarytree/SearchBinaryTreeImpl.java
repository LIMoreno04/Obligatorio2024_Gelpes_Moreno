/**
 * 
 */
package uy.edu.um.adt.binarytree;

import uy.edu.um.adt.Exceptions.AlreadyExistingValue;
import uy.edu.um.adt.Exceptions.EmptyTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pegardan
 * @param <T>
 *
 */
public class SearchBinaryTreeImpl<T extends Comparable<T>> implements BinaryTree<T> {

	private TreeNode<T> root;

	public SearchBinaryTreeImpl(){
		this.root = null;
	}

	public void add(T oElement) throws EmptyTree, AlreadyExistingValue {
		TreeNode<T> oElementToAdd = new TreeNode<T>(oElement);

		if (root == null) {

			root = oElementToAdd;

		} else {
			if(contains(oElement)){
				throw new AlreadyExistingValue();
			}else{
				root.add(oElement);
			}
		}
	}

	public boolean contains(T oElement)throws EmptyTree {
		if(root == null){
			throw new EmptyTree();
		}else{
			return contains(oElement, root);
		}
	}

	private boolean contains(T oElementToSearch, TreeNode<T> oTree) {
		boolean bContains = false;
		
		if (oTree != null) {

			int nValue = oElementToSearch.compareTo(oTree.getValue());
					
			if (nValue == 0) {
				
				bContains = true;
			
			} else if (nValue > 0) {
				
				bContains = contains(oElementToSearch, oTree.getRight());
		
			} else {
				
				bContains = contains(oElementToSearch, oTree.getLeft());
			
			}

		}
		return bContains;
	}

	public void remove(T oElement) {

		if (root != null) {

			root.remove(oElement);

		}

	}
	
	public T find(T oElement) throws EmptyTree{
		if(root == null){
			throw new EmptyTree();
		}else {
			return find(oElement, root);
		}
	}
	
	private T find(T oElementToSearch, TreeNode<T> oTree) {
		T oSearchedElement = null;
		
		if (oTree != null) {

			int nValue = oElementToSearch.compareTo(oTree.getValue());
					
			if (nValue == 0) {
				
				oSearchedElement = oTree.getValue();
			
			} else if (nValue > 0) {
				
				oSearchedElement = find(oElementToSearch, oTree.getRight());
		
			} else {
				
				oSearchedElement = find(oElementToSearch, oTree.getLeft());
			
			}

		}

		return oSearchedElement;
	}		

	public List<T> preOrder() {
		List<T> colValues = new ArrayList<T>();

		if (root != null) {

			colValues.addAll(root.preOrderTraverse());

		}

		return colValues;
	}

	public List<T> posOrder() {
		List<T> colValues = new ArrayList<T>();

		if (root != null) {

			colValues.addAll(root.postOrderTraverse());

		}

		return colValues;
	}

	public List<T> inOrder() {
		List<T> colValues = new ArrayList<T>();

		if (root != null) {

			colValues.addAll(root.inOrderTraverse());

		}

		return colValues;
	}

}
