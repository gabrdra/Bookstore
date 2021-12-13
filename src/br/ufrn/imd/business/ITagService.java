package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Tag;

public interface ITagService {

	public void addTag(Tag tag) throws BusinessException, DataException;
	
	public void removeTag();
	
	public void updateTag(Tag tag)throws BusinessException, DataException;
	
	public List<Tag> listTags() throws DataException;
	
	public Tag retrieveTagById(int id) throws DataException, BusinessException;
	
	public Tag retrieveTagByName(String name) throws DataException, BusinessException;
}
