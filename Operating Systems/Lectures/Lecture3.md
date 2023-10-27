# Mutual Exclusion

### General Introduction

In concurrent programming, mutual exclusion is a property of synchronization control that ensures that no two processes or threads can access a shared resource at the same time. This is important because concurrent access to shared resources can lead to race conditions, which can cause data corruption or other errors.

### Critical Sections

A critical section is a section of code that accesses or modifies a shared resource. It is important to ensure that only one thread can be in its critical section at a time, otherwise a race condition could occur.

### Attempts at Implementing Mutual Exclusion

There are many different ways to implement mutual exclusion. One early attempt was to use a global lock variable. Each thread would check the lock variable before entering its critical section. If the lock variable was set, the thread would wait until it was unset before proceeding. This approach works, but it can be inefficient and can lead to deadlocks.

### Peterson's Algorithm

Peterson's algorithm is a more sophisticated approach to mutual exclusion. It is a lock-free algorithm, which means that it does not use any locks. Instead, it relies on atomic instructions and memory barriers to ensure that only one thread can be in its critical section at a time.

### More Practical Techniques for Mutual Exclusion

In practice, there are a number of more practical techniques for implementing mutual exclusion. These include:

* **Atomic instructions:** Atomic instructions are instructions that execute as a whole, without interruption. This makes them ideal for implementing mutual exclusion.
* **Semaphores:** Semaphores are a synchronization primitive that can be used to implement mutual exclusion. A semaphore is a variable that can be used to signal between threads.
* **Monitors:** Monitors are a high-level synchronization primitive that can be used to implement mutual exclusion. They provide a number of features that make them easy to use, such as condition variables and priority inheritance.

### Example

Consider the following example of two threads updating a shared counter variable:

```
repeat
x = c
c = x + 1
<<do other work>>
forever;
```

This code is vulnerable to a race condition. If both threads try to update the counter variable at the same time, they could end up overwriting each other's changes. To avoid this race condition, we can use mutual exclusion to ensure that only one thread can update the counter variable at a time.

### OS Terminology

The following are some important OS terms related to mutual exclusion:

* **Race condition:** A race condition is a situation where the outcome of a computation depends on the timing or order of execution of the tasks.
* **Critical section:** A critical section is a section of code that accesses or modifies a shared resource.
* **Mutual exclusion:** Mutual exclusion is a property of synchronization control that ensures that no two processes or threads can access a shared resource at the same time.

### Atomicity

Atomicity is the property of an operation being indivisible. In the context of concurrent programming, an atomic operation is one that cannot be interrupted by another thread.

### Race Conditions are Evil

Race conditions are a particularly pernicious kind of programming error. They are difficult to debug because they can occur non-deterministically. This means that they may not occur when the program is tested, but may occur when the program is deployed in the real world.

### Avoiding a Race Condition

To avoid a race condition in the example above, we can use mutual exclusion to ensure that only one thread can update the counter variable at a time. This can be done using a variety of techniques, such as atomic instructions, semaphores, or monitors.

## Mutual Exclusion Using Shared Variables

### Notes on the Naïve Attempt at Mutual Exclusion (lock)

**Introduction**

The naïve attempt at mutual exclusion using a lock variable is a simple approach, but it has a major flaw: it does not guarantee mutual exclusion. This means that it is possible for two threads to be in their critical sections at the same time.

**The Algorithm**

The algorithm works as follows:

1. Each thread checks the lock variable. If the lock is false, the thread sets it to true and enters its critical section.
2. If the lock is true, the thread waits until the lock is set to false before entering its critical section.
3. When a thread leaves its critical section, it sets the lock to false.

**The Problem**

The problem with this algorithm is that there is a race condition in the test and update of the lock variable. If two threads check the lock variable at the same time and both threads see that the lock is false, both threads will set the lock to true and enter their critical sections. This is a violation of mutual exclusion, as two threads are now in their critical sections at the same time.

**Example**

Consider the following example of two threads using the naïve mutual exclusion algorithm to access a shared counter variable:

```
// Thread 0
int counter;

bool lock = false;

void critical_section() {
  // Access and modify the counter variable.
}

void process() {
  while (lock) {} // Wait until the lock is free.

  lock = true;
  critical_section();
  lock = false;
}
```

```
// Thread 1
int counter;

bool lock = false;

void critical_section() {
  // Access and modify the counter variable.
}

void process() {
  while (lock) {} // Wait until the lock is free.

  lock = true;
  critical_section();
  lock = false;
}
```

In this example, it is possible for both threads to enter their critical sections at the same time. This could happen if both threads check the lock variable at the same time and both threads see that the lock is false. Both threads would then set the lock to true and enter their critical sections.

**Conclusion**

The naïve attempt at mutual exclusion using a lock variable is a simple approach, but it does not guarantee mutual exclusion. It is important to use more sophisticated mutual exclusion algorithms, such as Peterson's algorithm, to ensure that only one thread can be in its critical section at a time.

### Notes on the Second Attempt at Mutual Exclusion (turn)

**Introduction**

