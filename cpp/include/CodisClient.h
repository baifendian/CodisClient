/**
 * @file
 * @brief
 *
 */

#ifndef CODIS_CLIENT_H
#define CODIS_CLIENT_H

#include <string>
#include <vector>
#include <map>
#include "Reply.h"
#include "Command.h"
#include "RedisClientPool.h"

using namespace std;

namespace bfd
{
namespace codis
{

typedef map<string, string> KVMap;


class CodisClient;
/**
 * @brief mget3
 */
class MgetAsyncRequestContext
{
	// mget过程中，对每个server发起的请求的回调变量
public:
	vector<string> user_keylist_;
	void (*callback)(KVMap& kvs);
	redisAsyncContext** async_context;
	CodisClient *client;
};

class CodisClient
{
public:
	  CodisClient(const string& proxyIP, const int port, const string& businessID);
	  ~CodisClient();
	  void returnAsync(redisAsyncContext* async_context) { m_ConnPool->returnItemAsync(async_context);};

	  /**
	   * @brief key
	   */
	  bool exists(string key);
	  int del(string key);
	  int del(vector<string>& keys);
	  string type(string key);
	  bool expire(string key, int seconds);

	  /**
	   * @brief string
	   */
	  bool set(string key, string value);
	  bool setnx(string key, string value);
	  bool setex(string key, string value, int seconds);
	  string get(string key);
	  string getset(string key, string value);
	  int setbit(string key, int index, bool value);
	  int getbit(string key, int index);
	  int bitcount(string key);

	  /**
	   * @brief 同步
	   */
	  vector<string> mget(vector<string>& keys);

	  /**
	   * @brief 全异步, 不支持多线程调用.异步执行命令,等所有命令都返回结果后, kvs.finish=true
	   */
	  bool mget2(vector<string>& keys, void (*callback)(KVMap& kvs));

	  bool mset(map<string, string>& keyvalues);
	  int incr(string key);
	  int decr(string key);
	  int incrby(string key, int incr);
	  int decrby(string key, int incr);
	  long append(string key, string value);

	  /**
	   * @brief list
	   */
	  int lpush(string key, string value);
	  int rpush(string key, string value);
	  int lpush(string key, vector<string> values);
	  int rpush(string key, vector<string> values);
	  int llen(string key);
	  vector<string> lrange(string key, int start, int end);
	  bool ltrim(string key, int start, int end);
	  bool lset(string key, int index, string value);
	  bool lrem(string key, int count, string value);
	  string lpop(string key);
	  string rpop(string key);

	  /**
	   * @brief set
	   */
	  bool sadd(string key, string member);
	  int sadd(string key, vector<string> members);
	  bool srem(string key, string member);
	  string spop(string key);
	  string srandmember(string key);
	  int scard(string key);
	  bool sismember(string key, string member);
	  vector<string> smembers(string key);

	  /**
	   * @brief sorted set
	   */
	  bool zadd(string key, int score, string member);
	  bool zrem(string key, string member);
	  int zincrby(string key, int incr, string member);
	  int zrank(string key, string member);
	  int zrevrank(string key, string member);
	  vector<string> zrange(string key, int start, int end);
	  vector<string> zrevrange(string key, int start, int end);
	  vector<string> zrangebyscore(string key, int min, int max);
	  int zcount(string key, int min, int max);
	  int zcard(string key);
	  int zscore(string key, string member);
	  int zremrangebyrank(string key, int min, int max);
	  int zremrangebyscore(string key, int min, int max);

	  /**
	   * @brief hash
	   */
	  bool hset(string key, string field, string value);
	  string hget(string key, string field);
	  vector<string> hmget(string key, vector<string>& field);
	  bool hmset(string key, vector<string>& fields, vector<string>& values);
	  int hincrby(string key, string field, int incr);
	  bool hexists(string key, string field);
	  bool hdel(string key, string field);
	  int hlen(string key);
	  vector<string> hkeys(string key);
	  vector<string> hvals(string key);
	  bool hgetall(string key, vector<string>& fields, vector<string>& values);

	  /**
	   * @brief command
	   */
	  Reply RedisCommand(const vector<string>& command);
	  Reply RedisCommand(Command& command);
	  vector<Reply> RedisCommands(vector<Command>& commands);

private:
	  aeEventLoop *m_Loop;
	  RedisClientPool *m_ConnPool;
	  string m_BID;


private:
	  static void* AEThread(void *arg);
	  static void mget2Callback(redisAsyncContext *c, void *r, void *privdata);
};

}
}

#endif
