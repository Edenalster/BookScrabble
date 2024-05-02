package test;
import java.util.ArrayList;



public class Board {
    private static Board board=null;
    private Tile[][] gameBoard;
    private final String [][]score = {
            {"w3",null,null,"l2",null,null,null,"w3",null,null,null,"l2",null,null,"w3"},
            {null,"w2",null,null,null,"l3",null,null,null,"l3",null,null,null,"w2",null},
            {null,null,"w2",null,null,null,"l2",null,"l2",null,null,null,"w2",null,null},
            {"l2",null,null,"w2",null,null,null,"l2",null,null,null,"w2",null,null,"l2"},
            {null,null,null,null,"w2",null,null,null,null,null,"w2",null,null,null,null},
            {null,"l3",null,null,null,"l3",null,null,null,"l3",null,null,null,"l3",null},
            {null,null,"l2",null,null,null,"l2",null,"l2",null,null,null,"l2",null,null},
            {"w3",null,null,"l2",null,null,null,"s",null,null,null,"l2",null,null,"w3"},
            {null,null,"l2",null,null,null,"l2",null,"l2",null,null,null,"l2",null,null},
            {null,"l3",null,null,null,"l3",null,null,null,"l3",null,null,null,"l3",null},
            {null,null,null,null,"w2",null,null,null,null,null,"w2",null,null,null,null},
            {"l2",null,null,"w2",null,null,null,"l2",null,null,null,"w2",null,null,"l2"},
            {null,null,"w2",null,null,null,"l2",null,"l2",null,null,null,"w2",null,null},
            {null,"w2",null,null,null,"l3",null,null,null,"l3",null,null,null,"w2",null},
            {"w3",null,null,"l2",null,null,null,"w3",null,null,null,"l2",null,null,"w3"},
    };
    private Board(){
        gameBoard=new Tile[15][15];
    }
    public static Board getBoard(){
        if (board==null){
            board=new Board();
        }
        return board;
    }
    public Tile[][] getTiles(){
        return this.gameBoard;
    }
    public boolean boardLegal(Word word) {
        boolean isSide = false;

        if (word.getRow() < 0 || word.getRow() > 14 || word.getCol() < 0 || word.getCol() > 14) {
            return false;
        }

        if (gameBoard[7][7] == null) {
            if (word.isVertical() && word.getRow() <= 7 && word.getRow() + word.getTiles().length >= 7 && word.getRow() + word.getTiles().length <= 14 && word.getCol() == 7) {
                return true;
            }

            if (!word.isVertical() && word.getCol() <= 7 && word.getCol() + word.getTiles().length >= 7 && word.getCol() + word.getTiles().length <= 14 && word.getRow() == 7) {
                return true;
            }

            return false;
        }

        for (int i = 0; i < word.getTiles().length; i++) {
            if (word.getTiles()[i] == null) {
                isSide = true;
                continue;
            }

            if (word.isVertical()) {
                if (word.getRow() + i > 14) {
                    return false;
                }

                if (gameBoard[word.getRow() + i][word.getCol()] != null && !(gameBoard[word.getRow() + i][word.getCol()].equals(word.getTiles()[i]))) {
                    return false;
                }

                if ((word.getCol() + 1 < 15 && gameBoard[word.getRow() + i][word.getCol() + 1] != null) || (word.getCol() - 1 >= 0 && gameBoard[word.getRow() + i][word.getCol() - 1] != null)) {
                    isSide = true;
                }

                if ((i == 0 && word.getRow() - 1 >= 0 && gameBoard[word.getRow() - 1][word.getCol()] != null) || (i == word.getTiles().length - 1 && word.getRow() + 1 < 15 && gameBoard[word.getRow() + 1][word.getCol()] != null)) {
                    isSide = true;
                }
            } else {
                if (word.getCol() + i > 14) {
                    return false;
                }

                if (gameBoard[word.getRow()][word.getCol() + i] != null && !(gameBoard[word.getRow()][word.getCol() + i].letter==word.getTiles()[i].letter)) {
                    return false;
                }

                if ((word.getRow() + 1 < 15 && gameBoard[word.getRow() + 1][word.getCol() + i] != null) || (word.getRow() - 1 >= 0 && gameBoard[word.getRow() - 1][word.getCol() + i] != null)) {
                    isSide = true;
                }

                if ((i == 0 && word.getCol() - 1 >= 0 && gameBoard[word.getRow()][word.getCol() - 1] != null) || (i == word.getTiles().length - 1 && word.getCol() + 1 < 15 && gameBoard[word.getRow()][word.getCol() + 1] != null)) {
                    isSide = true;
                }
            }
        }

        return isSide;
    }

    public boolean dictionaryLegal(Word word){
        return true;
    }

