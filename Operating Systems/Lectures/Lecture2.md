# Concurrency

## What is Concurrency?

In computer science:

* A **sequential system** is one where computations - or parts of a computation - are executed to completion, one after the other.

* A **concurrent system** is one where two or more computations are executing – literally or effectively – “at the same time”.

* A concurrent system is almost the same as a **parallel system**. We sometimes reserve the latter for the case where computations are literally proceeding at the same time.

## Scope of Concurrency

Concurrency and associated issues arise in various contexts:

* **Multi-tasking operating systems**, where many processes are running at once.

* **Individual applications** like Web servers, that must be processing many “requests” at the same time.

* **Multicore processors** where a single application is running across more than one core.

* **Parallel computers** in general.

* **Distributed systems** in general.

Concurrency issues are pervasive in modern computing systems.

## Processes and Threads

* A **thread** or **thread of control** is a specific sequence of instructions defined by some program or section of a program.

* Instruction sequences from one thread may run in parallel with or be interleaved in an unpredictable way with sequences from other threads.

* Any process has one or more threads.

* Processes also have additional structure associated with them, such as address spaces. Processes will be discussed in detail in later lectures.

* In most of this lecture, we focus on threads themselves.

## Processes and Threads

* Every process has at least one control flow within a single "address space".

* A process may also have multiple control flows within the same address space.

## Parallelism vs. Multitasking

* On a multi-core processor, threads may run on different cores, truly in parallel.

* However, when discussing concurrency in operating systems, multiple threads often share the same "core" through multitasking.

Multitasking involves rapidly switching between threads, giving the illusion of simultaneous execution. The operating system manages this context switching, allocating CPU time slices to each thread.

In contrast, parallelism involves genuinely concurrent execution of threads on separate cores, simultaneously processing instructions. This requires specialized hardware and is typically employed for computationally intensive tasks.

To summarize, multitasking simulates concurrency by interleaving threads on a single core, while parallelism achieves true concurrency by utilizing multiple cores.

## Concurrent Programming

* How do we start writing concurrent programs?

* Historically, many have promoted new programming languages with special "parallel" constructs.

* Today, it is more common to use thread libraries.

## Example from occam Language

* Popular in the UK in the 1980s and 1990s:

```occam
PAR
    SEQ
        x = 23
        print x
    SEQ
        y = 42
        print y
```

* `PAR` means "do following in parallel", `SEQ` means "do following in sequence".

* The code above runs blue and red threads in parallel.

## POSIX Threads

* A low-level library for thread programming, often used from the C programming language.

* The code for a new thread is defined inside some C function.

* A parent thread (e.g., "main program") calls the library function `pthread_create`, passing it a pointer to a function with the code for the new thread.

* A parent thread may create any number of threads, and children can create their own children, etc.

## Creating a POSIX Thread

```c
int main(int argc, char* argv []) {
    pthread_t thread;
    pthread_create(&thread, NULL, run, NULL);
    x = 23;
    print x;
}

void* run(void *) {
    y = 42;
    print y;
}
```

## Concurrency in Java

* Java occupies a middle ground between older, full-blown parallel languages like occam and languages like C that "just" do threads using libraries.

* It doesn't contain explicit parallel constructs, but many features of the language have been carefully designed to support concurrency.

* Modifiers like `synchronized` and `volatile` on declarations (see later lectures and labs)

* `synchronized` construct

* All carefully integrated with the Java memory model.

## Java Threads

* Thread creation in Java is similar in style to POSIX threads, except it follows an object-oriented pattern.

* The "run" method is defined in a class extending `java.lang.Thread`.

* Create an object of this class, then call the `start` method to begin the thread.

## Creating a Java Thread

```java
public static void main(String[] args) {
    MyThread thread = new MyThread(); // creating thread
    thread.start(); // begin running thread B
    
    int x = 23; // thread A executes in parallel with thread B
    System.out.println(x);

    thread.join();
}

public static class MyThread extends Thread {
    public void run() { // work for thread B
        int y = 42;
        System.out.println(y);
    }
}
```

# Non-Determinism

## A Multi-threaded Program

Consider this simple program with two threads, A and B:

**Thread A:**

```
x = 23
print x
```

**Thread B:**

```
y = 42
print y
```

Each thread assigns a value to a local variable and prints it out.

## Orders of Execution

Assuming individual statements like "x = 23" and "print y" happen instantaneously and no two statements execute simultaneously, there are still many possible orders of execution.


## Lessons

This simple example illustrates two general features of concurrent programs:

1. **Many possible orders of execution:** The number of possible orderings grows exponentially with program size, making concurrent programs hard to design and debug.

2. **Non-determinism:** Different orders of execution may lead to different outcomes.

## A Shared Counter

Things become more complex when different threads share access to the same variable:

**Thread A:**

```
x = c
c = x + 1
```

**Thread B:**

```
y = c
c = y + 1
```

Each thread tries to increment the value of variable `c` by 1.

## Possible Execution Orders

The final value of `c` depends on the execution order.

## Interference

This is a more serious case of non-determinism. The programmer may have expected each thread to increment `c` by 1, resulting in an increment of 2. This unpredictable behavior, where concurrent threads adversely affect each other, is called interference.

## Race Conditions

Situations like this are also called race conditions because the outcome depends on which thread reaches a particular point first. Interference and race conditions are essentially the same in this context.

## Avoiding Interference

One way to avoid race conditions is to ensure threads never share variables. This is essentially how processes work, each having an independent address space and no shared variables.

## Critical Sections

In the counter example, correct results require no overlap between the execution of these sections:

```
x = c
c = x + 1
```

and

```
y = c
c = y + 1
```

In general, sections of code that must not overlap are called critical sections.

## Mutual Exclusion

Techniques to ensure that critical sections do not overlap during execution are called mutual exclusion. Mutual exclusion is another example of synchronization between threads.


## Summary

- Concurrency plays a crucial role in various contexts, including operating systems, multithreaded applications, and parallel computing.

- Concurrent programming introduces challenges such as non-determinism and race conditions, where the outcome of a program depends on the unpredictable order of thread execution.

- Thread creation mechanisms vary across programming languages, with examples provided for occam, POSIX threads, and Java threads.

- Critical sections are code segments that must not overlap in execution to avoid race conditions. Mutual exclusion techniques ensure that critical sections are executed atomically, preventing interference between threads.