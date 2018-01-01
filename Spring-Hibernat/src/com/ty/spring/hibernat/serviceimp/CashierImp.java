package com.ty.spring.hibernat.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ty.spring.hibernat.service.BookShopService;
import com.ty.spring.hibernat.service.Cashier;

@Repository
public class CashierImp implements Cashier {
	
	@Resource
	private BookShopService bookShopService;

	@Override
	public void checkout(String username, List<String> isbns) {
		for(String isbn:isbns){
			bookShopService.purchase(username, isbn);
		}
	}
}
