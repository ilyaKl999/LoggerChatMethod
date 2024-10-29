import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Logic {}

abstract class Logger {
        /*
        Методы successfully и danger:
        Принимают сообщение и затраченное время.Вызывают общий метод log с соответствующим цветом и уровнем сообщения.
        Общий метод log:
        Получает стек вызовов и извлекает информацию о вызывающем методе.Форматирует текущее время с использованием
        DateTimeFormatter.Выводит время и дату голубым цветом, имя класса фиолетовым цветом, уровень сообщения
        желтым цветом, сообщение с заданным цветом и затраченное время ярко-пурпурным цветом.

        Методы startMethod и endMethod:
        Возвращают текущее время в миллисекундах, которое можно использовать для измерения затраченного времени.*/

    // Константы для цветов
    public static final String ANSI_RESET = "\u001B[0m";  //Сбрасывает цвет текста и фона к значениям по умолчанию.
    public static final String ANSI_RED = "\u001B[31m";   //Красный цвет текста (danger).
    public static final String ANSI_GREEN = "\u001B[32m"; //Зеленый цвет текста (message).
    public static final String ANSI_YELLOW = "\u001B[33m";//Желтый цвет текста (level).
    public static final String ANSI_PURPLE = "\u001B[35m";//Фиолетовый цвет текста (methodName/className).
    public static final String ANSI_CYAN = "\u001B[36m";  //Голубой цвет текста (timestamp).
    public static final String ANSI_BRIGHT_MAGENTA = "\u001B[95m"; // Ярко-пурпурный цвет (elapsedTime).

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Метод для логирования успешных сообщений
    public static void successfully(String message, long elapsedTime) {
        // Вызываем общий метод логирования с зеленым цветом и уровнем "SUCCESS"
        log(message, elapsedTime, ANSI_GREEN, "SUCCESS");
    }

    // Метод для логирования опасных сообщений
    public static void danger(String message, long elapsedTime) {
        // Вызываем общий метод логирования с красным цветом и уровнем "DANGER"
        log(message, elapsedTime, ANSI_RED, "DANGER");
    }

    // Общий метод для логирования
    private static void log(String message, long elapsedTime, String color, String level) {
        // Получаем стек вызовов и извлекаем информацию о вызывающем методе
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[3]; // Индекс 3 соответствует вызывающему методу
        String className = caller.getClassName();
        String methodName = caller.getMethodName();
        String lineNumber = String.valueOf(caller.getLineNumber());
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        // Выводим время и дату голубым цветом
        System.out.print(ANSI_CYAN + "[" + timestamp + "] " + ANSI_RESET);
        // Выводим имя класса фиолетовым цветом
        System.out.print(ANSI_PURPLE + "[" + className + "." + methodName + ":" + lineNumber + "] " + ANSI_RESET);
        // Выводим уровень сообщения желтым цветом
        System.out.print(ANSI_YELLOW + "[" + level + "] " + ANSI_RESET);
        // Выводим сообщение с заданным цветом
        System.out.println(color + "[" + message + "] " + ANSI_BRIGHT_MAGENTA + "[Time: " + elapsedTime + " ms]" + ANSI_RESET);
    }

    // Метод для начала измерения времени
    public static long startMethod() {
        // Возвращаем текущее время в миллисекундах
        return System.currentTimeMillis();
    }

    // Метод для окончания измерения времени
    public static long endMethod() {
        // Возвращаем текущее время в миллисекундах
        return System.currentTimeMillis();
    }
}

class User implements Serializable {

    private final String login;        // Поле для хранения логина пользователя (неизменяемое)
    private String name;               // Поле для хранения имени пользователя
    private String password;           // Поле для хранения пароля пользователя
    private int age;                   // Поле для хранения возраста пользователя
    private ArrayList<Dialog> dialogs; // Поле для хранения списка диалогов пользователя

