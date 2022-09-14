package io.javabrains.wordsearchapi.services;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WordGridService {




    private enum Direction{
        HORIZONTAL,
        VERTICAL,
        DIAGONAL,
        HORIZONTAL_INVERSE,
        VERTICAL_INVERSE,
        DIAGONAL_INVERSE

    }
    private class Coordinates{
        int x;
        int y;
        Coordinates(int x, int y){
            this.x=x;
            this.y=y;
        }
    }






    public char[][] generateGrid(List<String> words, int grid_size){
        List<Coordinates> coordinates=new ArrayList<>();
        char contents[][]=new char[grid_size][grid_size];
        for(int i=0;i<grid_size;i++)
        {
            for(int j=0;j<grid_size;j++)
            {
                coordinates.add(new Coordinates(i,j));
                contents[i][j]='_';
            }
        }



        for(String word:words){
            Collections.shuffle(coordinates);

            for(Coordinates coordinate:coordinates){
                int x=coordinate.x;
                int y=coordinate.y;

                Direction selectedDirection=getDirectionForFit(contents,word,coordinate);

                if(selectedDirection!=null)
                {
                    switch(selectedDirection)
                    {
                        case HORIZONTAL:
                            for(char c:word.toCharArray())
                            {
                                contents[x][y++]=c;
                            }
                            break;

                        case VERTICAL:
                            for(char c:word.toCharArray())
                            {
                                contents[x++][y]=c;
                            }
                            break;

                        case DIAGONAL:
                            for(char c:word.toCharArray())
                            {
                                contents[x++][y++]=c;
                            }
                            break;

                        case HORIZONTAL_INVERSE:
                            for(char c:word.toCharArray())
                            {
                                contents[x][y--]=c;
                            }
                            break;

                        case VERTICAL_INVERSE:
                            for(char c:word.toCharArray())
                            {
                                contents[x--][y]=c;
                            }
                            break;

                        case DIAGONAL_INVERSE:
                            for(char c:word.toCharArray())
                            {
                                contents[x--][y--]=c;
                            }
                            break;
                    }
                    break;
                }





            }




        }
        fillRandomLetters(contents);
        return contents;
    }

    public Direction getDirectionForFit(char[][] contents, String word, Coordinates coordinate)
    {
        List<Direction> directions= Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        for(Direction direction:directions){
            if(doesFit(contents, word, coordinate, direction)){

                return direction;
            }
        }
        return null;
    }

    public boolean doesFit(char[][] contents, String word, Coordinates coordinate, Direction direction)
    {
        int grid_size=contents[0].length;

        int wordLength=word.length();

        switch(direction)
        {
            case HORIZONTAL:

                if(coordinate.y + wordLength > grid_size)  return false;
                for(int i=0;i<wordLength;i++){
                    char current_letter= contents[coordinate.x][coordinate.y + i];
                    if( current_letter!= '_' && current_letter!= word.charAt(i) ) return false;
                }
                break;

            case VERTICAL:

                if(coordinate.x + wordLength > grid_size)  return false;
                for(int i=0; i<wordLength ;i++){
                    char current_letter=contents[coordinate.x + i][coordinate.y];
                    if( current_letter!= '_' && current_letter!= word.charAt(i) ) return false;
                }
                break;

            case DIAGONAL:

                if(coordinate.x + wordLength > grid_size || coordinate.y + wordLength >grid_size)  return false;
                for(int i=0;i < wordLength;i++){
                    char current_letter=contents[coordinate.x + i][coordinate.y + i];
                    if(current_letter!= '_' && current_letter!= word.charAt(i)) return false;
                }
                break;

            case HORIZONTAL_INVERSE:

                if(coordinate.y< wordLength)  return false;
                for(int i=0;i<wordLength;i++){
                    char current_letter=contents[coordinate.x][coordinate.y - i];
                    if(current_letter!= '_' && current_letter!= word.charAt(i)) return false;
                }
                break;

            case VERTICAL_INVERSE:


                if(coordinate.x < wordLength)  return false;
                for(int i=0; i<wordLength ;i++){
                    char current_letter=contents[coordinate.x - i][coordinate.y];
                    if(current_letter!= '_' && current_letter!= word.charAt(i)) return false;
                }
                break;

            case DIAGONAL_INVERSE:

                if(coordinate.x < wordLength || coordinate.y > wordLength)  return false;
                for(int i=0;i < wordLength;i++){
                    char current_letter=contents[coordinate.x - i][coordinate.y - i];
                    if(current_letter!= '_' && current_letter!= word.charAt(i)) return false;
                }
                break;


        }

        return true;
    }

    public void fillRandomLetters(char[][] contents){

        int grid_size=contents[0].length;
        String capLetters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0;i<grid_size;i++)
        {
            for(int j=0;j<grid_size;j++) {
                int randomIndex= ThreadLocalRandom.current().nextInt(0,capLetters.length());
                if(contents[i][j]=='_')
                {
                    contents[i][j]=capLetters.charAt(randomIndex);
                }
            }
        }


    }

    public void displayGrid(char[][] contents){
        int grid_size=contents[0].length;
        for(int i=0;i<grid_size;i++)
        {
            for(int j=0;j<grid_size;j++)
            {
                System.out.print(" "+contents[i][j]);
            }
            System.out.println();
        }
    }


}
