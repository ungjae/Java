package lab7;

import list.UnorderedList;
import list.OrderedList;
import sort.Sort;

import java.util.Iterator;

/**
 * <b>Title:</b> Lab 7:<br>
 * <b>Filename:</b> ListApp.java<br>
 * <b>Date Written:</b> November 25, 2018<br>
 * <b>Due Date:</b> November 25, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Driver to test the ordered and unordered list class 
 * that have been implemented using linked lists
 * </p>
 *
 * @author Ung Jae Yun
 */


public class ListApp {
	public static void main(String[] args){
		String[] str = {"hello","this","is","the","beginning"};
		UnorderedList<String> ulist = new UnorderedList<String>();
		OrderedList<String> olist = new OrderedList<String>();
		for(String s : str){
			ulist.add(s);
			System.out.println(ulist);
			olist.add(s);
			System.out.println(olist);			
		}
		try{
			System.out.println(ulist);
			System.out.println(ulist.removeFirst());
			System.out.println(ulist);
			System.out.println(olist);
			Iterator<String> it = ulist.iterator();
			while(it.hasNext())
				System.out.println("->" + it.next());
		}
		catch(Exception e){}
		System.out.println(ulist.search(str[4]));
		System.out.println(olist.search(str[4]));
		System.out.println(ulist.search("hello"));
		System.out.println(olist.search("hello"));
		System.out.println(ulist.search("Hello"));
		olist.remove("beginning");
		ulist.remove("beginning");
		System.out.println(olist);
		System.out.println(ulist);
		
		
		Integer[] x = {5, 4, 2, 6, 1, 7};
		Sort.insertionSort(x);
		for(int n : x)
			System.out.println(n);
		Sort.insertionSort(str);
		for(String s : str)
			System.out.println(s);
	}
}
