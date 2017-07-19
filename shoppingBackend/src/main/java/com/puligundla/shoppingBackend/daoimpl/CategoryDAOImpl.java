package com.puligundla.shoppingBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.puligundla.shoppingBackend.dao.CategoryDAO;
import com.puligundla.shoppingBackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {
		String selectActivecategory ="FROM Category WHERE active = :active";
		@SuppressWarnings("unchecked")
		Query<Category> query = sessionFactory.getCurrentSession().createQuery(selectActivecategory);
		query.setParameter("active", true);
		return query.getResultList();
		}

	@Override
	public Category get(int id) {
		
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Category category) {
		try {

			sessionFactory.getCurrentSession().persist(category);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Category category) {
		
		try {

			sessionFactory.getCurrentSession().update(category);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {

			sessionFactory.getCurrentSession().update(category);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
