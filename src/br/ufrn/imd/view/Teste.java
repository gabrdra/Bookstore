package br.ufrn.imd.view;


import java.util.ArrayList;

import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.business.TagService;
import br.ufrn.imd.business.TransactionService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductBook;
import br.ufrn.imd.model.Client;
import br.ufrn.imd.model.Tag;
import br.ufrn.imd.model.Transaction;


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
		//Tests with client
		
		/*Client client = new Client();
		client.setCpf("11122277745");
		client.setName("Rodrigo");
		try {
			new ClientService().addClient(client);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Client client = new Client();
		client.setId(8);
		client.setCpf("11122277745");
		client.setName("Rodrigo");
		try {
			new ClientService().removeClient(client);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Client client = new Client();
		client.setId(2);
		client.setCpf("12345618911");
		client.setName("Eduardo");
		try {
			new ClientService().updateClient(client);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			System.out.println(new ClientService().retrieveClientById(-1));
		} catch (DataException |BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			System.out.println(new ClientService().listClients());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Tests with tag
		/*Tag testTag = new Tag();
		testTag.setName("francês");
		try {
			new TagService().addTag(testTag);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Tag testTag = new Tag();
		testTag.setId(16);
		testTag.setName("Italiano");
		try {
			new TagService().updateTag(testTag);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Tag testTag = new Tag();
		testTag.setId(16);
		try {
			new TagService().removeTag(testTag);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			System.out.println(new TagService().retrieveTagById(1));
		} catch (DataException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			System.out.println(new TagService().listTags());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Tests with books
		/*Book book = new Book();
		book.setBarcode("7686626982215");
		book.setName("Arsène Lupin");
		book.setPrice(17.5);
		book.setAuthor("Maurice Leblanc");
		ArrayList<Tag> tags = new ArrayList<Tag>();
		Tag tempTag1 = new Tag();
		tempTag1.setId(3);
		tags.add(tempTag1);
		Tag tempTag2 = new Tag();
		tempTag2.setId(11);
		tags.add(tempTag2);
		Tag tempTag3 = new Tag();
		tempTag3.setId(15);
		tags.add(tempTag3);
		book.setTags(tags);
		try {
			new BookService().addBook(book);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Book book = new Book();
		book.setId(12);
		book.setBarcode("7686626982215");
		book.setName("Arsène Lupin II");
		book.setPrice(17.5);
		book.setAuthor("Maurice Leblanc");
		ArrayList<Tag> tags = new ArrayList<Tag>();
		Tag tempTag1 = new Tag();
		tempTag1.setId(3);
		tags.add(tempTag1);
		Tag tempTag2 = new Tag();
		tempTag2.setId(11);
		tags.add(tempTag2);
		Tag tempTag3 = new Tag();
		tempTag3.setId(15);
		tags.add(tempTag3);
		book.setTags(tags);
		try {
			new BookService().addBook(book);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Book book = new Book();
		book.setId(12);
		book.setBarcode("7686626982215");
		book.setName("Arsène Lupin II");
		book.setPrice(17.5);
		book.setAuthor("Maurice Leblanc");
		ArrayList<Tag> tags = new ArrayList<Tag>();
		Tag tempTag1 = new Tag();
		tempTag1.setId(3);
		tags.add(tempTag1);
		Tag tempTag2 = new Tag();
		tempTag2.setId(11);
		tags.add(tempTag2);
		Tag tempTag3 = new Tag();
		tempTag3.setId(15);
		tags.add(tempTag3);
		book.setTags(tags);
		try {
			new BookService().removeBook(book);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Book book = new Book();
		book.setId(13);
		try {
			new BookService().removeBook(book);
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			System.out.println(new BookService().retrieveBookByBarcode("6676476981216"));
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			System.out.println(new BookService().retrieveBookByBarcode(null));
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Tests with transaction
		/*try {
			System.out.println(new TransactionService().listTransactions());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(new TransactionService().retrieveTransactionsByClient(1));
		} catch (DataException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(new TransactionService().retrieveTransactionById(5));
		} catch (DataException | BusinessException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}/*
		/*Transaction transaction = new Transaction();
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			books.add(new BookService().retrieveBookById(10));
			books.add(new BookService().retrieveBookById(18));
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.setBooks(books);
		transaction.setId(7);
		transaction.setClient(1);
		try {
			new TransactionService().updateTransaction(transaction);
		} catch (DataException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Transaction transaction = new Transaction();
		Book book = null;
		try {
			book = new BookService().retrieveBookByBarcode("1764016980495");
		} catch (BusinessException | DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(book);
		transaction.setBooks(books);
		transaction.setClient(1);
		transaction.setValue(book.getPrice());
		System.out.println(transaction);
		try {
			new TransactionService().addTransaction(transaction);
		} catch (DataException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}

}
