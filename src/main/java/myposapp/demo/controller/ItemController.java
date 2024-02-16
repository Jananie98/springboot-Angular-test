package myposapp.demo.controller;

import myposapp.demo.dto.ItemDTO;
import myposapp.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/vi/item")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save")
    public String saveItem(@RequestBody ItemDTO itemDTO){
        String message =  itemService.saveItem(itemDTO);
        return "Saved";
    }

    @GetMapping(
            path = "getbyname",
            params = "name"
    )
    public List<ItemDTO> getItembyName(@RequestParam(value = "name") String itemname){
        List<ItemDTO> itemDTOS = itemService.getItembyName(itemname);
        return itemDTOS;
    }

    @GetMapping(path = "getbyId",params = "id")
    public ItemDTO getItembyId(@RequestParam(value = "id") int itemid){
        ItemDTO itemDTO1 = itemService.getItembyId(itemid);
        return itemDTO1;
    }
}
