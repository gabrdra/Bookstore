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
import br.ufrn.imd.model.ProductVinyl;
import br.ufrn.imd.model.Tag;

public class ProductVinylDAOJDBC extends ProductDAOJDBC{
	
	private Connection connection;

	public ProductVinylDAOJDBC() {
		this.connection= ConnectionJDBC.getInstance().getCon();
	}

	@Override
	public void addProduct(Product product) throws DataException {
		ProductVinyl productVinyl = (ProductVinyl) product; 
		String sql="INSERT INTO public.product (description, price, band, year, name, tags, barcode) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			//stmt.setInt(1, bo.getId());
			stmt.setString(1, productVinyl.getDescription());
			stmt.setDouble(2, productVinyl.getPrice());
			stmt.setString(3, productVinyl.getBand());
			stmt.setInt(4, productVinyl.getYear());
			stmt.setString(5, productVinyl.getName());
			Array tempArray = connection.createArrayOf("INTEGER", productVinyl.getTagsId().toArray());
			stmt.setArray(6, tempArray);
			stmt.setString(7, productVinyl.getBarcode());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar inserir o vinil no banco de dados \n");
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
			throw new DataException("Erro ao remover o vinil do banco de dados \n");
		}
		
	}

	@Override
	public void updateProduct(Product product) throws DataException {
		ProductVinyl productVinyl = (ProductVinyl) product;
		String sql="UPDATE public.product SET description = (?), price = (?), band = (?),year = (?), name = (?), tags = (?), barcode = (?) WHERE id="+productVinyl.getId();
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			//stmt.setInt(1, bo.getId());
			stmt.setString(1, productVinyl.getDescription());
			stmt.setDouble(2, productVinyl.getPrice());
			stmt.setString(3, productVinyl.getBand());
			stmt.setInt(4, productVinyl.getYear());
			stmt.setString(5, productVinyl.getName());
			Array tempArray = connection.createArrayOf("INTEGER", productVinyl .getTagsId().toArray());
			stmt.setArray(6, tempArray);
			stmt.setString(7, productVinyl.getBarcode());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar atualizar o vinil no banco de dados \n");
			//e.printStackTrace();
		}
		
	}

	@Override
	public List<? extends Product> listProducts() throws DataException {
		ArrayList<Product> listVinyl = new ArrayList<Product>();
		
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String sql = "SELECT * FROM public.product";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				ProductVinyl vinyl = new ProductVinyl();
				
				vinyl.setId(resultSet.getInt("id"));
				vinyl.setName(resultSet.getString("name"));
				vinyl.setBand(resultSet.getString("band"));
				vinyl.setYear(resultSet.getInt("year"));
				vinyl.setDescription(resultSet.getString("description"));
				vinyl.setPrice(resultSet.getDouble("price"));
				vinyl.setBarcode(resultSet.getString("barcode"));
				Integer[] tempArray = (Integer[])resultSet.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				vinyl.setTags(localTags);
				listVinyl.add(vinyl);
			}
			stmt.close();
			
		} catch (Exception e) {
			//e.printStackTrace();
			throw new DataException("Erro ao tentar listar os vinis armazenados no banco de dados \n");
		}
		
		return (List<Product>)listVinyl;
	}

	@Override
	public Product retrieveProductById(int id) throws DataException {
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String bookSql = "SELECT * FROM public.product WHERE id="+id;
			PreparedStatement prepstmt = connection.prepareStatement(bookSql);
			ResultSet result = prepstmt.executeQuery();
			ProductVinyl vinyl = new ProductVinyl();
			while(result.next()) {
				vinyl.setId(result.getInt("id"));
				vinyl.setName(result.getString("name"));
				vinyl.setBand(result.getString("band"));
				vinyl.setYear(result.getInt("year"));
				vinyl.setDescription(result.getString("description"));
				vinyl.setPrice(result.getDouble("price"));
				vinyl.setBarcode(result.getString("barcode"));
				Integer[] tempArray = (Integer[])result.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				vinyl.setTags(localTags);
			}
			
			prepstmt.close();
			return vinyl;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Erro ao tentar recuperar o vinil usando id \n");
		}
	}

	@Override
	public Product retrieveProductByBarcode(String barcode) throws DataException {
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String bookSql = "SELECT * FROM public.product WHERE barcode='"+barcode+"'";
			PreparedStatement prepstmt = connection.prepareStatement(bookSql);
			ResultSet result = prepstmt.executeQuery();
			ProductVinyl vinyl = new ProductVinyl();
			while(result.next()) {
				vinyl.setId(result.getInt("id"));
				vinyl.setName(result.getString("name"));
				vinyl.setBand(result.getString("band"));
				vinyl.setYear(result.getInt("year"));
				vinyl.setDescription(result.getString("description"));
				vinyl.setPrice(result.getDouble("price"));
				vinyl.setBarcode(result.getString("barcode"));
				Integer[] tempArray = (Integer[])result.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				vinyl.setTags(localTags);
			}
			prepstmt.close();
			return vinyl;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Erro ao tentar recuperar o vinil usando o c�digo de barras \n");
			//return null;
		}
	}

}
