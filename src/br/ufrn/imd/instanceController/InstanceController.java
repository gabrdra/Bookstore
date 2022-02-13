package br.ufrn.imd.instanceController;

public class InstanceController {
	public enum StoreTypes{
		BOOK, GAME, VINYL
	}
	public static StoreTypes currentInstanceType = StoreTypes.VINYL;
}
