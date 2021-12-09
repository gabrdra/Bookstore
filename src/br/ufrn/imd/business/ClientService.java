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
			//throw new BusinessException("CPF digitado ja existe no sistema");
			exceptions += "CPF digitado já existe no sistema \n";
		}
		/*if(client.getCpf().length()!=11) {
			//throw new BusinessException("Formato do CPF invalido");
			exceptions += "Formato do CPF inválido \n";
		}*/
		if(client.getName().length() <= 2) {
			exceptions += "Nome muito curto \n";
		}
		if(client.getName().length() >= 64) {
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
	public void updateClient(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Client> ListClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client retrieveClientById(int id) throws DataException, BusinessException{
		if(id < 1) {
			throw new BusinessException("id deve ser um número maior do que 0 \n");
		}
		return new ClientDAOJDBC().retrieveClientById(id);
		
	}
}
