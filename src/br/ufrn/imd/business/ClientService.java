package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.data.ClientDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.model.Client;

public class ClientService implements IClientService {

	@Override
	public void addClient(Client client) throws BusinessException {
		Client clientBd = new ClientDAOJDBC().getClientByCpf(client.getCpf());
		
		if(clientBd.getCpf()!=null) {
			throw new BusinessException("CPF digitado ja existe no sistema");
		}
		if(client.getCpf().length()!=11) {
			throw new BusinessException("Formato do CPF invalido");
		}
		
		new ClientDAOJDBC().addClient(client);
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
	public List<Client> ListClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client retrieveClientById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
