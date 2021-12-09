package br.ufrn.imd.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.data.connection.ConnectionJDBC;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Client;

public class ClientDAOJDBC implements ClientDAO{

	private Connection connection;
	
	public ClientDAOJDBC() {
		this.connection= new ConnectionJDBC().getCon();
	}

	@Override
	public void addClient(Client client) throws DataException{
		String sql="INSERT INTO public.client (cpf, name) VALUES (?, ?);";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setString(1, client.getCpf());
			stmt.setString(2, client.getName());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Cliente não pode ser inserido no banco de dados \n");
			//e.printStackTrace();
		}
		System.out.println("Cliente inserido com sucesso!");
	}

	@Override
	public void removeClient(Client client) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> listClients() throws DataException {
		
		List<Client> listClients = new ArrayList<Client>();
		
		try {
			String sql = "SELECT * FROM public.client";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				Client client = new Client();
				client.setCpf(resultSet.getString("cpf"));
				client.setName(resultSet.getString("name"));
				listClients.add(client);
			}
			stmt.close();
			
		} catch (Exception e) {
			throw new DataException("Erro ao acessar o banco de dados \n");
			//e.printStackTrace();
		}
		return listClients;
	}

	@Override
	public Client retrieveClientById(int id) throws DataException{
		try {
			String clientSql = "SELECT * FROM public.client WHERE id="+id;
			PreparedStatement prepstmt = connection.prepareStatement(clientSql);
			ResultSet result = prepstmt.executeQuery();
			Client client = new Client();
			while(result.next()) {
				client.setId(result.getInt("id"));
				client.setName(result.getString("name"));
				client.setCpf(result.getString("cpf"));
			}
			prepstmt.close();
			return client;
		}
		catch (Exception e) {
			throw new DataException("Erro ao tentar pegar o cliente usando a id \n");
			//e.printStackTrace();
			//return null;
		}
		
	}
	
	@Override
	public Client retrieveClientByCpf(String cpf) throws DataException {
		try {
			String clientSql = "SELECT * FROM public.client WHERE cpf='"+cpf+"'" ;
			PreparedStatement prepstmt = connection.prepareStatement(clientSql);
			ResultSet result = prepstmt.executeQuery();
			Client client = new Client();
			while(result.next()) {
				client.setId(result.getInt("id"));
				client.setName(result.getString("name"));
				client.setCpf(result.getString("cpf"));
			}
			prepstmt.close();
			return client;
		}
		catch (Exception e) {
			throw new DataException("Erro ao tentar pegar o cliente usando o CPF \n");
			//e.printStackTrace();
			//return null;
		}
		
	}
	
	
	
	
	
}