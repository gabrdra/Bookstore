package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.data.BookDAOJDBC;
//import br.ufrn.imd.data.ClientDAOJDBC;
import br.ufrn.imd.data.TagDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Tag;
import br.ufrn.imd.model.Transaction;

public class TagService implements ITagService {

	@Override
	public void addTag(Tag tag) throws BusinessException, DataException {
		String exceptions = "";
		if(retrieveTagByName(tag.getName()).getName()!=null) {
			exceptions += "Nome da tag já registrado no sistema \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new TagDAOJDBC().addTag(tag);
	}

	@Override
	public void removeTag(Tag tag) throws BusinessException, DataException {
		String exceptions = "";
		List<Book> books = new BookService().listBooks();
		for(Book book: books) {
			boolean found = false;
			for(Tag t: book.getTags()) {
				if(t.getId() == tag.getId()) {
					exceptions += "A tag não pode ser removida pois existe dentro de ao menos um livro \n";
					found = true;
					break;
				}
			}
			if(found) {
				break;
			}
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new TagDAOJDBC().removeTag(tag);

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

	@Override
	public Tag retrieveTagByName(String name) throws DataException, BusinessException {
		String exceptions = "";
		if(name.length() <= 1) {
			exceptions += "O nome da tag deve ter ao menos 2 letras \n"; 
		}
		else if(name.length() >= 32) {
			exceptions += "O nome da tag deve ser menor que 32 caracteres \n ";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		return new TagDAOJDBC().retrieveTagByName(name);
	}

	@Override
	public void updateTag(Tag tag) throws BusinessException, DataException {
		String exceptions = "";
		if(retrieveTagByName(tag.getName()).getName()!=null) {
			exceptions += "Nome da tag já registrado no sistema \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new TagDAOJDBC().updateTag(tag);
	}

}
