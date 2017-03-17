package Repository;

import Domain.HasId;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sergiu on 10/25/2016.
 */
public abstract class RepositoryInMemory<T extends HasId<ID>, ID> implements Repository<T, ID> {

    protected ArrayList<T> entities =  new ArrayList<T>();

    @Override
    public void save(T entity)
    {
        entities.add(entity);
    }

    @Override
    public void delete(ID id)
    {
        Iterator<T> it = entities.iterator();
        while(it.hasNext()) {
            if (it.next().getId().equals(id))
                it.remove();
        }
    }
    @Override
    public void update(ID id, T entity)
    {
        for (T e : entities)
            if (e.getId().equals(id))
            {
                entities.set(entities.indexOf(e), entity);
            }
    }

    @Override
    public T find(ID id)
    {
        for (T entity : entities)
            if (entity.getId() == id)
                return entity;
        return null;
    }

    @Override
    public long getSize()
    {
        return entities.size();
    }

    @Override
    public Iterable<T> getAll()
    {
        return entities;
    }

}
