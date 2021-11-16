package br.ufrn.imd.view;

import br.ufrn.imd.controller.Conection;

public class Teste {

	public static void main(String[] args) {
		Conection con = new Conection();
		
		String sql = "INSERT INTO public.book (id, description, price, author, name, tags) "
				+ "VALUES (6, NULL, 31, 'Tsouanas Thanos', 'FMCBOOK', '{1,12,8}');";
		
		int res = con.execSQL(sql);
		
		if(res > 0) {
			System.out.println("Cadastrado com Sucesso!!!");
		} else {
			System.out.println("Erro");
		}
				
	}

}
