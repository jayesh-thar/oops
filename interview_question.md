# Java OOP: Interview Notes

Top 10 interview questions on Java OOP fundamentals, explained in simple language with code and output, plus extra points that interviewers commonly ask.

**Format for each question**

| Part | Meaning |
| --- | --- |
| **Answer** | Short, interview-ready answer (say this first) |
| **Explain** | Simple explanation |
| **Example** | Small Java code with output |
| **Trap** | Common mistake or follow-up the interviewer may ask |

## Table of Contents

1. [Q1. OOP and the 4 pillars](#q1-oop-and-the-4-pillars)
2. [Q2. Class vs object and references](#q2-class-vs-object-and-references)
3. [Q3. Constructors](#q3-constructors)
4. [Q4. Overloading vs overriding](#q4-overloading-vs-overriding)
5. [Q5. Runtime polymorphism](#q5-runtime-polymorphism)
6. [Q6. Multiple inheritance and the diamond problem](#q6-multiple-inheritance-and-the-diamond-problem)
7. [Q7. Abstract class vs interface](#q7-abstract-class-vs-interface)
8. [Q8. Encapsulation vs abstraction](#q8-encapsulation-vs-abstraction)
9. [Q9. Access modifiers](#q9-access-modifiers)
10. [Q10. Overriding rules and field hiding](#q10-overriding-rules-and-field-hiding)
11. [Extra points](#extra-points)
12. [Quick revision](#quick-revision)

---

## Q1. OOP and the 4 pillars

**Question:** What is OOP? What are its 4 pillars?

### Answer

OOP (Object-Oriented Programming) is a way of writing programs using **objects** that hold **data** (fields) and **behaviour** (methods). The 4 pillars are **Encapsulation, Abstraction, Inheritance and Polymorphism**.

### Explain

| Pillar | Meaning | Java tool |
| --- | --- | --- |
| Encapsulation | Hide data, allow access only through methods | `private` fields + public methods |
| Abstraction | Show **what** it does, hide **how** | `abstract class`, `interface` |
| Inheritance | Child reuses parent's code (IS-A) | `extends` |
| Polymorphism | Same method name, different behaviour | overloading, overriding |

**Real-life example: a car**

- Encapsulation: the engine is hidden inside, you use the pedals.
- Abstraction: you press accelerate, you don't know the fuel injection details.
- Inheritance: `SportsCar` is a `Car`, so it reuses Car features.
- Polymorphism: `start()` works for petrol, diesel and electric cars, each in its own way.

### Trap
>
> **Is Java 100% object-oriented?** No. Java has 8 primitive types (`int`, `char`, `boolean`, etc.) which are not objects, and `static` members work without an object.

---

## Q2. Class vs object and references

**Question:** What is the difference between a class and an object? What is a reference variable?

### Answer

A **class** is a blueprint. An **object** is a real instance of that blueprint, created in **heap** memory using the `new` keyword. A **reference variable** stores the address of the object, not the object itself.

### Explain

- Class = house plan (design only).
- Object = actual house built from the plan.
- One class can create many objects, each with its own data.

```java
Student s1 = new Student();
```

| Part | Meaning |
| --- | --- |
| `Student s1` | Reference variable (the remote control) |
| `new Student()` | Object created in heap |
| `=` | Address of the object stored in `s1` |

### Example

```java
Student s1 = new Student();
s1.name = "Jayesh";
Student s2 = s1;        // copies the REFERENCE, not the object
s2.name = "Rahul";
System.out.println(s1.name);
```

Output:

```text
Rahul
```

**Why:** `s1` and `s2` point to the **same** object, so a change through one is visible through the other.

### Trap

- Calling a method on a `null` reference throws `NullPointerException`.
- Fields get default values (`0`, `false`, `null`), but **local variables do not**. Using one unassigned is a compile error.

---

## Q3. Constructors

**Question:** What is a constructor? Can it be inherited or overridden?

### Answer

A constructor is a special block that runs **automatically when an object is created** and initialises it. It has the same name as the class and **no return type**. It can be **overloaded**, but it can **not** be inherited or overridden.

### Explain

- If you write no constructor, the compiler adds a **default** (empty, no-argument) constructor.
- The moment you write **any** constructor, the compiler stops adding the default one.
- `this(...)` calls another constructor of the same class (constructor chaining, must be the first line).
- `super(...)` calls the parent constructor (must be the first line).
- If you don't write `super(...)`, the compiler adds `super();` itself.

### Example

```java
class Animal {
    Animal() { System.out.println("Animal constructor"); }
}

class Dog extends Animal {
    Dog() { System.out.println("Dog constructor"); }
}

new Dog();
```

Output:

```text
Animal constructor
Dog constructor
```

**Why:** the parent part of the object is built first, then the child.

### Trap

- `void Student() { }` is **not** a constructor. It has a return type, so it is a normal method (compiles fine, classic trick).
- If the parent has only a parameterized constructor, the child must call `super(args)` explicitly, otherwise a compile error.
- A constructor **can be private** (used in Singleton).

---

## Q4. Overloading vs overriding

**Question:** What is the difference between method overloading and method overriding?

### Answer

**Overloading:** same method name, **different parameters**, in the **same class**, decided at **compile time**.
**Overriding:** child class gives its **own version** of a parent method with the **same name and same parameters**, decided at **runtime**.

### Explain

| | Overloading | Overriding |
| --- | --- | --- |
| Where | Same class | Parent + child |
| Parameters | Must be different | Must be same |
| Binding | Compile time | Runtime |
| Needs inheritance | No | Yes |
| Return type | Can differ | Same or subtype |

### Example

Overloading:

```java
int    add(int a, int b)       { return a + b; }
double add(double a, double b) { return a + b; }

add(2, 3);      // 5
add(2.5, 3.5);  // 6.0
```

Overriding:

```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}

class Dog extends Animal {
    @Override
    void sound() { System.out.println("Bark"); }
}
```

### Trap

- Changing **only the return type** is not overloading. It is a compile error.
- Always write `@Override` so the compiler catches typos.

---

## Q5. Runtime polymorphism

**Question:** What is runtime polymorphism (dynamic method dispatch)?

### Answer

When a **parent reference points to a child object** and an overridden method is called, the JVM decides **at runtime** which version to run, based on the **actual object**, not the reference type.

### Explain

- **Reference type** decides what you are *allowed to call*.
- **Object type** decides *which version actually runs*.

### Example

```java
class Animal {
    void sound() { System.out.println("Animal"); }
}

class Dog extends Animal {
    void sound() { System.out.println("Dog barks"); }
    void fetch() { System.out.println("Dog fetches"); }
}

Animal a = new Dog();   // upcasting
a.sound();              // Dog barks
a.fetch();              // COMPILE ERROR: Animal has no fetch()
((Dog) a).fetch();      // OK after downcasting
```

**Why it matters:** one loop can handle `Dog`, `Cat`, `Cow` through an `Animal` array. Add a new animal tomorrow and the loop code does not change.

### Trap

- A wrong downcast (an `Animal` that is really a `Cat` cast to `Dog`) throws `ClassCastException`. Use `instanceof` to check first.
- **Fields are not polymorphic.** If `Parent` and `Child` both have a field `x`, then `Parent p = new Child();` and `p.x` gives the **parent's** `x`.

---

## Q6. Multiple inheritance and the diamond problem

**Question:** Why does Java not support multiple inheritance with classes? How do we achieve it then?

### Answer

To avoid the **diamond problem** (ambiguity). Java allows only **one parent class**, but a class can **implement multiple interfaces**.

### Explain

Suppose `B` and `C` both extend `A`, and both override `show()`. If `D` extends both `B` and `C`, which `show()` should `D` get? It is ambiguous, so Java blocks it.

Interfaces are safe because they traditionally only declare methods (no state, no body), so there is nothing conflicting to inherit.

### Example

```java
interface Flyable   { void fly(); }
interface Swimmable { void swim(); }

class Duck implements Flyable, Swimmable {
    public void fly()  { System.out.println("Duck flies"); }
    public void swim() { System.out.println("Duck swims"); }
}
```

### Trap

Since Java 8, interfaces can have `default` methods. If two interfaces have the **same default method**, the class **must override** it and can pick one:

```java
class C implements A, B {
    @Override
    public void hello() {
        A.super.hello();   // choose A's version
    }
}
```

---

## Q7. Abstract class vs interface

**Question:** What is the difference between an abstract class and an interface? *(Most asked)*

### Answer

An **abstract class** is a common base with shared code and state for closely related classes. An **interface** is a contract that defines a capability any class can adopt.

### Explain

| | Abstract class | Interface |
| --- | --- | --- |
| Keyword | `extends` | `implements` |
| Multiple inheritance | No (only one) | Yes (many) |
| Methods | Abstract + normal | Abstract, plus `default` / `static` |
| Fields | Any kind | Only constants (`public static final`) |
| Constructor | Yes | No |
| Relationship | IS-A | CAN-DO |

**When to use what**

- Related classes sharing code or state: **abstract class** (`Animal` → `Dog`, `Cat`).
- Unrelated classes sharing a skill: **interface** (`Bird`, `Plane`, `Drone` can all be `Flyable`).

### Example

```java
abstract class Animal {
    abstract void sound();      // no body, child must implement

    void sleep() {
        System.out.println("Sleeping...");
    }
}

interface Payment {
    void pay();
}
```

### Trap

- You cannot create an object of an abstract class or interface, but you **can** create a reference of that type pointing to a child object: `Payment p = new UpiPayment();`
- A child must implement **all** abstract methods, or be abstract too.
- `abstract` cannot be combined with `final`, `private` or `static`.

---

## Q8. Encapsulation vs abstraction

**Question:** What is the difference between encapsulation and abstraction?

### Answer

**Encapsulation** is hiding data and protecting it with access control (**how** you protect). **Abstraction** is hiding the implementation and showing only essential features (**what** you show).

### Explain

- **Encapsulation, ATM machine:** you cannot touch the cash vault, you only use buttons, and the machine validates your request.
- **Abstraction:** you press "withdraw", you don't need to know how the bank's server processes it.

| | Level | Tool |
| --- | --- | --- |
| Encapsulation | Data level | `private` fields + methods |
| Abstraction | Design level | `abstract class`, `interface` |

### Example

```java
class Student {
    private int age = 20;

    public void setAge(int a) {
        if (a > 0) { age = a; }      // validation
    }

    public int getAge() { return age; }
}

// s.age = -10;     -> COMPILE ERROR (private)
// s.setAge(21);    -> allowed, validated
```

### Trap

- Getters and setters **without validation** are no better than a public field. The value of encapsulation is **control**.
- If a getter returns a mutable object (`List`, `Date`) directly, the caller can modify your internal data. Return a copy.

---

## Q9. Access modifiers

**Question:** Explain the access modifiers in Java.

### Answer

Java has 4 access levels: `private`, default (no keyword), `protected` and `public`. They control who can access a class, field, method or constructor.

### Explain

| Modifier | Same class | Same package | Subclass (other package) | Everywhere |
| --- | :---: | :---: | :---: | :---: |
| `private` | ✅ | ❌ | ❌ | ❌ |
| default | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ (through inheritance) | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

**Memory trick:** `private` < default < `protected` < `public` (increasing openness).

### Trap

- **Top-level classes** can only be `public` or default. A `private` or `protected` top-level class is not allowed.
- A file can have only **one public class**, and the file name must match it.
- Default is also called **package-private**.
- `protected` in another package: the subclass can access it only through inheritance (its own object), not through a plain parent-type reference.

---

## Q10. Overriding rules and field hiding

**Question:** Can we override `static`, `private` and `final` methods? What happens with fields in inheritance?

### Answer

**No.** `final` methods cannot be overridden, `private` methods are not visible to the child, and `static` methods are **hidden**, not overridden. Fields are never overridden, they are **hidden**.

### Explain

| Member | What happens |
| --- | --- |
| `final` method | Locked, overriding gives a compile error |
| `private` method | Child cannot see it, a same-named child method is a completely **new** method |
| `static` method | Belongs to the class, the method that runs depends on the **reference type** (method hiding) |
| Field | Reference type decides, not the object |

Only **instance methods** are polymorphic. Fields are not.

### Example (field hiding)

```java
class Parent { int x = 10; }
class Child extends Parent { int x = 20; }

Parent p = new Child();
System.out.println(p.x);            // 10
System.out.println(((Child) p).x);  // 20
```

### Rules of overriding to remember

1. Same name and same parameters.
2. Return type same, or a subtype (covariant return).
3. Access cannot be more restrictive (`public` cannot become `private`, but it can widen).
4. Cannot throw broader checked exceptions than the parent.
5. Always use `@Override`.

---

## Extra points

Common interview asks that go beyond the 10 questions.

### `static` rules

- A static method **cannot use `this` or `super`** (no current object exists).
- A static method cannot directly use **non-static** fields or methods.
- `main` is `static` because the JVM calls it **before any object exists**.
- A `static` block runs **once**, when the class is first loaded.

### `final` trap

A `final` **reference** cannot point to another object, but the **object's contents can change**.

```java
final StringBuilder sb = new StringBuilder("Hi");
sb.append(" Jayesh");          // allowed
// sb = new StringBuilder();   // compile error
```

### `this` vs `super`

| Keyword | Meaning | Constructor form |
| --- | --- | --- |
| `this` | Current object | `this(...)` calls another constructor of the same class |
| `super` | Parent part | `super(...)` calls the parent constructor |

Both must be the **first line** in a constructor, so you can't use both together.

### HAS-A (composition)

- Inheritance = **IS-A** (`Dog` is an `Animal`).
- Composition = **HAS-A** (`Car` has an `Engine`).
- **Prefer composition over inheritance** unless there is a true IS-A relationship.

---

## Quick revision

- Class = blueprint, object = instance in heap, reference = remote control.
- Constructor: same name as class, no return type, not inherited.
- Overloading = compile time, overriding = runtime.
- The object decides which method runs, the reference decides what you can call.
- No multiple inheritance with classes (diamond problem), use interfaces.
- Abstract class = shared code (IS-A), interface = capability (CAN-DO).
- Encapsulation = protect data, abstraction = hide implementation.
- Access: `private` < default < `protected` < `public`.
- `static` / `private` / `final` methods cannot be overridden.
- Fields are hidden, not overridden.
