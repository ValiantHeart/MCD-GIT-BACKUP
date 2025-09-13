//Necessary Imports:
import java.util.*;
import java.io.*;
import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;

public class RecursiveCrawler extends Crawler
{
// An implementation of a Crawler which uses recursion to chase links
// and visit all files linked to the start point.

	public RecursiveCrawler()
	{
		super();
	}
	// Create an empty crawler

	@Override public void crawl(String pageFileName)
	{
		if(Crawler.validPageLink(pageFileName))
		{
			foundPages.add(pageFileName);
			Document doc = Jsoup.parse(pageFileName, "UTF-8");
			Elements links = doc.select("a[href]"); //problem line - return's an empty Elements object....
			for(Element link: links)
			{
				String linkedPage = link.attr("href");
				if(Crawler.validPageLink(linkedPage))
				{
					(this.foundPages).add(linkedPage);
					//crawl(linkedPage);
				}
				else
				{
					(this.skippedPages).add(linkedPage);
				}
			}
			for(Element link: links)
			{
				String linkedPage = link.attr("href");
				crawl(linkedPage);
			}
		}
		else
		{
			System.out.println("ERROR: PROVIDED LINK IS NOT VALID");
		}
	}
	// Implementation of crawling.  Visit the given page wich should be
	// valid.  Parse its contents using library functions in the JSoup
	// library.  Examine all links on the page. Any valid page that
	// exists and is unvisited should be visited recursively. By the
	// time crawl(..)  finishes, all pages that can be reached from the
	// start point should be returned by a call to the foundPagesXX()
	// methods.
	//
	// See the spec for additional implementation details.

	//public static void main(String args[]) throws Exception;
	// Optional main method for your own testing

}