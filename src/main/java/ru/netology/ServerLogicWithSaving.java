package ru.netology;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerLogicWithSaving extends ServerLogic implements Serializable {
    private static final long serialVersionUID = 1L;
    protected ServerLogic serverLogic;
    protected List<String[]> saves;                             //пронумерованный список всех произведенных запросов клиентов
    
    public ServerLogicWithSaving(File file, ServerLogic serverLogic) {
        super(file);
        try {
            this.saves = ServerLogicWithSaving.load().getSaves();
            System.out.println("1 "+saves);                                                             //d
            this.serverLogic = ServerLogicWithSaving.load().getServerLogic();
            System.out.println(this.serverLogic.mapMaxCategories);                                      //d
            try {
                this.serverLogic.setProducts(products = tsv_parser.parse(file, 2));
                this.serverLogic.setCategories(categories = tsv_parser.parse(file, 3));
            } catch (IOException e) {
                System.out.println("Отсутствуют данные по списку продуктов или категориям продуктов");
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            System.out.println("Отсутствует файл data.bin. Будет создан новый");
            this.saves = new ArrayList<>();
            this.mapMaxCategories = new HashMap<>();
            this.serverLogic = serverLogic;
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

    //добавляется подготовка данных и запись данных
    @Override
    public String response(String clientRequest) throws IOException {

                                                //нужно написать чтобы выполнялись старые нужные функции этого метода
        String response = serverLogic.response(clientRequest);           //!нужно ставить super чтобы выполнялись новые переопределенные методы и с обновлениями
        updateSaves(clientRequest);
        this.save();
        return response;
    }

    public void updateSaves(String clientRequest) throws IOException {
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

        String[] save = {String.valueOf(id + 1), product, category, date, sum};
        this.addSave(save);
        System.out.println("2 "+ saves);                                                        //d
    }

    public void addSave(String[] save) {
        saves.add(save);
    }

    public void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("data.bin")));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> getSaves() {
        return saves;
    }

    public ServerLogic getServerLogic() {
        return serverLogic;
    }
}
