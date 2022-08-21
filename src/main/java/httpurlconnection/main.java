package httpurlconnection;

import java.io.*;
import static httpurlconnection.HttpWorker.showAllCommentsFromLastPostOfDistinctUserByUserId;
import static httpurlconnection.HttpWorker.showUnDoneToDoesForDistinctUser;;

public class main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        HttpWorker.userAdd();//при відправці на адреси конкретних об'єктів - 404. При відправці в місце зберігання об'єктів - додає юзера з айді після останнього.
        HttpWorker.userUpdate();//Якщо відправляти на сторінку (айді 1) об'єкт з айді 1 то все працює за вимогами завдання. Але якщо відправити на айді інших існуючих об'єктів - сервер збереже його з айді згідно адреси на яку його відправляємо. Якщо відправити на адресу неіснуючого - код відповіді 500
        HttpWorker.removeUserByID(-4);//Чому сервер не кидає помилку?  Сервер чомусь генерує порожні сторінки на довільно задані адреси в https://jsonplaceholder.typicode.com/ З цього сайту неможливо добути помилку 404 взагалі
        HttpWorker.printUsersData();
        HttpWorker.printUserDataByID(11);//1-10//Як виявилось, помилку 404 сайт кидати уміє, але, схоже,  тільки для GET
        HttpWorker.printUsersDataByName("Patricia Lebsack");
        showAllCommentsFromLastPostOfDistinctUserByUserId(10);
        showUnDoneToDoesForDistinctUser(1);
        //HttpClient від apache на запити пут і пост повертає неповні об'єкти (там тільки актуальний айді елемента і більш нічого).
        //Швидко розібратись чому так не вдалося, тому вирішив ним не користуватись.
    }
}