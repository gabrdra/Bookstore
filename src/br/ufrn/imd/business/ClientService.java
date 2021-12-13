package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.data.ClientDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Client;

public class ClientService implements IClientService {

	@Override
	public void addClient(Client client) throws BusinessException, DataException {
		Client clientBd = retrieveClientByCpf(client.getCpf());
		
		String exceptions = "";
		
		if(clientBd.getCpf()!=null) {
			exceptions += "CPF digitado já existe no sistema \n";
		}
		if(client.getName().length() <= 2) {
			exceptions += "Nome muito curto \n";
		}
		else if(client.getName().length() >= 64) {
			exceptions += "Nome muito longo \n";
		}
		
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new ClientDAOJDBC().addClient(client);
	}
	
	

	public Client retrieveClientByCpf(String cpf) throws BusinessException, DataException {
		String exceptions = "";
		if(!cpf.matches("[0-9]+")) {
			exceptions += "CPF deve conter somente números \n";
		}
		if(cpf.length()!= 11) {
			exceptions += "CPF deve conter exatamente 11 números \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}		
		return new ClientDAOJDBC().retrieveClientByCpf(cpf);
	}



	@Override
	public void removeClient(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClient(Client client) throws DataException, BusinessException{
		Client clientBd = retrieveClientByCpf(client.getCpf());
		String exceptions = "";
		if(clientBd.getId() != client.getId() && clientBd.getCpf()!=null) {
			if(retrieveClientById(client.getId()).getId()==0) {
				exceptions += "Cliente inexistente \n";
			}
			else {
				exceptions += "CPF digitado já está cadastrado em outro cliente \n";
			}
			
		}
		if(client.getName().length() <= 2) {
			exceptions += "Nome muito curto \n";
		}
		else if(client.getName().length() >= 64) {
			exceptions += "Nome muito longo \n";
		}
		
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new ClientDAOJDBC().updateClient(client);

	}

	@Override
	public List<Client> listClients() throws DataException {
		return new ClientDAOJDBC().listClients();
	}

	@Override
	public Client retrieveClientById(int id) throws DataException, BusinessException{
		if(id < 1) {
			throw new BusinessException("id deve ser um número maior do que 0 \n");
		}
		return new ClientDAOJDBC().retrieveClientById(id);
		
	}
}
