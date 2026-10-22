// 4 Pillar | Core Concept of OOP:
/*
The four core pillars of **Object-Oriented Programming (OOP)**, as summarized at (4:50), are:

1.  **Encapsulation (4:52):** Bundling data (variables) and methods (functions) into a single unit (object) to reduce complexity and protect the internal state from direct interference.
2.  **Abstraction (4:58):** Hiding complex internal implementation details and exposing only the essential features to the user, making interaction simpler.
3.  **Inheritance (5:02):** Reusing code by allowing a child class to derive properties and methods from a parent class, which eliminates redundancy and improves efficiency.
4.  **Polymorphism (5:05):** Allowing a single function or method to operate in multiple forms, making the code more flexible, concise, and scalable for various object types.

*/


// # Inheritance: reuse one class to another other class with rewrite it's property again, another class may have extends it's properly.
// - a child class acquires the fields and methods of a parent class using extends. It gives code reuse and an IS-A relationship.
// - it inherit parent class to child class with there properties and behaviors 



        class Animal {
            void eat() {
                System.out.println("Eating... | parent class");
            }
        }
        class Cat extends Animal {
            void jump() {
                System.out.println("Jumping... | child class");
            }
        }

        public class Note {
            public static void main(String args[]) {
                Cat c1 = new Cat();
                c1.eat();
                c1.jump();
            }
        }
            


/*
------------------------------------------------------------------------
                    # Types of inheritance
------------------------------------------------------------------------
    Type	        Shape	        In Java (classes)
    Single	        A → B	        Yes
    Multilevel	    A → B → C	    Yes
    Hierarchical	A → B, A → C    Yes
    Multiple	    A, B → C	    No (use interfaces)
    Hybrid	        mix	            No with classes (use interfaces)
------------------------------------------------------------------------

- Does a child inherit constructors? No. It can only call them using super(...).
- Parent object using child reference? Dog d = new Animal(); is a compile error. A parent is not necessarily a child.
- Inheritance is IS-A (Dog is an Animal) | IS-A relationship


*/

// -------------------------------------------------------------------------------------------------------


// # Polymorphism: 'Different form' | same method behave differently depend on situation
/*
# Two Type of method's:
    1. Method overloading: Compile-Time | same method name but different parameter
    - Creating multiple methods with the same name but different parameter lists in the same class
    ```
    class Calculator {
        int add(int a, int b) {
            return a+b;
        }
        double add (double a, double b) {
            return a+b;
        }
        int add(int a, int b, int c) {
            return a+b+c;
        }
    }

    public  class Main {
        public static void main(String[] args) {
            Calculator c = new Calculator();
            System.out.println(c.add(5, 6));
            System.out.println(c.add(5.2, 6.5));
            System.out.println(c.add(7, 8, 9));
        }
    }

    - The compiler picks the method by looking at the arguments
    - add(5, 6) has (int, int) version then it pick up (int, int) method in class
    ```

    2. Method overriding: Runtime | same method name, same parameters,
    - When a child class provides its own implementation of a method already defined in its parent class.
    ```
    class Animal {
        void sound() {
            System.out.println("Animal sound");
        }
    }

    class Dog extends Animal {
        @Override
        void sound() {
            System.out.println("Bark");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Dog d = new Dog();
            d.sound();
        }
    }
    ```
*/


// --------------------------------------------------------------------------------------------------------

// # Packages: It's a way of storing related classes and interface together in directory(folder).


/*

```
------------
package animal;

public class Cat {
    public void eating() {
        System.out.println("Eating...");
    }
}

------------

import animal.Cat;
public class Main {
    public static void main(String[] args) {
        Cat c = new Cat();
        c.eating();
    }
}

------------
```

# Why do we use packages?
- In a big project with hundreds or thousands of classes, we use packages to organize related classes and interfaces into separate groups, making the project easier to manage and maintain.
- Packages help organize related classes and interfaces, avoid naming conflicts, and make large projects easier to maintain.


*/


// --------------------------------------------------------------------------------------------------------

// # Access Modifiers: it's a keyword that tell how can use classes, method's, field's or constructor.

/*
Types of access modifiers in Java
- Java has 4 access levels:
-----------------------------------------------------------------------------------------------
Modifier:	            Simple meaning:

public	                Accessible from anywhere, subject to normal Java access rules.
protected	            Accessible within the same package and by subclasses in other packages.
default (no keyword)	Accessible only within the same package.
private	                Accessible only within the same class.
-----------------------------------------------------------------------------------------------


Modifier	            Same class	    Same package	   Subclass (other package)	        Everywhere
private	                    ✅	            ❌	              ❌	                            ❌
default (no keyword)	    ✅	            ✅	              ❌	                            ❌
protected	                ✅	            ✅	              ✅ (through inheritance)	    ❌
public	                    ✅	            ✅	              ✅	                            ✅

-----------------------------------------------------------------------------------------------

- Top-level classes can only be public or default. private or protected class at top level is not allowed.
- A file can have only one public class, and the file name must match it.
- Default is also called package-private.



*/