The second attempt at mutual exclusion using a turn variable is a more sophisticated approach than the naïve attempt using a lock variable. It guarantees mutual exclusion, but it does not guarantee progress. This means that it is possible for one thread to be starved of access to the critical section if the other thread continues to occupy the critical section for a long time.

**The Algorithm**

The algorithm works as follows:

1. Each thread checks the turn variable. If the turn variable is equal to the thread's identity, the thread enters its critical section.
2. If the turn variable is not equal to the thread's identity, the thread waits until the turn variable is equal to its identity before entering its critical section.
3. When a thread leaves its critical section, it sets the turn variable to the other thread's identity.

**The Problem**

The problem with this algorithm is that it does not guarantee progress. This means that it is possible for one thread to be starved of access to the critical section if the other thread continues to occupy the critical section for a long time.

**Example**

Consider the following example of two threads using the second mutual exclusion algorithm to access a shared counter variable:

```
// Thread 0
int counter;

int turn = 0;

void critical_section() {
  // Access and modify the counter variable.
}

void process() {
  while (turn != 0) {} // Wait until it is my turn.

  turn = 1;
  critical_section();
  turn = 0;
}
```

```
// Thread 1
int counter;

int turn = 0;

void critical_section() {
  // Access and modify the counter variable.
}

void process() {
  while (turn != 1) {} // Wait until it is my turn.

  // Stay in the critical section forever.
  // This will starve thread 0 of access to the critical section.
  critical_section();
}
```

In this example, thread 1 can starve thread 0 of access to the critical section by staying in the critical section forever.

**Conclusion**

The second attempt at mutual exclusion using a turn variable is a more sophisticated approach than the naïve attempt using a lock variable, but it does not guarantee progress. It is important to use more sophisticated mutual exclusion algorithms, such as Peterson's algorithm, to ensure that all threads have a fair chance of accessing the critical section.

### Notes on the Third Attempt at Mutual Exclusion (interested)

**Introduction**

The third attempt at mutual exclusion using interested flags is more sophisticated than the second attempt using a turn variable, but it still does not guarantee progress. It is possible for both threads to get stuck in a deadlock situation, where both threads are waiting for the other thread to leave the critical section.

**The Algorithm**

The algorithm works as follows:

1. Each thread sets its interested flag to true to indicate that it wants to enter the critical section.
2. Each thread checks the other thread's interested flag. If the other thread's interested flag is true, the thread waits until the other thread's interested flag is set to false before entering the critical section.
3. When a thread leaves the critical section, it sets its interested flag to false to indicate that it is no longer interested in entering the critical section.

**The Problem**

The problem with this algorithm is that it is possible for both threads to get stuck in a deadlock situation. This can happen if the two threads set their interested flags to true at the same time and then both threads check the other thread's interested flag. Both threads will see that the other thread's interested flag is true, so both threads will wait until the other thread's interested flag is set to false. However, neither thread will ever set its interested flag to false, because both threads are waiting for the other thread to do it. As a result, both threads will be stuck in a deadlock situation forever.

**Example**

Consider the following example of two threads using the third mutual exclusion algorithm to access a shared counter variable:

```
// Thread 0
int counter;

bool interested[2] = {false, false};

void critical_section() {
  // Access and modify the counter variable.
}

void process() {
  interested[0] = true;
  while (interested[1]) {} // Wait until the other thread is no longer interested.

  critical_section();
  interested[0] = false;
}
```

```
// Thread 1
int counter;

bool interested[2] = {false, false};

void critical_section() {
  // Access and modify the counter variable.
}

void process() {
  interested[1] = true;
  while (interested[0]) {} // Wait until the other thread is no longer interested.

  critical_section();
  interested[1] = false;
}
```

In this example, it is possible for both threads to get stuck in a deadlock situation. This can happen if both threads set their interested flags to true at the same time and then both threads check the other thread's interested flag. Both threads will see that the other thread's interested flag is true, so both threads will wait until the other thread's interested flag is set to false. However, neither thread will ever set its interested flag to false, because both threads are waiting for the other thread to do it. As a result, both threads will be stuck in a deadlock situation forever.

**Conclusion**

The third attempt at mutual exclusion using interested flags is more sophisticated than the second attempt using a turn variable, but it still does not guarantee progress. It is important to use more sophisticated mutual exclusion algorithms, such as Peterson's algorithm, to ensure that all threads have a fair chance of accessing the critical section and to avoid deadlocks.

### Notes on Peterson's Algorithm

**Introduction**

Peterson's algorithm is a simple and elegant solution to the mutual exclusion problem. It is guaranteed to establish mutual exclusion and progress, even in the presence of concurrent access to the shared variables.

**The Algorithm**

The algorithm works as follows:

1. Each thread sets its interested flag to true to indicate that it wants to enter the critical section.
2. Each thread yields the turn to the other thread.
3. Each thread waits until the other thread's interested flag is set to false and the turn variable is equal to its own identity before entering the critical section.
4. When a thread leaves the critical section, it sets its interested flag to false.

**How it Works**

