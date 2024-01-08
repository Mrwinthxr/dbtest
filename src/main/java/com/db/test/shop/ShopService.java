package com.db.test.shop;

public interface ShopService {

    Shop createShop(String name);

    Shop addWorkerToShop(int shopId, int workerId);
}
