package mr.liu.dao;

import java.util.List;

public interface Dao<T> {

	// CRUD sur une table de la base de données
	public T getByID(long id);// select * from T where pk = id;

	public List<T> getAll(); // select * from T;

	public void save(T t);// insert into T values (t)

	public void update(T t, String[] args);// update T set args where id = t.id

	public void delete(T t);// delete from T whetre id = t.id

}
