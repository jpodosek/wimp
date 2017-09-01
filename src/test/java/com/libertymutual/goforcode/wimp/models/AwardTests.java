package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AwardTests {

	private Award award;
	
	@Before
	public void setUp() {
		award = new Award();
	}
	

	@Test
	public void test_Award_constructor_sets_title_organization_year() {	
		//Act
		String title = "Best Picture";
		String organization = "Academy";
		int year = 2008;
		Award award = new Award(title, organization, year);
			
		//Assert
		assertThat(award.getTitle()).isSameAs(title);
		assertThat(award.getOrganization()).isSameAs(organization);
		assertThat(award.getYear()).isEqualTo(year);
	}
	
	@Test
	public void test_setId_and_getId_work() {
		//Arrange
		Long id = 4L;
		award.setId(id);
		//Act
		Long actualId = award.getId();			
		//Assert
		assertThat(actualId).isSameAs(id);
	}
	
	@Test
	public void test_setTitle_and_getTitle_work() {
		//Arrange
		String title = "Best Picture";
		award.setTitle(title);
		//Act
		String actualTitle = award.getTitle();			
		//Assert
		assertThat(actualTitle).isSameAs(title);
	}
	
	@Test
	public void test_setOrganization_and_getOrganization_work() {
		//Arrange
		String org = "Academy of Asshats";
		award.setOrganization(org);
		//Act
		String actualOrg = award.getOrganization();			
		//Assert
		assertThat(actualOrg).isSameAs(org);
	}
	
	@Test
	public void test_setYear_and_getYear_work() {
		//Arrange
		int year = 2004;
		award.setYear(year);
		//Act
		int actualYear = award.getYear();			
		//Assert
		assertThat(actualYear).isEqualTo(year);
	}

}
