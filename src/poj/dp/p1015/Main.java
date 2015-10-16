package poj.dp.p1015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		f(System.in);
	}
	public static void f(InputStream in) throws IOException{
		BufferedReader stdin = 
	            new BufferedReader(
	                new InputStreamReader(in));
		
		List<Round> rounds = new ArrayList<Round>();
		
		boolean ifHeader = true;
		String line = stdin.readLine();
		
		Round round = null;
	    while(true){
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
	}
	
}
class Round{
	private static int roundCount = 0;
	private int roundNum = ++roundCount;
	
	private int m;
	private int n;
	private int nFinal;
	private int pairsCount=0;
	private List<Pair> pairs;
	private Chosen optChosen;
	
	Round(int m, int nFinal){
		this.m = m;
		this.n = m+1;
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
		
		List<Chosen> fewerChosen = initFewerChosenList();
		initChosenList();
		
		while( n < nFinal){
			n++;

			//新的一对和fewerChosen列表组成新的chosen列表
			getNewChosenList(fewerChosen, pairs.get(n-1));
		}
		printRes();
	}
	
	public void printRes(){
		System.out.println("Jury #"+this.roundNum+"\n"+this.optChosen);
		
	}
	
	private void getNewChosenList(List<Chosen> fewerChosen, Pair newPair){
		for( Chosen chosen: fewerChosen){
			// chosen added by new pair
			chosen.addPair(newPair);
			
			findOpt( chosen );
		}
	}
	
	private void findOpt(Chosen chosen ){
		if( optChosen == null){
			optChosen = chosen;
		}else{
			//新的chosen列表中找出最优的
			if( chosen.getDiff() < optChosen.getDiff() || 
				( chosen.getDiff() == optChosen.getDiff() 
					&& chosen.getPlus() > optChosen.getPlus()  )){
				optChosen = chosen;
			}
		}
	}
	
	//choose m-1 from m+1
	private List<Chosen> initFewerChosenList( ){
		List<Chosen> chosenList = new ArrayList<Chosen>();
		
		for(int notChosenIdx1 = 1 ; notChosenIdx1 <= n;notChosenIdx1++){
			for(int notChosenIdx2 = 1 ; notChosenIdx2 <= n;notChosenIdx2++){
				//一个chose, 去除n选2的选择
				if( notChosenIdx1 != notChosenIdx2){
					Chosen chosen = new Chosen();
					for( int lineNume = 1 ; lineNume <= n;lineNume++){
						if( lineNume != notChosenIdx1 &&
							lineNume != notChosenIdx2)
							chosen.addLine(lineNume);
					}
					chosen.cal(pairs);
					chosenList.add(chosen);
				}
			}
		}
		return chosenList;
	}
	
	//choose m from m+1
	private List<Chosen> initChosenList(){
		List<Chosen> chosenList = new ArrayList<Chosen>();
		for(int notChosenIdx = 1 ; notChosenIdx <= n;notChosenIdx++){
			Chosen chosen = new Chosen();
			for( int lineNume = 1 ; lineNume <= n;lineNume++){
				if( lineNume != notChosenIdx ){
					chosen.addLine(lineNume);
				}
			}
			chosen.cal(pairs);
			chosenList.add(chosen);
			
			findOpt(chosen);
		}
		return chosenList;
	}
}
	
class Chosen{
	private List<Integer> chosenIdxs = new ArrayList<Integer>();
	
	private int dSum = 0;
	private int pSum = 0;
	private int diff ;
	private int plus ;
	
	public void addLine(int lineIdx){
		chosenIdxs.add(lineIdx);
	}
	public void cal(List<Pair> pairs){
		if( pairs.size() != 0){
			for( Integer lineNum :chosenIdxs){
				if( lineNum >= 1){
					Pair pair = pairs.get(lineNum-1);
					dSum += pair.getDv();
					pSum += pair.getPv();
				}
			}
			this.diff = abs(dSum-pSum);
			this.plus = dSum+pSum;
		}
	}
	public void addPair(Pair newPair){
		this.dSum += newPair.getDv();
		this.pSum += newPair.getPv();
		this.diff = abs(dSum-pSum);
		this.plus = dSum+pSum;
		chosenIdxs.add(newPair.getLineNum());
	}
	
	public List<Integer> getChosenIdxs() {
		return chosenIdxs;
	}

	public void setChosenIdxs(List<Integer> chosenIdxs) {
		this.chosenIdxs = chosenIdxs;
	}

	public int getDiff(){
		return this.diff;
	}
	public int getPlus(){
		return plus;
	}
	
	public static int abs(int a){
		if( a >= 0 )
			return a;
		else
			return -a;
	}
	
	public String toString(){
		String res = 
				"Best jury has value "+this.dSum+
				" for prosecution and value "+this.pSum+
				" for defence:\n";
		for(Integer i: chosenIdxs){
			res += " " + Integer.toString(i);
		}
		res += "\n";
		return res;
	}
}

class Pair{
	private int lineNum;//from 1
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
