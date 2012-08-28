LazyList
========

A specialized list for performing operations on a very large set of elements.
This list guarantees O(1) for all operations applied to the list.  Reads and
updates to the list are guaranteed to be O(n), where n is the number of
operations that have been applied to the list. Deletes are O(1).

The primary advantage of the LazyList is to support quickly applying a small
number of operations that need to be performed on very large lists. Iterators
are provided that allow for easy iteration across the list, including an
iterator that updates each value and clears the list of operations for faster
reads.

Note, that this class does NOT update the value of each element as it reads
it.  The only way to do this is to use a LazyUpdaterIterator to update
each element and then flush the operations list.

Example Usage
-------------

	LazyList list = new LazyList();
	list.add(1);
	list.add(2);
	list.add(4);
	list.add(7);
	list.add(12);
	list.multiply(3);

	Result: [3, 6, 12, 21, 36]

Usage Dependencies
------------------
* Java 1.6

***

Project Information
===================

Authors
-------
Grant Kurtz

Repository
--------------
https://github.com/grnt426/LazyList

Project Dependencies
--------------------
* Java: 1.6
* Maven Library: apache-maven-3.0.4

Testing
-------
	
	mvn test