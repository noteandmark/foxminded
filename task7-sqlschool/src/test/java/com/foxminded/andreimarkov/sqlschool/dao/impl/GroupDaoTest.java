package com.foxminded.andreimarkov.sqlschool.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.foxminded.andreimarkov.sqlschool.TestResourseLoader;
import com.foxminded.andreimarkov.sqlschool.dao.ConnectionJdbc;
import com.foxminded.andreimarkov.sqlschool.domain.Group;

class GroupDaoTest {

	private GroupDao groupDao;

	@BeforeAll
	public static void init() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
		TestResourseLoader loader = new TestResourseLoader();
		loader.create("schema.sql");
	}	
	
	@BeforeEach
	public void setUp() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
		groupDao = new GroupDao();
		TestResourseLoader loader = new TestResourseLoader();
		loader.create("customData.sql");
	}

	@Test
	void testCreate_whenGivenNewGroup_thenShouldCreateNewOne() throws SQLException {
		
		Group expected = new Group();
		expected.setGroupName("Some group");
		groupDao.create(expected);

		Group actual = groupDao.read(expected.getGroupId()).get();
		assertEquals(expected, actual);
	}

	@Test
	void testRead_whenGivenGroupId_thenGetGroup() throws SQLException {
		Group expected = new Group();
		expected.setGroupId(1);
		expected.setGroupName("Some group");
		groupDao.create(expected);

		Group actual = groupDao.read(expected.getGroupId()).get();

		assertEquals(expected, actual);
	}

	@Test
	void testGetGroupsWithCountStudent() throws SQLException, IOException {
		Group group1 = new Group(1, "aa-11");
		Group group2 = new Group(2, "bb-22");
		List<Group> expected = new ArrayList<>();
		expected.add(group1);
		expected.add(group2);
		
		List<Group> actual = groupDao.getGroupsWithCountStudent(2);
		
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate() throws SQLException {
		
		Group actual = new Group();
		actual.setGroupName("Some group");
		groupDao.create(actual);
		actual.setGroupName("Updated group");
		groupDao.update(actual);

		Group expected = new Group();
		expected.setGroupId(actual.getGroupId());
		expected.setGroupName("Updated group");

		assertEquals(expected, actual);
	}

	@Test
	void testDelete_whenGivenGroup_thenShouldDeleteThisGroup() throws SQLException {
		Group actual = new Group();
		actual.setGroupName("Group for delete");
		groupDao.create(actual);
		groupDao.delete(actual);

		assertThrows(NoSuchElementException.class, () -> {
			groupDao.read(actual.getGroupId()).get();
		});
	}
	
}
