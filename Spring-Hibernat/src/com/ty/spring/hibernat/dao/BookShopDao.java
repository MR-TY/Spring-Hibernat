package com.ty.spring.hibernat.dao;

public interface BookShopDao {

	//根据书号获取书的单价
	public int findBookPriceByIsbn(String isbn);
	
	//根据书号更新书的仓库
	public void updateBookStock(String isbn);
	
	//更新账号的余额
	public void updateUserAccount(String username, int price);
}
