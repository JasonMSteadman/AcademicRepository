//Copyright 2017, Bradley Peterson, Weber State University, All rights reserved.

#include <iostream>
#include <string>
#include <sstream>
#include <cstdio>

//To prevent those using g++ from trying to use a library
//they don't have
#ifndef __GNUC__
#include <conio.h>
#endif

using std::cout;
using std::cerr;
using std::endl;
using std::string;

//These two base classes help the assignment compile and run without any changes.  
//Dot not modify these.  You will instead override the methods in the derived classes below.
template <typename T>
class CourseBaseStack {
public:
	CourseBaseStack() {}
	CourseBaseStack(const unsigned int size) {}
	void push(const T& item) {}
	T pop() { T temp{}; return temp; }
	const unsigned int size() const { return 0; }
protected:
	T* arr{ nullptr };

};

template <typename T>
class CourseBaseQueue {
public:
	CourseBaseQueue() {}
	CourseBaseQueue(const unsigned int size) {}
	void push_back(const T& item) {}
	T pop_front() { T temp{}; return temp; }
	const unsigned int size() const { return 0; }
protected:
	T* arr{ nullptr };

};

//**********************************
//Write your code below here
//		Jason Steadman
//		W01290097
//**********************************
template <typename T>
class CourseStack : public CourseBaseStack<T> {
private:
	unsigned currentSize{ 0 };
	unsigned int capacity{ 0 };
public:
	CourseStack<T>(const unsigned int size);
	~CourseStack<T>();
	void push(const T& item);
	T pop();
	const unsigned int size() const { return currentSize; }
};

template <typename T>
CourseStack<T>::CourseStack(const unsigned int size) {
	capacity = size;
	arr = new T[size];
}

template <typename T>
CourseStack<T>::~CourseStack() {
	delete[] arr;
	arr = nullptr;
	currentSize = 0;
}

template <typename T>
void CourseStack<T>::push(const T& item) {
	if (currentSize < capacity) {
		arr[currentSize++] = item;
		return;
	}
	else {
		cout << "Error: The stack is full, the max capacity is " << capacity << "." << endl;
		return;
	}
}

template <typename T>
T CourseStack<T>::pop() {
	if (currentSize != 0) {
		return arr[--currentSize];
	}
	else {
		throw 1;
	}
}

template <typename T>
class CourseQueue : public CourseBaseQueue<T> {
private:
	unsigned int currentSize{ 0 };
	unsigned int capacity{ 0 };
	unsigned int front{ 0 };
	unsigned int back{ 0 };
public:
	CourseQueue(const unsigned int size);
	~CourseQueue<T>();
	T pop_front();
	void push_back(const T& item);
	const unsigned int size() const { return currentSize; }
};

template <typename T>
CourseQueue<T>::CourseQueue(const unsigned int size) {
	capacity = size;
	arr = new T[size];
}

template <typename T>
CourseQueue<T>::~CourseQueue() {
	delete[] arr;
	arr = nullptr;
	currentSize = capacity = front = back = 0;
}

template <typename T>
T CourseQueue<T>::pop_front() {
	if (currentSize != 0) {
		unsigned int tempFront = front++;
		if (front == capacity) {
			front = 0;
		}
		currentSize--;
		return arr[tempFront];
	}
	else {
		throw 1;
	}
}

template <typename T>
void CourseQueue<T>::push_back(const T& item) {
	if (currentSize != capacity) {
		currentSize++;
		arr[back++] = item;
		if (back == capacity) {
			back = 0;
		}
	}
	else {
		cout << "Error: Queue is full." << endl;
	}
}

//**********************************
//Write your code above here
//**********************************


//This helps with testing, do not modify.
bool checkTest(string testName, int whatItShouldBe, int whatItIs) {

	if (whatItShouldBe == whatItIs) {
		cout << "Passed " << testName << endl;
		return true;
	}
	else {
		cout << "***Failed test " << testName << " *** " << endl << "   Output was " << whatItIs << endl << "   Output should have been " << whatItShouldBe << endl;
		return false;
	}
}


//This helps with testing, comment it in when ready, but do not modify the code.
bool checkTest(string testName, string whatItShouldBe, string whatItIs) {

	if (whatItShouldBe == whatItIs) {
		cout << "Passed " << testName << endl;
		return true;
	}
	else {
		if (whatItShouldBe == "") {
			cout << "***Failed test " << testName << " *** " << endl << "   Output was " << whatItIs << endl << "   Output should have been blank. " << endl;
		}
		else {
			cout << "***Failed test " << testName << " *** " << endl << "   Output was " << whatItIs << endl << "   Output should have been " << whatItShouldBe << endl;
		}
		return false;
	}
}

//This helps with testing, do not modify.
bool checkTestMemory(string testName, int whatItShouldBe, int whatItIs) {

	if (whatItShouldBe == whatItIs) {
		cout << "Passed " << testName << endl;
		return true;
	}
	else {
		cout << "***Failed test " << testName << " *** " << endl << ".  ";
		cout << "You are manually managing " << whatItIs << " bytes in memory, but it should be " << whatItShouldBe << " bytes." << endl;
		return false;
	}
}



