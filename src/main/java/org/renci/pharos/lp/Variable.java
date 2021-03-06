package org.renci.pharos.lp;

abstract class Variable {
	static final int LargeNumber=1000;
	private static String name="x";
	private int dimension=0;
	private int sub[];
	public Variable(){
		
	}
	public Variable(int...s){
		setDimension(s.length);
		for(int i=0;i<dimension;i++){
			sub[i]=s[i];
		}
	}
	
	public int getDimension(){
		return dimension;
	}
	static String getName() {
		return name;
	}
	public int[] getSub() {
		return sub;
	}
	public void setDimension(int d){
		dimension=d;
		setSub(new int[dimension]);
	}
	public static void setName(String name) {
		Variable.name = name;
	}
	public void setSub(int sub[]) {
		this.sub = sub;
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append(name);
		for(int i: sub){
			sb.append("_"+String.valueOf(i));
		}
		return sb.toString();
	}
	static String makeVariable(String name, String s1,String s2){
		StringBuilder sb=new StringBuilder();
		sb.append(name).append("_").append(s1).append("_").append(s2);
		return sb.toString();
	}
	static String makeVariable(String name, String s){
		StringBuilder sb=new StringBuilder();
		sb.append(name).append("_").append(s);
		return sb.toString();
	}
}
