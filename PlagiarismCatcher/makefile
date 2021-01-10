CPP  = g++
RES  = 
OBJ  = chunks.o HashTable.o plagiarismCatcher.o $(RES)
LINKOBJ  = chunks.o HashTable.o plagiarismCatcher.o $(RES)
BIN  = plagiarismCatcher
CXXFLAGS = $(CXXINCS)  
CFLAGS = $(INCS)  
RM = rm -f


all: plagiarismCatcher 

clean: 
	${RM} $(OBJ) $(BIN)

$(BIN): $(OBJ)
	$(CPP) $(LINKOBJ) -o plagiarismCatcher $(LIBS)

chunks.o: chunks.cpp
	$(CPP) -c chunks.cpp -o chunks.o $(CXXFLAGS)

HashTable.o: HashTable.cpp
	$(CPP) -c HashTable.cpp -o HashTable.o $(CXXFLAGS)

plagiarismCatcher.o: plagiarismCatcher.cpp
	$(CPP) -c plagiarismCatcher.cpp -o plagiarismCatcher.o $(CXXFLAGS)
