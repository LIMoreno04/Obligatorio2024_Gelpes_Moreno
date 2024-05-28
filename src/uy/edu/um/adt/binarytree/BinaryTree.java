/**
 * 
 */
package uy.edu.um.adt.binarytree;

import uy.edu.um.adt.Exceptions.AlreadyExistingValue;
import uy.edu.um.adt.Exceptions.EmptyTree;

import java.util.List;

/**
 * @author pegardan
 *
 */
public interface BinaryTree<T extends Comparable<T>> {

	void add(T oElement) throws EmptyTree, AlreadyExistingValue;

	void remove(T oElement);

	boolean contains(T oElement) throws EmptyTree;

	T find(T oElement) throws EmptyTree;
	
	List<T> preOrder();

	List<T> posOrder();

	List<T> inOrder();

}
