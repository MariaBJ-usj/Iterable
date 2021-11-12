package fr.dauphine.javaavance.td6;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Panel{
	
	//1 et 2/avec usage de classe anonyme
	public static Iterator<Integer> panel1(int debut, int fin) {
		return new Iterator<Integer>() {
			int i=debut;
			
			@Override
			public boolean hasNext() {
				return i<=fin;
			}

			@Override
			public Integer next() {
				if(!hasNext()) {
					throw new NoSuchElementException("Cet element n'existe pas");
				}else {
					return i++;
				}
			}
		};
	}
	
	//3/on redefinie la methode iterator() de l'interface Iterator
	public static Iterable<Integer> panel2(int debut, int fin) {
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return panel1(debut, fin);
			}
		};
	}
	
	//4/On utilise AbstractList class pour implementer une liste non modifiable afin d'utiliser les methodes size() et get()
	//afin de retourner le nombre d'elements dans la liste abstraite et un element d'un index specifique.
	
	//5/
	public static List<Integer> panel(int debut, int fin) {
		if(debut>fin) throw new IllegalArgumentException();
		return new AbstractList<Integer>() {
			@Override
			public int size() {
				return fin-debut+1;
			}

			@Override
			public Integer get(int index) {
				return debut+index;
			}
			
			@Override
			public Iterator<Integer> iterator() {
				return panel1(debut, fin);
			}
		};
	}
	
	
	
	public static void main(String[] args) {
		//1/
		Iterator<Integer> it=panel1(1,5);
		for(;it.hasNext();)
		   System.out.println(it.next()); // affiche 1 2 3 4 5
		
		//3/
		for(int i:panel2(1,5))
			System.out.println(i); // affiche 1 2 3 4 5
		
		//5/
		List<Integer> l = panel(3,6);
		for(int i:l) {
			System.out.println(i);
			//affiche 3 4 5 6
		}
		System.out.println(l.get(1));	//affiche 4
	}

}
