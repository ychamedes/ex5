ychamudot
jasonmg


=============================
=      File description     =
=============================
AbsOrder.java - AbsOrder class used to order a list of files alphanumerically by absolute path.
AllFilter.java- AllFilter class that "selects" for all files.
BetweenFilter.java - BetweenFilter class that selects for files with sizes that are between two bounds.
DirectoryProcessor.java - The main class of the filesprocessing program. Coordinates the actions of all the 
other modules in the package in order to receive and parse a list of commands and files, filter them based on 
a given attribute, and output the file names according to a given order.
ExecutableFilter.java - ExecutableFilter class that selects for files that are executable.
FileNameFilter.java - FileNameFilter class that selects for files that match a given name
Filter.java - Filter interface for filter classes (each sorts its own way).
FilterFactory.java -  A FilterFactory that creates an instance of the desired filter class.
GeneralFilter.java - GeneralFilter class, an abstract filter class that is inherited by filters that compare 
attributes, or other general purpose filters that may be added to the module.
GreaterThanFilter.java - GreaterThanClass that selects for files over a certain size.
HiddenFilter.java - HiddenFilter class that selects for files that are hidden.
NameContainsFilter.java - NameContainsFilter class that selects for files with names that contain a given 
sequence.
NameFilter.java - NameFilter class, an abstract filter class that is inherited by filters to compare file 
names and Strings.
NotFilter.java - NotFilter class that functions as a decorator on a filter class, returning the opposite 
files.
Order.java - Order interface for order classes (each sorts its own way).
OrderFactory.java - An OrderFactory that creates an instance of the desired order class.
Parsing.java - A module that parses a file containing a list of commands for the filesprocessing program. 
Builds Section objects to be passed back to the directoryProcessor class, which are then interpreted as 
commands for filters and orders.
PrefixFilter.java - PrefixFilter class that selects for files with a given prefix.
RegularOrder.java - RegularOrder class, which is an abstract class that sorts sets of files into an ordered 
array.
ReverseOrder.java - ReverseOrder class that functions as a decorator on a RegularOrder class, making them 
reverse their order.
Section.java – Section class that represents a section in the command file and its parameters.
SizeFilter.java – SizeFilter class, an abstract filter class that is inherited by filters to compare file size
SizeOrder.java – SizeOrder class used to order a list of files by size.
SmallerThanFilter.java – SmallerThanClass that selects for files under a certain size.
SuffixFilter.java – SuffixFilter class that selects for files with a given suffix.
TypeIErrorException.java – TypeIErrorException class, which is called when there is a Type I Error.
TypeIIErrorException.java – TypeIIErrorException class, which is called when there is a Type II Error.
TypeOrder.java - TypeOrder class used to order a list of files by type.
WritableFilter.java - WritableFilter class that selects for files that are writable.

=============================
=          Design           =
=============================
Design details:
We decided to divide the project between several separate modules, each of which performs a discrete task. The
 Manager module does the work of orchestrating the workflow from module to module, and outputting the fina
 result. The Manager receives files via the command line, and passes them to the Parsing module. The Parsing
 module reads the command file and creates a Section object for each section in the command file, which
 contains the specific filter and order commands of the section. The Sections are passed to the Filter module,
  which contains a FilterFactory class that determines which type of filter is required. It then builds the
  appropriate Filter object (an object which implements the Filter interface and also extends some abstract
  class of filter). The Filter module returns a HashSet containing the files that match the requested filter.
  The matching files are then passed to the Order module, which also contains a factory class that produces
  the desired order. Finally, the filtered and ordered files are returned to the manager, which prints the
  names of the files in order.
  This design was intended to maximize decomposability, understandability, and modular continuity between the
  modules, by keeping all the modules separate and "focused" on their own tasks. In addition, the design was
  meant to allow easy expansion of the project by the user. Adding a new filter or order involves modifiying
  or adding only a couple of classes, due to the "single-choice principle" design of storing the list of valid
   filters/orders in one location.

=============================
=  Implementation details   =
=============================
Exceptions hierarchy- We created two new exceptions classes, TypeIErrorException and TypeIIErrorException,
each of which extends the Exception class. TypeIErrorException has an empty body since it does need to convey
any information to the Manager, while TypeIIErrorException carries a message which is printed by the Manager
in the correct format. The exceptions are thrown wherever an error is detected, whether in the Filter or Order
 Factories, the Parsing module, or the Manager module itself. They are caught within the Manager module and
 handles by printing out the appropriate message.
Sorting- The filter module contains a factory class, which matches the filter command to the type of filter
requested, throwing an exception if no filter is matched. If the NOT parameter is used, it attaches the
NotFilter decorator to the desired filter. The hierarchy of the filters themselves consists of a Filter
interface, containing a single method sort(). Three abstract classes implement the interface, each of which
contains some shared code for filters that have shared properties. The SizeFilter class contains a for loop
that extracts and converts the size of the file to k-bytes, the NameFilter class has a for loop that extracts
the name of the file, and the GeneralFilter class has a general for loop for other kinds of filters. Each
specific filter type extends one of these classes, and consists of just one method, attribute(), that simply
returns whether or not a given file matches the desired attribute. When the Manager module receives the
requested filter object, it feeds it the files, and any files that match are stored in a HashSet data
structure which is finally returned to the Manager. We used a HashSet because, unlike an array, it can be
constantly expanded without having to declare the size of the set at the outset. In addition, a HashSet<File>
is typesafe, and its operations run in a good runtime. The drawbacks of using a HashSet are that it does not
allow for an order between the files, and cannot contain duplicates, but since we havent ordered the files
yet, and there should be no duplicates anyway, a HashSet seems like the correct choice.


=============================
=    Answers to questions   =
=============================
