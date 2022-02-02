package br.ufrn.imd.business.recommendation;

import java.util.HashMap;
import java.util.List;

import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;

public interface IRecommendation<T, U> {

	public List<T> retrieveRecommendationsForClient(int client, int recommendationAmount, HashMap<String,U> options) throws DataException, BusinessException;
}
