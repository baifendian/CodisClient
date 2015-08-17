#!/usr/bin/env python
#encoding:utf-8

import zookeeper
import time
import logging
import os
import json,sys, time, threading
import redis

logger = logging.getLogger()
lock = threading.Lock()

ZOOKEEPERADDR = ''
PROXYPATH = ""
CONNPOOL = []
CONNPOOLINDEX = -1
ZK = None

def watcher(zh, event, state, path):
	logger.debug("watcher callback zh:%d event:%d state:%d path:%s", zh, event, state, path)
    	if event == zookeeper.SESSION_EVENT and state == zookeeper.CONNECTING_STATE:  
		zookeeper.close(zh)
		zh = zookeeper.init(ZOOKEEPERADDR, watcher, 30000)
	elif event == zookeeper.SESSION_EVENT and state == zookeeper.EXPIRED_SESSION_STATE:  
		zookeeper.close(zh)
		zh = zookeeper.init(ZOOKEEPERADDR, watcher, 30000)
	elif event == zookeeper.SESSION_EVENT and state == zookeeper.CONNECTED_STATE:  #init connection complete
		pass
	elif event == zookeeper.CREATED_EVENT and state == zookeeper.CONNECTED_STATE:  
		InitConnPool(zh, PROXYPATH)
	elif event == zookeeper.DELETED_EVENT and state == zookeeper.CONNECTED_STATE:  
		InitConnPool(zh, PROXYPATH)
	elif  event == zookeeper.CHANGED_EVENT and state == zookeeper.CONNECTED_STATE:  
		InitConnPool(zh, PROXYPATH)
	elif  event == zookeeper.CHILD_EVENT and   state == zookeeper.CONNECTED_STATE:  
		InitConnPool(zh, PROXYPATH)
	else:
		logger.error("zookeeper connection state changed but not implemented: event:%d state:%d path:%s", event, state, path)


def InitConnPool(zk, proxyPath):
	global CONNPOOL
	global lock
	lock.acquire()
	try:
		proxylist = []
		CONNPOOL = []
		proxynamelist = zookeeper.get_children(zk, proxyPath, watcher)
		for proxy in proxynamelist:
			proxyinfo = zookeeper.get(zk, proxyPath+'/'+proxy, watcher)
			decoded = json.loads(proxyinfo[0])
			if decoded["state"] == "online":
				proxylist.append(decoded)
		for proxyinfo in proxylist:
			proxyip = proxyinfo["addr"].split(':')[0]
			proxyport = proxyinfo["addr"].split(':')[1]
			conn = redis.Redis(host=proxyip, port=int(proxyport), db=0)
			CONNPOOL.append(conn)	
	except Exception, e:
		logger.error("InitConnPool error")
	lock.release()

def InitFromZK(zkAddr, proxyPath):
	global ZOOKEEPERADDR
	global PROXYPATH
	global ZK

	ZOOKEEPERADDR = zkAddr
	PROXYPATH = proxyPath
	ZK = zookeeper.init(zkAddr, watcher, 30000)
	InitConnPool(ZK, proxyPath)

def GetProxy():
	global lock
	global CONNPOOLINDEX
	global CONNPOOL
	lock.acquire()
	CONNPOOLINDEX += 1
	if CONNPOOLINDEX >= len(CONNPOOL):
		CONNPOOLINDEX = 0
	if len(CONNPOOL) == 0:
		lock.release()
		return None
	else:
		lock.release()
		return CONNPOOL[CONNPOOLINDEX]	
	

if __name__ == '__main__':
	InitFromZK('127.0.0.1:2181', '/zk/codis/db_test/proxy')
	try:	
		ret = GetProxy().set('kk', 'vv')
	except redis.exceptions.ConnectionError, e:
		ret = GetProxy().set('kk', 'vv')
	
