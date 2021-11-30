package br.ufrn.imd.recommendation;

import br.ufrn.imd.model.Tag;

public class WeightTag {
	private int weight;
	private Tag tag;
	
	//public WeightTag() {}
	public WeightTag(Tag tag) {
		this.tag = tag;
		this.weight = 1;
	}
	
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Weight: "+weight+ " id: "+tag.getId();
	}
}
