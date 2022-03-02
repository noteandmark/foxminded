package com.foxminded.andreimarkov.sqlschool.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.given;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.andreimarkov.sqlschool.dao.impl.GroupDao;
import com.foxminded.andreimarkov.sqlschool.domain.Group;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

	@InjectMocks
	private GroupService groupService;
	
	@Mock
	private GroupDao groupDao;
	
	@Test
	void testGetGroupsWithCountStudent_whenGetCount_thenFindNeededGroups() throws SQLException {
		List<Group> groupList = new ArrayList<Group>();
		groupList.add(new Group(1, "aa-11"));
		given(groupDao.getGroupsWithCountStudent(10)).willReturn(groupList);
		List<Group> expected = groupService.getGroupsWithCountStudent("10");
		assertThat(expected,notNullValue());
	}

}
