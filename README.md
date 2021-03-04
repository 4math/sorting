# Sorting
A group project of testing and implementing  different sorting algorithms like Quicksort, Counting sort and Shaker sort. 

Project is built using Intellij IDEA 2020.

# Project structure

- [data](./data) folder contains CSV files with test results.
- [slides](.slides) folder contains presentation slides (in Latvian).
- [sorting/src/dip107](./sorting/src/dip107) folder contains all the source files:
  - [CountingSort.java](./sorting/src/dip107/CountingSort.java) - a class which holds counting sort algorithm implementation.
  - [QuickSort.java](./sorting/src/dip107/QuickSort.java) - a class which holds recursive quicksort algorithm implementation.
  - [ShakerSort.java](./sorting/src/dip107/ShakerSort.java) - a class which holds recursive shaker sort algorithm implementation.
  - [TestingFramework.java](./sorting/src/dip107/TestingFramework.java) - a class which allows to test all sorting algorithms by their execution time and to write results into a CSV file. 
  - [Main.java](./sorting/src/dip107/Main.java) - a starting point of a program, consists of console data input.

# Program arguments

| Argument     | Description                                                  |
| ------------ | ------------------------------------------------------------ |
| -fFullTest   | Tests sorting algorithms and writes results from all testing runs and average results to a CSV file. |
| -fOutputAvg  | Tests sorting algorithms and writes average time results to a CSV file. |
| no arguments | Program runs in a default mode. Input menu is shown, where sorting method can be chosen and array elements can be written. |

# Conclusions

- **Quicksort** is a quite fast sorting algorithm, even with 10 million elements it was able to perform in 2 seconds. However, it is crucial to choose correct pivot.
- **Counting sort** is the fastest sorting algorithm of the tested ones. It was able to perform in 17 milliseconds to sort an array with 10 million entries. However, this algorithm can be used only with integers and it takes quite a lot of memory space to perform a sorting operation.
- **Shaker sort** is the slowest algorithm, since its big O is n^2. We do not recommend to use it in practice. 

# Contributions

| Algorithm/Code part | Contributors   | Description                                                  |
| ------------------- | -------------- | ------------------------------------------------------------ |
| Quicksort           | cyoq           | Sorting algorithm implementation                             |
| Counting sort       | Maxim01U       | Sorting algorithm implementation                             |
| Shaker sort         | Ri0ee          | Sorting algorithm implementation                             |
| Testing Framework   | cyoq, Maxim01U | Code for testing algorithms by execution time and writing results to a CSV file |
| Input               | Ri0ee          | Code for getting input from the user                         |

