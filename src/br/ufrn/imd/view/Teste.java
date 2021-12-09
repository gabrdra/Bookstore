package br.ufrn.imd.view;


import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.business.TagService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Client;
import br.ufrn.imd.model.Tag;


public class Teste {

	public static void main(String[] args) throws BusinessException {
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
		//Tests with client
		
		/*Client client = new Client();
		client.setCpf("11122233345");
		client.setName("João");
		try {
			new ClientService().addClient(client);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			System.out.println(new ClientService().retrieveClientById(-1));
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Tests with tag
		Tag testTag = new Tag();
		testTag.setName("Francês");
		try {
			new TagService().addTag(testTag);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
