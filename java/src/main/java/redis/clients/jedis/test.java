package redis.clients.jedis;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
public class test {
	public test(Jedis j)
	{
		this.m_jedis=j;
	}
	private Jedis m_jedis;
	public void settest(){
		String ret=m_jedis.set("k1","v1");
		if(!ret.equals("OK")){
			System.out.println("set failed");}
		else{
			System.out.println("set succeeded");}
	}
	public void setbytetest(){
		String ret=m_jedis.set("k2".getBytes(),"v2".getBytes());
		//m_jedis.set("k2","v2");
		if(!ret.equals("OK")){
			System.out.println("setbyte failed");}
		else{
			System.out.println("setbyte succeeded");}
	}
	public void gettest(){
		String ret =m_jedis.get("k1");
		if(!ret.equals("v1")){
			System.out.println("get failed");}
		else{
			System.out.println("get succeeded");}
	}
	public void getbytetest(){
		byte[] ret =m_jedis.get("k1".getBytes());
		if(!new String(ret).equals("v1")){
			System.out.println("getbyte failed");}
		else{
			System.out.println("getbyte succeeded");}
	}
	public void deltest(){
		m_jedis.set("delkey1", "delvalue1");
		m_jedis.set("delkey2", "delvalue2");
		m_jedis.set("delkey3", "delvalue3");
		Long ret1 =m_jedis.del("delkey1");
		Long ret2 =m_jedis.del("delkey2","delkey3");
		//System.out.println(ret2);
		if(ret1!=1){
			System.out.println("del failed");}
		else{
			System.out.println("del succeeded");}
		if(ret2!=1){
			System.out.println("delmul failed");}
		else{
			System.out.println("delmul succeeded");}
	}
	public void delbytetest(){
		m_jedis.set("delbytekey1", "delvalue1");
		m_jedis.set("delbytekey2", "delvalue2");
		m_jedis.set("delbytekey3", "delvalue3");
		byte[][] delarray=new byte[2][];
		delarray[0]="delbytekey2".getBytes();
		delarray[1]="delbytekey3".getBytes();
		Long ret1 =m_jedis.del("delbytekey1".getBytes());
		Long ret2 =m_jedis.del("delbytekey2".getBytes(),"delbytekey3".getBytes());
		if(ret1!=1){
			System.out.println("delbyte failed");}
		else{
			System.out.println("delbyte succeeded");}
		if(ret2!=2){
			System.out.println("delmulbyte failed");}
		else{
			System.out.println("delmulbyte succeeded");}
	}
	public void typetest(){
		String ret =m_jedis.type("k1");
		if(!ret.equals("string")){
			System.out.println("type failed");}
		else{
			System.out.println("type succeeded");}
	}
	public void typebytetest(){
		String ret =m_jedis.type("k1".getBytes());
		if(ret.equals("string")){
			System.out.println("typebyte succeeded");}
		else{
			System.out.println("typebyte failed");}
	}
	public void setnxtest(){
		Long ret =m_jedis.setnx("k5", "v5");
		if(ret==1){
			System.out.println("setnx succeeded");}
		else{
			System.out.println("setnx failed");}
	}
	public void setnxbytetest(){
		Long ret =m_jedis.setnx("k6".getBytes(), "v6".getBytes());
		if(ret==1){
			System.out.println("setnxbyte succeeded");}
		else{
			System.out.println("setnxbyte failed");}
	}
	public void getsettest(){
		String ret =m_jedis.getSet("k6", "v66");
		if(ret.equals("v6")){
			System.out.println("getset succeeded");}
		else{
			System.out.println("getset failed");}
	}
//	public void getsetbytetest(){
//		String ret =m_jedis.getSet("k6".getBytes(), "v666".getBytes());
//		System.out.println(ret);
//	}
	public void mgettest(){
		List<String> ret;
		ret=m_jedis.mget("k1","k2");
		if(ret.size()==2){
			System.out.println("mget succeeded");}
		else{
			System.out.println("mget failed");}
	}
	public void mgetbytetest(){
		List<byte[]> ret;
		ret=m_jedis.mget("k1".getBytes(),"k2".getBytes());
		if(ret.size()==2){
			System.out.println("mgetbyte succeeded");}
		else{
			System.out.println("mgetbyte failed");}
	}
	public void msettest(){
		String[] src =new String[4];
		src[0]="k7";src[1]="v7";src[2]="k8";src[3]="v8";
		String ret=m_jedis.mset(src);
		if(ret.equals("OK")){
			System.out.println("mset succeeded");}
		else{
			System.out.println("mset failed");}
	}
	public void msetbytetest(){
		String ret=m_jedis.mset("k9".getBytes(),"v9".getBytes(),"k10".getBytes(),"v10".getBytes());
		if(ret.equals("OK")){
			System.out.println("msetbyte succeeded");}
		else{
			System.out.println("msetbyte failed");}
	}
	public void msetnxtest(){
		String[] src =new String[4];
		src[0]="k11";src[1]="v11";src[2]="k12";src[3]="v12";
		Long ret=m_jedis.msetnx(src);
		if(ret==1){
			System.out.println("msetnx succeeded");}
		else{
			System.out.println("msetnx failed");}
	}
	public void msetnxbytetest(){
		Long ret=m_jedis.msetnx("k13".getBytes(),"v13".getBytes(),"k14".getBytes(),"v14".getBytes());
		if(ret==1){
			System.out.println("msetnxbyte succeeded");}
		else{
			System.out.println("msetnxbyte failed");}
	}
	public void incrtest(){
		Long ret =m_jedis.incr("k15");
		if(ret==1){
			System.out.println("incr succeeded");}
		else{
			System.out.println("incr failed");}
	}
	public void incrbytetest(){
		m_jedis.set("k15", "1");
		Long ret =m_jedis.incr("k15".getBytes());
		if(ret==2){
			System.out.println("incrbyte succeeded");}
		else{
			System.out.println("incrbyte failed");}
	}
	public void decrtest(){
		m_jedis.set("k15","2");
		Long ret =m_jedis.decr("k15");
		if(ret==1){
			System.out.println("decr succeeded");}
		else{
			System.out.println("decr failed");}
	}
	public void decrbytetest(){
		m_jedis.set("k15", "1");
		Long ret =m_jedis.decr("k15".getBytes());
		//System.out.println(ret);
		if(ret==0){
			System.out.println("decrbyte succeeded");}
		else{
			System.out.println("decrbyte failed");}
	}
	public void incrbytest(){
		m_jedis.set("k15", "0");
		Long ret=m_jedis.incrBy("k15", 5);
		if(ret==5){
			System.out.println("incrby succeeded");}
		else{
			System.out.println("incrby failed");}
	}
	public void incrbybytetest(){
		m_jedis.set("k15", "5");
		Long ret=m_jedis.incrBy("k15".getBytes(), 5);
		if(ret==10){
			System.out.println("incrbybyte succeeded");}
		else{
			System.out.println("incrbybyte failed");}
	}
	public void decrbytest(){
		m_jedis.set("k15", "10");
		Long ret=m_jedis.decrBy("k15", 5);
		if(ret==5){
			System.out.println("decrby succeeded");}
		else{
			System.out.println("decrby failed");}
	}
	public void decrbybytetest(){
		m_jedis.set("k15", "5");
		Long ret=m_jedis.decrBy("k15".getBytes(), 5);
		if(ret==0){
			System.out.println("decrbybyte succeeded");}
		else{
			System.out.println("decrbybyte failed");}
	}
	public void appendtest(){
		m_jedis.set("k15", "1");
		Long ret =m_jedis.append("k15", "1");
		if(ret==2){
			System.out.println("append succeeded");}
		else{
			System.out.println("append failed");}
	}
	public void appendbytetest(){
		m_jedis.set("k15", "1");
		Long ret =m_jedis.append("k15".getBytes(), "1".getBytes());
		if(ret==2){
			System.out.println("appendbyte succeeded");}
		else{
			System.out.println("appendbyte failed");}
	}
	public void lpushtest(){
		Long ret =m_jedis.lpush("listtest", "a","b");
		if(ret==2){
			System.out.println("lpush succeeded");}
		else{
			System.out.println("lpush failed");}
		m_jedis.lpop("listtest");
		m_jedis.lpop("listtest");
	}
	public void lpushbytetest(){
		Long ret=m_jedis.lpush("listtest".getBytes(), "c".getBytes(),"d".getBytes());
		if(ret==2){
			System.out.println("lpushbyte succeeded");}
		else{
			System.out.println("lpushbyte failed");}
		m_jedis.lpop("listtest");
		m_jedis.lpop("listtest");
	}
	public void rpushtest(){
		Long ret =m_jedis.rpush("listtest", "e","f");
		if(ret==2){
			System.out.println("rpush succeeded");}
		else{
			System.out.println("rpush failed");}
		m_jedis.lpop("listtest");
		m_jedis.lpop("listtest");
	}
	public void rpushbytetest(){
		Long ret=m_jedis.rpush("listtest".getBytes(), "g".getBytes(),"h".getBytes());
		if(ret==2){
			System.out.println("rpushbyte succeeded");}
		else{
			System.out.println("rpushbyte failed");}
		m_jedis.lpop("listtest");
		m_jedis.lpop("listtest");
	}
	public void lpushxtest(){
		m_jedis.lpush("listtest", "a");
//		Long ret =m_jedis.lpushx("listtest","i","j");
		Long ret =m_jedis.lpushx("listtest","i");
		//Long ret1=m_jedis.llen("listtest");
		//List<String> ret2=m_jedis.lrange("listtest", 0, -1);
		//System.out.println(ret);
		//System.out.println(ret1);
		//for(String s :ret2)
		//	System.out.println(s);
		if(ret==m_jedis.llen("listtest")){
			System.out.println("lpushx succeeded");}
		else{
			System.out.println("lpushx failed");}
		//m_jedis.lpop("listtest");
		//m_jedis.lpop("listtest");
	}
	public void lpushxbytetest(){
		Long ret=m_jedis.lpushx("listtest".getBytes(), "k".getBytes());
		if(ret==m_jedis.llen("listtest")){
			System.out.println("lpushxbyte succeeded");}
		else{
			System.out.println("lpushxbyte failed");}
		m_jedis.lpop("listtest");
		m_jedis.lpop("listtest");
	}
	public void rpushxtest(){
		Long ret =m_jedis.rpushx("listtest", "m");
		if(ret==m_jedis.llen("listtest")){
			System.out.println("rpushx succeeded");}
		else{
			System.out.println("rpushx failed");}
		m_jedis.lpop("listtest");
		m_jedis.lpop("listtest");
	}
	public void rpushxbytetest(){
		Long ret=m_jedis.rpushx("listtest".getBytes(), "o".getBytes());
		if(ret==m_jedis.llen("listtest")){
			System.out.println("rpushxbyte succeeded");}
		else{
			System.out.println("rpushxbyte failed");}
		m_jedis.lpop("listtest");
		m_jedis.lpop("listtest");
	}
	public void llentest(){
		Long ret=m_jedis.llen("listtest");
		if(ret==2){
			System.out.println("llen succeeded");}
		else{
			System.out.println("llen failed");}
	}
	public void llenbytetest(){
		Long ret=m_jedis.llen("listtest".getBytes());
		if(ret==2){
			System.out.println("llenbyte succeeded");}
		else{
			System.out.println("llenbyte failed");}
	}
	public void lrangetest(){
		List<String> ret;
		ret=m_jedis.lrange("listtest", 0, -1);
		if(ret.size()==2){
			System.out.println("lrange succeeded");}
		else{
			System.out.println("lrange failed");}
	}
	public void lrangebytetest(){
		List<byte[]> ret;
		ret=m_jedis.lrange("listtest".getBytes(), 0, -1);
		if(ret.size()==2){
			System.out.println("lrangebyte succeeded");}
		else{
			System.out.println("lrangebyte failed");}
	}
	public void ltrimtest(){
		String ret =m_jedis.ltrim("listtest", 0, -1);
		if(ret.equals("OK")){
			System.out.println("ltrim succeeded");}
		else{
			System.out.println("ltrim failed");}
	}
	public void ltrimbytetest(){
		String ret =m_jedis.ltrim("listtest".getBytes(), 0, -1);
		if(ret.equals("OK")){
			System.out.println("ltrimbyte succeeded");}
		else{
			System.out.println("ltrimbyte failed");}
	}
	public void lsettest(){
		String ret =m_jedis.lset("listtest", 0, "bset");
		if(ret.equals("OK")){
			System.out.println("lset succeeded");}
		else{
			System.out.println("lset failed");}
	}
	public void lsetbytetest(){
		String ret =m_jedis.lset("listtest".getBytes(), 0, "bbyteset".getBytes());
		if(ret.equals("OK")){
			System.out.println("lsetbyte succeeded");}
		else{
			System.out.println("lsetbyte failed");}
	}
	public void lremtest(){
		m_jedis.lpush("listtest", "d");
		Long ret =m_jedis.lrem("listtest", 0, "d");
		if(ret==1){
			System.out.println("lrem succeeded");}
		else{
			System.out.println("lrem failed");}
	}
	public void lrembytetest(){
		m_jedis.lpush("listtest", "e");
		Long ret =m_jedis.lrem("listtest".getBytes(), 0, "e".getBytes());
		if(ret==1){
			System.out.println("lrembyte succeeded");}
		else{
			System.out.println("lrembyte failed");}
	}
	public void lpoptest(){
		m_jedis.lpush("listtest", "a");
		String ret =m_jedis.lpop("listtest");
		if(ret.equals("a")){
			System.out.println("lpop succeeded");}
		else{
			System.out.println("lpop failed");}
	}
	public void lpopbytetest(){
		m_jedis.lpush("listtest", "a");
		byte[] ret =m_jedis.lpop("listtest".getBytes());
		if(new String(ret).equals("a")){
			System.out.println("lpopbyte succeeded");}
		else{
			System.out.println("lpopbyte failed");}
	}
	public void rpoptest(){
		m_jedis.rpush("listtest", "r");
		String ret =m_jedis.rpop("listtest");
		if(ret.equals("r")){
			System.out.println("rpop succeeded");}
		else{
			System.out.println("rpop failed");}
	}
	public void rpopbytetest(){
		m_jedis.rpush("listtest", "r");
		byte[] ret =m_jedis.rpop("listtest".getBytes());
		if(new String(ret).equals("r")){
			System.out.println("rpopbyte succeeded");}
		else{
			System.out.println("rpopbyte failed");}
	}
	public void saddtest(){
		
		Long ret=m_jedis.sadd("settest", "a","b");
		if(ret==2){
			System.out.println("sadd succeeded");}
		else{
			System.out.println("sadd failed");}
	}
	public void saddbytetest(){
		Long ret=m_jedis.sadd("settest".getBytes(), "c".getBytes(),"d".getBytes());
		if(ret==2){
			System.out.println("saddbyte succeeded");}
		else{
			System.out.println("saddbyte failed");}
	}
	public void sremtest(){
		m_jedis.sadd("settest", "a","b");
		Long ret=m_jedis.srem("settest", "a","b");
		if(ret==2){
			System.out.println("srem succeeded");}
		else{
			System.out.println("srem failed");}
	}
	public void srembytetest(){
		m_jedis.sadd("settest","c","d");
		Long ret=m_jedis.srem("settest".getBytes(), "c".getBytes(),"d".getBytes());
		if(ret==2){
			System.out.println("srembyte succeeded");}
		else{
			System.out.println("srembyte failed");}
	}
	public void srandmembertest(){
		m_jedis.sadd("settest", "a","b");
		String ret=m_jedis.srandmember("settest");
		System.out.println(ret);
	}
	public void srandmemberbytetest(){
		byte[] ret=m_jedis.srandmember("settest".getBytes());
		System.out.println(new String(ret));
	}
	public void srandmemcountbertest(){
		List<String> ret=m_jedis.srandmember("settest",2);
		for(String s:ret)
		System.out.println(s);
	}
	public void srandmembecountrbytetest(){
		List<byte[]> ret=m_jedis.srandmember("settest".getBytes(),2);
		System.out.println(ret);
		for(byte[] s :ret)
			//for(byte b:s)
			System.out.println(new String(s));
	}
	public void scardtest(){
		Long ret=m_jedis.scard("settest");
		if(ret==2){
			System.out.println("scard succeeded");}
		else{
			System.out.println("scard failed");}
	}
	public void scardbytetest(){
		Long ret=m_jedis.scard("settest".getBytes());
		if(ret==2){
			System.out.println("scardbyte succeeded");}
		else{
			System.out.println("scardbyte failed");}
	}
	public void sismembertest(){
		m_jedis.sadd("settest", "c");
		boolean ret=m_jedis.sismember("settest", "c");
		if(ret){
			System.out.println("sismember succeeded");}
		else{
			System.out.println("sismember failed");}
	}
	public void sismemberbytetest(){
		m_jedis.sadd("settest", "c");
		boolean ret=m_jedis.sismember("settest".getBytes(), "c".getBytes());
		if(ret){
			System.out.println("sismember succeeded");}
		else{
			System.out.println("sismember failed");}
	}
	public void smemberstest(){
		Set<String> ret=m_jedis.smembers("settest");
		for(String s :ret)
			System.out.println(s);
	}
	public void smembersbytetest(){
		Set<byte[]> ret=m_jedis.smembers("settest".getBytes());
		for(byte[] s :ret)
			//for(byte b:ret)
			System.out.println(new String(s));
	}
	public void zaddtest(){
		Long ret=m_jedis.zadd("myzsettest", 1,"a");
		if(1!=ret){
			System.out.println("zadd failed");}
		else{
			System.out.println("zadd succeeded");}
	}
	public void zaddbytetest(){
		Long ret=m_jedis.zadd("myzsettest".getBytes(), 2,"b".getBytes());
		if(1!=ret){
			System.out.println("zaddbyte failed");}
		else{
			System.out.println("zaddbyte succeeded");}
	}
	public void zaddmapbytetest(){
		Map<byte[],Double> forzset =new HashMap<byte[],Double>();
		forzset.put("abc".getBytes(),3.0);
		Long ret=m_jedis.zadd("myzsettest".getBytes(), forzset);
		System.out.println(ret);
		if(1!=ret){
			System.out.println("zaddmapbyte failed");}
		else{
			System.out.println("zaddmapbyte succeeded");}
		m_jedis.zrem("myzsettest", "abc");
	}
	public void zaddmaptest(){
		Map<String,Double> forzset =new HashMap<String,Double>();
		forzset.put("def",4.0);
		Long ret=m_jedis.zadd("myzsettest", forzset);
		if(1!=ret){
			System.out.println("zaddmap failed");}
		else{
			System.out.println("zaddmap succeeded");}
		m_jedis.zrem("myzsettest", "def");
	}
	public void zremtest(){
		m_jedis.zadd("myzsettest",5, "f");
		Long ret=m_jedis.zrem("myzsettest", "f");
		if(1!=ret){
			System.out.println("zrem failed");}
		else{
			System.out.println("zrem succeeded");}
	}
	public void zrembytetest(){
		m_jedis.zadd("myzsettest",5, "f");
		Long ret=m_jedis.zrem("myzsettest".getBytes(), "f".getBytes());
		if(1!=ret){
			System.out.println("zrembyte failed");}
		else{
			System.out.println("zrembyte succeeded");}
	}
	public void zincrbytest(){
		Double ret=m_jedis.zincrby("myzsettest", 1, "zincr");
		//m_jedis.zincrby("myzsettest",-2,"a");
		if(1!=ret){
			System.out.println("zincrby failed");}
		else{
			System.out.println("zincrby succeeded");}
	}
	public void zincrbybytetest(){
		Double ret=m_jedis.zincrby("myzsettest".getBytes(), 1, "zincrbyte".getBytes());
		m_jedis.zincrby("myzsettest",-2,"a");
		if(1!=ret){
			System.out.println("zincrbybyte failed");}
		else{
			System.out.println("zincrbybyte succeeded");}
	}
	public void zranktest(){
		Long ret=m_jedis.zrank("myzsettest", "a");
		if(0!=ret){
			System.out.println("zrank failed");}
		else{
			System.out.println("zrank succeeded");}
	}
	public void zrankbytetest(){
		Long ret=m_jedis.zrank("myzsettest".getBytes(), "a".getBytes());
		if(0!=ret){
			System.out.println("zrankbyte failed");}
		else{
			System.out.println("zrankbyte succeeded");}
	}
	public void zrevranktest(){
		Long ret=m_jedis.zrevrank("myzsettest".getBytes(), "a".getBytes());
		if(3!=ret){
			System.out.println("zrevrank failed");}
		else{
			System.out.println("zrevrank succeeded");}
	}
	public void zrevrankbytetest(){
		Long ret=m_jedis.zrevrank("myzsettest".getBytes(), "a".getBytes());
		if(3!=ret){
			System.out.println("zrevrankbyte failed");}
		else{
			System.out.println("zrevrankbyte succeeded");}
	}
	public void zrangetest(){
		Set<String> ret=m_jedis.zrange("myzsettest", 0, -1);
		if(4!=ret.size()){
			System.out.println("zrange failed");}
		else{
			System.out.println("zrange succeeded");}
	}
	public void zrangebytetest(){
		Set<byte[]> ret=m_jedis.zrange("myzsettest".getBytes(), 0, -1);
		if(4!=ret.size()){
			System.out.println("zrangebyte failed");}
		else{
			System.out.println("zrangebyte succeeded");}
	}
	public void zrevrangetest(){
		Set<String> ret=m_jedis.zrevrange("myzsettest", 0, -1);
		if(4!=ret.size()){
			System.out.println("zrevrange failed");}
		else{
			System.out.println("zrevrange succeeded");}
	}
	public void zrevrangebytetest(){
		Set<byte[]> ret=m_jedis.zrevrange("myzsettest".getBytes(), 0, -1);
		if(4!=ret.size()){
			System.out.println("zrevrangebyte failed");}
		else{
			System.out.println("zrevrangebyte succeeded");}
	}
	public void zrangebyscoretest(){
		Set<String> ret =m_jedis.zrangeByScore("myzsettest", 0, 10);
		if(4!=ret.size()){
			System.out.println("zrangebyscore failed");}
		else{
			System.out.println("zrangebyscore succeeded");}
	}
	public void zrangebyscorebytetest(){
		Set<byte[]> ret =m_jedis.zrangeByScore("myzsettest".getBytes(), 0, 10);
		if(4!=ret.size()){
			System.out.println("zrangebyscorebyte failed");}
		else{
			System.out.println("zrangebyscorebyte succeeded");}
	}
	public void zcounttest(){
		Long ret=m_jedis.zcount("myzsettest", 0, 3);
		//System.out.println(ret);
		if(4!=ret){
			System.out.println("zcount failed");}
		else{
			System.out.println("zcount succeeded");}
	}
	public void zcountbytetest(){
		Long ret=m_jedis.zcount("myzsettest".getBytes(), 0, 3);
		if(4!=ret){
			System.out.println("zcountbyte failed");}
		else{
			System.out.println("zcountbyte succeeded");}
	}
	public void zcardtest(){
		Long ret=m_jedis.zcard("myzsettest");
		if(4!=ret){
			System.out.println("zcard failed");}
		else{
			System.out.println("zcard succeeded");}
	}
	public void zcardbytetest(){
		Long ret=m_jedis.zcard("myzsettest".getBytes());
		if(4!=ret){
			System.out.println("zcardbyte failed");}
		else{
			System.out.println("zcardbyte succeeded");}
	}
	public void zscoretest(){
		Double ret=m_jedis.zscore("myzsettest","b");
		if(2!=ret){
			System.out.println("zscore failed");}
		else{
			System.out.println("zscore succeeded");}
	}
	public void zscorebytetest(){
		Double ret=m_jedis.zscore("myzsettest".getBytes(),"b".getBytes());
		if(2!=ret){
			System.out.println("zscore failed");}
		else{
			System.out.println("zscore succeeded");}
	}
	public void zremrangebyranktest(){
		Long ret=m_jedis.zremrangeByRank("myzsettest",0,0);
		if(1!=ret){
			System.out.println("zscore failed");}
		else{
			System.out.println("zscore succeeded");}
	}
	public void zremrangebyrankbytetest(){
		Long ret=m_jedis.zremrangeByRank("myzsettest",0,0);
		if(1!=ret){
			System.out.println("zremrangebyrank failed");}
		else{
			System.out.println("zremrangebyrank succeeded");}
	}
	public void zremrangebyscoretest(){
		m_jedis.zadd("myzsettest", 1,"a");
		Long ret=m_jedis.zremrangeByScore("myzsettest",0,2);
		if(1!=ret){
			System.out.println("zremrangebyscore failed");}
		else{
			System.out.println("zremrangebyscore succeeded");}
	}
	public void zremrangebyscorebytetest(){
		m_jedis.zadd("myzsettest", 2,"b");
		Long ret=m_jedis.zremrangeByScore("myzsettest".getBytes(),0,2);
		if(1!=ret){
			System.out.println("zremrangebyscorebyte failed");}
		else{
			System.out.println("zremrangebyscorebyte succeeded");}
	}
	public void hsettest(){
		Long ret=m_jedis.hset("myhtest","name1", "1");
		if(1!=ret){
			System.out.println("hset failed");}
		else{
			System.out.println("hset succeeded");}
	}
	public void hsetbytetest(){
		Long ret=m_jedis.hset("myhtest".getBytes(),"name2".getBytes(), "2".getBytes());
		if(1!=ret){
			System.out.println("hset failed");}
		else{
			System.out.println("hset succeeded");}
	}
	public void hgettest(){
		String ret=m_jedis.hget("myhtest","name2");
		if(!ret.equals("2")){
			System.out.println("hget failed");}
		else{
			System.out.println("hget succeeded");}
	}
	public void hgetbytetest(){
		byte[] ret=m_jedis.hget("myhtest".getBytes(),"name2".getBytes());
		if(new String(ret).equals("2")){
			System.out.println("hget failed");}
		else{
			System.out.println("hget succeeded");}
	}
	public void hmsettest(){
		m_jedis.hdel("myhtest", "name3");
		Map<String,String> forhash =new HashMap<String,String>();
		forhash.put("name3", "3");
		String ret=m_jedis.hmset("myhtest",forhash);
		//System.out.println(ret);
		if(!ret.equals("OK")){
			System.out.println("hmset failed");}
		else{
			System.out.println("hmset succeeded");}
	}
	public void hmsetbytetest(){
		m_jedis.hdel("myhtest", "name4");
		Map<byte[],byte[]> forhash =new HashMap<byte[],byte[]>();
		forhash.put("name4".getBytes(), "4".getBytes());
		String ret=m_jedis.hmset("myhtest".getBytes(),forhash);
		if(!ret.equals("OK")){
			System.out.println("hmsetbyte failed");}
		else{
			System.out.println("hmsetbyte succeeded");}
	}
	public void hmgettest(){
		List<String> ret=m_jedis.hmget("myhtest","name1","name2");
		if(2!=ret.size()){
			System.out.println("hmget failed");}
		else{
			System.out.println("hmget succeeded");}
	}
	public void hmgetbytetest(){
		List<byte[]> ret=m_jedis.hmget("myhtest".getBytes(),"name1".getBytes(),"name2".getBytes());
		if(2!=ret.size()){
			System.out.println("hmget failed");}
		else{
			System.out.println("hmget succeeded");}
	}
	public void hincrbytest(){
		m_jedis.hset("myhtest", "name1", "1");
		Long ret=m_jedis.hincrBy("myhtest", "name1", 2);
		if(3!=ret){
			System.out.println("hincrby failed");}
		else{
			System.out.println("hincrby succeeded");}
	}
	public void hincrbybyteteby(){
		m_jedis.hset("myhtest", "name1", "3");
		Long ret=m_jedis.hincrBy("myhtest".getBytes(), "name1".getBytes(), 2);
		if(5!=ret){
			System.out.println("hincrbyteby failed");}
		else{
			System.out.println("hincrbyteby succeeded");}
	}
	public void hexiststest(){
		boolean ret=m_jedis.hexists("myhtest", "name1");
		if(true!=ret){
			System.out.println("hexists failed");}
		else{
			System.out.println("hexists succeeded");}
	}
	public void hexistsbytetest(){
		boolean ret=m_jedis.hexists("myhtest".getBytes(), "name1".getBytes());
		if(true!=ret){
			System.out.println("hexistsbyte failed");}
		else{
			System.out.println("hexistsbyte succeeded");}
	}
	public void hdeltest(){
		Long ret=m_jedis.hdel("myhtest", "name1");
		if(1!=ret){
			System.out.println("hdel failed");}
		else{
			System.out.println("hdel succeeded");}
		m_jedis.hset("myhtest", "name1", "1");
	}
	public void hdelbytetest(){
		Long ret=m_jedis.hdel("myhtest".getBytes(), "name1".getBytes());
		if(1!=ret){
			System.out.println("hdelbyte failed");}
		else{
			System.out.println("hdelbyte succeeded");}
		m_jedis.hset("myhtest", "name1", "1");
	}
	public void hlentest(){
		Long ret=m_jedis.hlen("myhtest");
		if(5!=ret){
			System.out.println("hlen failed");}
		else{
			System.out.println("hlen succeeded");}
	}
	public void hlenbytetest(){
		Long ret=m_jedis.hlen("myhtest".getBytes());
		if(5!=ret){
			System.out.println("hlenbyte failed");}
		else{
			System.out.println("hlenbyte succeeded");}
	}
	public void hkeystest(){
		Set<String> ret=m_jedis.hkeys("myhtest");
		if(5!=ret.size()){
			System.out.println("hkeys failed");}
		else{
			System.out.println("hkeys succeeded");}
	}
	public void hkeysbytetest(){
		Set<byte[]> ret=m_jedis.hkeys("myhtest".getBytes());
		if(5!=ret.size()){
			System.out.println("hkeysbyte failed");}
		else{
			System.out.println("hkeysbyte succeeded");}
	}
	public void hvalstest(){
		List<String> ret=m_jedis.hvals("myhtest");
		if(5!=ret.size()){
			System.out.println("hvals failed");}
		else{
			System.out.println("hvals succeeded");}
	}
	public void hvalsbytetest(){
		List<byte[]> ret=m_jedis.hvals("myhtest".getBytes());
		if(5!=ret.size()){
			System.out.println("hvalsbyte failed");}
		else{
			System.out.println("hvalsbyte succeeded");}
	}
	public void hgetalltest(){
		Map<String,String> ret=m_jedis.hgetAll("myhtest");
		if(5!=ret.size()){
			System.out.println("hgetall failed");}
		else{
			System.out.println("hgetall succeeded");}
	}
	public void hgetallbytetest(){
		Map<byte[],byte[]> ret=m_jedis.hgetAll("myhtest".getBytes());
		if(5!=ret.size()){
			System.out.println("hgetallbyte failed");}
		else{
			System.out.println("hgetallbyte succeeded");}
	}
	
