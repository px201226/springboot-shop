package com.alethio.service.sprignboot;


import com.alethio.service.common.ItemStatusDTO;
import com.alethio.service.common.ItemType;
import com.alethio.service.domain.item.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("com/alethio/service")
public class ItemController {

    private final IItemService iItemService;

    @PostMapping
    public ItemStatusDTO removeAvailableStock(@RequestParam("itemType") ItemType itemType,
                                              @RequestParam("itemId")   Long itemId,
                                              @RequestParam("quantity") Long quantity){

        return iItemService.removeAvailableStock(itemType,itemId,quantity);
    }
}



