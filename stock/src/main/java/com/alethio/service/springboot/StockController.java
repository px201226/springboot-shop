package com.alethio.service.springboot;



import com.alethio.service.common.ItemStatusDTO;
import com.alethio.service.common.ItemType;
import com.alethio.service.domain.stock.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/com/alethio/service")
public class StockController {

    private final IStockService stockService;

    @PostMapping
    public ItemStatusDTO placeOrder(@RequestParam ItemType itemType, @RequestParam Long itemId, @RequestParam Long quantity){
        ItemStatusDTO itemStatusDTO = stockService.placeOrder(itemType, itemId, quantity);
        return itemStatusDTO;
    }


    @GetMapping
    public ItemStatusDTO getItemStatus(@RequestParam("itemType") ItemType itemType, @RequestParam("itemId") Long itemId){
        return stockService.getItemStatus(itemType,itemId);
    }
}
