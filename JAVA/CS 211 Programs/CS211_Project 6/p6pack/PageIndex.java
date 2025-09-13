public class PageIndex
{
// Index of terms found on pages and the associated pages on which
// they were found.  An index is created empty but can use Crawler
// that has discoverd pages to add terms and pages to the index.
// PageIndex provides basic query facilities to look up which pages
// contain one or more words.

	protected ArraySet<IndexEntry> entries;
	// The set of IndexEntries which track search terms found on pages
	// along with the pages on which they are found.  Entries are kept
	// in a set as they should be unique.

	public PageIndex();
	// Create an empty PageIndex

	public int size();
	// Return the number of entries in the index which is the number of
	// terms that have been added with at least one page associated with
	// them.

	public String toString();
	// Return a string representation of the indexed terms and
	// pages. The format of the string is
	//
	// INDEX: #### entries
	// --------------------
	// @ entry1
	// entry1-file1
	// entry1-file2
	// entry1-file3
	// ...
	// @ entry2
	// entry2-file1
	// entry2-file2
	// entry2-file3
	// ...
	//
	// Which creates a header and then iterates through all IndexEntries
	// appending their toString().

	public boolean validTerm(String term);
	// Determine if the term given is valid. Valid terms do not appear
	// in the sorted array Util.STOP_WORDS which are uninformative
	// words.  Use binary search to efficiently determine if the given
	// term is in STOP_WORDS; return false if it is and true otherwise.

	public boolean containsTerm(String term);
	// Return true if the IndexContains the given term and some pages
	// associated with it and false otherwise.

	public List<String> getPagesWithTerm(String term);
	// Return a list of the pages associated with the given term. If
	// there are no pages associated with the given term, return and
	// empty list.

	public boolean addTermAndPage(String term, String page);
	// Add the given term found in the given page to the index.  If the
	// term is not valid as per the validTerm() method, do not add it
	// and return false.  Valid terms should be added along with the
	// page on which they occurred to an IndexEntry.  Return true if the
	// term is new to the index or if the term exists but the page is
	// new for that term.  Otherwise return false.

	public void addCrawledPages(Crawler crawler);
	// For each page in the given crawler's foundPageList(), open and
	// parse the page using the JSoup library.  Examine the body text of
	// the page which is a string.  Use a Scanner on this string and set
	// the delimiter to
	// 
	//    scan.useDelimiter("(\\p{Space}|\\p{Punct}|\\xA0)+");
	// 
	// which will parse through words on the page skipping most
	// punctuation. Add this page to the entries in the index associated
	// with each term in the body.

	public static List<String> intersectionOfSorted(List<String> x, List<String> y);
	// Find the intersection (common elements) of x and y. Assume that x
	// and y are sorted.  Use this fact to efficiently build up a list
	// of the common elements with a single loop through the lists x and
	// y.

	public List<String> query(String queryString);
	// Return a list of pages in the index which match the given
	// query. The query may be several space-separated words such as
	// "robotics artificial intelligence".  These should be separated
	// and used to retrieve lists of pages matching the words. Make use
	// of the insertionOfSorted(x,y) method to efficiently combine lists
	// of pages repeatedly in a loop to produce the end results.

	public static void main(String args[]);
	// Optional main method for your own testing

}