// --------------------------------------------------------------------------------------------------------

// # Encapsulation: (Bundle + Protect)
// - keeping data and methods in one class and protect it from direct access using access modifiers like private.

/*

```
class Student {
    private int age = 20;

    public void setAge(int a) {
        if (a > 0) {
            age = a;
        }
    }

    public int getAge() {
        return age;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();

        s.setAge(21);
        System.out.println(s.getAge());
    }
}
```

# Why do we use encapsulation?
- To protect data from unwanted changes and allow changes only through controlled methods.
- ex: s.age = -10; -> error and logically incorrect


- Encapsulation vs abstraction? Encapsulation = how you hide data (private + methods, protection). Abstraction = what you show (hide implementation complexity, design level).
- Benefits? Data safety, flexibility to change internals without breaking users, easier testing, and also protect from unusual changes.

*/


// ----------------------------------------------------------------------------------------------------------

// # Abstraction: Hiding the internal implementation and show only the essential features
// - what an object does, not how.

/*

// Java gives two tools:   
        1. abstract classes 
        2. interfaces.

1. abstract classes: An abstract class is a class declared with the abstract keyword that can define methods without implementations and methods with implementations.

```
    abstract class Animal {
        abstract void sound();  // No implementation

        void sleep() {          // Has implementation
            System.out.println("Sleeping...");
        }
    }

    class Dog extends Animal {
        void sound() {
            System.out.println("Barking...");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Dog d = new Dog();
            d.sound();
            d.sleep();
        }
    }
```
OUTPUT:
    Barking...
    Sleeping...




2. interface: An interface defines a contract that a class agrees to follow by implementing its required methods.

```
    interface Payment {
        void pay();
    }

    class UpiPayment implements Payment {
        public void pay() {
            System.out.println("Paying using UPI");
        }
    }

    class CardPayment implements Payment {
        public void pay() {
            System.out.println("Paying using Card");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Payment p = new UpiPayment();
            p.pay();

            p = new CardPayment();
            p.pay();
        }
    }
```
OUTPUT: 
    Paying using UPI
    Paying using Card


--------------------------------------------------------------------------------
# Abstract class: A common base with shared code and some unfinished behavior.
# Interface: A contract that says which behaviors a class must provide.
--------------------------------------------------------------------------------


*/

// ---------------------------------------------------------------------------------------------------------


/*

# The 4 pillars in one view:

Pillar:	        One-line definition:	            Java tool:
Encapsulation	Hide data, control access	        private + methods
Abstraction	    Show what, hide how	                abstract class, interface
Inheritance	    Reuse parent’s code (IS-A)	        extends
Polymorphism	Same call, different behaviour	    overloading, overriding


*/


// ----------------------------------------------------------------------------------------------------------

// # static: variable belong to class itself rather then belong to individual object

// ex:

/*
```
class Student {
        String name;
        static String college = "Marwadi University";
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.name = "Jayesh";
        // s.college = "RK University"; // allowed | then OUTPUT will not "Marwadi University" -> "Rk University"
        System.out.println(s.name);
        System.out.println(Student.college);
    }
}

```
OUTPUT:
    Jayesh
    Marwadi University

- college is static, so it belongs to the Student class and is shared by all students.

-> A static method can be called without creating an object.

```
    class Calculator {
        static int add(int a, int b) {
            return a + b;
        }
    }

    public class Main {
        public static void main(String[] args) {
            System.out.println(Calculator.add(5, 3));
        }
    }
```
OUTPUT: 8



====================================================================
Remember: static = belongs to the class, not to individual objects.
====================================================================


*/

// -------------------------------------------------------------------------------------------------------

// # final: can't be changed
// - used to prevent something from being changed, overridden, or inherited, depending on where it is used.

/*

It has three uses:

Used on:	    Effect:
Variable	    Becomes a constant, assigned only once
Method	        Can’t be overridden
Class	        Can’t be extended



ex:

class Main {
    public static void main(String[] args) {
        final int age = 21;

        System.out.println(age);

        // age = 22;  // Compilation error
    }
}


ex:

class Animal {
    final void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    // void sound() { }  // Compilation error
}


ex:

final class Animal {
}

// class Dog extends Animal { }  // Compilation error




- Can we override a final method? No.
- Can a final variable be set later? Only if it’s a blank final, once, in the constructor.

*/