//This helps with testing, do not modify.
void testCourseStack() {

	string result;
	string caughtError;
	CourseStack< int > *stack = new CourseStack<int>(5);

	stack->push(1);

	int data = stack->pop();
	checkTest("testCourseStack #1", 1, data);

	stack->push(1);
	stack->push(2);
	stack->push(3);
	stack->push(4);
	stack->push(5);
	checkTest("testCourseStack #2", 5, stack->pop());
	checkTest("testCourseStack #3", 4, stack->pop());
	checkTest("testCourseStack #4", 3, stack->pop());
	checkTest("testCourseStack #5", 2, stack->pop());
	checkTest("testCourseStack #6", 1, stack->pop());

	//now cover error handling
	try {
		result = stack->pop();
	}
	catch (int e) {
		caughtError = "caught";
	}
	checkTest("testCourseStack #7", "caught", caughtError);

	//check currentSize
	checkTest("testCourseStack #8", 0, stack->size());
	stack->push(12);
	stack->push(32);
	checkTest("testCourseStack #9", 2, stack->size());

	//now test filling it up
	stack->push(14);
	stack->push(53);
	stack->push(47);
	checkTest("testCourseStack #10", 5, stack->size());

	//This should simply not let the 20 go in, as it is out of room.
	stack->push(20);

	//Grab all the items again.
	checkTest("testCourseStack #11", 47, stack->pop());
	checkTest("testCourseStack #12", 53, stack->pop());
	checkTest("testCourseStack #13", 14, stack->pop());
	checkTest("testCourseStack #14", 32, stack->pop());
	checkTest("testCourseStack #15", 12, stack->pop());

	//now do error handling again
	try {
		result = stack->pop();
	}
	catch (int e) {
		caughtError = "caught";
	}
	checkTest("testCourseStack #16", "caught", caughtError);

	delete stack;

	//test some strings
	CourseStack<string> *sstack = new CourseStack<string>(10);

	sstack->push("pencil");
	sstack->push("pen");
	sstack->push("marker");

	checkTest("testCourseStack #17", 3, sstack->size());

	//remove pen from the stack.
	string temp = sstack->pop();
	sstack->pop();
	sstack->push(temp);

	//see if it worked 
	checkTest("testCourseStack #18", "marker", sstack->pop());
	checkTest("testCourseStack #19", "pencil", sstack->pop());

	checkTest("testCourseStack #20", 0, sstack->size());
	delete sstack;

}

//This helps with testing, comment it in when ready, but do not modify the code.
void testCourseQueue() {

	string result;
	string caughtError;
	CourseQueue<string> *pQueue = new CourseQueue<string>(5);

	//Tests push_back
	pQueue->push_back("penny");
	pQueue->push_back("nickel");
	pQueue->push_back("dime");
	pQueue->push_back("quarter");

	checkTest("testCourseQueue #1", 4, pQueue->size());

	checkTest("testCourseQueue #2", "penny", pQueue->pop_front());
	checkTest("testCourseQueue #3", 3, pQueue->size());

	checkTest("testCourseQueue #4", "nickel", pQueue->pop_front());
	checkTest("testCourseQueue #5", "dime", pQueue->pop_front());
	checkTest("testCourseQueue #6", "quarter", pQueue->pop_front());
	checkTest("testCourseQueue #7", 0, pQueue->size());

	caughtError = "not caught";
	try {
		result = pQueue->pop_front();
	}
	catch (int e) {
		caughtError = "caught";
	}
	checkTest("testCourseQueue #8", "caught", caughtError);
	checkTest("testCourseQueue #9", 0, pQueue->size());

	//Try it again.  This should make it wrap around, and fill up.
	pQueue->push_back("penny");
	pQueue->push_back("nickel");
	pQueue->push_back("dime");
	pQueue->push_back("quarter");

	checkTest("testCourseQueue #10", "penny", pQueue->pop_front());
	pQueue->push_back("half dollar");
	pQueue->push_back("silver dollar");

	//It should be full, no more room to add more.
	pQueue->push_back("million dollar bill");

	checkTest("testCourseQueue #11", "nickel", pQueue->pop_front());
	checkTest("testCourseQueue #12", "dime", pQueue->pop_front());
	checkTest("testCourseQueue #13", "quarter", pQueue->pop_front());
	checkTest("testCourseQueue #14", "half dollar", pQueue->pop_front());
	checkTest("testCourseQueue #15", "silver dollar", pQueue->pop_front());
	caughtError = "not caught";
	try {
		result = pQueue->pop_front();
	}
	catch (int e) {
		caughtError = "caught";
	}
	checkTest("testCourseQueue #16", "caught", caughtError);

	//Test adding and removing back and forth
	pQueue->push_back("penny");
	checkTest("testCourseQueue #17", "penny", pQueue->pop_front());
	pQueue->push_back("nickel");
	checkTest("testCourseQueue #18", "nickel", pQueue->pop_front());
	pQueue->push_back("dime");
	checkTest("testCourseQueue #19", "dime", pQueue->pop_front());
	pQueue->push_back("quarter");
	checkTest("testCourseQueue #20", "quarter", pQueue->pop_front());
	pQueue->push_back("half dollar");
	checkTest("testCourseQueue #21", "half dollar", pQueue->pop_front());
	pQueue->push_back("silver dollar");
	checkTest("testCourseQueue #22", 1, pQueue->size());

	checkTest("testCourseQueue #23", "silver dollar", pQueue->pop_front());
	caughtError = "not caught";
	try {
		result = pQueue->pop_front();
	}
	catch (int e) {
		caughtError = "caught";
	}
	checkTest("testCourseQueue #24", "caught", caughtError);

	delete pQueue;

}

void pressAnyKeyToContinue() {
	cout << "Press any key to continue...";

	//Linux and Mac users with g++ don't need this
	//But everyone else will see this message.
#ifndef __GNUC__
	_getch();

#else
	int c;
	fflush(stdout);
	do c = getchar(); while ((c != '\n') && (c != EOF));
#endif
	cout << endl;
}


int main() {

	{
		testCourseStack();
		pressAnyKeyToContinue();
		testCourseQueue();
		pressAnyKeyToContinue();
	}
	cout << "Shutting down the program" << endl;
	return 0;
}