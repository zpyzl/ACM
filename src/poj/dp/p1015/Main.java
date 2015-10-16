package poj.dp.p1015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader stdin = 
	            new BufferedReader(
	                new InputStreamReader(System.in));
		
		List<Round> rounds = new ArrayList<Round>();
		
		boolean ifHeader = true;
		String line = stdin.readLine();
		
		Round round = null;
	    while(!"0 0".equals(line)){
	    	String[] split = line.split(" ");
	    	//n m
	    	if( ifHeader ){
		    	int n = Integer.parseInt(split[0]);
		    	int m = Integer.parseInt(split[1]);
		    	
		    	round = new Round(m,n);
		    	rounds.add(round);
		    	
		    	ifHeader = false;
	    	}else{
		    	//read a pair
		    	Pair pair = new Pair(Integer.parseInt(split[0]),
		    						Integer.parseInt(split[1]),
		    						round.getPairsCount()+1);
		    	round.getPairs().add(pair);
		    	round.setPairsCount(round.getPairsCount()+1);
	    	
		    	if(round.getPairsCount()==round.getnFinal()){
		    		round.cal();
		    		
		    		ifHeader = true;//next next line is header
		    		line = stdin.readLine();
		    		if("0 0".equals(line)){
		    			break;
		    		}
		    	}
	    	}
	    	line = stdin.readLine();
	    }
	    stdin.close();
	    return;
	}
	
}
class Round{
	private static int roundCount = 0;
	private int roundNum = ++roundCount;
	
	private int m;
	private int n=m+1;
	private int nFinal;
	private int pairsCount=0;
	private List<Pair> pairs;
	private Chosen optChosen;
	
	Round(int m, int nFinal){
		this.m = m;
		this.nFinal = nFinal;
		pairs = new ArrayList<Pair>();
	}
	
	public int getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(int roundNum) {
		this.roundNum = roundNum;
	}

	public List<Pair> getPairs() {
		return pairs;
	}

	public void setPairs(List<Pair> pairs) {
		this.pairs = pairs;
	}
	
	public int getnFinal() {
		return nFinal;
	}

	public void setnFinal(int nFinal) {
		this.nFinal = nFinal;
	}

	public int getm() {
		return m;
	}

	public void setm(int m) {
		this.m = m;
	}

	public int getPairsCount() {
		return pairsCount;
	}
	public void setPairsCount(int pairsCount) {
		this.pairsCount = pairsCount;
	}
	public void cal( ){
		
		List<Chosen> smallChosen = null;//m-1 chosen
		List<Chosen> chosenList = null;
		chosenList = initChosenList(chosenList);
		
		while( n < nFinal){
			n++;

			//新的一对和chosen列表组成新的chosen列表
			getNewChosenList(chosenList, pairs.get(n-1));
			
		}
		printRes();
	}
	
	public void printRes(){
		System.out.println("Jury #"+this.roundNum+"\n"+this.optChosen);
		
	}
	
	private void getNewChosenList(List<Chosen> chosenList, Pair newPair,
			boolean getOpt){
		boolean optInited = false;
		for( Chosen chosen: chosenList){
			// chosen added by new pair
			chosen.inc(newPair);
			
			if( getOpt){
				optInited = findOpt( chosen, optInited );
			}
		}
	}
	
	private boolean findOpt(Chosen chosen,boolean init ){
		if( !init){
			optChosen = chosen;
			init = true;
		}else{
			//新的chosen列表中找出最优的
			if( chosen.getDiff() < optChosen.getDiff() || 
				( chosen.getDiff() == optChosen.getDiff() 
					&& chosen.getPlus() > optChosen.getPlus()  )){
				optChosen = chosen;
			}
		}
		return init;
	}
	
	private List<Chosen> initChosenList(List<Chosen> chosenList){
		chosenList = new ArrayList<Chosen>();
		for(int i = 0 ; i < n;i++){
			Pair pair = pairs.get(i);
			Chosen chosen = new Chosen();
			chosen.inc(pair);
		
			chosenList.add(chosen);
		}
		return chosenList;
	}
}
	
class Chosen{
	private List<Integer> chosenIdxs = new ArrayList<Integer>();
	
	private int dSum = 0;
	private int pSum = 0;
	private int diff ;
	
	public void inc(Pair newPair){
		this.dSum += newPair.getDv();
		this.pSum += newPair.getPv();
		this.diff = abs(dSum-pSum);
		chosenIdxs.add(newPair.getLineNum());
	}
	public int getDiff(){
		return this.diff;
	}
	public int getPlus(){
		return dSum+pSum;
	}
	
	public static int abs(int a){
		if( a >= 0 )
			return a;
		else
			return -a;
	}
	
	public String toString(){
		String res = 
				"Best jury has value "+this.pSum+
				" for prosecution and value "+this.dSum+
				" for defence:\n";
		for(Integer i: chosenIdxs){
			res += " " + Integer.toString(i);
		}
		res += "\n\n";
		return res;
	}
}

class Pair{
	private int lineNum;
	private int dv;
	private int pv;
	
	public Pair(int dv, int pv, int lineNum) {
		this.dv = dv;
		this.pv = pv;
		this.lineNum = lineNum;
	}
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	public int getDv() {
		return dv;
	}
	public void setDv(int dv) {
		this.dv = dv;
	}
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	
	
}
