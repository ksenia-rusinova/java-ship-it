package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<FragileParcel> frParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(30);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(20);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    addTrack();
                    break;
                case 5:
                    getListOfParcelsForEveryTypeOfBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Добавить мониторинг доставки (функция доступна только для хрупких посылок)");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Введите тип посылки 1, 2 или 3. 1.Стандартная посылка; 2.Хрупкая посылка; 3.Скоропортящаяся посылка;");
        int type = scanner.nextInt();
        scanner.nextLine();

        switch(type){
            case 1:
                System.out.println("Введите краткое описание посылки.");
                String descriptionStandard = scanner.nextLine();

                System.out.println("Введите вес посылки целым числом.");
                int weightStandard = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите адрес места назначения посылки.");
                String deliveryAddressStandard = scanner.nextLine();

                int sendDayStandard = checkSendDay();

                StandardParcel sp = new StandardParcel(sendDayStandard, deliveryAddressStandard, weightStandard, descriptionStandard);
                allParcels.add(sp);
                standardBox.addParcel(sp);
                break;
            case 2:
                System.out.println("Введите краткое описание посылки.");
                String descriptionFragile = scanner.nextLine();

                System.out.println("Введите вес посылки целым числом.");
                int weightFragile = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите адрес места назначения посылки.");
                String deliveryAddressFragile = scanner.nextLine();

                int sendDayFragile = checkSendDay();

                FragileParcel fp = new FragileParcel(sendDayFragile, deliveryAddressFragile, weightFragile, descriptionFragile);
                allParcels.add(fp);
                frParcels.add(fp);
                fragileBox.addParcel(fp);
                break;
            case 3:
                System.out.println("Введите краткое описание посылки.");
                String descriptionPerishable = scanner.nextLine();

                System.out.println("Введите вес посылки целым числом.");
                int weightPerishable = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите адрес места назначения посылки.");
                String deliveryAddressPerishable = scanner.nextLine();

                int sendDayPerishable = checkSendDay();

                System.out.println("Введите срок в днях, за который посылка не испортится.");
                int timeToLivePerishable = scanner.nextInt();
                scanner.nextLine();

                PerishableParcel pp = new PerishableParcel(sendDayPerishable, deliveryAddressPerishable, weightPerishable, descriptionPerishable, timeToLivePerishable);
                allParcels.add(pp);
                perishableBox.addParcel(pp);
                break;
            default:
                System.out.println("Такого типа посылки не существует.");
        }
    }

    private static void sendParcels() {
        if(!allParcels.isEmpty()){
            for(Parcel parcel : allParcels){
                parcel.packageItem(parcel.description);
                parcel.deliver(parcel.description, parcel.deliveryAddress);
            }
        } else {
            System.out.println("Посылки не добавлены.");
        }
    }

    private static void calculateCosts() {
        int total = 0;
        for(Parcel parcel : allParcels){
            total += parcel.calculateDeliveryCost(parcel.weight);
        }

        System.out.println("Общая стоимость всех доставок: " + total);
    }

    private static int checkSendDay() {
        System.out.println("Введите день месяца, в который посылка была отправлена.");
        int sendDay = 0;
        sendDay = scanner.nextInt();
        scanner.nextLine();
        while(sendDay < 0 || sendDay > 31){
            System.out.println("Введите число от 1 до 31.");
            sendDay = scanner.nextInt();
            scanner.nextLine();
        }
        return sendDay;
    }

    private static void addTrack(){
        if(!frParcels.isEmpty()){
            System.out.println("Введите новое местоположение посылки.");
            String newLocation = scanner.nextLine();

            for(FragileParcel frParcel : frParcels){
                frParcel.reportStatus(newLocation);
            }
        } else {
            System.out.println("Хрупкие посылки не найдены.");
        }
    }

    private static void getListOfParcelsForEveryTypeOfBox(){
        System.out.println("Введите тип посылки: 1, 2 или 3. 1.Стандартная посылка; 2.Хрупкая посылка; 3.Скоропортящаяся посылка;");
        int type = scanner.nextInt();
        scanner.nextLine();

        if(type == 1){
            if(!standardBox.getAllParcels().isEmpty()){
                System.out.println("В коробке с типом посылки <Стандартная посылка> лежат следующие посылки:");
                for(StandardParcel sb : standardBox.getAllParcels()){
                    System.out.println(sb.description);
                }
            } else {
                System.out.println("Коробка пустая.");
            }
        } else if(type == 2){
            if(!fragileBox.getAllParcels().isEmpty()){
                System.out.println("В коробке с типом посылки <Хрупкая посылка> лежат следующие посылки:");
                for(FragileParcel fb : fragileBox.getAllParcels()){
                    System.out.println(fb.description);
                }
            } else {
                System.out.println("Коробка пустая.");
            }
        } else if(type == 3){
            if(!perishableBox.getAllParcels().isEmpty()){
                System.out.println("В коробке с типом посылки <Скоропортящаяся посылка> лежат следующие посылки:");
                for(PerishableParcel pb : perishableBox.getAllParcels()){
                    System.out.println(pb.description);
                }
            } else {
                System.out.println("Коробка пустая.");
            }
        } else {
            System.out.println("Такого типа посылки нет.");
        }
    }
}

