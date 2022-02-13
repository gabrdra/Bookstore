package br.ufrn.imd.data;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.ufrn.imd.data.connection.ConnectionJDBC;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Product;
import br.ufrn.imd.model.ProductGame;
import br.ufrn.imd.model.Tag;

public class ProductGameDAOJDBC extends ProductDAOJDBC{
	
	private Connection connection;

	public ProductGameDAOJDBC() {
		this.connection= ConnectionJDBC.getInstance().getCon();
	}
	

	@Override
	public void addProduct(Product product) throws DataException {
		ProductGame productGame = (ProductGame) product; 
		String sql="INSERT INTO public.product (description, price, publisher, name, tags, barcode, platform) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			//stmt.setInt(1, bo.getId());
			stmt.setString(1, productGame.getDescription());
			stmt.setDouble(2, productGame.getPrice());
			stmt.setString(3, productGame.getPublisher());
			stmt.setString(4, productGame.getName());
			Array tempArray = connection.createArrayOf("INTEGER", productGame.getTagsId().toArray());
			stmt.setArray(5, tempArray);
			stmt.setString(6, productGame.getBarcode());
			stmt.setString(7, productGame.getPlatform());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar inserir o jogo no banco de dados \n");
			//e.printStackTrace();
		}
		
	}

	@Override
	public void removeProduct(Product product) throws DataException {
		String sql = "DELETE FROM public.product WHERE id="+product.getId();
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch(Exception e) {
			//e.printStackTrace();
			throw new DataException("Erro ao remover o jogo do banco de dados \n");
		}
		
	}

	@Override
	public void updateProduct(Product product) throws DataException {
		ProductGame productGame = (ProductGame) product;
		String sql="UPDATE public.product SET description = (?), price = (?), publisher = (?), name = (?), tags = (?), barcode = (?), platform = (?) WHERE id="+productGame.getId();
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			//stmt.setInt(1, bo.getId());
			stmt.setString(1, productGame.getDescription()); 
			stmt.setDouble(2, productGame.getPrice());
			stmt.setString(3, productGame.getPublisher());
			stmt.setString(4, productGame.getName());
			Array tempArray = connection.createArrayOf("INTEGER", productGame.getTagsId().toArray());
			stmt.setArray(5, tempArray);
			stmt.setString(6, productGame.getBarcode());
			stmt.setString(7, productGame.getPlatform());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar atualizar o jogo no banco de dados \n");
			//e.printStackTrace();
		}
		
	}

	@Override
	public List<? extends Product> listProducts() throws DataException {
		ArrayList<Product> listGames = new ArrayList<Product>();
		
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String sql = "SELECT * FROM public.product";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				ProductGame game = new ProductGame();
				
				game.setId(resultSet.getInt("id"));
				game.setName(resultSet.getString("name"));
				game.setPublisher(resultSet.getString("publisher"));
				game.setDescription(resultSet.getString("description"));
				game.setPrice(resultSet.getDouble("price"));
				game.setBarcode(resultSet.getString("barcode"));
				Integer[] tempArray = (Integer[])resultSet.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				game.setTags(localTags);
				game.setPlatform(resultSet.getString("platform"));
				listGames.add(game);
			}
			stmt.close();
			
		} catch (Exception e) {
			//e.printStackTrace();
			throw new DataException("Erro ao tentar listar os jogos armazenados no banco de dados \n");
		}
		
		return (List<Product>)listGames;
	}

	@Override
	public Product retrieveProductById(int id) throws DataException {
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String bookSql = "SELECT * FROM public.product WHERE id="+id;
			PreparedStatement prepstmt = connection.prepareStatement(bookSql);
			ResultSet result = prepstmt.executeQuery();
			ProductGame game = new ProductGame();
			while(result.next()) {
				game.setId(result.getInt("id"));
				game.setName(result.getString("name"));
				game.setPublisher(result.getString("publisher"));
				game.setDescription(result.getString("description"));
				game.setPrice(result.getDouble("price"));
				game.setBarcode(result.getString("barcode"));
				Integer[] tempArray = (Integer[])result.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				game.setTags(localTags);
				game.setPlatform(result.getString("platform"));
			}
			
			prepstmt.close();
			return game;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Erro ao tentar recuperar o jogo usando id \n");
		}
	}

	@Override
	public Product retrieveProductByBarcode(String barcode) throws DataException {
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String bookSql = "SELECT * FROM public.product WHERE barcode='"+barcode+"'";
			PreparedStatement prepstmt = connection.prepareStatement(bookSql);
			ResultSet result = prepstmt.executeQuery();
			ProductGame game = new ProductGame();
			while(result.next()) {
				game.setId(result.getInt("id"));
				game.setName(result.getString("name"));
				game.setPublisher(result.getString("publisher"));
				game.setDescription(result.getString("description"));
				game.setPrice(result.getDouble("price"));
				game.setBarcode(result.getString("barcode"));
				Integer[] tempArray = (Integer[])result.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				game.setTags(localTags);
				game.setPlatform(result.getString("platform"));
			}
			prepstmt.close();
			return game;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Erro ao tentar recuperar o jogo usando o c�digo de barras \n");
			//return null;
		}
	}

}
