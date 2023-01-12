package ru.netology;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServerLogicWithSaving extends ServerLogic implements Saving, Serializable {
    protected ServerLogic serverLogic;
    protected List<String[]> saves;

    public ServerLogicWithSaving(File file, ServerLogic serverLogic) {
        super(file);
        this.serverLogic = serverLogic;
        saves = new ArrayList<>();                                                      //d
//        this.saves = load();
    }

//сохранение данных
//        saves.forEach(n -> System.out.println(Arrays.deepToString(n)));  //save();       //2 надо сделать save() перед выводом

    public void add(String[] save) {                                    //d
        saves.add(save);
    }


    @Override
    public void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("data.bin")));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ServerLogicWithSaving load() {
        ServerLogicWithSaving serverLogicWithSaving1;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get("data.bin")));
            serverLogicWithSaving1 = (ServerLogicWithSaving) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return serverLogicWithSaving1;
    }

    public List<String[]> getSaves() {
        return saves;
    }

}
