# dbtest

Simple Spring application for generating workers, shops and allocating shifts at shops for workers.
made with spring, maven, lombok, h2 inmemory db and spring web

create user/worker:
curl localhost:8080/worker/create/{name}

name: string

create a shop:
curl localhost:8080/shop/create/{name}

name: string

add worker to shop:
curl localhost:8080/shop/addWorker/{shop}/{worker}

shop: int // Id of shop 
worker: int // Id of worker

add worker to shift at shop:
curl localhost:8080/shift/create/{shop}/{worker}/{hours}

shop: int // Id of shop 
worker: int // Id of worker
hours: int // hours a given shift should be
