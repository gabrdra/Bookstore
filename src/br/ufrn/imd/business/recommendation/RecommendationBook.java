package br.ufrn.imd.business.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import br.ufrn.imd.business.BookService;
import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.business.ITransactionService;
import br.ufrn.imd.business.TransactionService;
import br.ufrn.imd.data.ProductBookDAOJDBC;
import br.ufrn.imd.data.TransactionDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductBook;
import br.ufrn.imd.model.Tag;
import br.ufrn.imd.model.Transaction;
import br.ufrn.imd.model.recommendation.WeightBook;
import br.ufrn.imd.model.recommendation.WeightTag;

public class RecommendationBook implements IRecommendation<ProductBook, Integer>{
	
	public List<ProductBook> retrieveRecommendationsForClient(int client, int recommendationAmount, HashMap<String,Integer> options) throws DataException, BusinessException{
		ITransactionService transactions = new TransactionService();
		String exceptions = "";
		if(new ClientService().retrieveClientById(client).getId() == 0) {
			exceptions += "Cliente não existente \n";
		}
		if(recommendationAmount < 1) {
			exceptions += "Deve ser pedida ao menos uma recomendação \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		ArrayList<Transaction> clientPreviousTransactions = transactions.retrieveTransactionsByClient(client);
		ArrayList<ProductBook> previouslyBoughtBooks = new ArrayList<ProductBook>();
		//Get every instance of a book
		for(Transaction transaction: clientPreviousTransactions) {
			for(ProductBook book:transaction.getBooks()) {
				previouslyBoughtBooks.add(book);
			}
		}
		ArrayList<Tag> tags = new ArrayList<Tag>();
		//Get every instance of a tag in the client's previously bought books
		for(ProductBook book:previouslyBoughtBooks) {
			for(Tag tag:book.getTags()) {
				tags.add(tag);
			}
		}
		ArrayList<WeightTag> weightTags = new ArrayList<WeightTag>();
		//Populate the weightTags
		for(Tag tag:tags) {
			int indexOfWeightTag = -1;//If this remains -1 it means that this tag is the first instance of it in the tags array list
			for(int i = 0; i < weightTags.size();i++) {
				if(weightTags.get(i).getTag().getId() == tag.getId()) {
					indexOfWeightTag = i;
					break;
				}
			}
			if(indexOfWeightTag != -1) {
				WeightTag currentWeightTag = weightTags.get(indexOfWeightTag);
				currentWeightTag.setWeight(currentWeightTag.getWeight()+1);//Add 1 to the weight of the tag
			}
			else {
				weightTags.add(new WeightTag(tag)); //Creates a new weight tag with a weight of 1
			}
		}

		ArrayList<ProductBook> books = new BookService().listBooks(); //Get all books
		//Creates a priority queue that has the head as the one with the biggest priority
		PriorityQueue<WeightBook> weightBooks = new PriorityQueue<WeightBook>(Collections.reverseOrder()); 
		//Populate the weightBooks
		for(ProductBook book:books) {
			if(!(previouslyBoughtBooks.contains(book))) { //If the client already bought a book it won't recommend that book
				int weight = 0;
				for(Tag bookTag:book.getTags()) {
					for(WeightTag wTag:weightTags) {
						if(wTag.getTag().getId() == bookTag.getId()) {
							weight+=wTag.getWeight();
						}
					}
				}
				weightBooks.add(new WeightBook(weight, book));
				/*if(weightBooks.size()>recommendationAmount) {
					weightBooks.poll();
				}*/
			}
		}
		System.out.println(weightBooks);
		ArrayList<ProductBook> recommendedBooks = new ArrayList<ProductBook>();
		for(int i = 0; i < recommendationAmount; i++) {
			//Book currentBook = weightBooks.poll().getBook();
			recommendedBooks.add(weightBooks.poll().getBook());
			if(weightBooks.size()==0) {
				break;
			}
		}
		
		return recommendedBooks;
	}

	

	
	
}