    public User(String login, String name, String password, int age) { // Конструктор для создания объекта User
        this.login = login;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    // Сет Гет методы
    public String getLogin() {return login;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public ArrayList<Dialog> getDialogs() {return dialogs;}
    public void setDialogs(ArrayList<Dialog> dialogs) {this.dialogs = dialogs;}

    // Переопределение метода equals для сравнения объектов User по логину
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    // Переопределение метода hashCode для создания хэш-кода на основе логина
    @Override
    public int hashCode() {
        return Objects.hashCode(login);
    }

    // Переопределение метода toString для представления объекта User в виде строки
    @Override
    public String toString() {
        return "[User:" + "login=" + login  + ", name=" + name + ", password=" + password  + ", age=" + age + ']';}
}
class Dialog implements Serializable {
    //Поля
    private final long ID;
    private final String TIME;
    private ArrayList <User> participants;
    private ArrayList <Message> messages;
    //Конструктор
    public Dialog(long ID, ArrayList<User> participants) {
        this.ID = ID;
        this.TIME = StaticMethods.getTime();
        this.participants = participants;
    }
    //Сетеры гетеры
    public long getID() {return ID;}
    public String getTIME() {return TIME;}
    public ArrayList<User> getParticipants() {return participants;}
    public void setParticipants(ArrayList<User> participants) {this.participants = participants;}
    public ArrayList<Message> getMessages() {return messages;}
    public void setMessages(ArrayList<Message> messages) {this.messages = messages;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dialog dialog = (Dialog) o;
        return ID == dialog.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }

    @Override
    public String toString() {
        return "[Dialog=" + "ID=" + ID + ", TIME=" + TIME  + ']';
    }
}
class Message implements Serializable {
    //Приватные поля
    private final String TIME;
    private final String MESSAGE;
    private final User SENDER;
    // конструктор
    public Message(String MESSAGE, User SENDER) {
        this.TIME = StaticMethods.getTime();
        this.MESSAGE = MESSAGE;
        this.SENDER = SENDER;
    }
    // сетеры гетеры
    public String getTIME() {return TIME;}
    public String getMESSAGE() {return MESSAGE;}
    public User getSENDER() {return SENDER;}
}

abstract class StaticMethods {
    private static final String FILE_NAME = "User";
    private static final String FILE_PATH = "Dialogs";

    //Метод для получения времени
    public static String getTime () {
        // Получаем текущую дату и время
        LocalDateTime now = LocalDateTime.now();
        // Создаем форматтер для нужного формата
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yy HH:mm");
        // Форматируем дату и время
        return now.format(formatter);
    }

    // Загрузка списка юзеров
    public static ArrayList<User> loadUsers() {
        long a = Logger.startMethod();// Начало измерения времени
        File file = new File(FILE_NAME);// Создание объекта File для проверки существования файла
        if (file.exists()) {// Проверка существования файла
            // Загружаем ArrayList<User> из файла
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<User> users = (ArrayList<User>) ois.readObject();
                long b = Logger.endMethod();// Окончание измерения времени и логирование успешного сообщения
                Logger.successfully("Файл Users загружен.", b - a);
                return users;// Возвращаем загруженный список пользователей
            } catch (IOException | ClassNotFoundException e) {
                long b = Logger.endMethod();// Окончание измерения времени и логирование сообщения об ошибке
                Logger.danger("ВНИМАНИЕ! Ошибка сериализации с файлом User", b - a);
            }
        } else {
            long b = Logger.endMethod();// Окончание измерения времени и логирование сообщения о том, что файл не существует
            Logger.successfully("Файл Users не существует. Будет возвращена пустая коллекция.", b - a);
        }
        return new ArrayList<>();// Если файл не существует или произошла ошибка, возвращаем пустой ArrayList<User>
    }

    // Сохранение списка клиентов
    public static void saveUsers(ArrayList<User> users) {
        long a = Logger.startMethod();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
            long b = Logger.endMethod();
            Logger.successfully("Список клиентов успешно сохранен после изменений",b-a);
        } catch (IOException e) {
            long b = Logger.endMethod();
            Logger.danger("Ошибка при сохранении списка клиентов",b-a);
        }
    }

    // Отчистка списка и удаление пользователей (Системное)
    public static void clearUsers(ArrayList<User> users) {
        long a = Logger.startMethod();
        try {
            users.clear();
            long b = Logger.endMethod();
            Logger.successfully("Список клиентов пуст", b - a);
        } catch (Exception as) {
            long b = Logger.endMethod();
            Logger.danger("Ошибка при удалении пользователей из списка", b - a);
        }
    }

