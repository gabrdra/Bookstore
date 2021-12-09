package br.ufrn.imd.data;

import java.util.List;

import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Client;

public interface ClientDAO {

	public void addClient(Client client) throws DataException;
	
	
	public void removeClient(Client client);
	
	public Client consultClient();
	
	public void updateClient(Client client);
	
	public List<Client> ListClient();
	
	public Client getClientById(int id);


	Client getClientByCpf(String cpf);
}