    public ArrayList<Word> getWords(Word word){
        ArrayList<Word>words = new ArrayList<Word>();
        int j=0,row=0,col=0;

        words.add(word);
        for (int i = 0; i <word.getTiles().length ; i++) {
            ArrayList<Tile>tiles = new ArrayList<Tile>();
            if(word.isVertical()){
                if((word.getCol()-1>=0&&gameBoard[word.getRow()-i][word.getCol()-1]==null)&&(word.getCol()+1<15&&gameBoard[word.getRow()-i][word.getCol()+1]==null)){
                    continue;
                }
                if((word.getCol()-1>=0&&gameBoard[word.getRow()-i][word.getCol()-1]==null)&&(word.getCol()+1>14)){
                    continue;
                }
                if((word.getCol()+1<15&&gameBoard[word.getRow()-i][word.getCol()+1]==null)&&(word.getCol()-1<0)){
                    continue;
                }
                if(gameBoard[word.getRow()-i][word.getCol()]!=null){
                    continue;
                }
                row=word.getRow()-i;
                tiles.add(word.getTiles()[i]);

                while(word.getCol()-j-1>0&&gameBoard[word.getRow()-i][word.getCol()-j-1]!=null){
                    if(!(gameBoard[word.getRow()][word.getCol()+1]!=null |gameBoard[word.getRow()][word.getCol()-1]!=null )){
                        col=word.getCol()-j-1;


                    }
                    tiles.add(0,gameBoard[word.getRow()-i][word.getCol()-j-1]);
                    j++;
                }
                j=0;
                while(word.getCol()+j+1<15&&gameBoard[word.getRow()-i][word.getCol()+j+1]!=null){
                    tiles.add(gameBoard[word.getRow()-i][word.getCol()+j+1]);
                    j++;
                }
                j=0;
            }
            else{
                if((word.getRow()-1>=0&&gameBoard[word.getRow()-1][word.getCol()+i]==null)&&(word.getRow()+1<15&&gameBoard[word.getRow()+1][word.getCol()+i]==null)){
                    continue;
                }
                if((word.getRow()-1>=0&&gameBoard[word.getRow()-1][word.getCol()+i]==null)&&(word.getRow()+1>14)){
                    continue;
                }
                if((word.getRow()+1<15&&gameBoard[word.getRow()+1][word.getCol()+i]==null)&&(word.getRow()-1<0)){
                    continue;
                }
                if(gameBoard[word.getRow()][word.getCol()+i]!=null){
                    continue;
                }
                col = word.getCol()+i;
                tiles.add(word.getTiles()[i]);
                while(word.getRow()-j-1>0&&gameBoard[word.getRow()-j-1][word.getCol()+i]!=null){
                    if(!(gameBoard[word.getRow()+1][word.getCol()]!=null ||gameBoard[word.getRow()-1][word.getCol()]!=null )){
                        row=word.getRow()-j-1;

                    }
                    tiles.add(0,gameBoard[word.getRow()-j-1][word.getCol()+i]);
                    j++;
                }
                j=0;

                while(word.getRow()+j+1<15&&gameBoard[word.getRow()+j+1][word.getCol()+i]!=null){
                    tiles.add(gameBoard[word.getRow()+j+1][word.getCol()+i]);
                    j++;
                }
                j=0;
            }
            Tile[]tilelist = new Tile[tiles.size()];
            tilelist = tiles.toArray(tilelist);
            words.add(new Word(tilelist,row,col,!word.isVertical()));

        }


        return words;
    }
    public int getScore(Word word){
        int wordS=0;
        int multi=0;
        int letterS=0;

        for( int i=0;i<word.getTiles().length;i++){
            if(word.isVertical()){
                if(word.getTiles()[i]!=null){
                    letterS=word.getTiles()[i].score;
                } else{
                    if (gameBoard[word.getRow() + i][word.getCol()] != null) {
                        letterS = this.gameBoard[word.getRow() + i][word.getCol()].score;
                    }//letterS=this.gameBoard[word.getRow()+i][word.getCol()].score;
                }
                if(this.score[word.getRow()+i][word.getCol()]!=null){
                    switch(this.score[word.getRow()+i][word.getCol()].charAt(0)){
                        case 'w':
                            multi+= Character.getNumericValue(this.score[word.getRow()+i][word.getCol()].charAt(1));
                            wordS+=letterS;
                            break;
                        case 'l':
                            wordS+=letterS*Character.getNumericValue(this.score[word.getRow()+i][word.getCol()].charAt(1));
                            break;
                        case 's':
                            if(this.gameBoard[7][7]==null){
                                multi+=2;
                            }
                            wordS+=letterS;
                            break;
                    }
                } else {
                    wordS+=letterS;
                }
            }else {
                if(word.getTiles()[i]!=null) {
                    letterS = word.getTiles()[i].score;
                } else{
                    letterS=this.gameBoard[word.getRow()][word.getCol()+i].score;
                }
                if(this.score[word.getRow()][word.getCol()+i]!=null){
                    switch(this.score[word.getRow()][word.getCol()+i].charAt(0)){
                        case 'w':
                            multi+=Character.getNumericValue(this.score[word.getRow()][word.getCol()+i].charAt(1));
                            wordS+=letterS;
                            break;
                        case 'l':
                            wordS+=letterS*Character.getNumericValue(this.score[word.getRow()][word.getCol()+i].charAt(1));
                            break;
                        case 's':
                            if(this.gameBoard[7][7]==null){
                                multi+=2;
                            }
                            wordS+=letterS;
                            break;
                    }
                }else {
                    wordS+=letterS;
                }
            }
        }
        if(multi>0){
            wordS=wordS*multi;
        }
        return wordS;
    }
    public int tryPlaceWord(Word word){
        int totalS=0;
        if(!this.boardLegal(word)){
            return 0;
        }
        ArrayList<Word>allW=this.getWords(word);
        for(Word all:allW){
            if(!this.dictionaryLegal(all)){
                return 0;
            }
            totalS+=this.getScore(all);
        }
        for(int i=0; i<word.getTiles().length;i++){
            if(word.getTiles()[i]==null){
                continue;
            }
            if(word.isVertical()){
                gameBoard[word.getRow()+i][word.getCol()]=word.getTiles()[i];
            } else {
                gameBoard[word.getRow()][word.getCol()+i]=word.getTiles()[i];
            }
        }
        return totalS;
    }




}
