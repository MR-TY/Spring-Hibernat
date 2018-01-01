package com.ty.spring.hibernat.serviceimp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ty.spring.hibernat.dao.BookShopDao;
import com.ty.spring.hibernat.service.BookShopService;

@Service
public class ServiceImp implements BookShopService {
	@Resource
	private BookShopDao bookShopDao;
	
	//买书
	public void purchase(String username,String isbn){
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}
}
