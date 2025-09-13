// Thu Feb 23 19:12:19 EST 2017 
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test; // fixes some compile problems with annotations

public class SpellCheckerTests {
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("SpellCheckerTests");
  } 

  // tests for readAllLines
  @Test(timeout=2000) public void sc_read_all_lines_nonexistent(){
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    String [] lines = SpellChecker.readAllLines(dictFile);
    assertEquals(expectDictSize,lines.length);
  }
  @Test(timeout=2000) public void sc_read_all_lines_empty(){
    String dictFile = "empty-dict.txt";
    int expectDictSize = 0;
    String [] lines = SpellChecker.readAllLines(dictFile);
    assertEquals(expectDictSize,lines.length);
  }
  @Test(timeout=2000) public void sc_read_all_lines_small_dcit(){
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    String [] lines = SpellChecker.readAllLines(dictFile);
    assertEquals(expectDictSize,lines.length);
    assertEquals("apple"  ,lines[0]);
    assertEquals("banana" ,lines[1]);
    assertEquals("carrot" ,lines[2]);
  }
  @Test(timeout=2000) public void sc_read_all_lines_google_dict(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    String [] lines = SpellChecker.readAllLines(dictFile);
    assertEquals(expectDictSize,lines.length);
    assertEquals("the"    ,lines[1-1]);
    assertEquals("poison" ,lines[10000-1]);
    assertEquals("are"    ,lines[20-1]);
    assertEquals("time"   ,lines[50-1]);
    assertEquals("going"  ,lines[500-1]);
    assertEquals("kelkoo" ,lines[7607-1]);
  }

  // Utility to load a spell checker and verify that its dictionary is
  // the correct size
  public static SpellChecker loadChecker(String dictFile, boolean ignoreCase, int expectDictSize)
  {
    SpellChecker sc = new SpellChecker(dictFile,ignoreCase);
    int actualDictSize = sc.dictSize();
    String msg = "";
    msg += "Dictionary file read wrong\n";
    msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
    msg += String.format("EXPECT DICT SIZE: %d\n",expectDictSize);
    msg += String.format("ACTUAL DICT SIZE: %d\n",actualDictSize);
    assertEquals(msg, expectDictSize, actualDictSize);
    return sc;
  }    

  // Tests for SpellChecker constructors
  @Test(timeout=2000) public void sc_constructor_nonexistent_dict(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    loadChecker(dictFile,ignoreCase,expectDictSize);
  }
  @Test(timeout=2000) public void sc_constructor_empty_dict(){
    boolean ignoreCase = true;
    String dictFile = "empty-dict.txt";
    int expectDictSize = 0;
    loadChecker(dictFile,ignoreCase,expectDictSize);
  }
  @Test(timeout=2000) public void sc_constructor_short_file(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    loadChecker(dictFile,ignoreCase,expectDictSize);
  }
  @Test(timeout=2000) public void sc_constructor_google_dict(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    loadChecker(dictFile,ignoreCase,expectDictSize);
  }

  // Utility to check that a series of words is considered
  // correct/incorrect by the spell checker
  public static void check_isCorrect(String dictFile, boolean ignoreCase, int expectDictSize,
                                     Object [][] words_correct)
  {
    SpellChecker sc = loadChecker(dictFile,ignoreCase,expectDictSize);
    for(Object [] wc : words_correct){
      String word = (String) wc[0];
      boolean expect_isCorrect = (boolean) wc[1];
      boolean actual_isCorrect = sc.isCorrect(word);
      String msg = "";
      msg += "SpellChecker isCorrect(word) incorrect\n";
      msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
      msg += String.format("word: %s\n",word);
      msg += String.format("EXPECT isCorrect(): %s\n",expect_isCorrect);
      msg += String.format("ACTUAL isCorrect(): %s\n",actual_isCorrect);
      assertEquals(msg,expect_isCorrect,actual_isCorrect);
    }
  }

  // Tests for isCorrect method
  @Test(timeout=2000) public void sc_iscorrect_ignore_case_1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    Object words_correct[][] = {
      {"apple",              false},
      {"APPLE",              false},
      {"banana",             false},
      {"Banana",             false},
      {"island",             false},
      {"individual",         false},
      {"environmental",      false},
      {"Ma",                 false},
      {"int",                false},
      {"WARRIORS",           false},
      {"knowledgeStorm",     false},
      {"Virginia",           false},
      {"Professor",          false},
      {"gage",               false},
      {"Klingon",            false},
      {"tricorder",          false},
      {"romulan",            false},
      {"Starfleet",          false},
      {"SITH",               false},
      {"kyber",              false},
      {"lightsaber",         false},
      {"Hoth",               false},
    };      
    check_isCorrect(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_iscorrect_ignore_case_2(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object words_correct[][] = {
      {"apple",              true },
      {"APPLE",              true },
      {"banana",             true },
      {"Banana",             true },
      {"island",             false},
      {"individual",         false},
      {"environmental",      false},
      {"Ma",                 false},
      {"int",                false},
      {"WARRIORS",           false},
      {"knowledgeStorm",     false},
      {"Virginia",           false},
      {"Professor",          false},
      {"gage",               false},
      {"Klingon",            false},
      {"tricorder",          false},
      {"romulan",            false},
      {"Starfleet",          false},
      {"SITH",               false},
      {"kyber",              false},
      {"lightsaber",         false},
      {"Hoth",               false},
    };      
    check_isCorrect(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_iscorrect_ignore_case_3(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object words_correct[][] = {
      {"apple",              true },
      {"APPLE",              true },
      {"banana",             true },
      {"Banana",             true },
      {"island",             true },
      {"individual",         true },
      {"environmental",      true },
      {"Ma",                 true },
      {"int",                true },
      {"WARRIORS",           true },
      {"knowledgeStorm",     true },
      {"Virginia",           true },
      {"Professor",          true },
      {"gage",               true },
      {"Klingon",            false},
      {"tricorder",          false},
      {"romulan",            false},
      {"Starfleet",          false},
      {"SITH",               false},
      {"kyber",              false},
      {"lightsaber",         false},
      {"Hoth",               false},
    };      
    check_isCorrect(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_iscorrect_noignorecase_1(){
    boolean ignoreCase = false;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    Object words_correct[][] = {
      {"apple"      ,false},
      {"Banana"     ,false},
      {"bruits"     ,false},
      {"earldom's"  ,false},
      {"THIN"       ,false},
      {"undeniable" ,false},
      {"Slapped"    ,false},
      {"condemners" ,false},
      {"couching"   ,false},
      {"DEODORized" ,false},
      {"necessary"  ,false},
      {"gag's"      ,false},
      {"Klingon"    ,false},
      {"tricorder"  ,false},
      {"romulan"    ,false},
      {"Starfleet"  ,false},
      {"SITH"       ,false},
      {"kyber"      ,false},
      {"lightsaber" ,false},
      {"Hoth"       ,false},
    };      
    check_isCorrect(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_iscorrect_noignorecase_2(){
    boolean ignoreCase = false;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object words_correct[][] = {
      {"apple"      ,true},
      {"Banana"     ,false},
      {"bruits"     ,false},
      {"earldom's"  ,false},
      {"THIN"       ,false},
      {"undeniable" ,false},
      {"Slapped"    ,false},
      {"condemners" ,false},
      {"couching"   ,false},
      {"DEODORized" ,false},
      {"necessary"  ,false},
      {"gag's"      ,false},
      {"Klingon"    ,false},
      {"tricorder"  ,false},
      {"romulan"    ,false},
      {"Starfleet"  ,false},
      {"SITH"       ,false},
      {"kyber"      ,false},
      {"lightsaber" ,false},
      {"Hoth"       ,false},
    };      
    check_isCorrect(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_iscorrect_noignorecase_3(){
    boolean ignoreCase = false;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object words_correct[][] = {
      {"apple",              true },
      {"APPLE",              false},
      {"banana",             true },
      {"Banana",             false},
      {"island",             true },
      {"individual",         true },
      {"environmental",      true },
      {"Ma",                 false},
      {"int",                true },
      {"WARRIORS",           false},
      {"knowledgeStorm",     false},
      {"Virginia",           false},
      {"Professor",          false},
      {"gage",               true },
      {"Klingon",            false},
      {"tricorder",          false},
      {"romulan",            false},
      {"Starfleet",          false},
      {"SITH",               false},
      {"kyber",              false},
      {"lightsaber",         false},
      {"Hoth",               false},
    };      
    check_isCorrect(dictFile,ignoreCase,expectDictSize,words_correct);
  }

  // Utility to check results of correctWord
  public static void check_correctWord(String dictFile, boolean ignoreCase, int expectDictSize,
                                       Object [][] words_correct)
  {
    SpellChecker sc = loadChecker(dictFile,ignoreCase,expectDictSize);
    for(Object [] wc : words_correct){
      String word = (String) wc[0];
      String expect_correction = (String) wc[1];
      String actual_correction = sc.correctWord(word);
      String msg = "";
      msg += "SpellChecker correctWord(word) incorrect\n";
      msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
      msg += String.format("word: %s\n",word);
      msg += String.format("EXPECT correctWord(): %s\n",expect_correction);
      msg += String.format("ACTUAL correctWord(): %s\n",actual_correction);
      assertEquals(msg,expect_correction,actual_correction);
    }
  }

  // Tests for correctWord()
  @Test(timeout=2000) public void sc_correctword_ignorecase_1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    Object words_correct[][] = {
      {"apple"      ,"**apple**"},
      {"Banana"     ,"**Banana**"},
      {"bruits"     ,"**bruits**"},
      {"earldom's"  ,"**earldom's**"},
      {"THIN"       ,"**THIN**"},
      {"undeniable" ,"**undeniable**"},
      {"Slapped"    ,"**Slapped**"},
      {"condemners" ,"**condemners**"},
      {"couching"   ,"**couching**"},
      {"DEODORized" ,"**DEODORized**"},
      {"necessary"  ,"**necessary**"},
      {"gag's"      ,"**gag's**"},
      {"Klingon"    ,"**Klingon**"},
      {"tricorder"  ,"**tricorder**"},
      {"romulan"    ,"**romulan**"},
      {"Starfleet"  ,"**Starfleet**"},
      {"SITH"       ,"**SITH**"},
      {"kyber"      ,"**kyber**"},
      {"lightsaber" ,"**lightsaber**"},
      {"Hoth"       ,"**Hoth**"},
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_correctword_ignorecase_2(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object words_correct[][] = {
      {"apple"      ,"**apple**"},
      {"Banana"     ,"**Banana**"},
      {"bruits"     ,"**bruits**"},
      {"earldom's"  ,"**earldom's**"},
      {"THIN"       ,"**THIN**"},
      {"undeniable" ,"**undeniable**"},
      {"Slapped"    ,"**Slapped**"},
      {"condemners" ,"**condemners**"},
      {"couching"   ,"**couching**"},
      {"DEODORized" ,"**DEODORized**"},
      {"necessary"  ,"**necessary**"},
      {"gag's"      ,"**gag's**"},
      {"Klingon"    ,"**Klingon**"},
      {"tricorder"  ,"**tricorder**"},
      {"romulan"    ,"**romulan**"},
      {"Starfleet"  ,"**Starfleet**"},
      {"SITH"       ,"**SITH**"},
      {"kyber"      ,"**kyber**"},
      {"lightsaber" ,"**lightsaber**"},
      {"Hoth"       ,"**Hoth**"},
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_correctword_ignorecase_3(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object words_correct[][] = {
      {"apple"      ,"**apple**"},
      {"Banana"     ,"**Banana**"},
      {"bruits"     ,"**bruits**"},
      {"earldom's"  ,"**earldom's**"},
      {"THIN"       ,"**THIN**"},
      {"undeniable" ,"**undeniable**"},
      {"Slapped"    ,"**Slapped**"},
      {"condemners" ,"**condemners**"},
      {"couching"   ,"**couching**"},
      {"DEODORized" ,"**DEODORized**"},
      {"necessary"  ,"**necessary**"},
      {"gag's"      ,"**gag's**"},
      {"Klingon"    ,"**Klingon**"},
      {"tricorder"  ,"**tricorder**"},
      {"romulan"    ,"**romulan**"},
      {"Starfleet"  ,"**Starfleet**"},
      {"SITH"       ,"**SITH**"},
      {"kyber"      ,"**kyber**"},
      {"lightsaber" ,"**lightsaber**"},
      {"Hoth"       ,"**Hoth**"},
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_correctword_noignorecase_1(){
    boolean ignoreCase = false;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    Object words_correct[][] = {
      {"apple"      ,"**apple**"},
      {"Banana"     ,"**Banana**"},
      {"bruits"     ,"**bruits**"},
      {"earldom's"  ,"**earldom's**"},
      {"THIN"       ,"**THIN**"},
      {"undeniable" ,"**undeniable**"},
      {"Slapped"    ,"**Slapped**"},
      {"condemners" ,"**condemners**"},
      {"couching"   ,"**couching**"},
      {"DEODORized" ,"**DEODORized**"},
      {"necessary"  ,"**necessary**"},
      {"gag's"      ,"**gag's**"},
      {"Klingon"    ,"**Klingon**"},
      {"tricorder"  ,"**tricorder**"},
      {"romulan"    ,"**romulan**"},
      {"Starfleet"  ,"**Starfleet**"},
      {"SITH"       ,"**SITH**"},
      {"kyber"      ,"**kyber**"},
      {"lightsaber" ,"**lightsaber**"},
      {"Hoth"       ,"**Hoth**"},
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_correctword_noignorecase_2(){
    boolean ignoreCase = false;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object words_correct[][] = {
      {"apple"      ,"**apple**"},
      {"Banana"     ,"**Banana**"},
      {"bruits"     ,"**bruits**"},
      {"earldom's"  ,"**earldom's**"},
      {"THIN"       ,"**THIN**"},
      {"undeniable" ,"**undeniable**"},
      {"Slapped"    ,"**Slapped**"},
      {"condemners" ,"**condemners**"},
      {"couching"   ,"**couching**"},
      {"DEODORized" ,"**DEODORized**"},
      {"necessary"  ,"**necessary**"},
      {"gag's"      ,"**gag's**"},
      {"Klingon"    ,"**Klingon**"},
      {"tricorder"  ,"**tricorder**"},
      {"romulan"    ,"**romulan**"},
      {"Starfleet"  ,"**Starfleet**"},
      {"SITH"       ,"**SITH**"},
      {"kyber"      ,"**kyber**"},
      {"lightsaber" ,"**lightsaber**"},
      {"Hoth"       ,"**Hoth**"},
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=2000) public void sc_correctword_noignorecase_3(){
    boolean ignoreCase = false;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object words_correct[][] = {
      {"apple"      ,"**apple**"},
      {"Banana"     ,"**Banana**"},
      {"bruits"     ,"**bruits**"},
      {"earldom's"  ,"**earldom's**"},
      {"THIN"       ,"**THIN**"},
      {"undeniable" ,"**undeniable**"},
      {"Slapped"    ,"**Slapped**"},
      {"condemners" ,"**condemners**"},
      {"couching"   ,"**couching**"},
      {"DEODORized" ,"**DEODORized**"},
      {"necessary"  ,"**necessary**"},
      {"gag's"      ,"**gag's**"},
      {"Klingon"    ,"**Klingon**"},
      {"tricorder"  ,"**tricorder**"},
      {"romulan"    ,"**romulan**"},
      {"Starfleet"  ,"**Starfleet**"},
      {"SITH"       ,"**SITH**"},
      {"kyber"      ,"**kyber**"},
      {"lightsaber" ,"**lightsaber**"},
      {"Hoth"       ,"**Hoth**"},
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }


  // Utility to check results of correctDocument
  public static void check_correctDocument(String dictFile, boolean ignoreCase, int expectDictSize,
                                           Object [][] docs_correct)
  {
    SpellChecker sc = loadChecker(dictFile,ignoreCase,expectDictSize);
    for(Object [] wc : docs_correct){
      String content = (String) wc[0];
      String expect_correction = (String) wc[1];
      Document doc = new Document(content);
      sc.correctDocument(doc);
      String actual_correction = doc.toString();
      String msg = "";
      msg += "SpellChecker correctDocument(doc) incorrect\n";
      msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
      msg += String.format("CONTENT:\n%s\n",content);
      msg += String.format("EXPECT correctDocument():\n%s\n",expect_correction);
      msg += String.format("ACTUAL correctDocument():\n%s\n",actual_correction);
      assertEquals(msg,expect_correction,actual_correction);
    }
  }
  @Test(timeout=2000) public void sc_correctdoc_ignorecase_1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "**Apple**, **banana**, **CARROT**!",
      },
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "**One** **potatoe**, **two** **tumatoes**, **three** **potatoes**, **four**. **I** **misunderestimated** **how** **many** **potatoes**.",
      },
      {"If I see one more POTATOE, I'm gonna FREAK out.",
       "**If** **I** **see** **one** **more** **POTATOE**, **I'm** **gonna** **FREAK** **out**.",
      },
    };
    check_correctDocument(dictFile,ignoreCase,expectDictSize,docs_correct);
  }
  @Test(timeout=2000) public void sc_correctdoc_ignorecase_2(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "Apple, banana, CARROT!",
      },
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "**One** **potatoe**, **two** **tumatoes**, **three** **potatoes**, **four**. **I** **misunderestimated** **how** **many** **potatoes**.",
      },
      {"If I see one more POTATOE, I'm gonna FREAK out.",
       "**If** **I** **see** **one** **more** **POTATOE**, **I'm** **gonna** **FREAK** **out**.",
      },
    };
    check_correctDocument(dictFile,ignoreCase,expectDictSize,docs_correct);
  }
  @Test(timeout=2000) public void sc_correctdoc_ignorecase_3(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "Apple, banana, **CARROT**!",
      },
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "One **potatoe**, two **tumatoes**, three potatoes, four. I **misunderestimated** how many potatoes.",
      },
      {"If I see one more POTATOE, I'm gonna FREAK out.",
       "If I see one more **POTATOE**, **I'm** gonna **FREAK** out.",
      },
      {"Conceptually FORTRAN remained on familiar grounds in the sense that\n"+
       "its purpose was to aid the mechanization of computational processes we\n"+
       "used to do with pen and paper (and mechanical desk calculators if you\n"+
       "could afford them). This was in strong contrast to LISP whose purpose\n"+
       "was to enable the execution of processes that no one would dream of\n"+
       "performing with pen and paper.\n"+
       "-Edsger Dijkstra\n"+
       "",
       "**Conceptually** **FORTRAN** remained on familiar grounds in the sense that\n"+
       "its purpose was to aid the **mechanization** of computational processes we\n"+
       "used to do with pen and paper (and mechanical desk calculators if you\n"+
       "could afford them). This was in strong contrast to **LISP** whose purpose\n"+
       "was to enable the execution of processes that no one would dream of\n"+
       "performing with pen and paper.\n"+
       "-**Edsger** **Dijkstra**\n"+
       "",
      },
    };
    check_correctDocument(dictFile,ignoreCase,expectDictSize,docs_correct);
  }
  @Test(timeout=2000) public void sc_correctdoc_noignorecase_1(){
    boolean ignoreCase = false;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "**Apple**, **banana**, **CARROT**!",
      },
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "**One** **potatoe**, **two** **tumatoes**, **three** **potatoes**, **four**. **I** **misunderestimated** **how** **many** **potatoes**.",
      },
      {"If I see one more POTATOE, I'm gonna FREAK out.",
       "**If** **I** **see** **one** **more** **POTATOE**, **I'm** **gonna** **FREAK** **out**.",
      },
    };
    check_correctDocument(dictFile,ignoreCase,expectDictSize,docs_correct);
  }
  @Test(timeout=2000) public void sc_correctdoc_noignorecase_2(){
    boolean ignoreCase = false;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "**Apple**, banana, **CARROT**!",
      },
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "**One** **potatoe**, **two** **tumatoes**, **three** **potatoes**, **four**. **I** **misunderestimated** **how** **many** **potatoes**.",
      },
      {"If I see one more POTATOE, I'm gonna FREAK out.",
       "**If** **I** **see** **one** **more** **POTATOE**, **I'm** **gonna** **FREAK** **out**.",
      },
    };
    check_correctDocument(dictFile,ignoreCase,expectDictSize,docs_correct);
  }
  @Test(timeout=2000) public void sc_correctdoc_noignorecase_3(){
    boolean ignoreCase = false;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "**Apple**, banana, **CARROT**!",
      },
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "**One** **potatoe**, two **tumatoes**, three potatoes, four. **I** **misunderestimated** how many potatoes.",
      },
      {"If I see one more POTATOE, I'm gonna FREAK out.",
       "**If** **I** see one more **POTATOE**, **I'm** gonna **FREAK** out.",
      },
      {"Conceptually FORTRAN remained on familiar grounds in the sense that\n"+
       "its purpose was to aid the mechanization of computational processes we\n"+
       "used to do with pen and paper (and mechanical desk calculators if you\n"+
       "could afford them). This was in strong contrast to LISP whose purpose\n"+
       "was to enable the execution of processes that no one would dream of\n"+
       "performing with pen and paper.\n"+
       "-Edsger Dijkstra\n"+
       "",
       "**Conceptually** **FORTRAN** remained on familiar grounds in the sense that\n"+
       "its purpose was to aid the **mechanization** of computational processes we\n"+
       "used to do with pen and paper (and mechanical desk calculators if you\n"+
       "could afford them). **This** was in strong contrast to **LISP** whose purpose\n"+
       "was to enable the execution of processes that no one would dream of\n"+
       "performing with pen and paper.\n"+
       "-**Edsger** **Dijkstra**\n"+
       "",
      },
    };
    check_correctDocument(dictFile,ignoreCase,expectDictSize,docs_correct);
  }

  // Tests that multiple spell checkers can exist simultaneously
  @Test(timeout=2000) public void sc_multiple_1(){
    // Two separate spell checkers
    SpellChecker scA = new SpellChecker("does-not-exist.txt",true);
    SpellChecker scB = new SpellChecker("short-dict.txt",true);
    // Check independent sizes
    assertEquals(0     ,scA.dictSize());
    assertEquals(3     ,scB.dictSize());
    // Check different results for correctness of words
    assertEquals(false ,scA.isCorrect("Apple"));
    assertEquals(true  ,scB.isCorrect("Apple"));
    assertEquals(false ,scA.isCorrect("BANANA"));
    assertEquals(true  ,scB.isCorrect("BANANA"));
    // Check separate results for correcting documents
    String content = "Apple, banana, CARROT! ... pear";
    Document docA = new Document(content);
    Document docB = new Document(content);
    scA.correctDocument(docA);
    scB.correctDocument(docB);
    assertEquals("**Apple**, **banana**, **CARROT**! ... **pear**",
                 docA.toString());
    assertEquals("Apple, banana, CARROT! ... **pear**",
                 docB.toString());
  }
  @Test(timeout=2000) public void sc_multiple_2(){
    // Two separate spell checkers, first checks case
    SpellChecker scA = new SpellChecker("google-10000-english.txt",false);
    SpellChecker scB = new SpellChecker("short-dict.txt",true);
    // Check independent sizes
    assertEquals(10000 ,scA.dictSize());
    assertEquals(3      ,scB.dictSize());
    // Check different results for correctness of words
    assertEquals(false  ,scA.isCorrect("APPLE"));
    assertEquals(true   ,scB.isCorrect("APPLE"));
    assertEquals(false  ,scA.isCorrect("BANANA"));
    assertEquals(true   ,scB.isCorrect("BANANA"));
    assertEquals(true   ,scA.isCorrect("pair"));
    assertEquals(false  ,scB.isCorrect("pair"));
    // Check separate results for correcting documents
    String content = "Apple, banana, CARROT! ... pair";
    Document docA = new Document(content);
    Document docB = new Document(content);
    scA.correctDocument(docA);
    scB.correctDocument(docB);
    assertEquals("**Apple**, banana, **CARROT**! ... pair",
                 docA.toString());
    assertEquals("Apple, banana, CARROT! ... **pair**",
                 docB.toString());
  }
  @Test(timeout=2000) public void sc_multiple_3(){
    // Two separate spell checkers, first checks case
    SpellChecker scA = new SpellChecker("google-10000-english.txt",false);
    SpellChecker scB = new SpellChecker("short-dict.txt",true);
    // Check separate results for correcting documents
    String content = "Apple, banana, CARROT! ... pair, misunderesitmated vegetable.";
    Document docAB = new Document(content);
    scA.correctDocument(docAB);
    assertEquals("**Apple**, banana, **CARROT**! ... pair, **misunderesitmated** vegetable.",
                 docAB.toString());
    scB.correctDocument(docAB);
    assertEquals("**Apple**, banana, **CARROT**! ... **pair**, ****misunderesitmated**** **vegetable**.",
                 docAB.toString());
  }

}

