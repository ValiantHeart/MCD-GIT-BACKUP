//Necessary Imports:
import java.util.*;
import java.io.*;
import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;

public class IterativeCrawler extends Crawler
{
// An implementation of a crawler which does not use recursion and
// instead uses internal storage to track the pages that have yet to
// be visited.

	protected ArraySet<String> pendingPages;
	// A list of pages that remain to be visited. As a single page is
	// visited, any valid links that are found are added to this list to
	// be visited later.  This list should only contain valid, existing
	// pages which can be visited and have not yet been visited.

	public IterativeCrawler()
	{
		super();
	}
	// Create an empty crawler.

	@Override public void crawl(String pageFileName)
	{
		
	}
	// Master crawl method which will start at the given page and visit
	// all reachable pages.  This method should call the
	// crawlRemaining() method as its last action.

	public void crawlRemaining()
	{
		
	}
	// Enter a loop that crawls individual pages until there are no
	// pending pages remaining.

	public void addPendingPage(String pageFileName)
	{
		pendingPages.add(pageFileName);
	}
	// Add the given page to the list of of pending pages to be
	// visited. It is assumed that that page is valid and exists so can
	// be visited and parsed.

	public int pendingPagesSize()
	{
		return pendingPages.size();
	}
	// Return the number of pages remaining to visit.

	public String pendingPagesString()
	{
		return pendingPages.toString();
	}
	// Return a string with each pending page to visit on its own line.

	public void crawlNextPage()
	{
		
	}
	// Crawl a single page which is retrieved and removed from the list
	// of pending pages.  Parse the retrieved page's contents using
	// library functions in the JSoup library.  Examine all links on the
	// page. Any valid page that exists and is unvisited should be added
	// to the pending list. By the time crawlNextPage() finishes, one
	// additional page should be returned the foundPagesXX() methods
	// while the pending pages list may have grown substantially.
	//
	// See the spec for additional implementation details.

	//public static void main(String args[]) throws Exception;
	// Optional main method for your own testing

}