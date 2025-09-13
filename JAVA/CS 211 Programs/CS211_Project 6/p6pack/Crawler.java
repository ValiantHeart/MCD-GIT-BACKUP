//Necessary Imports:
import java.util.*;

public abstract class Crawler
{
// Abstract class to crawl linked pages. Descendent crawlers should
// override the crawl(page) method to initiate a crawllestof linked
// pages. The class is intended only to work for locally stored files
// for whic validPageLink(page) should return true while non-local and
// web links will return false. This class makes use of the ArraySet
// class.

	protected ArraySet<String> foundPages;
	// Sets of pages that have been found or have been skipped due to
	// their being invalid or non-existent.

	protected ArraySet<String> skippedPages;

	public Crawler() //Constructor type 0: empty everything: (COMPLETE)
	{
		this.foundPages = new ArraySet<String>(); //sets foundPages to an Empty Set.
		this.skippedPages = new ArraySet<String>(); //sets skippedPages to an Empty Set.
	}
	// Public constructor that creates an empty crawler.

	public abstract void crawl(String pageFileName);
	// Initiate a crawl on the given page.  Child classes should
	// override this.

	public List<String> foundPagesList() //(COMPLETE)
	{
		return (this.foundPages).asList(); //returns foundPages as a List.
	}
	// Return the unique pages that have been found so far and are
	// valid. Each item in the returned list should be unique and refer
	// to a valid file that exists.

	public List<String> skippedPagesList() //(COMPLETE)
	{
		return (this.skippedPages).asList(); //returns skippedPages as a List.
	}
	// Return the unique pages that have been skipped so far. These may
	// be invalid as per validPageLink(..), non-existent files, or links
	// off of the local file system.

	public String foundPagesString() //(COMPLETE)
	{
		String fancy_string = ""; //creates a new String named fancy_string as an empty string.
		for(String page: (this.foundPages).asList()) //for each page in the List of foundPages:
		{
			fancy_string += page + "\n"; //adds said page and a new line to fancy_string.
		}
		
		return fancy_string; //returns fancy_string.
	}
	// Return a string of pages that have been found so far.  Each page
	// is shown on its own line terminated with a \n

	public String skippedPagesString() //(COMPLETE)
	{
		String fancy_string = ""; //creates a new String named fancy_string as an empty string.
		for(String page: (this.skippedPages).asList()) //for each page in the List of skippedPages:
		{
			fancy_string += page + "\n"; //adds said page and a new line to fancy_string.
		}
		
		return fancy_string; //returns fancy_string.
	}
	// Return a string of pages that have been found so far.  Each page
	// is shown on its own line terminated with a \n

	public static boolean validPageLink(String pageFileName) //(COMPLETE)
	{
		if(pageFileName.contains("http://") || pageFileName.contains("https://")||pageFileName.contains("file://")) //if the pageFileName contains any of the forbidden substrings:
		{
			return false; //return false.
		}
		if(pageFileName.contains(".html") || pageFileName.contains(".HTML")) //if the pageFileName contains any of the follow substrings:
		{
			return true; //return true.
		}
		return false; //default return false.
	}
	// Return true if the given pageFileName is valid and false
	// otherwise. Valid pages
	//
	// - Do not start with http://, https://, or file://
	// - End with the extension .html or .HTML
	//
	// Any file not meeting the above criteria should generate a false
	// return value.

}