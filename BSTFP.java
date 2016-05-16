//BSTFP.java
//Binary Search Tree to keep track of the movies ordered by release date

public class BSTFP implements java.io.Serializable{
	
	//instance variables
	private Mnode root;
		
	//Constructor
	public BSTFP() {
		root = null;
	}
	
	//Method to check if the tree is empty or not
	public boolean isEmptyTree() {
		return root == null;
	}
	
	//Method to insert new node (pointed to by p)
	public void insert(Mnode p) {
		if (root == null) {
			root = p;
		}
		else {
			insert2(root,p);
		}
	}
	
	//Helper method for insert()
	public void insert2(Mnode temp,Mnode p) {
		if (p.getReleaseD() < temp.getReleaseD()) {
			if (temp.getLeft() == null) {
				temp.setLeft(p);
			}
			else {
				insert2(temp.getLeft(),p);
			}
		}
		else {
			if (temp.getRight() == null) {
				temp.setRight(p);
			}
			else {
				insert2(temp.getRight(),p);
			}
		}
	}
	
	//Method to search through tree and return node (pointer to by tree) that matches key
	public Mnode search(int key) {
		if (root == null) {
			return null;
		}
		else {
			return search2(root,key);
		}
	}
	
	//Helper method for search()
	public Mnode search2(Mnode temp, int key) {
		if (key == temp.getReleaseD()) {
			return temp;
		}
		else if (key < temp.getReleaseD()) {
			return search2(temp.getLeft(),key);
		}
		else {
			return search2(temp.getRight(),key);
		}
	}
	
	//Method that prints out the nodes in the tree in order
	public void traverse() {
		if (root != null) {
			traverse2(root);
		}
	}
	
	//Helper method for traverse()
	public void traverse2(Mnode temp) {
		if (temp != null) {
			traverse2(temp.getLeft());
			System.out.print("Date: " + temp.getReleaseD() + ",  ");
			System.out.println("Title: " + temp.getName());
			traverse2(temp.getRight());
		}
	}
	
	//Method that prints out the tree
	public void printTree() {
		printTree2(root);
		System.out.println();
	}
	
	//Helper method for printTree
	public void printTree2(Mnode tree) {
		if (tree != null) {
			System.out.println(tree.getReleaseD() + " ");
			if (tree.getLeft() != null) {
				System.out.print("Left: " + tree.getLeft().getReleaseD() + " ");
			}
			else {
				System.out.print("Left: null ");
			}
			if (tree.getRight() != null) {
				System.out.println("Right: " + tree.getRight().getReleaseD() + " ");
			}
			else {
				System.out.println("Right: null");
			}
			printTree2(tree.getLeft());
			printTree2(tree.getRight());
		}
	}
	
	//Method that deletes a node (pointed to by p)
	//delete: findParent, findSuccessor, isLeft, deleteRoot, and delete2 help methods
	public void delete(Mnode p) {
		if (p == root) {
			deleteRoot(p);
		}
		else {
			//System.out.println("not root");
			Mnode parent = findParent(p,root);
			delete2(p,parent);
		}
	}
	
	//Helper method for delete that finds the parent of node p
	public Mnode findParent(Mnode p, Mnode temp) {
		while (temp.getLeft() != p && temp.getRight() != p) {
			if (p.getReleaseD() < temp.getReleaseD()) {
				temp = temp.getLeft();
			}
			else {
				temp = temp.getRight();
			}
		}
		return temp;
	}
	
	//Helper method for delete that finds the successor to replace node p
	public Mnode findSuccessor(Mnode temp) {
		temp = temp.getRight();
		while (temp.getLeft() != null) {
			temp = temp.getLeft();
		}
		return temp;
	}
	
	//Helper method for delete that checks if the parent points to p via its left pointer (true) or right pointer (false)
	public boolean isLeft(Mnode p) {
		Mnode parent = findParent(p,root);
		if (parent.getLeft() == p) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Helper method for delete that is called by delete() to remove the root (p)
	public void deleteRoot(Mnode p) {
	 	Mnode newRoot = findSuccessor(p);
	 	p.getRight().setLeft(newRoot.getRight());
	 	root = newRoot;
		root.setLeft(p.getLeft());
		root.setRight(p.getRight());
		p.setLeft(null);
		p.setRight(null);
	}
	
	//Helper method for delete that actually removes node p depending on if p is a leaf, single parent, or double parent
	public void delete2(Mnode p,Mnode parent) {
		//LEAF
		if (p.getLeft() == null && p.getRight() == null) {
			if (isLeft(p) == true) {
				parent.setLeft(null);
			}
			else {
				parent.setRight(null);
			}
		}
		//SINGLE PARENT
		else if (p.getLeft() == null || p.getRight() == null) {
			if (p.getLeft() == null) {
				if (isLeft(p) == true) {
					parent.setLeft(p.getRight());
					p.setLeft(null);
				}
				else {
					parent.setRight(p.getRight());
					p.setRight(null);
				}
			}
			else {
				if (isLeft(p) == true) {
					parent.setLeft(p.getLeft());
					p.setLeft(null);
				}
				else {
					parent.setRight(p.getLeft());
					p.setRight(null);
				}
			}
		}
		//DOUBLE PARENT
		else {
			Mnode successor = findSuccessor(p);
			if (successor.getReleaseD() < parent.getReleaseD()) {
				parent.setLeft(successor);
				successor.setLeft(p.getLeft());
				p.setLeft(null);
				p.setRight(null);
			}
			else {
				parent.setRight(successor);
				successor.setLeft(p.getLeft());
				p.setLeft(null);
				p.setRight(null);
			}
		}
	}
}



