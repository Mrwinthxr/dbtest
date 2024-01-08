package com.db.test.shop.impl;

import com.db.test.shop.Shop;
import com.db.test.shop.ShopRepository;
import com.db.test.shop.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("shopservice")
public class ShopServiceImpl implements ShopService {

    final ShopRepository shopRepository;

    @Override
    public Shop createShop(String name) {
        Shop shop = Shop.builder().name(name).build();
        return shopRepository.saveAndFlush(shop);
    }

    @Override
    public Shop addWorkerToShop(int shopId, int workerId) {
        Shop shop = shopRepository.findShopById(shopId);
        List<String> currentWorkers = shop.workersAtShop();
        shop = shop.toBuilder()
            .workers(currentWorkers.isEmpty() ? ""+workerId : shop.getWorkers()+","+workerId)
            .build();
        log.info("Worker '{}' added to shop '{}', shop: '{}'", workerId, shopId, shop);
        return shopRepository.saveAndFlush(shop);
    }
}
