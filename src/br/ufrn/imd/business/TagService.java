package br.ufrn.imd.business;

import java.util.List;

//import br.ufrn.imd.data.ClientDAOJDBC;
import br.ufrn.imd.data.TagDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Tag;

public class TagService implements ITagService {

	@Override
	public void addTag(Tag tag) throws BusinessException, DataException {
		String exceptions = "";
		if(tag.getName().length() <= 1) {
			exceptions += "O nome da tag deve ter ao menos 2 letras \n"; 
		}
		else if(tag.getName().length() >= 32) {
			exceptions += "O nome da tag deve ser menor que 32 caracteres \n ";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new TagDAOJDBC().addTag(tag);
	}

	@Override
	public void removeTag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTag() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tag> listTags() throws DataException {
		return new TagDAOJDBC().listTags();
	}

	@Override
	public Tag retrieveTagById(int id) throws DataException, BusinessException{
		if(id < 1) {
			throw new BusinessException("id deve ser um número maior do que 0 \n");
		}
		return new TagDAOJDBC().retrieveTagById(id);
	}

}
