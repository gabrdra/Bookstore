package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.model.Tag;

public interface ITagService {

	public void addTag(Tag tag);
	
	public void removeTag();
	
	public void updateTag();
	
	public List<Tag> listTags();
	
	public Tag retrieveTagById(int id);
}
