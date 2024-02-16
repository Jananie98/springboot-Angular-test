package myposapp.demo.service.impl;

import myposapp.demo.dto.ItemDTO;
import myposapp.demo.entity.Item;
import myposapp.demo.repo.ItemRepo;
import myposapp.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//Where we write the logic
public class ItemServiceIMPL implements ItemService {
    // To save in the database
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveItem(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO,Item.class);
        if (!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.toString()+" - "+item.getItemName()+" saved!";
        }else {
            throw new DuplicateKeyException("Item Already Added!");
        }
    }

    @Override
    public List<ItemDTO> getItembyName(String itemname) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemname,true);
        if (items.size()>0){
            // Type token is used ti retreive data at the runtime
            // the reason to use a type token here is that this method outputs a list of times
            // so that the type token create a subclass of the class and store the data
            List<ItemDTO> itemDTOS = modelMapper.map(items,new TypeToken<List<ItemDTO>>(){}.getType());
            return itemDTOS;
        }else{
            throw new RuntimeException("No item Found");
        }
    }

    @Override
    public ItemDTO getItembyId(int itemid) {
        if (itemRepo.existsById(itemid)){
            Item item = itemRepo.getItembyId(itemid);
            ItemDTO itemDTO1 = modelMapper.map(item,ItemDTO.class);
            return itemDTO1;
        }else{
            throw new RuntimeException("No item Found");
        }

    }
}
