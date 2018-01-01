package com.ty.spring.hibernat.dao.imp;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.ty.spring.hibernat.dao.BookShopDao;

@Repository
public class BookShopDaoImp implements BookShopDao {
	@Resource
	private SessionFactory sessionFactory;

	// 获取和当前线程绑定的session
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int findBookPriceByIsbn(String isbn) {
		String hql = "SELECT b.price FROM Book b WHERE b.isbn = :isbn";
		Query query = getSession().createQuery(hql).setParameter("isbn", isbn);
		return (int) query.uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) {
		// 验证库存的书是否是大于0的
		String hql1 = "SELECT b.stock FROM Book b WHERE b.isbn = :isbn";
		int stock = (int) getSession().createQuery(hql1).setParameter("isbn", isbn).uniqueResult();
		if (stock == 0) {
			throw new RuntimeException("库存不足不能购买");
		} else {
			String hql = "UPDATE Book b SET b.stock = (b.stock-1) WHERE b.isbn = :isbn";
			getSession().createQuery(hql).setParameter("isbn", isbn).executeUpdate();
		}
	}

	@Override
	public void updateUserAccount(String username, int price) {
		System.out.println("进来了");
		// 验证余额是否足够
		String hql1 = "SELECT a.balance FROM Account a WHERE a.username = :username";
		int balance = (int) getSession().createQuery(hql1).setParameter("username", username).uniqueResult();
		System.out.println(balance);
		if (balance < price) {
			throw new RuntimeException("余额不足，不能购买");
		} else {
			String hql = "UPDATE Account a SET a.balance = a.balance - :price WHERE a.username = :username";
			getSession().createQuery(hql).setParameter("price", price).setParameter("username", username).executeUpdate();
		}
	}
}
