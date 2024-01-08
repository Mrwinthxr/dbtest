package com.db.test.controller.shop;

import com.db.test.shop.Shop;
import com.db.test.shop.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/shop")
public class ShopController {

    final ShopService shopService;

    @GetMapping(path = "/create/{name}")
    public ResponseEntity<Shop> createShop(@PathVariable("name") String name) {

        Shop shop = shopService.createShop(name);
        log.info("New shop created: '{}' and info: '{}'", shop.getId(), shop);

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping(path = "/addWorker/{shop}/{worker}")
    public ResponseEntity<Shop> createShop(@PathVariable("shop") int shop,
                                           @PathVariable("worker") int worker) {

        shopService.addWorkerToShop(shop, worker);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
