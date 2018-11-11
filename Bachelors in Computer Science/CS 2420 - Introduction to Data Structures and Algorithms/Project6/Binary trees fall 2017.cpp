// Copyright 2017, Bradley Peterson, Weber State University, all rights reserved.

#include <iostream>
#include <string>
#include <stack>
#include <sstream>
#include <cmath>
#include <memory>

//To prevent those using g++ from trying to use a library
//they don't have
#ifndef __GNUC__
#include <conio.h>
#else
#include <stdio.h>
#endif

using std::stack;
using std::istringstream;
using std::ostringstream;
using std::string;
using std::cout;
using std::cerr;
using std::endl;
using std::pow;

template <typename T>
struct Node {
	T data;
	Node< string > * leftChild{ nullptr };
	Node< string > * rightChild{ nullptr };
};

class TreeParser {
private:
	stack<string> mathStack; 
	double castStrToDouble(string const &s); 
	string castDoubleToStr(const double d); 
	void initialize();
	void TreeParser::delTree(Node<string> *p);

	bool isDigit(char c); 
	bool isOperator(char c); 
	void processExpression(Node<string> *p); 
	void computeAnswer(Node<string> *p);
	void inOrderTraversal(Node<string> *p) const;
	void postOrderTraversal(Node<string> *p) const;


protected: 
	string expression;
	unsigned int position;
	Node< string > * root{ nullptr };

public:
	TreeParser(); 
	~TreeParser();
	void displayParseTree(); 
	void processExpression(string &expression); 
	string computeAnswer();
	void inOrderTraversal() const;
	void postOrderTraversal() const;
};

void TreeParser::initialize() {
	expression = "";
	position = 0;
	while (!mathStack.empty()) {
		mathStack.pop();
	}

}

double TreeParser::castStrToDouble(const string &s) {
	istringstream iss(s);
	double x;
	iss >> x;
	return x;
}

string TreeParser::castDoubleToStr(const double d) {
	ostringstream oss;
	oss << d;
	return oss.str();

}

TreeParser::TreeParser() {
	initialize();
}

TreeParser::~TreeParser() {
	delTree(root);
	root = nullptr;
}

void TreeParser::delTree(Node<string> *p)  {
	if (p != nullptr) {
		delTree(p->leftChild);
		delTree(p->rightChild);
		delete p;
	}
	
}

void TreeParser::displayParseTree() {
	cout << "The expression seen using in-order traversal: ";
	inOrderTraversal();
	cout << endl;
	cout << "The expression seen using post-order traversal: ";
	postOrderTraversal();
	cout << endl;

}

void TreeParser::postOrderTraversal()const  {
	postOrderTraversal(root);
}

void TreeParser::postOrderTraversal(Node<string> *p) const  {
	if (p != nullptr) {
		postOrderTraversal(p->leftChild);
		postOrderTraversal(p->rightChild);
		cout << p->data << " ";
	}
}

void TreeParser::inOrderTraversal() const {
	inOrderTraversal(root);
}

void TreeParser::inOrderTraversal(Node<string> *p) const {
	if (p != nullptr) {
		inOrderTraversal(p->leftChild);
		cout << p->data << " ";
		inOrderTraversal(p->rightChild);
	}
}

bool TreeParser::isDigit(char c) {
	if (48 <= c && c <= 57) {
		return true;
	}
	else {
		return false;
	}
}

bool TreeParser::isOperator(char c) {
	if ((42 <= c && c <= 43) || c == 45 || c == 47 || c == 94) {
		return true;
	}
	else {
		return false;
	}
}

void TreeParser::processExpression(string &expression) {
	initialize();
	delTree(root);
	root = nullptr;
	if (expression.length()) {
		this->expression = expression;
		position = 0;
		Node< string > * temp = new Node< string >;
		root = temp;
		processExpression(temp); 
	}
	else {
		cout << "No expression found" << endl;
	}
}

void TreeParser::processExpression(Node<string> *p) {
	string temp;
	while (position < expression.length()) {
		if (expression[position] == 40) {			//Open parenthesis
			++position;
			Node< string > * node = new Node< string >;
			p->leftChild = node;
			processExpression(node);
		}
		else if (expression[position] == 41) {		//Close parenthesis
			++position;
			return;
		}

		if (isDigit(expression[position])) {
			while (isDigit(expression[position])) {
				temp += expression[position++];
			}
			p->data = temp;			
			return;
		}
		else if (isOperator(expression[position])) {
			p->data = expression[position++];
			Node< string > * node = new Node< string >;
			p->rightChild = node;
			processExpression(node);
		}
		else if (expression[position] == 32) {		//Space
			++position;
		}
		
	}

}

string TreeParser::computeAnswer() {
	while (!mathStack.empty()) {
		mathStack.pop();
	}
	computeAnswer(root);
	return mathStack.top();
}

void TreeParser::computeAnswer(Node<string> *p) {
	if (p != nullptr) {
		computeAnswer(p->leftChild);
		computeAnswer(p->rightChild);
		
		if (isOperator(p->data[0])) {
			double rh = castStrToDouble(mathStack.top());
			mathStack.pop();
			double lh = castStrToDouble(mathStack.top());
			mathStack.pop();
			double answer;

			switch (p->data[0]) {
			case 94:
				answer = pow(lh, rh);
				break;
			case 42:
				answer = lh * rh;
				break;
			case 47:
				answer = lh / rh;
				break;
			case 43:
				answer = lh + rh;
				break;
			case 45:
				answer = lh - rh;
				break;
			}
			mathStack.push(castDoubleToStr(answer));
		}
		else {
			mathStack.push(p->data);
		}
	}
}


void pressAnyKeyToContinue() {
	printf("Press any key to continue\n");

	//Linux and Mac users with g++ don't need this
	//But everyone else will see this message.
#ifndef __GNUC__
	_getch();
#else
	int c;
	fflush(stdout);
	do c = getchar(); while ((c != '\n') && (c != EOF));
#endif

}


int main() {
	
	TreeParser *tp = new TreeParser;

	string expression = "(4+7)";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display 11 as a double output

	expression = "(7-4)";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display 3 as a double output

	expression = "(4^3)";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display 64 as a double output

	expression = "((2-5)-5)";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display -8 as a double output

	expression = "(5*(6/2))";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display 15 as a double output

	expression = "((1 + 2) * (3 + 4))";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display 21 as a double output

	expression = "(543+321)";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display 864 as a double output

	expression = "(((((3+12)-7)*120)/(2+3))^3)";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display close to 7077888 as a double output

	expression = "(((((9+(2*(110-(30/2))))*8)+1000)/2)+(((3^4)+1)/2))";
	tp->processExpression(expression);
	tp->displayParseTree();
	cout << "The result is: " << tp->computeAnswer() << endl; //Should display close to 1337 as a double/decimal output
	
	pressAnyKeyToContinue();
	return 0;
}
