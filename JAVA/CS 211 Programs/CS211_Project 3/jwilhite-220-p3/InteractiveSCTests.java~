// Wed Mar  1 09:58:39 EST 2017 
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test; // fixes some compile problems with annotations

public class InteractiveSCTests {
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("InteractiveSCTests");
  } 


  // Utility to check that a series of words is considered
  // correct/incorrect by the spell checker
  public static void check_isCorrect(String dictFile, boolean ignoreCase, int expectDictSize,
                                     Object [][] words_correct)
  {
    String msg;
    Scanner inScanner = new Scanner("");
    StringWriter output = new StringWriter();
    PrintWriter outWriter = new PrintWriter(output);
    InteractiveSC sc = new InteractiveSC(dictFile,ignoreCase,inScanner,outWriter);

    boolean isSpellChecker = sc instanceof SpellChecker;
    msg = "";
    msg += "InteractiveSC must extend SpellChecker";
    assertEquals(msg, true, isSpellChecker);
    msg = "";

    int actualDictSize = sc.dictSize();
    msg = "";
    msg += "Dictionary file read wrong\n";
    msg += String.format("dictionary: %s\n", dictFile);
    msg += String.format("EXPECT DICT SIZE: %d\n",expectDictSize);
    msg += String.format("ACTUAL DICT SIZE: %d\n",actualDictSize);
    assertEquals(msg, expectDictSize, actualDictSize);

    for(Object [] wc : words_correct){
      String word = (String) wc[0];
      boolean expect_isCorrect = (boolean) wc[1];
      boolean actual_isCorrect = sc.isCorrect(word);
      msg = "";
      msg += "SpellChecker isCorrect(word) incorrect\n";
      msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
      msg += String.format("word: %s\n",word);
      msg += String.format("EXPECT isCorrect(): %s\n",expect_isCorrect);
      msg += String.format("ACTUAL isCorrect(): %s\n",actual_isCorrect);
      assertEquals(msg,expect_isCorrect,actual_isCorrect);
    }
  }

  // Tests for isCorrect method
  @Test(timeout=2000) public void isc_iscorrect_ignore_case_1(){
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
  @Test(timeout=2000) public void isc_iscorrect_ignore_case_2(){
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
  @Test(timeout=2000) public void isc_iscorrect_ignore_case_3(){
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
  @Test(timeout=2000) public void isc_iscorrect_noignorecase_1(){
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
  @Test(timeout=2000) public void isc_iscorrect_noignorecase_2(){
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
  @Test(timeout=2000) public void isc_iscorrect_noignorecase_3(){
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
  public static void check_correctWord(String dictFile, boolean ignoreCase, 
                                       Object [][] words_correct)
  {
    for(Object [] wc : words_correct){
      String msg;
      String word = (String) wc[0];
      String input = (String) wc[1];
      String expect_correction = (String) wc[2];
      String expect_output = (String) wc[3];
      Scanner inScanner = new Scanner(input);
      StringWriter output = new StringWriter();
      PrintWriter outWriter = new PrintWriter(output);
      InteractiveSC sc = new InteractiveSC(dictFile,ignoreCase,inScanner,outWriter);
      String exceptions ="";
      String actual_correction = "";
      try{
        actual_correction = sc.correctWord(word);
      }
      catch(Exception e){
        exceptions = e.toString();
      }
      outWriter.flush();
      String actual_output = output.toString();
      msg = "";
      if(exceptions.length() > 0){
        msg += String.format("exception thrown: %s\n", exceptions);
      }
      msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
      msg += String.format("word: %s\n",word);
      msg += String.format("input: %s\n",input);
      msg += String.format("EXPECT correctWord(): %s\n",expect_correction);
      msg += String.format("ACTUAL correctWord(): %s\n",actual_correction);
      msg += String.format("EXPECT output:\n%s\n",expect_output);
      msg += String.format("ACTUAL output:\n%s\n",actual_output);
      String errMsg;
      errMsg = "SpellChecker correctWord(word) incorrect\n" + msg;
      assertEquals(errMsg,expect_correction,actual_correction);
      errMsg = "SpellChecker correctWord(word) output incorrect\n" + msg;
      assertEquals(errMsg,expect_output,actual_output.replaceAll("\\r?\\n","\n"));
    }
  }

  // Tests for correctWord()
  @Test(timeout=2000) public void isc_correctword_1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    Object words_correct[][] = {
      {"apple"      ,"orange"      ,"orange"      ,"@- Correction for **apple**:\n@ Corrected to: orange\n"             },
      {"Banana"     ,"bonanza"     ,"bonanza"     ,"@- Correction for **Banana**:\n@ Corrected to: bonanza\n"           },
      {"bruits"     ,"BRUTES"      ,"BRUTES"      ,"@- Correction for **bruits**:\n@ Corrected to: BRUTES\n"            },
      {"earldom's"  ,"Earldom's"   ,"Earldom's"   ,"@- Correction for **earldom's**:\n@ Corrected to: Earldom's\n"      },
      {"THIN"       ,"thin"        ,"thin"        ,"@- Correction for **THIN**:\n@ Corrected to: thin\n"                },
      {"undeniable" ,"Undeniable"  ,"Undeniable"  ,"@- Correction for **undeniable**:\n@ Corrected to: Undeniable\n"    },
      {"Slapped"    ,"SLAPPED"     ,"SLAPPED"     ,"@- Correction for **Slapped**:\n@ Corrected to: SLAPPED\n"          },
      {"condemners" ,"condemners"  ,"condemners"  ,"@- Correction for **condemners**:\n@ Corrected to: condemners\n"    },
      {"couching"   ,"crouching"   ,"crouching"   ,"@- Correction for **couching**:\n@ Corrected to: crouching\n"       },
      {"DEODORized" ,"deodorized"  ,"deodorized"  ,"@- Correction for **DEODORized**:\n@ Corrected to: deodorized\n"    },
      {"necessary"  ,"necessarily" ,"necessarily" ,"@- Correction for **necessary**:\n@ Corrected to: necessarily\n"    },
      {"gag's"      ,"gag's"       ,"gag's"       ,"@- Correction for **gag's**:\n@ Corrected to: gag's\n"              },
      {"Klingon"    ,"Klingon"     ,"Klingon"     ,"@- Correction for **Klingon**:\n@ Corrected to: Klingon\n"          },
      {"tricorder"  ,"tricorder"   ,"tricorder"   ,"@- Correction for **tricorder**:\n@ Corrected to: tricorder\n"      },
      {"romulan"    ,"Romulan"     ,"Romulan"     ,"@- Correction for **romulan**:\n@ Corrected to: Romulan\n"          },
      {"Starfleet"  ,"Starfleet"   ,"Starfleet"   ,"@- Correction for **Starfleet**:\n@ Corrected to: Starfleet\n"      },
      {"SITH"       ,"Sith"        ,"Sith"        ,"@- Correction for **SITH**:\n@ Corrected to: Sith\n"                },
      {"kyber"      ,"Kyber"       ,"Kyber"       ,"@- Correction for **kyber**:\n@ Corrected to: Kyber\n"              },
      {"lightsaber" ,"LightSaber"  ,"LightSaber"  ,"@- Correction for **lightsaber**:\n@ Corrected to: LightSaber\n"    },
      {"Hoth"       ,"HothBeast"   ,"HothBeast"   ,"@- Correction for **Hoth**:\n@ Corrected to: HothBeast\n"           },
    };      
    check_correctWord(dictFile,ignoreCase,words_correct);
  }
  // Should produce no difference from the above results
  @Test(timeout=2000) public void isc_correctword_2(){
    boolean ignoreCase = false;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 0;
    Object words_correct[][] = {
      {"apple"      ,"orange"      ,"orange"      ,"@- Correction for **apple**:\n@ Corrected to: orange\n"             },
      {"Banana"     ,"bonanza"     ,"bonanza"     ,"@- Correction for **Banana**:\n@ Corrected to: bonanza\n"           },
      {"bruits"     ,"BRUTES"      ,"BRUTES"      ,"@- Correction for **bruits**:\n@ Corrected to: BRUTES\n"            },
      {"earldom's"  ,"Earldom's"   ,"Earldom's"   ,"@- Correction for **earldom's**:\n@ Corrected to: Earldom's\n"      },
      {"THIN"       ,"thin"        ,"thin"        ,"@- Correction for **THIN**:\n@ Corrected to: thin\n"                },
      {"undeniable" ,"Undeniable"  ,"Undeniable"  ,"@- Correction for **undeniable**:\n@ Corrected to: Undeniable\n"    },
      {"Slapped"    ,"SLAPPED"     ,"SLAPPED"     ,"@- Correction for **Slapped**:\n@ Corrected to: SLAPPED\n"          },
      {"condemners" ,"condemners"  ,"condemners"  ,"@- Correction for **condemners**:\n@ Corrected to: condemners\n"    },
      {"couching"   ,"crouching"   ,"crouching"   ,"@- Correction for **couching**:\n@ Corrected to: crouching\n"       },
      {"DEODORized" ,"deodorized"  ,"deodorized"  ,"@- Correction for **DEODORized**:\n@ Corrected to: deodorized\n"    },
      {"necessary"  ,"necessarily" ,"necessarily" ,"@- Correction for **necessary**:\n@ Corrected to: necessarily\n"    },
      {"gag's"      ,"gag's"       ,"gag's"       ,"@- Correction for **gag's**:\n@ Corrected to: gag's\n"              },
      {"Klingon"    ,"Klingon"     ,"Klingon"     ,"@- Correction for **Klingon**:\n@ Corrected to: Klingon\n"          },
      {"tricorder"  ,"tricorder"   ,"tricorder"   ,"@- Correction for **tricorder**:\n@ Corrected to: tricorder\n"      },
      {"romulan"    ,"Romulan"     ,"Romulan"     ,"@- Correction for **romulan**:\n@ Corrected to: Romulan\n"          },
      {"Starfleet"  ,"Starfleet"   ,"Starfleet"   ,"@- Correction for **Starfleet**:\n@ Corrected to: Starfleet\n"      },
      {"SITH"       ,"Sith"        ,"Sith"        ,"@- Correction for **SITH**:\n@ Corrected to: Sith\n"                },
      {"kyber"      ,"Kyber"       ,"Kyber"       ,"@- Correction for **kyber**:\n@ Corrected to: Kyber\n"              },
      {"lightsaber" ,"LightSaber"  ,"LightSaber"  ,"@- Correction for **lightsaber**:\n@ Corrected to: LightSaber\n"    },
      {"Hoth"       ,"HothBeast"   ,"HothBeast"   ,"@- Correction for **Hoth**:\n@ Corrected to: HothBeast\n"           },
    };      
    check_correctWord(dictFile,ignoreCase,words_correct);
  }

  // Utility to check results of correctDocumnet
  public static void check_correctDocument(String dictFile, boolean ignoreCase, 
                                           Object [][] docs_correct)
  {
    for(Object [] wc : docs_correct){
      String msg;
      String content = (String) wc[0];
      String input = (String) wc[1];
      String expect_correction = (String) wc[2];
      String expect_output = (String) wc[3];
      Scanner inScanner = new Scanner(input);
      StringWriter output = new StringWriter();
      PrintWriter outWriter = new PrintWriter(output);
      InteractiveSC sc = new InteractiveSC(dictFile,ignoreCase,inScanner,outWriter);
      Document doc = new Document(content);
      String exceptions = "";
      try{
        sc.correctDocument(doc);
      }
      catch(Exception e){
        exceptions = e.toString();
      }
      String actual_correction = doc.toString();

      outWriter.flush();
      String actual_output = output.toString();
      msg = "";
      if(exceptions.length()>0){
        msg += String.format("Exceptions during input:\n%s\n",exceptions);
      }
      msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
      msg += String.format("CONTENT:\n%s\n",content);
      msg += String.format("INPUT: %s\n",input);
      msg += String.format("EXPECT correctDocument():\n%s\n",expect_correction);
      msg += String.format("ACTUAL correctDocument():\n%s\n",actual_correction);
      msg += String.format("EXPECT output:\n%s\n",expect_output);
      msg += String.format("ACTUAL output:\n%s\n",actual_output);
      String errMsg;
      errMsg = "SpellChecker correctDocument(word) incorrect\n" + msg;
      assertEquals(errMsg,expect_correction,actual_correction);
      errMsg = "SpellChecker correctDocument(word) output incorrect\n" + msg;
      assertEquals(errMsg,expect_output,actual_output.replaceAll("\\r?\\n","\n"));
    }
  }
  @Test(timeout=2000) public void isc_correctdoc_ignorecase_1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "Orange Pear CELERY",
       "Orange, Pear, CELERY!",
       "@ MISSPELLING in: **Apple**, banana, CARROT!\n"+
       "@- Correction for **Apple**:\n"+
       "@ Corrected to: Orange\n"+
       "@ MISSPELLING in: Orange, **banana**, CARROT!\n"+
       "@- Correction for **banana**:\n"+
       "@ Corrected to: Pear\n"+
       "@ MISSPELLING in: Orange, Pear, **CARROT**!\n"+
       "@- Correction for **CARROT**:\n"+
       "@ Corrected to: CELERY\n"+
       ""
      },
    };
    check_correctDocument(dictFile,ignoreCase,docs_correct);
  }
  // No input required, no output expected
  @Test(timeout=2000) public void isc_correctdoc_ignorecase_2(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "",
       "Apple, banana, CARROT!",
       ""
      },
    };
    check_correctDocument(dictFile,ignoreCase,docs_correct);
  }
  @Test(timeout=2000) public void isc_correctdoc_ignorecase_3(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    Object docs_correct[][] = {
      {"Apple, bannana, CARROT!",
       "banana",
       "Apple, banana, CARROT!",
       "@ MISSPELLING in: Apple, **bannana**, CARROT!\n"+
       "@- Correction for **bannana**:\n"+
       "@ Corrected to: banana\n"+
       ""
      },
    };
    check_correctDocument(dictFile,ignoreCase,docs_correct);
  }
  @Test(timeout=2000) public void isc_correctdoc_ignorecase_4(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    Object docs_correct[][] = {
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "potato tomatoes underestimated",
       "One potato, two tomatoes, three potatoes, four. I underestimated how many potatoes.",
       "@ MISSPELLING in: One **potatoe**, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.\n"+
       "@- Correction for **potatoe**:\n"+
       "@ Corrected to: potato\n"+
       "@ MISSPELLING in: One potato, two **tumatoes**, three potatoes, four. I misunderestimated how many potatoes.\n"+
       "@- Correction for **tumatoes**:\n"+
       "@ Corrected to: tomatoes\n"+
       "@ MISSPELLING in: One potato, two tomatoes, three potatoes, four. I **misunderestimated** how many potatoes.\n"+
       "@- Correction for **misunderestimated**:\n"+
       "@ Corrected to: underestimated\n"+
       ""
      },
      {"If I see one more POTATOE, I'm gonna FREAK out.",
       "POTATO I'm freak",
       "If I see one more POTATO, I'm gonna freak out.",
       "@ MISSPELLING in: If I see one more **POTATOE**, I'm gonna FREAK out.\n"+
       "@- Correction for **POTATOE**:\n"+
       "@ Corrected to: POTATO\n"+
       "@ MISSPELLING in: If I see one more POTATO, **I'm** gonna FREAK out.\n"+
       "@- Correction for **I'm**:\n"+
       "@ Corrected to: I'm\n"+
       "@ MISSPELLING in: If I see one more POTATO, I'm gonna **FREAK** out.\n"+
       "@- Correction for **FREAK**:\n"+
       "@ Corrected to: freak\n"+
       ""
      },
      {"Conceptually FORTRAN remained on familiar grounds in the sense that\n"+
       "its purpose was to aid the mechanization of computational processes we\n"+
       "used to do with pen and paper (and mechanical desk calculators if you\n"+
       "could afford them). This was in strong contrast to LISP whose purpose\n"+
       "was to enable the execution of processes that no one would dream of\n"+
       "performing with pen and paper.\n"+
       "-Edsger Dijkstra\n"+
       "",
       "Conceptually FORTRAN mechanization Lisp Edsger DaMan",
       "Conceptually FORTRAN remained on familiar grounds in the sense that\n"+
       "its purpose was to aid the mechanization of computational processes we\n"+
       "used to do with pen and paper (and mechanical desk calculators if you\n"+
       "could afford them). This was in strong contrast to Lisp whose purpose\n"+
       "was to enable the execution of processes that no one would dream of\n"+
       "performing with pen and paper.\n"+
       "-Edsger DaMan\n"+
       "",
       "@ MISSPELLING in: **Conceptually** FORTRAN remained on familiar grounds in the sense that\n"+
       "@- Correction for **Conceptually**:\n"+
       "@ Corrected to: Conceptually\n"+
       "@ MISSPELLING in: Conceptually **FORTRAN** remained on familiar grounds in the sense that\n"+
       "@- Correction for **FORTRAN**:\n"+
       "@ Corrected to: FORTRAN\n"+
       "@ MISSPELLING in: \n"+
       "its purpose was to aid the **mechanization** of computational processes we\n"+
       "@- Correction for **mechanization**:\n"+
       "@ Corrected to: mechanization\n"+
       "@ MISSPELLING in: \n"+
       "could afford them). This was in strong contrast to **LISP** whose purpose\n"+
       "@- Correction for **LISP**:\n"+
       "@ Corrected to: Lisp\n"+
       "@ MISSPELLING in: \n"+
       "-**Edsger** Dijkstra\n"+
       "@- Correction for **Edsger**:\n"+
       "@ Corrected to: Edsger\n"+
       "@ MISSPELLING in: \n"+
       "-Edsger **Dijkstra**\n"+
       "@- Correction for **Dijkstra**:\n"+
       "@ Corrected to: DaMan\n"+
       "",
      },
    };
    check_correctDocument(dictFile,ignoreCase,docs_correct);
  }
  @Test(timeout=2000) public void isc_correctdoc_noignorecase_1(){
    boolean ignoreCase = false;
    String dictFile = "google-10000-english.txt";
    Object docs_correct[][] = {
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "ONE potato tomatoes Me underestimated",
       "ONE potato, two tomatoes, three potatoes, four. Me underestimated how many potatoes.",
       "@ MISSPELLING in: **One** potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.\n"+
       "@- Correction for **One**:\n"+
       "@ Corrected to: ONE\n"+
       "@ MISSPELLING in: ONE **potatoe**, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.\n"+
       "@- Correction for **potatoe**:\n"+
       "@ Corrected to: potato\n"+
       "@ MISSPELLING in: ONE potato, two **tumatoes**, three potatoes, four. I misunderestimated how many potatoes.\n"+
       "@- Correction for **tumatoes**:\n"+
       "@ Corrected to: tomatoes\n"+
       "@ MISSPELLING in: ONE potato, two tomatoes, three potatoes, four. **I** misunderestimated how many potatoes.\n"+
       "@- Correction for **I**:\n"+
       "@ Corrected to: Me\n"+
       "@ MISSPELLING in: ONE potato, two tomatoes, three potatoes, four. Me **misunderestimated** how many potatoes.\n"+
       "@- Correction for **misunderestimated**:\n"+
       "@ Corrected to: underestimated\n"+
       ""
      },
      {"Conceptually FORTRAN remained on familiar grounds in the sense that\n"+
       "its purpose was to aid the mechanization of computational processes we\n"+
       "used to do with pen and paper (and mechanical desk calculators if you\n"+
       "could afford them). This was in strong contrast to LISP whose purpose\n"+
       "was to enable the execution of processes that no one would dream of\n"+
       "performing with pen and paper.\n"+
       "-Edsger Dijkstra\n"+
       "",
       "AA BB CC DD EE FF GG",
       "AA BB remained on familiar grounds in the sense that\n"+
       "its purpose was to aid the CC of computational processes we\n"+
       "used to do with pen and paper (and mechanical desk calculators if you\n"+
       "could afford them). DD was in strong contrast to EE whose purpose\n"+
       "was to enable the execution of processes that no one would dream of\n"+
       "performing with pen and paper.\n"+
       "-FF GG\n"+
       "",
       "@ MISSPELLING in: **Conceptually** FORTRAN remained on familiar grounds in the sense that\n"+
       "@- Correction for **Conceptually**:\n"+
       "@ Corrected to: AA\n"+
       "@ MISSPELLING in: AA **FORTRAN** remained on familiar grounds in the sense that\n"+
       "@- Correction for **FORTRAN**:\n"+
       "@ Corrected to: BB\n"+
       "@ MISSPELLING in: \n"+
       "its purpose was to aid the **mechanization** of computational processes we\n"+
       "@- Correction for **mechanization**:\n"+
       "@ Corrected to: CC\n"+
       "@ MISSPELLING in: \n"+
       "could afford them). **This** was in strong contrast to LISP whose purpose\n"+
       "@- Correction for **This**:\n"+
       "@ Corrected to: DD\n"+
       "@ MISSPELLING in: \n"+
       "could afford them). DD was in strong contrast to **LISP** whose purpose\n"+
       "@- Correction for **LISP**:\n"+
       "@ Corrected to: EE\n"+
       "@ MISSPELLING in: \n"+
       "-**Edsger** Dijkstra\n"+
       "@- Correction for **Edsger**:\n"+
       "@ Corrected to: FF\n"+
       "@ MISSPELLING in: \n"+
       "-FF **Dijkstra**\n"+
       "@- Correction for **Dijkstra**:\n"+
       "@ Corrected to: GG\n"+
       "",
      },
    };
    check_correctDocument(dictFile,ignoreCase,docs_correct);
  }
}

