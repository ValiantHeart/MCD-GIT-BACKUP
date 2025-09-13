// Wed Mar  1 09:58:29 EST 2017 
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test; // fixes some compile problems with annotations

public class PersonalSCTests {
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("PersonalSCTests");
  } 

  // Utility to create a file with the given contents
  public static void makeFile(String filename, String contents){
    File file = new File(filename);
    if(file.exists()){
      return;
    }
    try{
      PrintWriter out = new PrintWriter(filename);
      out.print(contents);
      out.close();
    }
    catch(Exception e){
      throw new RuntimeException(e.getMessage());
    }
  }

  // Utility to remove a file, useful for clearing a personal dictionary
  public static void removeFile(String filename){
    try{
      (new File(filename)).delete();
    }
    catch(Exception e){
      throw new RuntimeException(e.getMessage());
    }
  }

  // Utility to load a spell checker and verify that its dictionary is
  // the correct size
  public static void loadChecker(String dictFile, boolean ignoreCase, int expect_dictSize,
                                         String pdictFile, int expect_pdictSize, String expect_pdictWords)
  {
    String msg;
    Scanner inScanner = new Scanner("");
    StringWriter output = new StringWriter();
    PrintWriter outWriter = new PrintWriter(output);
    PersonalSC sc = new PersonalSC(dictFile,ignoreCase,inScanner,outWriter,pdictFile);

    boolean isSpellChecker = sc instanceof SpellChecker;
    msg = "";
    msg += "PersonalSC must extend SpellChecker";
    assertEquals(msg, true, isSpellChecker);
    msg = "";

    boolean isInteractiveSC = sc instanceof InteractiveSC;
    msg = "";
    msg += "PersonalSC must extend InteractiveSC";
    assertEquals(msg, true, isInteractiveSC);
    msg = "";

    int actual_dictSize = sc.dictSize();
    int actual_pdictSize = sc.personalDictSize();
    String actual_pdictWords = sc.getAllPersonalDictWords();

    msg = "";
    msg += "Dictionary file read wrong\n";
    msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
    msg += String.format("EXPECT DICT SIZE: %d\n",expect_dictSize);
    msg += String.format("ACTUAL DICT SIZE: %d\n",actual_dictSize);
    assertEquals(msg, expect_dictSize, actual_dictSize);

    msg = "";
    msg += "Personal dictionary file read wrong\n";
    msg += String.format("personal dictionary: %s\n", pdictFile);
    msg += String.format("EXPECT DICT SIZE: %d\n",expect_pdictSize);
    msg += String.format("ACTUAL DICT SIZE: %d\n",actual_pdictSize);
    assertEquals(msg, expect_pdictSize, actual_pdictSize);

    msg = "";
    msg += "Personal dictionary file read wrong\n";
    msg += String.format("personal dictionary: %s\n", pdictFile);
    msg += String.format("EXPECT DICT WORDS:\n%s\n",expect_pdictWords);
    msg += String.format("ACTUAL DICT WORDS:\n%s\n",actual_pdictWords);
    assertEquals(msg, expect_pdictWords, actual_pdictWords.replaceAll("\\r?\\n","\n"));

  }    

  // Tests for PersonalSC constructors
  @Test(timeout=2000) public void psc_constructor1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expect_dictSize = 0;
    String pdictFile = "psc_constructor1.txt";
    int expect_pdictSize = 3;
    String expect_pdictWords =
      "kyber\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "";
    makeFile(pdictFile,expect_pdictWords);
    loadChecker(dictFile,ignoreCase,expect_dictSize,pdictFile,expect_pdictSize,expect_pdictWords);
    removeFile(pdictFile);
  }
  @Test(timeout=2000) public void psc_constructor2(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expect_dictSize = 10000;
    String pdictFile = "psc_constructor2.txt";
    int expect_pdictSize = 8;
    String expect_pdictWords =
      "Klingon\n"+   
      "tricorder\n"+ 
      "romulan\n"+   
      "Starfleet\n"+ 
      "SITH\n"+      
      "kyber\n"+     
      "lightsaber\n"+
      "Hoth\n"+      
      "";
    makeFile(pdictFile,expect_pdictWords);
    loadChecker(dictFile,ignoreCase,expect_dictSize,pdictFile,expect_pdictSize,expect_pdictWords);
    removeFile(pdictFile);
  }
  @Test(timeout=2000) public void psc_constructor3(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expect_dictSize = 10000;
    String pdictFile = "psc_constructor3.txt";
    int expect_pdictSize = 0;
    String expect_pdictWords =
      "";
    makeFile(pdictFile,expect_pdictWords);
    loadChecker(dictFile,ignoreCase,expect_dictSize,pdictFile,expect_pdictSize,expect_pdictWords);
    removeFile(pdictFile);
  }
  @Test(timeout=2000) public void psc_constructor4(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expect_dictSize = 10000;
    String pdictFile = "psc_constructor4.txt";
    int expect_pdictSize = 0;
    String expect_pdictWords =
      "";
    removeFile(pdictFile);
    loadChecker(dictFile,ignoreCase,expect_dictSize,pdictFile,expect_pdictSize,expect_pdictWords);
    removeFile(pdictFile);
  }
  @Test(timeout=2000) public void psc_constructor5(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expect_dictSize = 0;
    String pdictFile = "psc_constructor5.txt";
    int expect_pdictSize = 0;
    String expect_pdictWords =
      "";
    removeFile(pdictFile);
    loadChecker(dictFile,ignoreCase,expect_dictSize,pdictFile,expect_pdictSize,expect_pdictWords);
    removeFile(pdictFile);
  }

  // Utility to check that a series of words is considered
  // correct/incorrect by the spell checker
  public static void check_isCorrect(String dictFile, boolean ignoreCase, String pdictFile,
                                     Object [][] words_correct)
  {
    String msg;
    String input = "";
    Scanner inScanner = new Scanner(input);
    StringWriter output = new StringWriter();
    PrintWriter outWriter = new PrintWriter(output);
    PersonalSC sc = new PersonalSC(dictFile,ignoreCase,inScanner,outWriter,pdictFile);

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
    removeFile(pdictFile);
  }

  // Tests for isCorrect method
  @Test(timeout=2000) public void psc_iscorrect_ignorecase_1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    String pdictFile = "psc_iscorrect_ignore_case_1.txt";
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
    check_isCorrect(dictFile,ignoreCase,pdictFile,words_correct);
  }
  @Test(timeout=2000) public void psc_iscorrect_ignorecase_2(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    String pdictFile = "psc_iscorrect_ignore_case_2.txt";
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
    check_isCorrect(dictFile,ignoreCase,pdictFile,words_correct);
  }
  @Test(timeout=2000) public void psc_iscorrect_ignorecase_3(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    String pdictFile = "psc_iscorrect_ignore_case_3.txt";
    removeFile(pdictFile);
    makeFile(pdictFile,
             "kyber\n"+
             "lightsaber\n"+
             "Hoth\n");
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
      {"kyber",              true },
      {"lightsaber",         true },
      {"Hoth",               true },
    };      
    check_isCorrect(dictFile,ignoreCase,pdictFile,words_correct);
  }
  @Test(timeout=2000) public void psc_iscorrect_ignorecase_4(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_iscorrect_ignore_case_4.txt";
    removeFile(pdictFile);
    makeFile(pdictFile,
             "tricorder\n"+
             "Klingon\n"+
             "kyber\n"+
             "lightsaber\n"+
             "Hoth\n");
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
      {"Klingon",            true },
      {"tricorder",          true },
      {"romulan",            false},
      {"Starfleet",          false},
      {"SITH",               false},
      {"kyber",              true },
      {"lightsaber",         true },
      {"Hoth",               true },
    };      
    check_isCorrect(dictFile,ignoreCase,pdictFile,words_correct);
  }
  @Test(timeout=2000) public void psc_iscorrect_noignorecase_1(){
    boolean ignoreCase = false;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_iscorrect_ignore_case_4.txt";
    removeFile(pdictFile);
    makeFile(pdictFile,
             "tricorder\n"+
             "klingon\n"+
             "KYBER\n"+
             "lightsaber\n"+
             "Hoth\n");
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
      {"tricorder",          true },
      {"romulan",            false},
      {"Starfleet",          false},
      {"SITH",               false},
      {"kyber",              false},
      {"lightsaber",         true },
      {"Hoth",               true },
    };      
    check_isCorrect(dictFile,ignoreCase,pdictFile,words_correct);
  }

  // Utility to check results of correctWord
  public static void check_correctWord(String dictFile, boolean ignoreCase, 
                                       String pdictFile,
                                       Object [][] words_correct)
  {
    for(Object [] wc : words_correct){
      String msg;
      String word = (String) wc[0];
      String input = (String) wc[1];
      String expect_correction = (String) wc[2];
      String expect_pdictWords = (String) wc[3];
      String expect_output = (String) wc[4];
      Scanner inScanner = new Scanner(input);
      StringWriter output = new StringWriter();
      PrintWriter outWriter = new PrintWriter(output);
      PersonalSC sc = new PersonalSC(dictFile,ignoreCase,inScanner,outWriter,pdictFile);
      String exceptions ="";
      String actual_correction ="";
      try{
        actual_correction = sc.correctWord(word);
      }
      catch(Exception e){
        exceptions = e.toString();
      }
      outWriter.flush();
      String actual_output = output.toString();
      String actual_pdictWords = sc.getAllPersonalDictWords();
      msg = "";
      if(exceptions.length() > 0){
        msg += String.format("exception thrown: %s\n", exceptions);
      }
      msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
      msg += String.format("word: %s\n",word);
      msg += String.format("input: %s\n",input);
      msg += String.format("EXPECT correctWord(): %s\n",expect_correction);
      msg += String.format("ACTUAL correctWord(): %s\n",actual_correction);
      msg += String.format("EXPECT personal dict words:\n%s\n",expect_pdictWords);
      msg += String.format("ACTUAL personal dict words:\n%s\n",expect_pdictWords);
      msg += String.format("EXPECT output:\n%s\n",expect_output);
      msg += String.format("ACTUAL output:\n%s\n",actual_output);
      String errMsg;
      errMsg = "SpellChecker correctWord(word) incorrect\n" + msg;
      assertEquals(errMsg,expect_correction,actual_correction);
      errMsg = "SpellChecker personal dict words incorrect\n" + msg;
      assertEquals(errMsg,expect_pdictWords,actual_pdictWords);
      errMsg = "SpellChecker correctWord(word) output incorrect\n" + msg;
      assertEquals(errMsg,expect_output,actual_output.replaceAll("\\r?\\n","\n"));
    }
  }

  // Tests for correctWord()
  @Test(timeout=2000) public void psc_correctword_1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    String pdictFile = "psc_correctword_1.txt";
    Object words_correct[][] = {
      {"apple"      ,"yes"        ,"apple"      ,"apple\n"      , "@- **apple** not in dictionary add it? (yes / no)\n"}                                                           ,
      {"Banana"     ,"no bonanza" ,"bonanza"    ,""             , "@- **Banana** not in dictionary add it? (yes / no)\n@- Correction for **Banana**:\n@ Corrected to: bonanza\n" } ,
      {"bruits"     ,"no BRUTES"  ,"BRUTES"     ,""             , "@- **bruits** not in dictionary add it? (yes / no)\n@- Correction for **bruits**:\n@ Corrected to: BRUTES\n"}   ,
      {"earldom's"  ,"yes"        ,"earldom's"  ,"earldom's\n"  , "@- **earldom's** not in dictionary add it? (yes / no)\n"}                                                       ,
      {"THIN"       ,"no thin"    ,"thin"       ,""             , "@- **THIN** not in dictionary add it? (yes / no)\n@- Correction for **THIN**:\n@ Corrected to: thin\n"}         ,
      {"undeniable" ,"yes"        ,"undeniable" ,"undeniable\n" , "@- **undeniable** not in dictionary add it? (yes / no)\n" }                                                     ,
      {"klingon"    ,"yes"        ,"klingon"    ,"klingon\n"    , "@- **klingon** not in dictionary add it? (yes / no)\n" }                                                        ,
    };      
    check_correctWord(dictFile,ignoreCase,pdictFile,words_correct);
  }
  @Test(timeout=2000) public void psc_correctword_2(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    String pdictFile = "psc_correctword_2.txt";
    String pdictWords =
      "tricorder\n"+
      "klingon\n"+
      "KYBER\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "";
    makeFile(pdictFile,pdictWords);
    Object words_correct[][] = {
      {"apple"      ,"yes"        ,"apple"      ,pdictWords+"apple\n"      , "@- **apple** not in dictionary add it? (yes / no)\n"}                                                           ,
      {"Banana"     ,"no bonanza" ,"bonanza"    ,pdictWords+""             , "@- **Banana** not in dictionary add it? (yes / no)\n@- Correction for **Banana**:\n@ Corrected to: bonanza\n" } ,
      {"bruits"     ,"no BRUTES"  ,"BRUTES"     ,pdictWords+""             , "@- **bruits** not in dictionary add it? (yes / no)\n@- Correction for **bruits**:\n@ Corrected to: BRUTES\n"}   ,
      {"earldom's"  ,"yes"        ,"earldom's"  ,pdictWords+"earldom's\n"  , "@- **earldom's** not in dictionary add it? (yes / no)\n"}                                                       ,
      {"THIN"       ,"no thin"    ,"thin"       ,pdictWords+""             , "@- **THIN** not in dictionary add it? (yes / no)\n@- Correction for **THIN**:\n@ Corrected to: thin\n"}         ,
      {"undeniable" ,"yes"        ,"undeniable" ,pdictWords+"undeniable\n" , "@- **undeniable** not in dictionary add it? (yes / no)\n" }                                                     ,
    };      
    check_correctWord(dictFile,ignoreCase,pdictFile,words_correct);
    removeFile(pdictFile);
  }
  // yes/no prompt omitted for words in personal dictionary
  @Test(timeout=2000) public void psc_correctword_3(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    String pdictFile = "psc_correctword_3.txt";
    String pdictWords =
      "tricorder\n"+
      "klingon\n"+
      "KYBER\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "";
    makeFile(pdictFile,pdictWords);
    Object words_correct[][] = {
      {"klingon"   ,"bugger"   ,"bugger"   ,pdictWords , "@- Correction for **klingon**:\n@ Corrected to: bugger\n"}     ,
      {"Klingon"   ,"bugger"   ,"bugger"   ,pdictWords , "@- Correction for **Klingon**:\n@ Corrected to: bugger\n"}     ,
      {"KLINGON"   ,"bugger"   ,"bugger"   ,pdictWords , "@- Correction for **KLINGON**:\n@ Corrected to: bugger\n"}     ,
      {"Tricorder" ,"recorder" ,"recorder" ,pdictWords , "@- Correction for **Tricorder**:\n@ Corrected to: recorder\n"} ,
    };      
    check_correctWord(dictFile,ignoreCase,pdictFile,words_correct);
    removeFile(pdictFile);
  }
  // yes/no prompt omitted for words in system dictionary
  @Test(timeout=2000) public void psc_correctword_4(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_correctword_4.txt";
    String pdictWords =
      "tricorder\n"+
      "klingon\n"+
      "KYBER\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "";
    makeFile(pdictFile,pdictWords);
    Object words_correct[][] = {
      {"apple"      ,"orange"        ,"orange"     ,pdictWords                , "@- Correction for **apple**:\n@ Corrected to: orange\n"}                                                                  ,
      {"BANANA"     ,"Fruit"         ,"Fruit"      ,pdictWords                , "@- Correction for **BANANA**:\n@ Corrected to: Fruit\n"}                                                                  ,
      {"lightsaber" ,"glowstick"     ,"glowstick"  ,pdictWords                , "@- Correction for **lightsaber**:\n@ Corrected to: glowstick\n"}                                                          ,
      {"comparator" ,"yes"           ,"comparator" ,pdictWords+"comparator\n" , "@- **comparator** not in dictionary add it? (yes / no)\n"}                                                                ,
      {"comparator" ,"no comparison" ,"comparison" ,pdictWords                , "@- **comparator** not in dictionary add it? (yes / no)\n@- Correction for **comparator**:\n@ Corrected to: comparison\n"} ,
    };      
    check_correctWord(dictFile,ignoreCase,pdictFile,words_correct);
    removeFile(pdictFile);
  }
  @Test(timeout=2000) public void psc_correctword_noignorecase_1(){
    boolean ignoreCase = false;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_correctword_4.txt";
    String pdictWords =
      "tricorder\n"+
      "klingon\n"+
      "KYBER\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "";
    makeFile(pdictFile,pdictWords);
    Object words_correct[][] = {
      {"apple"      ,"orange"        ,"orange"     ,pdictWords                , "@- Correction for **apple**:\n@ Corrected to: orange\n"}                                                                  ,
      {"BANANA"     ,"no Fruit"      ,"Fruit"      ,pdictWords                , "@- **BANANA** not in dictionary add it? (yes / no)\n@- Correction for **BANANA**:\n@ Corrected to: Fruit\n"}              ,
      {"Lightsaber" ,"no glowstick"  ,"glowstick"  ,pdictWords                , "@- **Lightsaber** not in dictionary add it? (yes / no)\n@- Correction for **Lightsaber**:\n@ Corrected to: glowstick\n"}  ,
      {"comparator" ,"yes"           ,"comparator" ,pdictWords+"comparator\n" , "@- **comparator** not in dictionary add it? (yes / no)\n"}                                                                ,
      {"comparator" ,"no comparison" ,"comparison" ,pdictWords                , "@- **comparator** not in dictionary add it? (yes / no)\n@- Correction for **comparator**:\n@ Corrected to: comparison\n"} ,
    };      
    check_correctWord(dictFile,ignoreCase,pdictFile,words_correct);
    removeFile(pdictFile);
  }

  // Utility to check results of correctDocumnet
  public static void check_correctDocument(String dictFile, boolean ignoreCase, 
                                           String pdictFile, String pdictWords,
                                           String content, String input,
                                           String expect_correction,
                                           String expect_pdictWords,
                                           String expect_output)

  {
    String msg;
    Scanner inScanner = new Scanner(input);
    StringWriter output = new StringWriter();
    PrintWriter outWriter = new PrintWriter(output);
    removeFile(pdictFile);
    makeFile(pdictFile,pdictWords);
    PersonalSC sc = new PersonalSC(dictFile,ignoreCase,inScanner,outWriter,pdictFile);
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
    String actual_pdictWords = sc.getAllPersonalDictWords();
    msg = "";
    if(exceptions.length()>0){
      msg += String.format("Exceptions during input:\n%s\n",exceptions);
    }
    msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
    msg += String.format("personal dictionary: %s\n", pdictFile);
    msg += String.format("DOCUMENT CONTENT:\n%s\n",content);
    msg += String.format("INPUT: %s\n",input);
    msg += String.format("EXPECT correctDocument():\n%s\n",expect_correction);
    msg += String.format("ACTUAL correctDocument():\n%s\n",actual_correction);
    msg += String.format("EXPECT PERSONAL DICT WORDS:\n%s\n",expect_pdictWords);
    msg += String.format("ACTUAL PERSONAL DICT WORDS:\n%s\n",actual_pdictWords);
    msg += String.format("EXPECT output:\n%s\n",expect_output);
    msg += String.format("ACTUAL output:\n%s\n",actual_output);
    String errMsg;
    errMsg = "SpellChecker correctDocument(word) incorrect\n" + msg;
    assertEquals(errMsg,expect_correction,actual_correction);
    errMsg = "SpellChecker personal dict words incorrect\n" + msg;
    assertEquals(errMsg,expect_pdictWords,actual_pdictWords);
    errMsg = "SpellChecker correctDocument(word) output incorrect\n" + msg;
    assertEquals(errMsg,expect_output,actual_output.replaceAll("\\r?\\n","\n"));

    removeFile(pdictFile);
  }
  // Add word to personal dictionary
  @Test(timeout=2000) public void psc_correctdoc_ignorecase_1(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    String pdictFile = "psc_correctdoc_ignorecase_1.txt";
    String pdictWords =
      "";
    String content = "Apple, banana, CARROT!";
    String input = "no Orange yes no CELERY";
    String expect_correction = "Orange, banana, CELERY!";
    String expect_pdictWords =
      "banana\n"+
      "";
    String expect_output = 
      "@ MISSPELLING in: **Apple**, banana, CARROT!\n"+
      "@- **Apple** not in dictionary add it? (yes / no)\n"+
      "@- Correction for **Apple**:\n"+
      "@ Corrected to: Orange\n"+
      "@ MISSPELLING in: Orange, **banana**, CARROT!\n"+
      "@- **banana** not in dictionary add it? (yes / no)\n"+
      "@ MISSPELLING in: Orange, banana, **CARROT**!\n"+
      "@- **CARROT** not in dictionary add it? (yes / no)\n"+
      "@- Correction for **CARROT**:\n"+
      "@ Corrected to: CELERY\n"+
      "";
    check_correctDocument(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }
  // All words in system dictionary
  @Test(timeout=2000) public void psc_correctdoc_ignorecase_2(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    String pdictFile = "psc_correctdoc_ignorecase_2.txt";
    String pdictWords =
      "";
    String content = "Apple, banana, CARROT!";
    String input = "";
    String expect_correction = "Apple, banana, CARROT!";
    String expect_pdictWords =
      "";
    String expect_output = 
      "";
    check_correctDocument(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }
  // All words in system dictionary
  @Test(timeout=2000) public void psc_correctdoc_ignorecase_3(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_correctdoc_ignorecase_3.txt";
    String pdictWords =
      "potatoe\n"+
      "";
    String content = "One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.";
    String input = "no tomatoes yes";
    String expect_correction = "One potatoe, two tomatoes, three potatoes, four. I misunderestimated how many potatoes.";
    String expect_pdictWords =
      "potatoe\n"+
      "misunderestimated\n"+
      "";
    String expect_output = 
      "@ MISSPELLING in: One potatoe, two **tumatoes**, three potatoes, four. I misunderestimated how many potatoes.\n"+
      "@- **tumatoes** not in dictionary add it? (yes / no)\n"+
      "@- Correction for **tumatoes**:\n"+
      "@ Corrected to: tomatoes\n"+
      "@ MISSPELLING in: One potatoe, two tomatoes, three potatoes, four. I **misunderestimated** how many potatoes.\n"+
      "@- **misunderestimated** not in dictionary add it? (yes / no)\n"+
      "";
    check_correctDocument(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }
  @Test(timeout=2000) public void psc_correctdoc_ignorecase_4(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_correctdoc_ignorecase_4.txt";
    String pdictWords =
      "potatoe\n"+
      "lightsaber\n"+
      "misunderestimated\n"+
      "Sith\n"+
      "";
    String content = "One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.";
    String input = "yes";
    String expect_correction = "One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.";
    String expect_pdictWords =
      "potatoe\n"+
      "lightsaber\n"+
      "misunderestimated\n"+
      "Sith\n"+
      "tumatoes\n"+
      "";
    String expect_output = 
      "@ MISSPELLING in: One potatoe, two **tumatoes**, three potatoes, four. I misunderestimated how many potatoes.\n"+
      "@- **tumatoes** not in dictionary add it? (yes / no)\n"+
      "";
    check_correctDocument(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }
  @Test(timeout=2000) public void psc_correctdoc_ignorecase_5(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_correctdoc_ignorecase_5.txt";
    String pdictWords =
      "Sith\n"+
      "";
    String content = "One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.";
    String input = "yes yes yes";
    String expect_correction = "One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.";
    String expect_pdictWords =
      "Sith\n"+
      "potatoe\n"+
      "tumatoes\n"+
      "misunderestimated\n"+
      "";
    String expect_output = 
      "@ MISSPELLING in: One **potatoe**, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.\n"+
      "@- **potatoe** not in dictionary add it? (yes / no)\n"+
      "@ MISSPELLING in: One potatoe, two **tumatoes**, three potatoes, four. I misunderestimated how many potatoes.\n"+
      "@- **tumatoes** not in dictionary add it? (yes / no)\n"+
      "@ MISSPELLING in: One potatoe, two tumatoes, three potatoes, four. I **misunderestimated** how many potatoes.\n"+
      "@- **misunderestimated** not in dictionary add it? (yes / no)\n"+
      "";
    check_correctDocument(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }
  // Addiging to personal dictionary means later isntances are correct.
  @Test(timeout=2000) public void psc_correctdoc_ignorecase_6(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_correctdoc_ignorecase_6.txt";
    String pdictWords =
      "Sith\n"+
      "";
    String content = "Do not misunderestimate me. One potatoe, two potatoe, three potatoe, four. Sometimes I misunderestimate the potatoe count.";
    String input = "yes no POTATO yes";
    String expect_correction = "Do not misunderestimate me. One POTATO, two potatoe, three potatoe, four. Sometimes I misunderestimate the potatoe count.";
    String expect_pdictWords =
      "Sith\n"+
      "misunderestimate\n"+
      "potatoe\n"+
      "";
    String expect_output = 
      "@ MISSPELLING in: Do not **misunderestimate** me. One potatoe, two potatoe, three potatoe, four. Sometimes I misunderestimate the potatoe count.\n"+
      "@- **misunderestimate** not in dictionary add it? (yes / no)\n"+
      "@ MISSPELLING in: Do not misunderestimate me. One **potatoe**, two potatoe, three potatoe, four. Sometimes I misunderestimate the potatoe count.\n"+
      "@- **potatoe** not in dictionary add it? (yes / no)\n"+
      "@- Correction for **potatoe**:\n"+
      "@ Corrected to: POTATO\n"+
      "@ MISSPELLING in: Do not misunderestimate me. One POTATO, two **potatoe**, three potatoe, four. Sometimes I misunderestimate the potatoe count.\n"+
      "@- **potatoe** not in dictionary add it? (yes / no)\n"+
      "";
    check_correctDocument(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }



  // Utility to check savePersonalDict() method
  public static void check_pdict_save(String dictFile, boolean ignoreCase, 
                                      String pdictFile, String pdictWords,
                                      String content, String input,
                                      String expect_correction,
                                      String expect_pdictWords,
                                      String expect_output)
    throws Exception
  {
    String msg;
    Scanner inScanner = new Scanner(input);
    StringWriter output = new StringWriter();
    PrintWriter outWriter = new PrintWriter(output);
    removeFile(pdictFile);
    makeFile(pdictFile,pdictWords);
    PersonalSC sc = new PersonalSC(dictFile,ignoreCase,inScanner,outWriter,pdictFile);
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
    String actual_pdictWords = sc.getAllPersonalDictWords();
    msg = "";
    if(exceptions.length()>0){
      msg += String.format("Exceptions during input:\n%s\n",exceptions);
    }

    sc.savePersonalDict();
    actual_pdictWords = slurp(pdictFile);

    msg = "";
    msg += "Personal Dictionary not saved correctly";
    msg += String.format("personal dictionary save file:\n%s\n",pdictFile);
    msg += String.format("EXPECT PERSONAL DICTIONARY WORDS:\n%s\n",expect_pdictWords);
    msg += String.format("ACTUAL PERSONAL DICTIONARY WORDS:\n%s\n",actual_pdictWords);
    assertEquals(msg,expect_pdictWords,actual_pdictWords.replaceAll("\\r?\\n","\n"));

    removeFile(pdictFile);
  }

  // Read entire contents of a file and return as a string
  private static String slurp(String filename) throws Exception{
    return (new Scanner(new File(filename), "UTF-8").useDelimiter("\\Z").next())+"\n";
  }

  @Test(timeout=6000) public void psc_savePersonalDict_1() throws Exception{
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_savePersonalDict_1.txt";
    String pdictWords =
      "Sith\n"+
      "";
    String content = "Do not misunderestimate me. One potatoe, two potatoe, three potatoe, four. Sometimes I misunderestimate the potatoe count.";
    String input = "yes no POTATO yes";
    String expect_correction = "Do not misunderestimate me. One POTATO, two potatoe, three potatoe, four. Sometimes I misunderestimate the potatoe count.";
    String expect_pdictWords =
      "Sith\n"+
      "misunderestimate\n"+
      "potatoe\n"+
      "";
    String expect_output = 
      "";
    check_pdict_save(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }
  @Test(timeout=6000) public void psc_savePersonalDict_2() throws Exception{
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_savePersonalDict_2.txt";
    String pdictWords =
      "tricorder\n"+
      "klingon\n"+
      "KYBER\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "";
    String content = "Do not misunderestimate me. One potatoe, two tumato, three potatoe, four. Sometimes I misunderestimate the potatoe count. Lolz.";
    String input = "no understimate yes yes no mistake yes";
    String expect_correction = "Do not misunderestimate me. One POTATO, two potatoe, three potatoe, four. Sometimes I misunderestimate the potatoe count.";
    String expect_pdictWords =
      "tricorder\n"+
      "klingon\n"+
      "KYBER\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "potatoe\n"+
      "tumato\n"+
      "Lolz\n"+
      "";
    String expect_output = 
      "";
    check_pdict_save(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }
  // Should be no change to the dictionary - all in the system and personal dict
  @Test(timeout=6000) public void psc_savePersonalDict_3() throws Exception{
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    String pdictFile = "psc_savePersonalDict_3.txt";
    String pdictWords =
      "tricorder\n"+
      "klingon\n"+
      "KYBER\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "";
    String content = "Kyber crystals make lightsabers. Hoth is full of Sith.";
    String input = "";
    String expect_correction = "";
    String expect_pdictWords =
      "tricorder\n"+
      "klingon\n"+
      "KYBER\n"+
      "lightsaber\n"+
      "Hoth\n"+
      "";
    String expect_output = 
      "";
    check_pdict_save(dictFile,ignoreCase,pdictFile,pdictWords,content,input,expect_correction,expect_pdictWords,expect_output);
  }


}

