package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Client;

public interface IClientService {

	public void addClient(Client client) throws BusinessException, DataException;
	
	public void removeClient(Client client);
	
	public void updateClient(Client client);
	
	public List<Client> ListClient();
	
	public Client retrieveClientById(int id) throws DataException, BusinessException;
	
	public Client retrieveClientByCpf(String cpf) throws DataException, BusinessException;
}
