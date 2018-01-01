package com.ty.spring.hibernat.test;

import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ty.spring.hibernat.dao.BookShopDao;
import com.ty.spring.hibernat.dao.imp.BookShopDaoImp;
import com.ty.spring.hibernat.service.BookShopService;
import com.ty.spring.hibernat.service.Cashier;

public class TestSpringOrHibernate {
	private ApplicationContext context;
	private BookShopService bookShopService;
	private BookShopDao bookShopDao;
	private Cashier cashier;
	{
		context = new ClassPathXmlApplicationContext("application.xml");
		bookShopService = context.getBean(BookShopService.class);
		cashier = context.getBean(Cashier.class);
		bookShopDao = context.getBean(BookShopDao.class);
	}
	@Test
	public void testDao(){
		int price = bookShopDao.findBookPriceByIsbn("1001");
		System.out.println(price);
	}
	@Test
	public void testCashier1() {
		cashier.checkout("唐宇", Arrays.asList("1001", "1004"));
	}

	@Test
	public void test() {
		bookShopService.purchase("唐宇", "1001");
	}
}
