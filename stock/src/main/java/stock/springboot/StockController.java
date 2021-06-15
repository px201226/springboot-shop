package stock.springboot;


import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import stock.domain.common.ItemStatusDTO;
import stock.domain.common.ItemType;
import stock.domain.stock.IStockService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stock")
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
