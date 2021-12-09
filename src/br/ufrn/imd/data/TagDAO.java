package br.ufrn.imd.data;

import java.util.List;

import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Tag;

public interface TagDAO {

	public void addTag(Tag tag) throws DataException;
	
	public void removeTag();
	
	public Tag consultTag();
	
	public void updateTag();
	
	public List<Tag> listTags() throws DataException;
	
	public Tag retrieveTagById(int id) throws DataException;
	
	public Tag retrieveTagByName(String name) throws DataException;
	
	
}
