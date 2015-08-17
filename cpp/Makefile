CXX = g++
AR = ar cr
OBJ_DIR = obj
SRC_DIR = src

INCPATH = -Iinclude
INCPATH += -I3party/include
INCPATH += -I3party/include/hiredis
INCPATH += -I3party/include/zookeeper

LFLAGS = -L3party/lib -lhiredis
LFLAGS += -L3party/lib -lzookeeper_mt
LFLAGS += -L3party/lib -ljemalloc -pthread
LFLAGS += -L3party/lib -ljson


TARGETSO = bin/libcodisclient.so

CXX_OBJS = $(OBJ_DIR)/CodisClient.o \
			$(OBJ_DIR)/RoundRobinCodisPool.o \
			$(OBJ_DIR)/RedisClientPool.o \
			$(OBJ_DIR)/Reply.o \
			$(OBJ_DIR)/ae.o \
			$(OBJ_DIR)/zmalloc.o \
			$(OBJ_DIR)/Command.o
			
$(OBJ_DIR)/%.o:$(SRC_DIR)/%.cpp
	$(CXX) -c -fPIC -o $@ $< $(INCPATH) $(LFLAGS)
	
$(OBJ_DIR)/%.o:$(SRC_DIR)/%.c
	$(CXX) -c -fPIC -o $@ $< $(INCPATH) $(LFLAGS)

			
$(TARGETSO):$(CXX_OBJS)
	$(CXX) -fPIC -shared -o $(TARGETSO) $(CXX_OBJS) $(LFLAGS)
	cp 3party/lib/* bin/
 
.PHONY:clean
clean:
	-rm -f obj/*.o
	-rm -f bin/*

.PHONY:test
test:
	-rm -f bin/RedisClientTest
	make -f CodisClientTest.Makefile
	export LD_LIBRARY_PATH=./bin/:${LD_LIBRARY_PATH}
	./bin/CodisClientTest
