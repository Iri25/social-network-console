package socialnetwork.repository.memory;

import socialnetwork.domain.Entity;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID,E> {
    private Validator<E> validator;
    protected Map<ID,E> entities;

    /**
     * constructor with parameters
     * @param validator
     */
    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<ID,E>();
    }

    /**
     * findOne
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return the entity with @param id
     */
    @Override
    public E findOne(ID id){
       try{
        if (id == null)
            throw new IllegalArgumentException("Id must be not null!");
        return entities.get(id);
        }
        catch(ValidationException validationException){
           System.out.println(validationException.getLocalizedMessage());
            return entities.get(id);
        }
    }

    /**
     * findAll
     * @return all entities
     */
    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    /**
     * save
     * @param entity
     *         entity must be not null
     * @return @param entity saved
     */
    @Override
    public E save(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must be not null!");
        validator.validate(entity);
        if(entities.get(entity.getId()) != null) {
            return entity;
        }
        else entities.put(entity.getId(),entity);
        return null;
    }

    /**
     * delete
     * @param id
     *      id must be not null
     * @return all entities without the entity with @param id
     */
    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("Id must be not null!");
        if (entities.containsKey(id))
            return entities.remove(id);
        else
            return null;
    }

    /**
     * update
     * @param entity
     *          entity must not be null
     * @return all entities with @param entity updated
     */
    @Override
    public E update(E entity) {

        if(entity == null)
            throw new IllegalArgumentException("Entity must be not null!");
        validator.validate(entity);

        entities.put(entity.getId(), entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(), entity);
            return null;
        }
        return entity;

    }

}
