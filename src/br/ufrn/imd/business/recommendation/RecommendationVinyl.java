package br.ufrn.imd.business.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.business.IProductService;
import br.ufrn.imd.business.ITransactionService;
import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.business.TransactionService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.instanceController.InstanceController;
import br.ufrn.imd.model.Product;
import br.ufrn.imd.model.ProductVinyl;
import br.ufrn.imd.model.Tag;
import br.ufrn.imd.model.Transaction;
import br.ufrn.imd.model.recommendation.WeightProduct;
import br.ufrn.imd.model.recommendation.WeightTag;

public class RecommendationVinyl implements IRecommendation<ProductVinyl, Integer>{
	
	private IProductService productService = new ProductService();
	
	public List<ProductVinyl> retrieveRecommendationsForClient(int client, int recommendationAmount, HashMap<String,Integer> options) throws DataException, BusinessException{
		
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
		ArrayList<ProductVinyl> previouslyBoughtProducts = new ArrayList<ProductVinyl>();
		//Get every instance of a Product
		for(Transaction transaction: clientPreviousTransactions) {
			for(Integer productId:transaction.getProductsId()) {
				previouslyBoughtProducts.add((ProductVinyl)productService.retrieveProductById(productId));
			}
		}
		ArrayList<Tag> tags = new ArrayList<Tag>();
		//Get every instance of a tag in the client's previously bought Products
		for(ProductVinyl product:previouslyBoughtProducts) {
			for(Tag tag:product.getTags()) {
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

		ArrayList<Product> products = (ArrayList<Product>)productService.listProducts();  //Get all products
		//Creates a priority queue that has the head as the one with the biggest priority
		PriorityQueue<WeightProduct> weightProducts = new PriorityQueue<WeightProduct>(Collections.reverseOrder()); 
		//Populate the weightProducts
		if(true) {
			for(Product product:products) {
				System.out.println(product.getName()+ " " +!(previouslyBoughtProducts.contains(product)));
				if(!(previouslyBoughtProducts.contains(product))) { //If the client already bought a product it won't recommend that product
					int weight = 0;
					for(Tag productTag:product.getTags()) {
						for(WeightTag wTag:weightTags) {
							if(wTag.getTag().getId() == productTag.getId()) {
								weight+=wTag.getWeight();
							}
						}
					}
					weightProducts.add(new WeightProduct(weight, (ProductVinyl)product));
				}
			}
		}
		else {
			for(Product product:products) {
				ProductVinyl productVinyl = (ProductVinyl)product;
				if((!(previouslyBoughtProducts.contains(product)))){// && platform.toLowerCase().equals(productVinyl.getPlatform().toLowerCase())) { //If the client already bought a product it won't recommend that product
					int weight = 0;
					for(Tag productTag:product.getTags()) {
						for(WeightTag wTag:weightTags) {
							if(wTag.getTag().getId() == productTag.getId()) {
								weight+=wTag.getWeight();
							}
						}
					}
					weightProducts.add(new WeightProduct(weight, (ProductVinyl)product));
				}
			}
		}
		System.out.println(weightProducts);
		ArrayList<ProductVinyl> recommendedProducts = new ArrayList<ProductVinyl>();
		for(int i = 0; i < recommendationAmount; i++) {
			//Product currentProduct = weightProducts.poll().getProduct();
			recommendedProducts.add((ProductVinyl) weightProducts.poll().getProduct());
			if(weightProducts.size()==0) {
				break;
			}
		}
		
		return recommendedProducts;
	}
}
