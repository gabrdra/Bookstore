package br.ufrn.imd.business.recommendation;

import java.util.HashMap;
import java.util.List;

public interface IRecommendation<T, U> {

	public List<T> retrieveRecommendationForClient(int client, int recommendationAmount, HashMap<String,U> options);
}
