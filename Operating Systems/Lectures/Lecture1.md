# Introduction

### What is an Operating System?

- It is a system software that manage computer hardware resources and control processing.

- It allows multiple computational processes and users to share a processor simultaneously, protect data from unauthorized access and keep independent input/output (I/O) devices operating correctly.

- It provide a common services for application software.

- Users can not run any software application without it.

| Feature | System Software | Application Software |
|---|---|---|
| Definition | The operating system and utility programs that control a computer system and allow you to use your computer | Programs that allow a user to perform specific tasks on a computer |
| Purpose | Enables the boot process, launches applications, transfers files, controls hardware configuration, manages files on the hard drive, and protects from unauthorized use | Word processing, playing games, browsing the Web, listening to music, etc. |

### Characteristics of the OS

**OS as "Extended Machine"**

* I/O (for example) involves reading and writing control registers, handling interrupts, etc.
* Mistakes can crash the entire computer.
* The OS provides a cleaner, safer, higher-level set of operations for performing these tasks.

**OS as "Resource Manager"**

* In a modern OS, many different processes are running simultaneously; these processes must share resources.
* The OS arbitrates between the requests these processes make to I/O subsystems, memory, etc., to ensure the smooth functioning of the system.

### CPU

* The CPU reads a program from memory and executes that program

* A modern CPU is complex, containing control unit, Arithmetic Logic Units, cache memory, memory management unit, etc.

### Registers

**What are registers?**

Registers are very fast storage locations built into the CPU, typically used to hold individual words of data. They are small amounts of high-speed memory contained within the CPU itself.

**What are registers used for?**

Registers are used by the processor to store small amounts of data that are needed during processing. This data can include:

* The address of the next instruction to be executed
* The current instruction being decoded
* Intermediate results of calculations

**Why are registers important?**

Registers are important because they play a key role in OS design. They form part of the state of a computation, and the OS needs to be able to save and restore the state of registers when switching between processes.

**What are general-purpose registers (GPRs)?**

Most computer architectures provide a small set of general-purpose registers (GPRs). These registers can be used to store any type of data, and they are the most commonly used registers in a CPU.

**Additional details about registers:**

* Modern CPUs typically have 32-bit or 64-bit registers.
* The number of registers in a CPU can vary depending on the architecture.
* Some registers have special purposes, such as the instruction pointer (IP) register, which stores the address of the next instruction to be executed.

### General-Purpose Registers (GPRs)

**What are GPRs?**

General-purpose registers (GPRs) are a type of register that can be used to store any type of data. They are the most commonly used registers in a CPU.

**How many GPRs does an X86 CPU have?**

An X86 CPU has around eight GPR registers with names like EAX, EBX, ECX, EDX, EBP, ESI, EDI, and ESP.

**What are special-purpose registers?**

In addition to GPRs, CPUs also have special-purpose registers. These registers have specific functions, such as storing the address of the next instruction to be executed (the program counter) or controlling the mode the CPU is operating in (the program status word).

**Example of a special-purpose register:**

The program status word (PSW) is a special-purpose register that sets the mode the CPU is operating in. This register can be used to control things like whether the CPU is in protected mode or real mode.

### Assembly Language

Assembly language is a low-level programming language that is a symbolic representation of machine code. It is used by system programmers to write code that directly interacts with the hardware.

**Example of Assembly Language Instructions:**

```assembly
MOV EBX, EAX
ADD EBX, 4
```

These two instructions in Intel assembler perform the following operations:

1. The `MOV EBX, EAX` instruction copies the contents of register EAX to register EBX.

2. The `ADD EBX, 4` instruction increases the value in register EBX by 4.

In essence, these instructions are equivalent to the following high-level code:

```python
b = a + 4
```

Assembly language provides a more direct way to control the hardware and can be used to write very efficient code. However, it is also more difficult to learn and use than high-level languages.

### Instructions for Accessing Memory

The `MOV` instruction in Pentium assembly language can be used to transfer data between main memory and registers. It can move data from a memory location to a register or vice versa.

**Example:**

```assembly
MOV ESI, 105672
MOV EAX, [ESI]
```

In this example, the first instruction stores the constant value `105672` into the register `ESI`. The second instruction then loads the contents of the memory location pointed to by the value in register `ESI` (which is `105672`) into register `EAX`.

This effectively copies the value stored at memory address `105672` into register `EAX`.

### Low-Level I/O

**How I/O Devices are Controlled:**

I/O devices like hard disks are controlled and data is transferred through a set of associated ports. A range of ports is assigned to each device.

**Special Instructions for I/O:**

Special assembly language instructions `IN` and `OUT` are used to read from or write to ports.

**Example:**

```assembly
IN EAX, 368
```

This instruction reads data from port number 368 and copies the value into the CPU register `EAX`. If port number 368 corresponds to the "data word" in the disk controller, this instruction would retrieve the requested data from the disk controller.

**Significance of Low-Level I/O:**

