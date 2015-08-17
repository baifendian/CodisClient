#!/usr/bin/env python

import codis_client

#simple example
codis_client.InitFromZK('127.0.0.1:2181', '/zk/codis/db_test/proxy')
print codis_client.GetProxy().set("kk", "vv")

#better example
#if faild, then get other proxy connection
codis_client.InitFromZK('127.0.0.1:2181', '/zk/codis/db_test/proxy')
try:
	ret = codis_client.GetProxy().set("kk", "vv")
except redis.exceptions.ConnectionError, e:
	ret = codis_client.GetProxy().set("kk", "vv")

#best example
#if faild, reconnect other proxy util success
codis_client.InitFromZK('127.0.0.1:2181', '/zk/codis/db_test/proxy')
while True:
	try:
		ret = codis_client.GetProxy().set("kk", "vv")
	except redis.exceptions.ConnectionError, e:
		continue
	break


