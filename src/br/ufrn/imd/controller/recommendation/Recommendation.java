package br.ufrn.imd.controller.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import br.ufrn.imd.controller.data.BookDAOJDBC;
import br.ufrn.imd.controller.data.TransactionDAOJDBC;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Tag;
import br.ufrn.imd.model.Transaction;
import br.ufrn.imd.model.recommendation.WeightBook;
import br.ufrn.imd.model.recommendation.WeightTag;

public class Recommendation {
	
	public ArrayList<Book> getRecommendationsForClient(int client, int recommendationAmount){
		TransactionDAOJDBC transactions = new TransactionDAOJDBC();
		ArrayList<Transaction> clientPreviousTransactions = transactions.getTransactionsByClient(client);
		ArrayList<Book> previouslyBoughtBooks = new ArrayList<Book>();
		//Get every instance of a book
		for(Transaction transaction: clientPreviousTransactions) {
			for(Book book:transaction.getBooks()) {
				previouslyBoughtBooks.add(book);
			}
		}
		ArrayList<Tag> tags = new ArrayList<Tag>();
		//Get every instance of a tag in the client's previously bought books
		for(Book book:previouslyBoughtBooks) {
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

		ArrayList<Book> books = new BookDAOJDBC().listBooks(); //Get all books
		//Creates a priority queue that has the head as the one with the biggest priority
		PriorityQueue<WeightBook> weightBooks = new PriorityQueue<WeightBook>(Collections.reverseOrder()); 
		//Populate the weightBooks
		for(Book book:books) {
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
		ArrayList<Book> recommendedBooks = new ArrayList<Book>();
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
