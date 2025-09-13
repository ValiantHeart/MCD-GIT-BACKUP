""" Polybius Square Program, Python Implementation


variables
psTable: 2d array composing our square

functions:
    encode
    decode
    printTable
    editTable
    loadTable
    saveTable
"""
import pprint
class PolybiusSquare:
    psTable = [[A,B,C,D,E,F],[G,H,I,J,K,L],[M,N,O,P,Q,R],[S,T,U,V,W,X],[Y,Z,1,2,3,4],[5,6,7,8,9,0]]
    #Object functions:
    def __init__(self, psTable = [A,B,C,D,E,F],[G,H,I,J,K,L],[M,N,O,P,Q,R],[S,T,U,V,W,X],[Y,Z,1,2,3,4],[5,6,7,8,9,0]):
        self.psTable = psTable
    def __str__(self):
        return 
    #main functions
    def decode(string):
        for x in string:
    def encode(string):
        cleaned = ""
        encodedString = ""
        if " " not in sting:
            continue
        else
            cleaned = this.cleanString(string);
            
        for x in range(len(sting))
            for i in this.psTable:
                for j in this.psTable[i]:
                    if x == psTable[i][j] or x == psTable[][].:
                        encodedString.append(i+1,j+1 + " ")
            
        return encodedString    
    #Table Functions
    def printTable():
        pp = pprint.PrettyPrinter()
        return True
    def editTable(x, y, char):
        this.psTable[y][x] = char
        return True
    def loadTable(filename):
        
    def saveTable(filename = "PolybiusSquare.txt"):
        
