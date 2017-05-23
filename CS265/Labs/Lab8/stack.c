
struct s_node {
	int data;
	struct s_node *next ;
} ;

typedef enum { FALSE, TRUE } bool ;

typedef struct s_node node ;

typedef node* Stack ;

Stack push( Stack, int ) ;
int top( Stack ) ;
Stack pop( Stack ) ;
bool isEmpty( Stack s ) { return s==NULL; }

Stack stack_init() { return NULL ; }
Stack stack_kill( Stack ) ;

a.foo( 3, "kurt" ) ;

foo( &a, 3, "kurt" ) ;

int main() {
	Stack sergey, mandong, francisco ;

	sergey = stack_init() ;
	mandong = stack_init() ;
	francisco = stack_init() ;

	sergey = push( sergey, 13 ) ;
	isEmpty( sergey ) ;
	isEmpty( mandong ) ;
