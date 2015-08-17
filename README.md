# Codis-Client
## 介绍
	codis-client是codis集群的客户端,支持C++,Java,Python

## 环境依赖 (Python)
	1.redis-py-2.4(修复了源码中的一个connection的bug)
	2.zkpython-0.4.2
	3.zookeeper-c
	
## Install java
	cd jodis 
	mvn package

## Install python
```python
	cd zookeeper-3.4.6/src/c/
	./configure
	make
	make install
	cd redis-py-2.4
	python setup.py install
	cd zkpython-0.4.2
	python setup.py install	
```

## C++ Demo
```c++
```

## Python Demo
```python
	import codis_client
	
	#simple example
	codis_client.InitFromZK('127.0.0.1:2181', '/zk/codis/db_test/proxy', "businessID")
	print codis_client.GetProxy().set("kk", "vv")

	#better example
	#if faild, then get other proxy connection
	codis_client.InitFromZK('127.0.0.1:2181', '/zk/codis/db_test/proxy', "businessID")
	try:
		ret = codis_client.GetProxy().set("kk", "vv")
	except redis.exceptions.ConnectionError, e:
		ret = codis_client.GetProxy().set("kk", "vv")

	#best example
	#if faild, reconnect other proxy util success
	codis_client.InitFromZK('127.0.0.1:2181', '/zk/codis/db_test/proxy', "businessID")
	while True:
		try:
			ret = codis_client.GetProxy().set("kk", "vv")
		except redis.exceptions.ConnectionError, e:
			continue
		break
```

## Java Demo
```Java 
	JedisResourcePool jedisPool = new RoundRobinJedisPool("zkserver:2181", 30000, "/zk/codis/db_xxx/proxy", new JedisPoolConfig());
	try (Jedis jedis = jedisPool.getResource()) {
    		jedis.set("foo", "bar");
    		String value = jedis.get("foo");
	}
```	

