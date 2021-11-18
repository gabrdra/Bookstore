package br.ufrn.imd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.controller.Conection;
import br.ufrn.imd.model.Client;

public class ClientDAO {
	private Connection c;
	
	public ClientDAO() {
		this.c= new Conection().getCon();
	}
	
	public void adiciona(Client cl) {
		String sql="INSERT INTO public.client (cpf, name) VALUES (?, ?);";
		try {
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setLong(1, cl.getCpf());
			stmt.setString(2, cl.getName());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Client> ListClient() {
		
		List<Client> listClients = new ArrayList<Client>();
		
		try {
			String sql = "SELECT * FROM public.client";
			
			PreparedStatement stmt = c.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				Client client = new Client();
				client.setCpf(resultSet.getLong("cpf"));
				client.setName(resultSet.getString("name"));
				
				listClients.add(client);
			}
			stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listClients;
	}
}
