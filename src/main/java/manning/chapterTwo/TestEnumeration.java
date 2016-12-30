package manning.chapterTwo;

import org.junit.Test;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Set;



public class TestEnumeration{
	
	
	@Test
	public void a() {
		
		AggregateIterator<Integer> iterator = new AggregateIterator<Integer>();
		
		
		Integer[] array = {1,2,2,2,5,6}; 
		Enumeration<Integer> a = new MyEnumerator(array);
		
		//放进去
		iterator.addEnumeration(a);
		
		Integer[] array2 = {12,22,22,22,52,62}; 
		Enumeration<Integer> b = new MyEnumerator(array2);
		iterator.addEnumeration(b);
		
		//Iterator的接口
		 while (iterator.hasNext()) {
			  Integer str = iterator.next();
			  System.out.println(str);
			 }
		 
		 Set<Integer> d = iterator.loaded;
		
	}
	
	
	
	/**
	 * Aggregates Enumeration instances into one iterator and filters out duplicates.  Always keeps one
	 * ahead of the enumerator to protect against returning duplicates.
	 */
	protected static class AggregateIterator<E> implements Iterator<E> {

	    LinkedList<Enumeration<E>> enums = new LinkedList<Enumeration<E>>();
	    Enumeration<E> cur = null;
	    E next = null;
	    Set<E> loaded = new HashSet<E>();

	    public AggregateIterator addEnumeration(Enumeration<E> e) {
	        if (e.hasMoreElements()) {
	            if (cur == null) {
	                cur = e;
	                next = e.nextElement();
	                loaded.add(next);
	            } else {
	                enums.add(e);
	            }
	        }
	        return this;
	    }

	    public boolean hasNext() {
	        return (next != null);
	    }

	    public E next() {
	        if (next != null) {
	            E prev = next;
	            next = loadNext();
	            return prev;
	        } else {
	            throw new NoSuchElementException();
	        }
	    }

	    private Enumeration<E> determineCurrentEnumeration() {
	        if (cur != null && !cur.hasMoreElements()) {
	            if (enums.size() > 0) {
	                cur = enums.removeLast();
	            } else {
	                cur = null;
	            }
	        }
	        return cur;
	    }

	    private E loadNext() {
	        if (determineCurrentEnumeration() != null) {
	            E tmp = cur.nextElement();
	            while (loaded.contains(tmp)) {
	                tmp = loadNext();
	                if (tmp == null) {
	                    break;
	                }
	            }
	            if (tmp != null) {
	                loaded.add(tmp);
	            }
	            return tmp;
	        }
	        return null;

	    }

	    public void remove() {
	        throw new UnsupportedOperationException();
	    }
	}
	
	
	class MyEnumerator implements Enumeration<Integer>{ 
	      int count = 0; // 计数器
	      int length; //存储的数组的长度
	      Integer[] dataArray; // 存储数据数组的引用
	      
	      //构造器
	      MyEnumerator(Integer[] dataArray){ 
	            this.length=dataArray.length;
	            this.dataArray=dataArray;
	      } 
	      public boolean hasMoreElements() { 
	            return (count< length);
	      }
	      public Integer nextElement() {
	            return dataArray[count++];
	      }
	} 
}


