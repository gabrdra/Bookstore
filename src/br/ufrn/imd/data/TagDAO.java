package br.ufrn.imd.data;

import java.util.List;

import br.ufrn.imd.model.Tag;

public interface TagDAO {

	public void addTag(Tag tag);
	
	public void removeTag();
	
	public Tag consultTag();
	
	public void updateTag();
	
	public List<Tag> listTags();
	
	public Tag getTagById(int id);
	
	
}
