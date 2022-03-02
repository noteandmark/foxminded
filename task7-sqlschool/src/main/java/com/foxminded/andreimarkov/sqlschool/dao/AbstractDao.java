package com.foxminded.andreimarkov.sqlschool.dao;

import java.sql.SQLException;
import java.util.Optional;

public abstract class AbstractDao<T> {
	protected abstract T create (T t) throws SQLException;
	protected abstract Optional<T> read (int t) throws SQLException;
	protected abstract T update (T t) throws SQLException;
	protected abstract void delete (T t) throws SQLException;
}
