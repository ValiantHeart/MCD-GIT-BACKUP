public class AutomaticSC extends SpellChecker{
// A spell checker which automatically selects a correction for a
// misspelled word.  It inherets most functionality from its parent
// class but adjusts how correctWord(..) performs.

  public AutomaticSC(String dictFilename, boolean ignoreCase)
  {
	super(dictFilename, ignoreCase); //calls parent constructor.
  }
  // Construct an automatic spell checker. Pass the parameters to the
  // parent class constructor.

  @Override public String correctWord(String word)
  {
	for (int i = 0; i < super.dictWords.length; i++)
	{
		if (ignoreCase == true)
		{
			if (StringComparison.editDistance(word, dictWords[i]) < 1)
			{
				return dictWords[i];
			}
		}
		else
		{
/* 			if (word.compareTo(dictWords[i]) <= 1)
			{
				return dictWords[i];
			} */
			String search_var = matchCase(super.dictWords[i], word);
			if (StringComparison.editDistance(word, super.dictWords[i]) <= 1)
			{
				return dictWords[i];
			}
		}
	}
	return word;
  }
  
  // Return a correction for the given word. The correction is the
  // word in the dictionary which has the smallest edit distance from
  // the given word. 
  // If there are ties, favor whichever word appears
  // earlier in the dictionary. Make use of the methods of the
  // provided StringComparison to find the closest word in the
  // dictionary. Make sure to honor the ignoreCase option which may
  // lead you to convert words to all upper or lower case.

  public static String matchCase(String model, String source) //transforms the source string to match the case of the model.
  {
	if (model.equals(model.toUpperCase()) == true) //if the model is all caps:
	{
		return source.toUpperCase(); //return the source in all caps.
	}
 	if (isUpperCase(model.charAt(0)) == true) //if the model is capitalized:
	{
		source.toUpperCase(0); //capitalize the source string.
		return source; //then return it.
	}
	else //if it is neither capitalized nor all caps:
	{
		return source; //return the source.
	}
	
  }
  // Utility method to handle case matching between words. Check if
  // parameter model is all caps or only the first character is
  // capitalized and transform source to match the capitalization. In
  // the event that the model is neither all caps nor capitalized
  // followed by all lower case, return the source strnig as
  // is. Examples are given below.
  // 
  // |----------------------------------------|
  // | Situation   | model  | source | return |
  // |-------------+--------+--------+--------|
  // | All Caps    | BANANA | apple  | APPLE  |
  // | All Caps    | PEAR   | orange | ORANGE |
  // | Capitalized | Banana | orange | Orange |
  // | Capitalized | Apple  | pear   | Pear   |
  // | Neither     | banana | apple  | apple  |
  // | Neither     | banana | Apple  | Apple  |
  // | Neither     | BaNaNa | aPPle  | aPPle  |
  // | Neither     | peaR   | Orange | Orange |
  // |----------------------------------------|
  //
}