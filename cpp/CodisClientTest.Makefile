CXX = g++
OBJ_DIR = obj
TEST_DIR = test

INCPATH = -Iinclude
INCPATH += -I3party/include
INCPATH += -I3party/include/hiredis
INCPATH += -I3party/include/zookeeper

LFLAGS += -L3party -lgtest_main -lgtest -lpthread
LFLAGS += -Lbin -lcodisclient


TARGET = bin/CodisClientTest

CXX_OBJS = $(OBJ_DIR)/CodisClientTest.o 
			
$(OBJ_DIR)/%.o:$(TEST_DIR)/%.cpp
	$(CXX) -c -fPIC -o $@ $< $(INCPATH) $(LFLAGS)
	
$(OBJ_DIR)/%.o:$(TEST_DIR)/%.c
	$(CXX) -c -fPIC -o $@ $< $(INCPATH) $(LFLAGS)

			
$(TARGET):$(CXX_OBJS)
	$(CXX) -o $(TARGET) $(CXX_OBJS) $(LFLAGS)
	
.PHONY:clean
clean:
	-rm -f bin/RedisClientTest
