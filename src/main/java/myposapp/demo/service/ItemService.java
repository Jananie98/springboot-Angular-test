package myposapp.demo.service;

import myposapp.demo.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ItemService {
    String saveItem(ItemDTO itemDTO);

    List<ItemDTO> getItembyName(String itemname);

    ItemDTO getItembyId(int itemid);
}
