package dp.leetcode.editDistance;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Solution {
	private int count = 0; //number of steps
	public int minDistance(String word1, String word2) {
		if( word1.length() == 0 ){
			return word2.length();
		}
		if(	word2.length() == 0 ){
			return word1.length();
		}
		
		char[] chars1 = word1.toCharArray(), chars2 = word2.toCharArray();
		MyList w1 = toLinkedList(chars1);
		MyList w2 = toLinkedList(chars2);
		
		return minDistance(w1, w2);
	}
	private MyList toLinkedList(char[] chars) {
		MyList list = new MyList();
		for( char ch : chars){
			list.add(String.valueOf(ch));
		}
		list.resetIterator();
		return list;
	}
	private int minDistance(MyList w1, MyList w2) {
		//w1 stable, w2 generally changed to be w1
		//first k chars equals, then compare next two chars
		try{
			if( w1.size() == 1 ){
				if( w2.size() == 0){					
					return 1;
				}if( w2.size() == 1){
					if( w1.a().equals( w2.a()))
						return 0;
					else
						return 1;
				}else{//w2 size > 1
					if( w1.a().equals( w2.a()) )
						return w2.size() - 1;
					else						
						return w2.size();
				}
			}
			
			if( w2.size() == 1 ){
				return minDistance(w2,w1);//symmetry the same
			}
			
			//following branches make first k chars in w2 equals to w1's
			if( !w1.a().equals(w2.a()) && w1.b().equals( w2.b() )){
				//like ' |' , update w2[a2]
				w2.updateA( w1.a());
				count++;
				w1.move2();//the 2 chars become the same, move 2 chars
				w2.move2();
			}else if( w1.a().equals( w2.a()) && w1.b().equals( w2.b()) ){
				//no step
				w1.move1();
				w2.move1();
			}else if( w1.a().equals( w2.b()) && !w1.a().equals( w2.a())){
				//'\', delete w2[a2]
				w2.deleteA();
				count++;
				//1 char becomes the same
				w1.move1();
				w2.move1();
			}else if( w1.a().equals( w2.b()) && w1.a().equals( w2.a()) && !w1.b().equals( w2.b()) ){
				//'|\', no step
				w1.move1();
				w2.move1();
			}else if( w1.b().equals( w2.a()) && !w1.a().equals( w2.a() )){
				//'/', b1 == a2, insert w1[a1] into w2 before a2
				w2.insertA(w1.a());
				count++;
				w1.move2();
				w2.move2();
			}else if( w1.a().equals( w2.a()) && w1.b().equals( w2.a()) && !w1.b().equals( w2.b())){
				//'|/', no step
				w1.move1();
				w2.move1();
			}else{
				//all different 
				w2.updateA( w1.a());
				count++;
				w1.move1();
				w2.move1();
			}
		}catch(WordEndException e){
			e.printStackTrace();
			//w2 length may not equal to w1's
			if( e.getWordType() == 1){
				count += w2.deleteRest();
			}else{
				count += w2.appendAll(w1.getRest());
			}
			return this.count;
		}
		//recursion for the rest
		return minDistance( w1,  w2);
    }
}
class MyList{
	private List<String> list;
	private int p;//pointer
	private ListIterator<String> it;
	private String a;
	private String b;
	private int wordType;//1,2
	public MyList(){
		list = new LinkedList<String>();
	}
	public String a() {
		return list.get(p);
	}
	public String b() {
		return list.get(p+1);
	}
	public int size(){
		return list.size();
	}
	public void resetIterator() {
		p = 0;
	}
	public int appendAll(List<String> l) {
		int steps = 0;
		for( String s : l ){
			list.add(s);
			steps++;
		}
		return steps;
	}
	public List<String> getRest() {
		return list.subList(p+1, list.size());
	}
	public int deleteRest() {
		int nextIdx = it.nextIndex();
		int steps = 0;
		while( nextIdx != list.size()){
			list.remove(nextIdx);
			nextIdx = it.nextIndex();
			steps++;
		}
		return steps;
	}
	public void updateA(String c) {
		list.set(p, c);
	}
	public void insertA(String c) {
		list.add(p, c);
	}
	public void deleteA() {
		list.remove(p);
	}
	public void add(String s){
		list.add(s);
	}
	public void move1() throws WordEndException{//now iterator point b
		if( p + 1 == list.size()){
			throw new WordEndException(this.wordType,1);
		}else{
			p++;
		}
		checkIfSingleCompare();
	}
	public void move2() throws WordEndException{//now iterator point b
		if( p + 2 == list.size()){
			throw new WordEndException(this.wordType,1);
		}else{
			p += 2;
		}
		checkIfSingleCompare();
	}
	private void checkIfSingleCompare() throws WordEndException{
		if( p + 1 == list.size()){
			throw new WordEndException(this.wordType,2);
		}
	}
}

class WordEndException extends Exception{
	private static final long serialVersionUID = 1L;
	private int wordType;
	private int errorType;//1 when move, 2 for only compare a (not a and b)
	public WordEndException(int wordType,int errorType){
		this.wordType = wordType;
		this.errorType = errorType;
	}
	public int getWordType() {
		return wordType;
	}
	public void setWordType(int wordType) {
		this.wordType = wordType;
	}
	public int getErrorType() {
		return errorType;
	}
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	
}
