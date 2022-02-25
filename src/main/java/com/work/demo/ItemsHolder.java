package com.work.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ItemsHolder {
    //Recordemos añadir "@autowired" cuando instanciemos un objeto ItemHolder
    private Map<Integer, Items> compra = new ConcurrentHashMap<>();
    private AtomicInteger countId = new AtomicInteger();

    public void add(Items item){
        int aux = countId.incrementAndGet();
        item.setId(aux);
        compra.put(aux, item);
    }

    public boolean isThere(Items item){
        return compra.containsValue(item);
    }

    public boolean delete(int id){
        if (compra.containsKey(id)){
            compra.remove(id, compra.get(id));
            return true;
        }else{
            return false;
        }
    }
}