Low-level I/O provides direct control over hardware devices and allows for efficient data transfer. However, it requires detailed knowledge of the hardware and is more complex than using high-level I/O libraries.

### User and Kernel Modes

**Distinct Modes of Operation:**

Modern CPUs support different modes of operation, typically controlled by a register called the Program Status Word (PSW). This register determines the privileges and restrictions of the currently executing code.

**User Mode:**

When the CPU is in user mode, the executing code has limited access to system resources and cannot directly perform certain privileged operations, such as accessing hardware or modifying memory protection settings. This protects the system from accidental or malicious actions by user applications.

**Kernel Mode:**

In kernel mode, the executing code has unrestricted access to system resources and can perform any operation, including privileged ones. This mode is reserved for the operating system kernel, which is responsible for managing system resources and providing services to user applications.

**Privileged Instructions:**

Certain instructions, such as IN and OUT, are considered privileged and can only be executed in kernel mode. These instructions are used to interact with hardware devices and perform sensitive operations.

**Significance of User and Kernel Modes:**

The separation of user and kernel modes is a fundamental security mechanism in modern operating systems. It prevents user applications from directly accessing hardware or modifying critical system settings, ensuring system stability and preventing unauthorized actions.

**Additional Notes:**

* On recent x86 processors, the mode is controlled by bit 0 of the Control Register (CR0). If this bit is set, the CPU is in user mode (also known as "protected mode").
* The kernel is the core of the operating system and has complete control over everything in the system. It manages hardware resources, provides services to user applications, and enforces security policies.

### OS as "Kernel" Code

The operating system (OS) can be characterized as "the code that runs in kernel mode." This means that the OS has privileged access to system resources and can perform operations that are restricted to kernel mode, such as I/O operations and memory management.

User applications, on the other hand, run in user mode and cannot directly access hardware or perform privileged operations. They must rely on the OS to perform these tasks on their behalf. This separation of user and kernel modes ensures system stability and prevents unauthorized access to sensitive resources.

Therefore, I/O operations, for example, can only be performed directly by the OS, on behalf of application programs. Application programs make requests to the OS, and the OS carries out the requests using its privileged access to hardware and system resources.

This model ensures that user applications cannot disrupt the system or access sensitive data without explicit permission from the OS. It also allows the OS to manage hardware resources efficiently and provide a consistent interface for applications to interact with the system.

### Interrupts: Handling Hardware Events

**Purpose of Interrupts:**

When an I/O controller, such as the one on a disk card, has requested data available, it needs to notify the CPU. This is done by asserting an electrical signal called an interrupt.

**CPU's Response to Interrupts:**

Upon receiving an interrupt, the CPU temporarily halts the execution of the current program and switches to a specialized code called an interrupt handler. This handler is responsible for dealing with the event that triggered the interrupt.

**Interrupt Handlers:**

Interrupt handlers are typically installed at boot time and are designed to handle specific types of interrupts. They run in kernel mode, allowing them to access privileged resources and perform sensitive operations.

**Significance of Interrupts:**

Interrupts provide a mechanism for hardware devices to communicate with the CPU and request its attention. They allow the CPU to respond to events promptly and efficiently, ensuring that the system remains responsive and that data is transferred smoothly.

**Key Points:**

* Interrupts are electrical signals used by hardware devices to notify the CPU of events or requests.
* Interrupt handlers are specialized code that runs in kernel mode to handle specific types of interrupts.
* Interrupts allow the CPU to respond to events without disrupting the execution of user applications.

### The Extended Role of Interrupt Handlers in Operating Systems

While interrupt handlers initially served the purpose of processing data received from I/O controllers, their role has expanded significantly in modern operating systems. They play a crucial role in various aspects of system management, including:

**1. Process Scheduling:**

Interrupt handlers can be used to implement preemptive multitasking, where the operating system can switch between processes based on time slices or priority levels. When an interrupt occurs, the interrupt handler can initiate a context switch, saving the state of the current process and loading the state of the next process to be executed.

**2. System Call Implementation:**

System calls provide an interface for user applications to request services from the operating system. When a user application makes a system call, it triggers an interrupt. The corresponding interrupt handler then handles the system call request, performing the requested operation on behalf of the application.

**3. Handling Exceptions and Errors:**

Interrupt handlers can also be used to handle exceptions and errors that occur during program execution. For instance, if a program attempts to access invalid memory, an interrupt is generated, and the corresponding handler can take appropriate action, such as terminating the program or notifying the user.

In essence, interrupt handlers serve as the backbone of the operating system, enabling it to respond to events, manage processes, and provide services to user applications. They are the glue that connects hardware and software, ensuring a responsive and efficient computing environment.

**Key Points:**

- Interrupt handlers play a crucial role in process scheduling, system call implementation, and error handling.
- They enable the operating system to respond to events promptly and efficiently, ensuring system responsiveness and stability.
- Interrupt handlers are a fundamental building block of modern operating systems, underpinning their ability to manage resources and provide services to applications.