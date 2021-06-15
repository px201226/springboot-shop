package stock.springboot.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stock.domain.common.ItemStatusDTO;
import stock.domain.common.ItemType;
import stock.domain.exception.business.OutOfStockQuantityException;

/**
* 상품 정보를 관리하는 서비스
* */
@FeignClient(name = "item", url = "http://localhost:8082/item")
public interface IItemRemoteService {

    /**
     * 해당 아이템 재고를 감소시킵니다
     *
     * @param itemType      재고를 줄일 상품의 ItemType
     * @param itemId        재고를 줄일 상품의 id
     * @param quantity      줄일 양
     * @return              재고를 줄인 후 상품의 상태 객체
     * @throws OutOfStockQuantityException  이용 가능한 재고가 즐일 양보다 부족한 경우 예외를 던집니다.
     */
    @PostMapping(consumes = "application/json")
    public ItemStatusDTO removeAvailableStock(@RequestParam("itemType") ItemType itemType,
                                              @RequestParam("itemId")   Long itemId,
                                              @RequestParam("quantity") Long quantity)
                                              throws OutOfStockQuantityException;


    /**
     * 해당 아이템의 상태를 조회합니다.
     *
     * @param itemType      조회할 상품의 ItemType
     * @param itemId        조회할 상품의 id
     * @return              조회할 상품의 상태 객체
     */
    @GetMapping
    public ItemStatusDTO getItemStatus(@RequestParam("itemType")    ItemType itemType,
                                       @RequestParam("itemId")      Long itemId);


    /*
    * 테스트용 메서드
    * @Deprecated 현재 사용되지 않는 메서드입니다
    *  */
    @Deprecated
    @GetMapping
    public ItemStatusDTO addAvailableStock(@RequestParam("itemType") ItemType itemType,
                                           @RequestParam("itemId")   Long itemId,
                                           @RequestParam("quantity") Long quantity);
}
