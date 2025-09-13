// Thu Feb 23 19:12:19 EST 2017 
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test; // fixes some compile problems with annotations

public class AutomaticSCTests {
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("AutomaticSCTests");
  } 

  // Utility to load a spell checker and verify that its dictionary is
  // the correct size
  public static AutomaticSC loadChecker(String dictFile, boolean ignoreCase, int expectDictSize)
  {
    String msg;
    AutomaticSC sc = new AutomaticSC(dictFile,ignoreCase);
    boolean isSpellChecker = sc instanceof SpellChecker;
    msg = "";
    msg += "AutomaticSC must extend SpellChecker";
    assertEquals(msg, true, isSpellChecker);
    msg = "";
    int actualDictSize = sc.dictSize();
    msg += "Dictionary file read wrong\n";
    msg += String.format("dictionary: %s\nignore case: %s\n", dictFile,ignoreCase);
    msg += String.format("EXPECT DICT SIZE: %d\n",expectDictSize);
    msg += String.format("ACTUAL DICT SIZE: %d\n",actualDictSize);
    assertEquals(msg, expectDictSize, actualDictSize);
    boolean expectChildClass = true;
    boolean actualChildClass = sc instanceof SpellChecker;
    msg += String.format("EXPECT IS CHILD CLASS: %s\n",expectChildClass);
    msg += String.format("ACTUAL IS CHILD CLASS: %s\n",actualChildClass);
    assertEquals(msg, expectChildClass, actualChildClass);
    return sc;
  }    

  // Tests for AutomaticSC constructors
  @Test(timeout=4000) public void asc_constructor_nonexistent_dict(){
    boolean ignoreCase = true;
    String dictFile = "does-not-exist.txt";
    int expectDictSize = 0;
    loadChecker(dictFile,ignoreCase,expectDictSize);
  }
  @Test(timeout=4000) public void asc_constructor_empty_dict(){
    boolean ignoreCase = true;
    String dictFile = "empty-dict.txt";
    int expectDictSize = 0;
    loadChecker(dictFile,ignoreCase,expectDictSize);
  }
  @Test(timeout=4000) public void asc_constructor_short_file(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    loadChecker(dictFile,ignoreCase,expectDictSize);
  }
  @Test(timeout=4000) public void asc_constructor_google_dict(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    loadChecker(dictFile,ignoreCase,expectDictSize);
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

  @Test(timeout=2000) public void asc_matchcase_1(){
    String [][] mod_src_res = {
      {"BANANA" , "apple" , "APPLE"}, 
      {"PEAR"   , "orange", "ORANGE"},
      {"Banana" , "orange", "Orange"},
      {"Apple"  , "pear"  , "Pear"},  
      {"banana" , "apple" , "apple"}, 
      {"banana" , "Apple" , "Apple"}, 
      {"BaNaNa" , "aPPle" , "aPPle"},
      {"peaR"   , "Orange", "Orange"},
    };
    for(String [] msr : mod_src_res){
      String model=msr[0], source=msr[1];
      String expectResult = msr[2];
      String actualResult = AutomaticSC.matchCase(model,source);
      String msg ="";
      msg += String.format("matchCase() incorrect\n");
      msg += String.format("model:  %s\n",model);
      msg += String.format("source: %s\n",source);
      msg += String.format("EXPECT RESULT: %s\n",expectResult);
      msg += String.format("ACTUAL RESULT: %s\n",actualResult);
      assertEquals(msg,expectResult,actualResult);
    }      
  }
  

  // Tests for correctWord()
  // No tests for empty dictionary
  @Test(timeout=4000) public void asc_correctword_ignorecase_2(){
    boolean ignoreCase = true;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object words_correct[][] = {
      {"apple"      ,"apple"  }, 
      {"Banana"     ,"Banana" },
      {"bruits"     ,"banana" },
      {"earldom's"  ,"carrot" },
      {"THIN"       ,"APPLE"  },
      {"undeniable" ,"apple"  },
      {"Slapped"    ,"Apple"  },
      {"condemners" ,"banana" },
      {"couching"   ,"banana" },
      {"DEODORized" ,"apple"  },
      {"necessary"  ,"banana" },
      {"gag's"      ,"apple"  },
      {"Klingon"    ,"Banana" },
      {"tricorder"  ,"carrot" },
      {"romulan"    ,"apple"  },
      {"Starfleet"  ,"Apple"  },
      {"SITH"       ,"APPLE"  },
      {"kyber"      ,"apple"  },
      {"lightsaber" ,"apple"  },
      {"Hoth"       ,"Apple"  },
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=4000) public void asc_correctword_ignorecase_3(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object words_correct[][] = {
      {"apple"      ,"apple"     },
      {"Banana"     ,"Banana"    },
      {"bruits"     ,"fruits"    },
      {"earldom's"  ,"carlos"    },
      {"THIN"       ,"THIN"      },
      {"undeniable" ,"enable"    },
      {"Slapped"    ,"Shipped"   },
      {"condemners" ,"consumers" },
      {"couching"   ,"coaching"  },
      {"DEODORized" ,"described" },
      {"necessary"  ,"necessary" },
      {"gag's"      ,"games"     },
      {"Klingon"    ,"Clinton"   },
      {"tricorder"  ,"recorder"  },
      {"romulan"    ,"roman"     },
      {"Starfleet"  ,"Street"    },
      {"SITH"       ,"WITH"      },
      {"kyber"      ,"cyber"     },
      {"lightsaber" ,"lighter"   },
      {"Hoth"       ,"Both"      },
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=4000) public void asc_correctword_noignorecase_2(){
    boolean ignoreCase = false;
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object words_correct[][] = {
      {"apple"      ,"apple"    },
      {"Banana"     ,"banana"   },  
      {"bruits"     ,"banana"   },  
      {"earldom's"  ,"carrot"   },  
      {"THIN"       ,"apple"    },  
      {"undeniable" ,"apple"    },  
      {"Slapped"    ,"apple"    },  
      {"condemners" ,"banana"   },  
      {"couching"   ,"banana"   },  
      {"DEODORized" ,"apple"    },  
      {"necessary"  ,"banana"   },  
      {"gag's"      ,"apple"    },  
      {"Klingon"    ,"banana"   },  
      {"tricorder"  ,"carrot"   },  
      {"romulan"    ,"apple"    },  
      {"Starfleet"  ,"apple"    },  
      {"SITH"       ,"apple"    },  
      {"kyber"      ,"apple"    },  
      {"lightsaber" ,"apple"    },  
      {"Hoth"       ,"apple"    },  
    };      
    check_correctWord(dictFile,ignoreCase,expectDictSize,words_correct);
  }
  @Test(timeout=4000) public void asc_correctword_noignorecase_3(){
    boolean ignoreCase = false;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object words_correct[][] = {
      {"apple"      ,"apple"     },
      {"Banana"     ,"banana"    },
      {"bruits"     ,"fruits"    },
      {"earldom's"  ,"carlos"    },
      {"THIN"       ,"the"       },
      {"undeniable" ,"enable"    },
      {"Slapped"    ,"wrapped"   },
      {"condemners" ,"consumers" },
      {"couching"   ,"coaching"  },
      {"DEODORized" ,"authorized"},
      {"necessary"  ,"necessary" },
      {"gag's"      ,"games"     },
      {"Klingon"    ,"clinton"   },
      {"tricorder"  ,"recorder"  },
      {"romulan"    ,"roman"     },
      {"Starfleet"  ,"street"    },
      {"SITH"       ,"the"       },
      {"kyber"      ,"cyber"     },
      {"lightsaber" ,"lighter"   },
      {"Hoth"       ,"both"      },
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
    String dictFile = "short-dict.txt";
    int expectDictSize = 3;
    Object docs_correct[][] = {
      {"Apple, banana, CARROT!",
       "Apple, banana, CARROT!",
      },
      {"One potatoe, two tumatoes, three potatoes, four. I misunderestimated how many potatoes.",
       "Apple apple, apple apple, apple apple, apple. APPLE banana apple banana apple.",
      },
      {"If I see one more POTATOE, I'm gonna FREAK out.",
       "Apple APPLE apple apple apple APPLE, Apple banana APPLE apple.",
      },
    };
    check_correctDocument(dictFile,ignoreCase,expectDictSize,docs_correct);
  }
  @Test(timeout=2000) public void sc_correctdoc_ignorecase_2(){
    boolean ignoreCase = true;
    String dictFile = "google-10000-english.txt";
    int expectDictSize = 10000;
    Object docs_correct[][] = {
      {"Foure scoire and seven years ago our fathers brouht forth on this continent, a\n"+
       "new nation, CONCEIVED in Liberty, and dedicated to the proposition that all men\n"+
       "are created equale.\n"+
       "",
       "Four score and seven years ago our fathers brought forth on this continent, a\n"+
       "new nation, CONCERNED in Liberty, and dedicated to the proposition that all men\n"+
       "are created equal.\n"+
       ""
      },
      {"foure Scoire and seven years ago our fathers BROUHT forth on this continent, a\n"+
       "new nation, conceived in Liberty, and dedicated to the proposition that all men\n"+
       "are created Equale.\n"+
       "",
       "four Score and seven years ago our fathers BROUGHT forth on this continent, a\n"+
       "new nation, concerned in Liberty, and dedicated to the proposition that all men\n"+
       "are created Equal.\n"+
       ""
      },
      {"foURE scoire and seven years ago our fathers BrOuHt forth on this continent, a\n"+
       "new nation, conceived in Liberty, and dedicated to the proposition that all men\n"+
       "are created EQuale.\n"+
       "",
       "four score and seven years ago our fathers brought forth on this continent, a\n"+
       "new nation, concerned in Liberty, and dedicated to the proposition that all men\n"+
       "are created equal.\n"+
       ""
      },
    };
    check_correctDocument(dictFile,ignoreCase,expectDictSize,docs_correct);
  }

}

