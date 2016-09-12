package com.teste.phonebook.test;

import java.time.LocalDateTime;
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

import com.teste.phonebook.bo.ContactBO;
import com.teste.phonebook.bo.IContactBO;
import com.teste.phonebook.dao.ContactDAO;
import com.teste.phonebook.dto.ContactDTO;
import com.teste.phonebook.dto.OperatorDTO;
import com.teste.phonebook.entity.Contact;
import com.teste.phonebook.entity.Operator;
import com.teste.phonebook.util.PersistenceUtil;

@RunWith(Arquillian.class)
public class ContactTest {

	@Deployment
	public static JavaArchive createDeploy() {
		return ShrinkWrap.create(JavaArchive.class, "ContactTest.jar")
				.addClasses(Contact.class, ContactDAO.class, ContactDTO.class, IContactBO.class, ContactBO.class,
						Operator.class, OperatorDTO.class, PersistenceUtil.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}

	@Inject
	private IContactBO cBO;

	@Test
	@InSequence(1)
	public void testOne() {
		ContactDTO c = new ContactDTO();
		c.setSerial("* H/7");
		c.setName("Sandra ELENA");
		c.setPhone("4799993333");
		c.setData(LocalDateTime.now());
		c.setOperator(new OperatorDTO(2L));
		c.setStatus(1);

		try {
			this.cBO.save(c);
			Assert.assertTrue("Success", true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Assert.fail("Fail!");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Test
	@InSequence(2)
	public void testTwo() {
		List<ContactDTO> list = null;
		try {
			list = this.cBO.listAll();
		} catch (Exception e) {
			try {
				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		Assert.assertNotNull(list);
	}
}
