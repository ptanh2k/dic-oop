package Controller;

import Model.Dictionary;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    public void openFileToWrite(String fileName){
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeFileAfterWrite(String fileName){
        try{
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFileToRead(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) { // nếu file chưa tồn tại
                file.createNewFile(); // tạo mới file này
            }
            scanner = new Scanner(Paths.get(fileName), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterRead(String fileName) {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeWordToFile(Dictionary dict,String fileName){
        openFileToWrite(fileName);
        printWriter.println(dict.getWordID() + "|" +dict.getWord_target()
                +"|"+dict.getWord_explain());
        closeFileAfterWrite(fileName);
    }

    public ArrayList<Dictionary> readWordFromFile(String fileName){
        openFileToRead(fileName);
        ArrayList<Dictionary> dicts = new ArrayList<>();
        while(scanner.hasNextLine()){
            String data = scanner.nextLine();
            Dictionary dict = createWordFromData(data);
            dicts.add(dict);
        }
        closeFileAfterRead(fileName);
        return dicts;
    }

    public Dictionary createWordFromData(String data){
        String[] datas = data.split("\\|");

        Dictionary dict = new Dictionary(Integer.parseInt(datas[0]),datas[1],datas[2]);

        return dict;
    }
}
