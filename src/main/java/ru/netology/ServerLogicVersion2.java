//package ru.netology;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class ServerLogicVersion2 extends ServerLogic implements Saving {
//    ServerLogic serverLogic;
//    private List<String[]> saves;
//
//
//
//    //сохранение данных                                                                    //d
////        saves.forEach(n -> System.out.println(Arrays.deepToString(n)));  //save();       //2 надо сделать save() перед выводом
//
//
//    //d
//    @Override
//    public void save() {
//
//        try {
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("data.bin")));
//            objectOutputStream.writeObject(this);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    //d
//    @Override
//    public ServerLogic load() {
//        ServerLogic serverLogic1;
//        try {
//            ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get("data.bin")));
//            serverLogic1 = (ServerLogic) objectInputStream.readObject();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return serverLogic1;
//    }
//
//    public List<String[]> getSaves() {
//        return saves;
//    }                                   //d
//
//}
