package br.com.prj1.cj.dao;

public class TesteString {

	public static void main(String[] args) {
		String disponibilidade = "ManhãTardeNoite";
		
		System.out.println(disponibilidade.substring(0, 5).concat("/") + disponibilidade.substring(5, 10).concat("/"));
		System.out.println(disponibilidade.substring(5, 10).concat("/"));
		System.out.println(disponibilidade.substring(10, 15).concat("/"));

	}

}
