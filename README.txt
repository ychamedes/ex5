ychamudot
jasonmg


=============================
=      File description     =
=============================
AbsOrder.java
AllFilter.java
BetweenFilter.java
ExecutableFilter.java
FileNameFilter.java
Filter.java
FilterFactory.java
FilterParameter.java
GeneralFilter.java
GreaterThanFilter.java
HiddenFilter.java
Manager.java
NameContainsFilter.java
NameFilter.java
NotFilter.java
Order.java
OrderFactory.java
Parsing.java
PrefixFilter.java
RegularOrder.java
ReverseOrder.java
Section.java
SizeFilter.java
SizeOrder.java
SmallerThanFilter.java
SuffixFilter.java
TypeIErrorException.java
TypeOrder.java
WritableFilter.java


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