	public void randombinarykeytest(){
		byte[] ret=m_jedis.randomBinaryKey();
		System.out.println(new String(ret));
	}
	public void expirebytetest(){
		m_jedis.set("k","v");
		Long ret=m_jedis.expire("k".getBytes(), 10);
		if(1!=ret){
			System.out.println("expirebyte failed");}
		else{
			System.out.println("expirebyte succeeded");}
	}
	public void expiretest(){
		m_jedis.set("k","v");
		Long ret=m_jedis.expire("k", 10);
		if(1!=ret){
			System.out.println("expire failed");}
		else{
			System.out.println("expire succeeded");}
	}
	public void incrbyfloattest(){
		m_jedis.set("kfloat", "1");
		Double ret=m_jedis.incrByFloat("kfloat", 2);
		if(3!=ret){
			System.out.println("incrbyfloat failed");}
		else{
			System.out.println("incrbyfloat succeeded");}
	}
	public void incrbyfloatbytetest(){
		m_jedis.set("kfloat", "1");
		Double ret=m_jedis.incrByFloat("kfloat".getBytes(), 2);
		if(3!=ret){
			System.out.println("incrbyfloatbyte failed");}
		else{
			System.out.println("incrbyfloatbyte succeeded");}
	}
	public void hincrbyfloattest(){
		m_jedis.hset("myhtest", "name1float", "1");
		Double ret=m_jedis.hincrByFloat("myhtest", "name1float", 2);
		if(3!=ret){
			System.out.println("hincrbyfloat failed");}
		else{
			System.out.println("hincrbyfloat succeeded");}
	}
	public void hincrbybyfloatbytetest(){
		m_jedis.hset("myhtest", "name1float", "3");
		Double ret=m_jedis.hincrByFloat("myhtest".getBytes(), "name1float".getBytes(), 2);
		if(5!=ret){
			System.out.println("hincrbyfloatbyte failed");}
		else{
			System.out.println("hincrbyfloatbyte succeeded");}
	}
	public void lindextest()
	{
		m_jedis.lpush("lindex","index");
		String ret=m_jedis.lindex("lindex", 0);
		if(ret.equals("index")){
			System.out.println("lindex succeeded");}
		else{
			System.out.println("lindex failed");}
	}
	public void lindexbytetest()
	{
		m_jedis.lpush("lindex","indexbyte");
		byte[] ret=m_jedis.lindex("lindex".getBytes(), 0);
		if(new String(ret).equals("indexbyte")){
			System.out.println("lindexbyte succeeded");}
		else{
			System.out.println("lindexbyte failed");}
	}
	public void zrangewithscorestest(){
		Set<Tuple> ret=m_jedis.zrangeWithScores("zsetscores", 0, -1);
		if(ret.size()==1){
			System.out.println("zrangewithscores succeeded");
		}
		else{
			System.out.println("zrangewithscores failed");
		}			
	}
	public void zrangewithscoresbytetest(){
		Set<Tuple> ret=m_jedis.zrangeWithScores("zsetscores".getBytes(), 0, -1);
		if(ret.size()==1){
			System.out.println("zrangewithscoresbyte succeeded");
		}
		else{
			System.out.println("zrangewithscoresbyte failed");
		}			
	}
	public void zrevrangewithscorestest(){
		Set<Tuple> ret=m_jedis.zrevrangeWithScores("zsetscores", 0, -1);
		if(ret.size()==1){
			System.out.println("zrevrangewithscores succeeded");
		}
		else{
			System.out.println("zrevrangewithscores failed");
		}			
	}
	public void zrevrangewithscoresbytetest(){
		Set<Tuple> ret=m_jedis.zrevrangeWithScores("zsetscores".getBytes(), 0, -1);
		if(ret.size()==1){
			System.out.println("zrevrangewithscoresbyte succeeded");
		}
		else{
			System.out.println("zrevrangewithscoresbyte failed");
		}			
	}
	public void zrangebyscorewithscoreoffset(){
		Set<Tuple> ret=m_jedis.zrangeByScoreWithScores("zsetscores", 0.0,5.0,2,1);
		if(ret.size()==1){
			System.out.println("zrevrangewithscoresoffset succeeded");
		}
		else{
			System.out.println("zrevrangewithscoresoffset failed");
		}	
	}
	public void zrangebyscorewithscoreoffsetbyte(){
		Set<Tuple> ret=m_jedis.zrangeByScoreWithScores("zsetscores".getBytes(), 0.0,5.0,2,1);
		if(ret.size()==1){
			System.out.println("zrevrangewithscoresoffsetbyte succeeded");
		}
		else{
			System.out.println("zrevrangewithscoresoffsetbyte failed");
		}	
	}
	public void zrevrangebyscorewithscoreoffset(){
		Set<Tuple> ret=m_jedis.zrangeByScoreWithScores("zsetscores", 0.0,5.0,2,1);
		if(ret.size()==1){
			System.out.println("zrevrevrangewithscoresoffset succeeded");
		}
		else{
			System.out.println("zrevrangewithscoresoffset failed");
		}	
	}
	public void zrevrangebyscorewithscoreoffsetbyte(){
		Set<Tuple> ret=m_jedis.zrangeByScoreWithScores("zsetscores".getBytes(), 0.0,5.0,2,1);
		if(ret.size()==1){
			System.out.println("zrevrevrangewithscoresoffsetbyte succeeded");
		}
		else{
			System.out.println("zrevrevrangewithscoresoffsetbyte failed");
		}	
	}
	public void zlexcounttest(){
		Long ret=m_jedis.zlexcount("zsetscores","[c", "[d");
		if(ret==2){
			System.out.println("zlexcount succeeded");
		}
		else{
			System.out.println("zlexcount failed");
		}
	}
	public void zlexcountbytetest(){
		Long ret=m_jedis.zlexcount("zsetscores","[c", "[d");
		if(ret==2){
			System.out.println("zlexcountbyte succeeded");
		}
		else{
			System.out.println("zlexcountbyte failed");
		}
	}
	public void zrangebylextest(){
		Set<String> ret=m_jedis.zrangeByLex("zsetscores","-", "[d");
		if(ret.size()==4){
			System.out.println("zrangebylex succeeded");
		}
		else{
			System.out.println("zrangebylex failed");
		}
	}
	public void zrangebylexbytetest(){
		Set<String> ret=m_jedis.zrangeByLex("zsetscores","-", "[d");
		if(ret.size()==4){
			System.out.println("zrangebylexbyte succeeded");
		}
		else{
			System.out.println("zrangebylexbyte failed");
		}
	}
	public void zrevrangebylextest(){
		Set<String> ret=m_jedis.zrevrangeByLex("zsetscores","+", "[d");
		if(ret.size()==2){
			System.out.println("zrevrangebylex succeeded");
		}
		else{
			System.out.println("zrevrangebylex failed");
		}
	}
	public void zrevrangebylexbytetest(){
		Set<String> ret=m_jedis.zrevrangeByLex("zsetscores","+", "[d");
		if(ret.size()==2){
			System.out.println("zrevrangebylexbyte succeeded");
		}
		else{
			System.out.println("zrevrangebylexbyte failed");
		}
	}
	public void zremrangebylextest(){
		Long ret =m_jedis.zremrangeByLex("zsetscores", "-", "[a");
		if(ret==1){
			System.out.println("zremrangebylex succeeded");
		}
		else{
			System.out.println("zremrangebylex failed");
		}
		m_jedis.zadd("zsetscores",1,"a");
	}
	public void zremrangebylexbytetest(){
		Long ret =m_jedis.zremrangeByLex("zsetscores".getBytes(), "-".getBytes(), "[a".getBytes());
		if(ret==1){
			System.out.println("zremrangebylexbyte succeeded");
		}
		else{
			System.out.println("zremrangebylexbyte failed");
		}
		m_jedis.zadd("zsetscores",1,"a");
	}
	public void linserttest(){
		Long ret=m_jedis.linsert("lindex", LIST_POSITION.BEFORE,"index", "befor");
		if(ret!=1&&ret!=-1){
			System.out.println("linsert succeeded");
		}
		else{
			System.out.println("linsert failed");
		}
	}
	public void linsertbytetest(){
		Long ret=m_jedis.linsert("lindex".getBytes(), LIST_POSITION.BEFORE,"index".getBytes(), "befor".getBytes());
		if(ret!=1&&ret!=-1){
			System.out.println("linsertbyte succeeded");
		}
		else{
			System.out.println("linsertbyte failed");
		}
	}
	public void strlentest(){
		Long ret=m_jedis.strlen("kstr");
		if(ret==2){
			System.out.println("strlen succeeded");
		}
		else{
			System.out.println("strlen failed");
		}
	}
	public void strlenbytetest(){
		Long ret=m_jedis.strlen("kstr".getBytes());
		if(ret==2){
			System.out.println("strlenbyte succeeded");
		}
		else{
			System.out.println("strlenbyte failed");
		}
	}
 public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis j = new Jedis("192.168.241.129", 6379, "_");
		test t =new test(j);
//		t.settest();
//		t.setbytetest();
//		t.gettest();
//		t.getbytetest();
//		t.deltest();
//		t.delbytetest();
//		t.typetest();
//		t.typebytetest();
//		t.setnxtest();
//		t.setnxbytetest();
//		t.getsettest();
//		t.mgettest();
//		t.mgetbytetest();
//		t.msettest();
//		t.msetbytetest();
//		t.msetnxbytetest();
//		t.msetnxtest();
//		t.incrtest();
//		t.incrbytetest();
//		t.decrtest();
//		t.decrbytetest();
//		t.incrbytest();
//		t.incrbybytetest();
//		t.decrbytest();
//		t.decrbybytetest();
//		t.appendtest();
//		t.appendbytetest();
//		t.lpushtest();
//		t.lpushbytetest();
//		t.rpushtest();
//		t.rpushbytetest();
//		t.lpushxtest();
//		t.lpushxbytetest();
//		t.rpushxtest();
//		t.rpushxbytetest();
//		t.llentest();
//		t.llenbytetest();
//		t.lrangetest();
//		t.lrangebytetest();
//		t.ltrimtest();
//		t.ltrimbytetest();
//		t.lsettest();
//		t.lsetbytetest();
//		t.lremtest();
//		t.lrembytetest();
//		t.lpoptest();
//		t.lpopbytetest();
//		t.rpoptest();
//		t.rpopbytetest();
//		t.saddtest();
//		t.saddbytetest();
//		t.sremtest();
//		t.srembytetest();
//		t.srandmembertest();
//		t.srandmemberbytetest();
//		t.srandmemcountbertest();
//		t.srandmembecountrbytetest();
//		t.scardtest();
//		t.scardbytetest();
//		t.sismembertest();
//		t.sismemberbytetest();
//		t.smemberstest();
//		t.smembersbytetest();
//		t.zaddtest();
//		t.zaddmaptest();
//		t.zaddbytetest();
//		t.zaddmapbytetest();
//		t.zremtest();
//		t.zrembytetest();
//		t.zincrbytest();
//		t.zincrbybytetest();
//		t.zranktest();
//		t.zrankbytetest();
//		t.zrevranktest();
//		t.zrevrankbytetest();
//		t.zrangetest();
//		t.zrangebytetest();
//		t.zrevrangetest();
//		t.zrevrangebytetest();
//		t.zrangebyscorebytetest();
//		t.zrangebyscorebytetest();
//		t.zcounttest();
//		t.zcountbytetest();
//		t.zcardtest();
//		t.zcardbytetest();
//		t.zscoretest();
//		t.zscorebytetest();
//		t.zremrangebyranktest();
//		t.zremrangebyrankbytetest();
//		t.zremrangebyscoretest();
//		t.zremrangebyscorebytetest();
//		t.hsettest();
//		t.hsetbytetest();
//		t.hmsettest();
//		t.hmsetbytetest();
//		t.hmgettest();
//		t.hmgetbytetest();
//		t.hincrbytest();
//		t.hincrbybyteteby();
//		t.hexiststest();
//		t.hexistsbytetest();
//		t.hdeltest();
//		t.hdelbytetest();
//		t.hlentest();
//		t.hlenbytetest();
//		t.hkeystest();
//		t.hkeysbytetest();
//		t.hvalstest();
//		t.hvalsbytetest();
//		t.hgetalltest();
//		t.hgetallbytetest();
//		t.randombinarykeytest();
//		t.expiretest();
//		t.expirebytetest();
//		t.incrbyfloattest();
//		t.incrbyfloatbytetest();
//		t.lindextest();
//		t.lindexbytetest();
//		t.zrangewithscorestest();
//		t.zrangewithscoresbytetest();
//		t.zrevrangewithscorestest();
//		t.zrevrangewithscoresbytetest();
//		t.zrangebyscorewithscoreoffset();
//		t.zrangebyscorewithscoreoffsetbyte();
//		t.zrevrangebyscorewithscoreoffset();
//		t.zrevrangebyscorewithscoreoffsetbyte();
//		t.zlexcounttest();
//		t.zlexcountbytetest();
//		t.zrangebylextest();
//		t.zrangebylexbytetest();
//		t.zrevrangebylextest();
//		t.zrevrangebylexbytetest();
//		t.linserttest();
//		t.linsertbytetest();
//		t.strlentest();
//		t.strlenbytetest();
	}

	
}
