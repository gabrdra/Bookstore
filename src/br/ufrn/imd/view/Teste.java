package br.ufrn.imd.view;


import br.ufrn.imd.controller.data.TransactionDAOJDBC;
import br.ufrn.imd.controller.recommendation.Recommendation;


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
//		book.setId(33);
//		book.setAuthor("Teste3");
//		book.setDescription("Um livro qualquer");
//		book.setName("TesteVideo teste");
//		ArrayList<Tag> a =  new ArrayList<Tag>();
//		Tag tag1 = new Tag();
//		tag1.setId(25);
//		Tag tag2 = new Tag();
//		tag2.setId(32);
//		a.add(tag1);
//		a.add(tag2);
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
		//new Queries().buscaBookNome("Foundation");
		
		//new Queries().buscaBookDescricao("Um livro qualquer");
		
		//new Queries().buscaBookId(3);
		
		//new Queries().buscaClientCpf(316341654);
		
		//new Queries().buscaClientNome("Eduardo");
		
		System.out.println(new TransactionDAOJDBC().getTransactionsByClient(1));
		Recommendation recommendation = new Recommendation();
		//recommendation.getRecommendationsForClient(1, 4);
		System.out.println(recommendation.getRecommendationsForClient(1, 7));
		
	}

}
