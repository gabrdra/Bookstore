package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.data.ClientDAOJDBC;
import br.ufrn.imd.exceptions.ExistingCpfException;
import br.ufrn.imd.exceptions.InvalidCpfException;
import br.ufrn.imd.model.Client;

public class ClientService implements IClientService {

	@Override
	public void addClient(Client client) throws ExistingCpfException, InvalidCpfException {
		Client clientBd = new ClientDAOJDBC().getClientByCpf(client.getCpf());
		
		if(clientBd.getCpf()!=null) {
			throw new ExistingCpfException();
		}
		if(client.getCpf().length()!=11) {
			throw new InvalidCpfException();
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