Peterson's algorithm works by ensuring that only one thread can set the turn variable to the other thread's identity. This is achieved by having the threads yield the turn to the other thread before they check the other thread's interested flag. If the other thread is also interested in entering the critical section, it will have already set the turn variable to its own identity. This means that the first thread will have to wait until the other thread leaves the critical section before it can enter its own critical section.

**Progress**

Peterson's algorithm guarantees progress, even in the presence of concurrent access to the shared variables. This is because each thread will eventually be able to set the turn variable to the other thread's identity. If the other thread is not interested in entering the critical section, it will not set the turn variable to its own identity. This means that the first thread will be able to enter the critical section without having to wait.

**Deadlocks**

Peterson's algorithm is also deadlock-free. This means that it is impossible for the two threads to get stuck in a deadlock situation, where both threads are waiting for the other thread to do something.

**Conclusion**

Peterson's algorithm is a simple and elegant solution to the mutual exclusion problem. It is guaranteed to establish mutual exclusion and progress, even in the presence of concurrent access to the shared variables. It is also deadlock-free.

**Example**

The following example shows how two threads can use Peterson's algorithm to access a shared counter variable:

```
// Thread 0
int counter;

bool interested[2] = {false, false};
int turn = 1;

void critical_section() {
  // Access and modify the counter variable.
}

void process() {
  interested[0] = true;
  turn = 1;
  while (interested[1] && turn == 1) {}

  critical_section();

  interested[0] = false;
}
```

```
// Thread 1
int counter;

bool interested[2] = {false, false};
int turn = 0;

void critical_section() {
  // Access and modify the counter variable.
}

void process() {
  interested[1] = true;
  turn = 0;
  while (interested[0] && turn == 0) {}

  critical_section();

  interested[1] = false;
}
```

In this example, the two threads will never be able to enter their critical sections at the same time. This is because Peterson's algorithm ensures that only one thread can set the turn variable to the other thread's identity.

### Notes on Practical Approaches to Mutual Exclusion

**Introduction**

There are a number of practical approaches to mutual exclusion, each with its own advantages and disadvantages. Some of the most common approaches include:

* **Hardware support:** Some hardware architectures provide specialized instructions that can be used to implement mutual exclusion efficiently. For example, the x86 architecture provides the `XCHG` instruction, which can be used to atomically swap the contents of two registers. This instruction can be used to implement a variety of synchronization primitives, including mutual exclusion locks.
* **OS support:** Most operating systems provide system calls that can be used to implement mutual exclusion. For example, semaphores are a common synchronization primitive that is provided by many operating systems. Semaphores are integer variables that can be used to control access to shared resources.
* **Programming language support:** Some programming languages provide built-in support for mutual exclusion. For example, the Java programming language provides synchronized methods and blocks. Synchronized methods and blocks ensure that only one thread can be executing a synchronized method or block at a time.

**Peterson's Algorithm**

Peterson's algorithm is a classic mutual exclusion algorithm that can be implemented using only shared memory. It is a simple and elegant algorithm, but it is not particularly practical for use in real-world systems. Peterson's algorithm relies on busy waiting, which means that threads waste CPU time while they are waiting to enter the critical section.

**Test and Set Lock Instruction**

The test and set lock (TSL) instruction is a hardware instruction that can be used to implement mutual exclusion efficiently. The TSL instruction atomically sets a bit in memory and returns the previous value of the bit. This instruction can be used to implement a mutual exclusion lock as follows:

```
repeat
  while (TestAndSet(lock)) do nothing;
  // Critical section
  lock = false;
  // Do normal work
forever;
```

This code ensures that only one thread can be executing the critical section at a time.

**Semaphores**

Semaphores are a synchronization primitive that can be used to implement mutual exclusion. A semaphore is an integer variable that can be accessed using only two operations: `P()` and `V()`. The `P()` operation decreases the value of the semaphore by one. If the value of the semaphore is already zero, the `P()` operation blocks the calling thread until another thread calls the `V()` operation on the semaphore. The `V()` operation increases the value of the semaphore by one.

To implement mutual exclusion with semaphores, we can use the following code:

```
semaphore S = 1;

repeat
  P(S);
  // Critical section
  V(S);
  // Do normal work
forever;
```

This code ensures that only one thread can be executing the critical section at a time.

**Java Synchronized Methods**

Java provides synchronized methods and blocks, which can be used to implement mutual exclusion. A synchronized method or block ensures that only one thread can be executing it at a time. To declare a synchronized method, we use the `synchronized` modifier. For example:

```
class MyClass {
  synchronized void mySynchronizedMethod() {
    // Critical section
  }
}
```

To execute a synchronized method, we simply call it. If another thread is already executing the method, the calling thread will block until the other thread has finished executing the method.

**Conclusion**

There are a number of practical approaches to mutual exclusion. The best approach to use depends on the specific needs of the application. For example, if performance is critical, we may want to use a hardware-based approach, such as the TSL instruction. If we are developing for a particular operating system, we may want to use the synchronization primitives that are provided by that operating system. If we are using a programming language that provides built-in support for mutual exclusion, such as Java, we may want to use that support.