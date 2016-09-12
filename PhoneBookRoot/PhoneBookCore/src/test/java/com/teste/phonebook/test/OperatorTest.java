package com.teste.phonebook.test;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.teste.phonebook.bo.IOperatorBO;
import com.teste.phonebook.bo.OperatorBO;
import com.teste.phonebook.dao.OperatorDAO;
import com.teste.phonebook.dto.ContactDTO;
import com.teste.phonebook.dto.OperatorDTO;
import com.teste.phonebook.entity.Contact;
import com.teste.phonebook.entity.Operator;
import com.teste.phonebook.util.PersistenceUtil;

@RunWith(Arquillian.class)
public class OperatorTest {

	@Deployment
	public static JavaArchive createDeploy() {
		return ShrinkWrap.create(JavaArchive.class, "OperatorTest.jar")
				.addClasses(Operator.class, OperatorDAO.class, OperatorDTO.class, IOperatorBO.class, OperatorBO.class,
						Contact.class, ContactDTO.class, PersistenceUtil.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}

	@Inject
	private IOperatorBO oBO;

	@Test
	@InSequence(1)
	public void testOne() {
		OperatorDTO o = new OperatorDTO();
		o.setName("TEST1");
		o.setCode(69);
		o.setCategory("TEST1");
		o.setValue(5.00);
		try {
			this.oBO.save(o);
			Assert.assertTrue("Success", true);
		} catch (Exception e) {
			Assert.fail("FAIL!");
		}
	}

	@Test
	@InSequence(2)
	public void testTwo() {
		List<OperatorDTO> list = null;
		try {
			list = this.oBO.listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(list);
	}

}
