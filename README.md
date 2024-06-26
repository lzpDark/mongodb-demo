# mongodb-demo

this repo is just used to demo mongodb features

#### quick start

1. install mongodb (listen on port 27017, used by test)


#### start with spring-data-mongodb

https://docs.spring.io/spring-data/mongodb/reference/

#### create sharding cluster for testing

basically these 3 steps:
- create config server, initiate
- create several repl clusters, initiate
- create router with config server as param, add repl clusters as shards

ref to: https://gist.github.com/joeytwiddle/34c9b48517004efce0545f8e07388c9f

