package com.teste.phonebook.test;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.teste.phonebook.dao.OperatorDAO;
import com.teste.phonebook.entity.Contact;
import com.teste.phonebook.entity.Operator;
import com.teste.phonebook.util.PersistenceUtil;

@RunWith(Arquillian.class)
public class OperatorTest {

	@Deployment
	public static JavaArchive createDeploy() {
		return ShrinkWrap.create(JavaArchive.class, "OperatorTest.jar")
				.addClasses(Operator.class, OperatorDAO.class, Contact.class, PersistenceUtil.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}

	@Inject
	private OperatorDAO dao;

	@Inject
	private UserTransaction tx;

	@Test
	@InSequence(1)
	public void testOne() {
		Operator o = new Operator();
		o.setName("TEST");
		o.setCode(69);
		o.setCategory("TEST");
		o.setValue(5.00);
		try {
			this.tx.begin();
			this.dao.save(o);
			this.tx.commit();
			Assert.assertTrue("Success", true);
		} catch (Exception e) {
			try {
				this.tx.rollback();
				Assert.fail("FAIL!");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Test
	@InSequence(2)
	public void testTwo() {
		List<Operator> list = null;
		try {
			this.tx.begin();
			list = this.dao.loadAll();
			this.tx.commit();
		} catch (Exception e) {
			try {
				this.tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		Assert.assertNotNull(list);
	}

}
