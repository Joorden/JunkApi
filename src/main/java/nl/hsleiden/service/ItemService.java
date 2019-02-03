package nl.hsleiden.service;


import nl.hsleiden.model.Item;
import nl.hsleiden.persistence.ItemDAO;

import javax.inject.Inject;
import java.util.List;

public class ItemService {
    private final ItemDAO dao;

    @Inject
    public ItemService(ItemDAO dao)
    {
        this.dao = dao;
    }
    public List<Item> getAll(){ return dao.getAllItems();}
    public void add(Item item){ dao.addItem(item);}
    public void delete(String name){ dao.deleteItem(name);}
    public void update(Item item) {dao.updateItem(item);}
}
