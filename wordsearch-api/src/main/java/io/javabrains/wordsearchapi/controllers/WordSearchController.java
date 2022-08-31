package io.javabrains.wordsearchapi.controllers;

import io.javabrains.wordsearchapi.services.WordGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController("/")

public class WordSearchController {


    @Autowired
    WordGridService wordGridService;

    @GetMapping("/wordgrid")
    public String createGrid(@RequestParam int gridSize, @RequestParam String wordList ){

        List<String> words= Arrays.asList(wordList.split("-"));

        char grid[][]=wordGridService.generateGrid(words,gridSize);
        String gridString="";
        for(int i=0;i<gridSize;i++)
        {
            for(int j=0;j<gridSize;j++)
            {
                gridString+=grid[i][j]+ " ";
            }
            gridString+="\r\n";
        }
        return gridString;

    }


}
