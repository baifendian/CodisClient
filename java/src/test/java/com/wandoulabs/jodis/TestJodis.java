package com.wandoulabs.jodis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;

public class TestJodis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("value="+"aaaa");
		JedisResourcePool jedisPool = new RoundRobinJedisPool("192.168.161.22:2181", 30000, "/zk/codis/db_test/proxy", new JedisPoolConfig(), "businessID");
		Jedis jedis = jedisPool.getResource();
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println("value="+value);
	}

}
