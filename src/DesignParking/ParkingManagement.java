package DesignParking;

/**
 * Класс реализации;
 */

import java.util.Scanner;

public class ParkingManagement {

    private Stack S = new Stack(5);
    private Queue<CarInfo> Q = new Queue<CarInfo>();
    private double fee = 2;
    public final static int DEPARTURE = 0;
    public final static int ARRIVAL = 1;


    // Управление парковкой, лицензия указывает номерной знак, действие указывает на действие транспортного средства,
    // прибыл или уехал
    public void parkingManage(String license, String action, int time) throws Exception {
        if ("arrive".equals(action)) {
            CarInfo info = new CarInfo();
            info.license = license;
            if (S.gettop() < 5) {
                info.atime = time;
                info.state = ARRIVAL;
                S.push(info);
                System.out.println("Plate number + " + info.license + "Car in" + time + "Always on parking"
                        + S.gettop() + "Position!");
            } else {
                Q.push(info);
                System.out.println("Plate number + " + info.license + "Car is parcked on the street"
                        + Q.length() + "Position!");
            }
        } else if ("depart".equals(action)) {
            CarInfo info = null;
            int location = 0;// Для записи положения автомобиля
            // Создание нового стека для хранения других транспортных средств, временно снятых со стоянки из-за отъезда транспортного средства
            Stack S2 = new Stack(S.gettop());
            for (int i = S.gettop(); i > 0; i--) {
                info = (CarInfo) S.pop();
                if (info.license.equals(license)) {
                    info.dtime = time;
                    info.state = DEPARTURE;
                    location = i;
                    break;
                } else S2.push(info);// Другие транспортные средства временно сняты с парковки
            }
            while (S2.gettop() != 0) {
                // Другие машины возвращаются во двор
                S.push(S2.pop());
            }
            if (location != 0) {// На стоянке есть автомобили с указанным номером
                // Вычисляем время парковки и меняем миллисекунды на минуты
                int stime = info.dtime - info.atime;
                System.out.println("Plate number + " + info.license + "Car is parcked" + stime
                        + "1 min cost: " + (stime * fee));
            }
            if (!Q.isEmpty()) {
                info = (CarInfo) Q.pop();
                info.atime = time;
                info.state = ARRIVAL;
                S.push(info);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ParkingManagement plms = new ParkingManagement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Parking System!" + "Clue means that you come to parking, "
                + "exit clue means leaving the parking" + "Else" + "Else" + "means exit from system.");
        System.out.println("Parking contains only five cars!");
        System.out.println("-----------------------------------------------------------------------------------");
        while (true) {
            System.out.println("Please enter arrival or departure or exit, license plate, and at this point, "
                    + "separated by spaces");
            String action = sc.next();
            String license = sc.next();
            int time = sc.nextInt();
            plms.parkingManage(license, action, time);
            if ("exit".equals(action)) {
                System.out.println("Sucsessful! You exit the system!");
                break;
            }
        }

    }
}
