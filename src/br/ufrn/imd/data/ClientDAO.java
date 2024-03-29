package br.ufrn.imd.data;

import java.util.List;

import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Client;

public interface ClientDAO {

	public void addClient(Client client) throws DataException;
	
	public void removeClient(Client client) throws DataException;
	
	public void updateClient(Client client) throws DataException;
	
	public List<Client> listClients() throws DataException;
	
	public Client retrieveClientById(int id) throws DataException;

	public Client retrieveClientByCpf(String cpf) throws DataException;
}
