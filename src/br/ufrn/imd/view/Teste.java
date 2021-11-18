package br.ufrn.imd.view;

import java.util.HashSet;
import java.util.List;

import br.ufrn.imd.DAO.BookDAO;
import br.ufrn.imd.DAO.ClientDAO;
import br.ufrn.imd.DAO.TagDAO;
import br.ufrn.imd.controller.Queries;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Client;
import br.ufrn.imd.model.Tag;

public class Teste {

	public static void main(String[] args) {
//		Conection con = new Conection();
		
//		String sql = "INSERT INTO public.book (id, description, price, author, name, tags) "
//				+ "VALUES (6, NULL, 31, 'Tsouanas Thanos', 'FMCBOOK', '{1,12,8}');";
//		
//		int res = con.execSQL(sql);
//		
//		if(res > 0) {
//			System.out.println("Cadastrado com Sucesso!!!");
//		} else {
//			System.out.println("Erro");
//		}
//		
//		Book book = new Book();
//		book.setId(31);
//		book.setAuthor("TesteVideo");
//		book.setDescription("Um livro qualquer");
//		book.setName("TesteVideo teste");
//		HashSet<Integer> a =  new HashSet<Integer>();
//		a.add(1);
//		a.add(2);
//		book.setTags(a);
//		book.setPrice(3.5);
//		
//		new BookDAO().adiciona(book);
//		
//		
//		Tag tag = new Tag();
//		tag.setId(31);
//		tag.setName("TesteVideo");
//		
//		new TagDAO().adiciona(tag);
//		
//		Client client = new Client();
//		client.setCpf(316341654);
//		client.setName("TesteVideo");
//		
		new Queries().buscaBookNome("Foundation");
		
		new Queries().buscaBookDescricao("Um livro qualquer");
		
		new Queries().buscaBookId(3);
		
		new Queries().buscaClientCpf(316341654);
		
		new Queries().buscaClientNome("Eduardo");
		
		
		
		
	}

}
