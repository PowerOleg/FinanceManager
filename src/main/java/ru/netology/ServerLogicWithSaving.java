package ru.netology;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerLogicWithSaving extends ServerLogic implements Saving, Serializable {
    protected ServerLogic serverLogic;
    protected List<String[]> saves;                             //пронумерованный список всех произведенных запросов клиентов
    private static final long serialVersionUID = 1L;


    public ServerLogicWithSaving(File file, ServerLogic serverLogic) {
        super(file);
        this.serverLogic = serverLogic;
        try {
            this.saves = ServerLogicWithSaving.load().getSaves();
            this.mapMaxCategories = ServerLogicWithSaving.load().getMapMaxCategories();
            System.out.println(saves);                                                  //d
            System.out.println(mapMaxCategories);                                       //d

        } catch (RuntimeException e) {
            System.out.println("Отсутствует файл data.bin. Будет создан новый");
            this.saves = new ArrayList<>();
            this.mapMaxCategories = new HashMap<>();
        }

    }
    //добавляется подготовка данных и запись данных
    @Override
    public String response(String clientRequest) throws IOException {
        String response = serverLogic.response(clientRequest);
        ProductPurchase productPurchase = getProductPurchase(clientRequest);
//[0] это номер строчки
        int id = 0;
        for (String[] s : saves) {
            if (Integer.parseInt(s[0]) > id) id = Integer.parseInt(s[0]);
        }
//[1] это наименование товара
        String product = productPurchase.getTitle();
//[2] это наименование категории
        String category = checkProductCategory(productPurchase);
//[3] это дата
        String date = productPurchase.getDate();
//[4] это сумма
        String sum = String.valueOf(productPurchase.getSum());

        String[] save = {String.valueOf(id+1), product, category, date, sum};
        this.addSave(save);
        System.out.println(getMapMaxCategories());                                          //d
        this.save();
        return response;
    }


    public void addSave(String[] save) {
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

    public static ServerLogicWithSaving load() {
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
    public Map<String, Integer> getMapMaxCategories() {
        return mapMaxCategories;
    }
}