    //Добавление пользователя в список (Регистрация)
    public boolean addUser(ArrayList<User> users, User newUser) {
        long a = Logger.startMethod();
        for (User user: users){
            if (user.getLogin().equals(newUser.getLogin())){
                long b = Logger.endMethod();
                Logger.danger("Такой пользователь уже существует, логин должен быть уникальный", b-a);
                return false;
            }
        }
        try {
            users.add(newUser);
            saveUsers(users);
            long b = Logger.endMethod();
            Logger.successfully("Пользователь успешно добавлен в список", b - a);
            return true;
        } catch (Exception e) {
            long b = Logger.endMethod();
            Logger.danger("Ошибка при добавлении пользователя в список", b - a);
            return false;
        }
    }

    // Показать в консоль весь список (Системное)
    public static void showUserList (ArrayList<User> users) {System.out.println(users);}

    // Поиск клиента (Авторизация)
    public static User findUser(ArrayList<User> users, String login, String password) {
        long a = Logger.startMethod();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                long b = Logger.endMethod();
                Logger.successfully("Пользователь найден успешно", b-a);
                return user;
            }
        }
        long b = Logger.endMethod();
        Logger.danger("Пользователь не найден", b-a);
        return null;
    }

    // Загрузка списка диалогов
    public static ArrayList<Dialog> loadDialogs() {
        long a = Logger.startMethod();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<Dialog> dialogs = (ArrayList<Dialog>) ois.readObject();
                long b = Logger.endMethod();
                Logger.successfully("Список диалогов успешно возвращен", b-a);
                return dialogs;
            } catch (IOException | ClassNotFoundException e) {
                long b = Logger.endMethod();
                Logger.danger("Списка диалогов не существует, будет возвращен пустой список", b-a);
            }
        }
        return new ArrayList<>();
    }

    // Сохранение списка диалогов в файл
    public static void saveDialogs(ArrayList<Dialog> dialogs) {
        long a = Logger.startMethod();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(dialogs);
            long b = Logger.endMethod();
            Logger.successfully("Список успешно сохранен в файл", b-a);
        } catch (IOException e) {
            long b = Logger.endMethod();
            Logger.danger("Ошибка при записи списка диалогов в файл", b-a);
        }
    }

    // Метод для добавления диалога в общий список
    public static boolean createDialog(User creator1, User user2 , ArrayList<Dialog> dialogs) {
        long a = Logger.startMethod();
        try {
        ArrayList<User> users = new ArrayList<>();
        users.add(creator1);
        users.add(user2);
        Dialog dialog = new Dialog(loadDialogs().size()+2,users);
        dialogs.add(dialog);
        saveDialogs(loadDialogs());
        long b = Logger.endMethod();
        Logger.successfully("Диалог успешно создан и добавлен в список", b-a);
        return true;
        }catch (Exception e) {
            long b = Logger.endMethod();
            Logger.danger("ВНИМАНИЕ! ошибка добавления диалога в список", b-a);
            return false;
        }
    }

    // Метод для очистки списка диалога
    public static void clearDialogs(ArrayList<Dialog> dialogs) {
        long a = Logger.startMethod();
        if (dialogs != null) {
            dialogs.clear();
        }
        saveDialogs(dialogs);
        long b = Logger.endMethod();
        Logger.successfully("Список диалогов очищен", b-a);
    }

    // Добавление сообщения
    public static boolean addMessage(ArrayList<Dialog> dialogs, Message message , Dialog dialog) {
        long a = Logger.startMethod();
        for (Dialog dialog1 : dialogs) {
            if (dialog1.getID()==dialog.getID()) {
                dialog1.getMessages().add(message);
                long b = Logger.endMethod();
                Logger.successfully("Сообщение успешно добавлено в диалог", b-a);
                return true;
            }
        }
        long b = Logger.endMethod();
        Logger.danger("ВНИМАНИЕ! Ошибка при добавлении сообщения", b-a);
        return false;
    }
}






